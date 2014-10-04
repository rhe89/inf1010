/***
 * @author Roar Hoksnes Eriksen
 */

//Klasse for Ruter
class Square {
    private Board board;
    private int [] numbers;
    private int value;
    private int boxPos;
    private int rowPos;
    private int colPos;
    private int dim;
    private Row rowObj;
    private Column columnObj;
    private Box boxObj;
    public Square next;

    Square(Board board, int value, int dim, int boxPos, int rowPos, int colPos) {
        this.board = board;
        this.value = value;
        this.dim = dim;
        this.boxPos = boxPos;
        this.rowPos = rowPos;
        this.colPos = colPos;
        boxObj = board.getBoxPos(boxPos);
        rowObj = board.getRowPos(rowPos);
        columnObj = board.getColumnPos(colPos);
        numbers = new int[dim];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i+1;
        }
    }

    //returnerer verdien til denne ruten
    public int getValue() {
        return value;
    }

    //Returnerer radposisjon
    public int getRow() {
        return rowPos;
    }

    //Returnerer kolonneposisjon
    public int getCol() {
        return colPos;
    }

    //Returnerer dimensjonen til brettet
    public int getDim() {
        return dim;
    }

    //Returnerer boksposisjon
    public int getBox() {
        return boxPos;
    }

    /*Sjekker forst om nestepekeren er null, ogsaa om dette er en
    rute uten verdi. Saa gaar
    man gjennom alle tillatte verdier og sjekker om
    denne rutens boks, kolonne eller rad inneholder denne verdien.
    Hvis ikke saa sjekkes det om finished er lik true, hvis den er det
    saa legges en ny losning til, og finished settes til false slik at
    man kan fortsette aa se etter nye losninger. Hvis finished er false sjekkes
    neste rute.
    Hvis ruten har en forhaandsutfylt verdi sjekkes det om finieshed
    er true, hvis saa saa legges det til en ny losning og finished settes
    til false slik at man kan sjekke etter nye losninger. Hvis finished er false
    sjekkes neste rute
    */
    public void fillInnRemainingOfBoard() {
        boolean finished = false;

        if (next == null) {
            finished = true;
        }

        if (value == -1) {
            for (int i = 0; i<numbers.length;i++) {
                if(!rowObj.containsValue(numbers[i])) {
                    if(!columnObj.containsValue(numbers[i])) {
                        if(!boxObj.containsValue(numbers[i])) {
                            value = numbers[i];
                            if (finished) {
                                finished = false;
                                board.addSolution(board);
                            } else {
                                next.fillInnRemainingOfBoard();
                            }
                        }
                    }
                }
            } value = -1;
        } else {
            if (finished) {
                board.addSolution(this.board);
                finished = false;
            } else {
                next.fillInnRemainingOfBoard();
            }
        }
    }

    //Fyller brettet med forhaandsdefinerte verdier fra tekstfilen
    public String fillNewBoard() {
        return "" + this.value;
    }
}
