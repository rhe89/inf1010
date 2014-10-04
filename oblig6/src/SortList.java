/***
 * @author Roar Hoksnes Eriksen
 */

class SortList extends Thread {
    private String [] arrayToSort, sortedPartOfList;
    private Monitor monitor;
    private int wordCnt;
    //Brukes faar aa se om en traad allerede har sortert en liste
    private boolean beenHere;

    //Konstruktor for sorteringen
    SortList(String [] arrayToSort, Monitor monitor, boolean beenHere) {
        this.arrayToSort = arrayToSort;
        this.monitor = monitor;
        this.wordCnt = wordCnt;
        this.beenHere = beenHere;
    }

    //Konstruktor for mergingen
    SortList(boolean beenHere) {
        this.beenHere = beenHere;
    }

    /*Sorterer forst en og en liste (for hver traad),
    sender denne listen til monitoren for merging
    */
    public void run() {
        if (!beenHere) {
            sortedPartOfList = sortList(arrayToSort);
        } else {
            sortedPartOfList = arrayToSort;
        }
        monitor.start(sortedPartOfList, this);
    }

    /*Fletter to og to ferdigsorterte lister sammen til en arrayToSort
    Lager nye Word-objekter av hver String som skal sammenlignes, og
    bruker disses compareTo-metode for aa sammenligne storrelse
    */
    public String [] mergeLists(String [] arr1, String [] arr2) {
        int one = 0;
        int two = 0;
        int merge = 0;

        String [] arrayToSort = new String[arr1.length+arr2.length];
        while (one < arr1.length && two < arr2.length) {
            if (new Word(arr1[one]).compareTo(arr2[two]) < 0) {
                arrayToSort[merge++] = arr1[one++];
            } else {
                arrayToSort[merge++] = arr2[two++];
            }
        }
        while (one < arr1.length) {
            arrayToSort[merge++] = arr1[one++];
        }
        while (two < arr2.length) {
            arrayToSort[merge++] = arr2[two++];
        }
        return arrayToSort;
    }

    /*Sorterer en del av listen. Bruker innstikkssortering, samt oppretter
    nye Word-objekter for hvert ord og bruker disses compareTo-metode
    for aa sjekke om et ord er storre enn et annet
    Algoritmen jeg har brukt foeler jeg er den jeg har best kontroll paa fra
    pensum, og den er ogsaa veldig enkel aa programmere i mine oyne. Den er nesten
    slik som den i Rett paa Java-boka, men jeg maatte endre den litt for aa faa
    compareTo til aa fungere. Arrayen gaaes gjennom fra starten, og hver gang det finnes
    et ord som er mindre enn ordet foer flyttes det et hakk til venstre i listen, frem til alle
    ordene er gaatt gjennom og listen er sortert fra venstre til hoyre.
    Invarianten blir da at hver gang det finnes et ord som er mindre enn det foran,
    flyttes det et hakk til venstre i listen.
    */
    public String [] sortList(String [] arr) {
        sortedPartOfList = arr;

        for (int k = 0; k < sortedPartOfList.length; k++) {
            if (sortedPartOfList[k] != null) {
                String t = sortedPartOfList[k];
                int i = k;
                if (i > 0) {
                    while (i > 0 && new Word(sortedPartOfList[i-1]).compareTo(t) >= 0) {
                        sortedPartOfList[i] = sortedPartOfList[i-1];
                        --i;
                    }
                }
                sortedPartOfList[i] = t;
            }
        }
        return sortedPartOfList;

    }
}
