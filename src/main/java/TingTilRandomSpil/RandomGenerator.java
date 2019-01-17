package TingTilRandomSpil;

import ModelEnteties.EjendomsGruppe;
import ModelEnteties.Spil;
import ModelEnteties.chancekort.Chancekort;
import ModelEnteties.felter.*;
import ModelEnteties.raflebaeger.RafleBaeger;

import java.util.ArrayList;
import java.util.Random;

import static spillogik.SpilGenerator.genererSpillere;

public class RandomGenerator {



    public static Spil genererSpil(int antalSpillere, int antalFelter, int antalChanceKort, double startPenge ){
        Spil spil = new Spil();

        spil.setSpillere( genererSpillere(antalSpillere, startPenge) );
        spil.setFelter( genererRandomFelter(antalFelter, 100, 25) );
        spil.setChanceKort( genererChancekort(antalChanceKort) );
        spil.setRaflebaeger( new RafleBaeger(2));

        return spil;
    }


    public static EjendomsGruppe[] genererEjendomsGrupper(Ejendom[] ejendomme, int gruppeStoerrelse){
        ArrayList<EjendomsGruppe> ejendomsGrupper = new ArrayList<>();
        EjendomsGruppeGenerator ejendomsGruppeController = new EjendomsGruppeGenerator(gruppeStoerrelse);

        // Tilfoejer alle ejendomme til grupper
        for( Ejendom ejendom : ejendomme ){
            EjendomsGruppe gruppe = ejendomsGruppeController.tilfoejTilGruppe( ejendom );
            ejendom.setGruppe(gruppe);
            ejendomsGrupper.add(gruppe);
        }

        // Laver listen over ejendomsgrupper om til en almindelig array og returnere den ( .toArray() ) @author Malte
        return ejendomsGrupper.toArray(new EjendomsGruppe[0]);
    }




    /**
     * Finder en tilfældig ledig plads i en liste over felter.
     * Den tjekker IKKE om der overhovedet er plads i listen, så det skal man tage højde for
     *
     * @param felter Den liste som felter skal være i, og derfra den tomme plads skal findes
     * @return Array-index på den tomme plads i listen
     */
    public static int getRandomLedigFeltPlads( Felt[] felter ){
        Random random = new Random();
        int indeks;
        do{
            indeks = random.nextInt(felter.length);
        } while( felter[indeks] != null );
        return indeks;
    }


    // Generer Random Felter
    public static Felt[] genererRandomFelter(int antalFelter, double startPris, double prisStigning ) {

        Random random = new Random();
        Felt[] felter;

        /*
            Do-while loopet kører så længe at braettet ikke har et bestemt
            antal minimumsfelter (se condition længere ned).
            For hver iteration laver den et nyt braet (liste af felter).
         */
        do {

            felter = new Felt[antalFelter];
            EjendomsGruppeGenerator ejendomsGruppeCO = new EjendomsGruppeGenerator(3);

            felter[0] = new StartFelt(0);

            // Placerer "must have" felter
            int placering;

            placering = getRandomLedigFeltPlads(felter);
            felter[placering] = new Faengsel("Faengsel", placering);

            placering = getRandomLedigFeltPlads(felter);
            felter[placering] = new GaaIFaengsel(placering);

            // Genererer random felter
            int antalEjendomme = 0, antalChancefelter = 0, antalJernbaner = 0;
            double feltPris = startPris;

            NavneGenerator navneGenerator = new NavneGenerator();

            for (int i = 0; i < antalFelter; i++) {

                // Tjekker at feltet er tomt
                if (felter[i] == null) {

                    int feltType = random.nextInt(9);

                    // Ejendom
                    if (feltType <= 5) {
                        Ejendom ejendom = new Ejendom( navneGenerator.getEjendomsNavn(), (int) feltPris, (int) feltPris/2, i );
                        felter[i] = ejendom;
                        ejendomsGruppeCO.tilfoejTilGruppe(ejendom);

                        antalEjendomme++;

                        // Proev lykken
                    } else {
                        // TODO: Fix det her med chancekort - Malte
                        felter[i] = new ProevLykken( i );
                        antalChancefelter++;

                    }

                    // Øger prisen på felter, der kan købes
                    feltPris += prisStigning;
                }


            }
            if ( antalEjendomme > (antalJernbaner*2)   &&   antalEjendomme > (antalChancefelter*4) ) { break; }

        } while (true);

        return felter;
    }



    public static ArrayList<Chancekort> genererChancekort(int antalChancekort ){

        Random random = new Random();
        ArrayList<Chancekort> alleChancekort = new ArrayList<Chancekort>();

        for( int i = 0; i<antalChancekort; i++){

            Chancekort chancekort = null;
            int kortType = random.nextInt(4);

            switch (kortType){

                //_______________________________________________
                // Giver penge
                case 0:
                    //chancekort = new GiverPengeCO();
                    break;

                //_______________________________________________
                // Tager penge fra dig
                case 1:
                    //chancekort = new TagerPengeCO();
                    break;

                //_______________________________________________
                // Du må rykke som du ønsker
                case 2:
                    //chancekort = new RykkerSpillerCO();
                    break;

                //_______________________________________________
                // Du kan slippe for fængsel
                case 3:
                    //chancekort = new GratisUdAfFaengsel();
                    break;
            }
            alleChancekort.add(chancekort);
        }

        return alleChancekort;
    }


}
