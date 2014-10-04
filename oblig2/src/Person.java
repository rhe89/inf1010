/***
 * @author Roar Hoksnes Eriksen
 */
/**
 *Tar vare paa Person-objekter
 ***********************************************/

public class Person {

    private String navn;
    private String bokKat;
    private Person bestevenn;

    StorBeholder<Bok> mittBibliotek = new StorBeholder<Bok>();

    /**
     *Tar vare paa et Person-objekt, og setter navn og bokKat lik dennes
     ***************************************/
    Person(String navn, String bokKat) {
        this.navn = navn;
        this.bokKat = bokKat;
    }

    /**
     *Henter Person-objektets navn
     *@return Person-objektets navn
     ***************************************/
    public String hentNavn() {
        return navn;
    }

    /**
     *Gjoer Person-objektet til venn med p
     *@param p
     ***************************************/
    public void blirVennMed(Person p) {
        bestevenn = p;
    }

    /**
     *Henter Person-objektets bestevenn
     *@return bestevenn
     ***************************************/
    public Person hentBestevenn () {
        return bestevenn;
    }

    /**
     *Sjekker om Person-objektet har en bestevenn,
     *og returnerer dennes navn, eller "ingen" om
     *Person-objektet ikke har noen venner
     *@return Navnet til bestevennen eller "ingen"
     ***************************************/
    public String minBesteVennHeter () {
        if (bestevenn != null) {
            return bestevenn.navn;
        } else {
            return "ingen";
        }
    }

    /**
     *Skriver ut info om Person-objektets venner og boker
     ***************************************/
    public void skrivUtMeg () {
        System.out.println(navn + " er venn med " + minBesteVennHeter());
        System.out.println(navn + " liker " + bokKat + "-boker og har "
                + mittBibliotek.hentAntall() + " av dem.");

    }
    /**
     *Skriver ut info om alle Person-objektene
     ***************************************/
    public void skrivUtAllt () {
        skrivUtMeg();
        if (hentBestevenn() != null)
            hentBestevenn().skrivUtAllt();
    }

    /**
     *Sjekker om Personens interessefelt stemmer med Bok b
     *@param b
     *@return boolean True om Person-objektet liker kategori b, eller false
     *om Person-objektet ikke gjoer det
     **************************************/
    private boolean mittInteressefelt (Bok b) {
        if (bokKat.equals(b.kategori())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *Sjekker om Person-objektet vil ha boka, ved aa kalle paa metoden
     *mittInteressefelt. Kalles paa settInn-metoden i mittBibliotek for
     *aa oppdatere antall boker i Person-objektets bibliotek
     *@param b
     *@return Bok null om Personen vil ha boka, eller b hvis ikke
     **************************************/
    public Bok vilJegHaBoka(Bok b) {
        if (mittInteressefelt(b)) {
            mittBibliotek.settInn(b);
            return null;
        } else {
            return b;
        }
    }
}