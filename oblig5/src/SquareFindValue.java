/***
 * @author Roar Hoksnes Eriksen
 */

//Klasse for ruter som skal finne en verdi
class SquareFindValue extends Square  {

    SquareFindValue(Board board, int value, int dim, int boxPos, int row, int col) {
        super(board, value, dim, boxPos, row, col);
    }

    public String fillNewBoard() {
        return "";
    }
}
