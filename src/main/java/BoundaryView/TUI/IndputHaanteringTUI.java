package BoundaryView.TUI;

import Controller.ChanceFeltCO;
import ModelEnteties.Spiller;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.singletoner.ScannerSingleton;

import java.util.InputMismatchException;

/**
 * Indsæt beskrivelse her
 */
public class IndputHaanteringTUI {
    ScannerSingleton scan = ScannerSingleton.getInstance();

    public int TurMenu(int min, int max){
        int resultat = inputTal(min, max);
        return resultat;
    }

    public int velkomstMenu(int minInput, int maxInput){
        int resultat = inputTal(minInput, maxInput);
        return resultat;
    }

    public int instilingsSporgsmaal(int minInput, int maxInput){
        int resultat = inputTal(minInput, maxInput);
        return resultat;
    }

    /**
     * @author Jacob og Malte
     * Denne metode Kører scanneren i et while loop. hvis det ikke er en gyldig int der
     * bliver scannet, så vil try /catch fange den og fortælle at
     * det er et forkert input og køre scanner igen.
     * @return Den int der bliver scannet, hvis den er gyldig.
     * @param max ekslkusiv denne parameter
     * @param min inklusiv denne parameter
     * Vi bruger denne min, max practice da det er den der bliver brugt i index
     * og andre steder, hvor der bliver brugt minimum og maximum.
     */
    public int inputTal(int min, int max){

        while (true) {
            try {

                int tal = scan.nextInt();
                /**
                 * Denne scanner er meget vigtig og en virkligt dårlig løsning. Da når vi scanner en integer,
                 * Så efterlader den en new line på bufferen som hvis ikke vi har en scan.nextline her så
                 * bliver samlet op af den først følgende Scanner what a horror
                 */
                scan.nextLine();
                if (tal < max && tal >= min){
                    return tal;
                }
                System.out.println("Forkert input, tallet skal være mellem "+min+" og "+(max-1));

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
        int resultat = inputTal(1, 3);
        return resultat;
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
        int valg = inputTal(-1,100);
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
        int resultat = inputTal(1, 3);
        return resultat;
    }
    public void forsetTur(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }
    public void ejetAfEnAnden(){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Dette indput kan ikke hånteres endnu");
    }

    public void chanceFeltsInfo(ChanceFeltCO felt){
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
        int resultat = inputTal(1, 3);
        return resultat;
    }
    public void spillerEjendele(Spiller spiller){
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
    public String tagNavn(){

       String navn = scan.nextLine();
       return navn;
    }
}
