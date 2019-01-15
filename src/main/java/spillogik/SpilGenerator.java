package spillogik;

import BoundaryView.TUI.TUI;
import Controller.*;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.*;
import ModelEnteties.raflebaeger.RafleBaeger;

import java.util.ArrayList;

public class SpilGenerator {


    public static Spiller[] genererSpillere(int antalSpillere, double startPenge) {
        Spiller[] spillere = new Spiller[antalSpillere];

        for (int i = 0; i < antalSpillere; i++) {
            Spiller spiller = new Spiller();
            spiller.setPenge(startPenge);
            spiller.setNavn("spiller" + (i + 1));
            spiller.setId(i);
            spillere[i] = spiller;
        }

        return spillere;
    }


    public static BraetCO genererBraet(int antalFelter) {
        BraetCO braet = new BraetCO(antalFelter, new TUI());

        genererEjendomsGrupper(braet.getEjendomme(), 3);

        return braet;
    }


    public static ChanceAktionDTO[] genererChanceKort(int antalKort) {
        ChanceAktionDTO[] chanceKort = new ChanceAktionDTO[antalKort];


        return chanceKort;
    }


    public static Spil genererSpil(int antalSpillere, int antalFelter, int antalChanceKort, double startPenge) {
        Spil spil = new Spil();

        spil.setSpillere(genererSpillere(antalSpillere, startPenge));
        spil.setBraet(genererBraet(antalFelter));
        // spil.setChanceKort( genererChancekort);
        spil.setRaflebaeger(new RafleBaeger(2));

        return spil;
    }


    public static EjendomsGruppeDTO[] genererEjendomsGrupper(EjendomCO[] ejendomme, int gruppeStoerrelse) {
        ArrayList<EjendomsGruppeDTO> ejendomsGrupper = new ArrayList<>();
        EjendomsGruppeCO ejendomsGruppeController = new EjendomsGruppeCO(gruppeStoerrelse);

        // Tilfoejer alle ejendomme til grupper
        for (EjendomCO ejendom : ejendomme) {
            EjendomsGruppeDTO gruppe = ejendomsGruppeController.tilfoejTilGruppe(ejendom);
            ejendom.setGruppe(gruppe);
            ejendomsGrupper.add(gruppe);
        }

        // Laver listen over ejendomsgrupper om til en almindelig array og returnere den ( .toArray() ) @author Malte
        return ejendomsGrupper.toArray(new EjendomsGruppeDTO[0]);
    }


    public static Spil genererSpil() {
        return genererSpil(4, 39, 40, 1500);
    }

    public void genererOriginalBraet(){

        FeltDTO[] felter = new FeltDTO[40];

        EjendomCO ejendom;
        ChanceAktionDTO proevLykken;
        BetalSkat indkomstSkat;
        Rederi rederi;
        Bryggeri bryggeri;
        FaengselCO faengsel;
        GaaIFaengselCO gaaIFaengsel;

        felter[0] = new StartCO(200,0);

        ejendom = new EjendomCO("Rødovrevej",60,2,1);
        ejendom.setLejeHus(10,30,90,160);
        ejendom.setLejeHotel(250);
        ejendom.setPantsaetningsVaerdi(30);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        felter[1] = ejendom;

        felter[2] = new ChanceFeltCO(2,null);

        ejendom = new EjendomCO("Hvidovrevej", 60,4,3);
        ejendom.setLejeHus(20,60,180,320);
        ejendom.setLejeHotel(540);
        ejendom.setPantsaetningsVaerdi(30);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        felter[3] = ejendom;

        indkomstSkat = new BetalSkat();
        felter[4] = indkomstSkat;

        rederi = new Rederi();
        rederi.setPantsaetningsVaerdi(100);
        felter[5] = rederi;

        ejendom = new EjendomCO("Roskildevej",100,6,6);
        ejendom.setLejeHus(30,90,270,400);
        ejendom.setLejeHotel(550);
        ejendom.setPantsaetningsVaerdi(50);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        felter[6] = ejendom;

        felter[7] = new ChanceFeltCO(7,null);

        ejendom = new EjendomCO("Valby Langgade",100,6,8);
        ejendom.setLejeHus(30,90,270,400);
        ejendom.setLejeHotel(550);
        ejendom.setPantsaetningsVaerdi(50);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        felter[8] = ejendom;

        ejendom = new EjendomCO("Allégade",120,8,9);
        ejendom.setLejeHus(40,100,300,450);
        ejendom.setLejeHotel(600);
        ejendom.setPantsaetningsVaerdi(60);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        felter[9] = ejendom;

        felter[10] = new FaengselCO("Fængsel",10);

        ejendom = new EjendomCO("Frederiksberg Allé",140,10,11);
        ejendom.setLejeHus(50,150,450,625);
        ejendom.setLejeHotel(750);
        ejendom.setPantsaetningsVaerdi(70);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        felter[11] = ejendom;

        bryggeri = new Bryggeri();
        felter[12] = bryggeri;

        ejendom = new EjendomCO("Bülowsvej",140,10,13);
        ejendom.setLejeHus(50,150,450,625);
        ejendom.setLejeHotel(750);
        ejendom.setPantsaetningsVaerdi(70);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        felter[13] = ejendom;

        ejendom = new EjendomCO("Gl. Kongevej",160,12,14);
        ejendom.setLejeHus(60,180,500,700);
        ejendom.setLejeHotel(900);
        ejendom.setPantsaetningsVaerdi(80);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        felter[14] = ejendom;

        rederi = new Rederi();
        rederi.setPantsaetningsVaerdi(100);
        felter[15] = rederi;

        ejendom = new EjendomCO("Bernstorffsvej",180,14,16);
        ejendom.setLejeHus(70,200,550,750);
        ejendom.setLejeHotel(950);
        ejendom.setPantsaetningsVaerdi(90);






    }


}
