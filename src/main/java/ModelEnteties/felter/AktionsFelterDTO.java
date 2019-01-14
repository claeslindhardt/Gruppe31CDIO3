package ModelEnteties.felter;

import BoundaryView.UserInterfaceKontrakt;
import Controller.HandelsController;
import Controller.SpilController;

/**__________________________________________________________________________________________________________________________________________________________
 *  PROGRAMDOKUMENTATION: AktionsFelterDTO
 *
 * @author Claes
 *  Det er forældre klassen til alle de braet som ikke er ejelige,
 *  men som har en følge aktion når man lander på den, dens børneklasser er:
 *          1. StartCO
 *          2. TaxiCO
 *          3. ChanceFeltCO
 *          4. gaaIFaengsel
 *          5. FaengselCO
 */
public abstract class AktionsFelterDTO extends FeltDTO {

    public void aktionPaaFelt(HandelsController handel, SpilController spil, UserInterfaceKontrakt userInterfaceKontrakt) {
    }
}
