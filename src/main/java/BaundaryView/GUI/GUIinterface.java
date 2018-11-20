package BaundaryView.GUI;

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

    public void velkomstMenu(){
        String valg = gui.getUserSelection("|=========| MONOPOL SPILLET V1, MKIII",
                "starte nyt spil", "aendre spil instillinger","forsaette sidste spil");
        gui.showMessage(valg);
    }

    public void TurMenu(int getSpillerTur){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Det er spiller "+getSpillerTur+"'s tur.");

        System.out.println(
                "|--Kast terninger (1)   | Slut din tur(2)    | Se chancekort (3)         --|" +
                        "\n|--Se hvad du ejer(4)   | Se spilbraettet(5)| Se spiller stats(6)       --|" +
                        "\n|--Giv op (7)           | Byg på ejendom (8) | Handel med Ejede ting  (9)--|" +
                        "\n 9 og 8 er ikke en mulighed endnu"
        );
    }
    public void ikkeMuligt(){
        System.out.println("Dette er ikke en mulighed endnu - prøv igen");
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
    public void instilingsSporgsmaal0(){
        System.out.println("Hvor mange felter skal braettet have?: ");
        System.out.println("NB!: Hvis ikke braettet har 3 felter, spilles der ikke monololy laengere");
    }
    public void instilingsSporgsmaall(){
        System.out.print("Indtast antal oenskede Spillere som int: ");
    }
    public void instilingsSporgsmaal2(){
        System.out.println("Hvor mange terninger vil i spille med?: ");
    }
    public void instilingsSporgsmaal3(){
        System.out.println("Hvor få penge må man have før man går bankerot?: ");
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
        System.out.print("Du slog: ");
        //printTerninger(terningsKrus);
        System.out.print("og rykker derfor " + terningsKrus.getTotalVaerdi() + " felter.\n");
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
    public void menuGUI(){
        System.out.println("Oensker du at kører spillet med:" +
                "\n en html GUIinterface input (1)" +
                "\n i terminalen input (2)" +
                "\n_________________________________"
        );
    }
    public void printFaenselInfo(){
        //System.out.println("| Felt nr: " + getPlacering() +" | Felt Navn:" + getNavn()+" | Felt type:"+ getFeltType()+" |");
    }
    public void paaBesoegIFaengsel(){
        System.out.println("Du er nu på besoeg i faengslet.");
    }
    public void vilDuGiveOp(){
        System.out.println("Er du sikker paa, at du vil udgaa fra spillet?: (1) for ja, (2) for nej" );
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
    public void chanceKortsVejledning(){
        System.out.println("-------------------------------");
        System.out.println("Intast nummeret på det chance kort du gerne vil aktiverer:" +
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

        System.out.println("| Placering: "+stadtion.getPlacering()+" | Name: "+stadtion.getNavn()+" | Pris: "+stadtion.getPris() +" | Pantsat: "+stadtion.isPantsat()+"| ejer:"+ejer+"|");
    }
    public void hvorHen(int pos){
        System.out.println("Din nuvaerende position er: "+ pos);
        System.out.println("Hvor vil de hen?: ");
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
    public void stationsMuligheder(){
        System.out.println("hvis du ikke onsker at rejse tast 0, " +
                "\nellers intast den destination du ønsker at rejse til:");
    }
    public void turEfterJernbane(){
        System.out.println("Du kan nu forsaette din tur men får ikke muligheden for at tage jernbanen igen i denne tur," +
                "\n det tog er koert!");
    }
    public void manglerJernbaner(){
        System.out.println("Du ejer ikke nok jernabaner til at rejse:");

    }
    public void jernBaneTilbud(){
        System.out.println("Du er landet på et jernbanefelt, og ingen ejer det - vil du koebe det?" +
                "\nJa(1), Nej(2)");
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
    public void ejendomsBud(){
        System.out.println("Det er en ejendom vil du købe den?"+
                "\nJa(1), nej(2)"
        );
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
}
