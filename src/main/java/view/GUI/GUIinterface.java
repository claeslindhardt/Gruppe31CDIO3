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
import java.util.Random;

import static view.GUI.GUI_Generator.genererGUI;
import static view.GUI.GUI_Generator.genererSpillere;


/**
 * __________________________________________________________________________________________________________________________________________________________
 * PROGRAMDOKUMENTATION: GUIinterface.
 *
 *
 */
public class GUIinterface implements UserInterfaceKontrakt {

    GUI_Center gui_center = GUI_Center.getInstance();
    private GUI gui;
    IndputHaanteringGUI input = new IndputHaanteringGUI();
    private ArrayList<GUI_Player> spillere = new ArrayList<>();
    GUI_Field[] felter;


    public GUI_Field[] getFelter(){return felter;}



    //---------Metoder : -------------

    //===================================================
    //===================================================
    //          KODE NØDVENDIG FOR DRIFT:
    //===================================================
    //===================================================
    //Funktioner der kun bruges af GUI'en:
    //_______________________________________________________________

    /**
     *
     * @param spiller
     */
    public void fjernBil(GUI_Player spiller){

        for( GUI_Field felt : felter){

            if(felt.hasCar(spiller)){

                boolean[] harBil = new boolean[spillere.size()];

                for(int i=0; i < spillere.size(); i++){
                    harBil[i] = felt.hasCar(spillere.get(i));
                }

                felt.removeAllCars();

                for( int i=0; i<spillere.size(); i++){
                    if( harBil[i] && spillere.get(i) != spiller ){
                        felt.setCar(spillere.get(i), true);
                    }
                }
            }
        }
    }

    /**
     * Indsæt beskrivelse her
     * @param spiller
     */

    public void rykBil( GUI_Player spiller, int feltNr){
        fjernBil(spiller);
        felter[feltNr].setCar(spiller, true);
    }

    //Funktioner som Bruges af alle UserInterfaces:
    //_______________________________________________________________



    public void aabenSpil( Spil spil ){
        gui = genererGUI( spil );
        gui.showMessage("Hjerteligt velkommen til Matador!");
    }


    public void startSpil( Spil spil ) {

        gui.showMessage("Lad os spille! - " + spil.getSpillerMedTur().getNavn() + " starter.");

        GUI_Player[] gui_spillere = genererSpillere( spil.getSpillere() );

        for( GUI_Player gui_spiller : gui_spillere ){
            gui.addPlayer( gui_spiller );
            spillere.add( gui_spiller );
        }
    }


