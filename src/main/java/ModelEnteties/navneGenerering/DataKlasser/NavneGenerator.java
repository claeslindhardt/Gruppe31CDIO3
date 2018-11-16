package ModelEnteties.navneGenerering.DataKlasser;

import ModelEnteties.singletoner.RandomSingleton;

import java.util.ArrayList;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: NavneGenerator
 *
 * @author Claes
 *  Denne klasse er lavet til at genere navne til braet, derfor bliver den brugt
 *  i BretGeneratoren til at give braet navne når de bliver lavet på et bret.
 *  Den fungere på den måde at den concatinaterer et tilfældigt vejNavn med en
 *  tilfældig vejtype eller "station". Så ledes kan et stort antal vej navne. Generes
 *  uden de store problemer ønkes et stort bret. Eller et lille antal hurtigt. Således
 *  er disse klasser kodet skalerbart. Forældre klassen hedder NavneGenerator og den har
 *  2 børn:
 *          1. Ejendomsdoeber
 *          2. Jernbanedoeber
 */
public abstract class NavneGenerator {
    //|--------- Variabler:----------------------
    private String generetNavn;
    private ArrayList<String> alleNavne = new  ArrayList<String>();
    private String[] vejNavn = {"Old Kent", "Whitechapel", "Mediterranean", "Baltic",
            "The Angel Islington", "Oriental", "Euston", "Vermont", "Pentonville",
            "Connecticut", "Pall Mall", "St. Charles", "Whitehall", "States Avenue",
            "Northumberland", "Virginia", "Bow", "St. James",
            "Marlborough", "Tennessee", "Vine", "New York",
            "The Strand", "Kentucky Avenue", "Fleet", "Indiana",
            "Trafalgar", "Illinois", "Leicester", "Atlantic",
            "Coventry", "Ventnor", "Piccadilly", "Marvin", "Bruce",
            "Regent", "Pacific ", "Oxford", "North Carolina",
            "Bond", "Pennsylvania", "Park", "Park", "Mayfair", "Boardwalk"};
    private String[] vejType = {" Avenue"," Road", " Lane", " Boulevard", " Park", " Venue", " SideStreet",
            " place", " Street", " promonade", " beach", " Square", " Gardens"};

    //Singleton Variabler;
    private RandomSingleton randomTal = RandomSingleton.getInstance();

    //|--------- Getters og Setters:-------------
    public RandomSingleton getRandomTal() {
        return randomTal;
    }

    public void setRandomTal(RandomSingleton randomTal) {
        this.randomTal = randomTal;
    }
    public String getGeneretNavn() {
        return generetNavn;
    }

    public void setGeneretNavn(String generetNavn) {
        this.generetNavn = generetNavn;
    }

    public ArrayList<String> getAlleNavne() {
        return alleNavne;
    }

    public void setAlleNavne(ArrayList<String> alleNavne) {
        this.alleNavne = alleNavne;
    }

    public void addAlleNavne(String navn){
        this.alleNavne.add(navn);
    }

    public String[] getVejNavn() {
        return vejNavn;
    }

    public void setVejNavn(String[] vejNavn) {
        this.vejNavn = vejNavn;
    }

    public String[] getVejType() {
        return vejType;
    }

    public void setVejType(String[] vejType) {
        this.vejType = vejType;
    }
}
