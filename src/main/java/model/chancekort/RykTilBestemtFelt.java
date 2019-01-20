package model.chancekort;


/**
 * Chancekort der rykker dig til et bestemt felt paa braettet
 * ved feltets placering.
 */
public class RykTilBestemtFelt extends Chancekort {

    private int feltNummer; // Feltets placering paa braettet

    public int getFeltNummer() {
        return feltNummer;
    }

    public RykTilBestemtFelt(int feltNummer, String beskrivelse ){
        super( beskrivelse, true );
        this.feltNummer = feltNummer;
    }

}
