package spillogik;

import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spiller;
import ModelEnteties.felter.EjeligtFelt;
import ModelEnteties.felter.Ejendom;
import ModelEnteties.felter.Rederi;

public class EjendomsLogik {

    /** Private constructor sikrer at man ikke kan lave objekter af klassen. */
    private EjendomsLogik(){}


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

        for (int i = 0; i < ejendomsUdgangspunkt.getGruppe().getAntalEjendomme(); i++){
            Ejendom ejendom = ejendomsUdgangspunkt.getGruppe().getEjendomme().get(i);

            if( ejendom.getAntalHuse() > ejendomsUdgangspunkt.getAntalHuse()){

                return false;

            }
        }
        return true;
    }

    /**
     * @author Jacob
     *
     * Denne metode ser på inddelingen af huse. Hvis den ejendom, som man tager udgangspunkt i har et mindre antal
     * huse end andre ejendomme i gruppen returneres den som false og man kan derfor ikke sælge et hus.
     *
     * @param ejendomUdgangspunkt Udgangspunkt for undersøgelsen
     * @return om huse er ligeligt fordelt i ejendomsgruppen, og om den er i et mode, hvor man kan sælge huset.
     */
    /**public static boolean kanManSaelgeEtHus(Ejendom ejendomUdgangspunkt){

        for (int i = 0; i < ejendomUdgangspunkt.getGruppe().getAntalEjendomme(); i++){
            Ejendom ejendom = ejendomUdgangspunkt.getGruppe().getEjendomme().get(i);

            if ( ejendom.getAntalHuse() > ejendomUdgangspunkt.getAntalHuse() ){
                return false;
            }
        }
        return true;
    }*/


    /**
     * @author
     * Reglerne som skal opfyldes for at man kan koebe et hotel.
     * @param spiller
     * @param ejendom
     * @param ejendomsGruppe
     * @return
     */
    public static boolean kanKoebeHotel(Spiller spiller, Ejendom ejendom, EjendomsGruppeDTO ejendomsGruppe){
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
    public static boolean kanKoebeHus(Spiller spiller, Ejendom ejendom, EjendomsGruppeDTO ejendomsGruppe ){

        return      spiller.ejerEjendom( ejendom )
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  huseErFordeltIGruppe( ejendom )
                &&  ejendom.getAntalHuse() < 4
                &&  !ejendom.harHotel()
                &&  spiller.getPenge() > ejendom.getHusPris();
    }


    public static boolean kanSaelgeHus(Spiller spiller, Ejendom ejendom, EjendomsGruppeDTO ejendomsGruppe ){

        return      spiller.ejerEjendom( ejendom )
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  fordelingAfHuseVedSalg(ejendom)
                &&  ejendom.getAntalHuse() > 0
                &&  !ejendom.harHotel();

    }

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
    public static int beregnLejeBryggeri( int terningeKast, Spiller ejer ){
        int leje;

        if(ejer .getSpillerBryggerier().size() >= 2) {
            leje = terningeKast * 10;
        } else {
            leje = terningeKast * 4;
        }

        return leje;
    }

    public static int beregnLejeRederi(Rederi rederi, Spiller spiller){
        int leje = rederi.getLeje();

        if (spiller.getSpillerRederier().size() == 2){
            leje = leje * 2;
        }
        else if (spiller.getSpillerRederier().size() == 3){
            leje = leje * 2 * 2;
        }
        else if (spiller.getSpillerRederier().size() == 4){
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
