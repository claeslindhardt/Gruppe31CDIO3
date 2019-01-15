package BoundaryView.GUI;

import BoundaryView.UserInterfaceKontrakt;
import Controller.*;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.Bryggeri;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.raflebaeger.RafleBaeger;
import ModelEnteties.felter.FeltDTO;
import ModelEnteties.felter.ChanceAktionDTO;
import gui_fields.*;
import gui_main.GUI;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * __________________________________________________________________________________________________________________________________________________________
 * PROGRAMDOKUMENTATION: GUIinterface.
 *
 *
 */
public class GUIinterface implements UserInterfaceKontrakt {

    //----------- Variabler: -------------------
    private final int[][] SPILLERFARVER = { {0,204,0},{255,51,51},{10,30,201}, {255,128,0}, {50,255,240}, {135,245,36}, {255,137,235}, {245,239,72}};


    private GUI gui;
    private GUI hovedmenu = new GUI(new GUI_Field[0]);
    IndputHaanteringGUI input = new IndputHaanteringGUI();
    private ArrayList<GUI_Player> spillere = new ArrayList<>();
    GUI_Field[] felter;

    //---------Getters og setters: -------------
    public ArrayList<GUI_Player> getSpillere() {
        return spillere;
    }

    public void setSpillere(ArrayList<GUI_Player> spillere) {
        this.spillere = spillere;
    }

    public void addGUISpillerObjekter(GUI_Player spiller) {
        this.spillere.add(spiller);
    }

    public GUI_Field[] getFelter(){return felter;}

    /**
     * Genererer det grafiske braet til spillet (GUI), med spillere, felter og biler.
     *
     */
    public void genererGUIBret( Spil spil ){
        FeltDTO[] felter = spil.getFelter();
        Spiller[] spillere = spil.getSpillere();
        int antalFelter =  felter.length;

        GUI_Field[] gui_felter = new GUI_Field[ antalFelter ];

        // Laver felternes grafiske elementer
        for( int i = 0;  i < antalFelter; i++){

            GUI_Field gui_felt;

            FeltDTO felt = felter[i];

            if(felt.getFeltType() == "Bryggeri"){
                gui_felt = new GUI_Brewery();
                gui_felt.setTitle( felt.getNavn() );
                gui_felt.setSubText( felt.getFeltType() );
            } else {
                gui_felt = new GUI_Street();
                gui_felt.setTitle(felt.getNavn());
                gui_felt.setSubText(felt.getFeltType());
            }
            gui_felter[i] = gui_felt;

            if( felt.getFeltType().equals("Ejendom") ){
                EjendomCO ejendom = (EjendomCO) felt;
                gui_felt.setBackGroundColor( ejendom.getGruppe().getFarve() );
                gui_felt.setDescription("Grundpris: "+((EjendomCO) felt).getPris() + " / "
                        + "Grundleje: " + ((EjendomCO) felt).getLeje() + " / "
                        + "Huspris: " + ((EjendomCO) felt).getHusPris() + " / "
                        + "Leje fra hus 1: " + ((EjendomCO) felt).getLejeHus(1) + " / "
                        + "Leje fra hus 2: " + ((EjendomCO) felt).getLejeHus(2) + " / "
                        + "Leje fra hus 3: " + ((EjendomCO) felt).getLejeHus(3) + " / "
                        + "Leje fra hus 4: " + ((EjendomCO) felt).getLejeHus(4) + " / "
                        + "Leje fra hotel: " + ((EjendomCO) felt).getLejeHotel());

            }else if(felt.getFeltType().equals("Bryggeri")) {
                gui_felt.setDescription("Grundpris: "+((Bryggeri) felt).getPris() +" "+ ((Bryggeri) felt).getPris() + " / "
                        + "Huspris: " + ((Bryggeri) felt).getLeje());

            }


            else{
                gui_felt.setBackGroundColor( Color.CYAN );
                if (felt.getFeltType().equals("JernbaneCO")){
                    gui_felt.setDescription("Tag Toget" + " / " + "Jernbanepris: " + 0 );
                }
                else if (felt.getFeltType().equals("TaxiCO")){
                    gui_felt.setDescription("Tag en taxi");
                }
                else if (felt.getFeltType().equals("Chance Kort")){
                    gui_felt.setDescription("Prøv lykken");
                }
                else if (felt.getFeltType().equals("Fængsel")){
                    gui_felt.setDescription("På besøg");
                }
                else if (felt.getFeltType().equals("Gå i fængsel")){
                    gui_felt.setDescription("Du har brudt loven, gå i fængsel!");
                }

            }
        }

        this.felter = gui_felter;
        gui = new GUI( gui_felter, new Color(218,206,179));

        // Laver spilleres grafiske elementer
        for(int i=0;i<spillere.length;i++){


            GUI_Car bil = new GUI_Car(); //Opret en bil

            // Finder spiller farve
            int[] farveVaerdier = SPILLERFARVER[i%SPILLERFARVER.length];
            Color spillerFarve = new Color(farveVaerdier[0], farveVaerdier[1], farveVaerdier[2]);
            bil.setPrimaryColor(spillerFarve); //Lad den være gul

            GUI_Player spiller = new GUI_Player(spillere[i].getNavn(),(int) spillere[i].getPenge(), bil); //opret en spiller

            this.spillere.add(spiller);
            gui.addPlayer(spiller); //Sæt spilleren på
            gui_felter[0].setCar(spiller, true);

        }
        //Få Spiller objekterne til at rykke sig på planden når objekterne rykker sig

    }


