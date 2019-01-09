package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.EjeligtFeltDTO;

import java.util.ArrayList;

public class JernbaneCO extends EjeligtFeltDTO {

    //|----------- Metoder:------------------

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param userInterfaceKontrakt
     */
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.jernBaneInfo(this);
    }

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void tagTog(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO spillerMedTur = spil.getSpillerMedTur();
        ArrayList<JernbaneCO> jernbaner = spil.getBretGeneretForSpil().getJernbaner();
        ArrayList<JernbaneCO> muligeRejser = new ArrayList<JernbaneCO>();

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

            int destination = userInterfaceKontrakt.stationsMuligheder(0,(muligeRejser.size()+1));
            if(destination==0){
                userInterfaceKontrakt.turEfterJernbane();
            } else if(destination >= 0) {
                int rykSpillerTil = muligeRejser.get(destination - 1).getPlacering();
                spillerMedTur.setSpillerPosition(rykSpillerTil);
                userInterfaceKontrakt.rejseBekraeftelse(spil.getBretGeneretForSpil().getBret().get(rykSpillerTil).getNavn());
            }
        }else{
            userInterfaceKontrakt.manglerJernbaner();
        }
    }

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO spillerMedTur = spil.getSpillerMedTur();

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
    public JernbaneCO(String whatName, int whatPrice, int placering){
        setPlacering(placering);
        setPris(whatPrice);
        setNavn(whatName);
        setFeltType("JernbaneCO");

    }
}
