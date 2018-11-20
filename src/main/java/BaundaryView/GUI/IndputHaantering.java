package BaundaryView.GUI;

import ModelEnteties.singletoner.ScannerSingleton;

public class IndputHaantering {
    public int velkomstMenu(){
        ScannerSingleton scan = ScannerSingleton.getInstance();
        int menuOpt = scan.nextInt();
        return menuOpt;
    }
}
