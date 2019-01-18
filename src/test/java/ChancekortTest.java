import model.chancekort.*;
import controller.SpilController;
import model.Spil;
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

        ArrayList<Chancekort> alleChancekort = new ArrayList<>();
        Chancekort chancekort;

        chancekort = new RykAntalFelter(-4, "Du skal rykke fire felter tilbage");
        alleChancekort.add(chancekort);

        chancekort = new RykTilBestemtFelt(10, "Du skal på besøg i fængslet");
        alleChancekort.add(chancekort);

        chancekort = new JustererPenge(-100, "Du skal betale 100kr");
        alleChancekort.add(chancekort);

        chancekort = new JustererPenge(+100, "Du modtager 100kr");
        alleChancekort.add(chancekort);

        spil.setChancekort(alleChancekort);

        SpilController spilController = new SpilController();

        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
