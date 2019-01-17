import ModelEnteties.EjendomsGruppe;
import ModelEnteties.felter.Ejendom;
import org.junit.jupiter.api.Test;
import spillogik.EjendomsLogik;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FordelingAfHuseTest {


    /**
     * Tester om metoden huseErFordeltIGruppe virker som den skal.
     * Laver 3 ejendomme, og tilfoejer dem til en gruppe.
     *             Øger antallet af huse på hver ejendom gradvist.
     */
    @Test
    void testFordelingAfHuse(){
        /*  Tester om metoden huseErFordeltIGruppe virker som den skal.
            Laver 3 ejendomme, og tilfoejer dem til en gruppe.
            Øger antallet af huse på hver ejendom gradvist.
         */

        // Opretter ejendomme
        Ejendom ejendom1 = new Ejendom("Ejendom1", 100, 100, 0);
        Ejendom ejendom2 = new Ejendom("Ejendom2", 100, 100, 0);
        Ejendom ejendom3 = new Ejendom("Ejendom3", 100, 100, 0);

        // Opretter Ejendomsgruppe
        EjendomsGruppe ejendomsGruppe = new EjendomsGruppe(Color.BLUE);

        ejendomsGruppe.tilfoejEjendom(ejendom1);
        ejendomsGruppe.tilfoejEjendom(ejendom2);
        ejendomsGruppe.tilfoejEjendom(ejendom3);

        ejendom1.setGruppe(ejendomsGruppe);
        ejendom2.setGruppe(ejendomsGruppe);
        ejendom3.setGruppe(ejendomsGruppe);

        // Test 1 -  Ingen huse på nogen ejendomme
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom1) );
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom2) );
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom3) );

        // Test 2 -  Ejendom1: 1 hus
        ejendom1.setAntalHuse(1);
        assertFalse( EjendomsLogik.huseErFordeltIGruppe(ejendom1) );
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom2) );
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom3) );

        // Test 3 - Ejendom1: 1 hus, Ejendom2: 1 hus
        ejendom2.setAntalHuse(1);
        assertFalse( EjendomsLogik.huseErFordeltIGruppe(ejendom1) );
        assertFalse( EjendomsLogik.huseErFordeltIGruppe(ejendom2) );
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom3) );

        // Test 4 - Alle ejendomme: 1 hus
        ejendom3.setAntalHuse(1);
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom1) );
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom2) );
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom3) );

        // HOTELLER -------

        // Test 5 - Man skal kunne bygge på ejendom1
        ejendom1.setAntalHuse(4);
        ejendom2.setAntalHuse(0);
        ejendom2.setHarHotel(true);
        ejendom3.setAntalHuse(0);
        ejendom2.setHarHotel(true);

        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom1) );


        // Test 6 - Man skal ikke kunne bygge på ejendom1
        ejendom1.setAntalHuse(4);
        ejendom2.setAntalHuse(3);
        ejendom2.setHarHotel(false);

        ejendom3.setAntalHuse(3);
        ejendom2.setHarHotel(false);

        assertFalse( EjendomsLogik.huseErFordeltIGruppe(ejendom1) );


        // Test 7 - Man skal ikke kunne bygge på alle ejendomme
        ejendom1.setAntalHuse(4);
        ejendom2.setHarHotel(false);

        ejendom2.setAntalHuse(4);
        ejendom2.setHarHotel(false);

        ejendom3.setAntalHuse(4);
        ejendom2.setHarHotel(false);

        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom1) );
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom1) );
        assertTrue( EjendomsLogik.huseErFordeltIGruppe(ejendom1) );


    }

}
