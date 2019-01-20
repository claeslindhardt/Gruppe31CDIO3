package view;

import model.Spil;
import model.chancekort.Chancekort;
import model.felter.*;
import model.Spiller;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.raflebaeger.RafleBaeger;

import java.util.ArrayList;


public interface UserInterface {

    void aabenSpil( Spil spil );
    String[] opretSpillere( int min, int max );
    void startSpil(Spil spil);
    int turMenu(Spiller spiller, int minInput, int maxInput);
    void spillerErBankerot( Spiller spiller);
    void terningerResultat( RafleBaeger rafleBaeger );
    void harSlaaetMedTerning();
    void spilletErSlut( Spiller vinder );
    void ensTerninger();
    int  vilDuGiveOp();
    void harGivetOp( Spiller spiller );
    int chanceKortNr(Spiller spiller);
    void ingenChanceKort();
    void manglerPenge();
    void ejerAlleredeFelt();
    void ankerDom();
    void anketDomResultat( boolean loesladt );
    void kanIkkeSlaaFaengsel();
    void brugtUdAfFaengsel();
    void passeringAfStart(int gangeOverStart);
    void duErLandetPaa(Felt felt, Spiller spiller);
    void friParkering();
    int vaelgIndkomstSkat();
    void betaltIndkomstSkat( Spiller spiller, int skat );
    void statsSkat( Spiller spiller, int skat );
    void landetPaaStart();
    void paaBesoegIFaengsel();
    void gaaIFaengsel();
    void betalerLeje(int leje, Spiller betaler, Spiller modtager);
    void visChanceKort(Spiller spiller, Chancekort chancekort);
    int koebsBeslutning();
    void gennemfoertKoeb(EjeligtFelt felt, Spiller spiller );
    int vaelgEjendom(ArrayList<Ejendom> ejendomme);
    void byggetPaaEjendom( Ejendom ejendom, Spiller spiller );
    void solgtPaaEjendom(Ejendom ejendom, Spiller spiller );
    void kanIkkeKoebeHotel();
    void kanIkkeKoebeHus();
    void kanIkkeSaelgeHus();
    void kanIkkeSaelgeHotel();
    int kauktion();
    void betaltKauktion(Spiller spiller);


}
