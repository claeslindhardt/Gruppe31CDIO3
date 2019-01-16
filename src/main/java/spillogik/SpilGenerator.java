package spillogik;

import Controller.*;
import ModelEnteties.ChanceAktionDTO;
import ModelEnteties.EjendomsGruppeDTO;
import ModelEnteties.Spil;
import ModelEnteties.Spiller;
import ModelEnteties.felter.*;
import ModelEnteties.raflebaeger.RafleBaeger;

import java.awt.*;
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

    /**
     * @author Jacob
     *
     * Denne metode genererer det klassiske Matador bræt.
     *
     * @return Matador brættet returneres.
     */
    public static FeltDTO[] genererFelter(){

        FeltDTO[] felter = new FeltDTO[40];

        //-----------------------------------------Variabler--------------------------------------//
        EjendomCO ejendom;
        ChanceFeltCO proevLykken;
        BetalSkat indkomstSkat;
        BetalSkat statsSkat;
        Rederi rederi;
        Bryggeri bryggeri;
        FaengselCO faengsel;
        GaaIFaengselCO gaaIFaengsel;
        FriParkering friParkering;
        EjendomsGruppeDTO ejendomsGruppe;


        //----------------------------------------Brættet---------------------------------------//
        felter[0] = new StartCO(200,0);

        ejendomsGruppe = new EjendomsGruppeDTO(Color.blue,2);

        ejendom = new EjendomCO("Rødovrevej",60,2,1);
        ejendom.setLejeHus(10,30,90,160);
        ejendom.setLejeHotel(250);
        ejendom.setPantsaetningsVaerdi(30);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[1] = ejendom;

        proevLykken = new ChanceFeltCO(2,null);
        felter[2] = proevLykken;


        ejendom = new EjendomCO("Hvidovrevej", 60,4,3);
        ejendom.setLejeHus(20,60,180,320);
        ejendom.setLejeHotel(540);
        ejendom.setPantsaetningsVaerdi(30);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[3] = ejendom;

        indkomstSkat = new BetalSkat();
        felter[4] = indkomstSkat;

        rederi = new Rederi("Øresund A/S",200,5);
        rederi.setLeje(25);
        rederi.setPantsaetningsVaerdi(100);
        felter[5] = rederi;

        ejendomsGruppe = new EjendomsGruppeDTO(Color.PINK,3);

        ejendom = new EjendomCO("Roskildevej",100,6,6);
        ejendom.setLejeHus(30,90,270,400);
        ejendom.setLejeHotel(550);
        ejendom.setPantsaetningsVaerdi(50);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[6] = ejendom;

        proevLykken = new ChanceFeltCO(7,null);
        felter[7] = proevLykken;

        ejendom = new EjendomCO("Valby Langgade",100,6,8);
        ejendom.setLejeHus(30,90,270,400);
        ejendom.setLejeHotel(550);
        ejendom.setPantsaetningsVaerdi(50);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[8] = ejendom;

        ejendom = new EjendomCO("Allégade",120,8,9);
        ejendom.setLejeHus(40,100,300,450);
        ejendom.setLejeHotel(600);
        ejendom.setPantsaetningsVaerdi(60);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[9] = ejendom;

        faengsel = new FaengselCO("Fængsel",10);
        felter[10] = faengsel;

        ejendomsGruppe = new EjendomsGruppeDTO(Color.green,3);

        ejendom = new EjendomCO("Frederiksberg Allé",140,10,11);
        ejendom.setLejeHus(50,150,450,625);
        ejendom.setLejeHotel(750);
        ejendom.setPantsaetningsVaerdi(70);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[11] = ejendom;

        bryggeri = new Bryggeri("Tuborg",12);
        bryggeri.setPris(150);
        bryggeri.setPantsaetningsVaerdi(75);
        felter[12] = bryggeri;

        ejendom = new EjendomCO("Bülowsvej",140,10,13);
        ejendom.setLejeHus(50,150,450,625);
        ejendom.setLejeHotel(750);
        ejendom.setPantsaetningsVaerdi(70);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[13] = ejendom;

        ejendom = new EjendomCO("Gl. Kongevej",160,12,14);
        ejendom.setLejeHus(60,180,500,700);
        ejendom.setLejeHotel(900);
        ejendom.setPantsaetningsVaerdi(80);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[14] = ejendom;

        rederi = new Rederi("D.F.D.S",200,15);
        rederi.setLeje(25);
        rederi.setPantsaetningsVaerdi(100);
        felter[15] = rederi;

        ejendomsGruppe = new EjendomsGruppeDTO(Color.GRAY,3);

        ejendom = new EjendomCO("Bernstorffsvej",180,14,16);
        ejendom.setLejeHus(70,200,550,750);
        ejendom.setLejeHotel(950);
        ejendom.setPantsaetningsVaerdi(90);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[16] = ejendom;

        proevLykken = new ChanceFeltCO(17,null);
        felter[17] = proevLykken;

        ejendom = new EjendomCO("Hellerupvej",180,14,18);
        ejendom.setLejeHus(70,200,550,750);
        ejendom.setLejeHotel(950);
        ejendom.setPantsaetningsVaerdi(90);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[18] = ejendom;

        ejendom = new EjendomCO("Strandvej",200,16,19);
        ejendom.setLejeHus(80,220,600,800);
        ejendom.setLejeHotel(1000);
        ejendom.setPantsaetningsVaerdi(100);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[19] = ejendom;

        friParkering = new FriParkering(20);
        felter[20] = friParkering;

        ejendomsGruppe = new EjendomsGruppeDTO(Color.red,3);

        ejendom = new EjendomCO("Trianglen",220,18,21);
        ejendom.setLejeHus(90,250,700,875);
        ejendom.setLejeHotel(1050);
        ejendom.setPantsaetningsVaerdi(110);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[21] = ejendom;

        proevLykken = new ChanceFeltCO(22,null);
        felter[22] = proevLykken;

        ejendom = new EjendomCO("Østerbrogade",220,18,23);
        ejendom.setLejeHus(90,250,700,875);
        ejendom.setLejeHotel(1050);
        ejendom.setPantsaetningsVaerdi(110);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[23] = ejendom;

        ejendom = new EjendomCO("Grønningen",240,20,24);
        ejendom.setLejeHus(100,300,750,925);
        ejendom.setLejeHotel(1100);
        ejendom.setPantsaetningsVaerdi(120);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[24] = ejendom;

        rederi = new Rederi("Ø.K",200,25);
        rederi.setLeje(25);
        rederi.setPantsaetningsVaerdi(100);
        felter[25] = rederi;

        ejendomsGruppe = new EjendomsGruppeDTO(Color.white,3);

        ejendom = new EjendomCO("Bredgade",260,22,26);
        ejendom.setLejeHus(110,330,800,975);
        ejendom.setLejeHotel(1150);
        ejendom.setPantsaetningsVaerdi(130);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[26] = ejendom;

        ejendom = new EjendomCO("Kgs. Nytorv",260,22,27);
        ejendom.setLejeHus(110,330,800,975);
        ejendom.setLejeHotel(1150);
        ejendom.setPantsaetningsVaerdi(130);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[27] = ejendom;

        bryggeri = new Bryggeri("Carlsberg",28);
        bryggeri.setPris(150);
        bryggeri.setPantsaetningsVaerdi(75);
        felter[28] = bryggeri;

        ejendom = new EjendomCO("Østergade",280,24,29);
        ejendom.setLejeHus(120,360,850,1025);
        ejendom.setLejeHotel(1200);
        ejendom.setPantsaetningsVaerdi(140);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[29] = ejendom;

        gaaIFaengsel = new GaaIFaengselCO(30);
        felter[30] = gaaIFaengsel;

        ejendomsGruppe = new EjendomsGruppeDTO(Color.YELLOW,3);

        ejendom = new EjendomCO("Amagertorv",300,26,31);
        ejendom.setLejeHus(130,390,900,1100);
        ejendom.setLejeHotel(1275);
        ejendom.setPantsaetningsVaerdi(150);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[31] = ejendom;

        ejendom = new EjendomCO("Vimmelskaftet",300,26,32);
        ejendom.setLejeHus(130,390,900,1100);
        ejendom.setLejeHotel(1275);
        ejendom.setPantsaetningsVaerdi(150);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[32] = ejendom;

        proevLykken = new ChanceFeltCO(33,null);
        felter[33] = proevLykken;

        ejendom = new EjendomCO("Nygade", 320,28,34);
        ejendom.setLejeHus(150,450,1000,1200);
        ejendom.setLejeHotel(1400);
        ejendom.setPantsaetningsVaerdi(160);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[34] = ejendom;

        rederi = new Rederi("Bornholm 1866",200,35);
        rederi.setLeje(25);
        rederi.setPantsaetningsVaerdi(100);
        felter[35] = rederi;

        proevLykken = new ChanceFeltCO(36,null);
        felter[36] = proevLykken;

        ejendomsGruppe = new EjendomsGruppeDTO(Color.MAGENTA,2);

        ejendom = new EjendomCO("Frederiksberggade",350,35,37);
        ejendom.setLejeHus(175,500,1100,1300);
        ejendom.setLejeHotel(1500);
        ejendom.setPantsaetningsVaerdi(175);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[37] = ejendom;

        statsSkat = new BetalSkat();
        felter[38] = statsSkat;

        ejendom = new EjendomCO("Rådhuspladsen",400,50,39);
        ejendom.setLejeHus(200,600,1400,1700);
        ejendom.setLejeHotel(2000);
        ejendom.setPantsaetningsVaerdi(200);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[39] = ejendom;

        return felter;
    }


    public static ArrayList<ChanceAktionDTO> genererChancekort(){
        // TODO Implementer fastsatte chancekort
        return RandomGenerator.genererChancekort(10);
    }

    public static Spil genererSpil( int antalSpillere ){

        Spil spil = new Spil();

        spil.setSpillere(  genererSpillere( antalSpillere, 1500 )  );
        // TODO: Implementer standard generering af felter
        spil.setFelter( genererFelter() );
        //spil.setFelter( genererRandomFelter(40, 100, 25) );
        spil.setChanceKort( genererChancekort() );
        spil.setRaflebaeger( new RafleBaeger(2) );

        return spil;
    }



}
