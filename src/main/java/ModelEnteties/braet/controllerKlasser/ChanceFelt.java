package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.braet.dataKlasser.AktionsFelter;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;

import java.util.ArrayList;
import java.util.Random;

public class ChanceFelt extends AktionsFelter {
    //|-------initiering af objekter: -----------
    Random ra = new Random();
    //TODO: fix singleton
    //|--------- Variabler:-----------------
    private ArrayList<ChanceAktion> KortPaaFelt = new ArrayList<ChanceAktion>();

    //|--------- Getters og Setters:-----------------
    public ArrayList<ChanceAktion> getKortPaaFelt() {
        return KortPaaFelt;
    }

    public void setKortPaaFelt(ArrayList<ChanceAktion> kortPaaFelt) {
        KortPaaFelt = kortPaaFelt;
    }

    //|----------- Metoder:------------------
    public void aktionPaaFelt(SpilController spil){
        int muligeKort = spil.getBretGeneretForSpil().getStandardAntalChanceKortPrFelt();
        int vælgAktion = ra.nextInt(muligeKort)+1;
        ChanceAktion relavantKort = KortPaaFelt.get(vælgAktion);
        relavantKort.DirketeAktion();
    }
    public void printInfo(UserInterface userInterface){
        userInterface.chanceFeltsInfo(this);

    }
    //|--------- Constructor:-----------------
    public ChanceFelt(int placering, ArrayList<ChanceAktion> chanceKortTilFelt){
        setFeltType("Chance Kort");
        setNavn("Prøv lykken");
        setPlacering(getPlacering());
        this.setKortPaaFelt(chanceKortTilFelt);
    }
}
