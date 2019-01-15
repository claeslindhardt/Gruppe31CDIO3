package Controller;

import BoundaryView.UserInterfaceKontrakt;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.Bryggeri;
import ModelEnteties.felter.EjeligtFeltDTO;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.felter.Rederi;
import ModelEnteties.raflebaeger.RafleBaeger;
import spillogik.EjendomsLogik;

import java.util.ArrayList;

/*Denne klasse er en subcontroller.
* Denne controller håndtere alle alle metoder der har med pengetransaktioner at gøre.
* */


public class Handel {


    /**
     * @author Andreas
     * Metoder der indsamlere leje for når man lander på et ejeligt felt.
     * @param felt
     * @param spilleren
     * @param userInterfaceKontrakt
     */
    public void indsamleLeje(Spil spil, EjeligtFeltDTO felt, Spiller spilleren, UserInterfaceKontrakt userInterfaceKontrakt){
        Spiller ejeren = felt.getEjer();
        if( ejeren != null && spilleren != null) {
            //todo: enkapsuler dette på en ordenligt måde

            int lejeForFelt = 0;

            if (felt instanceof EjendomCO) {
                lejeForFelt = EjendomsLogik.beregnLejeTotal(felt,spilleren.ejerEjendomsGruppe(felt.getGruppe()));

            } else if (felt instanceof Bryggeri){
                lejeForFelt = EjendomsLogik.beregnLejeBryggeri( spil.getRaflebaeger().getTotalVaerdi(), felt.getEjer() );

            }
            else if (felt instanceof Rederi){
                lejeForFelt = EjendomsLogik.beregnLejeRederi(felt, ejeren);
            }

            spilleren.setPenge(spilleren.getPenge()-lejeForFelt);
            ejeren.addPenge(lejeForFelt);  // hvis Spiller ikke har nok penge til at betale skal den have mulighed for at pantsætte
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
            ejendom.setAntalHuse(0);

            ejendom.setLeje(EjendomsLogik.beregnLejeTotal(ejendom, spiller.ejerEjendomsGruppe( ejendom.getGruppe() )));
            spiller.addPenge(-ejendom.getHotelPris());
            userInterfaceKontrakt.updateSpillere( spiller );

        }
    }

    /**
     * @author Malte
     * FORLØBET i at købe et hus på en ejendom. Dvs. den der sørger beder UI
     * om at vise ting og tage i mod inputs.
     * @param ui : hvilket UserInterface der skal bruges.
     */
    public void koebHotelPaaEjendom(Spiller spiller, UserInterfaceKontrakt ui){
        EjendomCO[] ejendomme = spiller.getEjendomme();

        if( ejendomme.length > 0 ){
            ArrayList<EjendomCO> grundeMedMulighedForHotel = new ArrayList<EjendomCO>();


            for(int i = 0; i < ejendomme.length; i++){
                if( EjendomsLogik.kanKoebeHotel(spiller, ejendomme[i], ejendomme[i].getGruppe()) ){
                    grundeMedMulighedForHotel.add(ejendomme[i]);
                }
            }

            if(grundeMedMulighedForHotel.size() > 0){

                int ejendomsIndex = ui.input_EjendomAtByggeHotelPaa(grundeMedMulighedForHotel);
                koebHotel( spiller,  grundeMedMulighedForHotel.get(ejendomsIndex), ui );

                ui.byggeHotel(grundeMedMulighedForHotel.get(ejendomsIndex));
                ui.tillykkeMedHotel();

            }else {
                ui.kanIkkeKøbeHotel();
            }

        }else{
            ui.kanIkkeKøbeHotel();
        }
    }


}
