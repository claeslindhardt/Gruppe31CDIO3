package ModelEnteties.Spiller;

import Controller.UserInterfaceKontrakt;
import Controller.SpilController;
import ModelEnteties.braet.controllerKlasser.Ejendom;
import ModelEnteties.braet.controllerKlasser.Jernbane;

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
    /*
    public void bygPaaEjendom(){

    }*/
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


    public SpillerController(String NAVN, int ID,int position){
        setSpillerPosition(position);
        setId(ID);
        setNavn(NAVN);
    }


}
