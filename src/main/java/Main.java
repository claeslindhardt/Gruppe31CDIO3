import BoundaryView.TUI.TUI;
import BoundaryView.GUI.GUIinterface;
import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.singletoner.ScannerSingleton;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {


    //Variabler: ________________________________
        UserInterfaceKontrakt Ui;
        Ui = new TUI();

        ScannerSingleton scan = ScannerSingleton.getInstance();

    /**==========================================
    |                  MAIN                   |
    ==========================================*/
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
            3. at tage en Taxi
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
        System.out.println("Hjerteligt velkommen til Monopoly junior" +
                "\n----|input (1) for at spille med TUI(Text User Interface)" +
                "\n----|input (2) for at spille med GUIinterface(graphical User Interface), denne er endnu begrænset implementeret"
        );


        int input = 0;
        while (true) {
            try {
                input = scan.nextInt();
                if( input>0 && input<=2){
                    break;
                }
                System.out.println("Forkert input. tallet skal være mellem 1 og 2");
            }
            catch (InputMismatchException i){
                System.out.println("Dette er ikke et gyldigt input, proev igen!");
                scan.nextLine();
            }
        }


        if(input == 1) {
            System.out.println("Du valgte en TUI");
            Ui = new TUI();
        } else if(input == 2) {
            System.out.println("Du valgte en GUIinterface");
            Ui = new GUIinterface();

        }
        SpilController spil = new SpilController(Ui);

        while(spil.isKør()){
            spil.tjekForVinder();
            spil.tjekOmGivetOp();
            spil.tjekForFeangselsStraf();

            //Kører kun hvis ikke der er en der har vundet spillet
            if(!spil.isVinderFindes()){
                spil.turMenu(spil.getBretGeneretForSpil(),spil.getTerningeKrus());
            }

        }
        spil.getUserInterfaceKontrakt().spilletErSlut();
        /*
        SpillerController spiller = new SpillerController("bo",2,25);
        TUI UserInterfaceKontrakt = new TUI();
        spiller.chanceKortMuligheder(UserInterfaceKontrakt);*/
    }
}
