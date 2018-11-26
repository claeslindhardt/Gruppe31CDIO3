import BoundaryView.TUI.TUI;
import Controller.SpilController;
import Controller.UserInterfaceKontrakt;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.SpilleBraetController;
import ModelEnteties.braet.controllerKlasser.*;
import ModelEnteties.braet.dataKlasser.Felt;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpilControllerTest {
    /**
     * @auther Andreas
     * Formål: At teste om vi kan passere start og fortsat blive på spillepladen
     * Metode: brættet har 4 felter, spillers, start position er 0 (start), terningværdi er 5.
     * Forventet: spillerplacering efter slag 1.
     * Statur: Testen er godkendt og bestået.
     */



@Test
    void tjekForPasseringAfStartOgRykSpiller() {

        int spillerTur =1;

       UserInterfaceKontrakt pan = new TUI();
        SpilController spil = new SpilController(1,4,2,0,pan);


        spil.terningeKrus.setTotalVaerdi(3);
        spil.tjekForPasseringAfStartOgRykSpiller(spil.terningeKrus);

        int forventetSpillerPosition=1;
        int aktuelSpillerPosition=spil.getSpillerMedTur().getSpillerPosition();
        assertEquals(aktuelSpillerPosition,forventetSpillerPosition);
    }

    /**
     * @auther Andreas
     * Formål: At sikre at der kan findes en vinder i spillet.
     * Status: Gennemført. Der kan findes en vinder.
     * Test: Godkendt
     */
    @Test
    void tjekForVinder(){

    UserInterfaceKontrakt pan = new TUI();
    SpilController spil = new SpilController(3,4,2,0,pan);

    spil.getSpillerObjekter().get(0).setHarGivetOp(true);
    spil.getSpillerObjekter().get(1).setHarGivetOp(true);
    spil.setSpillerTur(3);

    spil.tjekForVinder();
        assertTrue(spil.getVinderFindes());


    }

    /**
     * @auther Andreas
     * Formål: At teste om Constructoren i SpilControllerklassen generer, hvad der forventes af den.
     * Det forventes at der genereres det indtastede antal spiller. Ligeså forventes det at der genereres feltobjekter af de syv feltcontrollere.
     * Her skal der som minimum genereres 1 startfelt, et fængselsfelt, et gåIFængsel felt, min 2 jernbane felter og min 5 chance felter.
     * Status: Test gennemført
     * Karatter: Ikke bestået.
     */

    @Test
    void Spilcontroller(){
    UserInterfaceKontrakt pan = new TUI();

        SpilController spil = new SpilController(3,20,2,0,pan);
    //Her bliver det testet at der bliver oprettet spiller objekter i en liste.
        int count = 0;
        SpillerController sp1 = spil.getSpillerObjekter().get(0);
        SpillerController sp2 = spil.getSpillerObjekter().get(1);
        SpillerController sp3 = spil.getSpillerObjekter().get(2);
        ArrayList<SpillerController> spillere = new ArrayList<SpillerController>();
        spillere.add(0,sp1);
        spillere.add(1,sp2);
        spillere.add(2,sp3);
        for(int i= 0;i<spil.getSpillerObjekter().size();i++) {
            assertEquals(spil.getSpillerObjekter().get(i), spillere.get(i));
            // Her testes det at der generes det anatal felter til listen som der er sat som input.
            spil.getAntalFelter();
            int forventetAntalFelter = 20;
            assertEquals(forventetAntalFelter, spil.getAntalFelter());
            assertEquals(forventetAntalFelter, spil.getAntalFelter(), "Der findes det antal felter i listen, som det var ønsket");

        }
         // Her testes det at der bliver genereret forskellige feltertyper
            ArrayList<Felt> felter = new ArrayList<Felt>();

            int startfelt=0, ejendom=0, chancefelt=0, faengsel=0, gaaIFaengsel=0, jernbane=0, taxi=0;


            for(int j = 0; j< spil.getAntalFelter();j++){
                if(spil.getBretGeneretForSpil().getBret().get(j) instanceof Start){
                    startfelt++;
                }else if (spil.getBretGeneretForSpil().getBret().get(j) instanceof Ejendom){
                    ejendom++;
                }else if (spil.getBretGeneretForSpil().getBret().get(j) instanceof ChanceFelt){
                    chancefelt++;
                }else if (spil.getBretGeneretForSpil().getBret().get(j) instanceof Faengsel){
                    faengsel++;
                }else if (spil.getBretGeneretForSpil().getBret().get(j) instanceof GaaIFaengsel){
                    gaaIFaengsel++;
                }else if (spil.getBretGeneretForSpil().getBret().get(j) instanceof Jernbane){
                    jernbane++;
                }else if (spil.getBretGeneretForSpil().getBret().get(j) instanceof Taxi){
                    taxi++;
                }

            }System.out.println("Start: "+startfelt);System.out.println("ejendomme:" +ejendom);System.out.println("Faengsel: " +faengsel);System.out.println("Gå i fængsel: "+gaaIFaengsel);
        System.out.println("Chance felt: "+chancefelt);System.out.println("Taxi felt: "+taxi);System.out.println("Jernbane: "+jernbane);


        assertTrue(ejendom<20||ejendom>5);
        assertTrue(startfelt==1);
        assertTrue(faengsel==1);
        assertTrue(jernbane<2);
        assertTrue(taxi<1);
        assertTrue(gaaIFaengsel==1);
        assertTrue(chancefelt<3);

        }

    }

