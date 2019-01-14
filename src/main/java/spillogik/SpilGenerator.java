package spillogik;

import BoundaryView.TUI.TUI;
import Controller.BraetCO;
import Controller.EjendomsGruppeCO;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.raflebaeger.RafleBaeger;
import ModelEnteties.felter.ChanceAktionDTO;
import ModelEnteties.felter.EjendomCO;

import java.util.ArrayList;

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




    public static BraetCO genererBraet(int antalFelter){
        BraetCO braet = new BraetCO( antalFelter, new TUI() );

        genererEjendomsGrupper( braet.getEjendomme(), 3 );

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


    public static EjendomsGruppeDTO[] genererEjendomsGrupper(EjendomCO[] ejendomme, int gruppeStoerrelse){
        ArrayList<EjendomsGruppeDTO> ejendomsGrupper = new ArrayList<>();
        EjendomsGruppeCO ejendomsGruppeController = new EjendomsGruppeCO(gruppeStoerrelse);

        // Tilfoejer alle ejendomme til grupper
        for( EjendomCO ejendom : ejendomme ){
            EjendomsGruppeDTO gruppe = ejendomsGruppeController.tilfoejTilGruppe( ejendom );
            ejendom.setGruppe(gruppe);
            ejendomsGrupper.add(gruppe);
        }

        // Laver listen over ejendomsgrupper om til en almindelig array og returnere den ( .toArray() ) @author Malte
        return ejendomsGrupper.toArray(new EjendomsGruppeDTO[0]);
    }


    public static Spil genererSpil(){
        return genererSpil( 4, 39, 40, 1500);
    }


}
