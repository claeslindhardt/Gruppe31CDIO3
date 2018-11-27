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
    public String nextLine(){ return  scan.nextLine(); }

    public int nextInt() {
        try {
            return scan.nextInt();
        }catch(InputMismatchException exception){
            throw exception; // rethrowing the exception
        }
    }
}
