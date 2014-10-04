/**
 * Main
 *
 * @author Roar Hoksnes Eriksen
 ***************************************************/

class Oblig3 {
	public static void main(String[] args) {
		Test start = new Test();
		start.opprett();
		start.testTilstand();

	}
}

/**
 * Oppretter Person-objekter og venneforhold, samt tester de invariante
 * tilstandspaastandelsene iht oppgaveteksten
 ***************************************************/
class Test {

	/**
	 * Inneholder alle Person-objekter
	 ***************************************************/
	PersonBeholder p1 = new PersonBeholder();
	Person emil;
	Person ane;
	Person per;
	Person lars;
	Person arne;
	Person lasse;
	Person kristian;

	/**
	 * Lager Person-objekter, og oppretter venneforhold
	 ***************************************************/
	void opprett() {

		emil = new Person("Emil", p1);
		ane = new Person("Ane", p1);
		per = new Person("Per", p1);
		lars = new Person("Lars", p1);
		arne = new Person("Arne", p1);
		lasse = new Person("Lasse", p1);
		kristian = new Person("Kristian", p1);

		ane.blirVennMed(kristian);
		ane.blirVennMed(emil);
		ane.blirVennMed(lars);
		ane.blirVennMed(lasse);

		kristian.blirVennMed(arne);
		kristian.blirVennMed(per);
		kristian.blirVennMed(emil);
		kristian.blirVennMed(ane);
		kristian.blirVennMed(lasse);
		kristian.blirVennMed(lars);

		arne.blirVennMed(lasse);
		arne.blirVennMed(lars);
		arne.blirVennMed(per);
		arne.blirVennMed(kristian);
		arne.blirVennMed(ane);

		emil.blirVennMed(lasse);
		emil.blirVennMed(lars);
		emil.blirVennMed(per);

		per.blirVennMed(lasse);
		per.blirVennMed(lars);

		lars.blirVennMed(lasse);

		p1.skrivAlle("");
	}

	/**
	 * Tester tilstandpaastander
	 ***************************************************/
	void testTilstand() {
		System.out.println("\nTesting av tilstandpaastander");
		ane.blirVennMed(ane); // Paastand 1;
		ane.blirVennMed(null); // Paastand 2;
		ane.blirVennMed(new Person("Arne", p1)); // Paastand 3
		Person lars2 = lars;
		ane.blirVennMed(lars2); // Paastand 4
	}
}

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

/**
 * Lager et nytt neste-objekt av typen Lelem, siden neste-objektet i Person
 * allerede er opprettet. Slik blir lenkelisten med venner riktig
 ***************************************************/
class Lelem {
	Lelem neste;
	Person objektet;

	/**
	 * Oppretter et Lelem-objekt som tar vare paa et Person-objekt
	 *
	 * @param p
	 *            Person-objekt
	 ***************************************************/
	Lelem(Person p) {
		objektet = p;
	}
}
