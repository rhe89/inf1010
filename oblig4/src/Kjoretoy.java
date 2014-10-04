/***
 * @author Roar Hoksnes Eriksen
 */

abstract class Kjoretoy {
    protected String navn;
    protected String regnr;
    protected int takst;
    protected int gangerRep;
    protected int gangerRepAvMek;
    protected boolean erMekaniker = false;
    protected boolean erEier = false;
    protected SELLbeholder<String, Person> repBeholder = new SELLbeholder<String, Person>();

    Kjoretoy(String regnr, int takst) {
        this.regnr = regnr;
        this.takst = takst;
        this.navn = navn;
    }

    //Legger til navn og Person-objekt p i repBeholderen
    //med mindre det ligger der fra for av
    //Setter objektets settMekaniker til aa vaere true
    //Tester leggInn()
    public void leggTilRep(String navn, Person p) {
        this.navn = navn;
        if (!repBeholder.inneholder(navn)) {
            repBeholder.leggInn(navn, p);
            gangerRep++;
            if (p instanceof Mekaniker) {
                gangerRepAvMek++;
                erMekaniker = true;
            }
        } else {
            repBeholder.leggInn(navn, p);
        }
    }

    //Tester antall()
    //Henter antall objekter i repBeholderen
    public int hentAntallReperasjoner() {
        return repBeholder.antall();
    }

    //Tester inneholder(n)
    //Sjekker om repBeholderen inneholder parameterens verdi
    public boolean inneholder(String navn) {
        return repBeholder.inneholder(navn);
    }

    //Tester iteratoren
    //Skriver navnet paa dette kjoretoys-objektets reperatorer
    public void hentNavn() {
        System.out.println(navn);
    }

    //Returnerer dette kjoretoys-objektets reperator
    public String hentNavnString() {
        return navn;
    }

    //Returnerer kjoretoys-objektets regnr
    public String hentRegnr() {
        return regnr;
    }

    //Returnerer kjoretoys-objektetets takst
    public int hentTakst() {
        return takst;
    }

    //Tester hent(N n)
    //Returnerer dette objektets klassetype
    public Person hentType() {
        return repBeholder.hent(regnr);
    }

    //Skriver ut dette objektets klassetype
    public String hentNavnPaaType() {
        return "Kjoretoy";
    }

    //Sjekker om dette objektets reperatorer er eiere men ikke mekanikere
    public boolean erEierOgIkkeMek(Person p) {
        if (p.erEier() && !p.erMekaniker()) {
            return true;
        }
        return false;
    }

    //Sjekker om dette objektets reperatorer er eiere
    public boolean erEier(Person p) {
        if (p.erEier()) {
            return true;
        }
        return false;
    }

    //Sjekker om dette objektets reperatorer er mekanikere
    public boolean erMekaniker() {
        if (erMekaniker) {
            return true;
        }
        return false;
    }

    //Metode for beregning av avgift
    public double beregnAvgift() {
        return 0.1;
    }
}
