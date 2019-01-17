package model.raflebaeger;
//TODO: del denne op i en data og en kontroller klasse
import model.singletoner.RandomSingleton;
/**___________________________________________________________________________________________________________________
 * PROGRAMDOKUMENTATION: raflebaeger
 * @author Claes
 * Denne klasses formål er at lave objekter som indeholder en verdi,
 * som kan ændre sig(inden for en ramme) når man bruger dens slaa funktion.
 */
public class Terning {
    //|--------- Variabler:-----------------
    private int vaerdi=1;
    //Singleton Variabler;
    private RandomSingleton randomTal = RandomSingleton.getInstance();
    //|--------- Getters og Setters:--------
    public int getVaerdi() {
        return vaerdi; }

    public void setVaerdi(int vaerdi){
        this.vaerdi = vaerdi;
    }

    //|----------- Metoder:-----------------
    public int slaa(){
        vaerdi = randomTal.nextInt(6)+1;
        return vaerdi;
    }
}
