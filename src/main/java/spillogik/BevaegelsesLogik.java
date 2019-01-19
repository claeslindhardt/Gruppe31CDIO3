package spillogik;

import model.felter.Felt;

public class BevaegelsesLogik {

    /** Private constructor sikrer at man ikke kan lave objekter af klassen. */
    private BevaegelsesLogik(){}


    /**
     * @author Malte
     * Beregner hvor mange gange spilleren passerer start, hvis
     * spilleren rykker 'antalFelter'.
     * Giver ikke noget output om hvor meget man skal have udbetalt
     * (se i stedet {@link #passererStartPenge(int)}
     *
     * @param startFelt         Spillerens start position
     * @param antalFelter       Hvor mange felter spilleren rykker frem
     * @param totalAntalFelter  Hvor mange felter der paa braettet i alt
     * @return                  Antallet af ganget spilleren passerer start
     */
    public static int antalGangeOverStart( int startFelt, int antalFelter, int totalAntalFelter){
        int gangeOverStart  = ( startFelt + antalFelter ) / totalAntalFelter;
        return gangeOverStart;
    }


    /**
     * @author Malte
     * Beregner hvor mange penge man skal have ved passering af start.
     *
     * @param gangeOverStart   Hvor mange gange man passerer start
     * @return Hvor mange penge spilleren skal have
     */
    public static int passererStartPenge( int gangeOverStart ){
        return gangeOverStart * 200;
    }


    /**
     * @author Malte
     * Beregner hvilket felt man lander på hvis man rykker 'antalFelter'.
     *
     * @param braet         Liste over alle felterne
     * @param startFelt     Feltet man start paa
     * @param antalFelter   Hvor mange felter man rykker. Man kan både rykke forlæns (positiv antalFelter) og baglæns (negativ antal felter)
     * @return              Det felt man ender paa efter at rykke antalFelter
     */
    public static Felt beregnEndeligtFelt( Felt[] braet, Felt startFelt, int antalFelter ){
        int endeligtFeltNr;
        int totalAntalFelter = braet.length;
        int startFeltNr = startFelt.getPlacering();

        // Beregninger
        int totalFelt = startFeltNr + antalFelter;
        int reduceret = totalFelt % totalAntalFelter; // Reducerer hvis man når hele vejen rundt om pladen.

        if( reduceret < 0 ){
            // Man er rykket så langt tilbage at man er gået baglæns over start
            endeligtFeltNr = totalAntalFelter + reduceret;
        } else {
            endeligtFeltNr = reduceret;
        }

        return braet[endeligtFeltNr];
    }







}
