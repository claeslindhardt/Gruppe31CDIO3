import controller.SpilController;
import model.Spil;
import spillogik.SpilGenerator;

public class SpilMedFalskRaflebaeger {

    public static void main(String[] args) {

        Spil spil = SpilGenerator.genererSpil(2);
        spil.setRaflebaeger( new FalskRaflebaeger(2) );

        SpilController spilController  = new SpilController();
        spilController.setSpil( spil );
        spilController.koerSpil();
    }


}