package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spiller;
import ModelEnteties.felter.EjendomCO;
import spillogik.EjendomsLogik;

import java.util.ArrayList;

/*Denne klasse er en subcontroller.
* Denne controller håndtere alle alle metoder der har med pengetransaktioner at gøre.
* */


public class Handel {


    /**
     * @author Andreas
     * Metoder der indsamlere leje for når man lander på et ejeligt felt.
     * @param ejendom
     * @param spilleren
     * @param userInterfaceKontrakt
     */
    public void indsamleLeje(EjendomCO ejendom, Spiller spilleren, UserInterfaceKontrakt userInterfaceKontrakt){
        Spiller ejeren = ejendom.getEjer();
        if( ejeren != null && spilleren != null) {
            //todo: enkapsuler dette på en ordenligt måde
            int lejeForFelt = EjendomsLogik.beregnLejeTotal(ejendom, spilleren.ejerEjendomsGruppe( ejendom.getGruppe() ));
            spilleren.setPenge(spilleren.getPenge()-lejeForFelt);
            ejeren.addPenge(ejendom.getLeje());  // hvis Spiller ikke har nok penge til at betale skal den have mulighed for at pantsætte
            userInterfaceKontrakt.updateSpillere(spilleren);
            userInterfaceKontrakt.updateSpillere(ejeren);
        }else{
            userInterfaceKontrakt.badErrorMessage();
        }
    }



    /**
     * @author Malte
     * Metode der koeber et hus på en ejendom for spilleren.
     * Dette inkluderer at bygge huset paa ejendom (ejendom.bygHuse),
     * og trække penge fra spilleren.
     *
     * @param ejendom: hvilken ejendom man vil bygge et hus paa.
     */
    public void koebHus(Spiller spiller, EjendomCO ejendom, UserInterfaceKontrakt userInterfaceKontrakt){
        if( EjendomsLogik.kanKoebeHus( spiller, ejendom, ejendom.getGruppe()) ){
            ejendom.bygHuse(1);

            ejendom.setLeje(EjendomsLogik.beregnLejeTotal(ejendom, spiller.ejerEjendomsGruppe( ejendom.getGruppe() )));
            spiller.addPenge(-ejendom.getHusPris());
            userInterfaceKontrakt.updateSpillere( spiller );

        }
    }

    /**
     * @author Malte
     * FORLØBET i at købe et hus på en ejendom. Dvs. den der sørger beder UI
     * om at vise ting og tage i mod inputs.
     * @param ui: hvilket UserInterface der skal bruges.
     */
    public void koebHusPaaEjendom(Spiller spiller, UserInterfaceKontrakt ui){
        EjendomCO[] ejendomme = spiller.getEjendomme();

        if( ejendomme.length > 0 ){
            ArrayList<EjendomCO> bebyggeligeEjendomme = new ArrayList<EjendomCO>();

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

                int ejendomsIndex = ui.input_EjendomAtByggePaa(bebyggeligeEjendomme);
                koebHus( spiller,  bebyggeligeEjendomme.get(ejendomsIndex), ui );

                ui.byggetHus(bebyggeligeEjendomme.get(ejendomsIndex));

            }else {
                ui.ejerIngenBebyggeligeEjendomme(); }

        }else{
            ui.ejerIngenEjendomme();
        }
    }


    public void koebHotel(Spiller spiller, EjendomCO ejendom, UserInterfaceKontrakt userInterfaceKontrakt){
        if( EjendomsLogik.kanKoebeHotel( spiller, ejendom, ejendom.getGruppe()) ){
            ejendom.bygHotel(true);

            ejendom.setLeje(EjendomsLogik.beregnLejeTotal(ejendom, spiller.ejerEjendomsGruppe( ejendom.getGruppe() )));
            spiller.addPenge(-ejendom.getHotelPris());
            userInterfaceKontrakt.updateSpillere( spiller );

        }
    }

    /**
     * @author Malte
     * FORLØBET i at købe et hus på en ejendom. Dvs. den der sørger beder UI
     * om at vise ting og tage i mod inputs.
     * @param ui: hvilket UserInterface der skal bruges.
     */
    public void koebHotelPaaEjendom(Spiller spiller, UserInterfaceKontrakt ui){
        EjendomCO[] ejendomme = spiller.getEjendomme();

        if( ejendomme.length > 0 ){
            ArrayList<EjendomCO> bebyggeligeEjendomme = new ArrayList<EjendomCO>();

            /* Finder bebyggelige ejendomme og flytter dem over i en seperat liste.
               Se kanKoebeHus() for at se, hvordan det vurderes om spilleren kan
               bygge et hus paa en ejendom.
             */
            for(int i = 0; i < ejendomme.length; i++){
                if( EjendomsLogik.kanKoebeHotel(spiller, ejendomme[i], ejendomme[i].getGruppe()) ){
                    bebyggeligeEjendomme.add(ejendomme[i]);
                }
            }

            if(bebyggeligeEjendomme.size() > 0){

                int ejendomsIndex = ui.input_EjendomAtByggePaa(bebyggeligeEjendomme);
                koebHotel( spiller,  bebyggeligeEjendomme.get(ejendomsIndex), ui );

                ui.byggeHotel(bebyggeligeEjendomme.get(ejendomsIndex));

            }else {
                ui.ejerIngenBebyggeligeEjendomme(); }

        }else{
            ui.ejerIngenEjendomme();
        }
    }


}
