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

import java.awt.*;
import java.util.Random;

public class GUI_Extension {

    private GUI gui;
    private GUI_Field[] gui_felter;
    private GUI_Player[] gui_spillere;



    public GUI_Extension( GUI gui ){
        this.gui = gui;
        this.gui_felter = gui.getFields();
    }

    public void setSpillere( GUI_Player[] gui_spillere ){
        this.gui_spillere = gui_spillere;

        for( GUI_Player gui_spiller : gui_spillere ){
            gui.addPlayer( gui_spiller );
        }
    }


    public String indtastTekst( String besked ) {

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


    public int indtastTal( String besked, int min, int max ) {
        return gui.getUserInteger( besked, min, max);
    }

    public void visTekst( String besked ){
        gui.showMessage( besked );
    }


    public void visChancekort( Chancekort chancekort ){
        GUI_Center.getInstance().setChanceCard( chancekort.getBeskrivelse() );
        GUI_Center.getInstance().displayChanceCard();
    }


    public int vaelgKnap( String besked, String ... valgMuligheder ){
        String tekstValg = gui.getUserButtonPressed( besked, valgMuligheder );
        for( int i = 0; i < valgMuligheder.length; i++ ){
            if( tekstValg == valgMuligheder[i] ){
                return i;
            }
        }
        return -1;
    }


    public int vaelgListe( String besked, String ... valgMuligheder ){

        String valg = gui.getUserSelection( besked, valgMuligheder );

        for( int i = 0; i < valgMuligheder.length; i++ ){
            if( valg == valgMuligheder[i] ){
                return i;
            }
        }

        return -1;
    }


    public int binaertValg( String besked, String valg1, String valg2 ){
        String valg = gui.getUserButtonPressed( besked,
                valg1, valg2);

        if( valg.equals(valg1) ){
            return 0;
        }else {
            return 1;
        }
    }

    public int vaelgJaNej( String besked ){
        return binaertValg(besked, "Ja", "Nej");
    }

    private void fjernBil( GUI_Player spiller ){
        for( GUI_Field felt : gui_felter ){

            if( felt.hasCar(spiller) ){

                boolean[] harBil = new boolean[ gui_spillere.length];

                for(int i=0; i <  gui_spillere.length; i++){
                    harBil[i] = felt.hasCar( gui_spillere[i]);
                }

                felt.removeAllCars();

                for( int i=0; i< gui_spillere.length; i++){
                    if( harBil[i] &&  gui_spillere[i] != spiller ){
                        felt.setCar( gui_spillere[i], true);
                    }
                }
            }
        }
    }


    public void opdaterSpillere( Spiller ... spillere ){

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


    public void setFeltEjer(EjeligtFelt felt, Spiller ejer ){
        GUI_Ownable gui_felt = (GUI_Ownable) gui_felter[felt.getPlacering()];

        if( ejer == null ){
            gui_felt.setBorder(Color.black);
        }else{
            gui_felt.setBorder( gui_spillere[ejer.getId()].getPrimaryColor() );
        }
    }


    public void opdaterFelt( EjeligtFelt felt ){

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

    public void opdaterTerninger( RafleBaeger rafleBaeger ){
        //lav dette til et forloop hvis vi finder en måde at display mere end to terninger på.
        int terning1= rafleBaeger.getTerning(0).getVaerdi();
        int terning2= rafleBaeger.getTerning(1).getVaerdi();

        Random rand = new Random();

        //Angiver, position af terningerne (Grid system - Andreas)
        int x1 = rand.nextInt(6)+1;
        int y1 = rand.nextInt(2)+7;
        int x2 = rand.nextInt(6)+1;
        int y2 = rand.nextInt(2)+7;

        gui.setDice(terning1,x1,y1,terning2,x2,y2);
    }

}
