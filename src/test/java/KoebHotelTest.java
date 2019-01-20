import controller.SpilController;
import model.Spil;
import model.Spiller;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.Felt;
import spillogik.spilgenerering.SpilGenerator;

public class KoebHotelTest {


    public static void main(String[] args) {

        Spil spil = SpilGenerator.genererSpil(3);
        spil.setRaflebaeger( new FalskRaflebaeger(2) );

        Spiller spiller = spil.getSpiller(0);

        spiller.    setNavn("Malte");
        spiller.setPenge(9999999);

        for( Felt felt : spil.getFelter() ){
            if( felt instanceof EjeligtFelt){

                EjeligtFelt ejeligtFelt = (EjeligtFelt) felt;

                ejeligtFelt.setEjer(spiller);
                spiller.addEjeligtFelt(ejeligtFelt);

                if( felt instanceof Ejendom ){
                    Ejendom ejendom = (Ejendom) felt;
                    ejendom.setAntalHuse(4);
                }
            }
        }

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }
}
