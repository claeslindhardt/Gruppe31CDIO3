package ModelEnteties.Spiller;

import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.felter.EjendomCO;
import org.junit.jupiter.api.Test;
import spillogik.EjendomsLogik;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FordelingAfHuseTest {



    @Test
    void testFordelingAfHuse(){
        /*  Tester om metoden huseErFordeltIGruppe virker som den skal.
            Laver 3 ejendomme, og tilfoejer dem til en gruppe.
            Øger antallet af huse på hver ejendom gradvist.
         */

        // Opretter ejendomme
        EjendomCO ejendom1 = new EjendomCO("Ejendom1", 100, 100, 0);
        EjendomCO ejendom2 = new EjendomCO("Ejendom2", 100, 100, 0);
        EjendomCO ejendom3 = new EjendomCO("Ejendom3", 100, 100, 0);

        // Opretter Ejendomsgruppe
        EjendomsGruppeDTO ejendomsGruppe = new EjendomsGruppeDTO(Color.BLUE, 4);

        ejendomsGruppe.tilfoejEjendom(ejendom1);
        ejendomsGruppe.tilfoejEjendom(ejendom2);
        ejendomsGruppe.tilfoejEjendom(ejendom3);

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


    }

}
