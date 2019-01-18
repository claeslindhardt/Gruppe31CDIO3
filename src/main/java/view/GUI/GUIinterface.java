package view.GUI;

import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;
import model.raflebaeger.Terning;
import view.UserInterfaceKontrakt;
import model.Spil;
import model.Spiller;
import model.chancekort.Chancekort;
import model.raflebaeger.RafleBaeger;
import model.felter.Felt;
import gui_codebehind.GUI_Center;
import gui_fields.*;
import gui_main.GUI;
import java.util.ArrayList;

import static view.GUI.GUI_Generator.genererGUI;
import static view.GUI.GUI_Generator.genererSpillere;


/**
 * __________________________________________________________________________________________________________________________________________________________
 * PROGRAMDOKUMENTATION: GUIinterface.
 *
 *
 */
public class GUIinterface implements UserInterfaceKontrakt {

    private GUI_Extension gui;


    public void aabenSpil( Spil spil ){
        gui = new GUI_Extension( genererGUI(spil) );
        gui.visTekst( "Hjerteligt velkommen til Matador!" );
    }


    public void startSpil( Spil spil ) {
        gui.setSpillere( genererSpillere( spil.getSpillere() ) );

        for( Spiller spiller : spil.getSpillere() ){
            gui.opdaterSpillere( spiller );
        }
        for( Felt felt : spil.getFelter() ){
            if( felt instanceof EjeligtFelt ){
                gui.opdaterFelt( (EjeligtFelt) felt );
            }
        }

        gui.visTekst( "Lad os spille! - " + spil.getSpillerMedTur().getNavn() + " starter." );
    }


