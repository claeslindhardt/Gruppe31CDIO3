package ModelEnteties.felter;

import BoundaryView.UserInterfaceKontrakt;
import Controller.Handel;
import Controller.SpilController;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;

public class Bryggeri extends EjeligtFeltDTO {

    private int pris = 25;
    private int  leje = 0;
    private EjendomsGruppeDTO gruppe;
    private String navn;



    public Bryggeri(String navn, int placering){

        setPlacering(placering);
        setFeltType("Bryggeri");
        this.navn = navn;

    }

    public int getLeje(){
        return leje;
    }

    public void setLeje(int leje){
        this.leje = leje;
    }

    public String getNavn(){
        return navn;

    }

    public int getPris(){

        return pris;
    }


    public void aktionPaaFelt(Handel handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        Spiller spillerMedTur = spil.getSpil().getSpillerMedTur();

        if(this.getEjer()==null){
            this.printInfo(userInterfaceKontrakt);


            int kobsBeslutning = userInterfaceKontrakt.ejendomsBud();
            switch (kobsBeslutning){
                case 0:
                    spil.getKoebFelt().koebBryggeri(this, spillerMedTur, userInterfaceKontrakt, spil);
                    break;
                case 1:
                    userInterfaceKontrakt.forsetTur();

                    break;
                default:

            }
        }else if(this.getEjer() != null && this.getEjer() != spillerMedTur){
            userInterfaceKontrakt.betalRente();
            userInterfaceKontrakt.updateSpillere(spillerMedTur);
            handel.indsamleLeje(spil.getSpil(),this,spillerMedTur, userInterfaceKontrakt);
        }else if(this.getEjer() == spillerMedTur){
            userInterfaceKontrakt.tetPaaMonopol();
        }
    }

}
