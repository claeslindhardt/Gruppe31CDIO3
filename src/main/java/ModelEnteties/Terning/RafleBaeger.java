package ModelEnteties.Terning;

import java.util.ArrayList;

public class RafleBaeger {
    //|--------- Variabler:-----------------
    private Terning[] terninger;
    private int     totalVaerdi;

    private int     antalTerninger = 2; // Forventes brugbar til at teste med
    //|--------- Getters og Setters:--------

    public Terning[] getTerninger() {
        return terninger;
    }

    public void setTerninger(Terning[] terninger) {
        this.terninger = terninger;
    }

    public void setTotalVaerdi(int totalVaerdi) {
        this.totalVaerdi = totalVaerdi;
    }

    public void setAntalTerninger(int antalTerninger) {
        this.antalTerninger = antalTerninger;
    }

    //|----------- Metoder:-----------------
    /** Laver et raflebæger, der indeholder 'antalTerninger' terninger (se Terning klasse).
     *  Den laver nye terninge objekter.
     *
     * @param antalTerninger Et fast antal terninger i bægeret - kan ikke ændres
     */
    public RafleBaeger(int antalTerninger){
        if(antalTerninger<1){
            // Sikrer at der altid vil være mindst EN terning
            antalTerninger=1;
        }

        // Laver Terning
        terninger = new Terning[antalTerninger];
        for( int i=0; i<antalTerninger; i++){
            terninger[i] = new Terning();
        }
        this.antalTerninger = antalTerninger;
    }


    /** Slaar med ALLE terninger i bægeret, og sætter bægerets
     *  'totalVaerdi' til summen af de individuelle terningers
     *  vaerdier.
     *  Den kalder alle terningeobjekters egen 'slaa()' metode.
     *
     * @return Summen af terningeobjekternes vaerdi efter egen slaa() metode.
     */
    public int slaa(){
        int sum = 0;
        for(int i=0; i<antalTerninger; i++){
            sum += terninger[i].slaa();
        }
        totalVaerdi = sum;
        return totalVaerdi;
    }

    /**
     * Undersøger om ALLE terninger i bægeret har den samme vaerdi.
     * Hvis der kun er én terning, vil dette altid være sandt.
     *
     * @return Om alle terninger er ens
     */
    public boolean erEns() {
        int førsteElement = terninger[0].getVaerdi();
        for(int i=1; i<antalTerninger; i++){
            if(førsteElement != terninger[i].getVaerdi()){
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

    /** @return Antallet af terninger i bægeret
     */
    public int getAntalTerninger() {
        return antalTerninger;
    }

    /** Udskriver til kommando linjen/terminalen alle værdier som er blevet slået efter
     * en slåning med Raflebagereret.
     */
    public ArrayList<Integer> FaaTerningVærdier(){
        ArrayList<Integer> Terninger = null;
        for(int i= 0; i< getAntalTerninger();i++){
            Terninger.add(getTerning(i).getVaerdi());
        }
        return Terninger;
    }
}
