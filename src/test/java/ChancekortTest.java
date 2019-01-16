import Controller.GratisUdAfFaengselCO;
import Controller.RykkerSpillerCO;
import Controller.SpilController;
import ModelEnteties.chancekort.Chancekort;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import spillogik.SpilGenerator;

import java.util.ArrayList;

public class ChancekortTest {


    /**
     * Test Suite: Chancekort
     *
     * Dette er en test suite, der er beregnet til at teste chancekort.
     * Den indeholder "custom" chancekort, så det er nemmere at se, om
     * kortene går rigtigt i gennem systemet.
     * Andet
     *  - Klassisk braet
     *  - Falsk raflebaeger
     *  *
     * @author Malte
     */
    public static void main(String[] args) {

        Spil spil = SpilGenerator.genererSpil(2);
        spil.setRaflebaeger(new FalskRaflebaeger(2));

        Spiller spiller = spil.getSpiller(0);

        ArrayList<Chancekort> alleChancekort = new ArrayList<>();

        Chancekort chancekort = new RykkerSpillerCO();
        chancekort.setBeskrivelse("Chancekort 1");
        chancekort.setPengeVerdi(200);
        alleChancekort.add(chancekort);

        chancekort = new GratisUdAfFaengselCO();
        chancekort.setBeskrivelse("Chancekort 1");
        alleChancekort.add(chancekort);

        spil.setChanceKort(alleChancekort);

        SpilController spilController = new SpilController();

        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
