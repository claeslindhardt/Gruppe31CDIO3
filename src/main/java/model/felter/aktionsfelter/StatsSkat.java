package model.felter.aktionsfelter;
//Ekstra Commit

import model.felter.Felt;

public class StatsSkat extends Felt {

    public double getSkat() {
        return skat;
    }

    private double skat;

    public StatsSkat(int placering, double skat) {
        super( "Betal statsskat", placering );
        this.skat = skat;

        setFeltType("StatsSkat");
    }
}
