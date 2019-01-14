package Controller;

import BoundaryView.UserInterfaceKontrakt;

public class HandlingsController {


    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void givOp(SpillerCO spiller, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        int svar;
        svar = userInterfaceKontrakt.vilDuGiveOp();
        if(svar==1) {
           spiller.setHarGivetOp(true);
            spiller.getSpillerEjendomme().clear();
            userInterfaceKontrakt.takForSpillet();
            spil.slutSpillerTur();
        }
        else {
            userInterfaceKontrakt.duGavIkkeOp();
        }

    }

}
