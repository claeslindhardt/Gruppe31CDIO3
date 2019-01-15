import Controller.*;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.Bryggeri;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.felter.FeltDTO;
import ModelEnteties.felter.Rederi;
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

        Spiller spiller = spil.getSpiller(0);

        Bryggeri Tuborg = new Bryggeri("Tuborg",3);
        spiller.addBryggeri(Tuborg);
        Tuborg.setEjer(spiller);

        Bryggeri Carlsberg = new Bryggeri("Carlsberg",4);
        spiller.addBryggeri(Carlsberg);
        Carlsberg.setEjer(spiller);

        Rederi Øresund = new Rederi("A/S Øresund",20,5);
        Øresund.setLeje(25);
        spiller.addRederi(Øresund);
        Øresund.setEjer(spiller);

        Rederi Bornholm = new Rederi("D/S Bornholm",20,6);
        Bornholm.setLeje(25);
        spiller.addRederi(Bornholm);
        Øresund.setEjer(spiller);

        Rederi ØK = new Rederi("Ø.K.",20,7);
        ØK.setLeje(25);
        spiller.addRederi(ØK);
        ØK.setEjer(spiller);

        Rederi DFDS = new Rederi("D.F.D.S.",20,8);
        DFDS.setLeje(25);
        spiller.addRederi(DFDS);
        DFDS.setEjer(spiller);

        spil.getBraet().getBret().set(3,(Tuborg));
        spil.getBraet().getBret().set(4,(Carlsberg));

        spil.getBraet().getBret().set(5,Øresund);
        spil.getBraet().getBret().set(6,Bornholm);
        spil.getBraet().getBret().set(7,ØK);
        spil.getBraet().getBret().set(8,DFDS);

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