    /**
     * Indsæt beskrivelse her
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

    public void rykBil( GUI_Player spiller, int feltNr){
        fjernBil(spiller);
        felter[feltNr].setCar(spiller, true);
    }

    public String spillerNavne() {
        String spillernavn = hovedmenu.getUserString("Indtast et navn");
        return spillernavn;
    }

    public int velkomstMenu(int minInput, int maxInput){
        String valg = hovedmenu.getUserButtonPressed("|=========| MONOPOL SPILLET V1, MKIII",
                "starte nyt spil", "aendre spil instillinger","forsaette sidste spil");
        hovedmenu.showMessage(valg);
        //todo: fix this to return the right option
        return input.velkomstMenu(valg);
    }


    public int TurMenu(int getSpillerTur, int minInput, int maxInput){

        String valg = gui.getUserButtonPressed("|--|Det er spiller "+ getSpillere().get(getSpillerTur-1).getName()+"'s tur.",
                "Kast terninger", "Slut din tur","Se chancekort","Se hvad du ejer","Se spiller stats","Giv op", "Byg på ejendom","Handel med Ejede ting");
        gui.showMessage(valg);
        return input.TurMenu(valg);

    }
    public void ikkeMuligt(){
        gui.showMessage("Dette er ikke en mulighed endnu - prøv igen");
    }

    public void opretteInstillinger(int getAntalSpillere,int getAntalFelter,int getAntalTerninger,int getSpillerTur,int getBankeraadGraense){
        hovedmenu.showMessage("I er: " + getAntalSpillere + " spillere." +
                "\nBraettet har "+(getAntalFelter+1)+" Felter," +
                "\nmed "+getAntalTerninger+" terninger på braettet." +
                "\nSpiller "+getSpillerTur + " Starter!" +
                "\nMan går bankerot og taber dermed hvis man har mindre end: "+getBankeraadGraense+" penge. ");
    }
    public void startSpilGrundFejl(){
        gui.showMessage("Wooops ikke en mulighed endnu, spillet starter" +
                "\nmed standard instillinger");
    }

    /**
     * @author Jacob og Chua
     *
     * Denne metode skriver først en tekst i GUI om hvad der skal ske nu, og derefter kan man skrive et input om'
     * hvor stort et bræt man vil generere (min - 16 og max - 40, og kun et lige antal, som er dividerbart med 4).
     * metoden {@link GUI#getUserInteger} er omkranset af en try / catch for at forhindre at man kan indtaste forkerte
     * input. En if/else i while loopet sørger for at det indtastede bliver indenfor parametrene.
     *
     * @param minInput - Denne parameter bliver kun brugt i TUI
     * @param maxInput - Denne parameter bliver kun brugt i TUI
     * @return - Der bliver returneret en indstilling af hvor stort brættet skal være.
     */
    public int instilingsSporgsmaal0(int minInput, int maxInput){
       String input = hovedmenu.getUserButtonPressed("Hvor mange felter skal braettet have?: ",
                "16","20","24","28","32","36","40");
       return Integer.parseInt(input);
    }

