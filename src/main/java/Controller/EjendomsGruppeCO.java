package Controller;


import ModelEnteties.felter.EjendomCO;
import ModelEnteties.EjendomsGruppeDTO;

import java.awt.*;

/**
 * @author Malte
 * Klasse der opretter grupper til ejendomme, og putter ejendomme i grupper.
 * Den gør det ved at fylde en gruppe op først, og opretter kun en ny gruppe,
 * når den tidligere gruppe er fyldt.
 */

public class EjendomsGruppeCO {

    private int gruppeStoerelser;
    private Color[] farveListe = {Color.red, Color.green,Color.BLUE,Color.YELLOW ,Color.ORANGE,Color.PINK,Color.GRAY,Color.MAGENTA};
    private int antalGrupper = 0; // Hvor mange grupper der er blevet lavet.
    private EjendomsGruppeDTO klarGruppe;


    public EjendomsGruppeCO(int gruppeStoerelser){
        // Sikrer at man ikke har grupper der er mindre end de kan være.
        if(gruppeStoerelser < 1 ){
            this.gruppeStoerelser = 1;
        }else{
            this.gruppeStoerelser = gruppeStoerelser;
        }
    }

    /**
     * @author Malte
     * Henter størrelsen på de grupper, som manageren opretter.
     * @return Størrelsen på grupperne (gruppeStoerrelse)
     */
    public int getGruppeStoerelser() {
        return gruppeStoerelser;
    }

    /**
     * @author Malte.
     * Opretter en ny Ejendomsgruppe, ved at give den specifik farve, og en stoerrelse med udgangspunkt i EGManageren.
     * @return Den nyopprettede gruppe.
     */
    private EjendomsGruppeDTO opretGruppe(){
        antalGrupper++;
        Color farve = farveListe[antalGrupper%farveListe.length];
        EjendomsGruppeDTO nyGruppe = new EjendomsGruppeDTO(farve, gruppeStoerelser);
        return nyGruppe;
    }

    /**
     * Tilfoejer en ejendom til den nuværende ledige gruppe.
     * @param ejendom Hvilken ejendom man gerne vil tilfoeje til en gruppe.
     * @return Ejendommen som gruppen er blevet tilfoejet til.
     */
    public EjendomsGruppeDTO tilfoejTilGruppe(EjendomCO ejendom){
        // Undersøger om den 'klarGruppe' faktisk er klar, eller om der skal oprettes en ny.
        if( klarGruppe == null || klarGruppe.erFuld() ){
            klarGruppe = opretGruppe();
            // ... laver ny gruppe
        }
        klarGruppe.tilfoejEjendom(ejendom);
        ejendom.setGruppe(klarGruppe);
        return klarGruppe;
    }

    public void clearKlarGruppe(){klarGruppe = null;}

}
