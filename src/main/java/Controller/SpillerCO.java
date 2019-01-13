package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.SpillerDTO;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.EjendomsGruppeDTO;
import spillogik.BevaegelsesLogik;
import spillogik.EjendomsLogik;

import java.util.ArrayList;

import static spillogik.EjendomsLogik.kanKoebeHotel;


public class SpillerCO extends SpillerDTO {
    //|----------- Metoder:------------------
    //_____________________________________
    //Diverse:

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void givOp(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int svar;
        svar = userInterfaceKontrakt.vilDuGiveOp();
        if(svar==1) {
            setHarGivetOp(true);
            getSpillerEjendomme().clear();
            userInterfaceKontrakt.takForSpillet();
            spil.slutSpillerTur();
        }
        else {
            userInterfaceKontrakt.duGavIkkeOp();
        }

    }

    /**
     * @author Malte
     * Giver spilleren penge, alt efter hvor mange gange over start spilleren kommer,
     * samt kører den passende UI-kode til passering af start.
     * Den finder IKKE ud af, hvor mange gange man kører over start.
     * @param gangeOverStart Antallet af gange man passerer start
     * @param userInterfaceKontrakt UI'en der skal bruges til at vise det.
     */
    public void passeringAfStart (int gangeOverStart, UserInterfaceKontrakt userInterfaceKontrakt) {
        penge += BevaegelsesLogik.passererStartPenge(gangeOverStart);
        userInterfaceKontrakt.passeringAfStart(gangeOverStart);
        userInterfaceKontrakt.updateSpillere(this);
    }

    /**
     * Indsæt beskrivelse her
     * @param userInterfaceKontrakt
     */
    public void chanceKortMuligheder(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt) {
        /*
        Her skal spilleren kunne:
            Se sine ChanceFeltCO
            aktivere et udvalgt ChanceFeltCO
         */
        if (getSpillerAktionsKort().size() > 0) {
            //Her printes de forskellige muligher:
            userInterfaceKontrakt.chanceKortHar();
            /*for(int i = 0; i<getSpillerAktionsKort().size();i++){
                userInterfaceKontrakt.chanceKortNr(i,this);
            }*/

            //Her er controlleren der lader en reagere på mulighederne


            int valg = userInterfaceKontrakt.chanceKortNr(this);
            //if(valg == -1){ }
            //else if(valg != -1){
            if (valg == getSpillerAktionsKort().size()) {
                //Der lægges en til for at er det stadig er den samme spilleres tur. I TurMenu bliver der nemlig udskrevet spillerens tur.
                spil.turMenu(spil.getBretGeneretForSpil(),spil.getTerningeKrus());
            } else {
                HandelsController handel = new HandelsController();
                getSpillerAktionsKort().get(valg).BetingetAktion(handel, spil, userInterfaceKontrakt);
                getSpillerAktionsKort().remove(valg);
            }
        } else {
            //}else{
            userInterfaceKontrakt.ingenChanceKort();
        }
        //}
    }
    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void tagTaxi(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int destination;
        //Spiller relavantSpiller = SpilData.getSpillerMedTur();

        this.setHarSlaaetForTuren(true);

        destination = userInterfaceKontrakt.hvorHen(this.getSpillerPosition(),1,spil.getAntalFelter());
        if(destination>spil.getAntalFelter() || destination< 1 ){
            userInterfaceKontrakt.holdDigPaaBrettet();
        }else{
            spil.rykSpillerTilFelt(this, spil.getBretGeneretForSpil().getBret().get(destination),1);
        }
    }
    //_____________________________________
    //Vis og print funktinoer:

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param userInterfaceKontrakt
     */
    public void printSpillerStats(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.spillerStat(this);
    }

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param userInterfaceKontrakt
     */
    public void visEjendeFelter(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.spillerEjendele(this);
    }

    //_____________________________________
    //Koebe og salg funktioner:

    /**
     * Indsæt beskrivelse her
     * @param ejendom
     * @param userInterfaceKontrakt
     */
    public void koebEjendom(EjendomCO ejendom, UserInterfaceKontrakt userInterfaceKontrakt) {
        //Sikkerheds Foranstaltning: Vi tjekker mod dobbeltkøb
        if (ejendom.getEjer() == this) {
            userInterfaceKontrakt.tetPaaMonopol();
        }
        else if (this.penge > ejendom.getPris()) {
            userInterfaceKontrakt.gennemfortKoeb(ejendom, this);
            //Todo: fix enkapsulering her
            this.penge -= ejendom.getPris();
            userInterfaceKontrakt.updateSpillere(this);
            //skifte ejerskab
            ejendom.setEjer(this);
            this.getSpillerEjendomme().add(ejendom);
        } else {
            userInterfaceKontrakt.monetosMangel();
        }
    }

