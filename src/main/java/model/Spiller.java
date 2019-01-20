package model;

import model.chancekort.Chancekort;
import model.felter.ejeligefelter.*;

import java.util.ArrayList;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: Spiller
 *
 * @author Claes
 *  Denne klasse holder den indivudelles spiller informationer,
 *  Den bliver brugt i SpilLederen, til at genere en liste med spillerObjekter
 *  som indholder alle de spillere der indgår i et givet spil.
 */
public class Spiller {
    //|--------- Variabler:-----------------
    private int id;
    private String navn = "?";

    private int position =0;
    private double penge = 1500;

    private ArrayList<Ejendom> ejendomme = new ArrayList<Ejendom>();
    private ArrayList<Rederi> rederier = new ArrayList<>();
    private ArrayList<Bryggeri> bryggerier = new ArrayList<Bryggeri>();
    private ArrayList<Chancekort> chancekort = new ArrayList<Chancekort>();

    private boolean erUdgaaet = false;
    private boolean erIFaengsel = false;
    private boolean harSlaaet = false;


    //|--------- Getters og Setters:-----------------

    public boolean erIFaengsel(){
        return erIFaengsel;
    }

    public boolean erUdgaaet() {
        return erUdgaaet;
    }

    public void setErUdgaaet( boolean erUdgaaet ) {
        this.erUdgaaet = erUdgaaet;
    }

    public void setErIFaengsel(boolean erIFaengsel) {
        this.erIFaengsel = erIFaengsel;
    }




    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Chancekort> getChancekort() {
        return chancekort;
    }

    public void addChancekort( Chancekort chancekort) {
        this.chancekort.add(chancekort);
    }

    public void fjernChancekort( Chancekort chancekort ){ this.chancekort.remove(chancekort); }

    public boolean harChancekort( Chancekort chancekort ){
        return this.chancekort.contains( chancekort );
    }

    /**
     * @author Malte
     * Henter liste over spillerens ejendomme.
     * @return Listen over spillerens ejendomme som en Ejendom array. Er tom, hvis der ikke er nogen i listen.
     */
    public Ejendom[] getEjendommeArray() {
        return ejendomme.toArray( new Ejendom[0] );
    }


    public void clearChancekort(){
        chancekort.clear();
    }

    public ArrayList<Bryggeri> getSpillerBryggerier() {
        return bryggerier;
    }

    public ArrayList<Rederi> getRederier(){ return rederier;}

    public double getPenge() {
        return penge;
    }

    public void setPenge(double penge) {
        this.penge = penge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
    public boolean harSlaaet() {
        return harSlaaet;
    }

    public void setHarSlaaet(boolean harSlaaetForTuren) {
        this.harSlaaet = harSlaaetForTuren;
    }

    public void addPenge(double beloeb) {
        this.penge+=beloeb;
    }


    /**
     * @author Malte
     * Undersoeger om spilleren ejer alle ejendomme i en specifik
     * ejendomsgruppe.
     * @param ejendomsGruppe Hvilken ejendomsgruppe man vil undersoege.
     * @return true: spilleren ejer alle i gruppen, false: spillere ejer ikke alle i gruppen
     */
    public boolean ejerEjendomsGruppe( Ejendomsgruppe ejendomsGruppe ){
        for( Ejendom ejendom : ejendomsGruppe.getEjendomme()){
            if( ejendom.getEjer() != this ){
                return false;
            }
        }
        return true;
    }


    /**
     * Samler alle ejedefelter til en liste og returnerer den.
     */
    public ArrayList<EjeligtFelt> getEjedeFelter(){

        ArrayList<EjeligtFelt> ejedeFelter = new ArrayList<>();

        ejedeFelter.addAll(bryggerier);
        ejedeFelter.addAll(rederier);
        ejedeFelter.addAll(ejendomme);

        return ejedeFelter;
    }

    /**
     * Tilfoejer et ejeligt felt til Spilleren. Den finder ud af
     * hvilken liste den skal ligge i (ejendomme, rederier, bryggerier)
     * og tilfoejer den til den rigtige liste.
     *
     * @author Malte
     * @param ejeligtFelt Feltet der skal tilfoejes til spilleren
     */
    public void addEjeligtFelt( EjeligtFelt ejeligtFelt ){
        if( ejeligtFelt instanceof Ejendom ){
            ejendomme.add( (Ejendom) ejeligtFelt );
        }
        if( ejeligtFelt instanceof Bryggeri ){
            bryggerier.add( (Bryggeri) ejeligtFelt );
        }
        if( ejeligtFelt instanceof Rederi ){
            rederier.add( (Rederi) ejeligtFelt );
        }
    }

}
