package ModelEnteties.Spiller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpillerDataTest {

    @Test
    void setgetPengeTest(){
        SpillerController spillerController = new SpillerController("Jacob",303,0);
        spillerController.setPenge(1000.0);
        assertTrue(spillerController.getPenge() == 1000.0);
    }
}