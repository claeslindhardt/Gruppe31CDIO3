package spillogik;


import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.FeltDTO;
import ModelEnteties.raflebaeger.RafleBaeger;
import ModelEnteties.felter.ChanceAktionDTO;
import ModelEnteties.felter.EjendomCO;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static spillogik.RandomGenerator.genererRandomFelter;

public class SpilGenerator {


    public static Spiller[] genererSpillere(int antalSpillere, double startPenge ){
        Spiller[] spillere = new Spiller[antalSpillere];

        for( int i = 0; i < antalSpillere; i++   ){
            Spiller spiller = new Spiller();
            spiller.setPenge(startPenge);
            spiller.setNavn("spiller"+(i+1));
            spiller.setId(i);
            spillere[i] = spiller;
        }

        return spillere;
    }


    public static FeltDTO[] genererFelter(){
        return null;
    }


    public static ArrayList<ChanceAktionDTO> genererChancekort(){
        return null;
    }

    public static Spil genererSpil( int antalSpillere ){

        Spil spil = new Spil();

        spil.setSpillere(  genererSpillere( antalSpillere, 1500 )  );
        // TODO: Implementer standard generering af felter
        //spil.setFelter( genererFelter() );
        spil.setFelter( genererRandomFelter(40, 100, 25) );
        spil.setChanceKort( genererChancekort() );
        spil.setRaflebaeger( new RafleBaeger(2) );

        return spil;
    }



}
