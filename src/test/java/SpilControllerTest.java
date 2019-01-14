import BoundaryView.TUI.TUI;
import Controller.*;
import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.BraetDTO;
import ModelEnteties.Spil;
import ModelEnteties.Terning.FalskRaflebaeger;
import spillogik.SpilGenerator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class SpilControllerTest {

    public static void main(String[] args) {
        Spil spil = SpilGenerator.genererSpil(3, 39,40,1500);
        spil.getSpiller(0).setNavn("Malte");
        spil.getSpiller(0).setPenge(9999999);
        spil.setRaflebaeger( new FalskRaflebaeger(2) );


        /*for( FeltDTO felt : spil.getBraet().getBret() ){
            if( felt instanceof EjeligtFeltDTO ){
                System.out.println("fundet felt");
                ((EjeligtFeltDTO)  felt).setEjer(spil.getSpiller(0));
            }
        }*/

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
