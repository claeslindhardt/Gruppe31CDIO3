import controller.SpilController;
import model.Spil;
import model.chancekort.Chancekort;
import model.chancekort.GratisUdAfFaengsel;
import spillogik.spilgenerering.SpilGenerator;

import java.util.ArrayList;

public class SpilMedFalskRaflebaeger {

    public static void main(String[] args) {

        Spil spil = SpilGenerator.genererSpil();
        spil.setRaflebaeger(new FalskRaflebaeger(2));


        ArrayList<Chancekort> chancekort = new ArrayList<Chancekort>();

        chancekort.add(new GratisUdAfFaengsel("I andledningen af Kongens fødselsdag, " +
                "benådes der herved for fængsel. Dette kort kan opbevares, indtil de får brug for det."));

        spil.setChancekort(chancekort);

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }
}