    public String[] opretSpillere( int min, int max ){

        int antalSpillere;
        do{
            antalSpillere = gui.getUserInteger( "Indtast antallet spillere:", 2, 6 );
            if ( antalSpillere >= min && antalSpillere <= max ) {

                break;
            }
            gui.showMessage("I skal være mellem " + min + " og " + max + " spillere.");

        }while(true);

        String[] navne = new String[antalSpillere];

        for( int i = 0; i < antalSpillere; i++ ){

            boolean navnErTaget = false;
            String indtastetNavn;
            do{
                indtastetNavn = gui.getUserString( "Indtast navnet paa spiller " + (i+1) + ":");

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
            gui.showMessage( "Du havde held i retten i dag og slog ens terninger! Du må derfor slå med terningerne og rykke igen med det samme.");
        }else{
            gui.showMessage("Du havde desværre ikke held i retten i dag. Prøv igen i næste runde.");
        }
    }

    public void ankerDom(){
        gui.showMessage("Du anker din dom, og får et forsøg til at komme ud af faengslet." +
                "\nDu skal blot slå ens med terningerne." );
    }



    public int TurMenu( Spiller spiller, int minInput, int maxInput){
        return input.valg(gui, "Det er "+ spiller.getNavn()+"'s tur.",
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

        gui.showMessage("Du slog " + resultat + ".");
    }


    public void bankeRaadtGrundetLikviditet(int getBankeraadGraense){
        gui.showMessage("Woops du har mindre end "+getBankeraadGraense+" penge, " +
                "\nog du har derfor ikke høj nok likviditet til at forsætte Spillet."
        );
    }

    public void spillerUdgår(int udgaaetSpiller){
        gui.showMessage("Spiller "+udgaaetSpiller+" er nu udgået");
    }

    public void vinder(int vinder){
        gui.showMessage("Hurra vi har en vinder! Vinderen blev spiller "+(vinder));

    }

    public void anketStraffeDom(int spillerTur){
        gui.showMessage("Tillykke spiller "+spillerTur +", din straffedom er blevet anket, og du får nu et forsøg til at komme ud af faengslet." +
                "\nDu skal blot slå to ens med terningerne"
        );
    }
    public void harSlaaetMedTerningfor(){
        gui.showMessage("Du har allerede slaeet alle de terninger du maa i denne tur.");
    }
    public void ingenHeldIRetten(){
        gui.showMessage("Ingen held i retten i dag, forbliv i faengsel." +
                "\nDu kan stadig opkraeve leje"
        );
    }
    public void heldIRetten(){
        gui.showMessage("Du havde held i retten i dag, og må derfor slå med terningerne og rykke igen med det samme.");
    }
    public void retsTerninger(int domsAfsigelseDel1, int domsAfsigelseDel2){
        gui.showMessage("Du slog "+domsAfsigelseDel1+" og "+domsAfsigelseDel2);
    }




    public void spilletErSlut( Spiller vinder ){
        gui.showMessage("Hurra! " + vinder.getNavn() + " har vunder spillet!");
        gui.showMessage("Tak, fordi I spillede med!");
    }


    public void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus, int spillerTur){
        ArrayList<Integer> tern = terningsKrus.FaaTerningVærdier();
        //lav dette til et forloop hvis vi finder en måde at display mere end to terninger på.
        int terning1=tern.get(0);
        int terning2=tern.get(1);
        Random rand = new Random();
        //Angiver, position af terningerne
        int x1 = rand.nextInt(8)+2;
        int y1 = rand.nextInt(8)+2;
        int x2 = rand.nextInt(8)+2;
        int y2 = rand.nextInt(8)+2;
        gui.setDice(terning1,x1,y1,terning2,x2,y2);
        String terning="";
        for(int i =0; i<tern.size();i++) {
            terning = terning.concat(tern.get(i)+ ", ");
        }
        gui.showMessage("Du slog: "+terning+
                "\nog rykker derfor " + terningsKrus.getTotalVaerdi() + " felter."
        );

    }


    public void ensTerninger(){
        gui.showMessage("Alle de terninger du slog, havde samme vaerdi, og derfor får du lov til at slå en ekstra gang!");
    }

    public void paaBesoegIFaengsel(){
        gui.showMessage("Du er nu på besoeg i faengslet.");
    }

    public int vilDuGiveOp(){
       return input.binaertValg("Er du sikker på, at du vil give op?", "Ja", "Nej",gui);
    }

    public void harGivetOp(){
        gui.showMessage("Tak for spillet!\nDine penge vil gå til skattefar.");
        // TODO: opdater spiller, bil og navn
    }


    public void passeringAfStart(int gangeOverStart){
        gui.showMessage("Tillykke du har passeret StartFelt "+gangeOverStart+" gang(e) og modtager "+200*gangeOverStart);
    }
    public void chanceKortHar(){
        gui.showMessage("Du har foelgende Chancekort:");
    }
    public int chanceKortNr(Spiller spiller){

        ArrayList<Chancekort> chancekort = spiller.getChancekort();

        int laengde = chancekort.size()+1;

        String[] alias = new String[laengde];


        for(int j = 0; j < chancekort.size();j++) {
            alias[j] = chancekort.get(j).getKortBeskrivelse();
        }

        alias[ chancekort.size()] = "Tilbage";
        String valg = gui.getUserSelection("Liste af dine Chancekort: ",alias);

        int valgKort = 0;
        for(int i = 0; i < alias.length;i++) {
            if (alias[i].equalsIgnoreCase(valg)){
                valgKort = i;

            }
        }
        return valgKort;
    }

    public void ingenChanceKort(){
        gui.showMessage("Du har ikke nogen chancekort.");
    }



    public void alleredeEjer(){
        gui.showMessage("Du er allerede ejer");
    }


    public void monetosMangel(){
        gui.showMessage("Du har ikke raad på nuvaerende tidspunkt. Vi vil dog stadig gerne bevare dig som kunde.");
    }

    public void iFaengselMedDig(){
        gui.showMessage("HOV HOV HOV, meget kan man boeje, men ikke loven! Bandit!"+
        "\nFordi du er landet på et felt, hvor man bliver kriminel "+
        "\nskal du nu en tur i kashotten.");
    }
    public void tetPaaMonopol(){
        gui.showMessage("Du er landet på et sted du ejer, naermer du dig et monopol?");
    }

    public void updateSpillere(Spiller spiller){

            double balance = spiller.getPenge();
            spillere.get(spiller.getId()).setBalance((int) balance);


    }

    /** Gennemføre købet ift. GUI; dvs ændrer feltets border til spillerens farve.
     *
     * @param ejendom Ejendommens der købes
     * @param spiller Spilleren der køber ejendommen
     */
    public void gennemfoertKoeb(Ejendom ejendom, Spiller spiller){
        gui.showMessage("Du har koebt " + ejendom.getNavn() + "!");

        /*  Henter gui_feltet med udgangspunkt i den givne 'ejendom' placering (ejendom.getplacering)
        *   og omformer (caster) den til en GUI_Street objekt, så setBorder-metoden kan bruges.*/
        GUI_Street gui_ejendom = (GUI_Street) gui.getFields()[ejendom.getPlacering()];

        // Ændrer borderen på feltet til spillerens bils farve
        gui_ejendom.setBorder(spillere.get( spiller.getId()).getCar().getPrimaryColor() );
    }

    public void gennemfoertKoeb( EjeligtFelt felt, Spiller spiller ){

        gui.showMessage("Du har koebt " + felt.getNavn() + "!");
    }

    /**
     * @author Filip
     * Samme som med gennemfoertKoeb
     * @param rederi Rederier der kan købes
     * @param spiller Spilleren der køber rederiet
     */
    public void gennemfoertKoebRederi (Rederi rederi, Spiller spiller){
        gui.showMessage("Du har koebt " + rederi.getNavn() + "!");

        GUI_Shipping gui_rederi = (GUI_Shipping) gui.getFields()[rederi.getPlacering()];
        gui_rederi.setBorder(spillere.get(spiller.getId()).getCar().getPrimaryColor());
    }

    /**
     * @author Filip
     * Samme som med gennemfoertKoeb
     * @param bryggeri Bryggerier der kan købes
     * @param spiller Spilleren der køber bryggeriet
     */
    public void gennemfoertKoebBryggeri (Bryggeri bryggeri, Spiller spiller){
        gui.showMessage("Du har koebt " + bryggeri.getNavn() + "!");

        GUI_Brewery gui_bryggeri = (GUI_Brewery) gui.getFields()[bryggeri.getPlacering()];

        gui_bryggeri.setBorder(spillere.get(spiller.getId()).getCar().getPrimaryColor());
    }

    public void betalRente(){
        gui.showMessage("En anden spiller ejer dette felt, du betaler derfor rente til ham:");
    }

    public void duErLandetPå(Felt felt, Spiller spiller ){
        GUI_Player guiSpiller = spillere.get( spiller.getId() );
        rykBil( guiSpiller, felt.getPlacering() );
        gui.showMessage( "Du er landet på " + felt.getNavn()+"." );
    }

    public void landetPaaStart(){
        gui.showMessage("Tag du dig bare en pause.");
    }


    public int ejendomsBud(){
        return input.binaertValg("Ingen ejer denne. Ønsker du at købe den?", "Ja", "Nej", gui);
    }


    public void visChanceKort( Chancekort chancekort ){
        gui_center.setChanceCard( chancekort.getBeskrivelse() );
        gui_center.displayChanceCard();
    }


    public void brugtUdAfFaengsel(){
        gui.showMessage("Du har brugt dit 'Gratis ud af feangsel' Chancekort. Var du bag traemmer, er du nu fri," +
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
    public void byggetHus(Ejendom ejendom) {

        GUI_Street husSkalPaa = (GUI_Street) (getFelter()[ejendom.getPlacering()]);
        husSkalPaa.setHouses(ejendom.getAntalHuse());

    }

    public  void saelgHus(Ejendom ejendom){
        GUI_Street husSkalPaa = (GUI_Street) (getFelter()[ejendom.getPlacering()]);
        husSkalPaa.setHouses(ejendom.getAntalHuse());
    }

    public void saelgHotel(Ejendom ejendom){
        GUI_Street husSkalPaa = (GUI_Street) (getFelter()[ejendom.getPlacering()]);
        husSkalPaa.setHotel(false);
        husSkalPaa.setHouses(ejendom.getAntalHuse());
    }

    @Override
    public void tillykkeMedHotel() {
        gui.showMessage("Tillykke! Du har koebt et hotel!");
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

        String valg = gui.getUserSelection("Hvilken ejendom vil du bygge paa? ",ejendomsListe);
        int indexRetur = 0;

        for (int i = 0; i < ejendomsListe.length; i++){
            if (valg == ejendomsListe[i]){
                indexRetur = i;
            }
        }
        return indexRetur;
    }

    public int input_EjendomAtSaelgeFra(ArrayList<Ejendom> ejendomme) {
        String[] ejendomsListe = new String[ejendomme.size()+1];

        //Arraylist converteres til et array
        for(int i = 0; i < ejendomme.size();i++){
            ejendomsListe[i] = ejendomme.get(i).getNavn();
        }

        ejendomsListe[ejendomme.size()] = "Tilbage";
        String valg = gui.getUserSelection("Hvilken ejendom vil du saelge på? ",ejendomsListe);
        int indexRetur = 0;

        for (int i = 0; i < ejendomsListe.length; i++){
            if (valg == ejendomsListe[i]){
                indexRetur = i;
            }
        }
        return indexRetur;
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
        String valg = gui.getUserSelection("Hvilken ejendom vil du bygge hotel paa? ",ejendomsListe);
        int indexRetur = 0;

        for (int i = 0; i < ejendomsListe.length; i++){
            if (valg == ejendomsListe[i]){
                indexRetur = i;
            }
        }
        return indexRetur;
    }


    /**
     * @ Filip
     * Printer en besked i GUI'en, hvis man vælger at slå terninger fra turmenu, mens man er i fængsel.
     */
    public void kanIkkeSlaaFaengsel(){
        gui.showMessage("Du kan ikke slaa terningerne, da du stadig er i faengsel");
    }

    public void kanIkkeKøbeHotel(){gui.showMessage("Du har desvaerre ikke mulighed for at købe et hotel endnu");};


    public int vaelgIndkomstSkat(){
        return input.binaertValg("Du skal betale skat!\nDu kan enten betale 200 kr. eller 10% af din samlede pengebeholdning \nHvad vælger du? ", "200 kr.", "10%", gui);
    }

    public void statsSkat( int skat ){
        gui.showMessage("Du skal betale ekstraordinær statsskat. Derfor bliver vi all " + skat + " kr. rigere!");
    }

    /**
     * @author Chua
     * Generere et hotel på den grund man ejer.
     * @param ejendom - Den grund man vil lave et hotel på
     */
    @Override
    public void byggeHotel(Ejendom ejendom) {
        GUI_Street hotelSkalPaa = (GUI_Street) (getFelter()[ejendom.getPlacering()]);
            if(getFelter()[ejendom.getPlacering()] instanceof GUI_Brewery){
                gui.showMessage("Ikke muligt at bygge på byggeriet");

            } else{ hotelSkalPaa.setHotel(ejendom.harHotel());}
    }

    public void friParkering(){
        gui.showMessage("Velkommen til fristedet, også kendt som parkerings pladsen.");
    }


}
