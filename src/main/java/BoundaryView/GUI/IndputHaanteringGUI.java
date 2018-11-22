package BoundaryView.GUI;

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
    public int TurMenu(String valg){
        int beslutning = 0;
        if(valg == "Kast terninger"){
            beslutning = 1;
        }else if(valg == "Slut din tur"){
            beslutning = 2;
        }else if(valg == "Se chancekort"){
            beslutning = 3;
        }else if(valg == "Se hvad du ejer"){
            beslutning = 2;
        }else if(valg == "Se spiller stats"){
            beslutning = 6;
        }else if(valg == "Giv op"){
            beslutning = 7;
        }else if(valg == "Byg på ejendom"){
            beslutning = 8;
        }else if(valg == "Handel med Ejede ting"){
            beslutning = 9;
        }
        return beslutning;
    }

    public int binartValg(String valg){
        int beslutning = 0;
        if(valg == "ja"){
            beslutning = 1;
        }else if(valg == "nej"){
            beslutning = 2;
        }
        return beslutning;
    }


}
