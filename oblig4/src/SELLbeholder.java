/***
 * @author Roar Hoksnes Eriksen
 */

import java.util.Iterator;

class SELLbeholder<N extends Comparable<N> , V> implements INF1010samling<N,V> {
    Node listehode;
    int antall;

    //Setter listehode til a vaere et tomt Node hver gang
    //det blir opprettet et nytt SELLbeholder-objekt
    //Siden listehode alltid er null er listehodet.neste det
    //forste objektet i beholderen
    SELLbeholder() {
        listehode = new Node(null, null);
    }

    //Returnerer en iterator
    public Iterator<V> iterator() {
        return new SingelIterator();
    }

    //En node-klasse for neste-peker, nokkel, og verdi
    private class Node <N extends Comparable<N>, V> {
        Node neste;
        N nkl;
        V verdi;

        Node(N n, V v) {
            this.nkl = n;
            this.verdi = v;
        }

        //Returnerer en int-verdi utifra compareTo()-metoden
        public int compareTo(N nkl) {
            return this.nkl.compareTo(nkl);
        }

        //Hjelpemetode for aa sjekke om neste-pekeren er null eller ikke
        boolean hasNext() {
            return neste != null;
        }
    }

    class SingelIterator implements Iterator<V> {
        Node neste;
        Node forrige;
        boolean kaltPaaNeste = false;

        //Setter neste- og forrige-pekere til a peke paa
        // listehode
        SingelIterator() {
            neste = listehode;
            forrige = listehode;
        }

        //Sjekker om nestepekeren er null eller ikke
        public boolean hasNext() {
            return neste.neste != null;
        }

        //Returnerer verdien til tmp, som er lik
        //det forste objektet i lista
        public V next() {
            Node tmp;
            kaltPaaNeste = true;

            if (forrige == listehode) {
                tmp = neste.neste;
                neste = neste.neste;
            } else {
                tmp = neste.neste;
                forrige = neste;
                neste = neste.neste;
            }

            return (V)tmp.verdi;
        }

