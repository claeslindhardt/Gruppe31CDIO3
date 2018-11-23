package ModelEnteties.Spiller;

import BoundaryView.TUI.TUI;
import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.braet.controllerKlasser.Ejendom;
import ModelEnteties.braet.controllerKlasser.EjendomsGruppe;
import ModelEnteties.braet.controllerKlasser.Jernbane;
import com.sun.tools.javac.comp.Todo;
import gui_main.GUI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

import static org.junit.jupiter.api.Assertions.*;

class SpillerControllerTest {

    @Test
    void givOp() {
    }

    /**
     * @author Andreas
     * Formål: At teste om vi kan passere start og fortsat blive på spillepladen
     * Metode: brættet har 4 felter, spillers, start position er 0 (start), terningværdi er 5.
     * Forventet: spillerplacering efter slag 1.
     * Statur: Testen er godkendt og bestået.
     */
    @Test
    void tjekForPasseringAfStartRykSpiller() {
        int spillerTur =1;

        UserInterfaceKontrakt userInterface = new TUI();
        SpilController spil = new SpilController(1,4,2,0,userInterface);


        spil.terningeKrus.setTotalVaerdi(5);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.terningeKrus);

        int forventetSpillerPosition=1;
        int aktuelSpillerPosition=spil.getSpillerMedTur().getSpillerPosition();
        assertEquals(aktuelSpillerPosition,forventetSpillerPosition);
    }

    /**
    @author Chua
     Her vil jeg undersøge, om spilleren får penge på man lander på start igen. Dette er en kode som skal finde en fejl.
     Testen bliver gennemført da den ikke går igennem går igennem :D
     */
    //TODO: ret fejlen
    @Test
    void landerPaaStart(){
        UserInterfaceKontrakt userInterface = new TUI();
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
     * Testen viser at koden er programmeret så man får penge hvis man rykker forbi start.
     * Positiv test
     */
    @Test
    void passeringAfStart(){
        UserInterfaceKontrakt userInterface = new TUI();
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

    /** @author Malte
     *  Tester for at integrationen mellem koebHus() og metoderne der tester, om man kan koebe
     *  et hus, virker korrekt.
     */
    @Test
    void koebHus() {

        // Setup

        SpillerController spiller = new SpillerController("Test", 0, 0);

        EjendomsGruppe ejendomsGruppe = new EjendomsGruppe();

        Ejendom ejendom1 = new Ejendom("Ejendom1", 0, 0, 0);
        ejendomsGruppe.tilfoejEjendom(ejendom1);

        Ejendom ejendom2 = new Ejendom("Ejendom2", 0, 0, 0);
        ejendomsGruppe.tilfoejEjendom(ejendom2);

        Ejendom ejendom3 = new Ejendom("Ejendom3", 0, 0, 0);
        ejendomsGruppe.tilfoejEjendom(ejendom3);

        double startPenge = spiller.getPenge();


        // Tester om man kan koebe hus uden at eje nogen af ejendommene.

        spiller.koebHus(ejendom1);
        assertEquals(0, ejendom1.getAntalHuse() );

        spiller.koebHus(ejendom2);
        assertEquals(0, ejendom2.getAntalHuse() );

        spiller.koebHus(ejendom3);
        assertEquals(0, ejendom3.getAntalHuse() );

        assertEquals(startPenge, spiller.getPenge());

        ejendom1.setEjer(spiller);


        // Tester om man kan koebe hus, når man ejer 1 ejendom

        spiller.koebHus(ejendom1);
        assertEquals(0, ejendom1.getAntalHuse() );

        spiller.koebHus(ejendom2);
        assertEquals(0, ejendom2.getAntalHuse() );

        spiller.koebHus(ejendom3);
        assertEquals(0, ejendom3.getAntalHuse() );
        assertEquals(startPenge, spiller.getPenge());


        // Tester om man kan koebe hus, når man ejer 2 ejendom

        ejendom2.setEjer(spiller);

        spiller.koebHus(ejendom1);
        assertEquals(0, ejendom1.getAntalHuse() );

        spiller.koebHus(ejendom2);
        assertEquals(0, ejendom2.getAntalHuse() );

        spiller.koebHus(ejendom3);
        assertEquals(0, ejendom3.getAntalHuse() );
        assertEquals(startPenge, spiller.getPenge());


        // Tester om man kan koebe hus, når man ejer alle ejendomme i gruppe
        ejendom3.setEjer(spiller);

        spiller.koebHus(ejendom1);
        assertEquals(1, ejendom1.getAntalHuse() );

        spiller.koebHus(ejendom2);
        assertEquals(1, ejendom2.getAntalHuse() );

        spiller.koebHus(ejendom3);
        assertEquals(1, ejendom3.getAntalHuse() );

        // Tester at pengene er blevet trukket korrekt fra spillerens beholdning.
        double pengeEfterKoeb = startPenge-ejendom1.getHusPris()-ejendom2.getHusPris()-ejendom3.getHusPris();
        assertEquals(pengeEfterKoeb, spiller.getPenge());

    }


}