/***
 * @author Roar Hoksnes Eriksen
 */

class Person {
    protected String navn;
    protected String regnr;
    protected boolean erEier = false;
    protected boolean erMekaniker = false;
    SELLbeholder<String, Kjoretoy> bilBeholder = new SELLbeholder<String, Kjoretoy>();

    Person(String navn) {
        this.navn = navn;
    }

    //Tester leggInn()
    //Legger til regnr og Kjoretoys-objektet i bilBeholderen
    //med mindre det ligger det fra for av
    //Setter settEier til true for dette objektet
    public void leggTilKjoretoy(String regnr, Kjoretoy k) {
        this.regnr = regnr;
        if (!bilBeholder.inneholder(regnr)) {
            bilBeholder.leggInn(regnr, k);
            erEier = true;
        }
    }

    //Tester antall()
    //Kaller paa bilBeholderens metode hentAntall(),
    //og returnerer dennes verdi
    public int hentAntallBiler() {
        return bilBeholder.antall();
    }

    //Returnerer objektets navn
    public String hentNavn() {
        return navn;
    }

    //Tester hentMinste()
    //Henter det minste objektets regnr i bilBeholderen
    public String hentMinste() {
        return bilBeholder.hentMinste().regnr;
    }
    //Tester hentStorste()
    //Henter det storste objektets regnr i bilBeholderen
    public String hentStorste() {
        return bilBeholder.hentStorste().regnr;
    }
    //Tester iteratoren
    //Skriver ut bilens regnr
    public void hentRegnr() {
        for (Kjoretoy k : bilBeholder) {
            System.out.println(k.hentRegnr());
        }
    }
    //Sjekker om dette objektet er eier
    public boolean erEier() {
        if (erEier == true) {
            return true;
        } else {
            return false;
        }
    }

    //Sjekker om dette objektet er mekaniker
    public boolean erMekaniker() {
        if (erMekaniker == true) {
            return true;
        } else {
            return false;
        }
    }

    //Tester hent(N n)
    public Kjoretoy hentType() {
        return bilBeholder.hent(regnr);
    }
}
