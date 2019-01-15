import Controller.*;
import ModelEnteties.Spil;
import ModelEnteties.felter.Bryggeri;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.felter.FeltDTO;
import spillogik.SpilGenerator;


class SpilControllerTest {

    public static void main(String[] args) {
        Spil spil = SpilGenerator.genererSpil(3, 39,40,1500);
        spil.getSpiller(0).setNavn("Malte");
        spil.getSpiller(0).setPenge(9999999);
        spil.setRaflebaeger( new FalskRaflebaeger(2) );


        for( FeltDTO felt : spil.getBraet().getBret() ){
            if( felt instanceof EjendomCO ){
                System.out.println("fundet   felt");
                ((EjendomCO)  felt).setEjer(spil.getSpiller(0));
                spil.getSpiller(0).tilføjEjendom( (EjendomCO) felt );
            }
        }

        Bryggeri Tuborg = new Bryggeri("Tuborg",3);
        Bryggeri Carlsberg = new Bryggeri("Carlsberg",4);

        spil.getBraet().getBret().set(3,(Tuborg));
        spil.getBraet().getBret().set(4,(Carlsberg));

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
