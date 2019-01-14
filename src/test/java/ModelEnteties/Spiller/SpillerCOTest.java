/*
package ModelEnteties.Spiller;

import BoundaryView.TUI.TUI;
import Controller.SpilController;
import BoundaryView.UserInterfaceKontrakt;
import Controller.SpillerCO;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.EjendomsGruppeDTO;
import Controller.JernbaneCO;
import gui_main.GUI;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpillerCOTest {

    @Test
    void givOp() {
    }

    */
/**
     * @author Andreas
     * Formål: At teste om vi kan passere start og fortsat blive på spillepladen
     * Metode: brættet har 4 felter, spillers, start position er 0 (start), terningværdi er 5.
     * Forventet: spillerplacering efter slag 1.
     * Statur: Testen er godkendt og bestået.
     *//*

    @Test
    void tjekForPasseringAfStartRykSpiller() {
        int spillerTur =1;

        UserInterfaceKontrakt userInterface = new TUI();
        SpilController spil = new SpilController(1,4,2,0,userInterface);


        spil.getTerningeKrus().setTotalVaerdi(5);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.getTerningeKrus());

        int forventetSpillerPosition=1;
        int aktuelSpillerPosition=spil.getSpillerMedTur().getSpillerPosition();
        assertEquals(aktuelSpillerPosition,forventetSpillerPosition);
    }

    */
/**
    @author Chua
     Her vil jeg undersøge, om spilleren får penge på man lander på start igen. Dette er en kode som skal finde en fejl.
     Testen bliver gennemført da den ikke går igennem går igennem :D
     *//*

    @Test
    void landerPaaStart(){
        UserInterfaceKontrakt userInterface = new TUI();
        SpilController spil = new SpilController(1, 7, 2, 0,userInterface);

        spil.getTerningeKrus().setTotalVaerdi(7);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.getTerningeKrus());

        double forventetSpillerBeholdning = 1700;
        double aktuelSpillerBeholdning = spil.getSpillerMedTur().getPenge();
        assertEquals(aktuelSpillerBeholdning,forventetSpillerBeholdning);
    }

    */
/**
     * @author Jacob & Chua
     * Her tester vi, om der sker noget med ens pengebeholdning når spilleren lander på feltet lige inden start.
     * DET GØR DER IKKE.
     * Vi lavede testen på baggrund af testen "landerPaaStart", eftersom "landerPaaStart" testen giver 200 penge, når man har
     * slået nogle terninger med øjne der adderes med spillerens placering resulterer i at spilleren rykker lige akkurat forbi
     * start, men så subtraherede 1 fra antallet af felter man har, så får man 200 penge når man lander på start.
     * Her tester vi, om man fra næstsidste position til sidste position, optjener point, på baggrund af "landerPaaStart"
     *//*

    @Test
    void faarManPengeNaarManPassererSidsteFeltOgLanderPaaStart(){
        UserInterfaceKontrakt userInterface = new TUI();
        SpilController spil = new SpilController(1, 6, 1, 0,userInterface);

        spil.getSpillerMedTur().setSpillerPosition(4);
        spil.getTerningeKrus().setTotalVaerdi(1);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.getTerningeKrus());

        double forventetSpillerBeholdning = 1500;
        double aktuelSpillerBeholdning = spil.getSpillerMedTur().getPenge();
        assertEquals(aktuelSpillerBeholdning,forventetSpillerBeholdning);
    }


    */
/**
     * @author Chua
     * Her vil jeg teste om spilleren får penge når man har passeret start.
     * Testen viser at koden er programmeret så man får penge hvis man rykker forbi start.
     * Positiv test
     *//*

    @Test
    void passeringAfStart(){
        UserInterfaceKontrakt userInterface = new TUI();
        SpilController spil = new SpilController(1, 7, 2, 0,userInterface);

        spil.getTerningeKrus().setTotalVaerdi(8);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.getTerningeKrus());

        double forventetSpillerBeholdning = 1700;
        double aktuelSpillerBeholdning = spil.getSpillerMedTur().getPenge();
        assertEquals(aktuelSpillerBeholdning,forventetSpillerBeholdning);
    }

    @Test
    void chanceKortMuligheder() {
    }

    @Test
    void tagTaxi() {
        TUI Ui = new TUI();
        GUI gui = new GUI();

        //BraetCO spilleBraet = new BraetCO(10,Ui);
        SpilController spil = new SpilController(2,10,2,200,Ui);
        spil.getSpillerMedTur().tagTaxi(spil,Ui);



    }

    @Test
    void printSpillerStats() {
    }

    @Test
    void visEjendeFelter() {
    }

    */
