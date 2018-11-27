package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.dataKlasser.EjeligtFeltDTO;


/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: EjendomCO
 *
 */
public class EjendomCO extends EjeligtFeltDTO {
    //|-------initiering af objekter: -----------


    //|--------- Variabler:-----------------
    private int     antalHuse;
    private int     antalHoteller;
    private double  husPris = 50;
    private int     leje = 50;

    //|--------- Getters og Setters:-----------------
    public int getAntalHuse() {
        return antalHuse;
    }

    public void setAntalHuse(int antalHuse) {
        this.antalHuse = antalHuse;
    }

    public int getAntalHoteller() {
        return antalHoteller;
    }

    public void setAntalHoteller(int antalHoteller) {
        this.antalHoteller = antalHoteller;
    }

    public double getHusPris() {
        return husPris;
    }

    public void setHusPris(double husPris) {
        this.husPris = husPris;
    }


    public int getLeje() {
        /* Ejendommens leje er vurderet ved standard lejen (leje),
            og antallet huse. Hvert hus øger lejen med halvdelen af den originale leje */
        int lejePerHus = leje/2;
        int totalLeje = leje + lejePerHus*antalHuse;
        return totalLeje;
    }

    public void setLeje(int leje) {
        this.leje = leje;
    }


    //|----------- Metoder:------------------
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.ejendomsInfo(this);
    }

    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        if(this.getEjer()==null){
            userInterfaceKontrakt.duErLandetPå();
            this.printInfo(userInterfaceKontrakt);


            int kobsBeslutning = userInterfaceKontrakt.ejendomsBud();
            switch (kobsBeslutning){
                case 1:
                    spillerMedTur.koebEjendom(this, userInterfaceKontrakt);
                    break;
                case 2:
                    userInterfaceKontrakt.forsetTur();

                    break;
                default:
                    userInterfaceKontrakt.ikkeMuligt();
            }
        }else if(this.getEjer() != null && this.getEjer() != spillerMedTur){
            userInterfaceKontrakt.betalRente();
            this.indsamleLeje(spillerMedTur, userInterfaceKontrakt);
        }else if(this.getEjer() == spillerMedTur){
            userInterfaceKontrakt.tetPaaMonopol();
        }
    }

    public void indsamleLeje(SpillerController spilleren, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerController ejeren = this.getEjer();
        if( ejeren != null && spilleren != null) {
            //todo: enkapsuler dette på en ordenligt måde
            spilleren.setPenge(spilleren.getPenge()-getLeje());
            ejeren.addPenge(getLeje());  // hvis Spiller ikke har nok penge til at betale skal den have mulighed for at pantsætte
        }else{
            userInterfaceKontrakt.badErrorMessage();
        }
    }


    /** @author Malte
     *  Tilføjer huse til ejendommen. Den tjekker ikke for, om der
     *  i følge reglerne kan bygges huse på ejendommen.
     * @param antalHuse: Hvor mange huse, der skal bygges.
     */
    public void bygHuse(int antalHuse){
        setAntalHuse(getAntalHuse()+antalHuse);
    }

    //|--------- Constructor:-----------------
    public EjendomCO(String whatName, int whatPrice, int whatRent, int placering){
        setPlacering(placering);
        setPris(whatPrice);
        setNavn(whatName);
        setLeje(whatRent);

        addAlleEjendomme(this);
        setAntalHoteller(0);
        setAntalHuse(0);
        setFeltType("Ejendom");
    }
}
