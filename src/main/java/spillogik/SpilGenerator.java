package spillogik;

import BoundaryView.TUI.TUI;
import Controller.BraetCO;
import Controller.SpillerCO;
import ModelEnteties.BraetDTO;
import ModelEnteties.Spil;
import ModelEnteties.SpillerDTO;
import ModelEnteties.Terning.RafleBaeger;
import ModelEnteties.felter.ChanceAktionDTO;

public class SpilGenerator {


    public static SpillerCO[] genererSpillere(int antalSpillere, double startPenge ){
        SpillerCO[] spillere = new SpillerCO[antalSpillere];

        for( int i = 0; i < antalSpillere; i++   ){
            SpillerCO spiller = new SpillerCO();
            spiller.setPenge(startPenge);
            spiller.setNavn("spiller"+(i+1));
            spillere[i] = spiller;
        }

        return spillere;
    }


    public static BraetDTO genererBraet(int antalFelter){
        BraetDTO braet = new BraetCO( antalFelter, new TUI() );


        return braet;
    }


    public static ChanceAktionDTO[] genererChanceKort(int antalKort ){
        ChanceAktionDTO[] chanceKort = new ChanceAktionDTO[antalKort];




        return chanceKort;
    }


    public static Spil genererSpil(int antalSpillere, int antalFelter, int antalChanceKort, double startPenge ){
        Spil spil = new Spil();

        spil.setSpillere( genererSpillere(antalSpillere, startPenge) );
        spil.setBraet( genererBraet(antalFelter) );
        // spil.setChanceKort( genererChancekort);
        spil.setRaflebaeger( new RafleBaeger(2));

        return spil;
    }

    public static Spil genererSpil(){
        return genererSpil( 4, 39, 40, 1500);
    }


}
