package ModelEnteties.chancekort;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: Chancekort
 *
 *  @author Claes & Malte
 *
 *  Denne klasse er det generelle chancekort, som alle chancekorts typer nedarver fra. *
 *  Her er altså tale om et polymorfisme(se programdokumentationen til felt.java for yderligere forklaring af dette),
 *  dette kan godt virke tricky. Eftersom dette polymorfisme ligger inden i et andet polymorfisme. Derfor har vi lavet
 *  Design Class Diagrammet som burde give et overblik over sammenspillet mellem alle klasserne. Grunden til vi har
 *  valgt også at lave dette som polymorfisme er fordi vi har vuderet at det er en vigtig del af det, der gør at spillet
 *  ikke bliver det vi kalder et 0 spil inden for spil teori. Det er et spil hvor man altid vil kunne forudse hvem vinder.
 *  Dette har også meget at sige i forhold til hvor meget varation der er fra spil til spil da dette virker som en
 *  atraktiv ting meget varation og skalerbar kode. Har vi gjort meget ud af at denne del blev netop det. Ved at lave
 *  en grund generationer der generer ny beskrivelser til alle chance kort. Så chancen for at to chancekort noglesinde
 *  er de samme er meget lille, og har via polymorfismet en meget skalerbar antal aktioner der følger af chancekortene.
 *
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
