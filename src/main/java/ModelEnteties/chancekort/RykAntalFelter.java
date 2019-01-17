package ModelEnteties.chancekort;

public class RykAntalFelter extends Chancekort {

    private int antalFelterAtRykke;

    public int getAntalFelterAtRykke(){
        return antalFelterAtRykke;
    }

    public RykAntalFelter(int antalFelterAtRykke, String beskrivelse){
        super(beskrivelse, true);
        this.antalFelterAtRykke = antalFelterAtRykke;
    }
}
