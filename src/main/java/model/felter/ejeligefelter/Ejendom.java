package model.felter.ejeligefelter;


import static spillogik.BeregnLeje.beregnLejeVedHotel;
import static spillogik.BeregnLeje.beregnLejeVedHus;


/**
 * Klassen der repræsentere ejendomme. Ejendommen har forskellige
 * prisniveauet ved forskelligt antal huse / hotel, og den gemmer
 * alle disse informationer.
 */
public class Ejendom extends EjeligtFelt {

    private boolean harHotel = false;
    private int     antalHuse = 0;
    private double  husPris = 50;
    private double  hotelPris = 100;
    private Ejendomsgruppe gruppe;
    private int lejeHotel = 0;
    private int lejeStart = 0;
    private int[] lejeHus = {0,0,0,0};



    public Ejendom(String navn, int pris, int startLeje, int placering){
        super( navn, pris, placering);
        setPlacering(placering);
        setPris(pris);
        setNavn(navn);

        setLejeStart(startLeje);

        setLejeHus( beregnLejeVedHus(this, 1),
                beregnLejeVedHus(this, 2),
                beregnLejeVedHus(this, 3),
                beregnLejeVedHus(this, 4) );

        setLejeHotel( beregnLejeVedHotel(this) );
    }


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

    public Ejendomsgruppe getGruppe() {
        return gruppe;
    }

    public void setGruppe(Ejendomsgruppe gruppe) {
        this.gruppe = gruppe;
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


    /** @author Malte
     *  Tilføjer huse til ejendommen. Den tjekker ikke for, om der
     *  i følge reglerne kan bygges huse på ejendommen.
     * @param antalHuse: Hvor mange huse, der skal bygges.
     */
    public void bygHuse(int antalHuse){
        setAntalHuse(getAntalHuse()+antalHuse);
    }


    /** @author Malte
     *  Fjerner et hus fra ejendommen.
     * @param antalHuse: Hvor mange huse, der skal bygges.
     */
    public void saelgHus(int antalHuse){
        setAntalHuse(getAntalHuse()-antalHuse);
    }

    public void bygHotel(boolean harHotel){
        setHarHotel(harHotel);
    }

    public void saelgHotel(boolean harHotel){setHarHotel(harHotel);}


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

}
