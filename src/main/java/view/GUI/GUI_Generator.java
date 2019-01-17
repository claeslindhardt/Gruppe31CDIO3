package view.GUI;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import model.Spiller;

import java.awt.*;

public class GUI_Generator {



    public static GUI_Player[] genererSpillere( Spiller[] spillere ){

        final Color[] SPILLERFARVER = { new Color(0,204,0), new Color(255,51,51), new Color(10,30,201),
                                        new Color(255,128,0), new Color(50,255,240), new Color(135,245,36), };

        GUI_Player[] gui_spillere = new GUI_Player[ spillere.length ];

        // Laver spilleres grafiske elementer
        for( int i=0; i < spillere.length; i++ ){

            GUI_Car bil = new GUI_Car();

            // Finder spiller farve
            Color spillerFarve = SPILLERFARVER[i % SPILLERFARVER.length];
            bil.setPrimaryColor(spillerFarve);

            GUI_Player gui_spiller = new GUI_Player( spillere[i].getNavn(), (int) spillere[i].getPenge(), bil );

            gui_spillere[i] = gui_spiller;

        }
        //Få Spiller objekterne til at rykke sig på planden når objekterne rykker sig


        return gui_spillere;
    }


}
