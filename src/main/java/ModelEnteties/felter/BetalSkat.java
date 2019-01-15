package ModelEnteties.felter;

import BoundaryView.UserInterfaceKontrakt;
import Controller.Handel;
import Controller.SpilController;

public class BetalSkat extends FeltDTO {

    private int extraordinærskat = 100;
    private int aktionærskat;

    public BetalSkat(int placering) {
        setPlacering(placering);
        setFeltType("Skat");
    }

    @Override
    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt) {
        super.aktionPaaFelt(handel, spil, userInterfaceKontrakt);

        double nuvPengebeholdning = spil.getSpil().getSpillerMedTur().getPenge();
//Her vurderer aktionPåfelt om spilleren befinder sig på felt 4, hvor der skatten der skal betales er specifik.
        if (spil.getSpil().getSpillerMedTur().getSpillerPosition() == 4) {

            userInterfaceKontrakt.skatteBesked(2);

            double skat;
            int valg = 0;

            /*HEr vælges det om spilleren vil beltale 200 ell er 10 %
            * */
            String betalingsValg = userInterfaceKontrakt.skatteBetaling();

            if (betalingsValg == "At betale 200") {
                valg = 1;
            } else {
                valg = 2;
            }
            switch (valg) {
                case 1:{ skat = 200;
                    spil.getSpil().getSpillerMedTur().setPenge(nuvPengebeholdning - skat);
                break;}

                case 2:{ spil.getSpil().getSpillerMedTur().setPenge(nuvPengebeholdning * 0.9);
                break;}

            }

         //Her vurderer aktionPåFelt om spilleren befinder sig på felt 38, hvor det er en anden skat.
        }else if(spil.getSpil().getSpillerMedTur().getSpillerPosition() == 38){
                userInterfaceKontrakt.skatteBesked(1);
                int skat =100;
                spil.getSpil().getSpillerMedTur().setPenge(nuvPengebeholdning - skat);}


        userInterfaceKontrakt.updateSpillere(spil.getSpil().getSpillerMedTur());
    }
}
