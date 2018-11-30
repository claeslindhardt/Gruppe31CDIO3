package ModelEnteties.braet.dataKlasser;

import Controller.SpilController;
import Controller.UserInterfaceKontrakt;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: FeltDTO
 * @author Claes
 * |-- HVORFOR ABSTRACT?
 * abstrackt er noget man kan gøre ved en klasse for at sikre at  man ikke kan lave instanser af den.
 * Dette er en fordel for forældre klasser/super klasser altså klasser der kun holder data som ikke
 * holder nogen controller funktion. Så disse klasser er kun til for datastrukturen skyld. De gør ikke
 * noget selvstændigt.
 *  */
public abstract class FeltDTO {
    //|--------- Variabler:-----------------
    private int placering;
    private String navn = null;
    private String feltType = null;

    //|--------- Getters og Setters:-----------------
    public int getPlacering() {
        return placering;
    }

    public void setPlacering(int placering) {
        this.placering = placering;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
    public String getFeltType() {
        return feltType;
    }

    public void setFeltType(String feltType) {
        this.feltType = feltType;
    }


    //|----------- Metoder:------------------
    //Disse er ikke i brug i denne forældre klasse men alt afgørende for, klassens børn
    public void printInfo(UserInterfaceKontrakt userInterfaceKontrakt){ }
    public void aktionPaaFelt(SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt){}
}
