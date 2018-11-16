package ModelEnteties.Spiller;

import BaundaryView.TUI.TUI;
import BaundaryView.UserInterface;
import Controller.SpilController;

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
    }/*
    public void tagTaxi(SpilController spil){
        int destination;
        //Spiller relavantSpiller = SpilData.getSpillerMedTur();

        this.setHarSlaaetForTuren(true);
        System.out.println("Din nuværende position er: "+ this.getSpillerPosition());
        System.out.println("Hvor vil de hen?: ");
        destination = getScanner().nextInt();
        if(destination>spil.getAntalFelter() || destination< 1 ){
            System.out.println("Den går ikke, du skulle have valgt noget der ligger inden for brettets antal braet");
        }else{
            this.setSpillerPosition(destination);
            System.out.println("Din position er: "+ this.getSpillerPosition());
            System.out.println("Du har i den rundt fart med taxien kommet til at passere Start, modtag 200");
            this.addPenge(200);
            //kalder en aktion på det felt man tager til med taxien
            spil.getBretGeneretForSpil().getBret().get(spil.getSpillerMedTur().getSpillerPosition()).aktionPaaFelt();
        }
    }*//*
    //_____________________________________
    //Vis og print funktinoer:
    public void printSpillerStats(){
        System.out.println("Navn: "+getNavn()+" ID:"+getId()+" getPlacering(): "+getSpillerPosition()+" Penge: "+getPenge());
    }
    public void visEjendeFelter(){
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

    }
    //_____________________________________
    //Koebe og salg funktioner:

    //Todo: generaliser køb af ejelige braet således at de får den samme funktion og koden bliver lettere skalerbar.
    public void koebEjendom(Ejendom ønsketEjendom) {
        //Sikkerheds Foranstaltning: Vi tjekker mod dobbeltkøb
        Spiller relevantSpiller = SpilData.getSpillerMedTur();
        if (ønsketEjendom.getEjer() == relevantSpiller) {
            System.out.println("Du er allerede ejer");
        }
        else if (relevantSpiller.penge > ønsketEjendom.getPris()) {
            System.out.println("du kan købe grunden hurra!!");
            //Todo: fix enkapsulering her
            relevantSpiller.penge -= ønsketEjendom.getPris();
            System.out.println("Ejendommen er nu din!");

            //skifte ejerskab
            ønsketEjendom.setEjer(relevantSpiller);
            relevantSpiller.spillerEjendomme.add(ønsketEjendom);
        } else {
            System.out.println("Du har ikke råd på nuværende tidspunkt. Vi vil dog stadig gerne bevare dig som kunde");
        }
    }

    public void koebJernbane(Jernbane relevantJernbane){
        //Sikkerhedsforanstaltning. Vi tjekker mod dobbeltkøb
        Spiller spillerMedTur = SpilData.getSpillerMedTur();
        if (relevantJernbane.getEjer() == spillerMedTur) {
            System.out.println("Du er allerede ejer");
        } else if (penge > relevantJernbane.getPris()) {
            //Todo: fix enkapsulering her
            penge -= relevantJernbane.getPris();
            System.out.println("Jernbanen er nu din!");

            //skifte ejerskab
            relevantJernbane.setEjer(spillerMedTur);;
            spillerJernbaner.add(relevantJernbane);
            relevantJernbane.tagTog();
        } else {
            System.out.println("Du har ikke råd på nuværende tidspunkt. Vi vil dog stadig gerne bevare dig som kunde");
        }
    }
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
