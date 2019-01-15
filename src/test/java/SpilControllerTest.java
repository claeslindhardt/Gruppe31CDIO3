import Controller.*;
import ModelEnteties.Spil;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.felter.FeltDTO;
import spillogik.RandomGenerator;
import spillogik.SpilGenerator;

import java.util.Random;


class SpilControllerTest {

    public static void main(String[] args) {
        Spil spil = RandomGenerator.genererSpil(3, 39,40,1500);
        spil.getSpiller(0).setNavn("Malte");
        spil.getSpiller(0).setPenge(9999999);
        spil.setRaflebaeger( new FalskRaflebaeger(2) );


        for( FeltDTO felt : spil.getFelter() ){
            if( felt instanceof EjendomCO ){
                System.out.println("fundet   felt");
                ((EjendomCO)  felt).setEjer(spil.getSpiller(0));
                spil.getSpiller(0).tilføjEjendom( (EjendomCO) felt );
            }
        }


        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
