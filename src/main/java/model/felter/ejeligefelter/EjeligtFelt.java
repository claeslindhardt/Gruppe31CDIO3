package model.felter.ejeligefelter;

import model.Spiller;
import model.felter.Felt;

/**
 *  Denne klasse er forældre klassen til alle felter, der kan ejes:
 *      -  Ejendom
 *      -  Rederi
 *      -  Bryggeri
 */
public abstract class EjeligtFelt extends Felt {

    private Spiller ejer;
    private int pris;


    public EjeligtFelt(String navn, int pris, int placering ){
        super(navn, placering);
        this.pris = pris;
    }


    public Spiller getEjer() {
        return ejer;
    }

    public void setEjer(Spiller ejer) {
        this.ejer = ejer;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

}
