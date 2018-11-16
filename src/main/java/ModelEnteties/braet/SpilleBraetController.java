package ModelEnteties.braet;

import Controller.UserInterface;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.controllerKlasser.*;
import ModelEnteties.braet.dataKlasser.Felt;
import ModelEnteties.braet.navneGenerering.controllerKlasser.EjendomsDoeber;
import ModelEnteties.braet.navneGenerering.controllerKlasser.JernbaneDoeber;
import ModelEnteties.chanceKort.controllerKlasser.GiverPenge;
import ModelEnteties.chanceKort.controllerKlasser.GratisUdAfFaengsel;
import ModelEnteties.chanceKort.controllerKlasser.RykkerSpiller;
import ModelEnteties.chanceKort.controllerKlasser.TagerPenge;
import ModelEnteties.chanceKort.dataKlasser.ChanceAktion;

import java.util.ArrayList;

public class SpilleBraetController extends SpilleBraetData {

    //|----------- Metoder:------------------
    public void printBret(UserInterface userInterface){

        for(int i = 0; i < this.getBret().size() ;i++){
            Felt felt = this.getBret().get(i);
            String felttyp = felt.getFeltType();//printInfo();
            userInterface.bretPrinter(felttyp);
        }
        userInterface.terminalLine();
    }

    public ArrayList<ChanceAktion> ChanceKortsGenerator(int antalChancekort, UserInterface userInterface){
        ArrayList<ChanceAktion> chanceKortTilFelt = new ArrayList<ChanceAktion>();

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
                    GratisUdAfFaengsel kaution = new GratisUdAfFaengsel();
                    chanceKortTilFelt.add(kaution);
                    break;
                default:
                userInterface.chanceKortGenereringsFejl();
            }
        }

        return chanceKortTilFelt;

    }

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
