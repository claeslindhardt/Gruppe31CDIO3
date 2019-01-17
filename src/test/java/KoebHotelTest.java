import Controller.SpilController;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.Ejendom;
import ModelEnteties.felter.Felt;
import spillogik.RandomGenerator;
import spillogik.SpilGenerator;

public class KoebHotelTest {


    public static void main(String[] args) {

        Spil spil = SpilGenerator.genererSpil(3);
        spil.setRaflebaeger( new FalskRaflebaeger(2) );

        Spiller spiller = spil.getSpiller(0);

        spiller.setNavn("Malte");
        spiller.setPenge(9999999);

        for( Felt felt : spil.getFelter() ){
            if( felt instanceof Ejendom){
                Ejendom ejendom = (Ejendom) felt;

                ejendom.setEjer(spiller);
                spiller.tilføjEjendom(ejendom);
                ejendom.setAntalHuse(4);
            }
        }

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }


}
