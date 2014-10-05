
/** Beholder for Person-objekter ***************************************************/
class PersonBeholder {

    private int antall;
    Lelem forstePerson;
    private boolean funnet = false;

    /**
     * Henter antallet i hvert objekts vennebeholder
     *
     * @return int antall
     ***************************************************/
    public int antall() {
        return antall;
    }

    /**
     * Setter inn et Person-objekt i en lenket liste, oeker antall med 1 for
     * hvert objekt
     *
     * @param p
     *            Person-objekt
     ***************************************************/
    public void settInnPerson(Person p) {
        if (erIbeholder(p.hentNavn())) {
            System.out.println("FEIL! " + p.hentNavn()
                    + " ligger allerede i personlista!");
        }
        Lelem lel = new Lelem(p);
        lel.neste = forstePerson;
        forstePerson = lel;
        antall++;
    }

    /**
     * Sjekker om beholderen inneholder navnet til et Person- objekt ved aa gaa
     * gjennom Person-beholderen
     *
     * @param s
     *            String
     * @return funnet
     ***************************************************/
    public boolean erIbeholder(String s) {
        for (Lelem p = forstePerson; p != null; p = p.neste) {
            if (s.equals(p.objektet.hentNavn())) {
                funnet = true;
            }
        }



        return funnet;
    }

    /**
     * Kaller paa skrivUtMegOgVenner saa lenge det neste objektet i lenkelisten
     * ikke har verdien null
     *
     * @param prefiks
     *            String
     ***************************************************/
    public void skrivAlle(String prefiks) {
        Lelem p = forstePerson;

        forstePerson.objektet.skrivUtMegOgVenner(prefiks);

        while (p.neste != null) {
            p.neste.objektet.skrivUtMegOgVenner(prefiks);
            p = p.neste;
        }
    }
}
