package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.AktionsFelterDTO;

public class StartCO extends AktionsFelterDTO {
    //|--------- Variabler:-----------------
    int penge;

    //|--------- Getters og Setters:-----------------
    public int getPenge() {
        return penge;
    }

    public void setPenge(int penge) {
        this.penge = penge;
    }

    //|----------- Metoder:------------------

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param spil
     * @param userInterfaceKontrakt
     */
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.overStartAnimation();
    }

    /**
     * Denne metode er linket til UserInterfaceKontrakt (interface). Den giver så enten GUI eller TUI mulighed for at
     * skrive noget kode til den, så metoden bliver sat i brug i enten GUI eller TUi
     * @param userInterfaceKontrakt
     */
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){
        userInterfaceKontrakt.startsFeltsInfo(this);
    }


    //|--------- Constructor:-----------------
    public StartCO(int startPasseringsInkomst, int placering){
        setPenge(startPasseringsInkomst);
        setPlacering(placering);
        setNavn("StartCO FeltDTO");
        setFeltType("StartCO");
    }
}
