package ModelEnteties.felter;

import Controller.SpilController;
import BoundaryView.UserInterfaceKontrakt;
import Controller.SpillerCO;
import ModelEnteties.EjendomsGruppeDTO;


/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: EjendomCO
 *
 */
public class EjendomCO extends EjeligtFeltDTO {

    private int     antalHuse;
    private int     antalHoteller;
    private double  husPris = 50;
    private int     leje = 50;
    private EjendomsGruppeDTO gruppe;
    private int lejeHotel = 0;
    private int lejeStart = 0;
    private int[] lejeHus = {0,0,0,0};


    //|--------- Getters og Setters:-----------------
    public int getAntalHuse() {
        return antalHuse;
    }

    public void setAntalHuse(int antalHuse) {
        this.antalHuse = antalHuse;
    }

    public int getAntalHoteller() {
        return antalHoteller;
    }

    public void setAntalHoteller(int antalHoteller) {
        this.antalHoteller = antalHoteller;
    }

    public double getHusPris() {
        return husPris;
    }

    public void setHusPris(double husPris) {
        this.husPris = husPris;
    }

    public EjendomsGruppeDTO getGruppe() {
        return gruppe;
    }

    public void setGruppe(EjendomsGruppeDTO gruppe) {
        this.gruppe = gruppe;
    }


    /**
     * @author Malte
     * Henter hvad lejen er på grunden, ved et bestemt antal huse.
     *
     * @param antalHuse Antallet af huse man ønsker at kende lejen ved. Skal ligge mellem 1 og 4 (begge inklusiv).
     *                  Giver man et input over dette returnerer den lejen ved 4 huse, og giver man et input
     *                  under dette returnerer den lejen ved 1 hus.
     * @return Lejen ved det antal huse, der er blevet indtastet.
     */
    public int getLejeHus(int antalHuse){
        // Justerer antalHuse, så det passer med index i arrayliste
        antalHuse--;

        if( antalHuse < 0 ) {
            antalHuse = 0;
        }else if( antalHuse > 3){
            antalHuse = 3;
        }
        return lejeHus[antalHuse];
    }


    /**
     * @author Malte
     * @return Grundens leje hvis der står et hotel.
     */
    public int getLejeHotel(){
        return lejeHotel;
    }

    /**
     * @return Grundens leje når der hverken er hotel eller huse på.
     */
    public int getLejeStart(){
        return lejeStart;
    }


    /**
     * Indsæt beskrivelse her
     * @return
     */
    public int getLeje() {
        /* Ejendommens leje er vurderet ved standard lejen (leje),
            og antallet huse. Hvert hus øger lejen med halvdelen af den originale leje */
        int lejePerHus = leje/2;
        int totalLeje = leje + lejePerHus*antalHuse;
        return totalLeje;
    }

    public void setLeje(int leje) {
        this.leje = leje;
    }


    //|----------- Metoder:------------------

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param userInterfaceKontrakt
     */
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.ejendomsInfo(this);
    }

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO spillerMedTur = spil.getSpillerMedTur();
        if(this.getEjer()==null){
            this.printInfo(userInterfaceKontrakt);


            int kobsBeslutning = userInterfaceKontrakt.ejendomsBud();
            switch (kobsBeslutning){
                case 1:
                    spillerMedTur.koebEjendom(this, userInterfaceKontrakt);
                    break;
                case 2:
                    userInterfaceKontrakt.forsetTur();

                    break;
                default:
                    userInterfaceKontrakt.ikkeMuligt();
            }
        }else if(this.getEjer() != null && this.getEjer() != spillerMedTur){
            userInterfaceKontrakt.betalRente();
            userInterfaceKontrakt.updateSpillere(spillerMedTur);
            this.indsamleLeje(spillerMedTur, userInterfaceKontrakt);
        }else if(this.getEjer() == spillerMedTur){
            userInterfaceKontrakt.tetPaaMonopol();
        }
    }

    /**
     * Indsæt beskrivelse her
     * @param spilleren
     * @param userInterfaceKontrakt
     */
    public void indsamleLeje(SpillerCO spilleren, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO ejeren = this.getEjer();
        if( ejeren != null && spilleren != null) {
            //todo: enkapsuler dette på en ordenligt måde
            spilleren.setPenge(spilleren.getPenge()-getLeje());
            ejeren.addPenge(getLeje());  // hvis Spiller ikke har nok penge til at betale skal den have mulighed for at pantsætte
            userInterfaceKontrakt.updateSpillere(spilleren);
        }else{
            userInterfaceKontrakt.badErrorMessage();
        }
    }


    /** @author Malte
     *  Tilføjer huse til ejendommen. Den tjekker ikke for, om der
     *  i følge reglerne kan bygges huse på ejendommen.
     * @param antalHuse: Hvor mange huse, der skal bygges.
     */
    public void bygHuse(int antalHuse){
        setAntalHuse(getAntalHuse()+antalHuse);
    }

    //|--------- Constructor:-----------------
    public EjendomCO(String whatName, int whatPrice, int whatRent, int placering){
        setPlacering(placering);
        setPris(whatPrice);
        setNavn(whatName);
        setLeje(whatRent);

        setAntalHoteller(0);
        setAntalHuse(0);
        setFeltType("Ejendom");
    }
}
