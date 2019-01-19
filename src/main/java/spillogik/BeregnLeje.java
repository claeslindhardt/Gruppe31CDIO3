package spillogik;

import model.Spiller;
import model.felter.ejeligefelter.EjeligtFelt;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;

public class BeregnLeje {




    public static double beregnSalgsPrisHus(Ejendom ejendom, int antalHuse){

        if(ejendom.harHotel()){
            return ((ejendom.getHotelPris())/2);
        }else {
            return ((ejendom.getHusPris() * antalHuse) / 2);
        }
    }
    /**
     * Henter information for, hvad en Ejendoms leje skal være med udgangspunkt i antallet af huse / hoteller
     * og om man ejer alle i Ejendomsgruppen.
     * I metoden {@link #beregnLejeVedHus} bliver legne for en ejendom udregnet. Denne information bliver gemt i en liste,
     * der holdes af ejendomsobjektet.
     *
     * @param felt           Hvilken Ejendom man vil beregne leje for
     * @param harAlleIGruppe    Om ejeren af Ejendommen har alle i 'ejendom's gruppe.
     * @return
     */
    public static int beregnLejeTotal(EjeligtFelt felt, boolean harAlleIGruppe ){
        int leje;

        //Her beregnes den leje som man skal betale, hvis at man har et en grund.
        if( ((Ejendom) felt).getAntalHuse() > 0 ){
            leje = ((Ejendom) felt).getLejeHus(((Ejendom) felt).getAntalHuse());

        }else if( ((Ejendom) felt).harHotel() ){
            leje = ((Ejendom) felt).getLejeHotel();

        }else{
            leje = ((Ejendom) felt).getLejeStart();
            if( harAlleIGruppe ){  leje *= 2;  }
        }
        return leje;


    }

    /**
     * @auther Andreas
     * Beregner den leje som skal betales, når en spiller lander på en andens bryggeri.
     * @param terningeKast
     * @param ejer
     * @return
     */
    public static int beregnLejeBryggeri( int terningeKast, Spiller ejer ){
        int leje;

        if(ejer .getSpillerBryggerier().size() >= 2) {
            leje = terningeKast * 10;
        } else {
            leje = terningeKast * 4;
        }

        return leje;
    }

    /**
     * @auther filip
     * @param rederi
     * @param spiller
     * @return
     */
    public static int beregnLejeRederi(Rederi rederi, Spiller spiller){
        int leje = rederi.getLeje();

        if (spiller.getRederier().size() == 2){
            leje = leje * 2;
        }
        else if (spiller.getRederier().size() == 3){
            leje = leje * 2 * 2;
        }
        else if (spiller.getRederier().size() == 4){
            leje = leje * 2 * 2 * 2;
        }

        return leje;
    }


    /**
     * @author Malte
     * Beregner lejen ved et bestemt antal huse for en Ejendom.
     *
     * @param ejendom       Ejendommen man vil beregne for
     * @param antalHuse     Antallet af huse man vil beregne leje for
     * @return Lejen på en Ejendom ved et bestemt antal huse.
     */
    public static int beregnLejeVedHus(Ejendom ejendom, int antalHuse){

        int lejePerHus = ejendom.getLejeStart() * 2;

        return ejendom.getLejeStart() + lejePerHus * antalHuse;
    }


    /**
     * @author Malte
     * Beregner lejen for en Ejendom, hvis der står et hotel på den.
     *
     * @param ejendom       Ejendommen man vil beregne for
     * @return  Lejen på Ejendommen hvis der står et hotel
     */
    public static int beregnLejeVedHotel( Ejendom ejendom ){
        return beregnLejeVedHus(ejendom, 5);
    }




}
