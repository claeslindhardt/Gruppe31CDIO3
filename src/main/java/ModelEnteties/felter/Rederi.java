package ModelEnteties.felter;

import BoundaryView.UserInterfaceKontrakt;
import Controller.Handel;
import Controller.SpilController;
import ModelEnteties.Spiller;

/**
 * @author Filip
 *Implementerer rederier i spillet, hvor lejen fordobles for hvert rederi, der ejes af samme spiller.
 */

public class Rederi extends EjeligtFeltDTO {

private int leje = 0;

    //|--------- Getters og Setters:-----------------

    public int getLeje(){ return leje; }

    public void setLeje(int leje){
        this.leje = leje;
    }



    //|----------- Metoder:------------------

    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        Spiller spillerMedTur = spil.getSpil().getSpillerMedTur();

        if(this.getEjer()==null){
            this.printInfo(userInterfaceKontrakt);


            int kobsBeslutning = userInterfaceKontrakt.ejendomsBud();
            switch (kobsBeslutning){
                case 0:
                    spil.getKoebFelt().koebRederi(this, spillerMedTur, userInterfaceKontrakt);
                    break;
                case 1:
                    userInterfaceKontrakt.forsetTur();

                    break;
                default:

            }
        }else if(this.getEjer() != null && this.getEjer() != spillerMedTur){
            userInterfaceKontrakt.betalRente();
            userInterfaceKontrakt.updateSpillere(spillerMedTur);
            handel.indsamleLeje(spil.getSpil(),this, spillerMedTur, userInterfaceKontrakt);
        }else if(this.getEjer() == spillerMedTur){
            userInterfaceKontrakt.tetPaaMonopol();
        }
    }

    //|--------- Constructor:-----------------
    public Rederi(String whatName, int whatPrice, int placering){
        setPlacering(placering);
        setPris(whatPrice);
        setNavn(whatName);
        setFeltType("Rederi");

    }

}
