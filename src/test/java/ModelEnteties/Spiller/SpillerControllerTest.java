package ModelEnteties.Spiller;

import BaundaryView.TUI.TUI;
import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.controllerKlasser.*;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;
import org.junit.Test;


import java.util.ArrayList;

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

        UserInterface userInterface = new TUI();
        SpilController spil = new SpilController(1,4,2,0,userInterface);


        spil.terningeKrus.setTotalVaerdi(5);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.terningeKrus);

        int forventetSpillerPosition=1;
        int aktuelSpillerPosition=spil.getSpillerMedTur().getSpillerPosition();
        assertEquals(aktuelSpillerPosition,forventetSpillerPosition);
    }
    /**
    @author Chua
     Her vil jeg undersøge, om spilleren får penge på man lander på start igen.
    */
    @Test
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
    @Test
    void passeringAfStart(){
        UserInterface userInterface = new TUI();
        SpilController spil = new SpilController(1, 7, 2, 0,userInterface);

        spil.terningeKrus.setTotalVaerdi(8);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.terningeKrus);

        double forventetSpillerBeholdning = 1700;
        double aktuelSpillerBeholdning = spil.getSpillerMedTur().getPenge();
        assertEquals(aktuelSpillerBeholdning,forventetSpillerBeholdning);
    }

    public void TurMenu(int getSpillerTur) {

    }

    public void ikkeMuligt() {

    }

    public void velkomstMenu() {

    }

    public void opretteInstillinger(int getAntalSpillere, int getAntalFelter, int getAntalTerninger, int getSpillerTur, int getBankeraadGraense) {

    }

    public void startSpilGrundFejl() {

    }

    public void instilingsSporgsmaal0() {

    }

    public void instilingsSporgsmaall() {

    }

    public void instilingsSporgsmaal2() {

    }

    public void instilingsSporgsmaal3() {

    }

    public void spillerPosition(int spillerPosition) {

    }

    public void bankeRaadtGrundetLiquditet(int getBankeraadGraense) {

    }

    public void spillerUdgår(int udgaaetSpiller) {

    }

    public void terminalLinje() {

    }

    public void midtTerminalLinje() {

    }

    public void vinder(int vinder) {

    }

    public void anketStraffeDom(int spillerTur) {

    }

    public void harSlaaetMedTerningfor() {

    }

    public void ingenHeldIRetten() {

    }

    public void heldIRetten() {

    }

    public void retsTerninger(int domsAfsigelseDel1, int domsAfsigelseDel2) {

    }

    public void spilletErSlut() {

    }

    public void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus) {

    }

    public void printTerninger(RafleBaeger terningsKrus) {

    }

    public void ensTerninger() {

    }

    public void menuGUI() {

    }

    public void printFaenselInfo() {

    }

    public void paaBesoegIFaengsel() {

    }

    public void vilDuGiveOp() {

    }

    public void takForSpillet() {

    }

    public void duGavIkkeOp() {

    }

    public void passeringAfStart(int gangeOverStart) {


    }

    public void chanceKortHar() {

    }

    public void chanceKortNr(int i, SpillerController spiller) {

    }

    public void chanceKortsVejledning() {

    }

    public void ingenChanceKort() {

    }

    public void hvorHen(int pos) {

    }

    public void holdDigPaaBrettet() {

    }

    public void overStart(int pos) {

    }

    public void spillerStat(SpillerController spiller) {

    }

    public void alleredeEjer() {

    }

    public void dinJernbane() {

    }

    public void monetosMangel() {

    }

    public void taxiInfo(Taxi vogn) {

    }

    public void overStartAnimation() {

    }

    public void startsFeltsInfo(Start felt) {

    }

    public void iFaengselMedDig() {

    }

    public void faengselInfo(GaaIFaengsel Faengsel) {

    }

    public void muligeDestinationer() {

    }

    public void jernBaneInfo(Jernbane stadtion) {

    }

    public void stationsMuligheder() {

    }

    public void turEfterJernbane() {

    }

    public void manglerJernbaner() {

    }

    public void jernBaneTilbud() {

    }

    public void forsetTur() {

    }

    public void ejetAfEnAnden() {

    }

    public void tetPaaMonopol() {

    }

    public void chanceFeltsInfo(ChanceFelt felt) {

    }

    public void gennemfortKoeb() {

    }

    public void ejendomsInfo(Ejendom ej) {

    }

    public void betalRente() {

    }

    public void duErLandetPå() {

    }

    public void badErrorMessage() {

    }

    public void ejendomsBud() {

    }

    public void spillerEjendele(SpillerController spiller) {

    }

    public void bretPrinter(String felt) {

    }

    public void terminalLine() {

    }

    public void chanceKortGenereringsFejl() {

    }

    public void printChanceKortDirekte(ChanceAktion di) {

    }

    public void chanceKortTilføjet() {

    }

    public void chanceKortBrugt() {

    }

    public void brugtUdAfFaengsel() {

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
        UserInterface userInterface = new TUI();
        double pengebeholdning = spiller.getPenge();

        spiller.koebEjendom(ejendom, userInterface);

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
        UserInterface userinterface = new TUI();
        SpilController spil = new SpilController(2,10,2,0,userinterface);

        double pengebeholdning = spiller.getPenge();

        spiller.koebJernbane(jernbane,userinterface,spil);

        assertEquals(spiller,jernbane.getEjer());
        assertEquals(pengebeholdning-jernbane.getPris(),spiller.getPenge());
        assertTrue(spiller.getSpillerJernbaner().contains(jernbane));
    }




}