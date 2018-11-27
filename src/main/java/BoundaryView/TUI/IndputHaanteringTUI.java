package BoundaryView.TUI;

import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.controllerKlasser.*;
import ModelEnteties.singletoner.ScannerSingleton;

import java.io.IOException;
import java.util.InputMismatchException;

public class IndputHaanteringTUI {
    ScannerSingleton scan = ScannerSingleton.getInstance();

    public int TurMenu(){
        int resultat = inputTal();
        return resultat;
    }

    public int velkomstMenu(){
        int resultat = inputTal();
        return resultat;
    }

    public int instilingsSporgsmaal(){
        int resultat = inputTal();
        return resultat;
    }

    /**
     * @author Jacob og Malte
     * Denne metode Kører scanneren i et while loop. hvis det ikke er en gyldig int der bliver scannet, så vil try /catch
     * fange den og fortælle at det er et forkert input og køre scanner igen.
     * @return Den int der bliver scannet, hvis den er gyldig.
     */
    public int inputTal(){

        while (true) {
            try {

                int tal = scan.nextInt();
                return tal;
            }

            catch (InputMismatchException i){
               System.out.println("Dette er ikke et gyldigt input, proev igen!");
               scan.nextLine();
                // Man bliver nød til at tilføje denne nextLine() metode, da sout printet i kommando prompten
                // bliver set på som et forkert input hver gang try kører og vil derfor blive fanget af catch.

            }
        }

    }



    public void spillerPosition(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void bankeRaadtGrundetLiquditet(int getBankeraadGraense){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");

    }
    public void spillerUdgår(int udgaaetSpiller){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void vinder(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void anketStraffeDom(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");

    }
    public void harSlaaetMedTerningfor(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void ingenHeldIRetten(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void heldIRetten(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void retsTerninger(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void spilletErSlut(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void ensTerninger(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void menuGUI(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public int vilDuGiveOp(){
        int svar = scan.nextInt();
        return svar;
    }

    public void takForSpillet(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void duGavIkkeOp(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void passeringAfStart(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void chanceKortHar(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void chanceKortNr(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");

    }
    public int chanceKortsVejledning(){
        int valg = scan.nextInt();
        return valg;
    }
    public void ingenChanceKort(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void jernBaneInfo(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public int hvorHen(){
        int destination = scan.nextInt();
        return destination;
    }
    public void holdDigPaaBrettet(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void overStart(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void spillerStat(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void alleredeEjer(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void dinJernbane(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void overStartAnimation(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void iFaengselMedDig(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void faengselInfo(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void muligeDestinationer(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public int stationsMuligheder(){
        int destination = scan.nextInt();
        return destination;
    }
    public void turEfterJernbane(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void manglerJernbaner(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public int jernBaneTilbud(){
        int kobsBeslutning = scan.nextInt();
        return kobsBeslutning;
    }
    public void forsetTur(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void ejetAfEnAnden(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void chanceFeltsInfo(ChanceFelt felt){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void gennemfortKoeb(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");

    }
    public void ejendomsInfo(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void betalRente(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void duErLandetPå(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void badErrorMessage(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public int ejendomsBud(){
        ScannerSingleton sc = ScannerSingleton.getInstance();
        int kobsBeslutning = sc.nextInt();
        return kobsBeslutning;
    }
    public void spillerEjendele(SpillerController spiller){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void printChanceKortDirekte(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void chanceKortTilføjet(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void chanceKortBrugt(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void brugtUdAfFaengsel(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
}
