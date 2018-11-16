package BaundaryView.TUI;

import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.braet.controllerKlasser.Faengsel;

import java.util.ArrayList;

/**
 * __________________________________________________________________________________________________________________________________________________________
 * PROGRAMDOKUMENTATION: TUI
 *
 * Dette er klassen der skal laves om til en GUI.
 * Her er alle System.out.println(""). Det vil sige at dette er klassen der står for alt som Useren ser
 * Det så kaldte Front-end puuuuhhh. Ikke et arbejde for back end funktionelt orienterede ordentlige mennesker.
 * Som ikke forstår sig på Ironi overhovet.
 *
 * Ideen med denne klasse er at gøre det let at bygge eller skifte GUI. fordi alt som skal ses af brugeren
 * kan ses her og alle funktioner der bruges er samlet i SpilleLeder. Ønsker man at lave en GUI skal den
 * bare have de samme funktioner og kald som denne klasse og så vil man have alt der skal bruges til at
 * lave en GUI.
 */
public class TUI {
    public void TurMenu(int getSpillerTur){
        System.out.println("_________________________________________________________________");
        System.out.println("|--|Det er spiller "+getSpillerTur+"'s tur.");

        System.out.println(
                "|--Kast terninger (1)   | slut din tur(2)    | se chancekort (3)         --|" +
                        "\n|--Se hvad du ejer(4)   | se spillebrettet(5)| se spiller stats(6)       --|" +
                        "\n|--Giv op (7)           | byg på ejendom (8) | Handel med Ejede ting  (9)--|" +
                        "\n 9 og 8 er ikke en mulighed endnu"
        );
    }
    public void ikkeMuligt(){
        System.out.println("Dette er ikke en mulighed endnu - prøv igen");
    }
    public void velkomstMenu(){
        System.out.println("________________________________________________");
        System.out.println("|=========| MONOPOL SPILLET MKIII |============|");
        System.out.println("|========VELKOMMEN TIL START MENUEN============|");
        System.out.println("|==============================================|");
        System.out.println("|====== For at starte nyt spil input: 1 =======|");
        System.out.println("|== For at ændre spil instillinger input: 2 ===|");
        System.out.println("|==== For at forsætte sidste spil input: 3 ====|(Woops ikke en mulighed endnu, under construction though)");
        System.out.println("|==============================================|");
    }
    public void opretteInstillinger(int getAntalSpillere,int getAntalFelter,int getAntalTerninger,int getSpillerTur,int getBankeraadGraense){
        System.out.println("_________________________________________________________________");
        System.out.println("I er: " + getAntalSpillere + " spillere.");
        System.out.println("Brettet har "+getAntalFelter+" Felter,");
        System.out.println("med "+getAntalTerninger+" terninger på brettet");
        System.out.println("og SpillerData "+getSpillerTur + " Starter!");
        System.out.println("Man går banke rådt og taber dermed hvis man har mindre end: "+getBankeraadGraense+" Penge");
    }
    public void startSpilGrundFejl(){
        System.out.println("wooops ikke en mulighed endnu, spillet starter" +
                "\nmed standardt instillinger");
    }
    public void instilingsSporgsmaal0(){
        System.out.println("Hvor mange braet skal brættet have?: ");
        System.out.println("NB!: Hvis ikke brettet har 3 braet, spilles der ikke monololy lengere");
    }
    public void instilingsSporgsmaall(){
        System.out.print("Indtast antal ønskede SpillerData som int: ");
    }
    public void instilingsSporgsmaal2(){
        System.out.println("Hvor mange terninger vil i spille med?: ");
    }
    public void instilingsSporgsmaal3(){
        System.out.println("Hvor få penge må man have før man går bankerådt?: ");
    }

    public void spillerPosition(int spillerPosition){
        System.out.println("Din position er nu felt nr: "+spillerPosition);

    }

    public void bankeRaadtGrundetLiquditet(int getBankeraadGraense){
        System.out.println("Woops du har mindre end "+getBankeraadGraense+" penge, " +
                "\ndu har derfor ikke høj nok liquditet til at forsætte Spillet."
        );
    }
    public void spillerUdgår(int udgaaetSpiller){
        System.out.println("SpillerData "+udgaaetSpiller+" er nu udgået");
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
        System.out.println("Tillykke, SpillerData "+spillerTur +" din straffedom er blevet anket og du får nu et forsøg til at komme ud af fængsel" +
                "\nDu skal blot slå to ens med terningerne"
        );
    }
    public void harSlaaetMedTerningfor(){
        System.out.println("Du har allerede slået alle de terninger du må, den her tur.");
    }
    public void ingenHeldIRetten(){
        System.out.println("ingen held i retten i dag, forbliv i fengsel." +
                "\nDu kan stadig opkreve rente og handle med ejendomme"
        );
    }
    public void heldIRetten(){
        System.out.println("du havde held i retten i dag! og må nu slå med terningerne og rykke igen!");
    }
    public void retsTerninger(int domsAfsigelseDel1, int domsAfsigelseDel2){
        System.out.println("Du slog "+domsAfsigelseDel1+" og "+domsAfsigelseDel2);
    }
    public void spilletErSlut(){
        System.out.println("Spillet er slut.");
    }
    public void spillerRykkerGrundetTerningslag(RafleBaeger terningsKrus){
        System.out.print("Du slog: ");
        printTerninger(terningsKrus);
        System.out.print("og rykker derfor " + terningsKrus.getTotalVaerdi() + " felter.\n");
    }

    public void printTerninger(RafleBaeger terningsKrus){
        ArrayList<Integer> tern = terningsKrus.FaaTerningVærdier();
        for(int i =0; i<tern.size();i++) {
            System.out.print(tern.get(i)+ ", ");
        }
    }
    public void ensTerninger(){
        System.out.println("Alle de terninger du slog, havde samme værdi! Du får lov til at slå en ekstra gang!");
    }
    public void menuGUI(){
        System.out.println("ønsker du at kører spillet med:" +
                "\n en html GUI input (1)" +
                "\n i terminalen input (2)" +
                "\n_________________________________"
        );
    }
    public void printFaenselInfo(){
        //System.out.println("| Felt nr: " + getPlacering() +" | Felt Navn:" + getNavn()+" | Felt type:"+ getFeltType()+" |");
    }
    public void paaBesoegIFaengsel(){
        System.out.println("Du er nu på besøg ved fængslet.");
    }
    public void vilDuGiveOp(){
        System.out.println("Er du sikker på at du vil udgå fra spillet?: (1) for ja, (2) for nej" );
    }

    public void takForSpillet(){
        System.out.println("Tak for spillet:)\nDine penge vil gå til skattefar");
    }
    public void duGavIkkeOp(){
        System.out.println("Du valgte ikke at give op. ");
    }
}
