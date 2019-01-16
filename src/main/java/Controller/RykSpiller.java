package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.raflebaeger.RafleBaeger;
import ModelEnteties.felter.FeltDTO;
import spillogik.BevaegelsesLogik;

public class RykSpiller {


    /**
     * Indsæt beskrivelse her
     */
    public void kastTerninger(Spil spil, Spiller spiller, UserInterfaceKontrakt ui, SpilController spilController) {
        RafleBaeger rafleBaeger = spil.getRaflebaeger();

        if ( !spiller.isHarSlaaetForTuren() ) {

            rafleBaeger.slaa();

            ui.spillerRykkerGrundetTerningslag( rafleBaeger, spil.getSpillerTur() );

            if ( rafleBaeger.erEns() ) {
                ui.ensTerninger();
                spil.getSpillerMedTur().setHarSlaaetForTuren(false);

            } else {
                spil.getSpillerMedTur().setHarSlaaetForTuren(true);

            }

            rykSpillerAntalFelter( spil, spiller, rafleBaeger.getTotalVaerdi(), ui, spilController );

        } else {
            ui.harSlaaetMedTerningfor();

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
    public void rykSpillerAntalFelter(Spil spil, Spiller spiller, int felterAtRykke, UserInterfaceKontrakt ui, SpilController spilController) {

        FeltDTO[] felter = spil.getFelter();

        FeltDTO endeligtFelt = BevaegelsesLogik.beregnEndeligtFelt( felter, felter[spiller.getSpillerPosition()], felterAtRykke  );

        int gangeOverStart  = BevaegelsesLogik.antalGangeOverStart(spiller.getSpillerPosition(), felterAtRykke, felter.length);

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
    public void rykSpillerTilFelt( Spiller spiller, FeltDTO felt, int gangeOverStart, UserInterfaceKontrakt ui, SpilController spilController){

        if( gangeOverStart > 0 ) {
            spiller.setPenge(spiller.getPenge() + BevaegelsesLogik.passererStartPenge(gangeOverStart));
            ui.passeringAfStart(gangeOverStart);
            ui.updateSpillere(spiller);

        }

        spiller.setSpillerPosition(felt.getPlacering());
        spilController.getLandPaaFelt().landPaaFelt(felt, spiller, ui);
    }









}
