package spillogik;

import Controller.SpillerCO;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.felter.EjendomCO;

public class EjendomsLogik {

    /** Private constructor sikrer at man ikke kan lave objekter af klassen. */
    private EjendomsLogik(){}


    /**
     * @param ejendomsGruppe        Ejendomsgruppen man vil undersøge
     * @param ejendomsUdgangspunkt  Udgangspunktet for undersøgelsen
     * @return  Om huse er fordeligt ligeligt i ejendomsgruppen
     */
    public static boolean huseErFordeltIGruppe( EjendomsGruppeDTO ejendomsGruppe, EjendomCO ejendomsUdgangspunkt ){
        // TODO: Implementer Jacobs i
        return true;
    }


    public static boolean kanKoebeHotel(){
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
                &&  huseErFordeltIGruppe( ejendomsGruppe, ejendom )
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


}
