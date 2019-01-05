package ModelEnteties.braet.navneGenerering.controllerKlasser;

import ModelEnteties.braet.navneGenerering.DataKlasser.NavneGenerator;

/**
 * Indsæt beskrivelse her
 */
public class JernbaneDoeber extends NavneGenerator {
    int vejnavn = getRandomTal().nextInt(Math.round(getVejNavn().length));
    public JernbaneDoeber(){
        setGeneretNavn(getVejNavn()[vejnavn].concat(" Station"));
        for(int i = 0; i<getAlleNavne().size(); i++){
            if(getGeneretNavn() == getAlleNavne().get(i)){
                setGeneretNavn(getVejNavn()[vejnavn].concat(" 2'nd ").concat(" Station"));
            }
        }
        addAlleNavne(getGeneretNavn());
    }
}
