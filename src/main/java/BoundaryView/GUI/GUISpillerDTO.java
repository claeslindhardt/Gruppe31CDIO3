package BoundaryView.GUI;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class GUISpillerDTO {
    //----------- Variabler: -------------------
    private GUI_Car bil;
    private GUI_Player deltager;

    //---------Getters og setters: -------------
    public GUI_Car getBil() {
        return bil;
    }

    public void setBil(GUI_Car bil) {
        this.bil = bil;
    }

    public GUI_Player getDeltager() {
        return deltager;
    }

    public void setDeltager(GUI_Player deltager) {
        this.deltager = deltager;
    }

    //---------Constructor: -------------
    public GUISpillerDTO(GUI_Car automobil, GUI_Player deltager){
        this.bil = automobil;
        this.deltager= deltager;
    }
}
