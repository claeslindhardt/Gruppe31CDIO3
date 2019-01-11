import BoundaryView.TUI.TUI;
import BoundaryView.GUI.GUIinterface;
import Controller.SpilController;
import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.singletoner.ScannerSingleton;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {


    // Håndterer startargument

        int startArgument = 0;
        // 0 = intet argument el. mere end ét argument
        // 1 = gui
        // 2 = tui

        if(args.length == 1 && args[0].equals("gui")){
            startArgument = 1;

        }else if(args.length == 1 && args[0].equals("tui")){
            startArgument = 2;
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




    // Metode kald ________________________________




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



        SpilController spilController;

        if (startArgument == 2){
            System.out.println("Starter med TUI");
            spilController = new SpilController( new TUI() );
        }else{
            System.out.println("Starter med GUI");
            spilController = new SpilController( new GUIinterface() );
        }

        spilController.start();



    }
}
