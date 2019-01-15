package spillogik;

import Controller.*;
import ModelEnteties.NavneGenerator;
import ModelEnteties.felter.EjendomCO;
import ModelEnteties.felter.FeltDTO;

import java.util.Random;

public class RandomGenerator {





    /**
     * Finder en tilfældig ledig plads i en liste over felter.
     * Den tjekker IKKE om der overhovedet er plads i listen, så det skal man tage højde for
     *
     * @param felter Den liste som felter skal være i, og derfra den tomme plads skal findes
     * @return Array-index på den tomme plads i listen
     */
    public static int getRandomLedigFeltPlads( FeltDTO[] felter ){
        Random random = new Random();
        int indeks;
        do{
            indeks = random.nextInt(felter.length);
        } while( felter[indeks] != null );
        return indeks;
    }


    // Generer Random Felter
    public static FeltDTO[] genererRandomFelter( int antalFelter, double startPris, double prisStigning ) {

        Random random = new Random();
        FeltDTO[] felter;

        /*
            Do-while loopet kører så længe at braettet ikke har et bestemt
            antal minimumsfelter (se condition længere ned).
            For hver iteration laver den et nyt braet (liste af felter).
         */
        do {

            felter = new FeltDTO[antalFelter];

            felter[0] = new StartCO(0,0);

            // Placerer "must have" felter
            int placering;

            placering = getRandomLedigFeltPlads(felter);
            felter[placering] = new FaengselCO("Faengsel", placering);

            placering = getRandomLedigFeltPlads(felter);
            felter[placering] = new TaxiCO(placering);

            placering = getRandomLedigFeltPlads(felter);
            felter[placering] = new GaaIFaengselCO(placering);

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
                        felter[i] = new EjendomCO( navneGenerator.getEjendomsNavn(), (int) feltPris, (int) feltPris/2, i );
                        antalEjendomme++;

                        // Jernbane
                    } else if (feltType <= 7) {
                        felter[i] = new JernbaneCO( navneGenerator.getJernbaneNavn(), (int) feltPris, i );
                        antalJernbaner++;

                        // Proev lykken
                    } else if (feltType == 8) {
                        // TODO: Fix det her med chancekort - Malte
                        felter[i] = new ChanceFeltCO(i, SpilGenerator.genererChancekort(20));
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


}
