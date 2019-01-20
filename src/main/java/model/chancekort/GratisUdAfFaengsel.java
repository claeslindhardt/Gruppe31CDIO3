package model.chancekort;


/**
 * Chancekort der lader dig komme gratis ud af faengslet.
 */
public class GratisUdAfFaengsel extends Chancekort {

    public GratisUdAfFaengsel(String beskrivelse ){
        super( beskrivelse, false );
        setKortBeskrivelse("Gratis ud af faengslet");
    }
}