    /**
     * @author Jacob og Chua
     *
     * Denne metode skriver først en tekst i GUI om hvad der skal ske nu, og derefter kan man skrive et input om'
     * hvor mange spillere man vil generere (min - 2 og max - 8).
     * metoden {@link GUI#getUserInteger} er omkranset af en try / catch for at forhindre at man kan indtaste forkerte
     * input. En if/else i while loopet sørger for at det indtastede bliver indenfor parametrene.
     *
     * @param minInput - Denne parameter bliver kun brugt i TUI
     * @param maxInput - Denne parameter bliver kun brugt i TUI
     * @return - Der bliver returneret en indstilling af hvor mange spillere der skal være i spillet.
     */
    public int instilingsSporgsmaall(int minInput, int maxInput){
        hovedmenu.showMessage("Hvor mange spillere vil i være?" +
                "\nNB Der kan være mellem " + minInput + " og " + maxInput + " spillere");
        while (true) {
            try {
                int valg = hovedmenu.getUserInteger("Indtast antal spillere i spillet");

                if (valg <= maxInput && valg >= minInput ) {
                    return valg;
                }
                hovedmenu.showMessage("Man kan vælge at være fra "+minInput+" til "+maxInput+" spillere, prøv igen!");
            } catch (Exception i) {
                hovedmenu.showMessage("Dette er ikke et gyldigt input, proev igen!");
            }
        }
    }

    /**
     * @author Jacob og Chua
     *
     * Denne metode returnerer bare 2 terninger, da det er det antal terninger som der ALTID skal være. Der bliver
     * skrevet en besked i GUI med den oplysning.
     *
     * @param minInput - Bliver KUN brugt i TUI
     * @param maxInput - Bliver KUN brugt i TUI
     * @return 2 terninger
     */
    public int instilingsSporgsmaal2(int minInput, int maxInput){
        hovedmenu.showMessage("Spillet starter med 2 terninger");
        return 2;
    }

    /**
     * @author Jacob og Chua
     *
     * Denne metode skriver først en tekst i GUI om hvad der skal ske nu, og derefter kan man skrive et input om'
     * hvor stor en bankerot graense der skal være (min - 0 og max - 1000).
     * metoden {@link GUI#getUserInteger} er omkranset af en try / catch for at forhindre at man kan indtaste forkerte
     * input. En if/else i while loopet sørger for at det indtastede bliver indenfor parametrene.
     *
     * @param minInput - Denne parameter bliver kun brugt i TUI
     * @param maxInput - Denne parameter bliver kun brugt i TUI
     * @return - Der bliver returneret en indstilling af hvor bankerot graensen skal ligge.
     */

    //TODO: Fjern denne på en ordentlig måde!!!!

    public int instilingsSporgsmaal3(int minInput, int maxInput){
        hovedmenu.showMessage("Hvor skal bankerot graensen ligge?: " +
                "\nNB Bankerot graensen skal ligge mellem 0 og 1000");
        while (true) {
            try {
                int valg = hovedmenu.getUserInteger("Indtast bankerotgraensen!");

                if (valg <= 1000 && valg >= 0) {
                    return valg;
                }
                hovedmenu.showMessage("Bankerotgraensen kan være fra 0 til 1000, vælg en ny bankerotgraense!");
            } catch (Exception i) {
                hovedmenu.showMessage("Dette er ikke et gyldigt input, proev igen!");
            }
        }
    }

