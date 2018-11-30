package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.braet.dataKlasser.AktionsFelterDTO;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktionDTO;

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
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int muligeKort = spil.getBretGeneretForSpil().getStandardAntalChanceKortPrFelt();
        int vælgAktion = ra.nextInt(muligeKort)+1;
        ChanceAktionDTO relavantKort = KortPaaFelt.get(vælgAktion);
        relavantKort.DirketeAktion();
    }
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.chanceFeltsInfo(this);

    }
    //|--------- Constructor:-----------------
    public ChanceFeltCO(int placering, ArrayList<ChanceAktionDTO> chanceKortTilFelt){
        setFeltType("Chance Kort");
        setNavn("Prøv lykken");
        setPlacering(getPlacering());
        this.setKortPaaFelt(chanceKortTilFelt);
    }
}
