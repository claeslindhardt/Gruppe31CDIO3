package view;

import model.Spil;
import model.chancekort.Chancekort;
import model.felter.*;
import model.Spiller;
import model.raflebaeger.RafleBaeger;

import java.util.ArrayList;


public interface UserInterfaceKontrakt {


    void aabenSpil( Spil spil );
    String[] opretSpillere( int min, int max );
    void startSpil();



    int TurMenu( Spiller spiller, int minInput, int maxInput);
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
    void alleredeEjer();
    void monetosMangel();
    void iFaengselMedDig();

    void tetPaaMonopol();
    void gennemfoertKoeb(Ejendom ejendom, Spiller spiller);
    void gennemfoertKoebRederi (Rederi rederi, Spiller spiller);
    void gennemfoertKoebBryggeri (Bryggeri bryggeri, Spiller spiller);
    void betalRente();
    void duErLandetPå(Felt felt, Spiller spiller);
    void badErrorMessage();
    int ejendomsBud();
    void spillerEjendele(Spiller spiller);
    void brugtUdAfFaengsel();
    void ejerIngenEjendomme();
    void ejerIngenBebyggeligeEjendomme();
    int input_EjendomAtByggePaa(ArrayList<Ejendom> ejendomme);
    int input_EjendomAtSaelgeFra(ArrayList<Ejendom> ejendomme);
    String spillerNavne();
    void updateSpillere(Spiller spiller);
    void kanIkkeSlaaFaengsel();
    void startSpil(Spil spil);
    void byggeHotel(Ejendom ejendom);
    void byggetHus(Ejendom ejendom);
    void saelgHus(Ejendom ejendom);
    void saelgHotel(Ejendom ejendom);
    void tillykkeMedHotel();
    int input_EjendomAtByggeHotelPaa(ArrayList<Ejendom> ejendomme);
    void kanIkkeKøbeHotel();
    void spillerMaaIkkeEns();
    void ikkeTaxiTilTaxi();
    void friParkering();
    int vaelgIndkomstSkat();
    void statsSkat( int skat );
    void landetPaaStart();
    void visChanceKort( Chancekort chancekort );


}
