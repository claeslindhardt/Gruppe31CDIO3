import model.raflebaeger.RafleBaeger;
import model.raflebaeger.Terning;
import model.singletoner.ScannerSingleton;

public class FalskRaflebaeger extends RafleBaeger {



    public FalskRaflebaeger(int antalTerninger){
        super(antalTerninger);

    }

    @Override
    public int slaa(){
        ScannerSingleton scan = ScannerSingleton.getInstance();

        int tern1 = scan.nextInt();
        int tern2 = scan.nextInt();

        Terning[] terninger = getTerninger();
        terninger[0].setVaerdi(tern1);
        terninger[1].setVaerdi(tern2);

        setTotalVaerdi(tern1+tern2);

        return getTotalVaerdi();
    }

    @Override
    public boolean erEns(){
        return true;
    }

   /* @Override
    public boolean erEns() {
                return false;
    }


    public int getVaerdi() {
        return totalVaerdi; }
*/

}

