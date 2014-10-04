/***
 * @author Roar Hoksnes Eriksen
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Samme oppsett som jeg hadde i oblig 5
class WriteFile {
    private File file;
    private String [] arrayToWrite;
    private long startTime;

    public WriteFile(String [] arrayToWrite, File file, long startTime) {
        this.file = file;
        this.startTime = startTime;
        this.arrayToWrite = arrayToWrite;
        try {
            FileWriter f = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(f);
            for (int i = 0; i < arrayToWrite.length; i++) {
                String s = arrayToWrite[i];
                out.write(s);
                out.write(System.getProperty("line.separator"));
            }
            out.close();
            long endTime = System.currentTimeMillis();
            //Skriver ut tid brukt
            System.out.println("Programmet brukte "+((endTime - startTime)) + " millisekunder");

        } catch (IOException e) {
            System.out.println("Feil med input/output");
        }
    }
}
