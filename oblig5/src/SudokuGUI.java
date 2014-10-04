
/*Bruker GUI-forslaget fra oppgaveteksten. Har oversatt variabel og metode-navn til engelsk
i tillegg til aa tilpasse GUIet i forhold til mitt behov. Dette gjelder for eksempel variabel
-deklarasjoner, metodekall, osv osv
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Tegner ut et Sudoku-brett.
 * @author Christian Tryti
 * @author Stein Gjessing
 */
class SudokuGUI extends JFrame {

    private final int SQUARE_SIZE = 50;	/* Storrelsen paa hver rute */
    private final int PLACE_TOP = 50;	/* Plass paa toppen av brettet */

    private JTextField[][] board;   /* for aa tegne ut alle rutene */
    private int dim;
    private int row;	/* antall ruter i en boks loddrett */
    private int col;
    private Board boardObj;
    private JTextField theSquare;
    private JButton findSolution;
    private JButton nextSolution;
    private JButton newFile;
    private SudokuContainer container;
    private int size;
    private JPanel boardPanel;
    private JPanel solutionCountPanel;
    private int counter;

    /** Lager et brett med knapper langs toppen. */
    public SudokuGUI(Board boardObj, int dim, int col, int row)  {
        this.dim = dim;
        this.row = row;
        this.col = col;
        this.boardObj = boardObj;
        container =  boardObj.solutions;
        size = boardObj.solutions.getSolutionCount();

        board = new JTextField[dim][dim];

        setPreferredSize(new Dimension(dim * SQUARE_SIZE,
                dim  * SQUARE_SIZE + PLACE_TOP));
        setTitle("Sudoku " + dim +" x "+ dim );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = makeButtons();
        JPanel boardPanel = makeBoard();
        JPanel solutionCountPanel = solutionCount();

        getContentPane().add(buttonPanel,BorderLayout.NORTH);
        getContentPane().add(boardPanel,BorderLayout.CENTER);
        getContentPane().add(solutionCountPanel,BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    private JPanel solutionCount() {
        solutionCountPanel = new JPanel();
        JLabel counterBox = new JLabel();
        counterBox.setText("Losning " + counter + " av " + container.getSolutionCount());
        solutionCountPanel.add(counterBox);
        return solutionCountPanel;
    }

    /**
     * Lager et panel med alle rutene.
     * @return en peker til panelet som er laget.
     */
    private JPanel makeBoard(){
        int value = 0;
        int top, left;
        Square square;
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(dim,dim));
        boardPanel.setAlignmentX(CENTER_ALIGNMENT);
        boardPanel.setAlignmentY(CENTER_ALIGNMENT);
        //Oker bredden med 7 for aa faa plass til Last ny fil-knappen
        setPreferredSize(new Dimension(new Dimension(dim * SQUARE_SIZE,
                dim * SQUARE_SIZE)));
        for(int i = 0; i < dim; i++) {
			/* finn ut om denne raden trenger en tykker linje paa toppen: */
            value++;
            top = (i % row == 0 && i != 0) ? 4 : 1;
            for(int j = 0; j < dim; j++) {
				/* finn ut om denne ruten er en del av en kolonne 
				   som skal ha en tykkere linje til venstre:       */
                left = (j % col == 0 && j != 0) ? 4 : 1;
                square = boardObj.getSquare(i, j);
                theSquare = new JTextField();
                nextSolution.setEnabled(false);
                theSquare.setBorder(BorderFactory.createMatteBorder
                        (top,left,1,1, Color.black));
                theSquare.setHorizontalAlignment(SwingConstants.CENTER);
                theSquare.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                theSquare.setText("" + square.fillNewBoard());
                board[i][j] = theSquare;
                boardPanel.add(theSquare);
            }
        }

        //Hvis Finn Losning blir trykket, sjekkes det forst
        //om det finnes losninger til dette brettet, hvis saa
        //saa hentes dette brettets forste losning(altsa ruteverdier), og brettet
        //oppdateres.
        findSolution.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (container.noSolutions()) {
                    showMessage("Det finnes ingen losninger til dette brettet!");
                } else {
                    for (int i = 0; i < dim; i++) {
                        for (int j = 0; j < dim; j++) {
                            theSquare = board[i][j];
                            theSquare.setText(findSolution(i, j));
                            boardPanel.add(theSquare);
                        }
                    }
                    nextSolution.setEnabled(true);
                    counter++;
                    getContentPane().add(solutionCount(),BorderLayout.SOUTH);
                    pack();

                }
                findSolution.setEnabled(false);
            }
        });

        //Hvis Neste Losning blir trykket, sjekkes det forst
        //om det finnes losninger til dette brettet, hvis saa
        //saa hentes dette brettets losning(altsa ruteverdier) paa
        //en gitt plass i beholderen (styrt av en teller), og brettet
        //oppdateres. Hvis telleren er storre enn beholderens storrelse
        //skrives det feilmelding for aa unngaa ArrayOutOfBounds-exception

        nextSolution.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (container.noSolutions()) {
                    showMessage("Det finnes ingen losninger til dette brettet!");
                } else {
                    if (counter == 0) {

                    } else {
                        if (counter < size) {
                            for (int i = 0; i < dim; i++) {
                                for (int j = 0; j < dim; j++) {
                                    theSquare = board[i][j];
                                    theSquare.setText(nextSolution(i, j, counter));
                                    boardPanel.add(theSquare);
                                }
                            }
                            counter++;
                            getContentPane().add(solutionCount(),BorderLayout.SOUTH);
                            pack();
                        } else {
                            showMessage("Det finnes ingen flere losninger!");
                            nextSolution.setEnabled(false);
                        }
                    }
                }

            }
        });

        //Hvis Last ny fil blir trykket kjores newFile()-metoden
        newFile.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e)  {
                newFile();

            }
        });

        return boardPanel;
    }

    //Starter programmet paa nytt, sender med en tom String-array slik
    //at JFileChooser kommer opp med en gang
    //Lukker denne JFramen
    private void newFile()	{
        String [] empty = new String[0];
        new Start(empty);
        this.dispose();
    }

    //Returnerer SudokuBeholderens forste losning og dennes ruteverdi paa plass i,j
    private String findSolution(int i, int j) {
        return "" + container.get(0).getSquare(i, j).getValue();
    }

    //Returnerer SudokuBeholderens losning pa plass index i beholderen og dennes ruteverdi paa plass i,j
    private String nextSolution(int i, int j, int index) {
        return "" + container.get(index).getSquare(i, j).getValue();
    }

    /**
     * Lager et panel med noen knapper.
     * @return en peker til panelet som er laget.
     */
    private JPanel makeButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        findSolution = new JButton("Finn losning(er)");
        nextSolution = new JButton("Neste losning");
        newFile = new JButton("Last ny fil");
        buttonPanel.add(findSolution);
        buttonPanel.add(nextSolution);
        buttonPanel.add(newFile);
        return buttonPanel;
    }

    //Viser en meldingsboks med n som tekst
    private void showMessage(String n)  {
        JOptionPane.showMessageDialog(this, n, "FEIL", JOptionPane.ERROR_MESSAGE);
    }
}
