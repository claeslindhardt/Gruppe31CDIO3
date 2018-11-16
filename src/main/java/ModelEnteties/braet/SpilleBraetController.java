package ModelEnteties.braet;

import ModelEnteties.braet.controllerKlasser.*;
import ModelEnteties.braet.navneGenerering.controllerKlasser.EjendomsDoeber;
import ModelEnteties.braet.navneGenerering.controllerKlasser.JernbaneDoeber;

import java.util.ArrayList;

public class SpilleBraetController extends SpilleBraetData {
    /*
    //|----------- Metoder:------------------
    public static void printBret(BretGenerator RelevantBret){

        for(int i = 0; i < RelevantBret.bret.size() ;i++){
            Felt felt = RelevantBret.bret.get(i);
            System.out.println("______________________________________________________________________________");
            felt.getFeltType();//printInfo();
        }
        System.out.println("______________________________________________________________________________");
    }
    public ArrayList<ChanceAktion> ChanceKortsGenerator(int antalChancekort){
        ArrayList<ChanceAktion> chanceKortTilFelt = new ArrayList<>();

        //ændre dette:
        ChanceAktion some = new GiverPenge();
        chanceKortTilFelt.add(some);
        //til dette

        for(int i = 0;i<antalChancekort;i++){
            int valgAfKortType = ra.nextInt(4)+1;
            switch (valgAfKortType){//ændre denne til valgAfKortType når du er fertig med at teste
                //_______________________________________________
                // Giver penge
                case 1:
                    GiverPenge faaPenge = new GiverPenge();
                    chanceKortTilFelt.add(faaPenge);
                    break;
                //_______________________________________________
                // Tager penge fra dig
                case 2:
                    TagerPenge mistPenge = new TagerPenge();
                    chanceKortTilFelt.add(mistPenge);
                    //_______________________________________________
                    // Du må rykke som du ønsker
                case 3:
                    RykkerSpiller rykkerDig = new RykkerSpiller();
                    chanceKortTilFelt.add(rykkerDig);
                    break;
                //_______________________________________________
                // Du kan slippe for fængsel
                case 4:
                    GratisUdafFeangsel kaution = new GratisUdafFeangsel();
                    chanceKortTilFelt.add(kaution);
                    break;
                default:
                    System.out.println("Der var et problem med generering af ChanceKort, på et specifikt felt.");
            }
        }

        return chanceKortTilFelt;

    }
    */
    /** Latent code under construction
    //|--------- Constructor:-----------------
    public SpilleBraetController(int antalFelter){

        //-------Tilføjning af objekter til brettet---
        Start go = new Start(getStartGrundPris(),0);
        getBret().add(go);
        Faengsel kashotten = new Faengsel("Vester Fængsel",1);
        getBret().add(kashotten);

        for(int i =0;i<antalFelter-1;i++){
            int feltType = ra.nextInt(8)+1;
            if (feltType<=3){//set til 3 når test er fertig
                int aktionsFeltType = ra.nextInt(8)+1;
                //_______________________________________________
                // Jernbane
                if(aktionsFeltType<=4){//set til 4 når test er fertig
                    JernbaneDoeber st = new JernbaneDoeber();
                    Jernbane station = new Jernbane(st.getGeneretNavn(),getStartGrundPris(),i+2);
                    getBret().add(station);
                    getJernbaner().add(station);
                }
                //_______________________________________________
                // ChanceFelt
                else if(aktionsFeltType<=6) {//set til 6 når test er fertig
                    ChanceFelt chance = new ChanceFelt(i+2,ChanceKortsGenerator(getStandardAntalChanceKortPrFelt()));
                    addBret(chance);
                }
                //_______________________________________________
                // Taxi
                else if(aktionsFeltType<=7) {//set til 7 når test er fertig
                    Taxi vogn = new Taxi(i+2);
                    getBret().add(vogn);
                }
                //_______________________________________________
                // GaaIFaengsel
                else {
                    GaaIFaengsel forbrydelse = new GaaIFaengsel(i+2);
                    getBret().add(forbrydelse);
                }
            }
            //_______________________________________________
            // Ejendom
            else{
                EjendomsDoeber navn = new EjendomsDoeber();
                Ejendom grund = new Ejendom(navn.getGeneretNavn(),getStartGrundPris(),getStandardLeje(),i+2);
                getBret().add(grund);
            }
            setStartGrundPris(getStartGrundPris()+getPrisStigningAfEjendomme());
            setStandardLeje(getStandardLeje()+getPrisStigningAfEjendomme());
        }


    }*/
}
