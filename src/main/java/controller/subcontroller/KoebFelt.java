package controller.subcontroller;

import view.UserInterface;
import model.Spiller;
import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;

public class KoebFelt {


    public void koebFelt(EjeligtFelt felt, Spiller spiller, UserInterface ui ){

        if (spiller.getPenge() >  felt.getPris()) {

            spiller.addPenge( -  felt.getPris() );
            felt.setEjer(spiller);
            spiller.addEjeligtFelt( felt );

            ui.gennemfoertKoeb(  felt, spiller);

        } else {
            ui.manglerPenge();
        }
    }

}
