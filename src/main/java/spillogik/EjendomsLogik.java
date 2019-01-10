package spillogik;

import ModelEnteties.felter.EjendomCO;

public class EjendomsLogik {


    /** Private constructor sikrer at man ikke kan lave objekter af klassen. */
    private EjendomsLogik(){}



    public boolean kanKoebeHotel(){
        return false;
    }


    public boolean kanKoebeHus(){
        return false;
    }


    public static int beregnLeje( EjendomCO ejendom, boolean harAlleIGruppe ){
        int leje;

        if( ejendom.getAntalHuse() > 0 ){
            leje = ejendom.getLejeHus(ejendom.getAntalHuse());

        }else if( ejendom.harHotel() ){
            leje = ejendom.getLejeHotel();

        }else{
            leje = ejendom.getLejeStart();

            if( harAlleIGruppe ){
                leje *= 2;
            }
        }

        return leje;
    }



    public static int beregnLejeVedHus(EjendomCO ejendom, int antalHuse){

        int lejePerHus = ejendom.getLejeStart() * 2;

        return lejePerHus * antalHuse;
    }


}
