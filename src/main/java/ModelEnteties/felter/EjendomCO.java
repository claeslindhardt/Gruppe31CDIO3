package ModelEnteties.felter;

import Controller.Handel;
import Controller.SpilController;
import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spiller;
import spillogik.EjendomsLogik;


/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: EjendomCO
 *
 */
public class EjendomCO extends EjeligtFeltDTO {

    private boolean harHotel = false;
    private int     antalHuse = 0;
    private double  husPris = 50;
    private double  hotelPris = 100;
    private int     leje = 0;
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

    public boolean harHotel(){
        return harHotel;
    }

    public void setHarHotel(boolean harHotel) {
        this.harHotel = harHotel;
    }

    public double getHusPris() {
        return husPris;
    }

    public double getHotelPris(){return hotelPris;}

    public void setHotelPris(double hotelPris) {
        this.hotelPris = hotelPris;
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
     * @param antalHuse Antallet aPf huse man ønsker at kende lejen ved. Skal ligge mellem 1 og 4 (begge inklusiv).
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
     * @author Malte
     * @return Grundens leje når der hverken er hotel eller huse på.
     */
    public int getLejeStart(){
        return lejeStart;
    }


    /**
     * @author Malte
     */
    public int getLeje() {
        return leje;
    }

    public void setLeje(int leje) {
        this.leje = leje;
    }

    /**
     * @param etHus     Lejen på grunden, når der står ét hus på.
     * @param toHuse    Lejen på grunden, når der står to huse på.
     * @param treHuse   Lejen på grunden, når der står tre huse på.
     * @param fireHuse  Lejen på grunden, når der står fire huse på.
     */
    public void setLejeHus( int etHus, int toHuse, int treHuse, int fireHuse ){
        lejeHus[0] = etHus;
        lejeHus[1] = toHuse;
        lejeHus[2] = treHuse;
        lejeHus[3] = fireHuse;
    }

    /**
     * @param lejeStart Lejen på grunden, når der står hverken hotel eller huse på.
     */
    public void setLejeStart(int lejeStart){
        this.lejeStart = lejeStart;
    }

    /**
     * @param lejeHotel Lejen af grunden, når der står et hotel på.
     */
    public void setLejeHotel(int lejeHotel){
        this.lejeHotel = lejeHotel;
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
    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        Spiller spillerMedTur = spil.getSpil().getSpillerMedTur();

        if(this.getEjer()==null){
            this.printInfo(userInterfaceKontrakt);


            int kobsBeslutning = userInterfaceKontrakt.ejendomsBud();
            switch (kobsBeslutning){
                case 0:
                    spil.getKoebFelt().koebEjendom(this, spillerMedTur, userInterfaceKontrakt);
                    break;
                case 1:
                    userInterfaceKontrakt.forsetTur();

                    break;
                default:

            }
        }else if(this.getEjer() != null && this.getEjer() != spillerMedTur){
            userInterfaceKontrakt.betalRente();
            userInterfaceKontrakt.updateSpillere(spillerMedTur);
            handel.indsamleLeje(spil.getSpil(),this,spillerMedTur, userInterfaceKontrakt);
        }else if(this.getEjer() == spillerMedTur){
            userInterfaceKontrakt.tetPaaMonopol();
        }
    }

    /**
     * Indsæt beskrivelse her
     * @param spilleren
     * @param userInterfaceKontrakt
     */



    /** @author Malte
     *  Tilføjer huse til ejendommen. Den tjekker ikke for, om der
     *  i følge reglerne kan bygges huse på ejendommen.
     * @param antalHuse: Hvor mange huse, der skal bygges.
     */
    public void bygHuse(int antalHuse){
        setAntalHuse(getAntalHuse()+antalHuse);
    }

    public void bygHotel(boolean harHotel){
        setHarHotel(harHotel);
    }

    //|--------- Constructor:-----------------
    public EjendomCO(String navn, int pris, int startLeje, int placering){
        setPlacering(placering);
        setPris(pris);
        setNavn(navn);

        setLejeStart(startLeje);

        setLejeHus( EjendomsLogik.beregnLejeVedHus(this, 1),
                    EjendomsLogik.beregnLejeVedHus(this, 2),
                    EjendomsLogik.beregnLejeVedHus(this, 3),
                    EjendomsLogik.beregnLejeVedHus(this, 4) );

        setLejeHotel( EjendomsLogik.beregnLejeVedHotel(this) );

        setLeje(startLeje);

        setFeltType("Ejendom");
    }
}
