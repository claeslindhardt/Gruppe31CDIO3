package controller.subcontroller;

import controller.SpilController;
import view.UserInterface;
import model.Spil;
import model.Spiller;
import model.felter.Felt;
import model.raflebaeger.RafleBaeger;
import spillogik.BevaegelsesLogik;

public class RykSpiller {


    /**
     * Forloebet i at spilleren kaster med terningerne.
     */
    public void kastTerninger(Spil spil, Spiller spiller, UserInterface ui, SpilController spilController) {
        RafleBaeger raflebaeger = spil.getRaflebaeger();

        if ( !spiller.harSlaaet() ) {
            raflebaeger.slaa();

            ui.terningerResultat( raflebaeger );

            if ( raflebaeger.erEns() ) {
                ui.ensTerninger();
                spil.getSpillerMedTur().setHarSlaaet(false);
            } else {
                spil.getSpillerMedTur().setHarSlaaet(true);
            }

            rykSpillerAntalFelter( spil, spiller, raflebaeger.getTotalVaerdi(), ui, spilController );

        } else {
            ui.harSlaaetMedTerning();
        }
    }


    /**
     * @author Malte
     * Rykker spilleren et bestemt antal felter fremad. Den beregner hvor mange
     * gange over start man bevæger sig, og udløser metoden {@link #rykSpillerTilFelt}.
     *
     * @param spiller       Spilleren der skal rykkes
     * @param felterAtRykke Hvor mange felter fremad spilleren rykker
     */
    void rykSpillerAntalFelter(Spil spil, Spiller spiller, int felterAtRykke, UserInterface ui, SpilController spilController) {
        Felt[] felter = spil.getFelter();

        Felt endeligtFelt = BevaegelsesLogik.beregnEndeligtFelt( felter, felter[spiller.getPosition()], felterAtRykke  );

        int gangeOverStart  = BevaegelsesLogik.antalGangeOverStart(spiller.getPosition(), felterAtRykke, felter.length);

        rykSpillerTilFelt( spiller, endeligtFelt, gangeOverStart, ui, spilController);
    }


    /**
     * @author Malte
     * Rykker spilleren til et specifikt felt på brættet, og udløser aktioner
     * ift. feltet, samt UI-metoder ifm. at flytte felt.
     * Beregner ikke selv, hvor mange gange spilleren bevæger sig over start,
     * men den udløser metoden passererStart() i Spiller med udgangspunkt i
     * 'gangeOverStart'
     *
     * @param spiller Spiller der skal rykkes
     * @param felt Feltet spilleren skal rykke til
     * @param gangeOverStart Hvor mange gange over start spilleren kommer. Hvis =0 sker der ikke noget.
     */
    void rykSpillerTilFelt(Spiller spiller, Felt felt, int gangeOverStart, UserInterface ui, SpilController spilController){

        if( gangeOverStart > 0 ) {
            spiller.setPenge(spiller.getPenge() + BevaegelsesLogik.passererStartPenge(gangeOverStart));
            ui.passeringAfStart(gangeOverStart);
        }

        spiller.setPosition(felt.getPlacering());
        spilController.getLandPaaFelt().landPaaFelt( felt,  spiller, spilController, ui);
    }

}
