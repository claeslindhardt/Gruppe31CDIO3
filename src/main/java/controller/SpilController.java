package controller;

import controller.subcontroller.*;
import model.Spiller;
import model.chancekort.Chancekort;
import model.felter.ejeligefelter.Ejendom;
import model.singletoner.RandomSingleton;
import view.GUI.GraphicalUserInterface;
import view.UserInterface;
import model.Spil;
import spillogik.SpilGenerator;

import static spillogik.SpilGenerator.genererSpil;
import static spillogik.VinderLogik.getVinder;
import static spillogik.VinderLogik.vinderFindes;

public class SpilController{

    private UserInterface ui; // Den UI, som SpilControlleren bruger
    private Spil spil;


    // Controlllere
    private RykSpiller rykSpiller  = new RykSpiller();
    private Handlinger handlinger  = new Handlinger();
    private Handel handel      = new Handel();
    private LandPaaFelt landPaaFelt = new LandPaaFelt();
    private BrugChancekort brugChancekort = new BrugChancekort();


    public LandPaaFelt getLandPaaFelt() {
        return landPaaFelt;
    }

    public BrugChancekort getBrugChancekort(){ return brugChancekort; }

    public Handel getHandel() {
        return handel;
    }

    public controller.subcontroller.KoebFelt getKoebFelt() {
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
        ui = new GraphicalUserInterface();
        spil = genererSpil();
    }


    public void koerSpil(){


        ui.aabenSpil( spil );

        // Tjekker om spillerne er blevet lavet, ellers laves de
        if( spil.getSpillere() == null ){
            String[] navne = ui.opretSpillere( 2, 6);
            spil.setSpillere( SpilGenerator.genererSpillere( navne) );
        }

        spil.setSpillerTur( RandomSingleton.getInstance().nextInt( spil.getAntalSpillere() ) + 1 );
        ui.startSpil( spil );

        do{
            Spiller spillerMedTur = spil.getSpillerMedTur();

            startSpillerTur( spillerMedTur );

            slutSpillerTur( spillerMedTur );

        }while( !vinderFindes( spil ) );

        ui.spilletErSlut( getVinder( spil ) );
    }


    /**
     * Indsæt beskrivelse her
     */
    public void slutSpillerTur( Spiller spiller ) {

        spiller.setHarSlaaetForTuren(false);

        if( spiller.getPenge() < 0  ){
            spillerUdgaar( spiller );
            ui.spillerErBankerot( spiller );
        }

        do {

            if (spil.getSpillerTur() >= spil.getAntalSpillere()) {
                spil.setSpillerTur(1);

            } else if (spil.getSpillerTur() <= spil.getAntalSpillere()) {
                spil.setSpillerTur(spil.getSpillerTur() + 1);
            }
        }while( spil.getSpillerMedTur().erUdgaaet() );
    }


    public void spillerUdgaar( Spiller spiller ){
        spiller.setErUdgaaet( true );

        for( Chancekort chancekort : spiller.getChancekort() ){
            spil.addChancekort( chancekort );
        }
        spiller.clearChancekort();

        for( Ejendom ejendom : spiller.getEjendomme() ){
            ejendom.setEjer(null);
        }
        spiller.clearEjendomme();

        spiller.setErUdgaaet( true );
    }



    public void startSpillerTur( Spiller spiller ){


        if( spiller.erIFaengsel() ){
            handlinger.ankerDom( spiller, spil.getRaflebaeger(), ui );
        }

        boolean turErSlut;

        do{
            turErSlut = turMenu( spiller );
        }while( !turErSlut );

    }



    /**
     * @author Filip
     * Gør det muligt for spillerne at vælge de forskellige funktioner i turmenuen og
     * sørger for at tilhørende metoder udføres
     */
    public boolean turMenu( Spiller spiller ) {
        int valg = ui.turMenu( spiller, 1, 12 );

        boolean slutTur = false;

        switch( valg ){

            case 0:

                if ( !spiller.erIFaengsel() ) {
                    rykSpiller.kastTerninger(spil, spil.getSpillerMedTur(), ui, this);

                } else{
                    ui.kanIkkeSlaaFaengsel();

                }
                break;

            case 1:
                slutTur = true;
                break;

            case 2:
                handlinger.chanceKortMuligheder( spiller, this, ui);
                break;

            case 3:
                if( handlinger.givOp( ui ) ){
                    slutTur = true;
                    spillerUdgaar( spiller );
                    ui.harGivetOp( spiller );
                }
                break;

            case 4:
                handel.koebHusPaaEjendom( spiller, ui);
                break;

            case 5:
                handel.koebHotelPaaEjendom( spiller, ui);
                break;

            case 6: handel.saelgHusPaaEjendom( spiller, ui );
                break;

            case 7: handel.saelgHotelPaaEjendom(this, spiller, ui);
                break;
        }

        return slutTur;

    }








}
