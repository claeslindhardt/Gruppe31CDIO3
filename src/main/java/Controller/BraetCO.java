package Controller;

import BoundaryView.UserInterfaceKontrakt;

import ModelEnteties.felter.EjendomCO;
import ModelEnteties.BraetDTO;
import ModelEnteties.felter.FeltDTO;
import ModelEnteties.NavneGenerator;
import ModelEnteties.felter.ChanceAktionDTO;

import java.util.ArrayList;

/**
 * Indsæt beskrivelse her
 */
public class BraetCO extends BraetDTO {

    //|----------- Metoder:------------------
    public void printBret(UserInterfaceKontrakt userInterfaceKontrakt){

        for(int i = 0; i < this.getBret().size() ;i++){
            FeltDTO feltDTO = this.getBret().get(i);
            String felttyp = feltDTO.getFeltType();//printInfo();
            userInterfaceKontrakt.bretPrinter(felttyp);
        }
        userInterfaceKontrakt.terminalLine();
    }

    /**
     * Indsæt beskrivelse her
     * @return
     */
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

    /**
     * Indsæt beskrivelse her
     * @param antalChancekort
     * @param userInterfaceKontrakt
     * @return
     */
    public ArrayList<ChanceAktionDTO> ChanceKortsGenerator(int antalChancekort, UserInterfaceKontrakt userInterfaceKontrakt){

        ArrayList<ChanceAktionDTO> chanceKortTilFelt = new ArrayList<ChanceAktionDTO>();

        //ændre dette:
        ChanceAktionDTO some = new GiverPengeCO();
        chanceKortTilFelt.add(some);
        //til dette

        for(int i = 0;i<antalChancekort;i++){
            int valgAfKortType = ra.nextInt(4)+1;
            switch (valgAfKortType){//ændre denne til valgAfKortType når du er fertig med at teste
                //_______________________________________________
                // Giver penge
                case 1:
                    GiverPengeCO faaPenge = new GiverPengeCO();
                    chanceKortTilFelt.add(faaPenge);
                    break;
                //_______________________________________________
                // Tager penge fra dig
                case 2:
                    TagerPengeCO mistPenge = new TagerPengeCO();
                    chanceKortTilFelt.add(mistPenge);
                    //_______________________________________________
                    // Du må rykke som du ønsker
                case 3:
                    RykkerSpillerCO rykkerDig = new RykkerSpillerCO();
                    chanceKortTilFelt.add(rykkerDig);
                    break;
                //_______________________________________________
                // Du kan slippe for fængsel
                case 4:
                    GratisUdAfFaengselCO kaution = new GratisUdAfFaengselCO();
                    chanceKortTilFelt.add(kaution);
                    break;
                default:
                userInterfaceKontrakt.chanceKortGenereringsFejl();
            }
        }

        return chanceKortTilFelt;

    }

    //|--------- Constructor:-----------------

    /**
     * Indsæt beskrivelse her
     * @param antalFelter
     * @param userInterfaceKontrakt
     */
    public BraetCO(int antalFelter, UserInterfaceKontrakt userInterfaceKontrakt){

        boolean lavFelter=true;
        //-------Tilføjning af objekter til brettet---
        StartCO go = new StartCO(getStartGrundPris(),0);
        FaengselCO kashotten = new FaengselCO("Vester Fængsel",1);

        do {int startfelt = 0, ejendom = 0, chancefelt = 0, faengsel = 0, gaaIFaengsel = 0, jernbane = 0, taxi = 0;
            getBret().add(go);

            getBret().add(kashotten);

            NavneGenerator navn = new NavneGenerator();
            for(int i =0;i<antalFelter-1;i++){
                int feltType = ra.nextInt(8)+1;
                if (feltType<=3){//set til 3 når test er fertig
                    int aktionsFeltType = ra.nextInt(8)+1;
                    //_______________________________________________
                    // JernbaneCO
                    if(aktionsFeltType<=4){//set til 4 når test er fertig
                        NavneGenerator st = new NavneGenerator();
                        JernbaneCO station = new JernbaneCO(st.getJernbaneNavn(),getStartGrundPris(),i+2);
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
                    // TaxiCO
                    else if(aktionsFeltType<=7) {//set til 7 når test er fertig
                        TaxiCO vogn = new TaxiCO(i+2);
                        getBret().add(vogn);
                    }
                    //_______________________________________________
                    // GaaIFaengselCO
                    else {
                        GaaIFaengselCO forbrydelse = new GaaIFaengselCO(i+2);
                        getBret().add(forbrydelse);
                    }
                }
                //_______________________________________________
                // EjendomCO
                else{
                    EjendomCO grund = new EjendomCO(navn.getEjendomsNavn(),getStartGrundPris(),getStandardLeje(),i+2);
                    getBret().add(grund);
                }
                setStartGrundPris(getStartGrundPris()+getPrisStigningAfEjendomme());
                setStandardLeje(getStandardLeje()+getPrisStigningAfEjendomme());
            }
                for (int j = 0; j < getBret().size(); j++) {
                    if (getBret().get(j) instanceof StartCO) {
                        startfelt++;
                    } else if (getBret().get(j) instanceof EjendomCO) {
                        ejendom++;
                    } else if (getBret().get(j) instanceof ChanceFeltCO) {
                        chancefelt++;
                    } else if (getBret().get(j) instanceof FaengselCO) {
                        faengsel++;
                    } else if (getBret().get(j) instanceof GaaIFaengselCO) {
                        gaaIFaengsel++;
                    } else if (getBret().get(j) instanceof JernbaneCO) {
                        jernbane++;
                    } else if (getBret().get(j) instanceof TaxiCO) {
                        taxi++;
                    }

                }
                if(ejendom > 6 && jernbane > 2 && jernbane < 4 && taxi == 1 && gaaIFaengsel == 1 && chancefelt > 1){

                    // Finder ejendomme og tilfoejer dem til en liste, som så bliver gemt i braettet
                    EjendomCO[] ejendomme = new EjendomCO[ejendom];
                    int taeller = 0;
                    for( FeltDTO felt : getBret() ){
                        if( felt instanceof EjendomCO ){
                            ejendomme[taeller] = (EjendomCO) felt;
                            taeller++;
                        }
                    }
                    setEjendomme( ejendomme );


                    break;

                }else{getBret().clear();getJernbaner().clear();
                    getEjendomsGruppeCO().clearKlarGruppe();setStartGrundPris(200);setStandardLeje(50);}

        }while(lavFelter);
    }
}