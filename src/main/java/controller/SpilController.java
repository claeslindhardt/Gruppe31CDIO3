package controller;

import controller.subcontroller.*;
import model.Spiller;
import model.chancekort.Chancekort;
import model.felter.ejeligefelter.Ejendom;
import model.singletoner.RandomSingleton;
import spillogik.VinderLogik;
import view.GUI.GraphicalUserInterface;
import view.UserInterface;
import model.Spil;
import spillogik.spilgenerering.SpilGenerator;

import static spillogik.spilgenerering.SpilGenerator.genererSpil;
import static spillogik.VinderLogik.getVinder;
import static spillogik.VinderLogik.vinderFindes;

/**
 * Controlleren der styrer spillet ved at sætte det i gang og slutte det,
 * samt styre hovedmenuen, og give ture til spillerne.
 */
public class SpilController{

    private UserInterface ui; // Den UI, som SpilControlleren bruger
    private Spil spil;

    // Controllere
    private RykSpiller rykSpiller  = new RykSpiller();
    private Handlinger handlinger  = new Handlinger();
    private HandelHotel handelHotel = new HandelHotel();
    private HandelHus handelHus = new HandelHus();
    private LandPaaFelt landPaaFelt = new LandPaaFelt();
    private BrugChancekort brugChancekort = new BrugChancekort();


    /** Laver en ny SpilController med en GUI */
    public SpilController(){
        ui = new GraphicalUserInterface();
        spil = genererSpil();
    }


    public LandPaaFelt getLandPaaFelt() {
        return landPaaFelt;
    }

    public BrugChancekort getBrugChancekort(){ return brugChancekort; }

    public Spil getSpil(){return spil;}

    public void setSpil(Spil spil){
        this.spil = spil;
    }

    public RykSpiller getRykSpiller() {
        return rykSpiller;
    }


    /**
     * Metoden der starter spillet, som SpilControllerne er indstillet til.
     */
    public void koerSpil(){

        ui.aabenSpil( spil );

        // Tjekker om spillerne er blevet lavet, ellers laves de
        if( spil.getSpillere() == null ){
            String[] navne = ui.opretSpillere( 2, 6 );
            spil.setSpillere( SpilGenerator.genererSpillere( navne ) );
        }

        spil.setSpillerTur( RandomSingleton.getInstance().nextInt( spil.getAntalSpillere() ) + 1 );
        ui.startSpil( spil );

        // Primære Spil loop
        do{
            Spiller spillerMedTur = spil.getSpillerMedTur();

            startSpillerTur( spillerMedTur );

            slutSpillerTur( spillerMedTur );

        }while( !vinderFindes( spil ) );

        ui.spilletErSlut( getVinder( spil ) );
    }


    /**
     * Giver spilleren turen. Det er loopet der "controller"
     * spillerens tur, og vurdere om turen skal slutte
     * eller fortsaette.
     */
    private void startSpillerTur( Spiller spiller ){

        // Tjekker om spilleren skal anke dom (slaa for at komme ud af faengsel)
        if( spiller.erIFaengsel() ){
            handlinger.ankerDom( spiller, spil.getRaflebaeger(), ui );
        }

        // Koerer turen
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
    private boolean turMenu( Spiller spiller ) {
        int valg = ui.turMenu( spiller, 1, 8 );

        boolean slutTur = false;

        switch( valg ){

            case 0: // Slaa med Terninger
                if ( !spiller.erIFaengsel() ) {
                    rykSpiller.kastTerninger(spil, spil.getSpillerMedTur(), ui, this);

                } else{
                    ui.kanIkkeSlaaFaengsel();

                }
                break;

            case 1: // Slutter turen
                slutTur = true;
                break;

            case 2: // Viser chancekort paa handen
                handlinger.chanceKortMuligheder( spiller, this, ui);
                break;

            case 3: // Giver op
                if( handlinger.givOp( ui ) ){
                    slutTur = true;
                    spillerUdgaar( spiller );
                    ui.harGivetOp( spiller );
                }
                break;

            case 4: // Koeber hus
                handelHus.koebHusForloeb( spiller, ui );
                break;

            case 5: // Koeb Hotel
                handelHotel.koebHotelForloeb( spiller, ui );
                break;

            case 6: // Saelg hus
                handelHus.saelgHusForloeb( spiller, ui );
                break;

            case 7: // Saelg hotel
                handelHotel.saelgHotelForloeb( spiller, ui );
                break;
        }

        return slutTur;
    }


    /**
     * Slutter spillerens tur, og vaelger den naeste spiller.
     */
    private void slutSpillerTur( Spiller spiller ) {

        spiller.setHarSlaaet(false);

        // Tjekker for bankerot
        if( VinderLogik.erBankerot(spiller) ){
            spillerUdgaar( spiller );
            ui.spillerErBankerot( spiller );
        }

        // Finder naeste spiller
        do {
            if (spil.getSpillerTur() >= spil.getAntalSpillere()) {
                spil.setSpillerTur(1);
            } else if (spil.getSpillerTur() <= spil.getAntalSpillere()) {
                spil.setSpillerTur(spil.getSpillerTur() + 1);
            }
        }while( spil.getSpillerMedTur().erUdgaaet() );
    }



    /**
     * Registrerer at spilleren udgaar fra spillet (dvs.
     * enten er gaaet bankerot eller har givet op).
     * Dette resetter alle spillerens ejendomme, samt
     * chancekort.
     */
    private void spillerUdgaar( Spiller spiller ){
        spiller.setErUdgaaet(true);

        // Rydder chancekort
        for( Chancekort chancekort : spiller.getChancekort() ){
            spil.addChancekort( chancekort );
        }
        spiller.clearChancekort();

        // Rydder ejendomme
        for( Ejendom ejendom : spiller.getEjendommeArray() ){
            ejendom.setEjer(null);
            ejendom.setHarHotel(false);
            ejendom.setAntalHuse(0);
        }
    }
















}
