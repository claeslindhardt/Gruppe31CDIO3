package controller;

import view.GUI.GUIinterface;
import view.UserInterfaceKontrakt;
import model.Spil;
import spillogik.SpilGenerator;

import java.util.Random;

public class SpilController{

    private UserInterfaceKontrakt ui; // Den UI, som SpilControlleren bruger
    private Spil spil;


    // Controlllere
    private RykSpiller  rykSpiller  = new RykSpiller();
    private Handlinger  handlinger  = new Handlinger();
    private Handel      handel      = new Handel();
    private LandPaaFelt landPaaFelt = new LandPaaFelt();
    private BrugChancekort brugChancekort = new BrugChancekort();


    public LandPaaFelt getLandPaaFelt() {
        return landPaaFelt;
    }

    public BrugChancekort getBrugChancekort(){ return brugChancekort; }

    public Handel getHandel() {
        return handel;
    }

    public controller.KoebFelt getKoebFelt() {
        return KoebFelt;
    }

    private KoebFelt    KoebFelt    = new KoebFelt();



    public Spil getSpil(){return spil;}

    public void setSpil(Spil spil){
        this.spil = spil;
    }

    public RykSpiller getRykSpiller() {
        return rykSpiller;
    }

    public Handlinger getHandlinger(){ return handlinger;  }


    /** Laver en ny SpilController med en GUI */
    public SpilController(){
        ui = new GUIinterface();
        spil = new Spil();
    }



    /**
     * @author Filip
     * Metode, der afgør om en faengslet spiller løslades eller skal blive i faengsel.
     */
    public void anketDomsigelse() {
        Random ra = new Random();
        int domsAfsigelseDel1 = ra.nextInt(5) + 1;
        int domsAfsigelseDel2 = ra.nextInt(5) + 1;
        ui.retsTerninger(domsAfsigelseDel1, domsAfsigelseDel2);
        if (domsAfsigelseDel1 == domsAfsigelseDel2) {
            ui.heldIRetten();
            spil.getSpillerMedTur().setFaengselsStraf(false);
            //Har udkemmenteret denne da jeg ikke syntes at det giver mening at have den.
            //getSpillerMedTur().setSpillerPosition(domsAfsigelseDel1 + domsAfsigelseDel2);
        } else {
            spil.getSpillerMedTur().setFaengselsStraf(true);
            ui.ingenHeldIRetten();
        }

    }

    /**
     * Indsæt beskrivelse her
     */
    public void slutSpillerTur() {
        spil.getSpillerMedTur().setHarSlaaetForTuren(false);
        spil.getSpillerMedTur().setHarAnketDomDenneRunde(false);
        tjekForBankeRaadt();

        if ( spil.getSpillerTur() >= spil.getAntalSpillere() ) {
            spil.setSpillerTur(1);

        } else if (spil.getSpillerTur() <= spil.getAntalSpillere()) {
            spil.setSpillerTur(spil.getSpillerTur() + 1);
        }


    }

    /**
     * Indsæt beskrivelse her
     */
    public void tjekForBankeRaadt() {

        if (spil.getSpillerMedTur().getPenge() < 0) {
            ui.bankeRaadtGrundetLikviditet(spil.getBankeraadGraense());
            spil.getSpillerMedTur().setHarGivetOp(true);
            spil.getSpillerMedTur().getSpillerEjendomme().clear();
            int udgaaetSpiller = spil.getSpillerMedTur().getId() + 1;
            ui.spillerUdgår(udgaaetSpiller);
        }

    }


    //_____________________________________
    //Tjekkere:

    /**
     * Indsæt beskrivelse her
     */
    public void tjekForFeangselsStraf(){
        if (spil.getSpillerMedTur().isFaengselsStraf()) {
            if (!spil.getSpillerMedTur().isHarAnketDomDenneRunde()) {
                ui.anketStraffeDom(spil.getSpillerTur());
                anketDomsigelse();
                spil.getSpillerMedTur().setHarAnketDomDenneRunde(true);
            }

        }
    }

