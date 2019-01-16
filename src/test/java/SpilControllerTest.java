import Controller.*;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.*;
import spillogik.RandomGenerator;
import spillogik.SpilGenerator;

import java.util.Random;


class SpilControllerTest {

    public static void main(String[] args) {
        Spil spil = RandomGenerator.genererSpil(3, 40,40,1500);
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

        Spiller spiller = spil.getSpiller(0);







        Rederi ØK = new Rederi("Ø.K.",20,7);
        ØK.setLeje(25);
        spiller.addRederi(ØK);
        ØK.setEjer(spiller);

        Rederi DFDS = new Rederi("D.F.D.S.",20,8);
        DFDS.setLeje(25);
        spiller.addRederi(DFDS);
        DFDS.setEjer(spiller);


        spil.getFelter()[7] = ØK;
        spil.getFelter()[8] = DFDS;

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
