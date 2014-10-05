
/**
 * Tar vare paa Person-objekter
 ***************************************************/
class Person {

    /**
     * Inneholder Person-objektets venner
     ***************************************************/
    private PersonBeholder mineVenner;
    private String navn;
    Person neste;

    /**
     * Tar vare paa et Person-objekt, setter objektet inn i PersonBeholder p1,
     * samt deklarerer PersonBeholder mineVenner
     ***************************************************/
    Person(String navn, PersonBeholder p1) {
        this.navn = navn;
        if (p1 != null) {
            mineVenner = new PersonBeholder();
            p1.settInnPerson(this);
        }
    }

    /**
     * Henter Person-objektets navn
     *
     * @return String navn
     ***************************************************/
    public String hentNavn() {
        return navn;
    }

    /**
     * Henter Person-objektets antall venner
     *
     * @return int antall
     ***************************************************/
    public int antallVenner() {
        return mineVenner.antall();
    }

    /**
     * Oppretter venne-forhold, samt holder orden paa tilstandspaastand 1, 2 og
     * 4
     *
     * @param p
     *            Person-objekt
     ***************************************************/
    public void blirVennMed(Person p) {
        if (p == null) {
            System.out.println("FEIL! Objektet ligger ikke i personlista!");
        } else if (p.navn == navn) {
            System.out.println("FEIL! " + p.navn
                    + " kan ikke bli venn med seg selv!");
        } else if (erVennMed(p.navn)) {
            System.out.println("FEIL! " + navn + " er allerede venn med "
                    + p.navn);
        } else {
            mineVenner.settInnPerson(p);
        }
    }

    /**
     * Sjekker om et Person-objekt er venn med et annet
     *
     * @param s
     *            String
     * @return true eller false
     ***************************************************/
    boolean erVennMed(String s) {
        if (mineVenner.erIbeholder(s)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Skriver ut et navnet til et Person-objekt, og og om objektet har venner,
     * navnene til disse
     *
     * @param prefiks
     *            String
     ***************************************************/
    public void skrivUtMegOgVenner(String prefiks) {

        System.out.println(prefiks + navn);

        if (prefiks.equals("")) {
            if (mineVenner.antall() > 0) {
                mineVenner.skrivAlle(" > ");
            } else {
                System.out.println(" >  har ingen venner! :/");
            }
        }
    }
}