    /**
     * Indsæt beskrivelse her
     * @param relevantJernbane
     * @param userInterfaceKontrakt
     * @param spil
     */
    public void koebJernbane(JernbaneCO relevantJernbane, UserInterfaceKontrakt userInterfaceKontrakt, SpilController spil){
        //Sikkerhedsforanstaltning. Vi tjekker mod dobbeltkøb
        if (relevantJernbane.getEjer() == this) {
            userInterfaceKontrakt.alleredeEjer();
        } else if (getPenge() > relevantJernbane.getPris()) {
            setPenge(getPenge()-relevantJernbane.getPris());
            userInterfaceKontrakt.dinJernbane();
            userInterfaceKontrakt.updateSpillere(this);
            //Todo: fix enkapsulering herunder:
            //skifte ejerskab
            relevantJernbane.setEjer(this);
            getSpillerJernbaner().add(relevantJernbane);
            relevantJernbane.tagTog(spil, userInterfaceKontrakt);
        } else {
            userInterfaceKontrakt.monetosMangel();
        }
    }

    public void handelMedEjendomme(){
        /*
                her skal man kunne:
                    Pansætte
                    byde på andre ejendomme
                    sætte sine egne på auktion
                 */

    }


    /**
     * @author Malte
     * Undersoege om man ejer en specifik ejendom, ved at sammenligne
     * ejeren af ejendommen med spilleren.
     * @param ejendom Ejendommen man oensker at undersoege.
     * @return True: spilleren ejer den, False: spilleren ejer den ikke.
     */
    public boolean ejerEjendom(EjendomCO ejendom){
        return ejendom.getEjer() == this;
    }

    /**
     * @author Malte
     * Undersoeger om spilleren ejer alle ejendomme i en specifik
     * ejendomsgruppe.
     * @param ejendomsGruppe Hvilken ejendomsgruppe man vil undersoege.
     * @return true: spilleren ejer alle i gruppen, false: spillere ejer ikke alle i gruppen
     */
    public boolean ejerEjendomsGruppe(EjendomsGruppeDTO ejendomsGruppe){
        for( EjendomCO ejendom : ejendomsGruppe.getEjendomme()){
            if( ejendom.getEjer() != this){
                return false;
            }
        }
        return true; }



    /**
     * @author Malte
     * Metode der koeber et hus på en ejendom for spilleren.
     * Dette inkluderer at bygge huset paa ejendom (ejendom.bygHuse),
     * og trække penge fra spilleren.
     *
     * @param ejendom: hvilken ejendom man vil bygge et hus paa.
     */
    public void koebHus(EjendomCO ejendom, UserInterfaceKontrakt userInterfaceKontrakt){
        if( EjendomsLogik.kanKoebeHus(this, ejendom, ejendom.getGruppe()) ){
            ejendom.bygHuse(1);

            ejendom.setLeje(EjendomsLogik.beregnLejeTotal(ejendom, ejerEjendomsGruppe(ejendom.getGruppe())));
            addPenge(-ejendom.getHusPris());
            userInterfaceKontrakt.updateSpillere(this);

        }
    }

    /**
     * @author Malte
     * FORLØBET i at købe et hus på en ejendom. Dvs. den der sørger beder UI
     * om at vise ting og tage i mod inputs.
     * @param ui: hvilket UserInterface der skal bruges.
     */
    public void koebHusPaaEjendom(UserInterfaceKontrakt ui){
        EjendomCO[] ejendomme = getEjendomme();

        if( ejendomme.length > 0 ){
            ArrayList<EjendomCO> bebyggeligeEjendomme = new ArrayList<EjendomCO>();

            /* Finder bebyggelige ejendomme og flytter dem over i en seperat liste.
               Se kanKoebeHus() for at se, hvordan det vurderes om spilleren kan
               bygge et hus paa en ejendom.
             */
            for(int i = 0; i < ejendomme.length; i++){
                if( EjendomsLogik.kanKoebeHus(this, ejendomme[i], ejendomme[i].getGruppe()) ){
                    bebyggeligeEjendomme.add(ejendomme[i]);
                }
            }

            if(bebyggeligeEjendomme.size() > 0){

                int ejendomsIndex = ui.input_EjendomAtByggePaa(bebyggeligeEjendomme);
                koebHus(bebyggeligeEjendomme.get(ejendomsIndex),ui);
                ui.byggetHus(bebyggeligeEjendomme.get(ejendomsIndex));

            }else {
                ui.ejerIngenBebyggeligeEjendomme(); }

        }else{
            ui.ejerIngenEjendomme();
        }
    }


//----------------------Constructor-----------------------------------------------------------------------------------//
    public SpillerCO(String NAVN, int ID, int position){
        setSpillerPosition(position);
        setId(ID);
        setNavn(NAVN);
    }


}
