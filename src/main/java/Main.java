import BaundaryView.TUI.TUI;
import Controller.SpilController;
import ModelEnteties.Spiller.SpillerController;

public class Main {
    public static void main(String[] args) {
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
     *//*
        TUI Ui = new TUI();
        SpilController spil = new SpilController(Ui);
        spil.startMenu();
        spil.genererSpillere(spil.getAntalSpillere());
        spil.BretGenerator spilleBret = new BretGenerator(spil.getAntalFelter());
        spil.RafleBaeger terningsKrus = new Raflebaeger(spil.antalTerninger);
        spil.setBretGeneretForSpil(spilleBret);

        while(spil.isKør()){
            spil.tjekForVinder();
            spil.tjekOmGivetOp();
            spil.tjekForFeangselsStraf();

            //Kører kun hvis ikke der er en der har vundet spillet
            if(!spil.isVinderFindes()){
                spil.turMenu(spilleBret,terningsKrus);
            }

        }
        UserInterface.spilletErSlut();
        /*
        SpillerController spiller = new SpillerController("bo",2,25);
        TUI UserInterface = new TUI();
        spiller.chanceKortMuligheder(UserInterface);*/
    }
}
