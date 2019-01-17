package ModelEnteties.chancekort;

public class JustererPenge extends Chancekort {

    private double pengeVaerdi; // Kan være negativ

    public double getPengeVaerdi(){
        return pengeVaerdi;
    }

    public JustererPenge( double pengeVaerdi, String beskrivelse ){
        super( beskrivelse, true );
        this.pengeVaerdi = pengeVaerdi;
    }

}
