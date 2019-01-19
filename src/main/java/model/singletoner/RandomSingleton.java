package model.singletoner;

import java.util.Random;


public class RandomSingleton {
    private static RandomSingleton instance;
    private Random rand = new Random();


    /**
     * Constructor er private for at sikre,
     * at man ikke selv kan lave instanser af den, men
     * er nødt til at bruge getInstance()
     */
    private RandomSingleton(){}


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
