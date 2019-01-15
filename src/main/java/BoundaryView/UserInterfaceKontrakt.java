package BoundaryView;

import Controller.*;
import ModelEnteties.Spil;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.Spiller;
import ModelEnteties.raflebaeger.RafleBaeger;
import Controller.BraetCO;
import ModelEnteties.felter.FeltDTO;
import ModelEnteties.felter.ChanceAktionDTO;

import java.util.ArrayList;

/**
 * Controller der linker mellem UI og Spillogikken
 */
public interface UserInterfaceKontrakt {
    int TurMenu(int getSpillerTur, int minInput, int maxInput);
    void ikkeMuligt();
    int velkomstMenu(int minInput, int maxInput);
    void opretteInstillinger(int getAntalSpillere,int getAntalFelter,int getAntalTerninger,int getSpillerTur,int getBankeraadGraense);
    void startSpilGrundFejl();
    int instilingsSporgsmaal0(int minInput, int maxInput);
    int instilingsSporgsmaall(int minInput, int maxInput);
    int instilingsSporgsmaal2(int minInput, int maxInput);
    int instilingsSporgsmaal3(int minInput, int maxInput);
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
    void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus, int spillerTur);
    void printTerninger(RafleBaeger terningsKrus);
    void ensTerninger();
    void printFaenselInfo();
    void paaBesoegIFaengsel();
    int vilDuGiveOp();
    void takForSpillet();
    void duGavIkkeOp();
    void passeringAfStart(int gangeOverStart);
    void chanceKortHar();
    int chanceKortNr(Spiller spiller);
    int chanceKortsVejledning();
    void ingenChanceKort();
    int hvorHen(int pos, int min, int max);
    void holdDigPaaBrettet();
    void overStart(int pos);
    void spillerStat(Spiller spiller);
    void alleredeEjer();
    void dinJernbane();
    void monetosMangel();
    void taxiInfo(TaxiCO vogn);
    void overStartAnimation();
    void startsFeltsInfo(StartCO felt);
    void iFaengselMedDig();
    void faengselInfo(GaaIFaengselCO Faengsel);
    void muligeDestinationer();
    void jernBaneInfo(JernbaneCO stadtion);
    int stationsMuligheder(int min, int max);
    void turEfterJernbane();
    void manglerJernbaner();
    int jernBaneTilbud();
    void forsetTur();
    void ejetAfEnAnden();
    void tetPaaMonopol();
    void chanceFeltsInfo(ChanceFeltCO felt);
    void gennemfortKoeb(EjendomCO ejendom, Spiller spiller);
    void ejendomsInfo(EjendomCO ej);
    void betalRente();
    void duErLandetPå(FeltDTO felt, Spiller spiller);
    void badErrorMessage();
    int ejendomsBud();
    void spillerEjendele(Spiller spiller);
    void bretPrinter(String felt);
    void terminalLine();
    void chanceKortGenereringsFejl();
    void printChanceKortDirekte(ChanceAktionDTO di);
    void chanceKortTilføjet();
    void chanceKortBrugt();
    void brugtUdAfFaengsel();
    void byggetHus(EjendomCO ejendom);
    void ejerIngenEjendomme();
    void ejerIngenBebyggeligeEjendomme();
    int input_EjendomAtByggePaa(ArrayList<EjendomCO> ejendomme);
    void genererGUIBret(BraetCO braet, ArrayList<Spiller> spillere);
    String spillerNavne();
    void rejseBekraeftelse(String jernbane);
    void updateSpillere(Spiller spiller);
    void kanIkkeSlaaFaengsel();
    void startSpil(Spil spil);
    void kanIkkeKøbeHotel();
    void spillerMaaIkkeEns();
    void ikkeTaxiTilTaxi();
    void ditBryggeri();
}
