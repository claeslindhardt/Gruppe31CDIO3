package spillogik.spilgenerering;



import model.Spil;
import model.Spiller;
import model.raflebaeger.RafleBaeger;
import static spillogik.spilgenerering.ChancekortGenerator.genererChancekort;
import static spillogik.spilgenerering.FeltGenerator.genererFelter;


public class SpilGenerator {

    /**
     * Generer et klassisk matadorspil.
     * Her generes ikke spillerne for spillet, da dette gøres
     * ved spilstart.
     *
     * @return Et Matador spil med generet felter, chancekort og raflebaeger.
     */
    public static Spil genererSpil(){

        Spil spil = new Spil();

        spil.setFelter( genererFelter() );
        spil.setChancekort( genererChancekort() );
        spil.setRaflebaeger( new RafleBaeger(2) );

        return spil;
    }


    /**
     * Et klassisk Matadorspil med forgenerede spillere med
     * navnene "Spiller 1", "Spiller 2" osv.
     * Bruges til test.
     *
     * @return Et Matador spil med generet felter, chancekort og raflebaeger.
     */
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


    /**
     * @author Malte
     * Generer en række spillere med udgangspunkt i en liste
     * af navne. Der blivere genereret lige så mange spillere
     * som listen er lang.
     * Navnene bliver ikke vurderet her (om de er tomme eller
     * om de forekommer dobbelt).
     *
     * @param navne     Et liste af vilkårlige navne
     * @return En liste af spillerobjekter.
     */
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
