package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.BraetDTO;
import ModelEnteties.SpilData;
import ModelEnteties.Terning.FalskRaflebaeger;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.felter.FeltDTO;
import ModelEnteties.singletoner.RandomSingleton;
import spillogik.BevaegelsesLogik;

import java.util.Random;

public class SpilController extends SpilData {

    //|----------- Metoder:------------------

    /**
     * Hvorfor 2 constructore?
     * jo fordi man kan enten konstruere et spil med default configurationer eller man kan selv
     * vælge dem. Dette er også meget nyttigt til test :)
     */
    public SpilController(UserInterfaceKontrakt gui) {
        this.setUserInterfaceKontrakt(gui);
        startMenu();
        genererSpillere(getAntalSpillere());
        BraetCO spilleBret = new BraetCO(getAntalFelter(), getUserInterfaceKontrakt());
        //RafleBaeger terningsKrus = new RafleBaeger(getAntalTerninger());
        FalskRaflebaeger terningsKrus = new FalskRaflebaeger(getAntalTerninger());
        setTerningeKrus(terningsKrus);
        setBretGeneretForSpil(spilleBret);
        gui.genererGUIBret(spilleBret, getSpillerObjekter());
    }

    public SpilController(int antalSpillere, int antalFelter, int antalTerninger, int bankeRaadtGrense, UserInterfaceKontrakt gui) {
        this.setAntalSpillere(antalSpillere);
        this.setAntalFelter(antalFelter);
        this.setAntalTerninger(antalTerninger);
        this.setBankeraadGraense(bankeRaadtGrense);
        this.setUserInterfaceKontrakt(gui);
        genererSpillere(getAntalSpillere());
        BraetCO spilleBret = new BraetCO(getAntalFelter(), getUserInterfaceKontrakt());
        RafleBaeger terningsKrus = new RafleBaeger(getAntalTerninger());
        //FalskRaflebaeger terningsKrus = new FalskRaflebaeger(getAntalTerninger());
        setTerningeKrus(terningsKrus);
        setBretGeneretForSpil(spilleBret);
        gui.genererGUIBret(spilleBret, getSpillerObjekter());
    }
    //_____________________________________
    // Diverse:

    /**
     * Indsæt beskrivelse her
     * @param antalSpillere
     */
    public void genererSpillere(int antalSpillere) {
        for (int i = 0; i < antalSpillere; i++) {
            String navn = getUserInterfaceKontrakt().spillerNavne();
            SpillerCO deltager = new SpillerCO(navn, i, 0);
            getSpillerObjekter().add(deltager);
        }
    }

    /**
     * Indsæt beskrivelse her
     */
    public void printSpilleresInfo() {
        for (int i = 0; i < getSpillerObjekter().size(); i++) {
            getSpillerObjekter().get(i).printSpillerStats(getUserInterfaceKontrakt());
        }
    }

    /**
     * @author Filip
     * Metode, der afgør om en faengslet spiller løslades eller skal blive i faengsel.
     */
    public void anketDomsigelse() {
        Random ra = new Random();
        int domsAfsigelseDel1 = ra.nextInt(5) + 1;
        int domsAfsigelseDel2 = ra.nextInt(5) + 1;
        getUserInterfaceKontrakt().retsTerninger(domsAfsigelseDel1, domsAfsigelseDel2);
        if (domsAfsigelseDel1 == domsAfsigelseDel2) {
            getUserInterfaceKontrakt().heldIRetten();
            getSpillerMedTur().setFaengselsStraf(false);
            getSpillerMedTur().setSpillerPosition(domsAfsigelseDel1 + domsAfsigelseDel2);
        } else {
            getSpillerMedTur().setFaengselsStraf(true);
            getUserInterfaceKontrakt().ingenHeldIRetten();
        }

    }

    /**
     * Indsæt beskrivelse her
     */
    public void slutSpillerTur() {
        getSpillerMedTur().setHarSlaaetForTuren(false);
        getSpillerMedTur().setHarAnketDomDenneRunde(false);
        tjekForBankeRaadt();

        if (getSpillerTur() >= getAntalSpillere()) {
            setSpillerTur(1);

        } else if (getSpillerTur() <= getAntalSpillere()) {
            setSpillerTur(getSpillerTur() + 1);
        }


    }

    /**
     * Indsæt beskrivelse her
     */
    public void tjekForBankeRaadt() {

        if (getSpillerMedTur().getPenge() < getBankeraadGraense()) {
            getUserInterfaceKontrakt().bankeRaadtGrundetLiquditet(getBankeraadGraense());
            getSpillerMedTur().setHarGivetOp(true);
            getSpillerMedTur().getSpillerEjendomme().clear();
            int udgaaetSpiller = getSpillerMedTur().getId() + 1;
            getUserInterfaceKontrakt().spillerUdgår(udgaaetSpiller);
        }

    }

