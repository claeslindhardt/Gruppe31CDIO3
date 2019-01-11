import BoundaryView.TUI.TUI;
import Controller.*;
import BoundaryView.UserInterfaceKontrakt;
import Controller.SpillerCO;
import ModelEnteties.BraetDTO;
import ModelEnteties.Spil;
import ModelEnteties.felter.EjendomCO;
import org.junit.jupiter.api.Test;
import spillogik.SpilGenerator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class SpilControllerTest {

    @Test
    void enTest(){

        SpillerCO spiller1 = new SpillerCO();
        SpillerCO spiller2 = new SpillerCO();

        spiller1.setNavn("Malte");
        spiller1.setPenge(10000);

        Spil spil = SpilGenerator.genererSpil(3, 39,40,1500);
        spil.getSpiller(0).setNavn("Malte");
        spil.getSpiller(0).setPenge(9999);

        SpilController spilController = new SpilController();
        spilController.setSpil(spil);
        spilController.koerSpil();
    }

}
