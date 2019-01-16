package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.ChanceAktionDTO;
import ModelEnteties.felter.FeltDTO;

import java.util.ArrayList;
import java.util.Random;

public class ChanceFeltCO extends FeltDTO {
    //|-------initiering af objekter: -----------
    Random ra = new Random();
    //TODO: fix singleton
    //|--------- Variabler:-----------------
    private ArrayList<ChanceAktionDTO> KortPaaFelt = new ArrayList<ChanceAktionDTO>();

    //|--------- Getters og Setters:-----------------
    public ArrayList<ChanceAktionDTO> getKortPaaFelt() {
        return KortPaaFelt;
    }

    public void setKortPaaFelt(ArrayList<ChanceAktionDTO> kortPaaFelt) {
        KortPaaFelt = kortPaaFelt;
    }

    //|----------- Metoder:------------------

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        ArrayList<ChanceAktionDTO> chancekort = spil.getSpil().getChanceKort();

        ChanceAktionDTO trukketKort = chancekort.get(0);
        chancekort.remove(0);
        chancekort.add(trukketKort);

        trukketKort.DirketeAktion(handel, spil, userInterfaceKontrakt);
    }

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param userInterfaceKontrakt
     */
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.chanceFeltsInfo(this);

    }
    //|--------- Constructor:-----------------
    public ChanceFeltCO( int placering ){
        super( "Prøv lykken", placering );
        setPlacering(placering);
        setFeltType("Chance Kort");
        setNavn("Prøv lykken");
    }
}
