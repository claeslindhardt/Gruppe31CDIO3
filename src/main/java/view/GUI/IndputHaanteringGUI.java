package view.GUI;

import gui_main.GUI;

/**
 * Indsæt beskrivelse her
 */
public class IndputHaanteringGUI {
    public int velkomstMenu(String valg) {
        int menuOpt = 1;
        if (valg == "starte nyt spil") {
            menuOpt = 1;
        } else if (valg == "aendre spil instillinger") {
            menuOpt = 2;
        } else if (valg == "forsaette sidste spil") {
            menuOpt = 3;
        }
        return menuOpt;
    }

    public int TurMenu(String valg) {
        int beslutning = 0;
        if (valg == "Kast terninger") {
            beslutning = 1;
        } else if (valg == "Slut din tur") {
            beslutning = 2;
        } else if (valg == "Se chancekort") {
            beslutning = 3;
        } else if (valg == "Se hvad du ejer") {
            beslutning = 4;
        } else if (valg == "Se spiller stats") {
            beslutning = 6;
        } else if (valg == "Giv op") {
            beslutning = 7;
        } else if (valg == "Byg på ejendom") {
            beslutning = 8;
        } else if (valg == "Byg hotel") {
            beslutning = 9;
        } else if (valg == "Handel med Ejede ting") {
            beslutning = 10;
        } else if (valg == "Sælg hus på ejendommen") {
            beslutning = 11;
        }else if (valg == "Sælg Hotel på ejendommen") {
            beslutning = 12;}
        return beslutning;
    }

    public int binartValg(String valg) {
        int beslutning = 0;
        if (valg == "ja") {
            beslutning = 1;
        } else if (valg == "nej") {
            beslutning = 2;
        }
        return beslutning;
    }


    public int binaertValg( String besked, String valg1, String valg2, GUI gui ){
        String valg = gui.getUserButtonPressed( besked,
                valg1, valg2);

        if( valg.equals(valg1) ){
            return 0;
        }else {
            return 1;
        }
    }

}


