package ModelEnteties.singletoner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerSingleton {
    private static ScannerSingleton instance;
    private Scanner scan;

    private ScannerSingleton(){
        scan = new Scanner(System.in);
    }

    public static ScannerSingleton getInstance() {
        if (instance == null) {
            instance = new ScannerSingleton();

        }
        return instance;
    }

    /**
     * Fungerer ligesom en Scanners nextLine()
     * @return Den String der er blevet inputtet
     */
    public String nextLine(){
        return  scan.nextLine();

    }


    /**
     * Fungerer ligesom Scanner-klassens egen nextInt()
     * @return Det input som brugeren taster ind.
     * @throws InputMismatchException Hvis det er et forkert input (ikke en int), smides denne exception ligesom en almindelig scanner
     */
    public int nextInt() throws InputMismatchException {
        return scan.nextInt();
        /*try {
            return scan.nextInt();
        }catch(InputMismatchException exception){
            throw exception; // rethrowing the exception
        }*/
    }
}
