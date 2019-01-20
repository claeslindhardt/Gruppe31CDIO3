package model.chancekort;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: Chancekort
 *
 *  @author Claes & Malte
 *
 *  Denne klasse er det generelle chancekort, som alle chancekorts typer nedarver fra. *
 *  Her er altså tale om et polymorfisme
 */
public abstract class Chancekort {

    private String beskrivelse;             // Beskrivelsen der bruges, når man bruger / trækker kortet
    private String kortBeskrivelse = "";    // Beskrivelsen der bruges i lister
    private boolean direkteAktion;          // True: Kortet bruges når det trækkes, False: kortet tages op på hånden


    // --------- Constructor --------------------
    public Chancekort( String beskrivelse, boolean direkteAktion ){
        this.beskrivelse = beskrivelse;
        this.direkteAktion = direkteAktion;
    }

    //|--------- Getters og Setters:-------------

    public boolean erDirekteAktion(){
        return direkteAktion;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setKortBeskrivelse(String kortBeskrivelse){
        this.kortBeskrivelse = kortBeskrivelse;
    }

    public String getKortBeskrivelse(){return kortBeskrivelse;}

}