/**
     * @author Jacob, Malte
     * Unit test, som tester om metoden koebEjendom virker. Den tester
     *//*

    @Test
    void koebEjendom() {

        EjendomCO ejendom = new EjendomCO("Hajgade",100,50,0);
        SpillerCO spiller = new SpillerCO("Jacob",30,0);
        UserInterfaceKontrakt userInterfaceKontrakt = new TUI();
        double pengebeholdning = spiller.getPenge();
        ArrayList<EjendomCO> mineEjendomme = new ArrayList<EjendomCO>();
        spiller.koebEjendom(ejendom, userInterfaceKontrakt);

        assertEquals(spiller,ejendom.getEjer());
        assertEquals(pengebeholdning-ejendom.getPris(),spiller.getPenge());
        assertTrue(spiller.getSpillerEjendomme().contains(ejendom));
    }

    */
/**
     * @author Jacob
     * Unit test, som tester om metoden koebJernbane
     *//*

    @Test
    void koebJernbane() {
        JernbaneCO jernbane = new JernbaneCO("Hovedbanegården",500,0);
        SpillerCO spiller = new SpillerCO("Jacob",10,0);

        UserInterfaceKontrakt userinterface = new TUI();
        GUI userInterface1 = new GUI();
        SpilController spil = new SpilController(2,10,2,0,userinterface);
        double pengebeholdning = spiller.getPenge();

        spiller.koebJernbane(jernbane,userinterface,spil);

        assertEquals(spiller,jernbane.getEjer());
        assertEquals(pengebeholdning-jernbane.getPris(),spiller.getPenge());
        assertTrue(spiller.getSpillerJernbaner().contains(jernbane));
    }

    */
/** @author Malte
     *  Tester for at integrationen mellem koebHus() og metoderne der tester, om man kan koebe
     *  et hus, virker korrekt.
     *//*

    @Test
    void koebHus() {

        // Setup
        UserInterfaceKontrakt userInterface = new TUI();

        SpillerCO spiller = new SpillerCO("Test", 0, 0);
        EjendomsGruppeDTO ejendomsGruppe = new EjendomsGruppeDTO(Color.BLUE, 3);


        EjendomCO ejendom1 = new EjendomCO("Ejendom1", 0, 0, 0);
        ejendomsGruppe.tilfoejEjendom(ejendom1);
        ejendom1.setGruppe(ejendomsGruppe);

        EjendomCO ejendom2 = new EjendomCO("Ejendom2", 0, 0, 0);
        ejendomsGruppe.tilfoejEjendom(ejendom2);
        ejendom2.setGruppe(ejendomsGruppe);

        EjendomCO ejendom3 = new EjendomCO("Ejendom3", 0, 0, 0);
        ejendomsGruppe.tilfoejEjendom(ejendom3);
        ejendom3.setGruppe(ejendomsGruppe);

        double startPenge = spiller.getPenge();


        // Tester om man kan koebe hus uden at eje nogen af ejendommene.

        spiller.koebHus(ejendom1,userInterface);
        assertEquals(0, ejendom1.getAntalHuse() );

        spiller.koebHus(ejendom2,userInterface);
        assertEquals(0, ejendom2.getAntalHuse() );

        spiller.koebHus(ejendom3,userInterface);
        assertEquals(0, ejendom3.getAntalHuse() );

        assertEquals(startPenge, spiller.getPenge());

        ejendom1.setEjer(spiller);


        // Tester om man kan koebe hus, når man ejer 1 ejendom

        spiller.koebHus(ejendom1,userInterface);
        assertEquals(0, ejendom1.getAntalHuse() );

        spiller.koebHus(ejendom2,userInterface);
        assertEquals(0, ejendom2.getAntalHuse() );

        spiller.koebHus(ejendom3, userInterface);
        assertEquals(0, ejendom3.getAntalHuse() );
        assertEquals(startPenge, spiller.getPenge());


        // Tester om man kan koebe hus, når man ejer 2 ejendom

        ejendom2.setEjer(spiller);

        spiller.koebHus(ejendom1, userInterface);
        assertEquals(0, ejendom1.getAntalHuse() );

        spiller.koebHus(ejendom2, userInterface);
        assertEquals(0, ejendom2.getAntalHuse() );

        spiller.koebHus(ejendom3, userInterface);
        assertEquals(0, ejendom3.getAntalHuse() );
        assertEquals(startPenge, spiller.getPenge());


        // Tester om man kan koebe hus, når man ejer alle ejendomme i gruppe
        ejendom3.setEjer(spiller);

        spiller.koebHus(ejendom1, userInterface);
        assertEquals(1, ejendom1.getAntalHuse() );

        spiller.koebHus(ejendom2, userInterface);
        assertEquals(1, ejendom2.getAntalHuse() );

        spiller.koebHus(ejendom3, userInterface);
        assertEquals(1, ejendom3.getAntalHuse() );

        // Tester at pengene er blevet trukket korrekt fra spillerens beholdning.
        double pengeEfterKoeb = startPenge-ejendom1.getHusPris()-ejendom2.getHusPris()-ejendom3.getHusPris();
        assertEquals(pengeEfterKoeb, spiller.getPenge());

    }




}*/
