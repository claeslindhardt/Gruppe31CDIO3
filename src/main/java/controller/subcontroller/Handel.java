package controller.subcontroller;

import controller.SpilController;
import view.UserInterface;
import model.Spil;
import model.Spiller;
import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;
import spillogik.EjendomsLogik;

import java.util.ArrayList;

/*Denne klasse er en subcontroller.
* Denne controller håndtere alle alle metoder der har med pengetransaktioner at gøre.
* */


public class Handel {






    /**
     * @author Malte
     * Metode der koeber et hus på en ejendom for spilleren.
     * Dette inkluderer at bygge huset paa ejendom (ejendom.bygHuse),
     * og trække penge fra spilleren.
     *
     * @param ejendom: hvilken ejendom man vil bygge et hus paa.
     */
    public void koebHus(Spiller spiller, Ejendom ejendom, UserInterface userInterface){
        if( EjendomsLogik.kanKoebeHus( spiller, ejendom, ejendom.getGruppe()) ){
            ejendom.bygHuse(1);

            ejendom.setLeje(EjendomsLogik.beregnLejeTotal(ejendom, spiller.ejerEjendomsGruppe( ejendom.getGruppe() )));
            spiller.addPenge(-ejendom.getHusPris());

        }
    }

    public void saelgHus(Spiller spiller, Ejendom ejendom, UserInterface ui) {
        if (EjendomsLogik.kanSaelgeHus(spiller, ejendom, ejendom.getGruppe())){

            ejendom.saelgHus(1);

            spiller.addPenge(EjendomsLogik.beregnSalgsPrisHus(ejendom, 1));

            ui.solgtPaaEjendom(ejendom, spiller);
        }
    }



    public void saelgHotel(Spiller spiller, Ejendom ejendom){

        spiller.addPenge(EjendomsLogik.beregnSalgsPrisHus(ejendom,1));
        ejendom.saelgHotel(false);
        ejendom.setAntalHuse(4);
    }



    /**
     * @author Malte
     * FORLØBET i at købe et hus på en ejendom. Dvs. den der sørger beder UI
     * om at vise ting og tage i mod inputs.
     * @param ui: hvilket UserInterface der skal bruges.
     */
    public void koebHusPaaEjendom(Spiller spiller, UserInterface ui){
        Ejendom[] ejendomme = spiller.getEjendommeArray();

        if( ejendomme.length > 0 ){
            ArrayList<Ejendom> bebyggeligeEjendomme = new ArrayList<Ejendom>();

            /* Finder bebyggelige ejendomme og flytter dem over i en seperat liste.
               Se kanKoebeHus() for at se, hvordan det vurderes om spilleren kan
               bygge et hus paa en ejendom.
             */
            for(int i = 0; i < ejendomme.length; i++){
                if( EjendomsLogik.kanKoebeHus(spiller, ejendomme[i], ejendomme[i].getGruppe()) ){
                    bebyggeligeEjendomme.add(ejendomme[i]);
                }
            }

            if(bebyggeligeEjendomme.size() > 0){

                int ejendomsIndex = ui.vaelgEjendom(bebyggeligeEjendomme);

                if( ejendomsIndex < bebyggeligeEjendomme.size() ){
                    koebHus( spiller,  bebyggeligeEjendomme.get(ejendomsIndex), ui );
                    ui.byggetPaaEjendom( bebyggeligeEjendomme.get(ejendomsIndex), spiller );
                }


            }else {
                ui.kanIkkeKoebeHus(); }

        }else{
            ui.kanIkkeKoebeHus();
        }
    }

    /**
     * @auther Andreas
     * Metoder der sælger et hus på en ejendom.
     * Metoden taler først sammen med en metoden opretEjendomskartotek, hvor der laves en ArrayList med alle de ejendomme der
     * har et hus på sig.
     * Så vises listen i UI.
     * Og man vælger den ejendom, hvorfra der skal sælges et hus.
     * @param spiller
     * @param ui
     */
    public void saelgHusPaaEjendom( Spiller spiller, UserInterface ui ){

        // Finder ejendomme med sælgbare huse på
        ArrayList<Ejendom> kartotek = opretEjendomsKartotek(spiller);

        // Tjekker at der overhovedet er nogen huse man kan bygge på
        if( kartotek.size() > 0 ) {

            int ejendomsIndex = ui.vaelgEjendom(kartotek);

            if( ejendomsIndex < kartotek.size() ){
                saelgHus(spiller,  kartotek.get(ejendomsIndex), ui);
                ui.solgtPaaEjendom( kartotek.get(ejendomsIndex), spiller );
            }
        }else{
            ui.kanIkkeSaelgeHus();
        }
    }