    public void spillerPosition(int spillerPosition){
        gui.showMessage("Din position er nu felt nr: "+spillerPosition);

    }

    public void bankeRaadtGrundetLiquditet(int getBankeraadGraense){
        gui.showMessage("Woops du har mindre end "+getBankeraadGraense+" penge, " +
                "\nog du har derfor ikke høj nok liquditet til at forsætte Spillet."
        );
    }
    public void spillerUdgår(int udgaaetSpiller){
        gui.showMessage("Spiller "+udgaaetSpiller+" er nu udgået");
    }
    public void terminalLinje(){
        gui.showMessage("_________________________________________________________________");
    }
    public void midtTerminalLinje(){
        System.out.println("-----------------------------------------------");
    }
    public void vinder(int vinder){
        gui.showMessage("Hurra Vi har en vinder, vinder blev spiller "+(vinder));

    }

    public void anketStraffeDom(int spillerTur){
        gui.showMessage("Tillykke, Spiller "+spillerTur +" din straffedom er blevet anket og du får nu et forsøg til at komme ud af faengsel." +
                "\nDu skal blot slå to ens med terningerne"
        );
    }
    public void harSlaaetMedTerningfor(){
        gui.showMessage("Du har allerede slaeet alle de terninger du maa, den her tur.");
    }
    public void ingenHeldIRetten(){
        gui.showMessage("Ingen held i retten i dag, forbliv i faengsel." +
                "\nDu kan stadig opkraeve rente og handle med ejendomme"
        );
    }
    public void heldIRetten(){
        gui.showMessage("Du havde held i retten i dag, og må defor slå med terningerne og rykke igen med det samme.");
    }
    public void retsTerninger(int domsAfsigelseDel1, int domsAfsigelseDel2){
        gui.showMessage("Du slog "+domsAfsigelseDel1+" og "+domsAfsigelseDel2);
    }
    public void spilletErSlut(){
        System.out.println("Spillet er slut.");
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
        String ternin="";
        for(int i =0; i<tern.size();i++) {
            ternin = ternin.concat(tern.get(i)+ ", ");
        }
        gui.showMessage("Du slog: "+ternin+
                "\nog rykker derfor " + terningsKrus.getTotalVaerdi() + " felter."
        );

        //Todo:Ryk spilleren her xxxxxxxxxxxxxxxx

    }

    public void printTerninger(RafleBaeger terningsKrus){
        ArrayList<Integer> tern = terningsKrus.FaaTerningVærdier();
        for(int i =0; i<tern.size();i++) {
            gui.showMessage(tern.get(i)+ ", ");
        }
    }
    public void ensTerninger(){
        gui.showMessage("Alle de terninger du slog, havde samme vaerdi. Du får lov til at slå en ekstra gang.");
    }

    public void printFaenselInfo(){
        //System.out.println("| FeltDTO nr: " + getPlacering() +" | FeltDTO Navn:" + getNavn()+" | FeltDTO type:"+ getFeltType()+" |");
    }
    public void paaBesoegIFaengsel(){
        System.out.println("Du er nu på besoeg i faengslet.");
    }
    public int vilDuGiveOp(){
        String valg = gui.getUserSelection("|--|Er du sikker på at du vil give op?",
                "ja", "nej");
        gui.showMessage(valg);
       return input.binartValg(valg);
    }

