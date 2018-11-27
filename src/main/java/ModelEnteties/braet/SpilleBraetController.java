package ModelEnteties.braet;

import Controller.UserInterfaceKontrakt;

import ModelEnteties.braet.controllerKlasser.*;
import ModelEnteties.braet.dataKlasser.FeltDTO;
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
    public void printBret(UserInterfaceKontrakt userInterfaceKontrakt){

        for(int i = 0; i < this.getBret().size() ;i++){
            FeltDTO feltDTO = this.getBret().get(i);
            String felttyp = feltDTO.getFeltType();//printInfo();
            userInterfaceKontrakt.bretPrinter(felttyp);
        }
        userInterfaceKontrakt.terminalLine();
    }

    public String toString(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < this.getBret().size(); i++){

            if (i > 0){
                stringBuilder.append(" , ");
            }

            FeltDTO str = this.getBret().get(i);
            stringBuilder.append(str);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public ArrayList<ChanceAktion> ChanceKortsGenerator(int antalChancekort, UserInterfaceKontrakt userInterfaceKontrakt){

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
                userInterfaceKontrakt.chanceKortGenereringsFejl();
            }
        }

        return chanceKortTilFelt;

    }

    //|--------- Constructor:-----------------
    public SpilleBraetController(int antalFelter, UserInterfaceKontrakt userInterfaceKontrakt){

        boolean lavFelter=true;
        //-------Tilføjning af objekter til brettet---
        Start go = new Start(getStartGrundPris(),0);
        Faengsel kashotten = new Faengsel("Vester Fængsel",1);

        do {int startfelt = 0, ejendom = 0, chancefelt = 0, faengsel = 0, gaaIFaengsel = 0, jernbane = 0, taxi = 0;
            getBret().add(go);

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
                // ChanceFeltCO
                else if(aktionsFeltType<=6) {//set til 6 når test er fertig
                    ChanceFeltCO chance = new ChanceFeltCO(i+2,ChanceKortsGenerator(getStandardAntalChanceKortPrFelt(), userInterfaceKontrakt));
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
            // EjendomCO
            else{
                EjendomsDoeber navn = new EjendomsDoeber();
                EjendomCO grund = new EjendomCO(navn.getGeneretNavn(),getStartGrundPris(),getStandardLeje(),i+2);
                EjendomsGruppeCO gruppe = getEjendomsGruppeManager().tilfoejTilGruppe(grund);
                grund.setGruppe(gruppe);
                getBret().add(grund);
            }
            setStartGrundPris(getStartGrundPris()+getPrisStigningAfEjendomme());
            setStandardLeje(getStandardLeje()+getPrisStigningAfEjendomme());
        }
            for (int j = 0; j < getBret().size(); j++) {
                if (getBret().get(j) instanceof Start) {
                    startfelt++;
                } else if (getBret().get(j) instanceof EjendomCO) {
                    ejendom++;
                } else if (getBret().get(j) instanceof ChanceFeltCO) {
                    chancefelt++;
                } else if (getBret().get(j) instanceof Faengsel) {
                    faengsel++;
                } else if (getBret().get(j) instanceof GaaIFaengsel) {
                    gaaIFaengsel++;
                } else if (getBret().get(j) instanceof Jernbane) {
                    jernbane++;
                } else if (getBret().get(j) instanceof Taxi) {
                    taxi++;
                }

            }
            if(jernbane>2&&taxi==1&&gaaIFaengsel==1&&chancefelt>1){break;}else{getBret().clear();getJernbaner().clear();}
        }while(lavFelter);
    }
}