    /**
     * Indsæt beskrivelse her
     */
    public void tjekOmGivetOp() {
        if (spil.getSpillerMedTur().isHarGivetOp()) {
            if (spil.getSpillerTur() == spil.getAntalSpillere()) {
                spil.setSpillerTur(1);
            } else {
                spil.setSpillerTur(spil.getSpillerTur() + 1);
            }
        }
    }

    /**
     * Indsæt beskrivelse her
     */
    public void tjekForVinder() {
        if (spil.getAntalSpillere() - tjekAntalSpillereISpil() == 1) {
            //SpillerCO spillerMedTur = spillerObjekter.get(spillerTur - 1);
            if (!spil.getSpillerMedTur().isHarGivetOp()) {
                //Der ligger en til for at da det er den spiller i rækken, der ligger forud for vinderen, der giver op.
                spil.setVinder(spil.getSpillerMedTur().getId() + 1);
                spil.setVinderFindes(true);
                ui.vinder(spil.getSpillerMedTur().getId() + 1);
            }
        }
    }

    /**
     * Indsæt beskrivelse her
     * @return
     */
    public int tjekAntalSpillereISpil() {
        int UdgaetSpillere = 0;
        for (int i = 0; i < spil.getSpillereArrayList().size(); i++) {
            if (spil.getSpillereArrayList().get(i).isHarGivetOp()) {
                UdgaetSpillere++;
            }
        }

        return UdgaetSpillere;
    }


    /**
     * @author Filip
     * Gør det muligt for spillerne at vælge de forskellige funktioner i turmenuen og
     * sørger for at tilhørende metoder udføres
     */
    public void turMenu() {
        int input = ui.TurMenu(spil.getSpillerTur(), 1, 12);

        switch (input) {
            case 1:

                if (!spil.getSpillerMedTur().isFaengselsStraf()) {
                    rykSpiller.kastTerninger(spil, spil.getSpillerMedTur(), ui, this);

                } else if (spil.getSpillerMedTur().isFaengselsStraf()) {
                    ui.kanIkkeSlaaFaengsel();
                }

                break;
            case 2:
                slutSpillerTur();
                break;
            case 3:
                handlinger.chanceKortMuligheder(spil.getSpillerMedTur(), this, ui);
                break;
            case 4:
                ui.spillerEjendele(spil.getSpillerMedTur());
                break;
            case 5:
                /*spilleBret.printBret(ui);*/
                break;
            case 6:
                printSpilleresInfo();
                break;
            case 7:
                handlinger.givOp(spil.getSpillerMedTur(), this, ui);
                break;
            case 8:
                handel.koebHusPaaEjendom(spil.getSpillerMedTur(), ui);
                break;
            case 9:
                handel.koebHotelPaaEjendom(spil.getSpillerMedTur(), ui);
                break;
            case 10:
                //spil.getSpillerMedTur().handelMedEjendomme();
                break;
            case 11: handel.saelgHusPaaEjendom( spil.getSpillerMedTur(), ui );
                break;
            case 12: handel.saelgHotelPaaEjendom(this,spil.getSpillerMedTur(), ui);
                break;
            default:

        }

    }


    public void koerSpil(){

        ui.aabenSpil( spil );

        // Tjekker om spillerne er blevet lavet, ellers laves de
        if( spil.getSpillere() == null ){
            String[] navne = ui.opretSpillere( 2, 6);
            spil.setSpillere( SpilGenerator.genererSpillere( navne) );
        }

        ui.startSpil( spil );

        do{
            tjekForVinder();
            tjekOmGivetOp();
            tjekForFeangselsStraf();

            if( !spil.getVinderFindes() ){
                turMenu();

            }else{
                break;
            }

        }while( true );


        ui.spilletErSlut();



    }





}
