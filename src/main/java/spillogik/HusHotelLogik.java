package spillogik;

import model.felter.ejeligefelter.Ejendomsgruppe;
import model.Spiller;
import model.felter.ejeligefelter.Ejendom;

public class HusHotelLogik {


    /**@author Jacob
     *
     * Denne metode ser på inddelingen af huse. Hvis den ejendom, som man tager udgangspunkt i har et større antal
     * huse end andre ejendomme i dens gruppe, returneres den som false og man kan derfor ikke koebe et hus.
     *
     * @param ejendomsUdgangspunkt  Udgangspunktet for undersøgelsen
     * @return  Om huse er fordeligt ligeligt i ejendomsgruppen
     */
    public static boolean huseErFordeltIGruppe( Ejendom ejendomsUdgangspunkt ){

        for (int i = 0; i < ejendomsUdgangspunkt.getGruppe().getAntalEjendomme(); i++){
            Ejendom ejendom = ejendomsUdgangspunkt.getGruppe().getEjendomme().get(i);

            if( ejendom.getAntalHuse() < ejendomsUdgangspunkt.getAntalHuse() ){
                return ejendom.harHotel();
            }
        }
        return true;
    }


    /**
     * @author Andreas
     * Undersøger at husene er ligeligt fordelt paa ejendommene i gruppen,
     * med udgangspunkt i én ejendom. Det vil sige, om alle andre ejendomme
     * har det samme antal eller færre antal huse.
     *
     * Anvendes for at man kan se, hvilke ejendommen man kan sælge huse på.
     *
     * @param ejendomsUdgangspunkt   Udgangspunktet for undersøgelsen
     * @return  Om huse er fordeligt ligeligt i ejendomsgruppen
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
     * Reglerne som skal opfyldes for at man kan koebe et hotel.
     *
     * @param spiller           Spilleren som vil koebe
     * @param ejendom           Ejendom som spilleren vil koebe hotel paa.
     * @param ejendomsGruppe    Ejendomsgruppen som Ejendommen tilhoerer
     * @return  Om man kan koebe hotel eller ej.
     */
    public static boolean kanKoebeHotel(Spiller spiller, Ejendom ejendom, Ejendomsgruppe ejendomsGruppe){
        return      ejendom.getEjer() == spiller
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  huseErFordeltIGruppe( ejendom )
                &&  ejendom.getAntalHuse() == 4
                &&  !ejendom.harHotel()
                &&  spiller.getPenge() > ejendom.getHusPris();

    }


    /**
     * @author Malte
     * Undersøger, om man kan koebe et hus paa en vilkårlig ejendom.
     * *
     * @param spiller           Spilleren som vil koebe
     * @param ejendom           Ejendom som spilleren vil koebe hus paa
     * @param ejendomsGruppe    Ejendomsgruppen som Ejendommen tilhoerer
     * @return  Om man kan koebe hus eller ej.
     */
    public static boolean kanKoebeHus(Spiller spiller, Ejendom ejendom, Ejendomsgruppe ejendomsGruppe ){

        return      ejendom.getEjer() == spiller
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  huseErFordeltIGruppe( ejendom )
                &&  ejendom.getAntalHuse() < 4
                &&  !ejendom.harHotel()
                &&  spiller.getPenge() > ejendom.getHusPris();
    }


    /**
     * Undersøger, om man kan saege et hus paa en vilkårlig ejendom.
     * *
     * @param spiller           Spilleren som vil koebe
     * @param ejendom           Ejendom som spilleren vil saege hus paa
     * @param ejendomsGruppe    Ejendomsgruppen som Ejendommen tilhoerer
     * @return  Om man kan saege hus eller ej.
     */
    public static boolean kanSaelgeHus( Spiller spiller, Ejendom ejendom, Ejendomsgruppe ejendomsGruppe ){

        return      ejendom.getEjer() == spiller
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  fordelingAfHuseVedSalg(ejendom)
                &&  ejendom.getAntalHuse() > 0
                &&  !ejendom.harHotel();

    }



}
