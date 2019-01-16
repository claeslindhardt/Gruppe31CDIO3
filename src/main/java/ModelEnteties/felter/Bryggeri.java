package ModelEnteties.felter;

import BoundaryView.UserInterfaceKontrakt;
import Controller.Handel;
import Controller.SpilController;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;

public class Bryggeri extends EjeligtFeltDTO {
    private int  leje = 0;
    private EjendomsGruppeDTO gruppe;


    public Bryggeri(String navn, int pris, int placering){
        super(navn,pris,placering);
    }

    public int getLeje(){
        return leje;
    }

    public void setLeje(int leje){
        this.leje = leje;
    }


}
