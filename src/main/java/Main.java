import BoundaryView.TUI.TUI;
import BoundaryView.GUI.GUIinterface;
import Controller.SpilController;
import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.singletoner.ScannerSingleton;
import java.util.InputMismatchException;

public class Main {


    public static void main(String[] args) {
        /**
         * @author Claes Lindhardt
         * main klassen har til formål at lade java vide hvor den skal starte.
         * den gør brug af parameteret
         * @param String[] args En liste af argumenter givet til programmet ved start,
         *                 som repræsenteres som en en liste af strings
         *
         * og desusden variablerne:
         * @param startArgumen      angiver [indset forklaring]
         * @param Ui                angiver hvilket UserInterface der ønskes at gøre brug af.
         * @param SpilController    alle de obejekter der skal til for at spille spillet(brettet, spillerne osv.)
         *
         */

        int startArgument = HaanterStartArgument(args.length, args);
        UserInterfaceKontrakt Ui = valgAfUI(startArgument);

        SpilController spilController = new SpilController(Ui);
        spilController.start();

        /**
         * ==========================================
         *          Typsik forløb i main
         * ==========================================
         * 1. Funktionen HaanterStartArgument giver en mulighed for at vælge startArgumentet gennem terminalen
         * 2. Så vælges hvilken slags BrugerInteraktionsflade(UI) der skal gøres brug af.
         * 3. En spille controller oprettes så på baggrund af valgt UI
         *    Her vælges også spillets specifikation(så som antal spillere)
         * 4. Så kører spillet tur baseret som et loop, der tjekker om der skulle være en vinder
         * 5. når der til sidst er fundet en vinder kørers slutprotokollen der sikrer at
         *    alle process bliver lukket rigtigt.
         * __________________________________________
         * (se evt. Use-Casen i bunden For uddybelse)
         */

    }



    public static int HaanterStartArgument(int arglength, Object[] args){
        /**
         * @author Claes Lindhardt
         * Når man kører programmet (f.eks. via .bat-filen) kan man give den en række startargumenter,
         * som havner i en String array. Med udgangspunkt i den liste kan man så få programmet til
         * at gøre noget bestemt afhængigt af argumenterne. Så man kan starte programmet med nogle
         * bestemte indstillinger.
         * @param startArgument     Så man som start argument kan angive hvilken UI man vil bruge
         */
        // Håndterer startargument

        int startArgument = 0;
        // 0 = intet argument el. mere end ét argument
        // 1 = gui
        // 2 = tui

        if(arglength == 1 && args[0].equals("gui")){
            startArgument = 1;

        }else if(arglength == 1 && args[0].equals("tui")){
            startArgument = 2;
        }
        return startArgument;
    }



    public static UserInterfaceKontrakt valgAfUI(int startArgument){
        /**
         * @author Claes Lindhardt
         * Funktion tager et input fra brugeren og implementere, med udgangspunkt i det
         * den relevante interface af brugerinteraktiosnfladen.
         * @param startArgument     hvis man før man kører program filen et andet
         *                          sted på forhånd har angivet hvilke UI man ønsker.
         *                          bruges dette parameter til at springe valget over
         * @return Ui               Den valgte UI enten en TUI eller en GUI
         */
        // Efterspørger start argument
        if( startArgument == 0) {

            ScannerSingleton scan = ScannerSingleton.getInstance();

            System.out.println("Hjerteligt velkommen til Monopoly junior" +
                    "\n----|input (1) for at spille med GUIinterface( Graphical User Interface)"+
                    "\n----|input (2) for at spille med TUI (Text User Interface)"

            );

            while (true) {
                try {
                    startArgument = scan.nextInt();
                    if (startArgument == 1 || startArgument == 2) {
                        break;
                    }
                    System.out.println("Forkert input. tallet skal være mellem 1 og 2");
                } catch (InputMismatchException i) {
                    System.out.println("Dette er ikke et gyldigt input, proev igen!");
                    scan.nextLine();
                }
            }
        }


        UserInterfaceKontrakt Ui = null;

        if(startArgument == 1) {
            System.out.println("Starter med GUI");
            Ui = new GUIinterface();
        } else if (startArgument == 2){
            System.out.println("Starter med TUI");
            Ui = new TUI();
        }
        return Ui;
    }

}
/*
    ______________________________________________
    |Et standard exempel spil forløber(USE-Case):|
    |============================================|
    1. først vælger brugeren spil instillingerne
    2. spil og bret generes ud fra brugerens valg
    3. en tilfældigt udvalgt spiller får den første tur:
            Hvor han kan:
            1. Slå men terningerne
            2. give turen videre
            3. Se spiller statestikkerne
            4. Se hvad han selv ejer
            5. Se hvilke chance kort han har
            6. give op
            7. At bygge huse/hotel på ejede grunde
            8. Se spille brettet
    4.Spilleren vælger at slå med terningerne
    5 Han Lander på et nyt felt, heraf følger en aktion:
            som fx. kunne være:
            1. At betale rente
            2. At kunne købe et nyt felt
            3. at tage en TaxiCO
    6. Så vil han se hvad hans muligheder nu er
    og for tur menuen igen
    7. Når han er fertig slutter han sin tur.
    8. scripted tjekker således om han har givet op,
    eller er gået banke rådt.
    9. hvis der kun er en spiller tilbage vil han blive erkleret vinder!!!!!
    10. ellers vil turen blive givet videre til den næste i række følgen.
    |============================================|
     */

