package spillogik;

import ModelEnteties.BraetDTO;
import ModelEnteties.Spil;
import ModelEnteties.SpillerDTO;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.felter.ChanceAktionDTO;

public class SpilGenerator {


    public static SpillerDTO[] genererSpillere( int antalSpillere, double startPenge ){
        SpillerDTO[] spillere = new SpillerDTO[antalSpillere];

        for( int i = 0; i < antalSpillere; i++   ){
            SpillerDTO spiller = new SpillerDTO();
            spiller.setPenge(startPenge);
            spillere[i] = spiller;
        }

        return spillere;
    }


    public static BraetDTO genererBraet(int antalFelter){
        BraetDTO braet = new BraetDTO();


        return braet;
    }


    public static ChanceAktionDTO[] genererChanceKort(int antalKort){
        ChanceAktionDTO[] chanceKort = new ChanceAktionDTO[antalKort];


        return chanceKort;
    }


    public static Spil genererSpil(int antalSpillere, int antalFelter, int antalChanceKort, double startPenge ){
        Spil spil = new Spil();

        spil.setSpillere( genererSpillere(antalSpillere, startPenge) );
        spil.setBraet( genererBraet(antalFelter) );
        // spil.setChanceKort( genererChancekort);
        spil.setRafleBaeger( new RafleBaeger(2));

        return spil;
    }

    public static Spil genererSpil(){
        return genererSpil( 4, 40, 40, 1500);
    }


}
