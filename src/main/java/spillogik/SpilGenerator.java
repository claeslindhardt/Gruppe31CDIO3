package spillogik;

import ModelEnteties.BraetDTO;
import ModelEnteties.Spil;
import ModelEnteties.SpillerDTO;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.felter.ChanceAktionDTO;

public class SpilGenerator {


    public static SpillerDTO[] genererSpillere(int antalSpillere){
        SpillerDTO[] spillere = new SpillerDTO[antalSpillere];



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


    public static Spil genererSpil(int antalSpillere, int antalFelter, int antalChanceKort ){
        Spil spil = new Spil();

        spil.setSpillere( genererSpillere(antalSpillere) );
        spil.setBraet( genererBraet(antalFelter) );
        // spil.setChanceKort( genererChancekort);
        spil.setRafleBaeger( new RafleBaeger(2));

        return spil;
    }


}