    /**
     * @auther Andreas
     * Metoder der sælger et hotel på en ejendom.
     * Metoden taler først sammen med en metoden opretHotlekartotek, hvor der laves en ArrayList med alle de ejendomme der
     * har et hotel på sig.
     * Så vises listen i UI.
     * Og man vælger den ejendom, hvorfra der skal sælges et hus.
     * @param spiller
     * @param ui
     */
    public void saelgHotelPaaEjendom(SpilController spil, Spiller spiller, UserInterface ui ){

        ArrayList<Ejendom> kartotek = opretHotelKartotek(spiller);

        if(kartotek.size() > 0){
            int ejendomsIndex = ui.vaelgEjendom(kartotek);

            if( ejendomsIndex < kartotek.size() ) {
                saelgHotel(spiller, kartotek.get(ejendomsIndex) );
                ui.solgtPaaEjendom(kartotek.get(ejendomsIndex), spiller );
            }
        }else{
            ui.kanIkkeSaelgeHotel();
        }

    }

    /**
     * @author Chua
     * Metode der koeber et hotel på en ejendom for spilleren.
     * Dette inkluderer at bygge hotel paa ejendom (ejendom.bygHotel),
     * og trække penge fra spilleren.
     * @param spiller
     * @param ejendom
     * @param userInterface
     */
    public void koebHotel(Spiller spiller, Ejendom ejendom, UserInterface userInterface){
        if( EjendomsLogik.kanKoebeHotel( spiller, ejendom, ejendom.getGruppe()) ){
            ejendom.bygHotel(true);
            ejendom.setAntalHuse(0);

            ejendom.setLeje(EjendomsLogik.beregnLejeTotal(ejendom, spiller.ejerEjendomsGruppe( ejendom.getGruppe() )));
            spiller.addPenge(-ejendom.getHotelPris());
        }
    }

    /**
     * @author Chua
     * FORLØBET i at købe et hotel på en ejendom. Dvs. den der beder UI
     * om at vise ting og tage imod inputs.
     * @param ui : hvilket UserInterface der skal bruges.
     */
    public void koebHotelPaaEjendom(Spiller spiller, UserInterface ui){
        Ejendom[] ejendomme = spiller.getEjendommeArray();

        if( ejendomme.length > 0 ){
            ArrayList<Ejendom> grundeMedMulighedForHotel = new ArrayList<Ejendom>();


            for(int i = 0; i < ejendomme.length; i++){
                if( EjendomsLogik.kanKoebeHotel(spiller, ejendomme[i], ejendomme[i].getGruppe()) ){
                    grundeMedMulighedForHotel.add(ejendomme[i]);
                }
            }

            if(grundeMedMulighedForHotel.size() > 0){

                int ejendomsIndex = ui.vaelgEjendom( grundeMedMulighedForHotel );
                if( ejendomsIndex < grundeMedMulighedForHotel.size() ){
                    koebHotel( spiller,  grundeMedMulighedForHotel.get(ejendomsIndex), ui );

                    ui.byggetPaaEjendom( grundeMedMulighedForHotel.get(ejendomsIndex), spiller );
                }

            }else {
                ui.kanIkkeKoebeHotel();
            }

        }else{
            ui.kanIkkeKoebeHotel();
        }
    }


    /**
     * Metoden der samler alle de ejendomme som en Spiller ejer, hvor
     * der kan sælges et hus fra. Det er {@link EjendomsLogik#kanSaelgeHus}
     * som vurderer hvorvidt der kan sælge et hus på ejendommen.
     *
     * @author Andreas
     * @param spiller Hvilken spiller man ønsker et kartotek for
     * @return Liste over de ejendomme, der kan sælges et hus fra
     */
    private ArrayList<Ejendom> opretEjendomsKartotek( Spiller spiller ){

        Ejendom[] ejendomme = spiller.getEjendommeArray();

        ArrayList<Ejendom> kartotek = new ArrayList<>();

        for( int i = 0; i < ejendomme.length; i++ ){
            if( EjendomsLogik.kanSaelgeHus( spiller, ejendomme[i], ejendomme[i].getGruppe() ) ){
                kartotek.add( ejendomme[i] );
            }
        }
        return kartotek;
    }


    private ArrayList<Ejendom> opretHotelKartotek(Spiller spiller){

        Ejendom[] ejendomme = spiller.getEjendommeArray();

        ArrayList<Ejendom> kartotek = new ArrayList<>();

        for( int j = 0; j < ejendomme.length; j++ ){
            if( ejendomme[j].harHotel() ){
                kartotek.add( ejendomme[j] );
            }
        }
        return kartotek;
    }

}