    // Indtast antal spillere og spillernavne
    public String[] opretSpillere( int min, int max ){

        int antalSpillere;
        do{
            antalSpillere = gui.indtastTal( "Indtast antallet spillere:", 2, 6 );
            if ( antalSpillere >= min && antalSpillere <= max ) {

                break;
            }
            gui.visTekst("I skal være mellem " + min + " og " + max + " spillere.");

        }while(true);

        String[] navne = new String[antalSpillere];

        for( int i = 0; i < antalSpillere; i++ ){

            boolean navnErTaget = false;
            String indtastetNavn;
            do{
                indtastetNavn = gui.indtastTekst( "Indtast navnet paa spiller " + (i+1) + ":");

                for(int j = 0; j < antalSpillere; j++){

                    if( navne[j] != null && navne[j].equalsIgnoreCase( indtastetNavn )) {
                        navnErTaget = true;
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



    public int TurMenu( Spiller spiller, int minInput, int maxInput){

        return gui.vaelgKnap("Det er "+ spiller.getNavn()+"'s tur.",
                "Kast terninger", "Slut din tur","Se chancekort","Giv op", "Byg hus", "Byg hotel","Sælg hus","Sælg hotel");
    }


    public void terningerResultat( RafleBaeger raflebaeger ){
        Terning[] terninger = raflebaeger.getTerninger();
        String resultat = "";

        for( int i = 0; i < terninger.length; i++ ){

            if( i == terninger.length - 1 ){
                resultat += ( "og " + terninger[i].getVaerdi() );

            }else if( i == terninger.length - 2){
                resultat += terninger[i].getVaerdi() + " ";

            }else
                resultat += terninger[i].getVaerdi() + ", ";
        }

        gui.visTekst("Du slog " + resultat + ".");
        //TODO: Opdater terninger
    }


    public void bankeRaadtGrundetLikviditet(int getBankeraadGraense){
        gui.visTekst("Woops du har mindre end "+getBankeraadGraense+" penge, " +
                "\nog du har derfor ikke høj nok likviditet til at forsætte Spillet."
        );
    }

    public void spillerUdgår(int udgaaetSpiller){
        gui.visTekst("Spiller "+udgaaetSpiller+" er nu udgået");
    }

    public void vinder(int vinder){
        gui.visTekst("Hurra vi har en vinder! Vinderen blev spiller "+(vinder));

    }

    public void anketStraffeDom(int spillerTur){
        gui.visTekst("Tillykke spiller "+spillerTur +", din straffedom er blevet anket, og du får nu et forsøg til at komme ud af faengslet." +
                "\nDu skal blot slå to ens med terningerne"
        );
    }
    public void harSlaaetMedTerningfor(){
        gui.visTekst("Du har allerede slaeet alle de terninger du maa i denne tur.");
    }
    public void ingenHeldIRetten(){
        gui.visTekst("Ingen held i retten i dag, forbliv i faengsel." +
                "\nDu kan stadig opkraeve leje"
        );
    }
    public void heldIRetten(){
        gui.visTekst("Du havde held i retten i dag, og må derfor slå med terningerne og rykke igen med det samme.");
    }
    public void retsTerninger(int domsAfsigelseDel1, int domsAfsigelseDel2){
        gui.visTekst("Du slog "+domsAfsigelseDel1+" og "+domsAfsigelseDel2);
    }




    public void spilletErSlut( Spiller vinder ){
        gui.visTekst("Hurra! " + vinder.getNavn() + " har vunder spillet!");
        gui.visTekst("Tak, fordi I spillede med!");
    }


    public void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus, int spillerTur){
        ArrayList<Integer> tern = terningsKrus.FaaTerningVærdier();

        String terning="";
        for(int i =0; i<tern.size();i++) {
            terning = terning.concat(tern.get(i)+ ", ");
        }
        gui.visTekst("Du slog: "+terning+
                "\nog rykker derfor " + terningsKrus.getTotalVaerdi() + " felter."
        );

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

    public void harGivetOp(){
        gui.visTekst("Tak for spillet!\nDine penge vil gå til skattefar.");
        // TODO: opdater spiller, bil og navn
    }


    public void passeringAfStart(int gangeOverStart){
        gui.visTekst("Tillykke du har passeret StartFelt "+gangeOverStart+" gang(e) og modtager "+200*gangeOverStart);
    }
    public void chanceKortHar(){
        gui.visTekst("Du har foelgende Chancekort:");
    }
    public int chanceKortNr(Spiller spiller){

        ArrayList<Chancekort> chancekort = spiller.getChancekort();

        int laengde = chancekort.size()+1;

        String[] alias = new String[laengde];


        for(int j = 0; j < chancekort.size();j++) {
            alias[j] = chancekort.get(j).getKortBeskrivelse();
        }

        alias[ chancekort.size()] = "Tilbage";

        return gui.vaelgListe("Liste af dine Chancekort: ",alias);
    }

    public void ingenChanceKort(){
        gui.visTekst("Du har ikke nogen chancekort.");
    }



    public void alleredeEjer(){
        gui.visTekst("Du er allerede ejer");
    }


    public void monetosMangel(){
        gui.visTekst("Du har ikke raad på nuvaerende tidspunkt. Vi vil dog stadig gerne bevare dig som kunde.");
    }

    public void iFaengselMedDig(){
        gui.visTekst("HOV HOV HOV, meget kan man boeje, men ikke loven! Bandit!"+
        "\nFordi du er landet på et felt, hvor man bliver kriminel "+
        "\nskal du nu en tur i kashotten.");
    }
    public void tetPaaMonopol(){
        gui.visTekst("Du er landet på et sted du ejer, naermer du dig et monopol?");
    }



    public void gennemfoertKoeb( EjeligtFelt felt, Spiller spiller ){

        gui.visTekst("Du har koebt " + felt.getNavn() + "!");
    }

    public void betalRente(){
        gui.visTekst("En anden spiller ejer dette felt, du betaler derfor rente til ham:");
    }

    public void duErLandetPå(Felt felt, Spiller spiller ){
        gui.visTekst( "Du er landet på " + felt.getNavn()+"." );
        gui.opdaterSpillere( spiller );
    }

    public void landetPaaStart(){
        gui.visTekst("Tag du dig bare en pause.");
    }


    public int ejendomsBud(){
        return gui.vaelgJaNej("Ingen ejer denne. Ønsker du at købe den?");
    }


    public void visChanceKort( Chancekort chancekort ){
        gui.visChancekort(chancekort);
    }


    public void brugtUdAfFaengsel(){
        gui.visTekst("Du har brugt dit 'Gratis ud af feangsel' Chancekort. Var du bag traemmer, er du nu fri," +
                "\nog hvis du ikke var, faar du så lov til at slå med terningerne igen.");
    }

    /**
     * @author Jacob og Andreas
     *
     * Denne metode sætter et hus på den ejendom som man har valgt at bygge den på.
     *
     * @param ejendom den Ejendom man vil bygge på
     */
    @Override
    public void byggetHus( Ejendom ejendom ) {
        gui.visTekst( "Du har bygget et hus paa " + ejendom.getNavn() );
        gui.opdaterFelt( ejendom );
    }

    public  void saelgHus(Ejendom ejendom){
        gui.opdaterFelt( ejendom );
    }

    public void saelgHotel( Ejendom ejendom ){
        gui.opdaterFelt( ejendom );
    }

    @Override
    public void ejerIngenEjendomme() {

    }

    @Override
    public void ejerIngenBebyggeligeEjendomme() {

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
    public int input_EjendomAtByggePaa(ArrayList<Ejendom> ejendomme) {

        String[] ejendomsListe = new String[ejendomme.size()];

        for (int i = 0; i < ejendomsListe.length; i++){
            ejendomsListe[i] = ejendomme.get(i).getNavn();
        }

        return gui.vaelgListe("Hvilken ejendom vil du bygge paa? ",ejendomsListe);
    }

    public int input_EjendomAtSaelgeFra(ArrayList<Ejendom> ejendomme) {
        String[] ejendomsListe = new String[ejendomme.size()+1];

        //Arraylist converteres til et array
        for(int i = 0; i < ejendomme.size();i++){
            ejendomsListe[i] = ejendomme.get(i).getNavn();
        }

        ejendomsListe[ejendomme.size()] = "Tilbage";
        return gui.vaelgListe("Hvilken ejendom vil du saelge på? ", ejendomsListe);
    }



    /**
     * @author Chua
     * Generere en liste af ejendomme som den nuværende spiller ejer, som man kan bygge hotel på.
     * @param ejendomme
     * @return
     */
    @Override
    public int input_EjendomAtByggeHotelPaa(ArrayList<Ejendom> ejendomme) {

        String[] ejendomsListe = new String[ejendomme.size()+1];

        for (int i = 0; i < ejendomme.size(); i++){
            ejendomsListe[i] = ejendomme.get(i).getNavn();
        }
        ejendomsListe[ejendomme.size()] = "Tilbage";
        return gui.vaelgListe("Hvilken ejendom vil du bygge hotel paa? ",ejendomsListe);

    }


    /**
     * @ Filip
     * Printer en besked i GUI'en, hvis man vælger at slå terninger fra turmenu, mens man er i fængsel.
     */
    public void kanIkkeSlaaFaengsel(){
        gui.visTekst("Du kan ikke slaa terningerne, da du stadig er i faengsel");
    }

    public void kanIkkeKøbeHotel(){
        gui.visTekst("Du har desvaerre ikke mulighed for at købe et hotel endnu");};


    public int vaelgIndkomstSkat(){
        return gui.binaertValg("Du skal betale skat!\nDu kan enten betale 200 kr. eller 10% af din samlede pengebeholdning \nHvad vælger du? ", "200 kr.", "10%" );
    }

    public void statsSkat( int skat ){
        gui.visTekst("Du skal betale ekstraordinær statsskat. Derfor bliver vi all " + skat + " kr. rigere!");
    }

    /**
     * @author Chua
     * Generere et hotel på den grund man ejer.
     * @param ejendom - Den grund man vil lave et hotel på
     */
    @Override
    public void byggetHotel( Ejendom ejendom ) {
        gui.opdaterFelt( ejendom );
    }


    public void friParkering(){
        gui.visTekst("Velkommen til fristedet, også kendt som parkerings pladsen.");
    }


}
