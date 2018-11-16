package ModelEnteties.braet.controllerKlasser;

import BaundaryView.TUI.TUI;
import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.dataKlasser.EjeligtFelt;

import java.util.ArrayList;
import java.util.Scanner;

public class Jernbane extends EjeligtFelt {
    //|-------initiering af objekter: -----------
    Scanner sc = new Scanner(System.in);
    //TODO: fix singleton
    //|----------- Metoder:------------------
    public void printInfo(UserInterface userInterface){
        userInterface.jernBaneInfo(this);
    }
    public void tagTog(SpilController spil,UserInterface userInterface){
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        ArrayList<Jernbane> jernbaner = spil.getBretGeneretForSpil().getJernbaner();
        ArrayList<Jernbane> muligeRejser = new ArrayList<Jernbane>();

        for (int i=0; i<jernbaner.size();i++){
            if(spillerMedTur== jernbaner.get(i).getEjer()){
                muligeRejser.add(jernbaner.get(i));
            }
        }
        if(muligeRejser.size()>1){
            userInterface.muligeDestinationer();
            for(int i = 0;i<muligeRejser.size();i++){
                System.out.print(i+1+": ");
                muligeRejser.get(i).printInfo();
            }
            userInterface.stationsMuligheder();
            int destination = sc.nextInt();
            if(destination==0){
                userInterface.turEfterJernbane();
            } else if(destination >= 0) {
                int rykSpillerTil = muligeRejser.get(destination - 1).getPlacering();
                spillerMedTur.setSpillerPosition(rykSpillerTil);
            }
        }else{
            userInterface.manglerJernbaner();
        }
    }

    public void aktionPaaFelt(SpilController spil, UserInterface userInterface){
        SpillerController spillerMedTur = spil.getSpillerMedTur();

        if(this.getEjer()==null) {
        userInterface.jernBaneTilbud();
            int kobsBeslutning = sc.nextInt();
            switch (kobsBeslutning) {
                case 1:
                    spillerMedTur.koebJernbane(this, userInterface,spil);
                    break;
                case 2:
                    userInterface.forsetTur();
                    break;
                default:
                    userInterface.ikkeMuligt();
            }

        }else if(this.getEjer() != spillerMedTur ){
            userInterface.ejetAfEnAnden();
        }else{
            userInterface.tetPaaMonopol();
            this.tagTog(spil,userInterface);

        }
    }

    //|--------- Constructor:-----------------
    public Jernbane(String whatName, int whatPrice,int placering){
        setPlacering(placering);
        setPris(whatPrice);
        setNavn(whatName);
        addAlleJernbaner(this);
        setFeltType("Jernbane");

    }
}
