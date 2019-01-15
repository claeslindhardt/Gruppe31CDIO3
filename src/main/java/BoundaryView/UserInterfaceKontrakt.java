package BoundaryView;

import Controller.*;
import ModelEnteties.Spil;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.Spiller;
import ModelEnteties.felter.Rederi;
import ModelEnteties.raflebaeger.RafleBaeger;
import ModelEnteties.felter.FeltDTO;
import ModelEnteties.felter.ChanceAktionDTO;

import java.util.ArrayList;

/**
 * Controller der linker mellem UI og Spillogikken
 */
public interface UserInterfaceKontrakt {
    //===================================================
    //===================================================
    //          KODE NØDVENDIG FOR DRIFT:
    //===================================================
    //===================================================
    int TurMenu(int getSpillerTur, int minInput, int maxInput);
    void ikkeMuligt();
    int velkomstMenu(int minInput, int maxInput);
    int instilingsSporgsmaal0(int minInput, int maxInput);
    int instilingsSporgsmaall(int minInput, int maxInput);
    void bankeRaadtGrundetLiquditet(int getBankeraadGraense);
    void spillerUdgår(int udgaaetSpiller);
    void vinder(int vinder);
    void anketStraffeDom(int spillerTur);
    void harSlaaetMedTerningfor();
    void ingenHeldIRetten();
    void heldIRetten();
    void retsTerninger(int domsAfsigelseDel1, int domsAfsigelseDel2);
    void spilletErSlut();
    void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus, int spillerTur);
    void ensTerninger();
    void paaBesoegIFaengsel();
    int vilDuGiveOp();
    void takForSpillet();
    void duGavIkkeOp();
    void passeringAfStart(int gangeOverStart);
    void chanceKortHar();
    int chanceKortNr(Spiller spiller);
    void ingenChanceKort();
    int hvorHen(int pos, int min, int max);
    void holdDigPaaBrettet();
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
    void printChanceKortDirekte(ChanceAktionDTO di);
    void chanceKortTilføjet();
    void chanceKortBrugt();
    void brugtUdAfFaengsel();
    void ejerIngenEjendomme();
    void ejerIngenBebyggeligeEjendomme();
    int input_EjendomAtByggePaa(ArrayList<EjendomCO> ejendomme);
    String spillerNavne();
    void rejseBekraeftelse(String jernbane);
    void updateSpillere(Spiller spiller);
    void kanIkkeSlaaFaengsel();
    void startSpil(Spil spil);
    void byggeHotel(EjendomCO ejendom);
    void byggetHus(EjendomCO ejendom);
    void tillykkeMedHotel();
    int input_EjendomAtByggeHotelPaa(ArrayList<EjendomCO> ejendomme);
    void kanIkkeKøbeHotel();
    void spillerMaaIkkeEns();
    void ikkeTaxiTilTaxi();
    void ditRederi(Rederi rederi, Spiller spiller);
    void ditBryggeri();
    void friParkering();

    //===================================================
    //===================================================
    //      KODEN TIL SKALERING OG VELIGEHOLDELSE:
    //===================================================
    //===================================================
    /*
    void startSpilGrundFejl();
    int instilingsSporgsmaal2(int minInput, int maxInput);
    int instilingsSporgsmaal3(int minInput, int maxInput);
    void opretteInstillinger(int getAntalSpillere,int getAntalFelter,int getAntalTerninger,int getSpillerTur,int getBankeraadGraense);
    */
}
