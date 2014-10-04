/***
 * @author Roar Hoksnes Eriksen
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

//Klasse som starter programmet
class Start {
    private String fileName;
    private String solutionName;
    private File file;
    private File solutionFile;
    private Scanner read;
    private int dim;
    private int row;
    private int col;
    private int [][] sqaureValues;
    private Board b;
    private String checkFormat;
    private int cancelCounter;
    private final int errorMessage = JOptionPane.ERROR_MESSAGE;
    private final int informationMessage = JOptionPane.INFORMATION_MESSAGE;

    /*Hvis ingen argumenter blir gitt naar programmet startes
    opprettes et JFileChooser-objekt som lar bruker velge fil
    og losningen(e) paa sudokoen lagres til fil
    */
    Start(String [] args) {
        if (args.length > 2) {
            new MessageGUI("For mange argumenter i kommandolinjen!", errorMessage);
        }
        if (args.length == 0) {
            final JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Aapne en fil med et Sudoku-brett");

            //Tvinger bruker til kun aa velge .txt-filer
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    ".txt filer", "txt");

            fc.setAcceptAllFileFilterUsed(false);
            int returnValue = fc.CANCEL_OPTION;

            fc.setFileFilter(filter);

            //Hvis bruker trykker cancel(avbryt), vises JFileChooser-boksen paa nytt
            //med mindre brukeren taster cancel tre ganger
            while (returnValue != fc.APPROVE_OPTION && cancelCounter < 3) {
                returnValue = fc.showOpenDialog(null);
                cancelCounter++;
                if (cancelCounter > 2) {
                    System.out.println("Programmet termineres");
                    System.exit(1);
                }
            }


            //Filen settes til aa vaere den bruker valgte, den initialiseres, losningen
            //lagres og GUIet blir opprettet
            File file = fc.getSelectedFile();
            makeFile(file);
            saveFile();
            makeGUI();

		/*Hvis et argument blir gitt settes fileName til den filen som skal
		loses, og losningen(e) lagres til fil. GUI blir saa opprettet*/
        } else if (args.length == 1) {
            fileName = args[0];
            makeFile(file);
            saveFile();
            makeGUI();
		/*Hvis to argumenter blir gitt settes fileName til det forste argumentet
		og solutionName til det andre argumentet
		Deretter lagres losningsfilen med losningen(e) paa sudokoen til fil
		*/
        } else {
            fileName = args[0];
            solutionName = args[1];
            saveFile(fileName, solutionName);
            saveFile();
            new MessageGUI("Losningen ble lagret i " + solutionName, informationMessage);
            System.exit(1);
        }
    }

    //Metode for aa lage en ny fil. Om File-objektet er null
    //er det ikke blitt brukt JFileChooser, og filen kom fra terminal
    //Om filen ikke slutter paa txt skrives feilmelding
    private void makeFile(File file) {
        if (file == null) {
            if (fileName != null) {
                checkFormat = fileName.substring(fileName.length() - 3);
                if (checkFormat.equals("txt")) {
                } else {
                    new MessageGUI("Feil format! Innfilen skal vaere av .txt-format", errorMessage);
                }
            }
            file = new File(fileName);
        }
        try {
            read = new Scanner(file);
        } catch (FileNotFoundException e) {
            new MessageGUI("Filen finnes ikke!", errorMessage);
        }
        readFile(read);
    }

    //Metode om det blir oppgitt to filer, da blir den siste lagret i losnings
    //beholderen
    private void saveFile(String fileName, String solutionName) {
        if (file == null) {
            file = new File(fileName);
        }
        try {
            read = new Scanner(file);
        } catch (FileNotFoundException e) {
            new MessageGUI("Filen finnes ikke!", errorMessage);
        }

        if (fileName != null) {
            checkFormat = fileName.substring(fileName.length() - 3);
            if (checkFormat.equals("txt")) {
            } else {
                new MessageGUI("Feil format! Innfilen skal vaere av .txt-format", errorMessage);
            }
        }

        if (solutionName != null) {
            checkFormat = solutionName.substring(solutionName.length() - 3);
            if (checkFormat.equals("txt")) {
            } else {
                new MessageGUI("Feil format! Utfilen skal vaere av .txt-format", errorMessage);
            }
        }
        file = new File(fileName);


        readFile(read);
        solutionFile = new File(solutionName);
    }

    //Leser filen
    private void readFile(Scanner read) {
        try {
            dim = read.nextInt();
            row = read.nextInt();
            col = read.nextInt();
        } catch (NoSuchElementException e) {
            //Hvis noen av disse ikke er en int er blir exceptionet catchet
            //og feilmelding skrives ut
            new MessageGUI("Feil! Filen er ikke en sudokufil", errorMessage);

        }

        sqaureValues = new int[dim][dim];
        read.useDelimiter("");

        //Leser verdiene for hver rute, og legger det inn i en array
        //Konverterer stringene til char, og caster char-verdiene til int (hvis en char
        //er A, saa faar den verdien 10, b 11 osv.)
        //Oppretter saa et nytt brett-objekt, etter at den metoden er returnert
        //saa kalles en metode som finner losninger for brettet, og naar
        //den metoden er returnert saa skrives et nytt GUI for brettet
        for (int i = 0; i < dim; i++) {
            read.nextLine();
            for (int j = 0; j < dim; j++) {
                String s = read.next();
                char c = s.charAt(0);
                int value = (int) c;
                if(value == 46 || value == 48) {
                    sqaureValues[i][j] = -1;
                } else if(value >= 49 && value <= 57) {
                    sqaureValues[i][j] = (value - 48);
                } else if(value >= 65 && value <= 90) {
                    sqaureValues[i][j] = (value - 55);
                } else if(value >= 97 && value <= 122) {
                    sqaureValues[i][j] = (value - 87);
                }

                //Sjekker om noen av verdiene i brettet er storre enn det som er tillatt
                if (sqaureValues[i][j] > dim) {
                    System.out.println(dim);
                    new MessageGUI("Feil! En verdi i brettet er ikke tillatt (storre enn dimensjonen)", errorMessage);
                }
            }
        }
        b = new Board(dim, row, col, sqaureValues);
        System.out.println("Prover aa finne losninger");
        b.findSolutions();
    }

    //Oppretter GUIet
    private void makeGUI() {
        new SudokuContainer().makeGUI(b, dim, col, row);
    }

    //Lagrer losningen til fil
    //Fikk mye hjelp av min gode venn Google med denne
    private void saveFile()  {
        if (solutionFile == null) {
            solutionFile = new File("Losning.txt");
        }
        try {
            FileWriter f = new FileWriter(solutionFile);
            BufferedWriter out = new BufferedWriter(f);
            Board[] solutions = b.solutions.getContainer();
            for (int k = 0; k < b.solutions.getSolutionCount(); k++) {
                out.write(k+1 + ": ");
                for (int i = 0; i < dim; i++) {

                    for (int j = 0; j < dim; j++) {
                        int s = b.solutions.get(k).getSquare(i, j).getValue();
                        out.write("" + (s));
                    }
                    out.write(" // ");
                }
                out.write(System.getProperty("line.separator"));
            }
            out.write(System.getProperty("line.separator"));
            out.write("Antall losninger: " + b.solutions.getSolutionCount());
            out.close();
        } catch (IOException e) {
            new MessageGUI("Feil med input/output", errorMessage);
        }
    }

    //GUI for aa opprette forskjellige meldingsbokser til feilsjekkene som blir gjort
    private class MessageGUI extends JFrame {
        MessageGUI(String n, int message) {
            JOptionPane.showMessageDialog(this, n, "Yo!", message);
            System.out.println("Programmet termineres");
            System.exit(1);
        }
    }
}
