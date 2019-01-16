import Controller.RykkerSpillerCO;
import Controller.SpilController;
import ModelEnteties.ChanceAktionDTO;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import spillogik.SpilGenerator;

import java.util.ArrayList;

public class ChancekortTest {


    public static void main(String[] args) {

        Spil spil = SpilGenerator.genererSpil(2);
        spil.setRaflebaeger(new FalskRaflebaeger(2));

        Spiller spiller = spil.getSpiller(0);



        ChanceAktionDTO chancekort = new RykkerSpillerCO();
        chancekort.setBeskrivelse("Chancekort 1");
        chancekort.setPengeVerdi(200);

        spiller.addChancekort(chancekort);


        ArrayList<ChanceAktionDTO> alleChancekort = new ArrayList<>();

        spil.setChanceKort(alleChancekort);

        SpilController spilController = new SpilController();

        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