    public void takForSpillet(){
        gui.showMessage("Tak for spillet:)\nDine penge vil gå til skattefar");
    }
    public void duGavIkkeOp(){
        gui.showMessage("Du valgte ikke at give op. ");
    }
    public void passeringAfStart(int gangeOverStart){
        gui.showMessage("Tillykke du har passeret StartCO "+gangeOverStart+" gange og modtager "+200*gangeOverStart);
    }
    public void chanceKortHar(){
        gui.showMessage("Du har foelgende Chance Kort:");
    }
    public int chanceKortNr(Spiller spiller){

        int laengde = spiller.getSpillerAktionsKort().size()+1;

        String[] alias = new String[laengde];


        for(int j = 0; j < spiller.getSpillerAktionsKort().size();j++) {
            alias[j] = spiller.getSpillerAktionsKort().get(j).getKortBeskrivelse();

        }
        alias[spiller.getSpillerAktionsKort().size()] = "Tilbage";
        String valg = gui.getUserSelection("Liste af dine Chance kort: ",alias);

        int valgKort = 0;
        for(int i = 0; i < alias.length;i++) {
            if (alias[i].equalsIgnoreCase(valg)){
                valgKort = i;

            }
        }
        return valgKort;
    }
    public int chanceKortsVejledning(){
        int valg = gui.getUserInteger("Intast nummeret på det chance kort du gerne vil aktiverer");
        return valg;
    }
    public void ingenChanceKort(){
        System.out.println("Du har ikke nogen Chance Kort lige nu.");
    }

    public void jernBaneInfo(JernbaneCO stadtion){
        String ejer;
        if(stadtion.getEjer() == null){
            ejer = "Ingen ejer endnu";
        }else{
            ejer = stadtion.getEjer().getNavn();
        }

        gui.showMessage("| Placering: "+stadtion.getPlacering()+" | Name: "+stadtion.getNavn()+" | Pris: "+stadtion.getPris() +" | Pantsat: "+stadtion.isPantsat()+"| ejer:"+ejer+"|");
    }
    public int hvorHen(int pos, int min, int max){
        gui.showMessage("Din nuvaerende position er: "+ pos+"Hvor vil de hen?: ");
        int valg = gui.getUserInteger("Intast nummeret på det felt du gerne vil hen til");
        return valg;
    }
    public void holdDigPaaBrettet(){
        gui.showMessage("Den gaar ikke, du skulle have valgt noget der ligger inden for braettets antal felter");
    }
    public void overStart(int pos){
        gui.showMessage("Din position er: "+ pos+
                "\nDu har i din rundfart med taxaen kommet til at passere StartCO, modtag 200");
    }
    public void spillerStat(Spiller spiller){
        gui.showMessage("Navn: "+spiller.getNavn()+" ID:"+spiller.getId()+" getPlacering(): "+spiller.getSpillerPosition()+" Penge: "+spiller.getPenge());

    }

    public void alleredeEjer(){
        gui.showMessage("Du er allerede ejer");
    }

    public void dinJernbane(){
        gui.showMessage("Jernbanen er nu din!");
    }

    public void ditBryggeri(){
        gui.showMessage("Bryggeriet er nu dit!");
    }