    /**
     * Indsæt beskrivelse her
     * @param terningsKrus
     */
    public void kastTerninger(RafleBaeger terningsKrus) {
        if (!getSpillerMedTur().isHarSlaaetForTuren()) {

            terningsKrus.slaa();

            getUserInterfaceKontrakt().spillerRykkerGrundetTerningslag(terningsKrus, getSpillerTur());

            if (terningsKrus.erEns()) {
                getUserInterfaceKontrakt().ensTerninger();
                getSpillerMedTur().setHarSlaaetForTuren(false);
            } else {
                getSpillerMedTur().setHarSlaaetForTuren(true);
            }

            rykSpillerAntalFelter(getSpillerMedTur(), getTerningeKrus().getTotalVaerdi());

        } else {
            getUserInterfaceKontrakt().harSlaaetMedTerningfor();
        }
    }


    /**
     * @author Malte
     * Rykker spilleren et bestemt antal felter fremad. Den beregner hvor mange
     * gange over start man bevæger sig, og udløser metoden {@link #rykSpillerTilFelt}.
     *
     * @param spiller       Spilleren der skal rykkes
     * @param felterAtRykke Hvor mange felter fremad spilleren rykker
     */
    public void rykSpillerAntalFelter( SpillerCO spiller, int felterAtRykke ) {

        FeltDTO[] braet = getBretGeneretForSpil().getBretArray();

        FeltDTO endeligtFelt = BevaegelsesLogik.beregnEndeligtFelt( braet, braet[spiller.getSpillerPosition()], felterAtRykke  );

        int gangeOverStart  = BevaegelsesLogik.antalGangeOverStart(spiller.getSpillerPosition(), felterAtRykke, braet.length);

        rykSpillerTilFelt( spiller, endeligtFelt, gangeOverStart);
    }


    /**
     * @author Malte
     * Rykker spilleren til et specifikt felt på brættet, og udløser aktioner
     * ift. feltet, samt UI-metoder ifm. at flytte felt.
     * Beregner ikke selv, hvor mange gange spilleren bevæger sig over start,
     * men den udløser metoden passererStart() i SpillerCO med udgangspunkt i
     * 'gangeOverStart'
     *
     * @param spiller Spiller der skal rykkes
     * @param felt Feltet spilleren skal rykke til
     * @param gangeOverStart Hvor mange gange over start spilleren kommer. Hvis =0 sker der ikke noget.
     */
    public void rykSpillerTilFelt( SpillerCO spiller, FeltDTO felt, int gangeOverStart){

        if( gangeOverStart > 0 ){
            spiller.passeringAfStart(gangeOverStart, getUserInterfaceKontrakt());}

        spiller.setSpillerPosition(felt.getPlacering());

        getUserInterfaceKontrakt().duErLandetPå(felt, spiller);

        felt.aktionPaaFelt(this, getUserInterfaceKontrakt());
    }


    //_____________________________________
    //Tjekkere:

    /**
     * Indsæt beskrivelse her
     */
    public void tjekForFeangselsStraf() {
        if (getSpillerMedTur().isFaengselsStraf()) {
            if (!getSpillerMedTur().isHarAnketDomDenneRunde()) {
                getUserInterfaceKontrakt().terminalLinje();
                getUserInterfaceKontrakt().anketStraffeDom(getSpillerTur());
                anketDomsigelse();
                getSpillerMedTur().setHarAnketDomDenneRunde(true);
            }

        }
    }

    /**
     * Indsæt beskrivelse her
     */
    public void tjekOmGivetOp() {
        if (getSpillerMedTur().isHarGivetOp()) {
            if (getSpillerTur() == getAntalSpillere()) {
                setSpillerTur(1);
            } else {
                setSpillerTur(getSpillerTur() + 1);
            }
        }
    }

    /**
     * Indsæt beskrivelse her
     */
    public void tjekForVinder() {
        if (getAntalSpillere() - tjekAntalSpillereISpil() == 1) {
            getUserInterfaceKontrakt().terminalLinje();
            //SpillerCO spillerMedTur = spillerObjekter.get(spillerTur - 1);
            if (!getSpillerMedTur().isHarGivetOp()) {
                //Der ligger en til for at da det er den spiller i rækken, der ligger forud for vinderen, der giver op.
                setVinder(getSpillerMedTur().getId() + 1);
                getUserInterfaceKontrakt().vinder(getVinder());
                setVinderFindes(true);
                setKør(false);
            }

        }
    }

