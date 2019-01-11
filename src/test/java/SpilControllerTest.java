import BoundaryView.TUI.TUI;
import Controller.*;
import BoundaryView.UserInterfaceKontrakt;
import Controller.SpillerCO;
import ModelEnteties.Spil;
import ModelEnteties.SpillerDTO;
import ModelEnteties.felter.EjendomCO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class SpilControllerTest {

    @Test
    void enTest(){

        SpillerDTO spiller1 = new SpillerDTO();
        SpillerDTO spiller2 = new SpillerDTO();

        spiller1.setNavn("Malte");
        spiller1.setPenge(10000);

        Spil spil = new Spil();
        spil.setSpillere(spiller1, spiller2);


        SpilController spilController = new SpilController();
        spilController.start();

        spilController.koerSpil();



    }

}
