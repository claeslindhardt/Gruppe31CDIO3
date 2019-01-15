import BoundaryView.TUI.TUI;
import Controller.*;
import BoundaryView.UserInterfaceKontrakt;
import Controller.SpillerCO;
import ModelEnteties.BraetDTO;
import ModelEnteties.Spil;
import ModelEnteties.Terning.FalskRaflebaeger;
import ModelEnteties.felter.EjeligtFeltDTO;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.felter.FeltDTO;
import org.junit.jupiter.api.Test;
import spillogik.SpilGenerator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class SpilControllerTest {

    public static void main(String[] args) {
        Spil spil = SpilGenerator.genererSpil(3, 39,40,1500);
        spil.getSpiller(0).setNavn("Malte");
        spil.getSpiller(0).setPenge(9999999);
        spil.setRaflebaeger( new FalskRaflebaeger(2) );


        for( FeltDTO felt : spil.getBraet().getBret() ){
            if( felt instanceof EjendomCO ){
                System.out.println("fundet felt");
                ((EjendomCO)  felt).setEjer(spil.getSpiller(0));
                spil.getSpiller(0).tilføjEjendom( (EjendomCO) felt );
            }
        }


        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();

    }

}
