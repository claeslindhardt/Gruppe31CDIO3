package ModelEnteties;

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
public class NavneGenerator {
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

    public String getGenereretNavn() {
        return generetNavn;
    }

    public void setGenereretNavn(String generetNavn) {
        this.generetNavn = generetNavn;
    }


    /**
     * @author Chua
     * Laver en getter for generering af navn for jernbane.
     *  genererer et random tal for "vejnavn"
     *  saetter dette random tal ind i arraylisten for "getVejNavn()[]" for at få et random vejnavn.
     *  "setGenereretNavn" sætter vejnavnet sammen med stringen "station"
     *  Derefter undersøges om dette nye navn der er blevet genereret i "genereretNavn()" allerede eksisterer.
     *  Dette undersøges ved at sætte det lig med arraylisten "getAlleNavne" med et for-loop (undersøger alle navne i listen).
     *  Hvis der allerede eksisterer et navn som det genererede navn, så vil navnet rettes til vejNavn "2'nd" station.
     *  Derefter, også hvis navnet ikke allerede eksisterer, så tilføjes navnet til arraylisten "alleNavne"
     *  Returnerer det genererede navn som var sat i setteren med "setGenereretNavn", ved at returnere "getGenereretNavn()"
     *  som så alligevel er "genereretNavn"
     *  Dette returneres til getteren "getJernbaneNavn()" som kan benyttes når metoden bliver kaldt.
     * @return
     */
    public String getJernbaneNavn(){
        int vejnavn = getRandomTal().nextInt(Math.round(getVejNavn().length));
            setGenereretNavn(getVejNavn()[vejnavn].concat(" Station"));
            for(int i = 0; i<getAlleNavne().size(); i++){
                if(getGenereretNavn() == getAlleNavne().get(i)){
                    setGenereretNavn(getVejNavn()[vejnavn].concat(" 2'nd ").concat(" Station"));
                }
            }
            addAlleNavne(getGenereretNavn());
        return getGenereretNavn();
    }


    /**
     * @author Chua
     *
     * Laver en getter for genererering af navne for ejendomme
     * Der laves to random integer, som bliver brugt til at sammensætte et navn og sætte det i setGenereretNavn.
     * I setGenereretNavn er der blevet lavet et navn af de to arrays med navne, først vejNavn[] og så vejType[].
     * Vejnavn og type bliver så valgt af de to random tal.
     * Der bliver tjekket for, om navnet allerede eksisterer i "alleNavne" mha. et for loop, som går igennem arraylisten "alleNavne".
     * Hvis navnet allerede eksisterer vil der indsættet et "2'nd" mellem navn og type.
     * Hvis ikke det eksisterer, og hvis det allerede gør, så bliver set sat ind i arraylisten som et nyt navn, det genereredeNavn.
     * Der returneres getteren for genereret navn, da det er navnet der er blevet lavet i denne metode.
     * Det indeholder dog alligevel bare det genererede navn alligevel.
     * @return
     */
    public String getEjendomsNavn() {
        int vejnavn =  getRandomTal().nextInt(getVejNavn().length);
        int vejtype =  getRandomTal().nextInt(getVejType().length);
        setGenereretNavn(getVejNavn()[vejnavn].concat(getVejType()[vejtype]));

        for(int i = 0; i<getAlleNavne().size(); i++){
            if(getGenereretNavn()==getAlleNavne().get(i)){
                setGenereretNavn(getVejNavn()[vejnavn].concat(" 2'nd ").concat(getVejType()[vejtype]));
            }
        }
        addAlleNavne(getGenereretNavn());
        return getGenereretNavn();
    }


    public ArrayList<String> getAlleNavne() {
        return alleNavne;
    }


    public void addAlleNavne(String navn){
        this.alleNavne.add(navn);
    }

    public String[] getVejNavn() {
        return vejNavn;
    }


    public String[] getVejType() {
        return vejType;
    }

}


