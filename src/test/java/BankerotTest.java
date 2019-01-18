import controller.SpilController;
import model.Spil;
import model.felter.ejeligefelter.Ejendom;

import static spillogik.SpilGenerator.genererSpil;

public class BankerotTest {

    public static void main(String[] args) {

        Spil spil = genererSpil(3);

        spil.getSpiller(0).setPenge(1);
        spil.setSpillerTur(1);

        ((Ejendom) spil.getFelter()[1]).setEjer( spil.getSpiller(1) );

        spil.setRaflebaeger( new FalskRaflebaeger(2) );

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }


}
