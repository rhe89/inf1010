/***
 * @author Roar Hoksnes Eriksen
 */

import java.util.ArrayList;
import java.io.File;

//Klasse for aa dele opp antall ord i forhold til hvor mange
//traader som brukes
class SplitIntoThreads {
    private Monitor monitor;
    private String [] unsortedArray, arrayToSort;
    private String[][] arrayToSort2D;
    private int wordCnt, threadCnt, size, leftOver, counter;
    private File out;
    private long startTime;

    SplitIntoThreads(int wordCnt, int threadCnt, String [] unsortedArray, File out, long startTime) {
        this.wordCnt = wordCnt;
        this.threadCnt = threadCnt;
        this.unsortedArray = unsortedArray;
        this.out = out;
        this.startTime = startTime;
        monitor = new Monitor(wordCnt, out, startTime);
        splitIntoThreads();
    }

    //Metode for oppdelingen. Bruker modulo for aa finne ut hvor mange
    //ord som blir til overs. Legger saa temparrayen inn i en 2D array, som
    //inneholder x antall ord i forhold til x antall traader
    private void splitIntoThreads() {
        leftOver = wordCnt % threadCnt;
        size = (wordCnt-leftOver) / threadCnt;
        size += 1;
        counter = 0;
        arrayToSort2D = new String[threadCnt][size];

        for (int i = 0; i < threadCnt; i++) {
            arrayToSort = new String[size+1];
            for (int j = 0; j < size; j++) {
                if (leftOver == -1) {
                    if (j == size-1) {
                        arrayToSort2D[i][j] = null;
                        arrayToSort[j] = arrayToSort2D[i][j];
                    } else {
                        arrayToSort2D[i][j] = unsortedArray[counter++];
                        arrayToSort[j] = arrayToSort2D[i][j];
                    }
                } else {
                    if (j != size-1) {
                        arrayToSort2D[i][j] = unsortedArray[counter++];
                        arrayToSort[j] = arrayToSort2D[i][j];
                    } else {
                        arrayToSort2D[i][j] = unsortedArray[counter++];
                        arrayToSort[j] = arrayToSort2D[i][j];
                        leftOver--;
                    }
                }
            }


            //Fjerner nullverdier i arrayen vha en arraylist
            ArrayList<String> removeNull = new ArrayList<String>();
            for (int j = 0; j < arrayToSort.length; j++) {
                String temp;
                if (arrayToSort[j] != null) {
                    if (!arrayToSort[j].equals("")) {
                        removeNull.add(arrayToSort[j]);
                    }
                }
            }

            //Lager en ny versjon av arrayToSort med arraylisten uten nullverdier
            arrayToSort = removeNull.toArray(new String[removeNull.size()]);
            //Starter traadene som skal dele opp arrayen
            new SortList(arrayToSort, monitor, false).start();
        }
    }
}
