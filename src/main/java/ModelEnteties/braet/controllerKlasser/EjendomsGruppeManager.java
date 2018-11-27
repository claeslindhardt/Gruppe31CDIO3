package ModelEnteties.braet.controllerKlasser;


/**
 * @author Malte
 * Klasse der opretter grupper til ejendomme, og putter ejendomme i grupper.
 * Den gør det ved at fylde en gruppe op først, og opretter kun en ny gruppe,
 * når den tidligere gruppe er fyldt.
 */

public class EjendomsGruppeManager{

    private int gruppeStoerelser;
    private String[] farveListe = {"roed", "groen", "blaa","gul" ,"orange","lilla","lyseroed","sort"};
    private int antalGrupper = 0; // Hvor mange grupper der er blevet lavet.
    private EjendomsGruppeCO klarGruppe;


    public EjendomsGruppeManager(int gruppeStoerelser){
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
     * @author Malte
     * Opretter en ny Ejendomsgruppe, ved at give den specifik farve, og en stoerrelse med udgangspunkt i EGManageren.
     * @return Den nyopprettede gruppe.
     */
    private EjendomsGruppeCO opretGruppe(){
        antalGrupper++;
        String farve = farveListe[antalGrupper%farveListe.length];
        EjendomsGruppeCO nyGruppe = new EjendomsGruppeCO(farve, gruppeStoerelser);
        return nyGruppe;
    }

    /**
     * Tilfoejer en ejendom til den nuværende ledige gruppe.
     * @param ejendom Hvilken ejendom man gerne vil tilfoeje til en gruppe.
     * @return Ejendommen som gruppen er blevet tilfoejet til.
     */
    public EjendomsGruppeCO tilfoejTilGruppe(EjendomCO ejendom){
        // Undersøger om den 'klarGruppe' faktisk er klar, eller om der skal oprettes en ny.
        if( klarGruppe == null || !klarGruppe.erFuld() ){
            klarGruppe = opretGruppe();
            // ... laver ny gruppe
        }
        klarGruppe.tilfoejEjendom(ejendom);
        return klarGruppe;
    }

}
