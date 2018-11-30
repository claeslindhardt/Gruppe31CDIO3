package BoundaryView.GUI;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class GUISpillerDTO {
    //----------- Variabler: -------------------
    GUI_Car bil;
    GUI_Player deltager;

    //---------Getters og setters: -------------


    //---------Constructor: -------------
    public GUISpillerDTO(GUI_Car automobil, GUI_Player deltager){
        this.bil = automobil;
        this.deltager= deltager;
    }
}
