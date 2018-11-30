package ModelEnteties.Spiller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpillerDTOTest {

    /**
     * @author Jacob
     * Tester om en spillers pengebeholdning kan ændres
     */
    @Test
    void setgetPengeTest(){
        SpillerCO spillerCO = new SpillerCO("Jacob",303,0);
        spillerCO.setPenge(1000.0);
        assertTrue(spillerCO.getPenge() == 1000.0);
    }

}