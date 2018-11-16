package ModelEnteties.braet.controllerKlasser;

import java.util.ArrayList;
/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: EjendomsGruppe
 *
 * @author Claes
 *  Dette er klassen der holder styr på grupper af ejendomme. En Ejendomsgruppe er et objekt, som identificeres
 *  ved sin farve.
 *  Når man laver et nyt Ejendoms-objekt, sættes den automatisk ind i en gruppe. Der laves en ny gruppe hvis:
 *
 *      1) Det er den første ejendom (og dermed den første gruppe)
 *      2) Hvis gruppen er fuld (der kan maks være GRUPPESTØRRELSE Ejendomme i én gruppe)
 *
 */
public class EjendomsGruppe {
    //|--------- Variabler:-----------------
    //Statiske variabler
    private static final int GRUPPESTØRRELSE = 3;
    private static final String[] FARVELISTE = {"roed", "groen", "blaa","gul" ,"orange","lilla","lyseroed","sort"};
    private static EjendomsGruppe klarGruppe;
    private static ArrayList<EjendomsGruppe> alleGrupper = new ArrayList<EjendomsGruppe>(); //might come in handy

    //Dynamiske variabler:
    private String farve;
    private ArrayList<Ejendom> ejendomme = new ArrayList<Ejendom>();
    //|--------- Getters og Setters:-----------------
    public static int getGRUPPESTØRRELSE() {
        return GRUPPESTØRRELSE;
    }

    public static String[] getFARVELISTE() {
        return FARVELISTE;
    }

    public static EjendomsGruppe getKlarGruppe() {
        return klarGruppe;
    }

    public static void setKlarGruppe(EjendomsGruppe klarGruppe) {
        EjendomsGruppe.klarGruppe = klarGruppe;
    }

    public static ArrayList<EjendomsGruppe> getAlleGrupper() {
        return alleGrupper;
    }

    public static void setAlleGrupper(ArrayList<EjendomsGruppe> alleGrupper) {
        EjendomsGruppe.alleGrupper = alleGrupper;
    }

    public String getFarve() {
        return farve;
    }

    public void setFarve(String farve) {
        this.farve = farve;
    }

    public ArrayList<Ejendom> getEjendomme() {
        return ejendomme;
    }

    public void setEjendomme(ArrayList<Ejendom> ejendomme) {
        this.ejendomme = ejendomme;
    }


    //|----------- Metoder:------------------
    public static EjendomsGruppe get(Ejendom ejendom){
        // Checking any group has been created, or if the current "open group" is full - then create a new group.
        if( klarGruppe == null || klarGruppe.ejendomme.size() >= GRUPPESTØRRELSE){ klarGruppe = new EjendomsGruppe();}
        klarGruppe.ejendomme.add(ejendom);
        return klarGruppe;
    }
    //Todo bed malte rykke den her til TUI
    public void printInfo(){
        System.out.print("Ejendomsgruppe: "+farve+", har huse: ");
        //TODO:Implement this.
        //ejendomme.forEach(a -> System.out.print(a.getNavn()+", "));
        System.out.println();
    }

    //|--------- Constructor:-----------------
    public EjendomsGruppe() {
        // A new EjendomsGruppe will get the next farve in the farveList
        int groupIndex = alleGrupper.size(); // Putting this in a variable, so I don't have to call a method every time I need it (speeds up)
        if (groupIndex >= FARVELISTE.length){
            System.out.println("WARNING: No more EjendomsGruppe Colors available - reusing farves!");
            farve = FARVELISTE[groupIndex%FARVELISTE.length]; // Using Modulus to "reset" color index counting
        }else{
            farve = FARVELISTE[groupIndex];
        }
        alleGrupper.add(this);
    }
}
