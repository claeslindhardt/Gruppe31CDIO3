package controller.subcontroller;

import model.Spiller;
import model.felter.ejeligefelter.Ejendom;
import spillogik.EjendomsLogik;
import view.UserInterface;
import java.util.ArrayList;

import static spillogik.EjendomsLogik.beregnLejeTotal;




public class HandelHus {


    /**
     * @author Malte
     * FORLØBET i at købe et hus på en ejendom. Dvs. den der sørger beder UI
     * om at vise ting og tage i mod inputs.
     * @param ui: hvilket UserInterface der skal bruges.
     */
    public void koebHusForloeb(Spiller spiller, UserInterface ui ){
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
                    Ejendom valgtEjendom = bebyggeligeEjendomme.get( ejendomsIndex );
                    valgtEjendom.bygHuse(1);
                    valgtEjendom.setLeje( beregnLejeTotal(valgtEjendom, spiller.ejerEjendomsGruppe( valgtEjendom.getGruppe() )));
                    spiller.addPenge(-valgtEjendom.getHusPris());
                    ui.byggetPaaEjendom( bebyggeligeEjendomme.get(ejendomsIndex), spiller );
                }

            }else {
                ui.kanIkkeKoebeHus(); }

        }else{
            ui.kanIkkeKoebeHus();
        }
    }



    /**
     * @author Andreas
     * Metoder der sælger et hus på en ejendom.
     * Metoden taler først sammen med en metoden opretEjendomskartotek, hvor der laves en ArrayList med alle de ejendomme der
     * har et hus på sig.
     * Så vises listen i UI.
     * Og man vælger den ejendom, hvorfra der skal sælges et hus.
     * @param spiller   Spilleren der ønsker at koebe et hus
     * @param ui        Den UI, der skal bruges
     */
    public void saelgHusForloeb(Spiller spiller, UserInterface ui ){

        // Finder ejendomme med sælgbare huse på
        ArrayList<Ejendom> kartotek = sorterEjendomme(spiller);

        // Tjekker at der overhovedet er nogen huse man kan bygge på
        if( kartotek.size() > 0 ) {

            int ejendomsIndex = ui.vaelgEjendom(kartotek);

            if( ejendomsIndex < kartotek.size() ){
                Ejendom valgtEjendom = kartotek.get( ejendomsIndex );
                valgtEjendom.saelgHus(1);
                spiller.addPenge(EjendomsLogik.beregnSalgsPrisHus(valgtEjendom, 1));
                ui.solgtPaaEjendom( kartotek.get(ejendomsIndex), spiller );
            }
        }else{
            ui.kanIkkeSaelgeHus();
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
    private ArrayList<Ejendom> sorterEjendomme( Spiller spiller ){

        Ejendom[] ejendomme = spiller.getEjendommeArray();

        ArrayList<Ejendom> kartotek = new ArrayList<>();

        for( int i = 0; i < ejendomme.length; i++ ){
            if( EjendomsLogik.kanSaelgeHus( spiller, ejendomme[i], ejendomme[i].getGruppe() ) ){
                kartotek.add( ejendomme[i] );
            }
        }
        return kartotek;
    }



}
