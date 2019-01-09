package ModelEnteties.felter;

import Controller.SpilController;
import BoundaryView.UserInterfaceKontrakt;
import Controller.SpillerCO;
import ModelEnteties.EjendomsGruppeDTO;


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
    private EjendomsGruppeDTO gruppe;

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

    public EjendomsGruppeDTO getGruppe() {
        return gruppe;
    }

    public void setGruppe(EjendomsGruppeDTO gruppe) {
        this.gruppe = gruppe;
    }


    /**
     * Indsæt beskrivelse her
     * @return
     */
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

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param userInterfaceKontrakt
     */
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.ejendomsInfo(this);
    }

    /**
     * Indsæt beskrivelse her
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO spillerMedTur = spil.getSpillerMedTur();
        if(this.getEjer()==null){
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
            userInterfaceKontrakt.updateSpillere(spillerMedTur);
            this.indsamleLeje(spillerMedTur, userInterfaceKontrakt);
        }else if(this.getEjer() == spillerMedTur){
            userInterfaceKontrakt.tetPaaMonopol();
        }
    }

    /**
     * Indsæt beskrivelse her
     * @param spilleren
     * @param userInterfaceKontrakt
     */
    public void indsamleLeje(SpillerCO spilleren, UserInterfaceKontrakt userInterfaceKontrakt){
        SpillerCO ejeren = this.getEjer();
        if( ejeren != null && spilleren != null) {
            //todo: enkapsuler dette på en ordenligt måde
            spilleren.setPenge(spilleren.getPenge()-getLeje());
            ejeren.addPenge(getLeje());  // hvis Spiller ikke har nok penge til at betale skal den have mulighed for at pantsætte
            userInterfaceKontrakt.updateSpillere(spilleren);
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

        setAntalHoteller(0);
        setAntalHuse(0);
        setFeltType("Ejendom");
    }
}
