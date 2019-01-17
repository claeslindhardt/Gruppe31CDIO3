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
    int TurMenu( Spiller spiller, int minInput, int maxInput);
    void bankeRaadtGrundetLikviditet(int getBankeraadGraense);

    void ankerDom();
    void anketDomResultat( boolean loesladt );
    void terningerResultat( RafleBaeger rafleBaeger );

    void harSlaaetMedTerningfor();
    void spilletErSlut( Spiller vinder );
    void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus, int spillerTur);
    void ensTerninger();
    void paaBesoegIFaengsel();
    int vilDuGiveOp();
    void harGivetOp();
    void passeringAfStart(int gangeOverStart);
    void chanceKortHar();
    int chanceKortNr(Spiller spiller);
    void ingenChanceKort();
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
    void brugtUdAfFaengsel();
    void ejerIngenEjendomme();
    void ejerIngenBebyggeligeEjendomme();
    int input_EjendomAtByggePaa(ArrayList<Ejendom> ejendomme);
    int input_EjendomAtSaelgeFra(ArrayList<Ejendom> ejendomme);
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
    void friParkering();
    int vaelgIndkomstSkat();
    void statsSkat( int skat );
    void landetPaaStart();
    void visChanceKort( Chancekort chancekort );


}
