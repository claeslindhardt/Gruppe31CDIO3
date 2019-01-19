package spillogik;

import model.felter.ejeligefelter.Ejendomsgruppe;
import model.Spiller;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;

public class HusHotelLogik {


    /**@author Jacob
     *
     * Denne metode ser på inddelingen af huse. Hvis den ejendom, som man tager udgangspunkt i har et større antal
     * huse end andre ejendomme i dens gruppe, returneres den som false og man kan derfor ikke koebe et hus.
     *
     * @param ejendomsUdgangspunkt  Udgangspunktet for undersøgelsen
     * @return  Om huse er fordeligt ligeligt i ejendomsgruppen og at man derfor kan koebe et hus på ejendommen
     */
    public static boolean huseErFordeltIGruppe( Ejendom ejendomsUdgangspunkt ){

        for (int i = 0; i < ejendomsUdgangspunkt.getGruppe().getAntalEjendomme(); i++){
            Ejendom ejendom = ejendomsUdgangspunkt.getGruppe().getEjendomme().get(i);

            if( ejendom.getAntalHuse() < ejendomsUdgangspunkt.getAntalHuse() ){
                if ( ejendom.harHotel()){
                    return true;
                }
                return false;
            }
        }
        return true;
    }


    /**
     * @author Andreas
     *
     * Anvendes for at man kan se, hvilke ejendommen man kan sælge huse på.
     * @param ejendomsUdgangspunkt
     * @return
     */
    public static boolean fordelingAfHuseVedSalg( Ejendom ejendomsUdgangspunkt ){

        for( int i = 0; i < ejendomsUdgangspunkt.getGruppe().getAntalEjendomme(); i++ ){

            Ejendom ejendom = ejendomsUdgangspunkt.getGruppe().getEjendomme().get(i);

            if( ejendom.getAntalHuse() > ejendomsUdgangspunkt.getAntalHuse()||ejendom.harHotel()){

                return false;
            }
        }
        return true;
    }



    /**
     * @author
     * Reglerne som skal opfyldes for at man kan koebe et hotel.
     * @param spiller
     * @param ejendom
     * @param ejendomsGruppe
     * @return
     */
    public static boolean kanKoebeHotel(Spiller spiller, Ejendom ejendom, Ejendomsgruppe ejendomsGruppe){
        return spiller.ejerEjendom( ejendom )
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  huseErFordeltIGruppe( ejendom )
                &&  ejendom.getAntalHuse() == 4
                &&  !ejendom.harHotel()
                &&  spiller.getPenge() > ejendom.getHusPris();

    }


    /**
     * @author Malte
     * Undersøger, om man kan koebe et hus paa en vilkårlig ejendom.
     * Her tjekkes for at
     *  1) man ejer ejendommen
     *  2) man ejer alle ejendomme i gruppen
     *  3) der er ligelig fordeling af huse paa ejendommene i gruppen (ikke implementeret)
     *  4) antallet af huse er under 4
     *  5) spilleren har nok penge til at koebe ejendommen.
     *
     * @param spiller           Spilleren som vil koebe
     * @param ejendom           Ejendom som spilleren vil koebe hus paa
     * @param ejendomsGruppe    Ejendomsgruppen som Ejendommen tilhoerer
     * @return  Om man kan koebe hus eller ej.
     */
    public static boolean kanKoebeHus(Spiller spiller, Ejendom ejendom, Ejendomsgruppe ejendomsGruppe ){

        return      spiller.ejerEjendom( ejendom )
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  huseErFordeltIGruppe( ejendom )
                &&  ejendom.getAntalHuse() < 4
                &&  !ejendom.harHotel()
                &&  spiller.getPenge() > ejendom.getHusPris();
    }


    public static boolean kanSaelgeHus( Spiller spiller, Ejendom ejendom, Ejendomsgruppe ejendomsGruppe ){

        return      spiller.ejerEjendom( ejendom )
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  fordelingAfHuseVedSalg(ejendom)
                &&  ejendom.getAntalHuse() > 0
                &&  !ejendom.harHotel();

    }





}