    public void monetosMangel(){
        gui.showMessage("Du har ikke raad på nuvaerende tidspunkt. Vi vil dog stadig gerne bevare dig som kunde");
    }
    public void taxiInfo(TaxiCO vogn){
        gui.showMessage("| FeltDTO nr: " + vogn.getPlacering() +" | FeltDTO Navn:" + vogn.getNavn()+" | FeltDTO type:"+ vogn.getFeltType()+" |");
    }
    public void overStartAnimation(){
        System.out.println("Aktion som foelger af StartCO");
    }
    public void startsFeltsInfo(StartCO felt){
        gui.showMessage("| FeltDTO nr: " + felt.getPlacering() +" | FeltDTO Navn:" + felt.getNavn()+" | FeltDTO type:"+ felt.getFeltType()+" |");
    }
    public void iFaengselMedDig(){
        gui.showMessage("HOV HOV HOV, meget kan man boeje men ikke loven!"+
        "\nFordi du er landet på et felt, hvor man bliver kriminel"+
        "\nSkal du en tur i kashotten.");
    }
    public void faengselInfo(GaaIFaengselCO Faengsel){
        gui.showMessage("| FeltDTO nr: " + Faengsel.getPlacering() +" | FeltDTO Navn:" + Faengsel.getNavn()+" | FeltDTO type:"+ Faengsel.getFeltType()+" |");

    }
    public void muligeDestinationer(){
        gui.showMessage("Du kan rejse til ");
    }
    public int stationsMuligheder(int min, int max){
        int valg = gui.getUserInteger("hvis du ikke onsker at rejse tast 0,\nellers intast den destination du ønsker at rejse til:");
        return valg;
    }
    public void turEfterJernbane(){
        gui.showMessage("Du kan nu forsaette din tur men får ikke muligheden for at tage jernbanen igen i denne tur," +
                "\n det tog er koert!");
    }
    public void manglerJernbaner(){
        gui.showMessage("Du ejer ikke nok jernabaner til at rejse:");

    }
    public int jernBaneTilbud(){
        String valg = gui.getUserSelection("|--|Det er en JernbaneCO vil du købe den?",
                "ja", "nej");
        gui.showMessage(valg);

        return input.binartValg(valg);
    }
    public void forsetTur(){
        gui.showMessage("Forsaet din tur");
    }
    public void ejetAfEnAnden(){
        gui.showMessage("En anden Spiller ejer dette felt, Du kan derfor ikke koebe det");
    }
    public void tetPaaMonopol(){
        gui.showMessage("Du er landet på et sted du ejer, naermer du dig et monopoly?");
    }
    public void chanceFeltsInfo(ChanceFeltCO felt){
        gui.showMessage("| FeltDTO nr: " + felt.getPlacering() +" | FeltDTO Navn:" + felt.getNavn()+" | FeltDTO type:"+ felt.getFeltType()+" |"
                +"\nKort på felt:");
        for(int i = 0; i<felt.getKortPaaFelt().size();i++){
            gui.showMessage(felt.getKortPaaFelt().get(i).getBeskrivelse()+"|-| ");
        }
        System.out.print(" |");
        System.out.println(" ");
    }

    public void updateSpillere(Spiller spiller){
        for(int i = 0; i < spillere.size();i++){
            double balance = spiller.getPenge();
            spillere.get(spiller.getId()).setBalance((int) balance);

        }
    }

    /** Gennemføre købet ift. GUI; dvs ændrer feltets border til spillerens farve.
     *
     * @param ejendom Ejendommens der købes
     * @param spiller Spilleren der køber ejendommen
     */
    public void gennemfortKoeb(EjendomCO ejendom, Spiller spiller){
        gui.showMessage("Du har koebt " + ejendom.getNavn() + "!");

        /*  Henter gui_feltet med udgangspunkt i den givne 'ejendom' placering (ejendom.getplacering)
        *   og omformer (caster) den til en GUI_Street objekt, så setBorder-metoden kan bruges.*/
        GUI_Street gui_ejendom = (GUI_Street) gui.getFields()[ejendom.getPlacering()];

        // Ændrer borderen på feltet til spillerens bils farve
        gui_ejendom.setBorder(spillere.get( spiller.getId()).getCar().getPrimaryColor() );

    }
    public void ejendomsInfo(EjendomCO ej){
        String ejer;
        if(ej.getEjer() == null){
            ejer = "Ingen ejer endnu";
        }else{
            ejer = ej.getEjer().getNavn();
        }


        gui.showMessage("| FeltDTO nr: " + ej.getPlacering() +" | FeltDTO Navn:" + ej.getNavn()+" | FeltDTO type:"+ ej.getFeltType()+" |"+
                "\n| Pris: "+ej.getPris()+ " | Rent: "+ej.getLeje()+" | Antal Huse: "+ej.getAntalHuse()+
                " | Huspris: "+ej.getHusPris()+" | Antal hoteller: "+ej.harHotel() +"|"+
                "\n| Pantsat: "+ej.isPantsat() +" | Group: "+ej.getGruppe().getFarve()+ "|"+" ejer: "+ejer+"|");
    }

