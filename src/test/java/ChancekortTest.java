import ModelEnteties.chancekort.*;
import Controller.SpilController;
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
        Chancekort chancekort;


        chancekort = new GratisUdAfFaengsel("Gå ud af fængsel gratis");
        alleChancekort.add(chancekort);

        chancekort = new RykTilBestemtFelt(39, "Ryk til rådhuspladsen");
        alleChancekort.add(chancekort);

        chancekort = new JustererPenge(-100,"Trækker 100 kr. fra");
        alleChancekort.add(chancekort);

        chancekort = new JustererPenge(100,"Giver 100 kr.");
        alleChancekort.add(chancekort);

        chancekort = new SmidIFaengsel("Du skal gå i faengsel");
        alleChancekort.add(chancekort);




        spil.setChanceKort(alleChancekort);

        SpilController spilController = new SpilController();

        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
