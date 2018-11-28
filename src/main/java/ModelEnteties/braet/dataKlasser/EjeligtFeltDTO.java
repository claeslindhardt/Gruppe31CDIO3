package ModelEnteties.braet.dataKlasser;

import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.controllerKlasser.EjendomCO;
import ModelEnteties.braet.controllerKlasser.Jernbane;

import java.util.ArrayList;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: EjeligtFeltDTO
 * @author
 *  Denne klasse er forældre klassen til alle de braet som opfører sig relativt
 *  statisk, samt har den egenskab at de kan ejes af et spiller objekt. Så vidt
 *  har denne klasse børne-klasserne:
 *          1. Ejendomm
 *          2. Jernbane
 */
public abstract class EjeligtFeltDTO extends FeltDTO {

    //|--------- Variabler:-----------------

    private SpillerController ejer;
    private int pris = 200;
    private boolean pantsat = false;

    //|--------- Getters og Setters:-----------------

    public String getEjerNavn(){
        return ejer.getNavn();
    }

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
