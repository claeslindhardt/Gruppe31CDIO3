package model.singletoner;

import java.util.Random;

/**
 * Indsæt beskrivelse her
 */
public class RandomSingleton {
    private static RandomSingleton instance;
    private Random rand;

    private RandomSingleton(){
        rand = new Random();
    }

    public static RandomSingleton getInstance() {
        if (instance == null) {
            instance = new RandomSingleton();

        }
        return instance;
    }
    public int nextInt(int grense) {
        return rand.nextInt(grense);
    }

}
