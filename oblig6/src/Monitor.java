/***
 * @author Roar Hoksnes Eriksen
 */

import java.io.File;
import java.util.ArrayList;

class Monitor {
    //Bruker en arraylist til aa ta vare paa to og to arrayer som merges
    private ArrayList<String[]> mergeContainer = new ArrayList<String[]>();
    private String [] mergedArray;
    private int wordCnt;
    private File out;
    private long startTime;

    public Monitor(int wordCnt, File out, long startTime) {
        this.wordCnt = wordCnt;
        this.out = out;
        this.startTime = startTime;
    }

    //Legger til ferdigsorterte lister i en arraylist, naar denne arraylisten
    //inneholder to lister starter en ny traad aa "merge" disse to sammen. Merge-metoden returnerer
    //deretter en merget liste, som saa settes til aa vaere lik en array som til slutt vil vaere den ferdige
    //listen naar de to siste listene skal merges sammen. Arraylisten slettes for innhold etter hver
    //merging, slik at den hele tiden er klar til aa ta imot to nye lister. Hvis listen som blir merget
    //er de to siste, altsaa utgjor hele lista, saa skrives denne til fil, hvis ikke saa startes en ny
    //traad som fortsetter aa merge den gjeldende liste-delen med en ny liste-del
    public synchronized void start(String[] arrayToSort, SortList workingThread) {
        mergeContainer.add(arrayToSort);

        if (mergeContainer.size() == 2) {
            mergedArray = new String[(mergeContainer.get(0).length)+(mergeContainer.get(1).length)];
            mergedArray = workingThread.mergeLists(mergeContainer.get(0), mergeContainer.get(1));
            mergeContainer.clear();

            if (mergedArray.length == wordCnt) {
                new WriteFile(mergedArray, out, startTime);
            } else {
                new SortList(mergedArray, this, true).start();
            }
        }
    }
}
