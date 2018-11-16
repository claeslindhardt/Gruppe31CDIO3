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

            this.printInfo();

            System.out.println("Det er en ejendom vil du købe den?"+
                    "\nJa(1), nej(2)"
            );
            int kobsBeslutning = sc.nextInt();
            switch (kobsBeslutning){
                case 1:
                    spillerMedTur.koebEjendom(this, userInterface);
                    break;
                case 2:
                    userInterface.forsetTur();

                    break;
                default:
                    System.out.println("ikke en mulighed endnu, men skriv gerne til os hvis der er noget du vil have");
            }
        }else if(this.getEjer() != null && this.getEjer() != spillerMedTur){
            userInterface.betalRente();
            this.indsamleLeje(spillerMedTur);
        }else if(this.getEjer() == spillerMedTur){
            userInterface.tetPaaMonopol();
        }
    }

    public void indsamleLeje(SpillerController spilleren){
        SpillerController ejeren = this.getEjer();
        if( ejeren != null && spilleren != null) {
            //todo: enkapsuler dette på en ordenligt måde
            spilleren.setPenge(spilleren.getPenge()-getLeje());
            ejeren.addPenge(getLeje());  // hvis Spiller ikke har nok penge til at betale skal den have mulighed for at pantsætte
        }else{
            System.out.println("ERROR: WOOPS, TRIED TO COLLECTRENT WHEN PLAYER OBJECT WAS EMPTY!");
        }
    }
    //|--------- Constructor:-----------------
    public Ejendom(String whatName, int whatPrice, int whatRent, int placering){
        setPlacering(placering);
        setPris(whatPrice);
        setNavn(whatName);
        setLeje(whatRent);
        setGruppe(EjendomsGruppe.get(this)); // Getting a group for this property
        addAlleEjendomme(this);
        setAntalHoteller(0);
        setAntalHuse(0);
        setFeltType("Ejendom");
    }
}
