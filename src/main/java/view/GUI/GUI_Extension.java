package view.GUI;

import gui_codebehind.GUI_Center;
import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import model.Spiller;
import model.chancekort.Chancekort;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.raflebaeger.RafleBaeger;
import model.singletoner.RandomSingleton;

import java.awt.*;
import java.util.Random;


/**
 * Klasse der samler informationerne i GUI-objektet til noget mere
 * brugbart til dette projekt, samt udvider dets funktionalitet.
 * Denne bruges som "GUI" af GraphicalUserInterface i stedet
 * for den udleverede matadorgui.
 */
class GUI_Extension {


    private GUI gui;
    private GUI_Field[] gui_felter;
    private GUI_Player[] gui_spillere;


    GUI_Extension( GUI gui ){
        this.gui = gui;
        this.gui_felter = gui.getFields();
    }


    void setSpillere( GUI_Player[] gui_spillere ){
        this.gui_spillere = gui_spillere;

        for( GUI_Player gui_spiller : gui_spillere ){
            gui.addPlayer( gui_spiller );
        }
    }


    /**
     * Udvider den orignale GUI's tekstinput ved
     * sikre at man ikke kan indtaste en tom besked.
     */
    String indtastTekst( String besked ) {

        String input;
        do{

            input = gui.getUserString( besked );
            if( !input.isEmpty() ){
                break;
            }
            gui.showMessage("Hov, du indtastede vist ikke noget.");
        }while(true);
        return input;
    }



    int indtastTal( String besked, int min, int max ) {
        return gui.getUserInteger( besked, min, max);
    }


    void visTekst( String besked ){
        gui.showMessage( besked );
    }


    void visChancekort( Chancekort chancekort ){
        GUI_Center.getInstance().setChanceCard( chancekort.getBeskrivelse() );
        GUI_Center.getInstance().displayChanceCard();
    }


    /** Udvider funktionaliteten i den orginale GUI's knap mulighed
     *  ved at returnere et tal frem for teksten paa det felt der
     *  trykkets paa.
     */
    int vaelgKnap( String besked, String ... valgMuligheder ){
        String tekstValg = gui.getUserButtonPressed( besked, valgMuligheder );
        for( int i = 0; i < valgMuligheder.length; i++ ){
            if( tekstValg == valgMuligheder[i] ){
                return i;
            }
        }
        return -1;
    }


    /** Udvider funktionaliteten i den orginale GUI's liste mulighed
     *  ved at returnere et tal frem for teksten paa det felt der
     *  trykkets paa.
     */
    int vaelgListe( String besked, String ... valgMuligheder ){

        String valg = gui.getUserSelection( besked, valgMuligheder );

        for( int i = 0; i < valgMuligheder.length; i++ ){
            if( valg == valgMuligheder[i] ){
                return i;
            }
        }

        return -1;
    }


    /**
     * Udvider funktionaliteten i den orginale GUI's binare knap mulighed
     *  ved at returnere et tal frem for teksten paa det felt der
     *  trykkets paa.
     */
    int binaertValg( String besked, String valg1, String valg2 ){
        String valg = gui.getUserButtonPressed( besked,
                valg1, valg2);

        if( valg.equals(valg1) ){
            return 0;
        }else {
            return 1;
        }
    }


    /**
     * Stiller SPilleren et sporgsmaal med et ja/nej mulighed
     * som svar.
     *
     * @param besked    Beskeden der vises
     * @return 0 = Ja, 1 = Nej
     */
    int vaelgJaNej( String besked ){
        return binaertValg(besked, "Ja", "Nej");
    }


    /**
     * Fjerner en spillers bil fra braettet. Dette bruges
     * til at flytte spillerens bil, samt fjerne spillerens
     * bil naar spiller udgaar fra spillet.
     */
    private void fjernBil( GUI_Player spiller ){

        // Finder spillerens bil ved at gennemgaa alle felter
        for( GUI_Field felt : gui_felter ){

            if( felt.hasCar(spiller) ){

                boolean[] harBil = new boolean[ gui_spillere.length];

                // Tjekker hvilke spillere der har biler paa feltet
                for(int i=0; i <  gui_spillere.length; i++){
                    harBil[i] = felt.hasCar( gui_spillere[i]);
                }

                felt.removeAllCars();

                /* Tilfoejere alle andre spillers biler end den spiller
                    der er parametren i metoden */
                for( int i=0; i< gui_spillere.length; i++){
                    if( harBil[i] &&  gui_spillere[i] != spiller ){
                        felt.setCar( gui_spillere[i], true);
                    }
                }
            }
        }
    }


    /**
     * Opdater de grafiske spillere med udgangspunkt
     * i deres reelle spillerobjektet.
     *
     * @param spillere  Spillerne der skal opdateres.
     */
    void opdaterSpillere( Spiller ... spillere ){

        for( int i = 0; i < spillere.length; i++ ){
            Spiller spiller = spillere[i];
            GUI_Player gui_spiller = gui_spillere[ spiller.getId() ];

            fjernBil( gui_spiller );

            if( spiller.erUdgaaet() ){
                gui_spiller.setName( spiller.getNavn() + " (Udgaaet)");
                gui_spiller.setBalance(0);
                for( EjeligtFelt felt : spiller.getEjedeFelter() ){
                    opdaterFelt(felt);
                }

            } else {
                gui_felter[ spiller.getPosition() ].setCar( gui_spiller, true );
                gui_spiller.setBalance( (int) spiller.getPenge() );
            }
        }
    }


    /**
     * Saetter ejeren af et felt til at vaere en specifk spiller
     * frem for at vurdere det ud fra det reelles felt ejer
     *
     */
    private void setFeltEjer(EjeligtFelt felt, Spiller ejer ){
        GUI_Ownable gui_felt = (GUI_Ownable) gui_felter[felt.getPlacering()];

        if( ejer == null ){
            gui_felt.setBorder(Color.black);
        }else{
            gui_felt.setBorder( gui_spillere[ejer.getId()].getPrimaryColor() );
        }
    }


    /**
     * Opdaterer et felt ift. ejeren af feltet samt antallet af
     * huse og hoteller paa feltet.
     */
    void opdaterFelt( EjeligtFelt felt ){

        setFeltEjer( felt, felt.getEjer() );

        if( felt instanceof Ejendom){
            Ejendom ejendom = (Ejendom) felt;
            GUI_Street gui_felt = (GUI_Street) gui_felter[felt.getPlacering()];

            if( ejendom.harHotel() ){
                gui_felt.setHotel(true);
            }else{
                gui_felt.setHouses(ejendom.getAntalHuse());
            }
        }

    }


    /**
     * Opdaterer terningerne paa braettet med udgangspunkt i Raflebaegeret
     * der indeholder de reelle terninger.
     * Det sikres at terningerne ligger inden for et bestemt areal paa
     * braettet for ikke at overlappe med andre grafiske elementer.
     */
    void opdaterTerninger( RafleBaeger rafleBaeger ){

        int terning1 = rafleBaeger.getTerning(0).getVaerdi();
        int terning2 = rafleBaeger.getTerning(1).getVaerdi();

        RandomSingleton rand = RandomSingleton.getInstance();

        //Angiver, position af terningerne (Grid system - Andreas)
        int x1 = rand.nextInt(6) + 1;
        int y1 = rand.nextInt(2) + 7;
        int x2 = rand.nextInt(6) + 1;
        int y2 = rand.nextInt(2) + 7;

        gui.setDice(terning1,x1,y1,terning2,x2,y2);
    }

}
