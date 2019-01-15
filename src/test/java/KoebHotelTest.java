import Controller.SpilController;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.Bryggeri;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.felter.FeltDTO;
import spillogik.SpilGenerator;

public class KoebHotelTest {


    public static void main(String[] args) {

        Spil spil = SpilGenerator.genererSpil(3, 39,40,1500);
        spil.setRaflebaeger( new FalskRaflebaeger(2) );

        Spiller spiller = spil.getSpiller(0);

        spiller.setNavn("Malte");
        spiller.setPenge(9999999);

        for( FeltDTO felt : spil.getBraet().getBret() ){
            if( felt instanceof EjendomCO){
                EjendomCO ejendom = (EjendomCO) felt;

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