    public void betalRente(){
        gui.showMessage("En anden Spiller ejer dette felt, du betaler derfor rente til ham:");
    }
    public void duErLandetPå(FeltDTO felt, Spiller spiller){
        String str; String str1 = "Du er landet på felt "; String str2 = "Du bliver overført til ";
        if(felt.getPlacering()==1){
            str = str2;
        }else str = str1;



        gui.showMessage(str+felt.getPlacering()+": "+felt.getNavn());
        GUI_Player guiSpiller = spillere.get(spiller.getId());
        rykBil(guiSpiller,felt.getPlacering());
    }
    public void badErrorMessage(){
        gui.showMessage("ERROR: WOOPS, TRIED TO COLLECTRENT WHEN PLAYER OBJECT WAS EMPTY!");
    }
    public int ejendomsBud(){
        String valg = gui.getUserSelection("|--|Det er en ejendom vil du købe den?",
                "ja", "nej");
        gui.showMessage(valg);

        return input.binartValg(valg);
    }
    public void spillerEjendele(Spiller spiller){
        gui.showMessage("Ejendeomme: ");
        for(int i = 0; i<spiller.getSpillerEjendomme().size();i++){
            spiller.getSpillerEjendomme().get(i).printInfo(this);
        }
        gui.showMessage("Jernbaner: ");
        for(int i = 0; i<spiller.getSpillerJernbaner().size();i++){
            spiller.getSpillerJernbaner().get(i).printInfo(this);

        }
    }
    public void bretPrinter(String felt){
        System.out.println("______________________________________________________________________________");
        System.out.println(felt);
    }
    public void terminalLine(){
        System.out.println("______________________________________________________________________________");
    }
    public void chanceKortGenereringsFejl(){
        gui.showMessage("Der var et problem med generering af ChanceKort, på et specifikt felt.");
    }
    public void printChanceKortDirekte(ChanceAktionDTO di){
        gui.showMessage(di.getBeskrivelse());

    }
    public void chanceKortTilføjet(){
        gui.showMessage("Dette kort vil blive tilfoejet til dine chance kort," +
                "\ndu kan nu bruge det når du oensker."
        );
    }
    public void chanceKortBrugt(){
        System.out.println("Du har nu brugt dit TaxiCO chance kort");
    }
    public void brugtUdAfFaengsel(){
        gui.showMessage("Du har brugt dit 'Gratis ud af feangsel' chance kort. Var du bag trammer er du nu fri," +
                "\nog hvis du ikke var, faar du alligvel lov til at slå med terningerne igen.");
    }

    /**
     * @author Jacob og Andreas
     *
     * Denne metode sætter et hus på den ejendom som man har valgt at bygge den på.
     *
     * @param ejendom den Ejendom man vil bygge på
     */
    @Override
    public void byggetHus(EjendomCO ejendom) {

        GUI_Street husSkalPaa = (GUI_Street) (getFelter()[ejendom.getPlacering()]);
        husSkalPaa.setHouses(ejendom.getAntalHuse());

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
    public int input_EjendomAtByggePaa(ArrayList<EjendomCO> ejendomme) {

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

    public void rejseBekraeftelse(String jernbane){
        gui.showMessage("Du er rejst til "+jernbane);
    }

    /**
     * @ Filip
     * Printer en besked i GUI'en, hvis man vælger at slå terninger fra turmenu, mens man er i fængsel.
     */
    public void kanIkkeSlaaFaengsel(){
        gui.showMessage("Du kan ikke slaa terningerne, da du stadig er i faengsel");
    }

    public void kanIkkeKøbeHotel(){gui.showMessage("Du har desværre ikke mulighed for at købe et hotel endnu");};

    public void spillerMaaIkkeEns(){ hovedmenu.showMessage("To spillere kan ikke hedde det samme. \n Indtast et nyt navn.");}

    public void ikkeTaxiTilTaxi(){ gui.showMessage("Du kan ikke tage en taxi til en taxi, det ville være snyd!"); }

    @Override
    public void startSpil(Spil spil) {

        genererGUIBret( spil );

        hovedmenu = null;

    }

}
