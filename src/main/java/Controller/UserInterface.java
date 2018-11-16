package Controller;

import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.controllerKlasser.*;

public interface UserInterface {
    void TurMenu(int getSpillerTur);
    void ikkeMuligt();
    void velkomstMenu();
    void opretteInstillinger(int getAntalSpillere,int getAntalFelter,int getAntalTerninger,int getSpillerTur,int getBankeraadGraense);
    void startSpilGrundFejl();
    void instilingsSporgsmaal0();
    void instilingsSporgsmaall();
    void instilingsSporgsmaal2();
    void instilingsSporgsmaal3();
    void spillerPosition(int spillerPosition);
    void bankeRaadtGrundetLiquditet(int getBankeraadGraense);
    void spillerUdgår(int udgaaetSpiller);
    void terminalLinje();
    void midtTerminalLinje();
    void vinder(int vinder);
    void anketStraffeDom(int spillerTur);
    void harSlaaetMedTerningfor();
    void ingenHeldIRetten();
    void heldIRetten();
    void retsTerninger(int domsAfsigelseDel1, int domsAfsigelseDel2);
    void spilletErSlut();
    void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus);
    void printTerninger(RafleBaeger terningsKrus);
    void ensTerninger();
    void menuGUI();
    void printFaenselInfo();
    void paaBesoegIFaengsel();
    void vilDuGiveOp();
    void takForSpillet();
    void duGavIkkeOp();
    void passeringAfStart(int gangeOverStart);
    void chanceKortHar();
    void chanceKortNr(int i, SpillerController spiller);
    void chanceKortsVejledning();
    void ingenChanceKort();
    void hvorHen(int pos);
    void holdDigPaaBrettet();
    void overStart(int pos);
    void spillerStat(SpillerController spiller);
    void alleredeEjer();
    void dinJernbane();
    void monetosMangel();
    void taxiInfo(Taxi vogn);
    void overStartAnimation();
    void startsFeltsInfo(Start felt);
    void iFaengselMedDig();
    void faengselInfo(GaaIFaengsel Faengsel);
    void muligeDestinationer();
    void jernBaneInfo(Jernbane stadtion);
    void stationsMuligheder();
    void turEfterJernbane();
    void manglerJernbaner();
    void jernBaneTilbud();
    void forsetTur();
    void ejetAfEnAnden();
    void tetPaaMonopol();
    void chanceFeltsInfo(ChanceFelt felt);
    void gennemfortKoeb();
    void ejendomsInfo(Ejendom ej);
    void betalRente();
    void duErLandetPå();
}