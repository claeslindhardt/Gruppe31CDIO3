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

    private static final int GRUPPESTØRRELSE = 3;
    private static final String[] FARVELISTE = {"roed", "groen", "blaa","gul" ,"orange","lilla","lyseroed","sort"};

    private static EjendomsGruppe klarGruppe;
    private static ArrayList<EjendomsGruppe> alleGrupper = new ArrayList<EjendomsGruppe>(); //might come in handy

    //Dynamiske variabler:
    private String farve;
    private ArrayList<Ejendom> ejendomme = new ArrayList<Ejendom>();


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


    //|--------- Getters og Setters:-----------------

    public static ArrayList<EjendomsGruppe> getAlleGrupper() {
        return alleGrupper;
    }

    public String getFarve() {
        return farve;
    }

    public ArrayList<Ejendom> getEjendomme() {
        return ejendomme;
    }

    /** Tilføjer en ejendom til gruppen
     * @param ejendom Ejendommen der skal tilføjes til gruppen.
     * @author Malte
     */
    public void tilfoejEjendom( Ejendom ejendom){
        ejendomme.add(ejendom);
    }

    //|----------- Metoder:------------------

    /** Henter en gruppe, hvori der er plads til en ejendom.
     * @return en gruppe med plads til mindst én ejendom mere.
     * @author Malte
     */
    public static EjendomsGruppe getKlarGruppe(){
        if( klarGruppe == null || klarGruppe.ejendomme.size() >= GRUPPESTØRRELSE){
            klarGruppe = new EjendomsGruppe();}
        return klarGruppe;
    }

    //Todo bed malte rykke den her til TUI
    public void printInfo(){
        System.out.print("Ejendomsgruppe: "+farve+", har huse: ");
        //TODO:Implement this.
        //ejendomme.forEach(a -> System.out.print(a.getNavn()+", "));
        System.out.println();
    }


}
