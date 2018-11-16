package ModelEnteties.Spiller;

import Controller.SpilData;
import Controller.UserInterface;
import Controller.SpilController;
import ModelEnteties.braet.controllerKlasser.Ejendom;
import ModelEnteties.braet.controllerKlasser.Jernbane;

public class SpillerController extends SpillerData {
    //|----------- Metoder:------------------
    //_____________________________________
    //Diverse:
    public void givOp(SpilController spil, UserInterface userInterface){
        int svar;
        userInterface.vilDuGiveOp();
        svar = getScanner().nextInt();
        if(svar==1) {
            setHarGivetOp(true);
            getSpillerEjendomme().clear();
            userInterface.takForSpillet();
            spil.slutSpillerTur();
        }
        else {
            userInterface.duGavIkkeOp();
        }

    }
    public int passeringAfStart (int terningvalg, SpilController spil, UserInterface userInterface) {

        setSpillerPosition((getSpillerPosition()+ terningvalg)% spil.getAntalFelter());
        int gangeOverStart = (getSpillerPosition()+terningvalg)/spil.getAntalFelter();

        penge += 200*gangeOverStart;
        userInterface.passeringAfStart(gangeOverStart);
        return gangeOverStart;
    }
    public void chanceKortMuligheder(UserInterface userInterface){
        /*
        Her skal spilleren kunne:
            Se sine ChanceFelt
            aktivere et udvalgt ChanceFelt
         */
        if(spillerAktionsKort.size()>0){
            //Her printes de forskellige muligher:
            userInterface.chanceKortHar();
            for(int i = 0; i<getSpillerAktionsKort().size();i++){
                userInterface.chanceKortNr(i,this);
            }

            //Her er controlleren der lader en reagere på mulighederne
            userInterface.chanceKortsVejledning();
            int valg = getScanner().nextInt();
            if(valg == -1){ }
            else if(valg != -1){
                getSpillerAktionsKort().get(valg).BetingetAktion();
                getSpillerAktionsKort().remove(valg);
            }
        }else{
            userInterface.ingenChanceKort();
        }
    }
    public void tagTaxi(SpilController spil,UserInterface userInterface){
        int destination;
        //Spiller relavantSpiller = SpilData.getSpillerMedTur();

        this.setHarSlaaetForTuren(true);
        userInterface.hvorHen(this.getSpillerPosition());
        destination = getScanner().nextInt();
        if(destination>spil.getAntalFelter() || destination< 1 ){
            userInterface.holdDigPaaBrettet();
        }else{
            this.setSpillerPosition(destination);
            userInterface.overStart(this.getSpillerPosition());
            this.addPenge(200);
            //kalder en aktion på det felt man tager til med taxien
            spil.getBretGeneretForSpil().getBret().get(spil.getSpillerMedTur().getSpillerPosition()).aktionPaaFelt();
        }
    }
    //_____________________________________
    //Vis og print funktinoer:
    public void printSpillerStats(UserInterface userInterface){
        userInterface.spillerStat(this);
    }
    /*
    public void visEjendeFelter(UserInterface userInterface){
        System.out.println("Ejendeomme: ");
        for(int i = 0; i<spillerEjendomme.size();i++){
            spillerEjendomme.get(i).printInfo();
        }
        System.out.println("Jernbaner: ");
        for(int i = 0; i<spillerJernbaner.size();i++){
            spillerJernbaner.get(i).printInfo();

        }
    }
    /*
    public void bygPaaEjendom(){

    }*/
    //_____________________________________
    //Koebe og salg funktioner:

    public void koebEjendom(Ejendom ønsketEjendom, UserInterface userInterface) {
        //Sikkerheds Foranstaltning: Vi tjekker mod dobbeltkøb
        if (ønsketEjendom.getEjer() == this) {
            userInterface.tetPaaMonopol();
        }
        else if (this.penge > ønsketEjendom.getPris()) {
            userInterface.gennemfortKoeb();
            //Todo: fix enkapsulering her
            this.penge -= ønsketEjendom.getPris();

            //skifte ejerskab
            ønsketEjendom.setEjer(this);
            this.spillerEjendomme.add(ønsketEjendom);
        } else {
            userInterface.monetosMangel();
        }
    }
    /*
    public void koebJernbane(Jernbane relevantJernbane, UserInterface userInterface,SpilController spil){
        //Sikkerhedsforanstaltning. Vi tjekker mod dobbeltkøb
        if (relevantJernbane.getEjer() == this) {
            userInterface.alleredeEjer();
        } else if (getPenge() > relevantJernbane.getPris()) {
            setPenge(getPenge()-relevantJernbane.getPris());
            userInterface.dinJernbane();
            //Todo: fix enkapsulering herunder:
            //skifte ejerskab
            relevantJernbane.setEjer(this);;
            spillerJernbaner.add(relevantJernbane);
            relevantJernbane.tagTog(spil);
        } else {
            userInterface.monetosMangel();
        }
    }
    /*
    public void handelMedEjendomme(){
        /*
                her skal man kunne:
                    Pansætte
                    byde på andre ejendomme
                    sætte sine egne på auktion
                 */
    //}


    public SpillerController(String NAVN, int ID,int position){
        setSpillerPosition(position);
        setId(ID);
        setNavn(NAVN);
    }
}
