import model.raflebaeger.RafleBaeger;
import model.raflebaeger.Terning;

import java.util.Scanner;

public class FalskRaflebaeger extends RafleBaeger {

    private Scanner scanner = new Scanner( System.in );

    public FalskRaflebaeger(int antalTerninger){
        super(antalTerninger);

    }

    @Override
    public void slaa(){

        int tern1 = scanner.nextInt();
        int tern2 = scanner.nextInt();

        Terning[] terninger = getTerninger();
        terninger[0].setVaerdi(tern1);
        terninger[1].setVaerdi(tern2);

        setTotalVaerdi(tern1+tern2);
    }

    @Override
    public boolean erEns(){
        return false;
    }

}

