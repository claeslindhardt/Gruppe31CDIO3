package model;

import model.felter.Ejendom;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Malte
 *  Dette er klassen der holder styr på grupper af ejendomme. En Ejendomsgruppe er et objekt, som identificeres
 *  ved sin farve.
 */
public class Ejendomsgruppe {

    private Color   farve;
    private ArrayList<Ejendom> ejendomme = new ArrayList<Ejendom>();

    /**
     * @author Malte
     * Laver en ny tom ejendomsgruppe, der kan indeholde 'stoerrelse' antal ejendomme i sig.
     * Gruppen kendetegnes derudover ved sin farve.
     *
     * @param farve Farven som skal kendetegne gruppen (givet kun en visuel sammenligning af gruppen,
     *              og har ingen betydning for hvordan Ejendommene vurderes om de er i samme gruppe.
     */
    public Ejendomsgruppe(Color farve) {
        this.farve = farve;
    }

    //--------- Getters og Setters:-----------------

    public Color getFarve() {
        return farve;
    }

    public ArrayList<Ejendom> getEjendomme() {
        return ejendomme;
    }

    public int getAntalEjendomme(){ return ejendomme.size(); }

    public void tilfoejEjendom( Ejendom ejendom){
        ejendomme.add(ejendom);
        ejendom.setGruppe(this);
    }

}