        //Fjerner et objekt fra lista
        public void remove() {
            if (kaltPaaNeste) {
                listehode.neste = listehode.neste.neste;
                antall--;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    // legger objektet som v peker pa og som har N n inn i beholderen
    //Oker antall for hvert objekt som blir lagt inn
    public void leggInn(N n, V v) {
        Node nyNode = new Node(n, v);
        Node node = listehode.neste;

        if (inneholder(n)) {
            System.out.println("FEIL! OBJEKTET ER ALLEREDE I BEHOLDEREN ");
            return;
        }

        //Legger objektet forst i beholderen om listehode.nestepeker ikke peker paa noe
        //dvs om beholderen er tom
        if (listehode.neste == null) {
            listehode.neste = nyNode;
            antall++;
            return;
        }

        //Legger nyNode sin neste til listehode.neste sin nestepeker
        if (nyNode.compareTo(listehode.neste.nkl) < 0) {
            nyNode.neste = listehode.neste;
            listehode.neste = nyNode;
            antall++;
            return;

            //Sjekker om det nye objektet er mindre enn det gamle og om det
            //finnes en nestepeker hvis det nye objektet blir lagt til
        } else if ((nyNode.compareTo(node.nkl) < 0) && !listehode.neste.hasNext()) {
            listehode.neste = nyNode;
            nyNode = node.neste;
            antall++;
            return;
        }

        //Saa lenge det forste-objektet sin nestepeker ikke er null sa gaar
        //lokka. Da sjekkes det om det nye objektet er mindre enn det
        //neste objektet i lista, hvis det er det, sa legges det til foer
        //det neste objektet
        while (node.neste != null) {
            if(nyNode.compareTo(node.neste.nkl) < 0) {
                nyNode.neste = node.neste;
                node.neste = nyNode;
                antall++;
                return;
            }
            node = node.neste;
        }
        node.neste = nyNode;
        antall++;
        return;
    }

    //Returnerer antall objekter i sellbeholderen
    public int antall() {
        return antall;
    }

    //Sjekker forst om node eller node sin nestepeker er null,
    //hvis ikke saa brukers compareTo(n)-metoden for aa hente
    //nodens verdi, om compareTo() == 0.
    public V hent(N n) {
        Node node = listehode;

        if (node == null || node.neste == null) {
            return null;
        }

        while (node.neste != null) {
            if (node.neste.compareTo(n) == 0) {
                return (V)node.neste.verdi;
            } node = node.neste;
        }

        if (node.compareTo(n) == 0) {
            return (V) node.verdi;
        }
        return null;
    }

    //Faar inn et nr, hvis nr ikke 0 eller storre enn
    //antall sa vil en for-lokke finne noden paa riktig plass
    //og dennes verdi blir returnert
    public V hent(int nr) {
        Node node = listehode.neste;
        if (node == null) {
            return null;
        }
        while (node != null) {
            if (nr < 1 || nr > antall()) {
                return null;
            } else {
                for (int i = 1; i < nr; i++) {
                    node = node.neste;
                }
                return (V) node.verdi;
            }
        } return null;
    }

    //Returnerer det forste objektet i beholderen, som alltid er minst
    public V hentMinste() {
        if (listehode.neste != null) {
            return (V)listehode.neste.verdi;
        } else {
            return null;
        }
    }

    // Gaar gjennom beholderen ved aa flytte paa neste-pekeren til
    // det peker paa det siste objektet i beholderen. Siden det siste
    //objektet alltid er det storste, returneres dette
    public V hentStorste() {
        Node node = listehode.neste;

        if (node != null) {
            for (int i = 1; i < antall; i++) {
                node = node.neste;
            }
            return (V)node.verdi;
        } return null;
    }

    //Sjekker om parameteret er null, hvis ikke
    //saa opprettes et nytt objekt som peker paa
    //det forste objektet i beholderen, hvis dette
    //ikke er null saa returneres true straks
    //parameter-objektet er lik det nye objektet.
    //Hvis det ikke blir likt, returneres false.
    public boolean inneholder(N n) {

        if(n == null) {
            return false;
        }

        Node node = listehode.neste;

        if (listehode.neste == null) {
            return false;
        }
        while (node != null) {
            if (node.nkl.compareTo(n) == 0) {
                return true;
            } node = node.neste;
        }
        return false;
    }

    //Sjekker om det forste objektet i beholderen ikke
    //peker paa noe, for saa aa sjekke om det er likt
    //som parameter-objektet, isaafall settes forstepekeren
    //til aa peke pa sin neste-peker og true returneres.
    //Hvis det ikke er likt saa settes forrige
    //til aa peke paa det forste objektet i beholderen, og
    //node til aa peke paa det andre objektet i beholderen.
    //Hvis node er lik paramtereren, settes forrige sin neste-peker
    //til aa peke paa node sin neste-peker, true returneres
    //og antall minkes med 1. Hvis ikke saa settes forrige til aa peke paa node,
    //og node settes til aa peke paa node sin nestepeker.
    //Hvis node saa er like stor som n, saa settes forrige sin neste-peker
    //til aa vaere lik node sin neste-peker og true returneres.
    public boolean fjernElement(N n) {
        if (listehode.neste == null) {
            return false;
        }
        if (listehode.neste.nkl.compareTo(n) == 0){
            listehode.neste = listehode.neste.neste;
            antall--;
            return true;
        }

        Node forrige = listehode.neste;
        Node node = listehode.neste.neste;

        while (node.hasNext()){
            if (node.nkl.compareTo(n) == 0){
                forrige.neste = node.neste;
                antall--;
                return true;
            }
            forrige = node;
            node = node.neste;
        }

        if (node.nkl.compareTo(n) == 0) {
            forrige.neste = node.neste;
            antall--;
            return true;
        }
        return false;
    }

    //Setter det forste objektet i beholderen til aa vaere null
    //og siden objektene peker paa neste-pekere av det forste-objektet
    //blir beholderen tom
    public void fjernAlle() {
        listehode.neste = null;
        antall = 0;
    }

    //Mottar en array, setter node til aa vaere lik listehode
    //og sjekker om node.neste, altsaa det forste objektet,
    //er lik null. Hvis ikke saa gaas arrayen gjennom vha
    //antall objekter i beholderen, og hver plass i arrayen
    //blir satt til aa peke paa det neste objektet i beholderen
    //Naar i er er en mindre enn antall returneres arrayen
    public V[] tilArray(V[] a) {
        Node node = listehode;

        if (node.neste == null) {
            return null;
        } else {
            for (int i = 0; i < antall; i++) {
                a[i] = (V) node.neste.verdi;
                node = node.neste;
            }
        }
        return a;
    }
}

