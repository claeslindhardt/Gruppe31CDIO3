package navneGenerering.DataKlasser;

import java.util.ArrayList;
import java.util.Random;

public abstract class NavneGenerator {
    //|-------initiering af objekter: -----------
    private Random rand = new Random();

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
    //|--------- Getters og Setters:-------------
    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
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
