package ModelEnteties.chanceKort.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;

import java.util.Random;

public class GratisUdAfFaengsel extends ChanceAktion {
    //|-------initiering af objekter: -----------
    Random ra = new Random();

    //|----------- Metoder:----------------------
    public void DirketeAktion(SpilController spil, UserInterface userInterface){
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        userInterface.printChanceKortDirekte(this);
        userInterface.chanceKortTilføjet();
        
        spillerMedTur.addSpillerAktionsKort(this);
    }
    public void BetingetAktion(SpilController spil, UserInterface userInterface){
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        spillerMedTur.setFaengselsStraf(false);
        spillerMedTur.setHarSlaaetForTuren(false);
        //todo:test and make sout's
        System.out.println("Du har brugt din gratis ud af feangsel, var du bag trammer er du nu fri," +
                "\nhvid du ikke var får du alligvel lov til at slå med terningerne igen.");
    }
    //|----------- Constructor:------------------
    public GratisUdAfFaengsel(){
        int valgAfGrund = ra.nextInt(getPositiveGrunde().length);
        String grund = getPositiveGrunde()[valgAfGrund];
        String slutBeskrivelse = " Derfor syntes vi at du er så god en borger," +
                "\nat du fortjener at komme engang gratis ud af fængsel. ";
        String forklaring = grund.concat(slutBeskrivelse);

        this.setBeskrivelse(forklaring);
    }
}
