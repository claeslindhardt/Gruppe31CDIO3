package ModelEnteties.braet.controllerKlasser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EjendomsGruppeManagerTest {

    /**
     * @author Jacob
     * Denne test tjekker hvilke gruppestoerelser der er tilladt i programmet.
     */
    @Test
    void EjendomsGruppeManagerTest(){
        EjendomsGruppeManager ejendomsGruppeManager;

        ejendomsGruppeManager = new EjendomsGruppeManager(100);
        assertEquals(100, ejendomsGruppeManager.getGruppeStoerelser());

        ejendomsGruppeManager = new EjendomsGruppeManager(1000);
        assertEquals(1000,ejendomsGruppeManager.getGruppeStoerelser());

        ejendomsGruppeManager = new EjendomsGruppeManager(2147483647);
        assertEquals(2147483647,ejendomsGruppeManager.getGruppeStoerelser());

        ejendomsGruppeManager = new EjendomsGruppeManager(0);
        assertFalse(ejendomsGruppeManager.getGruppeStoerelser() == 0);

        ejendomsGruppeManager = new EjendomsGruppeManager(-10000);
        assertFalse(ejendomsGruppeManager.getGruppeStoerelser() == -10000);

        ejendomsGruppeManager = new EjendomsGruppeManager(-2147483647);
        assertFalse(ejendomsGruppeManager.getGruppeStoerelser() == -2147483647);
    }

}