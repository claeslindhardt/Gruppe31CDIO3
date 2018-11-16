package ModelEnteties.Spiller;

import BaundaryView.TUI.TUI;
import Controller.SpilController;

public class SpillerController extends SpillerData {
    //|----------- Metoder:------------------
    //_____________________________________
    //Diverse:
    public void givOp(SpilController spil, TUI UserInterface){
        int svar;
        UserInterface.vilDuGiveOp();
        svar = sc.nextInt();
        if(svar==1) {
            setHarGivetOp(true);
            getSpillerEjendomme().clear();
            UserInterface.takForSpillet();
            spil.slutSpillerTur();
        }
        else {
            UserInterface.duGavIkkeOp();
        }

    }/*
    public void passeringAfStart (int terningvalg) {

        getSpillerPosition() = (getSpillerPosition()+ terningvalg)%SpilData.getAntalFelter();
        int gangeOverStart = (spillerPosition+terningvalg)/SpilData.getAntalFelter();

        penge += 200*gangeOverStart;
        System.out.println("Tillykke du har passeret Start "+gangeOverStart+" gange og modtager "+200*gangeOverStart);

    }/*
    public void chanceKortMuligheder(){
        /*
        Her skal spilleren kunne:
            Se sine ChanceFelt
            aktivere et udvalgt ChanceFelt
         *//*
        if(spillerAktionsKort.size()>0){
            //Her printes de forskellige muligher:
            System.out.println("Du har følgende Chance Kort:");
            for(int i = 0; i<getSpillerAktionsKort().size();i++){
                System.out.println("Chance kort nr. "+i+": ");
                System.out.println(getSpillerAktionsKort().get(i).getBeskrivelse());
            }

            //Her er controlleren der lader en reagere på mulighederne
            System.out.println("-------------------------------");
            System.out.println("Intast nummeret på det chance kort du gerne vil aktiverer:" +
                    "\nEller hvis du vil tilbage til tur menuen (-1):"
            );
            int valg = sc.nextInt();
            if(valg == -1){ }
            else if(valg != -1){
                getSpillerAktionsKort().get(valg).BetingetAktion();
                getSpillerAktionsKort().remove(valg);
            }
        }else{
            System.out.println("Du har ikke nogen Chance Kort lige nu.");
        }
    }
    public void tagTaxi(){
        //Todo omskriv denne funktion så den gør brug af den ligger i spiller og ikke behøver hente SpillerMedTur
        int destination;
        Spiller relavantSpiller = SpilData.getSpillerMedTur();

        relavantSpiller.setHarSlaaetForTuren(true);
        System.out.println("Din nuværende position er: "+ relavantSpiller.getSpillerPosition());
        System.out.println("Hvor vil de hen?: ");
        destination = sc.nextInt();
        if(destination>SpilData.getAntalFelter() || destination< 1 ){
            System.out.println("Den går ikke, du skulle have valgt noget der ligger inden for brettets antal braet");
        }else{
            relavantSpiller.setSpillerPosition(destination);
            System.out.println("Din position er: "+ relavantSpiller.getSpillerPosition());
            System.out.println("Du har i den rundt fart med taxien kommet til at passere Start, modtag 200");
            relavantSpiller.addPenge(200);
            //kalder en aktion på det felt man tager til med taxien
            SpilData.getBretGeneretForSpil().getBret().get(SpilData.getSpillerMedTur().getSpillerPosition()).aktionPaaFelt();
        }
    }
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
