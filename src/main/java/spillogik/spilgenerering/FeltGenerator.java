package spillogik.spilgenerering;

import model.felter.Felt;
import model.felter.aktionsfelter.*;
import model.felter.ejeligefelter.Bryggeri;
import model.felter.ejeligefelter.Ejendom;
import model.felter.ejeligefelter.Ejendomsgruppe;
import model.felter.ejeligefelter.Rederi;

import java.awt.*;

public class FeltGenerator {


    public static Felt[] genererFelter(){

        Felt[] felter = new Felt[40];

        //-----------------------------------------Variabler--------------------------------------//
        Ejendom ejendom;
        ProevLykken proevLykken;
        StatsSkat statsSkat;
        Rederi rederi;
        Bryggeri bryggeri;
        Faengsel faengsel;
        GaaIFaengsel gaaIFaengsel;
        FriParkering friParkering;
        Ejendomsgruppe ejendomsGruppe;


        //----------------------------------------Brættet---------------------------------------//
        felter[0] = new StartFelt(0);

        ejendomsGruppe = new Ejendomsgruppe( new Color(100,100,255) );

        ejendom = new Ejendom("Rødovrevej",60,2,1);
        ejendom.setLejeHus(10,30,90,160);
        ejendom.setLejeHotel(250);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[1] = ejendom;

        proevLykken = new ProevLykken(2);
        felter[2] = proevLykken;


        ejendom = new Ejendom("Hvidovrevej", 60,4,3);
        ejendom.setLejeHus(20,60,180,320);
        ejendom.setLejeHotel(540);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[3] = ejendom;

        IndkomstSkat indkomstSkat = new IndkomstSkat(4);
        felter[4] = indkomstSkat;

        rederi = new Rederi("Øresund A/S",200,  5 );
        felter[5] = rederi;

        ejendomsGruppe = new Ejendomsgruppe( Color.PINK );

        ejendom = new Ejendom("Roskildevej",100,6,6);
        ejendom.setLejeHus(30,90,270,400);
        ejendom.setLejeHotel(550);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[6] = ejendom;

        proevLykken = new ProevLykken(7);
        felter[7] = proevLykken;

        ejendom = new Ejendom("Valby Langgade",100,6,8);
        ejendom.setLejeHus(30,90,270,400);
        ejendom.setLejeHotel(550);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[8] = ejendom;

        ejendom = new Ejendom("Allégade",120,8,9);
        ejendom.setLejeHus(40,100,300,450);
        ejendom.setLejeHotel(600);
        ejendom.setHusPris(50);
        ejendom.setHotelPris(50);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[9] = ejendom;

        faengsel = new Faengsel(10);
        felter[10] = faengsel;

        ejendomsGruppe = new Ejendomsgruppe( Color.green );

        ejendom = new Ejendom("Frederiksberg Allé",140,10,11);
        ejendom.setLejeHus(50,150,450,625);
        ejendom.setLejeHotel(750);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[11] = ejendom;

        bryggeri = new Bryggeri("Tuborg",150, 12);
        felter[12] = bryggeri;

        ejendom = new Ejendom("Bülowsvej",140,10,13);
        ejendom.setLejeHus(50,150,450,625);
        ejendom.setLejeHotel(750);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[13] = ejendom;

        ejendom = new Ejendom("Gl. Kongevej",160,12,14);
        ejendom.setLejeHus(60,180,500,700);
        ejendom.setLejeHotel(900);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[14] = ejendom;

        rederi = new Rederi("D.F.D.S",200, 15);
        felter[15] = rederi;

        ejendomsGruppe = new Ejendomsgruppe( Color.GRAY );

        ejendom = new Ejendom("Bernstorffsvej",180,14,16);
        ejendom.setLejeHus(70,200,550,750);
        ejendom.setLejeHotel(950);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[16] = ejendom;

        proevLykken = new ProevLykken(17);
        felter[17] = proevLykken;

        ejendom = new Ejendom("Hellerupvej",180,14,18);
        ejendom.setLejeHus(70,200,550,750);
        ejendom.setLejeHotel(950);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[18] = ejendom;

        ejendom = new Ejendom("Strandvej",200,16,19);
        ejendom.setLejeHus(80,220,600,800);
        ejendom.setLejeHotel(1000);
        ejendom.setHusPris(100);
        ejendom.setHotelPris(100);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[19] = ejendom;

        friParkering = new FriParkering(20);
        felter[20] = friParkering;

        ejendomsGruppe = new Ejendomsgruppe( Color.red );

        ejendom = new Ejendom("Trianglen",220,18,21);
        ejendom.setLejeHus(90,250,700,875);
        ejendom.setLejeHotel(1050);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[21] = ejendom;

        proevLykken = new ProevLykken(22 );
        felter[22] = proevLykken;

        ejendom = new Ejendom("Østerbrogade",220,18,23);
        ejendom.setLejeHus(90,250,700,875);
        ejendom.setLejeHotel(1050);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[23] = ejendom;

        ejendom = new Ejendom("Grønningen",240,20,24);
        ejendom.setLejeHus(100,300,750,925);
        ejendom.setLejeHotel(1100);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[24] = ejendom;

        rederi = new Rederi("Ø.K",200, 25);
        felter[25] = rederi;

        ejendomsGruppe = new Ejendomsgruppe( Color.white );

        ejendom = new Ejendom("Bredgade",260,22,26);
        ejendom.setLejeHus(110,330,800,975);
        ejendom.setLejeHotel(1150);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[26] = ejendom;

        ejendom = new Ejendom("Kgs. Nytorv",260,22,27);
        ejendom.setLejeHus(110,330,800,975);
        ejendom.setLejeHotel(1150);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[27] = ejendom;

        bryggeri = new Bryggeri("Carlsberg",150, 28);
        bryggeri.setPris(150);
        felter[28] = bryggeri;

        ejendom = new Ejendom("Østergade",280,24,29);
        ejendom.setLejeHus(120,360,850,1025);
        ejendom.setLejeHotel(1200);
        ejendom.setHusPris(150);
        ejendom.setHotelPris(150);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[29] = ejendom;

        gaaIFaengsel = new GaaIFaengsel(30);
        felter[30] = gaaIFaengsel;

        ejendomsGruppe = new Ejendomsgruppe( Color.YELLOW );

        ejendom = new Ejendom("Amagertorv",300,26,31);
        ejendom.setLejeHus(130,390,900,1100);
        ejendom.setLejeHotel(1275);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[31] = ejendom;

        ejendom = new Ejendom("Vimmelskaftet",300,26,32);
        ejendom.setLejeHus(130,390,900,1100);
        ejendom.setLejeHotel(1275);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[32] = ejendom;

        proevLykken = new ProevLykken(33);
        felter[33] = proevLykken;

        ejendom = new Ejendom("Nygade", 320,28,34);
        ejendom.setLejeHus(150,450,1000,1200);
        ejendom.setLejeHotel(1400);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[34] = ejendom;

        rederi = new Rederi("Bornholm 1866",200,35);
        felter[35] = rederi;

        proevLykken = new ProevLykken(36);
        felter[36] = proevLykken;

        ejendomsGruppe = new Ejendomsgruppe( Color.MAGENTA );

        ejendom = new Ejendom("Frederiksberggade",350,35,37);
        ejendom.setLejeHus(175,500,1100,1300);
        ejendom.setLejeHotel(1500);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[37] = ejendom;

        statsSkat = new StatsSkat(38, 100);
        felter[38] = statsSkat;

        ejendom = new Ejendom("Rådhuspladsen",400,50,39);
        ejendom.setLejeHus(200,600,1400,1700);
        ejendom.setLejeHotel(2000);
        ejendom.setHusPris(200);
        ejendom.setHotelPris(200);
        ejendomsGruppe.tilfoejEjendom(ejendom);
        felter[39] = ejendom;

        return felter;
    }

}
