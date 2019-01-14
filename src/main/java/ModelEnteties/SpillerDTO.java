package ModelEnteties;

import ModelEnteties.felter.EjendomCO;
import Controller.JernbaneCO;
import ModelEnteties.felter.ChanceAktionDTO;

import java.util.ArrayList;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: Spiller
 *
 * @author Claes
 *  Denne klasse holder den indivudelles spiller informationer,
 *  Den bliver brugt i SpilLederen, til at genere en liste med spillerObjekter
 *  som indholder alle de spillere der indgår i et givet spil.
 */
public class SpillerDTO {
    //|--------- Variabler:-----------------
    private int spillerPosition=0;
    private int id;
    private String navn = "?";
    // TODO: find en løsning på dette, vi har både en liste af spiller ejendom i spillerData og en ejer på ejendom. Det er High copleing modsat af de vi ønsker lav enentuellt en registre løsning
    ArrayList<EjendomCO> spillerEjendomme = new ArrayList<EjendomCO>();
    ArrayList<JernbaneCO> spillerJernbaner = new ArrayList<JernbaneCO>();
    ArrayList<ChanceAktionDTO> spillerAktionsKort = new ArrayList<ChanceAktionDTO>();
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

    public ArrayList<EjendomCO> getSpillerEjendomme() {
        return spillerEjendomme;
    }

    public void setSpillerEjendomme(ArrayList<EjendomCO> spillerEjendomme) {
        this.spillerEjendomme = spillerEjendomme;
    }


    /**
     * @author Malte
     * Henter liste over spillerens ejendomme.
     * @return Listen over spillerens ejendomme som en EjendomCO array. Er tom, hvis der ikke er nogen i listen.
     */
    public EjendomCO[] getEjendomme() {
        if(spillerEjendomme != null){

            EjendomCO[] ejendomme = new EjendomCO[spillerEjendomme.size()];

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

    public void setSpillerJernbaner(ArrayList<JernbaneCO> spillerJernbaner) {
        this.spillerJernbaner = spillerJernbaner;
    }

    public ArrayList<ChanceAktionDTO> getSpillerAktionsKort() {
        return spillerAktionsKort;
    }

    public void setSpillerAktionsKort(ArrayList<ChanceAktionDTO> spillerAktionsKort) {
        this.spillerAktionsKort = spillerAktionsKort;
    }

    /**
     * Indsæt beskrivelse her
     * @param spillerAktionsKort
     */
    public void addSpillerAktionsKort(ChanceAktionDTO spillerAktionsKort) {
        this.spillerAktionsKort.add(spillerAktionsKort);
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
    public void tilføjEjendom(EjendomCO ejendom){
        spillerEjendomme.add(ejendom);


    }
}
