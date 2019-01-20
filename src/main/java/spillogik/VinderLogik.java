package spillogik;

import model.Spil;
import model.Spiller;


public class VinderLogik {


    /**
     * Tjekker om en vinder findes ved at undersøge
     * om alle spillere paa naer én er udgaaet.
     *
     * @return True: der findes en vinder (kan findes via getVinder).
     */
    public static boolean vinderFindes( Spil spil ){
        int antalSpillereISpil = 0;

        for( Spiller spiller : spil.getSpillere() ){
            if( !spiller.erUdgaaet() ){
                antalSpillereISpil++;
            }
        }
        return antalSpillereISpil == 1;
    }


    /**
     * Finder vinderen af spillet.
     *
     * @param spil  Spillet man ønsker at finde vinderen af
     * @return      Vinderen af spillet. Returnere 'null' hvis ingen vinder findes.
     */
    public static Spiller getVinder( Spil spil ){

        if( vinderFindes( spil ) ) {
            for (Spiller spiller : spil.getSpillere()) {
                if (!spiller.erUdgaaet()) {
                    return spiller;
                }
            }
        }
        return null;
    }


    /**
     * Vurderer om en spiller er gået bankerot.
     */
    public static boolean erBankerot( Spiller spiller ){
        return spiller.getPenge() <= 0;
    }


}
