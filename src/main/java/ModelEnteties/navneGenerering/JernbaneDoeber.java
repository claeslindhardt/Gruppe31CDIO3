package ModelEnteties.navneGenerering;

/**
 * Denne 'børneklasse' nedaver fra NavneGeneratoren
 * og har til formål at navngive Jermbaner.
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