    /**
     * Indsæt beskrivelse her
     * @return
     */
    public int tjekAntalSpillereISpil() {
        int UdgaetSpillere = 0;
        for (int i = 0; i < getSpillerObjekter().size(); i++) {
            if (getSpillerObjekter().get(i).isHarGivetOp()) {
                UdgaetSpillere++;
            }
        }

        return UdgaetSpillere;
    }

    /**
     * Indsæt beskrivelse her
     * @param terningKrus
     */
    public void tjekForPasseringAfStartOgRykSpiller(RafleBaeger terningKrus) {
        /*int rykVeardi = terningKrus.getTotalVaerdi();
        int nuvaerendeposition = getSpillerMedTur().getSpillerPosition();
        if (nuvaerendeposition + rykVeardi > getAntalFelter() - 1) {
            getSpillerMedTur().passeringAfStart(terningKrus.getTotalVaerdi(), this, getUserInterfaceKontrakt());
        } else {
            getSpillerMedTur().setSpillerPosition(getSpillerMedTur().getSpillerPosition() + rykVeardi);
        }
        getUserInterfaceKontrakt().spillerPosition(getSpillerMedTur().getSpillerPosition());*/
    }


    //_____________________________________
    //Menuer:

    /**
     * Indsæt beskrivelse her
     */
    public void startMenu() {
        RandomSingleton rand = RandomSingleton.getInstance();
        int menuOpt = getUserInterfaceKontrakt().velkomstMenu(1,4);


        if (menuOpt == 2) {
            startInstillingsMenu();
        } else if (menuOpt == 3) {
            getUserInterfaceKontrakt().startSpilGrundFejl();
        } else if (menuOpt == 1) {
            int starter = rand.nextInt(getAntalSpillere()) + 1;
            setSpillerTur(starter);
        }
        getUserInterfaceKontrakt().opretteInstillinger(getAntalSpillere(), getAntalFelter(), getAntalTerninger(), getSpillerTur(), getBankeraadGraense());


    }

    /**
     * Indsæt beskrivelse her
     */
    public void startInstillingsMenu() {
        //Todo: make it possible to choose a default option here:
        Random rand = new Random();

        int felter = getUserInterfaceKontrakt().instilingsSporgsmaal0(9, 21);
        setAntalFelter(felter);


        int spillerMeangde = getUserInterfaceKontrakt().instilingsSporgsmaall(2,11);
        setAntalSpillere(spillerMeangde);

        int starter = rand.nextInt(getAntalSpillere()) + 1;
        setSpillerTur(starter);

        int terninger = getUserInterfaceKontrakt().instilingsSporgsmaal2(1,5);
        setAntalTerninger(terninger);

        int driftsomkostninger = getUserInterfaceKontrakt().instilingsSporgsmaal3(0, 99999);
        setBankeraadGraense(driftsomkostninger);


    }

    /**
     * @author Filip
     * Gør det muligt for spillerne at vælge de forskellige funktioner i turmenuen og
     * sørger for at tilhørende metoder udføres
     * @param spilleBret BraetCO objekt, hvor nogle af metoderne benyttes af turmenu
     * @param terningsKrus RafleBaeger objekt, som benyttes til at kaste terninger
     */
    public void turMenu(BraetCO spilleBret, RafleBaeger terningsKrus) {

        int input = getUserInterfaceKontrakt().TurMenu(getSpillerTur(), 1, 10);

        switch (input) {
            case 1:

                if (!getSpillerMedTur().isFaengselsStraf()) {
                    kastTerninger(terningsKrus);
                    //Denne funktion  kan kalder:
                    //tjekForPasseringAfStartOgRykSpiller(Raflebaeger terningKrus)
                    //og aktionPåFelt.
                }
                else if (getSpillerMedTur().isFaengselsStraf()){
                    getUserInterfaceKontrakt().kanIkkeSlaaFaengsel();
                }
                break;
            case 2:
                slutSpillerTur();
                break;
            case 3:
                getSpillerMedTur().chanceKortMuligheder(this,getUserInterfaceKontrakt());
                break;
            case 4:
                getSpillerMedTur().visEjendeFelter(getUserInterfaceKontrakt());
                break;
            case 5:
                spilleBret.printBret(getUserInterfaceKontrakt());
                break;
            case 6:
                printSpilleresInfo();
                break;
            case 7:
                getSpillerMedTur().givOp(this, getUserInterfaceKontrakt());
                break;
            case 8:
                getSpillerMedTur().koebHusPaaEjendom(getUserInterfaceKontrakt());
                break;
            case 9:
                getSpillerMedTur().handelMedEjendomme();
                break;
            case 99:
                setKør(false);
                break;
            default:
                getUserInterfaceKontrakt().ikkeMuligt();
        }

    }


}
