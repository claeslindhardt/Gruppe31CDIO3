package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.dataKlasser.EjeligtFelt;

import java.util.ArrayList;

public class Jernbane extends EjeligtFelt {

    //|----------- Metoder:------------------
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.jernBaneInfo(this);
    }
    public void tagTog(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        ArrayList<Jernbane> jernbaner = spil.getBretGeneretForSpil().getJernbaner();
        ArrayList<Jernbane> muligeRejser = new ArrayList<Jernbane>();

        for (int i=0; i<jernbaner.size();i++){
            if(spillerMedTur== jernbaner.get(i).getEjer()){
                muligeRejser.add(jernbaner.get(i));
            }
        }
        if(muligeRejser.size()>1){
            userInterfaceKontrakt.muligeDestinationer();
            for(int i = 0;i<muligeRejser.size();i++){
                System.out.print(i+1+": ");
                muligeRejser.get(i).printInfo(userInterfaceKontrakt);
            }

            int destination = userInterfaceKontrakt.stationsMuligheder();
            if(destination==0){
                userInterfaceKontrakt.turEfterJernbane();
            } else if(destination >= 0) {
                int rykSpillerTil = muligeRejser.get(destination - 1).getPlacering();
                spillerMedTur.setSpillerPosition(rykSpillerTil);
            }
        }else{
            userInterfaceKontrakt.manglerJernbaner();
        }
    }

    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerController spillerMedTur = spil.getSpillerMedTur();

        if(this.getEjer()==null) {

            int kobsBeslutning = userInterfaceKontrakt.jernBaneTilbud();
            switch (kobsBeslutning) {
                case 1:
                    spillerMedTur.koebJernbane(this, userInterfaceKontrakt,spil);
                    break;
                case 2:
                    userInterfaceKontrakt.forsetTur();
                    break;
                default:
                    userInterfaceKontrakt.ikkeMuligt();
            }

        }else if(this.getEjer() != spillerMedTur ){
            userInterfaceKontrakt.ejetAfEnAnden();
        }else{
            userInterfaceKontrakt.tetPaaMonopol();
            this.tagTog(spil, userInterfaceKontrakt);

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
