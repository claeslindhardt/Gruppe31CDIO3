package ModelEnteties.felter;
//Ekstra Commit
import BoundaryView.UserInterfaceKontrakt;
import Controller.Handel;
import Controller.SpilController;

public class StatsSkat extends FeltDTO {

    private double skat;

    public StatsSkat(int placering, double skat) {
        this.skat = skat;
        setPlacering(placering);
        setNavn("Betal statsskat");
        setFeltType("Skat");
    }

    @Override
    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt ui) {

        double nuvPengebeholdning = spil.getSpil().getSpillerMedTur().getPenge();

        // Her vurderer aktionPåfelt om spilleren befinder sig på felt 4, hvor der skatten der skal betales er specifik.

        ui.skatteBesked(1);
        spil.getSpil().getSpillerMedTur().setPenge(nuvPengebeholdning - skat);

        ui.updateSpillere(spil.getSpil().getSpillerMedTur());
    }
}
