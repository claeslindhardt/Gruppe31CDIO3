package ModelEnteties.braet.controllerKlasser;

import java.util.ArrayList;

/**
 * @author Malte
 *  Dette er klassen der holder styr på grupper af ejendomme. En Ejendomsgruppe er et objekt, som identificeres
 *  ved sin farve.
 *  Når man laver et nyt Ejendoms-objekt, sættes den automatisk ind i en gruppe. Der laves en ny gruppe hvis:
 *
 *      1) Det er den første ejendom (og dermed den første gruppe)
 *      2) Hvis gruppen er fuld (der kan maks være GRUPPESTØRRELSE Ejendomme i én gruppe)
 *
 */

public class EjendomsGruppe {

    private int stoerrelse;
    private String farve;
    private ArrayList<Ejendom> ejendomme = new ArrayList<Ejendom>();

    /**
     * @author Malte
     * Laver en ny tom ejendomsgruppe, der kan indeholde 'stoerrelse' antal ejendomme i sig.
     * Gruppen kendetegnes derudover ved sin farve.
     *
     * @param farve Farven som skal kendetegne gruppen (givet kun en visuel sammenligning af gruppen,
     *              og har ingen betydning for hvordan Ejendommene vurderes om de er i samme gruppe.
     * @param stoerrelse Antallet af Ejendomme som gruppe skal indeholder.
     */
    public EjendomsGruppe(String farve, int stoerrelse) {
        this.farve = farve;
        this.stoerrelse = stoerrelse;
    }

    //--------- Getters og Setters:-----------------

    public String getFarve() {
        return farve;
    }

    /**
     * @author Malte
     * Ser om Ejendomsgruppen er fuld (dvs. der ikke bør være flere ejendomme i den).
     * @return True: den er fuld, False: der er stadig plads.
     */
    public boolean erFuld(){
        return ejendomme.size() >= stoerrelse;
    }

    public ArrayList<Ejendom> getEjendomme() {
        return ejendomme;
    }

    public int getAntalEjendomme(){ return ejendomme.size(); }

    /**
     * @author Malte
     * Undersoeger om gruppen indeholder en bestemt ejendom.
     * @param ejendom Ejendommen som gruppen skal indeholder
     * @return Om den indeholder 'ejendom' eller ej.
     */
    public boolean indeholderEjendom(Ejendom ejendom){
        return ejendomme.contains(ejendom);
    }

    /** Tilføjer en ejendom til gruppen
     * @param ejendom Ejendommen der skal tilføjes til gruppen.
     * @author Malte
     */
    public void tilfoejEjendom( Ejendom ejendom){
        ejendomme.add(ejendom);
    }


    //|----------- Metoder:------------------

    //Todo bed malte rykke den her til TUI
    public void printInfo(){
        System.out.print("Ejendomsgruppe: "+farve+", har huse: ");
        //TODO:Implement this.
        //ejendomme.forEach(a -> System.out.print(a.getNavn()+", "));
        System.out.println();
    }

    // TODO: Implementér logikken i denne. Kan være den skal flyttes.
    public boolean huseErLigeligtFordelt(){
        return true;
    }


}
