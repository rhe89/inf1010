/***
 * @author Roar Hoksnes Eriksen
 */

class Person {
    private String navn;
    private Person [] kjenner;
    private Person likerikke;
    private Person forelsketi;
    private Person bestevenn;
    private int teller = 0;

    Person(String n, int lengde) {
        navn = n;
        //Definerer lengden paa kjenner-arrayen
        kjenner = new Person[lengde];
    }

    public String hentNavn() {
        return navn;
    }

    //Returnerer true hvis objektet kjenner p-objektet
    public boolean erKjentMed(Person p) {
        if (p == kjenner[teller] ) {
            return true;
        } else {
            return false;
        }
    }
    //This blir kjent med p-objektet, med mindre this
    //er lik p-objektet. Samme struktur for metodene
    //blirForelsketi og blirUvennMed
    public void blirKjentMed(Person p) {
        if (p != this) {
            kjenner[teller] = p;
            teller++;

        }
    }

    public void blirForelsketI(Person p) {
        if (p != this) {
            forelsketi = p;
        }
    }

    public void blirUvennMed(Person p) {
        if (p != this) {
            likerikke = p;
        }
    }

    //Returnerer true hvis objektet kjenner p, og ikke er
    //uvenn med p
    public boolean erVennMed(Person p) {
        if (erKjentMed(p) && likerikke != p) {
            return true;
        } else {
            return false;
        }
    }
    //Setter likerikke til null hvis objektet kjenner p
    //og er uvenn med p
    public void blirVennMed(Person p) {
        if (erKjentMed(p) && likerikke == p) {
            likerikke = null;
        }

    }
    //Skriver ut objektets venner, ved aa gaa gjennom kjenner-
    //arrayen
    public void skrivUtVenner() {
        System.out.println(navn + " sine venner: ");
        for (Person p : kjenner)
            if ( p != null)  {
                System.out.print(p.hentNavn() + " ");
            }
        System.out. println("");
    }

    //returnerer Person bestevenn, som ligger paa
    //kjenner-arrayets plass 0.
    public Person hentBestevenn() {
        if (kjenner[0] != null) {
            bestevenn = kjenner[0];
        }

        return bestevenn;
    }

    //returnerer kjenner-arrayen, som inneholder alle
    //objektets kjenninger
    public Person[] hentKjenninger() {
        return kjenner;
    }

    public void skrivUtKjenninger () {
        for (Person p : kjenner)
            if ( p != null)  {
                System.out.print(p.hentNavn() + " ");
            }
        System.out. println("");
    }

    public void skrivUtAlt () {
        System.out.print(navn + " kjenner: "); skrivUtKjenninger();
        if (forelsketi != null)
            System.out.println(navn + " er forelsket i " + forelsketi.hentNavn());
        if (likerikke != null)
            System.out.println(navn + " liker ikke " + likerikke.hentNavn());
    }
}