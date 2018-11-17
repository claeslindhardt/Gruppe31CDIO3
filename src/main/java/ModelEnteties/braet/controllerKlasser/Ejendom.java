package ModelEnteties.braet.controllerKlasser;

import Controller.SpilController;
import Controller.UserInterface;
import ModelEnteties.Spiller.SpillerController;
import ModelEnteties.braet.dataKlasser.EjeligtFelt;

import java.util.Scanner;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: Ejendom
 *
 */
public class Ejendom extends EjeligtFelt {
    //|-------initiering af objekter: -----------
    Scanner sc = new Scanner(System.in);
    //TODO: fix singleton

    //|--------- Variabler:-----------------
    int antalHuse;
    int antalHoteller;
    double husPris = 50;
    int leje = 50;

    //|--------- Getters og Setters:-----------------
    public int getAntalHuse() {
        return antalHuse;
    }

    public void setAntalHuse(int antalHuse) {
        this.antalHuse = antalHuse;
    }

    public int getAntalHoteller() {
        return antalHoteller;
    }

    public void setAntalHoteller(int antalHoteller) {
        this.antalHoteller = antalHoteller;
    }

    public double getHusPris() {
        return husPris;
    }

    public void setHusPris(double husPris) {
        this.husPris = husPris;
    }

    public int getLeje() {
        return leje;
    }

    public void setLeje(int leje) {
        this.leje = leje;
    }


    //|----------- Metoder:------------------
    public void printInfo(UserInterface userInterface){
        userInterface.ejendomsInfo(this);
    }
    public void aktionPaaFelt(SpilController spil, UserInterface userInterface){
        SpillerController spillerMedTur = spil.getSpillerMedTur();
        if(this.getEjer()==null){
            userInterface.duErLandetPå();
            this.printInfo();

            userInterface.ejendomsBud();
            int kobsBeslutning = sc.nextInt();
            switch (kobsBeslutning){
                case 1:
                    spillerMedTur.koebEjendom(this, userInterface);
                    break;
                case 2:
                    userInterface.forsetTur();

                    break;
                default:
                    userInterface.ikkeMuligt();
            }
        }else if(this.getEjer() != null && this.getEjer() != spillerMedTur){
            userInterface.betalRente();
            this.indsamleLeje(spillerMedTur,userInterface);
        }else if(this.getEjer() == spillerMedTur){
            userInterface.tetPaaMonopol();
        }
    }

    public void indsamleLeje(SpillerController spilleren,UserInterface userInterface){
        SpillerController ejeren = this.getEjer();
        if( ejeren != null && spilleren != null) {
            //todo: enkapsuler dette på en ordenligt måde
            spilleren.setPenge(spilleren.getPenge()-getLeje());
            ejeren.addPenge(getLeje());  // hvis Spiller ikke har nok penge til at betale skal den have mulighed for at pantsætte
        }else{
            userInterface.badErrorMessage();
        }
    }
    //|--------- Constructor:-----------------
    public Ejendom(String whatName, int whatPrice, int whatRent, int placering){
        setPlacering(placering);
        setPris(whatPrice);
        setNavn(whatName);
        setLeje(whatRent);

        EjendomsGruppe ejendomsGruppe = EjendomsGruppe.getKlarGruppe();
        setGruppe(ejendomsGruppe); // Getting a group for this property
        ejendomsGruppe.tilfoejEjendom(this);

        addAlleEjendomme(this);
        setAntalHoteller(0);
        setAntalHuse(0);
        setFeltType("Ejendom");
    }
}
