package ModelEnteties.braet.controllerKlasser;

import Controller.EjendomsGruppeCO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EjendomsGruppeDTOManagerTest {

    /**
     * @author Jacob
     * Denne test tjekker hvilke gruppestoerelser der er tilladt i programmet.
     * Det er ikke tilladt at have en gruppe stoerelse på mindre end 1, og stoerre end max int værdi.
     */
    @Test
    void EjendomsGruppeManagerTest(){
        EjendomsGruppeCO ejendomsGruppeCO;

        ejendomsGruppeCO = new EjendomsGruppeCO(1);
        assertEquals(1, ejendomsGruppeCO.getGruppeStoerelser());

        ejendomsGruppeCO = new EjendomsGruppeCO(100);
        assertEquals(100, ejendomsGruppeCO.getGruppeStoerelser());

        ejendomsGruppeCO = new EjendomsGruppeCO(1000);
        assertEquals(1000, ejendomsGruppeCO.getGruppeStoerelser());

        ejendomsGruppeCO = new EjendomsGruppeCO(2147483647);
        assertEquals(2147483647, ejendomsGruppeCO.getGruppeStoerelser());

        ejendomsGruppeCO = new EjendomsGruppeCO(0);
        assertFalse(ejendomsGruppeCO.getGruppeStoerelser() == 0);

        ejendomsGruppeCO = new EjendomsGruppeCO(-10000);
        assertFalse(ejendomsGruppeCO.getGruppeStoerelser() == -10000);

        ejendomsGruppeCO = new EjendomsGruppeCO(-2147483647);
        assertFalse(ejendomsGruppeCO.getGruppeStoerelser() == -2147483647);
    }

}