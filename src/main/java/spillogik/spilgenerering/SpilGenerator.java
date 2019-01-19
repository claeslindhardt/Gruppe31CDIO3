package spillogik.spilgenerering;

import model.felter.ejeligefelter.Ejendomsgruppe;
import model.chancekort.*;
import model.Spil;
import model.Spiller;
import model.felter.*;
import model.felter.aktionsfelter.*;
import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Rederi;
import model.raflebaeger.RafleBaeger;
import model.singletoner.RandomSingleton;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static spillogik.spilgenerering.ChancekortGenerator.genererChancekort;
import static spillogik.spilgenerering.FeltGenerator.genererFelter;

public class SpilGenerator {

    public static Spil genererSpil(){

        Spil spil = new Spil();

        spil.setFelter( genererFelter() );
        spil.setChancekort( genererChancekort() );
        spil.setRaflebaeger( new RafleBaeger(2) );

        return spil;
    }


    public static Spil genererSpil( int antalSpillere ){

        Spil spil = new Spil();
        spil.setFelter( genererFelter() );
        spil.setChancekort( genererChancekort() );
        spil.setRaflebaeger( new RafleBaeger(2) );

        String[] spillerNavne = new String[antalSpillere];
        for( int i = 0; i < antalSpillere; i++ ){
            spillerNavne[i] = "Spiller " + (i+1);
        }
        spil.setSpillere(  genererSpillere( spillerNavne )  );

        return spil;
    }



    public static Spiller[] genererSpillere(String ... navne ) {
        int antalSpillere = navne.length;
        Spiller[] spillere = new Spiller[antalSpillere];

        for (int i = 0; i < antalSpillere; i++) {
            Spiller spiller = new Spiller();
            spiller.setPenge(1500);
            spiller.setNavn( navne[i] );
            spiller.setId(i);
            spillere[i] = spiller;
        }

        return spillere;
    }

}
