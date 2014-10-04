/***
 * @author Roar Hoksnes Eriksen
 */

import java.util.HashMap;

//Superklasse for Box, Column og Row
abstract class PartOfBoard {
    private int dim;
    private HashMap<Square, Square> values;

    PartOfBoard(int dim) {
        this.dim = dim;
        values = new HashMap<Square, Square>();
    }

    //Metode for aa sjekke om noen av kolonne, boks eller rad-
    //objektenne inneholder parameter-verdien
    public boolean containsValue(int value) {
        for (Square s : values.values()) {
            if (value == s.getValue()) {
                return true;
            }
        }
        return false;
    }

    //Legger til en firkant i HashMapen
    public void addSquare(Square square) {
        values.put(square, square);
    }
}
