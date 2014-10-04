/***
 * @author Roar Hoksnes Eriksen
 */

//Klasse for brett
class Board {
    //En 2D-array for alle ruter, som har samme verdier som rutene paa brettet
    private Square [][]allSquares;
    private int [][] squareValues;
    private Row [] rows;
    private Column [] columns;
    private Box [] box;
    public int col;
    public int row;
    private int dim;
    public SudokuContainer solutions;

    //Opretter et nytt brett
    //Lager nye rute-objekter for hver rute paa brettet, og lager nye
    //boks, kolonne og rad objekter for hver kolonne og rad-verdi, og putter
    //rutene inn i sine respektive plasser i nevnte rad, boks og kolonne-objekter
    Board(int dim, int row, int col, int [][] squareValues) {
        solutions = new SudokuContainer();
        this.squareValues = squareValues;
        this.dim = dim;
        allSquares = new Square[dim][dim];
        rows = new Row[dim];
        columns = new Column[dim];
        box = new Box[dim];

        this.col = col;
        this.row = row;
        int nr = 0;

        //Lager nye rad, kolonne og boks objekter
        for (int i = 0; i < dim; i++) {
            rows[i] = new Row(dim);
            columns[i] = new Column(dim);
            box[i] = new Box(dim);
        }

        //Oppretter nye ruter, avhengig av om de har en verdi eller ikke saa
        //blir de opprettet som SquareWithValue eller SquareFindValue
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {

                int x = ((nr/(row * dim)) * row);
                int y = ((nr % dim) / col);
                int boks = x+y;
                int rowPos = i;
                int colPos = j;

                if (squareValues[i][j] != -1) {
                    allSquares[i][j] = new SquareWithValue(this, squareValues[i][j], dim, boks, rowPos, colPos);
                } else {
                    allSquares[i][j] = new SquareFindValue(this, squareValues[i][j], dim, boks, rowPos, colPos);
                }
                nr++;
            }
        }

        //Legger til nye rad, kolonne og boksposisjoner for hver rute
        nr = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {

                int x = ((nr/(row * dim)) * row);
                int y = ((nr % dim) / col);
                int boks = x+y;

                box[boks].addSquare(allSquares[i][j]);
                rows[i].addSquare(allSquares[i][j]);
                columns[i].addSquare(allSquares[j][i]);
                nr++;
            }

        }

        //Lager nestepekere for hver rute. Om ruten til hoyre og under
        //er den siste, settes nestepekeren til null. Hvis ruten til hoyre
        //er den ytterste, settes nestepekeren til aa peke paa den forste ruten
        //i neste rad. Nestepekeren settes til aa peke paa ruten til hoyre om ingen
        //av if-setningene over intreffer
        for (int i = 0; i < allSquares.length; i++) {
            for (int j = 0; j < allSquares[0].length; j++) {
                if (i==allSquares.length-1 && j==allSquares.length-1) {
                    allSquares[i][j].next = null;
                } else if (j==allSquares[0].length-1) {
                    allSquares[i][j].next = allSquares[i+1][j-(allSquares[0].length-1)];
                } else {
                    allSquares[i][j].next = allSquares[i][j+1];
                }
            }
        }
    }

    //returnerer boksposisjon
    public Box getBoxPos(int index) {
        return box[index];
    }

    //returnerer radposisjon
    public Row getRowPos(int index) {
        return rows[index];
    }

    //returnerer kolonneposisjon
    public Column getColumnPos(int index) {
        return columns[index];
    }

    public int getDim() {
        return dim;
    }

    public int getColumn() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    //Returnerer et array med alle rutene
    public Square [][] getArray() {
        return allSquares;
    }

    //Returnerer et ruteobjekt pa plass x og y pa brettet
    public Square getSquare(int x, int y) {
        return allSquares[x][y];
    }

    public void addSolution(Board board) {
        solutions.insert(board);
    }

    //Metode som starter dette brettets losnings-algoritme
    //Starter paa plass 0,0, da dette er overst til venstre paa
    //brettet
    public void findSolutions() {
        allSquares[0][0].fillInnRemainingOfBoard();
    }
}
