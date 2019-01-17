import Controller.*;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.*;
import spillogik.RandomGenerator;


class SpilControllerTest {

    public static void main(String[] args) {
        Spil spil = RandomGenerator.genererSpil(3, 40,40,1500);
        spil.getSpiller(0).setNavn("Malte");
        spil.getSpiller(0).setPenge(9999999);
        spil.setRaflebaeger( new FalskRaflebaeger(2) );


        for( Felt felt : spil.getFelter() ){
            if( felt instanceof Ejendom){
                System.out.println("fundet   felt");
                ((Ejendom)  felt).setEjer(spil.getSpiller(0));
                spil.getSpiller(0).tilføjEjendom( (Ejendom) felt );
            }
        }

        Spiller spiller = spil.getSpiller(0);







        Rederi ØK = new Rederi("Ø.K.",20,7,7);
        spiller.addRederi(ØK);
        ØK.setEjer(spiller);

        Rederi DFDS = new Rederi("D.F.D.S.",20,8,8);
        spiller.addRederi(DFDS);
        DFDS.setEjer(spiller);


        spil.getFelter()[7] = ØK;
        spil.getFelter()[8] = DFDS;

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
