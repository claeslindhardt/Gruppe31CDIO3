package view.GUI;

import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;
import model.Spiller;
import model.chancekort.Chancekort;
import model.felter.Felt;
import model.felter.ejeligefelter.EjeligtFelt;
import model.raflebaeger.RafleBaeger;

import java.awt.*;

public class GUI_Extension {

    private GUI gui;
    private GUI_Field[] gui_felter;
    private GUI_Player[] gui_spillere;



    public void indtastTekst(){

    }

    public void indtastTal(){

    }

    public void visBesked(){

    }

    public void addSpiller( Spiller spiller ){

    }


    public void visChancekort( Chancekort chancekort ){

    }


    public void vaelgKnap(){


    }

    public void vaelgListe(){

    }

    public void binaertValg(){

    }

    public void vaelgJaNej( ){

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
            GUI_Player gui_spiller = gui_spillere[i];

            fjernBil( gui_spiller );

            if( spiller.erUdgaaet() ){
                gui_spiller.setName( spiller.getNavn() + " (Udgaaet)");
                gui_spiller.setBalance(0);

                for( EjeligtFelt felt : spiller.getEjedeFelter() ){
                    opdaterFeltEjer( felt, null );
                }
            } else {
                gui_felter[ spiller.getSpillerPosition() ].setCar( gui_spiller, true );
            }
        }
    }


    public void opdaterFeltEjer( Felt felt, Spiller ejer ){
        GUI_Ownable gui_felt = (GUI_Ownable) gui_felter[felt.getPlacering()];

        if( ejer == null ){
            gui_felt.setBorder( Color.black );
        }else{
            gui_felt.setBorder( gui_spillere[ejer.getId()].getPrimaryColor() );
        }
    }

    public void opdaterTerninger( RafleBaeger rafleBaeger ){


    }



}
