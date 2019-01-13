package spillogik;

import Controller.SpillerCO;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.felter.EjendomCO;

import java.util.ArrayList;

public class EjendomsLogik {

    private static ArrayList <EjendomCO> ejendomme = new ArrayList<EjendomCO>();

    /** Private constructor sikrer at man ikke kan lave objekter af klassen. */
    private EjendomsLogik(){}


    /**@author Jacob
     *
     * Denne metode ser på inddelingen af huse. Hvis den ejendom, som man tager udgangspunkt i har et større antal
     * huse end andre ejendomme returneres den som false og man kan derfor ikke koebe et hus.
     *
     * @param ejendomsUdgangspunkt  Udgangspunktet for undersøgelsen
     * @return  Om huse er fordeligt ligeligt i ejendomsgruppen og at man derfor kan koebe et hus på ejendommen
     */
    public static boolean huseErFordeltIGruppe( EjendomCO ejendomsUdgangspunkt ){
        for (int i = 0; i < ejendomme.size(); i++){
            EjendomCO ejendom = ejendomme.get(i);

            if( ejendom.getAntalHuse() < ejendomsUdgangspunkt.getAntalHuse() ){
                return false;
            }
        }
        return true;
    }

    /**
     * @author Jacob
     *
     * Denne metode ser på inddelingen af huse. Hvis den ejendom, som man tager udgangspunkt i har et mindre antal
     * huse end andre ejendomme returneres den som false og man kan derfor ikke sælge et hus.
     *
     * @param ejendomUdgangspunkt Udgangspunkt for undersøgelsen
     * @return om huse er ligeligt fordelt i ejendomsgruppen, og om den er i et mode, hvor man kan sælge huset.
     */
    public static boolean kanManSaelgeEtHus(EjendomCO ejendomUdgangspunkt){

        for (int i = 0; i < ejendomme.size(); i++){
            EjendomCO ejendom = ejendomme.get(i);

            if ( ejendom.getAntalHuse() > ejendomUdgangspunkt.getAntalHuse() ){
                return false;
            }
        }
        return true;
    }


    public static boolean   kanKoebeHotel(){
        // TODO: Implemener denne
        return false;
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
    public static boolean kanKoebeHus( SpillerCO spiller, EjendomCO ejendom, EjendomsGruppeDTO ejendomsGruppe ){

        return      spiller.ejerEjendom( ejendom )
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  huseErFordeltIGruppe( ejendom )
                &&  ejendom.getAntalHuse() < 4
                &&  !ejendom.harHotel()
                &&  spiller.getPenge() > ejendom.getHusPris();
    }


    /**
     * Beregner hvad en Ejendoms leje skal være med udgangspunkt i antallet af huse / hoteller
     * og om man ejer alle i Ejendomsgruppen.
     *
     * @param ejendom           Hvilken Ejendom man vil beregne leje for
     * @param harAlleIGruppe    Om ejeren af Ejendommen har alle i 'ejendom's gruppe.
     * @return
     */
    public static int beregnLejeTotal( EjendomCO ejendom, boolean harAlleIGruppe ){
        int leje;

        if( ejendom.getAntalHuse() > 0 ){
            leje = ejendom.getLejeHus(ejendom.getAntalHuse());

        }else if( ejendom.harHotel() ){
            leje = ejendom.getLejeHotel();

        }else{
            leje = ejendom.getLejeStart();
            if( harAlleIGruppe ){  leje *= 2;  }
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
    public static int beregnLejeVedHus(EjendomCO ejendom, int antalHuse){

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
    public static int beregnLejeVedHotel( EjendomCO ejendom ){
        return beregnLejeVedHus(ejendom, 5);
    }


}
