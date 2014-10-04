/***
 * @author Roar Hoksnes Eriksen
 */

import javax.swing.*;

//Klasse for losninger
class SudokuContainer {
    private int solutionCount = 0;
    //Array som kan ta vare paa opptil 500 bord-objekter
    Board[] solutions = new Board[500];
    int [][] values;
    Board newBoard;

    //Legger inn en ny losning i beholderen, skriver ut feilmelding om det er over
    //500 losninger
    public void insert(Board board) {
        values = new int[board.getDim()][board.getDim()];
        newBoard = new Board(board.getDim(), board.getRow(), board.getCol(), getValues(board));
        if (solutionCount < 500) {
            solutions[solutionCount] = newBoard;
            solutionCount++;

            //Hvis det finnes mer enn 500 losninger opprettes GUIet med de 500 losningene som er blitt lagt
            //inn. Legger til en sleep-metode slik at det ikke blir opprettet x-antall GUIer for hver gang det
            //blir lagt inn en losning som er over antallet som det er plass til. Vet at dette ikke er optimalt
            //men var det eneste jeg kom paa for aa unngaa at x antall guier ble opprettet oppaa hverandre
        } else {
            JOptionPane.showMessageDialog(new SudokuGUI(board, board.getDim(), board.col, board.row), "For mange losninger, GUI opprettes!", "FEIL", JOptionPane.ERROR_MESSAGE);
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    //Returnerer arrayen med losninger
    public Board get(int index) {
        return solutions[index];
    }

    //Returnerer beholderen med losninger
    public Board [] getContainer() {
        return solutions;
    }

    //Henter antall losninger
    public int getSolutionCount() {
        return solutionCount;
    }

    //Returnerer en array med nye verdier for det nye brettet
    public int[][] getValues(Board board) {
        for (int i = 0; i < board.getDim(); i++) {
            for (int j = 0; j < board.getDim(); j++) {
                values[i][j] = board.getArray()[i][j].getValue();
            }
        }
        return values;
    }

    //Sjekker om det finnes losninger i beholderen
    //eller ei
    public boolean noSolutions() {
        if (solutions[0] == null) {
            return true;
        } else {
            return false;
        }
    }

    //Lager et gui med sudokubrettet og dets losninger
    public void makeGUI(Board b, int dim, int col, int row) {
        new SudokuGUI(b, dim, col, row);
    }
}
