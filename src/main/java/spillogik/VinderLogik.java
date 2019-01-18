package spillogik;

import model.Spil;
import model.Spiller;

public class VinderLogik {




    public static boolean vinderFindes( Spil spil ){
        int antalSpillereISpil = 0;

        for( Spiller spiller : spil.getSpillere() ){
            if( !spiller.erUdgaaet() ){
                antalSpillereISpil++;
            }
        }

        return antalSpillereISpil == 1;
    }


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


    public static boolean erBankerot( Spiller spiller ){
        return spiller.getPenge() <= 0;
    }


}
