package ModelEnteties.navneGenerering;

/**
 * Denne 'børneklasse' nedaver fra NavneGeneratoren
 *  * og har til formål at navngive Ejendomme.
 */
public class EjendomsDoeber extends NavneGenerator {
    public EjendomsDoeber(){
        int vejnavn =  getRandomTal().nextInt(getVejNavn().length);
        int vejtype =  getRandomTal().nextInt(getVejType().length);
        setGeneretNavn(getVejNavn()[vejnavn].concat(getVejType()[vejtype]));
        for(int i = 0; i<getAlleNavne().size(); i++){
            if(getGeneretNavn()==getAlleNavne().get(i)){
                setGeneretNavn(getVejNavn()[vejnavn].concat(" 2'nd ").concat(getVejType()[vejtype]));
            }
        }
        addAlleNavne(getGeneretNavn());
    }
}
