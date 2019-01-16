package BoundaryView;

import Controller.*;
import ModelEnteties.Spil;
import ModelEnteties.felter.*;
import ModelEnteties.Spiller;
import ModelEnteties.raflebaeger.RafleBaeger;
import ModelEnteties.ChanceAktionDTO;

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
    void bankeRaadtGrundetLikviditet(int getBankeraadGraense);
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
    void startsFeltsInfo(StartFelt felt);
    void iFaengselMedDig();
    void faengselInfo(GaaIFaengsel Faengsel);
    void muligeDestinationer();
    void jernBaneInfo(JernbaneCO stadtion);
    int stationsMuligheder(int min, int max);
    void turEfterJernbane();
    void manglerJernbaner();
    int jernBaneTilbud();
    void forsetTur();
    void ejetAfEnAnden();
    void tetPaaMonopol();
    void chanceFeltsInfo(ProevLykken felt);
    void gennemfoertKoeb(Ejendom ejendom, Spiller spiller);
    void ejendomsInfo(Ejendom ej);
    void betalRente();
    void duErLandetPå(Felt felt, Spiller spiller);
    void badErrorMessage();
    int ejendomsBud();
    void spillerEjendele(Spiller spiller);
    void printChanceKortDirekte(ChanceAktionDTO di);
    void chanceKortTilføjet();
    void chanceKortBrugt();
    void brugtUdAfFaengsel();
    void ejerIngenEjendomme();
    void ejerIngenBebyggeligeEjendomme();
    int input_EjendomAtByggePaa(ArrayList<Ejendom> ejendomme);
    String spillerNavne();
    void rejseBekraeftelse(String jernbane);
    void updateSpillere(Spiller spiller);
    void kanIkkeSlaaFaengsel();
    void startSpil(Spil spil);
    void byggeHotel(Ejendom ejendom);
    void byggetHus(Ejendom ejendom);
    void tillykkeMedHotel();
    int input_EjendomAtByggeHotelPaa(ArrayList<Ejendom> ejendomme);
    void kanIkkeKøbeHotel();
    void spillerMaaIkkeEns();
    void ikkeTaxiTilTaxi();
    void ditRederi(Rederi rederi, Spiller spiller);
    void ditBryggeri();
    void friParkering();
    String skatteBetaling();
    void skatteBesked(int valg);
    void trækEtChancekort();
    void landetPaaStart();


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
