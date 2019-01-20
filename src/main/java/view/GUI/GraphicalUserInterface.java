package view.GUI;

import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.raflebaeger.Terning;
import view.UserInterface;
import model.Spil;
import model.Spiller;
import model.chancekort.Chancekort;
import model.raflebaeger.RafleBaeger;
import model.felter.Felt;

import java.util.ArrayList;

import static view.GUI.GUI_Generator.genererGUI;
import static view.GUI.GUI_Generator.genererSpillere;



public class GraphicalUserInterface implements UserInterface {

    private GUI_Extension gui;


    /**
     * Aabne GUI'en. Den allerfoerste metode der bliver kaldt i gui'en.
     */
    public void aabenSpil( Spil spil ){
        gui = new GUI_Extension( genererGUI(spil) );
        gui.visTekst( "Hjerteligt velkommen til Matador!" );
    }


    /**
     * Starter spillet (efter oprettelsen af spillerne).
     */
    public void startSpil( Spil spil ) {

        // Generer de grafiske spillere
        gui.setSpillere( genererSpillere( spil.getSpillere() ) );

        // Opdaterer spillerne
        for( Spiller spiller : spil.getSpillere() ){
            gui.opdaterSpillere( spiller );
        }

        // Opdaterer alle felter (primaert ift. test, saa man kan starte med at eje ting)
        for( Felt felt : spil.getFelter() ){
            if( felt instanceof EjeligtFelt ){
                gui.opdaterFelt( (EjeligtFelt) felt );
            }
        }

        gui.visTekst( "Lad os spille! - " + spil.getSpillerMedTur().getNavn() + " starter." );
    }


    /**
     * Forespoerger antallet af spillere i spillet, samt et navn for
     * hver af disse spillere.
     * @param min Min antallet af spillere (inklusiv)
     * @param max Max antallet af spillere (inklusiv)
     * @return En liste over alle navnene paa de oprettede spillere.
     */
    public String[] opretSpillere( int min, int max ){

        // Indtast antallet af spilelre
        int antalSpillere;
        do{
            antalSpillere = gui.indtastTal( "Indtast antallet spillere:", 2, 6 );
            if ( antalSpillere >= min && antalSpillere <= max ) {

                break;
            }
            gui.visTekst("I skal være mellem " + min + " og " + max + " spillere.");

        }while(true);


        // Indtast navnene paa alle spillere. Her tjekkes for om navne er taget.
        String[] navne = new String[antalSpillere];

        // Loop der koerer for alle spisllere
        for( int i = 0; i < antalSpillere; i++ ){

            String indtastetNavn;
            boolean navnErTaget;

            // Loop der korer saa laenge man ikke har indtastet et navn, som ikke er taget.
            do{
                indtastetNavn = gui.indtastTekst( "Indtast navnet paa spiller " + (i+1) + ":");

                navnErTaget = false;
                for(int j = 0; j < antalSpillere; j++){

                    if( navne[j] != null && navne[j].equalsIgnoreCase( indtastetNavn )) {
                        navnErTaget = true;
                        gui.visTekst("Det navn er allerede taget");
                    }else{
                    }
                }

            }while( navnErTaget );
            navne[i] = indtastetNavn;
        }
        return navne;
    }


    public void anketDomResultat( boolean loesladt ){
        if( loesladt ){
            gui.visTekst( "Du havde held i retten i dag og slog ens terninger! Du må derfor slå med terningerne og rykke igen med det samme.");
        }else{
            gui.visTekst("Du havde desværre ikke held i retten i dag. Prøv igen i næste runde.");
        }
    }

    public void ankerDom(){
        gui.visTekst("Du anker din dom, og får et forsøg til at komme ud af faengslet." +
                "\nDu skal blot slå ens med terningerne." );
    }

public int kauktion(){
        int valg =gui.vaelgJaNej("VIl du betale kauktion på 500 for at komme ud af fængslet?");
        return valg;
}

public void betaltKauktion(Spiller spiller){gui.visTekst("Det var den rigtige beslutning. Liver er for kort til at sidde der og kuggelure");
    gui.opdaterSpillere(spiller);
    }


