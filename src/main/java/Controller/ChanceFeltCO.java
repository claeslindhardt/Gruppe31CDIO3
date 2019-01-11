package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.AktionsFelterDTO;
import ModelEnteties.felter.ChanceAktionDTO;

import java.util.ArrayList;
import java.util.Random;

public class ChanceFeltCO extends AktionsFelterDTO {
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
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int muligeKort = spil.getSpil().getBraet().getStandardAntalChanceKortPrFelt();
        int vælgAktion = ra.nextInt(muligeKort)+1;
        ChanceAktionDTO relavantKort = KortPaaFelt.get(vælgAktion);
        relavantKort.DirketeAktion(spil, userInterfaceKontrakt);
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
    public ChanceFeltCO(int placering, ArrayList<ChanceAktionDTO> chanceKortTilFelt){
        setPlacering(placering);
        setFeltType("Chance Kort");
        setNavn("Prøv lykken");
        this.setKortPaaFelt(chanceKortTilFelt);
    }
}
