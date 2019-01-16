package ModelEnteties.chancekort;

public class RykTilBestemtFelt extends Chancekort {

    private int feltNummer;

    public int getFeltNummer() {
        return feltNummer;
    }

    public RykTilBestemtFelt(int feltNummer, String beskrivelse ){
        super( beskrivelse, true );
        this.feltNummer = feltNummer;
    }

}