    public int turMenu(Spiller spiller, int minInput, int maxInput){

        return gui.vaelgKnap("Det er "+ spiller.getNavn()+"'s tur.",
                "Kast terninger", "Slut din tur","Se chancekort","Giv op", "Byg hus", "Byg hotel","Sælg hus","Sælg hotel");
    }


    /**
     * Viser resultat af terningerne paa skrift, samt
     * opdaterer de grafiske terninger.
     */
    public void terningerResultat( RafleBaeger raflebaeger ){
        Terning[] terninger = raflebaeger.getTerninger();
        String resultat = "";

        /* Teksten er gjort skalerbar ift. antallet af
            terninger (ift. kommaer og "og")
         */
        for( int i = 0; i < terninger.length; i++ ){

            if( i == terninger.length - 1 ){
                resultat += ( "og " + terninger[i].getVaerdi() );

            }else if( i == terninger.length - 2){
                resultat += terninger[i].getVaerdi() + " ";

            }else
                resultat += terninger[i].getVaerdi() + ", ";
        }

        gui.visTekst("Du slog " + resultat + ".");
        gui.opdaterTerninger(raflebaeger);
    }


    public void spillerErBankerot( Spiller spiller ){
        gui.visTekst("Woops du har er gået bankerot! - Tak for spillet " + spiller.getNavn() );
        gui.opdaterSpillere( spiller );
    }

    public void harSlaaetMedTerning(){
        gui.visTekst("Du har allerede slaeet alle de terninger du maa i denne tur.");
    }



    public void spilletErSlut( Spiller vinder ){
        gui.visTekst("Hurra! " + vinder.getNavn() + " har vunder spillet!");
        gui.visTekst("Tak, fordi I spillede med!");
    }


    public void ensTerninger(){
        gui.visTekst("Alle de terninger du slog, havde samme vaerdi, og derfor får du lov til at slå en ekstra gang!");
    }

    public void paaBesoegIFaengsel(){
        gui.visTekst("Du er nu på besoeg i faengslet.");
    }

    public int vilDuGiveOp(){
       return gui.vaelgJaNej("Er du sikker på, at du vil give op?");
    }

    public void harGivetOp( Spiller spiller ){
        gui.visTekst("Tak for spillet!\nDine penge vil gå til skattefar.");
        gui.opdaterSpillere( spiller );
    }


    public void passeringAfStart(int gangeOverStart){
        gui.visTekst("Tillykke du har passeret start " + gangeOverStart + " gang og modtager " + (200*gangeOverStart) );
    }


    /**
     * Viser en liste over alle chancekort en spiller har til spilleren.
     */
    public int chanceKortNr(Spiller spiller){

        ArrayList<Chancekort> chancekort = spiller.getChancekort();

        int laengde = chancekort.size()+1;

        String[] alias = new String[laengde];

        // Tilfoejer en 'tilbage' mulighed til listen.
        for(int j = 0; j < chancekort.size();j++) {
            alias[j] = chancekort.get(j).getKortBeskrivelse();
        }
        alias[ chancekort.size()] = "Tilbage";

        return gui.vaelgListe("Liste af dine Chancekort: ",alias);
    }

    public void ingenChanceKort(){
        gui.visTekst("Du har ikke nogen chancekort.");
    }

    public void manglerPenge(){
        gui.visTekst("Du har ikke raad lige nu.");
    }

    public void gaaIFaengsel(){
        gui.visTekst("HOV HOV HOV, meget kan man boeje, men ikke loven! Bandit!"+
        "\nFordi du er landet på et felt, hvor man bliver kriminel "+
        "\nskal du nu en tur i kashotten.");
    }


    public void ejerAlleredeFelt(){
        gui.visTekst("Du er landet på et sted du ejer, naermer du dig et monopol?");
    }

    public void gennemfoertKoeb( EjeligtFelt felt, Spiller spiller ){
        gui.visTekst("Du har koebt " + felt.getNavn() + "!");
        gui.opdaterSpillere( spiller );
        gui.opdaterFelt( felt );
    }

    public void betalerLeje(int leje, Spiller betaler, Spiller modtager){
        gui.visTekst( modtager.getNavn() + " ejer dette felt, og du betaler derfor " + leje + ".");
        gui.opdaterSpillere( betaler, modtager );
    }

