package BaundaryView.GUI;

import ModelEnteties.singletoner.ScannerSingleton;

public class IndputHaanteringGUI {
    public int velkomstMenu(String valg){
        int menuOpt = 1;
        if(valg == "starte nyt spil"){
            menuOpt = 1;
        }else if(valg == "aendre spil instillinger"){
            menuOpt = 2;
        }else if(valg == "forsaette sidste spil"){
            menuOpt = 3;
        }
        return menuOpt;
    }
}
