package Controller;

import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.SpilleBraetController;
import ModelEnteties.braet.dataKlasser.FeltDTO;
import ModelEnteties.singletoner.RandomSingleton;
import ModelEnteties.singletoner.ScannerSingleton;

import java.util.Random;
import java.util.Scanner;

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
        SpilleBraetController spilleBret = new SpilleBraetController(getAntalFelter(), getUserInterfaceKontrakt());
        RafleBaeger terningsKrus = new RafleBaeger(getAntalTerninger());
        setTerningeKrus(terningsKrus);
        setBretGeneretForSpil(spilleBret);
        gui.generGUIBret(getAntalFelter(), spilleBret, getSpillerObjekter());
    }

    public SpilController(int antalSpillere, int antalFelter, int antalTerninger, int bankeRaadtGrense, UserInterfaceKontrakt gui) {
        this.setAntalSpillere(antalSpillere);
        this.setAntalFelter(antalFelter);
        this.setAntalTerninger(antalTerninger);
        this.setBankeraadGraense(bankeRaadtGrense);
        this.setUserInterfaceKontrakt(gui);
        genererSpillere(getAntalSpillere());
        SpilleBraetController spilleBret = new SpilleBraetController(getAntalFelter(), getUserInterfaceKontrakt());
        RafleBaeger terningsKrus = new RafleBaeger(getAntalTerninger());
        setTerningeKrus(terningsKrus);
        setBretGeneretForSpil(spilleBret);
        gui.generGUIBret(getAntalFelter(), spilleBret, getSpillerObjekter());
    }
    //_____________________________________
    // Diverse:

    public void genererSpillere(int antalSpillere) {
        for (int i = 0; i < antalSpillere; i++) {
            SpillerController deltager = new SpillerController(getUserInterfaceKontrakt().spillerNavne(), i, 0);
            getSpillerObjekter().add(deltager);
        }
    }

    public void printSpilleresInfo() {
        for (int i = 0; i < getSpillerObjekter().size(); i++) {
            getSpillerObjekter().get(i).printSpillerStats(getUserInterfaceKontrakt());
        }
    }

    public void anketDomsigelse() {
        Random ra = new Random();
        int domsAfsigelseDel1 = ra.nextInt(5) + 1;
        int domsAfsigelseDel2 = ra.nextInt(5) + 1;
        getUserInterfaceKontrakt().retsTerninger(domsAfsigelseDel1, domsAfsigelseDel2);
        if (domsAfsigelseDel1 == domsAfsigelseDel2) {
            getUserInterfaceKontrakt().heldIRetten();
            getSpillerMedTur().setFaengselsStraf(false);
            getSpillerMedTur().setSpillerPosition(domsAfsigelseDel1 + domsAfsigelseDel2);
        } else if (domsAfsigelseDel1 != domsAfsigelseDel2) {
            getSpillerMedTur().setFaengselsStraf(true);
            getUserInterfaceKontrakt().ingenHeldIRetten();
        }

    }

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

    public void tjekForBankeRaadt() {

        if (getSpillerMedTur().getPenge() < getBankeraadGraense()) {
            getUserInterfaceKontrakt().bankeRaadtGrundetLiquditet(getBankeraadGraense());
            getSpillerMedTur().setHarGivetOp(true);
            getSpillerMedTur().getSpillerEjendomme().clear();
            int udgaaetSpiller = getSpillerMedTur().getId() + 1;
            getUserInterfaceKontrakt().spillerUdgår(udgaaetSpiller);
        }

    }

    public void kastTerninger(RafleBaeger terningsKrus, SpilleBraetController spilleBret) {
        if (!getSpillerMedTur().isHarSlaaetForTuren()) {
            terningsKrus.slaa();
            getUserInterfaceKontrakt().spillerRykkerGrundetTerningslag(terningsKrus, getSpillerTur());
            if (terningsKrus.erEns()) {
                getUserInterfaceKontrakt().ensTerninger();
                getSpillerMedTur().setHarSlaaetForTuren(false);
            } else {
                getSpillerMedTur().setHarSlaaetForTuren(true);
            }
            tjekForPasseringAfStartOgRykSpiller(terningsKrus);
            getUserInterfaceKontrakt().midtTerminalLinje();

            FeltDTO felt = spilleBret.getBret().get(getSpillerMedTur().getSpillerPosition());

            getUserInterfaceKontrakt().duErLandetPå(felt, getSpillerMedTur());

            felt.aktionPaaFelt(this, getUserInterfaceKontrakt());


        } else {
            getUserInterfaceKontrakt().harSlaaetMedTerningfor();
        }
    }

    //_____________________________________
    //Tjekkere:
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

    public void tjekOmGivetOp() {
        if (getSpillerMedTur().isHarGivetOp()) {
            if (getSpillerTur() == getAntalSpillere()) {
                setSpillerTur(1);
            } else {
                setSpillerTur(getSpillerTur() + 1);
            }
        }
    }

    public void tjekForVinder() {
        if (getAntalSpillere() - tjekAntalSpillereISpil() == 1) {
            getUserInterfaceKontrakt().terminalLinje();
            //SpillerController spillerMedTur = spillerObjekter.get(spillerTur - 1);
            if (!getSpillerMedTur().isHarGivetOp()) {
                //Der ligger en til for at da det er den spiller i rækken, der ligger forud for vinderen, der giver op.
                setVinder(getSpillerMedTur().getId() + 1);
                getUserInterfaceKontrakt().vinder(getVinder());
                setVinderFindes(true);
                setKør(false);
            }

        }
    }

    public int tjekAntalSpillereISpil() {
        int UdgaetSpillere = 0;
        for (int i = 0; i < getSpillerObjekter().size(); i++) {
            if (getSpillerObjekter().get(i).isHarGivetOp()) {
                UdgaetSpillere++;
            }
        }

        return UdgaetSpillere;
    }

    public void tjekForPasseringAfStartOgRykSpiller(RafleBaeger terningKrus) {
        int rykVeardi = terningKrus.getTotalVaerdi();
        int nuvaerendeposition = getSpillerMedTur().getSpillerPosition();
        if (nuvaerendeposition + rykVeardi > getAntalFelter() - 1) {
            getSpillerMedTur().passeringAfStart(terningKrus.getTotalVaerdi(), this, getUserInterfaceKontrakt());
        } else {
            getSpillerMedTur().setSpillerPosition(getSpillerMedTur().getSpillerPosition() + rykVeardi);
        }
        getUserInterfaceKontrakt().spillerPosition(getSpillerMedTur().getSpillerPosition());
    }


    //_____________________________________
    //Menuer:
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

    public void turMenu(SpilleBraetController spilleBret, RafleBaeger terningsKrus) {

        int input = getUserInterfaceKontrakt().TurMenu(getSpillerTur(), 1, 10);

        switch (input) {
            case 1:
                kastTerninger(terningsKrus, spilleBret);
                //Denne funktion  kan kalder:
                //tjekForPasseringAfStartOgRykSpiller(Raflebaeger terningKrus)
                //og aktionPåFelt.
                break;
            case 2:
                slutSpillerTur();
                break;
            case 3:
                getSpillerMedTur().chanceKortMuligheder(getUserInterfaceKontrakt());
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