    public void duErLandetPaa(Felt felt, Spiller spiller ){
        gui.opdaterSpillere( spiller );
        gui.visTekst( "Du er landet på " + felt.getNavn()+"." );
    }

    public int koebsBeslutning(){
        return gui.vaelgJaNej("Ingen ejer denne. Ønsker du at købe den?");
    }


    public void visChanceKort(Spiller spiller, Chancekort chancekort){
        gui.visChancekort(chancekort);
        gui.opdaterSpillere( spiller );
    }


    public void brugtUdAfFaengsel(){
        gui.visTekst("Du har brugt dit 'Gratis ud af feangsel' Chancekort. Var du bag traemmer, er du nu fri," +
                "\nog hvis du ikke var, faar du så lov til at slå med terningerne igen.");
    }


    public void byggetPaaEjendom( Ejendom ejendom, Spiller spiller ){
        gui.visTekst( "Du har bygget paa " + ejendom.getNavn() + "." );
        gui.opdaterSpillere(spiller);
        gui.opdaterFelt( ejendom );
    }

    public void solgtPaaEjendom(Ejendom ejendom, Spiller spiller ){
        gui.visTekst( "Du har solgt paa " + ejendom.getNavn() + "." );
        gui.opdaterSpillere(spiller);
        gui.opdaterFelt( ejendom );
    }


    /**
     * @author Jacob og Andreas
     *
     * Denne metode laver en liste i GUI, efter man har valgt at bygge på en ejendom, som viser en liste af
     * bebyggelige ejendomme.
     * Først omdannes arrayList ejendomme til String[] ejedomsliste.
     * Så fylder man den nye array ud med elementer fra arrayList med en for løkke.
     * Derefter vises en besked, hvorefter man vælger en bebyggelig grund fra listen og et hus bliver vist på
     * ejendommen i GUI
     *
     * @param ejendomme arrayList med alle ejendommene
     * @return den ejendom fra listen med bebyggelige ejendomme, som man har valgt at bygge på
     */
    @Override
    public int vaelgEjendom( ArrayList<Ejendom> ejendomme ) {

        String[] ejendomsListe = new String[ejendomme.size()];

        for (int i = 0; i < ejendomsListe.length; i++){
            ejendomsListe[i] = ejendomme.get(i).getNavn();
        }

        return gui.vaelgListe("Hvilken ejendom vil du bygge paa? ",ejendomsListe);
    }



    /**
     * @ Filip
     * Printer en besked i GUI'en, hvis man vælger at slå terninger fra turmenu, mens man er i fængsel.
     */
    public void kanIkkeSlaaFaengsel(){
        gui.visTekst("Du kan ikke slaa terningerne, da du stadig er i faengsel");
    }

    public void kanIkkeKoebeHotel(){
        gui.visTekst("Du har desvaerre ikke mulighed for at bygge et hotel endnu");
    }


    public void kanIkkeKoebeHus(){
        gui.visTekst("Du har desvarre ikke mulighed for at bygge et hus endnu.");
    }

    public void kanIkkeSaelgeHotel(){
        gui.visTekst("Du kan ikke saelge et hotel lige nu.");
    }

    public void kanIkkeSaelgeHus(){
        gui.visTekst("Du kan ikke saelge et hus lige nu.");
    }



    public int vaelgIndkomstSkat(){
        return gui.binaertValg("Du skal betale skat!\nDu kan enten betale 200 kr. eller 10% af din samlede pengebeholdning \nHvad vælger du? ", "200 kr.", "10%" );
    }

    public void betaltIndkomstSkat( Spiller spiller, int skat){
        gui.opdaterSpillere(spiller);
        gui.visTekst( "Du har betalt "+skat+" kr. i indkomstskat.");
    }

    public void statsSkat( Spiller spiller, int skat ){
        gui.visTekst("Du skal betale ekstraordinær statsskat. Derfor bliver vi all " + skat + " kr. rigere!");
        gui.opdaterSpillere( spiller );
    }

    public void friParkering(){
        gui.visTekst("Velkommen til fristedet, også kendt som parkerings pladsen.");
    }

    public void landetPaaStart(){
        gui.visTekst("Tag du dig bare en pause.");
    }


}
