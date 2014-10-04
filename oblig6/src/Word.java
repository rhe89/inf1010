/***
 * @author Roar Hoksnes Eriksen
 */

//Klasse for ord som implementerer Comparable for sammenligning av strenger
class Word implements Comparable<String> {
    String t;

    Word(String t) {
        this.t = t;
    }
    public int compareTo(String s) {
        return t.compareTo(s);
    }
}