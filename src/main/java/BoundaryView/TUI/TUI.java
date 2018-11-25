package BoundaryView.TUI;

import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.controllerKlasser.*;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;
import ModelEnteties.singletoner.ScannerSingleton;
import gui_fields.GUI_Field;
import gui_main.GUI;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * __________________________________________________________________________________________________________________________________________________________
 * PROGRAMDOKUMENTATION: TUI
 *
 * Dette er klassen der skal laves om til en GUIinterface.
 * Her er alle System.out.println(""). Det vil sige at dette er klassen der står for alt som Useren ser
 * Det så kaldte Front-end puuuuhhh. Ikke et arbejde for back end funktionelt orienterede ordentlige mennesker.
 * Som ikke forstår sig på Ironi overhovet.
 *
 * Ideen med denne klasse er at gøre det let at bygge eller skifte GUIinterface. fordi alt som skal ses af brugeren
 * kan ses her og alle funktioner der bruges er samlet i SpilleLeder. Ønsker man at lave en GUIinterface skal den
 * bare have de samme funktioner og kald som denne klasse og så vil man have alt der skal bruges til at
 * lave en GUIinterface.
 */
public class TUI implements UserInterfaceKontrakt {
    //TODO: forsimpel alle de steder der er gentagelser i teksten her.
    //Todo. Gør det muligt for denne at tage input via IndputHaanteringsklassen.
    IndputHaanteringTUI input = new IndputHaanteringTUI();
    public int TurMenu(int getSpillerTur){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Det er spiller "+getSpillerTur+"'s tur.");

        System.out.println(
                "|--Kast terninger (1)   | Slut din tur(2)    | Se chancekort (3)         --|" +
                        "\n|--Se hvad du ejer(4)   | Se spilbraettet(5)| Se spiller stats(6)       --|" +
                        "\n|--Giv op (7)           | Byg på ejendom (8) | Handel med Ejede ting  (9)--|" +
                        "\n 9 og 8 er ikke en mulighed endnu"
        );
        return input.TurMenu();
    }
    public void ikkeMuligt(){
        System.out.println("Dette er ikke en mulighed endnu - prøv igen");
    }
    public int velkomstMenu(){

        System.out.println("_________________________________________________");
        System.out.println("|=========| MONOPOL SPILLET MKIII |=============|");
        System.out.println("|========VELKOMMEN TIL START MENUEN=============|");
        System.out.println("|===============================================|");
        System.out.println("|====== For at starte nyt spil input: 1 ========|");
        System.out.println("|== For at aendre spil instillinger input: 2 ===|");
        System.out.println("|==== For at forsaette sidste spil input: 3 ====|(Woops ikke en mulighed endnu, under construction though)");
        System.out.println("|===============================================|");

        return input.velkomstMenu();
    }
    public void opretteInstillinger(int getAntalSpillere,int getAntalFelter,int getAntalTerninger,int getSpillerTur,int getBankeraadGraense){
        System.out.println("_________________________________________________________________");
        System.out.println("I er: " + getAntalSpillere + " spillere.");
        System.out.println("Braettet har "+getAntalFelter+" Felter,");
        System.out.println("med "+getAntalTerninger+" terninger på braettet.");
        System.out.println("Spiller "+getSpillerTur + " Starter!");
        System.out.println("Man går bankerot og taber dermed hvis man har mindre end: "+getBankeraadGraense+" penge.");
    }
    public void startSpilGrundFejl(){
        System.out.println("Wooops ikke en mulighed endnu, spillet starter" +
                "\nmed standard instillinger");
    }
    public int instilingsSporgsmaal0(){
        System.out.println("Hvor mange felter skal braettet have?: ");
        System.out.println("NB!: Hvis ikke braettet har 3 felter, spilles der ikke monololy laengere");
        return input.instilingsSporgsmaal();
    }
    public int instilingsSporgsmaall(){
        System.out.print("Indtast antal oenskede Spillere som int: ");
        return input.instilingsSporgsmaal();
    }
    public int instilingsSporgsmaal2(){
        System.out.println("Hvor mange terninger vil i spille med?: ");
        return input.instilingsSporgsmaal();
    }
    public int instilingsSporgsmaal3(){
        System.out.println("Hvor få penge må man have før man går bankerot?: ");
        return input.instilingsSporgsmaal();
    }

