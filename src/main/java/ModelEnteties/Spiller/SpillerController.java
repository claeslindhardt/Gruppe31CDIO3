package ModelEnteties.Spiller;

import Controller.UserInterfaceKontrakt;
import Controller.SpilController;
import ModelEnteties.braet.controllerKlasser.Ejendom;
import ModelEnteties.braet.controllerKlasser.EjendomsGruppe;
import ModelEnteties.braet.controllerKlasser.Jernbane;

import java.util.ArrayList;
import java.util.Scanner;

public class SpillerController extends SpillerData {
    //|----------- Metoder:------------------
    //_____________________________________
    //Diverse:
    public void givOp(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int svar;
        userInterfaceKontrakt.vilDuGiveOp();
        svar = getScanner().nextInt();
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
    public int passeringAfStart (int terningvalg, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt) {
        int gangeOverStart = (getSpillerPosition()+terningvalg)/spil.getAntalFelter();
        setSpillerPosition((getSpillerPosition()+ terningvalg)% spil.getAntalFelter());


        penge += 200*gangeOverStart;
        userInterfaceKontrakt.passeringAfStart(gangeOverStart);
        return gangeOverStart;
    }
    public void chanceKortMuligheder(UserInterfaceKontrakt userInterfaceKontrakt){
        /*
        Her skal spilleren kunne:
            Se sine ChanceFelt
            aktivere et udvalgt ChanceFelt
         */
        if(spillerAktionsKort.size()>0){
            //Her printes de forskellige muligher:
            userInterfaceKontrakt.chanceKortHar();
            for(int i = 0; i<getSpillerAktionsKort().size();i++){
                userInterfaceKontrakt.chanceKortNr(i,this);
            }

            //Her er controlleren der lader en reagere på mulighederne
            userInterfaceKontrakt.chanceKortsVejledning();
            int valg = getScanner().nextInt();
            if(valg == -1){ }
            else if(valg != -1){
                getSpillerAktionsKort().get(valg).BetingetAktion();
                getSpillerAktionsKort().remove(valg);
            }
        }else{
            userInterfaceKontrakt.ingenChanceKort();
        }
    }
    public void tagTaxi(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int destination;
        //Spiller relavantSpiller = SpilData.getSpillerMedTur();

        this.setHarSlaaetForTuren(true);
        userInterfaceKontrakt.hvorHen(this.getSpillerPosition());
        destination = getScanner().nextInt();
        if(destination>spil.getAntalFelter() || destination< 1 ){
            userInterfaceKontrakt.holdDigPaaBrettet();
        }else{
            this.setSpillerPosition(destination);
            userInterfaceKontrakt.overStart(this.getSpillerPosition());
            this.addPenge(200);
            //kalder en aktion på det felt man tager til med taxien
            spil.getBretGeneretForSpil().getBret().get(spil.getSpillerMedTur().getSpillerPosition()).aktionPaaFelt(spil, userInterfaceKontrakt);
        }
    }
    //_____________________________________
    //Vis og print funktinoer:
    public void printSpillerStats(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.spillerStat(this);
    }

    public void visEjendeFelter(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.spillerEjendele(this);
    }

    //_____________________________________
    //Koebe og salg funktioner:

    public void koebEjendom(Ejendom ønsketEjendom, UserInterfaceKontrakt userInterfaceKontrakt) {
        //Sikkerheds Foranstaltning: Vi tjekker mod dobbeltkøb
        if (ønsketEjendom.getEjer() == this) {
            userInterfaceKontrakt.tetPaaMonopol();
        }
        else if (this.penge > ønsketEjendom.getPris()) {
            userInterfaceKontrakt.gennemfortKoeb();
            //Todo: fix enkapsulering her
            this.penge -= ønsketEjendom.getPris();

            //skifte ejerskab
            ønsketEjendom.setEjer(this);
            this.spillerEjendomme.add(ønsketEjendom);
        } else {
            userInterfaceKontrakt.monetosMangel();
        }
    }

    public void koebJernbane(Jernbane relevantJernbane, UserInterfaceKontrakt userInterfaceKontrakt, SpilController spil){
        //Sikkerhedsforanstaltning. Vi tjekker mod dobbeltkøb
        if (relevantJernbane.getEjer() == this) {
            userInterfaceKontrakt.alleredeEjer();
        } else if (getPenge() > relevantJernbane.getPris()) {
            setPenge(getPenge()-relevantJernbane.getPris());
            userInterfaceKontrakt.dinJernbane();
            //Todo: fix enkapsulering herunder:
            //skifte ejerskab
            relevantJernbane.setEjer(this);;
            spillerJernbaner.add(relevantJernbane);
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
    boolean ejerEjendom(Ejendom ejendom){
        return ejendom.getEjer() == this;
    }

    /**
     * @author Malte
     * Undersoeger om spilleren ejer alle ejendomme i en specifik
     * ejendomsgruppe.
     * @param ejendomsGruppe Hvilken ejendomsgruppe man vil undersoege.
     * @return true: spilleren ejer alle i gruppen, false: spillere ejer ikke alle i gruppen
     */
    boolean ejerEjendomsGruppe(EjendomsGruppe ejendomsGruppe){
        for( Ejendom ejendom : ejendomsGruppe.getEjendomme()){
            if( ejendom.getEjer() != this){
                return false;
            }
        }
        return true; }

    /**
     * @author Malte
     * Undersøger, om man kan koebe et hus paa en vilkårlig ejendom.
     * Her tjekkes for at
     *  1) man ejer ejendommen
     *  2) man ejer alle ejendomme i gruppen
     *  3) der er ligelig fordeling af huse paa ejendommene i gruppen (ikke implementeret)
     *  4) antallet af huse er under 4
     *  5) spilleren har nok penge til at koebe ejendommen.
     * @param ejendom: ejendommen man oensker at koebe et hus paa.
     * @return true: man kan koebe et hus paa ejendommen, false: man kan ikke koebe et hus paa ejendommen.
     */
    boolean kanKoebeHus(Ejendom ejendom){
        EjendomsGruppe ejendomsGruppe = ejendom.getGruppe();
        return( ejerEjendom(ejendom)
                && ejerEjendomsGruppe(ejendomsGruppe)
                && ejendomsGruppe.huseErLigeligtFordelt()
                && ejendom.getAntalHuse() < 4
                && getPenge()>ejendom.getHusPris() ); }

    /**
     * @author Malte
     * Metode der koeber et hus på en ejendom for spilleren.
     * Dette inkluderer at bygge huset paa ejendom (ejendom.bygHuse),
     * og trække penge fra spilleren.
     * @param ejendom: hvilken ejendom man vil bygge et hus paa.
     */
    void koebHus(Ejendom ejendom){
        if( kanKoebeHus(ejendom) ){
            ejendom.bygHuse(1);
            addPenge(-ejendom.getHusPris());
        } }

    /**
     * @author Malte
     * FORLØBET i at købe et hus på en ejendom. Dvs. den der sørger beder UI
     * om at vise ting og tage i mod inputs.
     * @param ui: hvilket UserInterface der skal bruges.
     */
    public void koebHusPaaEjendom(UserInterface ui){
        Ejendom[] ejendomme = getEjendomme();

        if( ejendomme.length > 0 ){
            ArrayList<Ejendom> bebyggeligeEjendomme = new ArrayList<Ejendom>();

            /* Finder bebyggelige ejendomme og flytter dem over i en seperat liste.
               Se kanKoebeHus() for at se, hvordan det vurderes om spilleren kan
               bygge et hus paa en ejendom.
             */
            for(int i = 0; i < ejendomme.length; i++){
                if(kanKoebeHus(ejendomme[i])){
                    bebyggeligeEjendomme.add(ejendomme[i]);
                }
            }

            if(bebyggeligeEjendomme.size() > 0){

                int ejendomsIndex = ui.input_EjendomAtByggePaa(bebyggeligeEjendomme);
                koebHus(bebyggeligeEjendomme.get(ejendomsIndex));
                ui.byggetHus(bebyggeligeEjendomme.get(ejendomsIndex));

            }else {
                ui.ejerIngenBebyggeligeEjendomme(); }

        }else{
            ui.ejerIngenEjendomme();
        }
    }

    public SpillerController(String NAVN, int ID,int position){
        setSpillerPosition(position);
        setId(ID);
        setNavn(NAVN);
    }


}
