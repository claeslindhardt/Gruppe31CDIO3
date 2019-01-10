package spillogik;

import Controller.SpillerCO;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.felter.EjendomCO;

public class EjendomsLogik {


    /** Private constructor sikrer at man ikke kan lave objekter af klassen. */
    private EjendomsLogik(){}


    // TODO: Implementer Jacobs i
    public boolean huseErFordeltPaaGruppe( EjendomsGruppeDTO ejendomsGruppe, EjendomCO ejendomsUdgangspunkt ){
        return true;
    }


    public boolean kanKoebeHotel(){
        return false;
    }


    public boolean kanKoebeHus( SpillerCO spiller, EjendomCO ejendom, EjendomsGruppeDTO ejendomsGruppe ){

        return      spiller.ejerEjendom( ejendom )
                &&  spiller.ejerEjendomsGruppe( ejendomsGruppe )
                &&  huseErFordeltPaaGruppe( ejendomsGruppe, ejendom )
                &&  ejendom.getAntalHuse() < 4
                &&  !ejendom.harHotel()
                &&  spiller.getPenge() > ejendom.getHusPris();
    }


    public static int beregnLejeTotal( EjendomCO ejendom, boolean harAlleIGruppe ){
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
