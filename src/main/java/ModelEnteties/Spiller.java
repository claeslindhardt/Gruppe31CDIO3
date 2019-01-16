package ModelEnteties;

import ModelEnteties.felter.Bryggeri;
import ModelEnteties.felter.Ejendom;
import Controller.JernbaneCO;
import ModelEnteties.felter.Rederi;

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
    ArrayList<JernbaneCO> spillerJernbaner = new ArrayList<JernbaneCO>();
    ArrayList<Rederi> spillerRederier = new ArrayList<>();
    ArrayList<Bryggeri> spillerBryggeri = new ArrayList<Bryggeri>();

    ArrayList<ChanceAktionDTO> chancekort = new ArrayList<ChanceAktionDTO>();

    protected double penge = 1500;
    protected boolean faengselsStraf = false;
    protected boolean harGivetOp=false;
    protected boolean harAnketDomDenneRunde = false;
    protected boolean harSlaaetForTuren = false;


    //|--------- Getters og Setters:-----------------

    public int getSpillerPosition() {
        return spillerPosition;
    }

    public void setSpillerPosition(int spillerPosition) {
        this.spillerPosition = spillerPosition;
    }

    public boolean isFaengselsStraf() {
        return faengselsStraf;
    }

    public void setFaengselsStraf(boolean faengselsStraf) {
        this.faengselsStraf = faengselsStraf;
    }

    public ArrayList<Ejendom> getSpillerEjendomme() {
        return spillerEjendomme;
    }

    public void setSpillerEjendomme(ArrayList<Ejendom> spillerEjendomme) {
        this.spillerEjendomme = spillerEjendomme;
    }


    public ArrayList<ChanceAktionDTO> getChancekort() {
        return chancekort;
    }

    public void addChancekort( ChanceAktionDTO chancekort) {
        this.chancekort.add(chancekort);
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

    public ArrayList<JernbaneCO> getSpillerJernbaner() {
        return spillerJernbaner;
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

    public void setSpillerJernbaner(ArrayList<JernbaneCO> spillerJernbaner) {
        this.spillerJernbaner = spillerJernbaner;
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
    public boolean ejerEjendomsGruppe( EjendomsGruppeDTO ejendomsGruppe ){
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
