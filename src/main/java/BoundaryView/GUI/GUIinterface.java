package BoundaryView.GUI;

import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.controllerKlasser.*;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;
import gui_fields.GUI_Field;
import gui_main.GUI;

import java.util.ArrayList;

/**
 * __________________________________________________________________________________________________________________________________________________________
 * PROGRAMDOKUMENTATION: GUIinterface.
 *
 *
 */
public class GUIinterface implements UserInterfaceKontrakt {
    //TODO: forsimpel alle de steder der er gentagelser i teksten her.
    GUI gui = new GUI(new GUI_Field[0]);
    IndputHaanteringGUI input = new IndputHaanteringGUI();

    //Hvordan får jeg den så til at retunere noget til back endet?
    public int velkomstMenu(){
        String valg = gui.getUserSelection("|=========| MONOPOL SPILLET V1, MKIII",
                "starte nyt spil", "aendre spil instillinger","forsaette sidste spil");
        gui.showMessage(valg);
        //todo: fix this to return the right option
        return input.velkomstMenu(valg);
    }

    public int TurMenu(int getSpillerTur){
        String valg = gui.getUserSelection("|--|Det er spiller "+getSpillerTur+"'s tur.",
                "Kast terninger", "Slut din tur","Se chancekort","Se hvad du ejer","Se spiller stats","Giv op", "Byg på ejendom","Handel med Ejede ting");
        gui.showMessage(valg);
        return input.TurMenu(valg);

    }
    public void ikkeMuligt(){
        gui.showMessage("Dette er ikke en mulighed endnu - prøv igen");
    }

    public void opretteInstillinger(int getAntalSpillere,int getAntalFelter,int getAntalTerninger,int getSpillerTur,int getBankeraadGraense){
        gui.showMessage("I er: " + getAntalSpillere + " spillere." +
                "\nBraettet har "+getAntalFelter+" Felter," +
                "\nmed "+getAntalTerninger+" terninger på braettet." +
                "\nSpiller "+getSpillerTur + " Starter!" +
                "\nMan går bankerot og taber dermed hvis man har mindre end: "+getBankeraadGraense+" penge. ");
    }
    public void startSpilGrundFejl(){
        gui.showMessage("Wooops ikke en mulighed endnu, spillet starter" +
                "\nmed standard instillinger");
    }
    public void instilingsSporgsmaal0(){
        gui.showMessage("Hvor mange felter skal braettet have?: " +
                "\nNB!: Hvis ikke braettet har 3 felter, spilles der ikke monololy laengere"
        );
    }
    public void instilingsSporgsmaall(){
        gui.showMessage("Indtast antal oenskede Spillere som int: ");
    }
    public void instilingsSporgsmaal2(){
        gui.showMessage("Hvor mange terninger vil i spille med?: ");
    }
    public void instilingsSporgsmaal3(){
        gui.showMessage("Hvor få penge må man have før man går bankerot?: ");
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
    public void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus){
        gui.showMessage("Du slog: ");
        //printTerninger(terningsKrus);
        gui.showMessage("og rykker derfor " + terningsKrus.getTotalVaerdi() + " felter.\n");
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
        //System.out.println("| Felt nr: " + getPlacering() +" | Felt Navn:" + getNavn()+" | Felt type:"+ getFeltType()+" |");
    }
    public void paaBesoegIFaengsel(){
        System.out.println("Du er nu på besoeg i faengslet.");
    }
    public void vilDuGiveOp(){
        gui.showMessage("Er du sikker paa, at du vil udgaa fra spillet?: (1) for ja, (2) for nej" );
    }

    public void takForSpillet(){
        System.out.println("Tak for spillet:)\nDine penge vil gå til skattefar");
    }
    public void duGavIkkeOp(){
        System.out.println("Du valgte ikke at give op. ");
    }
    public void passeringAfStart(int gangeOverStart){
        gui.showMessage("Tillykke du har passeret Start "+gangeOverStart+" gange og modtager "+200*gangeOverStart);
    }
    public void chanceKortHar(){
        System.out.println("Du har foelgende Chance Kort:");
    }
    public void chanceKortNr(int i, SpillerController spiller){
        gui.showMessage("Chance kort nr. "+i+": ");
        gui.showMessage(spiller.getSpillerAktionsKort().get(i).getBeskrivelse());

    }
    public void chanceKortsVejledning(){
        gui.showMessage("Intast nummeret på det chance kort du gerne vil aktiverer:" +
                "\nEller hvis du vil tilbage til tur menuen (-1):"
        );
    }
    public void ingenChanceKort(){
        System.out.println("Du har ikke nogen Chance Kort lige nu.");
    }

    public void jernBaneInfo(Jernbane stadtion){
        String ejer;
        if(stadtion.getEjer() == null){
            ejer = "Ingen ejer endnu";
        }else{
            ejer = stadtion.getEjer().getNavn();
        }

        gui.showMessage("| Placering: "+stadtion.getPlacering()+" | Name: "+stadtion.getNavn()+" | Pris: "+stadtion.getPris() +" | Pantsat: "+stadtion.isPantsat()+"| ejer:"+ejer+"|");
    }
    public void hvorHen(int pos){
        gui.showMessage("Din nuvaerende position er: "+ pos+"Hvor vil de hen?: ");
    }
    public void holdDigPaaBrettet(){
        gui.showMessage("Den gaar ikke, du skulle have valgt noget der ligger inden for braettets antal felter");
    }
    public void overStart(int pos){
        gui.showMessage("Din position er: "+ pos+
                "\nDu har i din rundfart med taxaen kommet til at passere Start, modtag 200");
    }
    public void spillerStat(SpillerController spiller){
        gui.showMessage("Navn: "+spiller.getNavn()+" ID:"+spiller.getId()+" getPlacering(): "+spiller.getSpillerPosition()+" Penge: "+spiller.getPenge());

    }

