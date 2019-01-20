package model.felter;

/**
 * @author Claes & Malte
 *
 * Klassen der repræsentere et felt, som alle felttyper nedarver fra.
 *
 * HVORFOR ABSTRACT?
 * abstrackt er noget man kan gøre ved en klasse for at sikre at  man ikke kan lave instanser af den.
 * I dette tilfaelde skal man ikke bare kunne lave et felt, men kun et felt af en bestemt type *
 */
public abstract class Felt {
    private int placering; // Placering paa braettet.
    private String navn;


    public Felt(String navn, int placering ){
        this.navn = navn;
        this.placering = placering;
    }


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

}
