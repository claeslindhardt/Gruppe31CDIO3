package model.raflebaeger;


public class RafleBaeger {

    private Terning[] terninger;
    private int         totalVaerdi = 0;
    private int         antalTerninger;



    /** Laver et raflebæger, der indeholder 'antalTerninger' terninger (se raflebaeger klasse).
     *  Den laver nye terninge objekter.
     *
     * @param antalTerninger Et fast antal terninger i bægeret - kan ikke ændres
     */
    public RafleBaeger(int antalTerninger){
        this.antalTerninger = antalTerninger;
        terninger = new Terning[antalTerninger];
        for( int i=0; i<antalTerninger; i++){
            terninger[i] = new Terning();
        }
    }




    /** Slaar med ALLE terninger i bægeret, og sætter bægerets
     *  'totalVaerdi' til summen af de individuelle terningers
     *  vaerdier.
     *  Den kalder alle terningeobjekters egen 'slaa()' metode.
     */
    public void slaa(){
        int sum = 0;
        for(int i=0; i<antalTerninger; i++){
            sum += terninger[i].slaa();
        }
        totalVaerdi = sum;
    }



    /**
     * Undersøger om ALLE terninger i bægeret har den samme vaerdi.
     * Hvis der kun er én terning, vil dette altid være sandt.
     *
     * @return Om alle terninger er ens
     */
    public boolean erEns() {
        int foersteElement = terninger[0].getVaerdi();
        for(int i=1; i<antalTerninger; i++){
            if(foersteElement != terninger[i].getVaerdi()){
                return false;
            }
        }
        return true;
    }



    /**
     * Henter terningen ud fra terningens index i Raflebægeret.
     * @param terningeIndex Terningens index (0 - (antalTerninger-1))
     * @return Terningen som objekt - her kan hentes terningens specifikke vaerdi
     */
    public Terning getTerning(int terningeIndex){
        if( terningeIndex >= terninger.length ){
            terningeIndex = terninger.length-1;
        }else if(terningeIndex<0){
            terningeIndex = 1;
        }
        return terninger[terningeIndex];
    }



    /** @return Den nuværende sum af terningerns vaerdier
     */
    public int getTotalVaerdi() {


        return totalVaerdi;
    }

    public Terning[] getTerninger() {
        return terninger;
    }

    public void setTotalVaerdi(int totalVaerdi) {
        this.totalVaerdi = totalVaerdi;
    }
}
