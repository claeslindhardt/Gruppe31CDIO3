package ModelEnteties.braet.controllerKlasser;

import ModelEnteties.EjendomCO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EjendomCOTest {


    /**
     * @author Malte
     *
     * Test: getLeje()
     * Type: Unit Test
     *
     * Her testes om getLeje() faktisk giver den rigtige leje tilbage, med
     * udgangspunkt i basislejen og antallet af huse på den.
     * Det gøres ved at lave et Ejendomsobjekt, og bruge getLeje() metoder
     * før der er bygget hus, og for hver gang der bygges et hus.
     */
    @Test
    void getLeje() {

        // Setup
        int basisLeje = 100;
        int forventetLeje;
        int faktiskLeje;
        int[] forventetLejeMedHus = {150, 200, 250, 300};
        EjendomCO ejendom = new EjendomCO("ejendom", 100, basisLeje, 0);

        // Basis
        forventetLeje = basisLeje;
        faktiskLeje = ejendom.getLeje();

        System.out.println("Basis \n" + "Forventet: "+forventetLeje+"\n"+ "Faktisk: "+faktiskLeje+"\n");

        assertEquals(forventetLeje, faktiskLeje);

        // Med huse (1-4)
        for(int antalHuse=1; antalHuse <= 4; antalHuse++){
            ejendom.setAntalHuse(antalHuse);
            forventetLeje = forventetLejeMedHus[antalHuse-1];
            faktiskLeje = ejendom.getLeje();
            System.out.println(antalHuse + " Hus \n" + "Forventet: "+forventetLeje+"\n"+ "Faktisk: "+faktiskLeje+"\n");
            assertEquals(forventetLeje, faktiskLeje);
        }

    }
}