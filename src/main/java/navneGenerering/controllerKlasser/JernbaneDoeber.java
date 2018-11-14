package navneGenerering.controllerKlasser;

import navneGenerering.DataKlasser.NavneGenerator;

public class JernbaneDoeber extends NavneGenerator {
    int vejnavn = getRand().nextInt(getVejNavn().length);
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
