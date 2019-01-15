package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.felter.EjendomCO;
import spillogik.EjendomsLogik;

/*Denne klasse er en subcontroller.
* Denne controller håndtere alle alle metoder der har med pengetransaktioner at gøre.
* */


public class Handel {


    /**
     * @author Andreas
     * Metoder der indsamlere leje for når man lander på et ejeligt felt.
     * @param ejendom
     * @param spilleren
     * @param userInterfaceKontrakt
     */
    public void indsamleLeje(EjendomCO ejendom,SpillerCO spilleren, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO ejeren = ejendom.getEjer();
        if( ejeren != null && spilleren != null) {
            //todo: enkapsuler dette på en ordenligt måde
            int lejeForFelt = EjendomsLogik.beregnLejeTotal(ejendom,spilleren.ejerEjendomsGruppe(ejendom.getGruppe()));
            spilleren.setPenge(spilleren.getPenge()-lejeForFelt);
            ejeren.addPenge(ejendom.getLeje());  // hvis Spiller ikke har nok penge til at betale skal den have mulighed for at pantsætte
            userInterfaceKontrakt.updateSpillere(spilleren);
            userInterfaceKontrakt.updateSpillere(ejeren);
        }else{
            userInterfaceKontrakt.badErrorMessage();
        }
    }


}
