package model.chancekort;

/**
 * Chancekort der rykket dig et bestemt antal felter
 * frem eller tilbage paa braettet.
 */
public class RykAntalFelter extends Chancekort {

    private int antalFelterAtRykke; // Hvor mange felter man skal rykke (negativ = rykke tilbage)

    public int getAntalFelterAtRykke(){
        return antalFelterAtRykke;
    }

    public RykAntalFelter(int antalFelterAtRykke, String beskrivelse){
        super(beskrivelse, true);
        this.antalFelterAtRykke = antalFelterAtRykke;
    }
}
