package ModelEnteties.braet.dataKlasser;

import ModelEnteties.Spiller.SpillerController;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION
 *  @author Malte
 *  Forælder-klassen til alle felt-klasser der er ejelige. Herunder:
 *      - Ejendom
 *      - Jernbane
 */
public abstract class EjeligtFeltDTO extends FeltDTO {

    //|--------- Variabler:-----------------

    private SpillerController ejer;
    private int pris = 200;
    private boolean pantsat = false;

    //|--------- Getters og Setters:-----------------

    public SpillerController getEjer() {
        return ejer;
    }

    public void setEjer(SpillerController ejer) {
        this.ejer = ejer;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public boolean isPantsat() {
        return pantsat;
    }

    public void setPantsat(boolean pantsat) {
        this.pantsat = pantsat;
    }

}
