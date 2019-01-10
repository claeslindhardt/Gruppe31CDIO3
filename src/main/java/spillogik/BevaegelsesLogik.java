package spillogik;

import Controller.SpillerCO;

public class BevaegelsesLogik {

    /** Private constructor sikrer at man ikke kan lave objekter af klassen. */
    private BevaegelsesLogik(){}


    public static boolean passererSpillerStart( int startFelt, int antalFelter, int totalAntalFelter){
        return antalGangeOverStart(startFelt, antalFelter, totalAntalFelter) > 0;
    }


    public static int antalGangeOverStart( int startFelt, int antalFelter, int totalAntalFelter){
        int gangeOverStart  = ( startFelt + antalFelter ) / totalAntalFelter;
        return gangeOverStart;
    }

    public static int passererStartPenge( int antalGangeOverStart ){
        return antalGangeOverStart * 200;
    }


    public static int beregnEndeligtFelt( int startFelt, int antalFelter, int totalAntalFelter ){
        int endeligtFelt = ( startFelt + antalFelter ) % totalAntalFelter;
        return endeligtFelt;
    }






}
