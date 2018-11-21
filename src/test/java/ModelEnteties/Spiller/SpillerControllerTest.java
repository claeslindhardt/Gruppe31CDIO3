package ModelEnteties.Spiller;

import BaundaryView.TUI.TUI;
import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.braet.controllerKlasser.Ejendom;
import ModelEnteties.braet.controllerKlasser.Jernbane;
import gui_main.GUI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpillerControllerTest {

    @Test
    void givOp() {
    }

    /**
     @author Chua
     Her vil jeg undersøge, om spilleren får penge på man lander på start igen.
     */
    @org.junit.Test
    void passeringAfStart(){
        UserInterface userInterface = new TUI();
        SpilController spil = new SpilController(1, 7, 2, 0,userInterface);

        spil.terningeKrus.setTotalVaerdi(7);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.terningeKrus);

        double forventetSpillerBeholdning = 1700;
        double aktuelSpillerBeholdning = spil.getSpillerMedTur().getPenge();
        assertEquals(aktuelSpillerBeholdning,forventetSpillerBeholdning);
    }

    /**
     * @author Chua
     * Her vil jeg teste om spilleren får penge når man har passeret start.
     */
    @org.junit.Test
    void passeringAfStart(){
        UserInterface userInterface = new TUI();
        SpilController spil = new SpilController(1, 7, 2, 0,userInterface);

        spil.terningeKrus.setTotalVaerdi(8);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.terningeKrus);

        double forventetSpillerBeholdning = 1700;
        double aktuelSpillerBeholdning = spil.getSpillerMedTur().getPenge();
        assertEquals(aktuelSpillerBeholdning,forventetSpillerBeholdning);
    }



    @Test
    void chanceKortMuligheder() {
    }

    @Test
    void tagTaxi() {
    }

    @Test
    void printSpillerStats() {
    }

    @Test
    void visEjendeFelter() {
    }

    /**
     * @author Jacob, Malte
     * Unit test, som tester om metoden koebEjendom virker.
     */
    @Test
    void koebEjendom() {

        Ejendom ejendom = new Ejendom("Hajgade",100,50,0);
        SpillerController spiller = new SpillerController("Jacob",30,0);
        UserInterfaceKontrakt userInterfaceKontrakt = new TUI();
        double pengebeholdning = spiller.getPenge();
      
        ArrayList<Ejendom> mineEjendomme = new ArrayList<Ejendom>();
        spiller.koebEjendom(ejendom, userInterfaceKontrakt);


        assertEquals(spiller,ejendom.getEjer());
        assertEquals(pengebeholdning-ejendom.getPris(),spiller.getPenge());
        assertTrue(spiller.getSpillerEjendomme().contains(ejendom));
    }

    /**
     * @author Jacob
     * Unit test, som tester om metoden koebJernbane
     */
    @Test
    void koebJernbane() {
        Jernbane jernbane = new Jernbane("Hovedbanegården",500,0);
        SpillerController spiller = new SpillerController("Jacob",10,0);
    
        UserInterfaceKontrakt userinterface = new TUI();
        GUI userInterface1 = new GUI();

        SpilController spil = new SpilController(2,10,2,0,userinterface);

        double pengebeholdning = spiller.getPenge();

        spiller.koebJernbane(jernbane,userinterface,spil);

        assertEquals(spiller,jernbane.getEjer());
        assertEquals(pengebeholdning-jernbane.getPris(),spiller.getPenge());
        assertTrue(spiller.getSpillerJernbaner().contains(jernbane));
    }




}