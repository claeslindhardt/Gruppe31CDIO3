package view.GUI;

import gui_fields.*;
import gui_main.GUI;
import model.Spil;
import model.Spiller;
import model.felter.Felt;
import model.felter.aktionsfelter.*;
import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;

import java.awt.*;

public class GUI_Generator {


    public static GUI genererGUI( Spil spil ){
        GUI gui = new GUI( genererFelter(spil.getFelter()), new Color(218,206,179));
        return gui;
    }


    public static GUI_Player[] genererSpillere( Spiller[] spillere ){

        final Color[] SPILLERFARVER = { new Color(0,204,0), new Color(255,51,51), new Color(10,30,201),
                                        new Color(255,128,0), new Color(50,255,240), new Color(135,245,36), };

        GUI_Player[] gui_spillere = new GUI_Player[ spillere.length ];

        for( int i=0; i < spillere.length; i++ ){

            GUI_Car bil = new GUI_Car();

            // Finder spiller farve
            Color spillerFarve = SPILLERFARVER[i % SPILLERFARVER.length];
            bil.setPrimaryColor( spillerFarve );

            GUI_Player gui_spiller = new GUI_Player( spillere[i].getNavn(), (int) spillere[i].getPenge(), bil );

            gui_spillere[i] = gui_spiller;

        }

        return gui_spillere;
    }


    public static GUI_Field[] genererFelter( Felt[] felter ){


        GUI_Field[] gui_felter = new GUI_Field[ felter.length ];

        for( int i = 0;  i < felter.length; i++) {

            GUI_Field gui_felt = null;

            Felt felt = felter[i];

            if (felt instanceof EjeligtFelt) {
                gui_felt = genererEjeligtFelt((EjeligtFelt) felt);

            } else if (felt instanceof StartFelt) {
                gui_felt = genererStartFelt((StartFelt) felt);

            } else if (felt instanceof ProevLykken) {
                gui_felt = new GUI_Chance();
                gui_felt.setDescription("Prøv lykken");

            } else if (felt instanceof FriParkering) {
                gui_felt = genererFriParkering();

            } else if (felt instanceof GaaIFaengsel) {
                gui_felt = genererGaaIFaengsel();
            } else if (felt instanceof Faengsel) {
                gui_felt = genererFaengsel();

            } else if ( felt instanceof IndkomstSkat || felt instanceof StatsSkat  ) {
                gui_felt = genererSkatteFelt( felt );
            }

            gui_felter[i] = gui_felt;
        }

        return gui_felter;
    }


    public static GUI_Ownable genererEjeligtFelt( EjeligtFelt ejeligtFelt ){

        GUI_Ownable gui_felt = null;

        if ( ejeligtFelt instanceof Ejendom ) {
            gui_felt = genererEjendom( (Ejendom) ejeligtFelt );


        } else if (ejeligtFelt instanceof Bryggeri) {
            gui_felt = genererBryggeri();

        } else if ( ejeligtFelt instanceof Rederi) {
            gui_felt = genererRederi( (Rederi) ejeligtFelt );
        }
        gui_felt.setTitle(ejeligtFelt.getNavn());
        gui_felt.setSubText("Pris: " + ejeligtFelt.getPris() + " kr.");

        return gui_felt;
    }

    public static GUI_Street genererEjendom( Ejendom ejendom ){

        GUI_Street gui_ejendom = new GUI_Street();

        gui_ejendom.setBackGroundColor( ejendom.getGruppe().getFarve() );
        gui_ejendom.setHouses( ejendom.getAntalHuse() );
        gui_ejendom.setDescription("Grundleje: " + ( ejendom).getLeje() + " / "
                + "Huspris: " + ( ejendom).getHusPris() + " / "
                + "Leje fra hus 1: " + ( ejendom).getLejeHus(1) + " / "
                + "Leje fra hus 2: " + ( ejendom).getLejeHus(2) + " / "
                + "Leje fra hus 3: " + ( ejendom).getLejeHus(3) + " / "
                + "Leje fra hus 4: " + ( ejendom).getLejeHus(4) + " / "
                + "Leje fra hotel: " + ( ejendom).getLejeHotel());

        return gui_ejendom;
    }

    public static GUI_Brewery genererBryggeri(){

        GUI_Brewery gui_bryggeri = new GUI_Brewery();

        gui_bryggeri.setDescription("Leje hvis 1 bryggeri ejes: 4 gange terningernes værdi" + " "
                                    + "Leje hvis 2 bryggerier ejes: 10 gange terningernes værdi");

        return gui_bryggeri;
    }

    public static GUI_Shipping genererRederi( Rederi rederi ){

        GUI_Shipping gui_rederi = new GUI_Shipping();
        gui_rederi.setBackGroundColor(Color.white);

        gui_rederi.setDescription("Grundleje: " + rederi.getLeje() + " / " +
                "Leje hvis 2 rederier ejes: " + rederi.getLeje() * 2 + " / " +
                "Leje hvis 3 rederier ejes: " + rederi.getLeje() * 2 * 2 + " / " +
                "Leje hvis 4 rederier ejes: " + rederi.getLeje() * 2 * 2 * 2);

        return gui_rederi;
    }

    public static GUI_Start genererStartFelt( StartFelt start  ){

        GUI_Start gui_start = new GUI_Start();
        gui_start.setTitle( start.getNavn() );
        gui_start.setSubText("");
        gui_start.setBackGroundColor(Color.white);
        gui_start.setDescription("Start");

        return gui_start;
    }


    public static GUI_Refuge genererFriParkering(){

        GUI_Refuge gui_friParkering = new GUI_Refuge();

        gui_friParkering.setBackGroundColor(Color.white);
        gui_friParkering.setSubText("Fri parkering");
        gui_friParkering.setDescription("Her er der helle");

        return gui_friParkering;
    }

    public static GUI_Tax genererSkatteFelt( Felt skatteFelt ){

        GUI_Tax gui_skatteFelt = new GUI_Tax();

        gui_skatteFelt.setTitle( skatteFelt.getNavn());
        gui_skatteFelt.setSubText("");

        if( skatteFelt instanceof StatsSkat ) {
            gui_skatteFelt.setDescription("Du skal betale 100 til almenvellet");
        }else {
            gui_skatteFelt.setDescription("Du skal betale 200 eller 10% af din formue");
        }

        return gui_skatteFelt;
    }

    public static GUI_Jail genererFaengsel() {

        GUI_Jail gui_faengsel = new GUI_Jail();
        gui_faengsel.setSubText("Faengsel");
        gui_faengsel.setDescription("Du er paa besoeg i faengslet.");
        return gui_faengsel;
    }

    public static GUI_Jail genererGaaIFaengsel() {
        GUI_Jail gui_faengsel = new GUI_Jail();
        gui_faengsel.setSubText("Gaa I faengsel");
        gui_faengsel.setDescription("I faengsel med dig!");
        return gui_faengsel;
    }

}
