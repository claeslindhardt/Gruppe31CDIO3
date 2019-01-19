package controller.subcontroller;


import model.Spil;
import model.Spiller;
import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;
import spillogik.EjendomsLogik;
import view.UserInterface;


/**
 * Denne klasse eksisterer, da der er lidt flere funktioner i at lande
 * på et ejeligt felt, end de andre felter.
 */
class LandPaaEjeligtFelt {


    /**
     * Forløbet i at lande paa et felt af typen {@link EjeligtFelt}. Det er ens
     * hvad der sker, når man lander paa et ejeligt felt, uanset hvilket
     * felt det er - derfor er de samlet i denne ene metode.
     *
     * @author Malte
     */
    void landPaaFelt(EjeligtFelt felt, Spiller spiller, Spil spil, UserInterface ui ){

        if( felt.getEjer() == null ){

            int koebsBeslutning = ui.koebsBeslutning();

            if( koebsBeslutning == 0 ){
                koebFelt( felt, spiller, ui );
            }

        }else if( felt.getEjer() != null  &&  felt.getEjer() != spiller ){
            indsamleLeje( spil, felt, spiller, ui);

        }else if( felt.getEjer() == spiller ){
            ui.ejerAlleredeFelt();
        }
    }



    /**
     * @author Andreas
     * Metoder der indsamlere leje for når man lander på et ejeligt felt.
     */
    private void indsamleLeje( Spil spil, EjeligtFelt felt, Spiller spilleren, UserInterface ui ){
        Spiller ejeren = felt.getEjer();
        if( ejeren != null && spilleren != null) {

            int lejeForFelt = 0;

            if (felt instanceof Ejendom) {
                lejeForFelt = EjendomsLogik.beregnLejeTotal(felt,spilleren.ejerEjendomsGruppe(felt.getGruppe()));

            } else if (felt instanceof Bryggeri){
                lejeForFelt = EjendomsLogik.beregnLejeBryggeri( spil.getRaflebaeger().getTotalVaerdi(), felt.getEjer() );

            }
            else if (felt instanceof Rederi){
                lejeForFelt = EjendomsLogik.beregnLejeRederi((Rederi) felt, ejeren);
            }

            spilleren.setPenge(spilleren.getPenge()-lejeForFelt);
            ejeren.addPenge(lejeForFelt);
            ui.betalerLeje( lejeForFelt, spilleren, ejeren );
        }
    }


    private void koebFelt(EjeligtFelt felt, Spiller spiller, UserInterface ui ){

        if (spiller.getPenge() >  felt.getPris()) {

            spiller.addPenge( -  felt.getPris() );
            felt.setEjer(spiller);
            spiller.addEjeligtFelt( felt );

            ui.gennemfoertKoeb(  felt, spiller);

        } else {
            ui.manglerPenge();
        }
    }



}
