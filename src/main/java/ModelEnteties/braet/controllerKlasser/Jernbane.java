package ModelEnteties.braet.controllerKlasser;

import BaundaryView.TUI.TUI;
import Controller.SpilController;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.dataKlasser.EjeligtFelt;

import java.util.ArrayList;
import java.util.Scanner;

public class Jernbane extends EjeligtFelt {
    //|-------initiering af objekter: -----------
    Scanner sc = new Scanner(System.in);

    //|----------- Metoder:------------------
    public void printInfo(TUI UserInterface){
        UserInterface.jernBaneInfo(this);
    }
    public void tagTog(SpilController spil){
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        ArrayList<Jernbane> jernbaner = spil.getBretGeneretForSpil().getJernbaner();
        ArrayList<Jernbane> muligeRejser = new ArrayList<Jernbane>();

        for (int i=0; i<jernbaner.size();i++){
            if(spillerMedTur== jernbaner.get(i).getEjer()){
                muligeRejser.add(jernbaner.get(i));
            }
        }
        if(muligeRejser.size()>1){
            System.out.println("Du kan rejse til ");
            for(int i = 0;i<muligeRejser.size();i++){
                System.out.print(i+1+": ");
                muligeRejser.get(i).printInfo();
            }
            System.out.println("hvis du ønkser ikke at rejse tast 0, " +
                    "\nellers intast den destination du ønsker at rejse til:");
            int destination = sc.nextInt();
            if(destination==0){
                System.out.println("Du kan nu forsætte din tur men får ikke muligheden for at tage jernbanen igen i denne tur," +
                        "\n Det tog er kørt!");
            } else if(destination >= 0) {
                int rykSpillerTil = muligeRejser.get(destination - 1).getPlacering();
                spillerMedTur.setSpillerPosition(rykSpillerTil);
            }
        }else{
            System.out.println("Du ejer ikke nok jernabaner til at rejse:");
        }
    }
    /*
    public void aktionPaaFelt(){
        Spiller spillerMedTur = SpilLeder.getSpillerMedTur();

        if(this.getEjer()==null) {
            System.out.println("Du er landet på et jernbanefelt, og ingen ejer det - vil du købe det?" +
                    "\nJa(1), nej(2)");
            int kobsBeslutning = sc.nextInt();
            switch (kobsBeslutning) {
                case 1:
                    spillerMedTur.koebJernbane(this);
                    break;
                case 2:
                    System.out.println("fotrsæt din tur");
                    break;
                default:
                    System.out.println("ikke en mulighed endnu, men skriv gerne til os hvis der er noget du vil have");
            }

        }else if(this.getEjer() != spillerMedTur ){
            System.out.println("en anden Spiller ejer dette felt, Du kan derfor ikke købe det");
        }else{
            System.out.println("du er landet på en jernbane du ejer, nermer du dig et monopoly?");
            this.tagTog();

        }
    }*/

    //|--------- Constructor:-----------------
    public Jernbane(String whatName, int whatPrice,int placering){
        setPlacering(placering);
        setPris(whatPrice);
        setNavn(whatName);
        addAlleJernbaner(this);
        setFeltType("Jernbane");

    }
}
