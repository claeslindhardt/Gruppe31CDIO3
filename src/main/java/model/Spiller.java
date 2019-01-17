package model;

import model.chancekort.Chancekort;
import model.felter.Bryggeri;
import model.felter.Ejendom;
import model.felter.Rederi;

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
    private int spillerPosition=0;
    private int id;
    private String navn = "?";
    // TODO: find en løsning på dette, vi har både en liste af spiller ejendom i spillerData og en ejer på ejendom. Det er High copleing modsat af de vi ønsker lav enentuellt en registre løsning
    ArrayList<Ejendom> spillerEjendomme = new ArrayList<Ejendom>();
    ArrayList<Rederi> spillerRederier = new ArrayList<>();
    ArrayList<Bryggeri> spillerBryggeri = new ArrayList<Bryggeri>();

    ArrayList<Chancekort> chancekort = new ArrayList<Chancekort>();

    protected double penge = 1500;
    protected boolean harGivetOp=false;
    protected boolean harAnketDomDenneRunde = false;
    protected boolean harSlaaetForTuren = false;

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

    public void setHarSlaaet(boolean harSlaaet) {
        this.harSlaaet = harSlaaet;
    }

    public boolean harSlaaet(){
        return harSlaaet;

    }


    public int getSpillerPosition() {
        return spillerPosition;
    }

    public void setSpillerPosition(int spillerPosition) {
        this.spillerPosition = spillerPosition;
    }

    public ArrayList<Ejendom> getSpillerEjendomme() {
        return spillerEjendomme;
    }

    public void setSpillerEjendomme(ArrayList<Ejendom> spillerEjendomme) {
        this.spillerEjendomme = spillerEjendomme;
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
    public Ejendom[] getEjendomme() {
        if(spillerEjendomme != null){

            Ejendom[] ejendomme = new Ejendom[spillerEjendomme.size()];

            for( int i = 0; i < ejendomme.length; i++){
                ejendomme[i] = spillerEjendomme.get(i);
            }

            return ejendomme;
        }else{
            return null;
        }
    }

    public void clearEjendomme(){
        spillerEjendomme.clear();
    }

    public void clearChancekort(){
        chancekort.clear();
    }

    public ArrayList<Bryggeri> getSpillerBryggerier() {
        return spillerBryggeri;
    }
    
    public void addBryggeri(Bryggeri bryggeri){
        spillerBryggeri.add(bryggeri);
    }

    public void addRederi(Rederi rederi){
        spillerRederier.add(rederi);
    }

    public ArrayList<Rederi> getSpillerRederier(){ return spillerRederier;}

    public void setSpillerRederier (ArrayList<Rederi> spillerRederier){
        this.spillerRederier = spillerRederier;
    }


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

    public boolean isHarGivetOp() {
        return harGivetOp;
    }

    public void setHarGivetOp(boolean harGivetOp) {
        this.harGivetOp = harGivetOp;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
    public boolean isHarSlaaetForTuren() {
        return harSlaaetForTuren;
    }

    public void setHarSlaaetForTuren(boolean harSlaaetForTuren) {
        this.harSlaaetForTuren = harSlaaetForTuren;
    }

    public boolean isHarAnketDomDenneRunde() {
        return harAnketDomDenneRunde;
    }

    public void setHarAnketDomDenneRunde(boolean harAnketDomDenneRunde) {
        this.harAnketDomDenneRunde = harAnketDomDenneRunde;
    }
    public void addPenge(double beloeb) {
        this.penge+=beloeb;
    }

    /**
     * @author Jacob og Andreas
     * tilføjer ejendomme til en spillers ejendomme.
     * Denne metode bliver brugt i vores test spil.
     *
     * @param ejendom den ejendom der skal tilføjes
     */
    public void tilføjEjendom(Ejendom ejendom) {
        spillerEjendomme.add(ejendom);
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
     * @author Malte
     * Undersoege om man ejer en specifik ejendom, ved at sammenligne
     * ejeren af ejendommen med spilleren.
     * @param ejendom Ejendommen man oensker at undersoege.
     * @return True: spilleren ejer den, False: spilleren ejer den ikke.
     */
    public boolean ejerEjendom(Ejendom ejendom){
        return ejendom.getEjer() == this;
    }






}
