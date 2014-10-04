/***
 * @author Roar Hoksnes Eriksen
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Klasse for behandling av kommandolinje-argumenter og tekstfil
class Start {
    private int threadCnt = 0;
    private int wordCnt = 0;
    private long startTime;
    private File in, out;
    private Scanner inFile;
    private String checkFormat;
    private String [] args, arrayFromFile;

    Start(String[] args, long startTime) {
        this.args = args;
        this.startTime = startTime;
    }
    public void initalize() {
        //Sjekker om det er for faa argumenter i kommandolinjen
        if (args.length < 3) {
            System.out.println("For faa argumenter i kommandolinjen");
            System.out.println("Formatet skal vaere: ");
            System.out.println("<Antall traader> <Innfil> <Utfil>");
            System.exit(1);

            //Sjekker om det er for mange argumenter i kommandolinjen
        } else if (args.length > 3) {
            System.out.println("For mange argumenter i kommandolinjen");
            System.out.println("Formatet skal vaere: ");
            System.out.println("<Antall traader> <Innfil> <Utfil>");
            System.exit(1);
        } else {

            //Sjekker om antall traader er et heltall
            try {
                threadCnt = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Feil! Antall traader maa vaere et heltall!");
                System.exit(1);
            }

            //Sjekker om antall traader er mer enn 0
            if (threadCnt == 0) {
                System.out.println("Feil! Antall traader maa vaere mer enn 0");
                System.exit(1);
            }

            //Sjekker om innfilen er en tekstfil
            checkFormat = args[1].substring(args[1].length() - 3);
            if (!checkFormat.equals("txt")) {
                System.out.println("Feil! Filen det skal leses fra maa vaere en .txt-fil!");
                System.exit(1);
            }

            //Sjekker om utfilen er en tekstfil
            checkFormat = args[2].substring(args[2].length() - 3);
            if (!checkFormat.equals("txt")) {
                System.out.println("Feil! Filen det skal skrives til maa vaere en .txt-fil!");
                System.exit(1);
            }

            //Lager nye File-objekter av de to filene oppgitt i kommandolinjen
            in = new File(args[1]);
            out = new File(args[2]);

            readFile();
        }
    }

    //Leser filen
    private void readFile() {
        try {
            inFile = new Scanner(in);
        } catch (FileNotFoundException e) {
            System.out.println("Filen finnes ikke!");
        }

        //Sjekker om den forste verdien i filen er en int
        try {
            wordCnt = inFile.nextInt();
            inFile.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Feil! Filen er ikke av riktig format");
            System.exit(1);
        }

        //Legger alle ordene inn i en array
        arrayFromFile = new String[wordCnt+1];

        int counter = 0;
        while (inFile.hasNext()) {
            arrayFromFile[counter++] = inFile.nextLine();
        }

        //Sjekker om lengden paa fila er lik antallet i starten av fila
        if (counter != wordCnt) {
            System.out.println("Feil! Antallet starten av filen er ikke lik antall ord i filen");
            System.exit(1);
        }

        //Initialiserer oppdelingen
        new SplitIntoThreads(wordCnt, threadCnt, arrayFromFile, out, startTime);
    }
}

