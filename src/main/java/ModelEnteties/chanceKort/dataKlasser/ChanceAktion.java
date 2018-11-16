package ModelEnteties.chanceKort.dataKlasser;

import ModelEnteties.singletoner.RandomSingleton;

import java.util.Random;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: ChanceAktion
 *
 * @author Claes
 * Det her er objeket som essientielt er et chance kort. Fordi der er mange forskellige slags chance kort.
 * har denne forældre klasse(ChanceAktion) en række børn:
 *          1. GiverPenge
 *          2. TagerPenge
 *          3. RykkerSpiller
 *          4. GratisUdafFeangsel
 *  Her er altså tale om et polymorfisme(se programdokumentationen til felt.java for yderligere forklaring af dette),
 *  dette kan godt virke tricky. Eftersom dette polymorfisme ligger inden i et andet polymorfisme. Derfor har vi lavet
 *  Design Class Diagrammet som burde give et overblik over sammenspillet mellem alle klasserne. Grunden til vi har
 *  valgt også at lave dette som polymorfisme er fordi vi har vuderet at det er en vigtig del af det, der gør at spillet
 *  ikke bliver det vi kalder et 0 spil inden for spil teori. Det er et spil hvor man altid vil kunne forudse hvem vinder.
 *  Dette har også meget at sige i forhold til hvor meget varation der er fra spil til spil da dette virker som en
 *  atraktiv ting meget varation og skalerbar kode. Har vi gjort meget ud af at denne del blev netop det. Ved at lave
 *  en grund generationer der generer ny beskrivelser til alle chance kort. Så chancen for at to chancekort noglesinde
 *  er de samme er meget lille, og har via polymorfismet en meget skalerbar antal aktioner der følger af chancekortene.
 */
public abstract class ChanceAktion {
    //|--------- Variabler:----------------------
    private String beskrivelse;
    private int pengeVerdi;

    private static String[] positiveGrunde= {" Det er din fødselsdag"," der var en skatte beregning fejl",
            " Du har penge udbetalt på aktier", " Doctor Who kidnappede dig"," Du fik gratis rosengin. Yaiiiii :)",
            " Dorthe Jørgensen synes du er sej", " Du har printet organer!!! You saved the wooorld"
    };
    private static String[] negativeGrunde= {" der var en skatte beregning fejl"," Der blev indført en ny miljø afgift",
            " Du blev straffet for finans fusk", " Der kom en elefant og trådte på dit hus",
            " Din mor besluttede hun ville være russisk operasanger"
    };
    //Singleton Variabler;
    private RandomSingleton randomTal = RandomSingleton.getInstance();

    //|--------- Getters og Setters:-------------

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public int getPengeVerdi() {
        return pengeVerdi;
    }

    public void setPengeVerdi(int pengeVerdi) {
        this.pengeVerdi = pengeVerdi;
    }

    public static String[] getPositiveGrunde() {
        return positiveGrunde;
    }

    public static void setPositiveGrunde(String[] positiveGrunde) {
        ChanceAktion.positiveGrunde = positiveGrunde;
    }

    public static String[] getNegativeGrunde() {
        return negativeGrunde;
    }

    public static void setNegativeGrunde(String[] negativeGrunde) {
        ChanceAktion.negativeGrunde = negativeGrunde;
    }

    //|----------- Metoder:----------------------
    public void DirketeAktion(){ }
    public void BetingetAktion(){ }
}