    public void alleredeEjer(){
        gui.showMessage("Du er allerede ejer");
    }

    public void dinJernbane(){
        gui.showMessage("Jernbanen er nu din!");
    }
    public void monetosMangel(){
        gui.showMessage("Du har ikke raad på nuvaerende tidspunkt. Vi vil dog stadig gerne bevare dig som kunde");
    }
    public void taxiInfo(Taxi vogn){
        gui.showMessage("| Felt nr: " + vogn.getPlacering() +" | Felt Navn:" + vogn.getNavn()+" | Felt type:"+ vogn.getFeltType()+" |");
    }
    public void overStartAnimation(){
        System.out.println("Aktion som foelger af Start");
    }
    public void startsFeltsInfo(Start felt){
        gui.showMessage("| Felt nr: " + felt.getPlacering() +" | Felt Navn:" + felt.getNavn()+" | Felt type:"+ felt.getFeltType()+" |");
    }
    public void iFaengselMedDig(){
        gui.showMessage("HOV HOV HOV, meget kan man boeje men ikke loven!"+
        "\nFordi du er landet på et felt, hvor man bliver kriminel"+
        "\nSkal du en tur i kashotten.");
    }
    public void faengselInfo(GaaIFaengsel Faengsel){
        gui.showMessage("| Felt nr: " + Faengsel.getPlacering() +" | Felt Navn:" + Faengsel.getNavn()+" | Felt type:"+ Faengsel.getFeltType()+" |");

    }
    public void muligeDestinationer(){
        gui.showMessage("Du kan rejse til ");
    }
    public void stationsMuligheder(){
        gui.showMessage("hvis du ikke onsker at rejse tast 0, " +
                "\nellers intast den destination du ønsker at rejse til:");
    }
    public void turEfterJernbane(){
        gui.showMessage("Du kan nu forsaette din tur men får ikke muligheden for at tage jernbanen igen i denne tur," +
                "\n det tog er koert!");
    }
    public void manglerJernbaner(){
        gui.showMessage("Du ejer ikke nok jernabaner til at rejse:");

    }
    public int jernBaneTilbud(){
        String valg = gui.getUserSelection("|--|Det er en Jernbane vil du købe den?",
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
    public void chanceFeltsInfo(ChanceFelt felt){
        gui.showMessage("| Felt nr: " + felt.getPlacering() +" | Felt Navn:" + felt.getNavn()+" | Felt type:"+ felt.getFeltType()+" |"
                +"\nKort på felt:");
        for(int i = 0; i<felt.getKortPaaFelt().size();i++){
            gui.showMessage(felt.getKortPaaFelt().get(i).getBeskrivelse()+"|-| ");
        }
        System.out.print(" |");
        System.out.println(" ");
    }
    public void gennemfortKoeb(){
        gui.showMessage("Du kan koebe grunden hurra!!");
        gui.showMessage("Ejendommen er nu din!");

    }
    public void ejendomsInfo(Ejendom ej){
        String ejer;
        if(ej.getEjer() == null){
            ejer = "Ingen ejer endnu";
        }else{
            ejer = ej.getEjer().getNavn();
        }


        gui.showMessage("| Felt nr: " + ej.getPlacering() +" | Felt Navn:" + ej.getNavn()+" | Felt type:"+ ej.getFeltType()+" |"+
                "\n| Pris: "+ej.getPris()+ " | Rent: "+ej.getLeje()+" | Antal Huse: "+ej.getAntalHuse()+
                " | Huspris: "+ej.getHusPris()+" | Antal hoteller: "+ej.getAntalHoteller() +"|"+
                "\n| Pantsat: "+ej.isPantsat() +" | Group: "+ej.getGruppe().getFarve()+ "|"+" ejer: "+ejer+"|");
    }

    public void betalRente(){
        gui.showMessage("En anden Spiller ejer dette felt, du betaler derfor rente til ham:");
    }
    public void duErLandetPå(){
        gui.showMessage("Du er landet på ");
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
    public void spillerEjendele(SpillerController spiller){
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
    public void printChanceKortDirekte(ChanceAktion di){
        gui.showMessage(di.getBeskrivelse());

    }
    public void chanceKortTilføjet(){
        gui.showMessage("Dette kort vil blive tilfoejet til dine chance kort," +
                "\ndu kan nu bruge det når du oensker."
        );
    }
    public void chanceKortBrugt(){
        System.out.println("Du har nu brugt dit Taxi chance kort");
    }
    public void brugtUdAfFaengsel(){
        gui.showMessage("Du har brugt dit 'Gratis ud af feangsel' chance kort. Var du bag trammer er du nu fri," +
                "\nog hvis du ikke var, faar du alligvel lov til at slå med terningerne igen.");
    }

    @Override
    public void byggetHus(Ejendom ejendom) {

    }

    @Override
    public void ejerIngenEjendomme() {

    }

    @Override
    public void ejerIngenBebyggeligeEjendomme() {

    }

    @Override
    public int input_EjendomAtByggePaa(ArrayList<Ejendom> ejendomme) {
        return 0;
    }
}