    public void spillerPosition(int spillerPosition){
        System.out.println("Din position er nu felt nr: "+spillerPosition);

    }

    public void bankeRaadtGrundetLiquditet(int getBankeraadGraense){
        System.out.println("Woops du har mindre end "+getBankeraadGraense+" penge, " +
                "\nog du har derfor ikke høj nok liquditet til at forsætte Spillet."
        );
    }
    public void spillerUdgår(int udgaaetSpiller){
        System.out.println("Spiller "+udgaaetSpiller+" er nu udgået");
    }
    public void terminalLinje(){
        System.out.println("_________________________________________________________________");
    }
    public void midtTerminalLinje(){
        System.out.println("-----------------------------------------------");
    }
    public void vinder(int vinder){
        System.out.println("Hurra Vi har en vinder, vinder blev spiller "+(vinder));

    }

    public void anketStraffeDom(int spillerTur){
        System.out.println("Tillykke, Spiller "+spillerTur +" din straffedom er blevet anket og du får nu et forsøg til at komme ud af faengsel." +
                "\nDu skal blot slå to ens med terningerne"
        );
    }
    public void harSlaaetMedTerningfor(){
        System.out.println("Du har allerede slaeet alle de terninger du maa, den her tur.");
    }
    public void ingenHeldIRetten(){
        System.out.println("Ingen held i retten i dag, forbliv i faengsel." +
                "\nDu kan stadig opkraeve rente og handle med ejendomme"
        );
    }
    public void heldIRetten(){
        System.out.println("Du havde held i retten i dag, og må defor slå med terningerne og rykke igen med det samme.");
    }
    public void retsTerninger(int domsAfsigelseDel1, int domsAfsigelseDel2){
        System.out.println("Du slog "+domsAfsigelseDel1+" og "+domsAfsigelseDel2);
    }
    public void spilletErSlut(){
        System.out.println("Spillet er slut.");
    }
    public void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus){
        System.out.print("Du slog: " + terningsKrus.getTotalVaerdi());
        //printTerninger(terningsKrus);
        System.out.print(" og rykker derfor " + terningsKrus.getTotalVaerdi() + " felter.\n");
    }

    public void printTerninger(RafleBaeger terningsKrus){
        ArrayList<Integer> tern = terningsKrus.FaaTerningVærdier();
        for(int i =0; i<tern.size();i++) {
            System.out.print(tern.get(i)+ ", ");
        }
    }
    public void ensTerninger(){
        System.out.println("Alle de terninger du slog, havde samme vaerdi. Du får lov til at slå en ekstra gang.");
    }

    public void printFaenselInfo(){
        //System.out.println("| Felt nr: " + getPlacering() +" | Felt Navn:" + getNavn()+" | Felt type:"+ getFeltType()+" |");
    }
    public void paaBesoegIFaengsel(){
        System.out.println("Du er nu på besoeg i faengslet.");
    }
    public int vilDuGiveOp(){
        System.out.println("Er du sikker paa, at du vil udgaa fra spillet?: (1) for ja, (2) for nej" );
        return input.vilDuGiveOp();
    }

    public void takForSpillet(){
        System.out.println("Tak for spillet:)\nDine penge vil gå til skattefar");
    }
    public void duGavIkkeOp(){
        System.out.println("Du valgte ikke at give op. ");
    }
    public void passeringAfStart(int gangeOverStart){
        System.out.println("Tillykke du har passeret Start "+gangeOverStart+" gange og modtager "+200*gangeOverStart);
    }
    public void chanceKortHar(){
        System.out.println("Du har foelgende Chance Kort:");
    }
    public void chanceKortNr(int i, SpillerController spiller){
        System.out.println("Chance kort nr. "+i+": ");
        System.out.println(spiller.getSpillerAktionsKort().get(i).getBeskrivelse());

    }
    public int chanceKortsVejledning(){
        System.out.println("-------------------------------");
        System.out.println("Intast nummeret på det chance kort du gerne vil aktiverer:" +
                "\nEller hvis du vil tilbage til tur menuen (-1):"
        );

        return input.chanceKortsVejledning();
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

        System.out.println("| Placering: "+stadtion.getPlacering()+" | Name: "+stadtion.getNavn()+" | Pris: "+stadtion.getPris() +" | Pantsat: "+stadtion.isPantsat()+"| ejer:"+ejer+"|");
    }
    public int hvorHen(int pos){
        System.out.println("Din nuvaerende position er: "+ pos);
        System.out.println("Hvor vil de hen?: ");
        return input.hvorHen();
    }
    public void holdDigPaaBrettet(){
        System.out.println("Den gaar ikke, du skulle have valgt noget der ligger inden for braettets antal felter");
    }
    public void overStart(int pos){
        System.out.println("Din position er: "+ pos);
        System.out.println("Du har i din rundfart med taxaen kommet til at passere Start, modtag 200");
    }
    public void spillerStat(SpillerController spiller){
        System.out.println("Navn: "+spiller.getNavn()+" ID:"+spiller.getId()+" getPlacering(): "+spiller.getSpillerPosition()+" Penge: "+spiller.getPenge());

    }

    public void alleredeEjer(){
        System.out.println("Du er allerede ejer");
    }

    public void dinJernbane(){
        System.out.println("Jernbanen er nu din!");
    }
    public void monetosMangel(){
        System.out.println("Du har ikke raad på nuvaerende tidspunkt. Vi vil dog stadig gerne bevare dig som kunde");
    }
    public void taxiInfo(Taxi vogn){
        System.out.println("| Felt nr: " + vogn.getPlacering() +" | Felt Navn:" + vogn.getNavn()+" | Felt type:"+ vogn.getFeltType()+" |");
    }
    public void overStartAnimation(){
        System.out.println("Aktion som foelger af Start");
    }
    public void startsFeltsInfo(Start felt){
        System.out.println("| Felt nr: " + felt.getPlacering() +" | Felt Navn:" + felt.getNavn()+" | Felt type:"+ felt.getFeltType()+" |");
    }
    public void iFaengselMedDig(){
        System.out.println("HOV HOV HOV, meget kan man boeje men ikke loven!");
        System.out.println("Fordi du er landet på et felt, hvor man bliver kriminel");
        System.out.println("Skal du en tur i kashotten.");
    }
    public void faengselInfo(GaaIFaengsel Faengsel){
        System.out.println("| Felt nr: " + Faengsel.getPlacering() +" | Felt Navn:" + Faengsel.getNavn()+" | Felt type:"+ Faengsel.getFeltType()+" |");

    }
    public void muligeDestinationer(){
        System.out.println("Du kan rejse til ");
    }
    public int stationsMuligheder(){
        System.out.println("hvis du ikke onsker at rejse tast 0, " +
                "\nellers intast den destination du ønsker at rejse til:");
        return input.stationsMuligheder();
    }
    public void turEfterJernbane(){
        System.out.println("Du kan nu forsaette din tur men får ikke muligheden for at tage jernbanen igen i denne tur," +
                "\n det tog er koert!");
    }
    public void manglerJernbaner(){
        System.out.println("Du ejer ikke nok jernabaner til at rejse:");

    }
    public int jernBaneTilbud(){
        System.out.println("Du er landet på et jernbanefelt, og ingen ejer det - vil du koebe det?" +
                "\nJa(1), Nej(2)");
        return input.jernBaneTilbud();
    }
    public void forsetTur(){
        System.out.println("Forsaet din tur");
    }
    public void ejetAfEnAnden(){
        System.out.println("En anden Spiller ejer dette felt, Du kan derfor ikke koebe det");
    }
    public void tetPaaMonopol(){
        System.out.println("Du er landet på et sted du ejer, naermer du dig et monopoly?");
    }
    public void chanceFeltsInfo(ChanceFelt felt){
        System.out.println("| Felt nr: " + felt.getPlacering() +" | Felt Navn:" + felt.getNavn()+" | Felt type:"+ felt.getFeltType()+" |"
                +"\nKort på felt:");
        for(int i = 0; i<felt.getKortPaaFelt().size();i++){
            System.out.print(felt.getKortPaaFelt().get(i).getBeskrivelse()+"|-| ");
        }
        System.out.print(" |");
        System.out.println(" ");
    }
    public void gennemfortKoeb(){
        System.out.println("Du kan koebe grunden hurra!!");
        System.out.println("Ejendommen er nu din!");

    }
    public void ejendomsInfo(Ejendom ej){
        String ejer;
        if(ej.getEjer() == null){
            ejer = "Ingen ejer endnu";
        }else{
            ejer = ej.getEjer().getNavn();
        }


        System.out.println("| Felt nr: " + ej.getPlacering() +" | Felt Navn:" + ej.getNavn()+" | Felt type:"+ ej.getFeltType()+" |"+
                "\n| Pris: "+ej.getPris()+ " | Rent: "+ej.getLeje()+" | Antal Huse: "+ej.getAntalHuse()+
                " | Huspris: "+ej.getHusPris()+" | Antal hoteller: "+ej.getAntalHoteller() +"|"+
                "\n| Pantsat: "+ej.isPantsat() +" | Group: "+ej.getGruppe().getFarve()+ "|"+" ejer: "+ejer+"|");
    }

    public void betalRente(){
        System.out.println("En anden Spiller ejer dette felt, du betaler derfor rente til ham:");
    }
    public void duErLandetPå(){
        System.out.println("Du er landet på ");
    }
    public void badErrorMessage(){
        System.out.println("ERROR: WOOPS, TRIED TO COLLECTRENT WHEN PLAYER OBJECT WAS EMPTY!");
    }
    public int ejendomsBud(){
        System.out.println("Det er en ejendom vil du købe den?"+
                "\nJa(1), nej(2)"
        );

        return input.ejendomsBud();
    }
    public void spillerEjendele(SpillerController spiller){
        System.out.println("Ejendeomme: ");
        for(int i = 0; i<spiller.getSpillerEjendomme().size();i++){
            spiller.getSpillerEjendomme().get(i).printInfo(this);
        }
        System.out.println("Jernbaner: ");
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
        System.out.println("Der var et problem med generering af ChanceKort, på et specifikt felt.");
    }
    public void printChanceKortDirekte(ChanceAktion di){
        System.out.println(di.getBeskrivelse());

    }
    public void chanceKortTilføjet(){
        System.out.println("Dette kort vil blive tilfoejet til dine chance kort," +
                "\ndu kan nu bruge det når du oensker."
        );
    }
    public void chanceKortBrugt(){
        System.out.println("Du har nu brugt dit Taxi chance kort");
    }
    public void brugtUdAfFaengsel(){
        System.out.println("Du har brugt dit 'Gratis ud af feangsel' chance kort. Var du bag trammer er du nu fri," +
                "\nog hvis du ikke var, faar du alligvel lov til at slå med terningerne igen.");
    }

    public void byggetHus(Ejendom ejendom) {
        System.out.println("Du har bygget et hus paa "+ejendom.getNavn());
    }

    public void ejerIngenEjendomme() {
        System.out.println("Du ejer ingen ejendomme");
    }

    public void ejerIngenBebyggeligeEjendomme() {
        System.out.println("Du kan ikke bygge på nogen af dine ejendomme endnu.");
    }

    public int input_EjendomAtByggePaa(ArrayList<Ejendom> ejendomme) {

        ScannerSingleton scanner = ScannerSingleton.getInstance();

        System.out.println("Hvilken ejendom vil du bygge på?");

        for(int i=0; i<ejendomme.size(); i++ ){
            System.out.println(i+1 + ") "+ejendomme.get(i).getNavn());
        }

        int input = 0;
        while(true) {
            try {
                input = scanner.nextInt();

                if(input>0 && input < ejendomme.size()+1) {
                    break;
                }else{
                    System.out.println("Forkert input!");
                }
            } catch (InputMismatchException exception) {
                System.out.println("Forkert input!");
            }
        }
        return (input-1);
    }
    public void generGUIBret(int AntalFelter){

    }

    public void spillerNanvne(int antalSpillere){
        System.out.println("Indtast navnet på de "+antalSpillere+" der skal være med i spillet.\n Afslut med Enter efter hvert navn");
    }
}
