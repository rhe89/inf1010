/***
 * @author Roar Hoksnes Eriksen
 */

import java.io.File;
import java.util.Scanner;

class LesData {
    SELLbeholder<String, Person> persBeholder = new SELLbeholder<String, Person>();
    SELLbeholder<String, Kjoretoy> kjoretoyBeholder = new SELLbeholder<String, Kjoretoy>();

    private Scanner lesFil, lesFil2, tast;
    private File persOgKjorData = new File(System.getProperty("user.dir")+"/nyeData.txt");
    private File eierOgRepData = new File(System.getProperty("user.dir")+"/eierOgRepData.txt");
    private int takst, ant;
    private int antall, nummer;
    private String navn, regnr;
    private Person p;
    private Mekaniker m;
    private Kjoretoy k;
    private Personbil bil;
    private Buss buss;
    private Lastebil lastebil;

    private boolean fantNavn = false;
    private boolean fantBil = false;
    private String valg = "n";
    private Person [] persArray;
    private Kjoretoy [] kArray;


    //Leser filen med personer og mekanikere
    void les() {
        try {
            lesFil = new Scanner(persOgKjorData, "ISO-8859-1");
        } catch (Exception e) {
            System.out.println("Feil! Filen finnes ikke!");
            lesFil = null;
        }
        System.out.println(new File(System.getProperty("user.dir")).getAbsolutePath());
        //Leser personer, legger det i personbeholderen
        lesFil.nextLine();
        ant = lesFil.nextInt();
        lesFil.nextLine();

        for (int i = 0; i < ant; i++) {
            navn = "  " + lesFil.nextLine();
            p = new Person(navn);
            if (!persBeholder.inneholder(navn)) {
                persBeholder.leggInn(navn, p);
            }
        }

        //Leser mekanikere, hvis navnet finnes i persbeholderen fra for
        //slettes objektet og et nytt Mekaniker-objekt legges til paa
        //samme plass
        lesFil.nextLine();
        ant = lesFil.nextInt();
        lesFil.nextLine();


        for (int i = 0; i < ant; i++) {
            navn = "  " + lesFil.nextLine();
            m = new Mekaniker(navn);
            if (persBeholder.inneholder(navn)) {
                persBeholder.fjernElement(navn);
                persBeholder.leggInn(navn, m);
                persBeholder.hent(navn).erMekaniker = true;
            } else {
                persBeholder.leggInn(navn, m);
                persBeholder.hent(navn).erMekaniker = true;
            }
        }

        //Leser personbiler, legger det i kjoretoybeholderen
        lesFil.next();
        ant = lesFil.nextInt();

        for (int i = 0; i < ant; i++) {
            regnr = lesFil.next();
            takst = Integer.parseInt(lesFil.next());
            bil = new Personbil(regnr, takst);
            if (!kjoretoyBeholder.inneholder(regnr)) {
                kjoretoyBeholder.leggInn(regnr, bil);
            }
        }

        //Leser lastebiler, legger det i kjoretoybeholderen
        lesFil.next();
        ant = lesFil.nextInt();

        for (int i = 0; i < ant; i++) {
            regnr = lesFil.next();
            takst = Integer.parseInt(lesFil.next());
            lastebil = new Lastebil(regnr, takst);
            if (!kjoretoyBeholder.inneholder(regnr)) {
                kjoretoyBeholder.leggInn(regnr, lastebil);
            }
        }

        //Leser busser, legger det i kjoretoybeholderen
        lesFil.next();
        ant = lesFil.nextInt();

        for (int i = 0; i < ant; i++) {
            regnr = lesFil.next();
            takst = Integer.parseInt(lesFil.next());
            buss = new Buss(regnr, takst);
            if (!kjoretoyBeholder.inneholder(regnr)) {
                kjoretoyBeholder.leggInn(regnr, buss);
            }
        }

        //Leser filen med eiere og reperasjoner
        try {
            lesFil2 = new Scanner(eierOgRepData, "ISO-8859-1");
        } catch (Exception e) {
            System.out.println("Feil! Filen finnes ikke!");
            lesFil2 = null;
        }

        lesFil2.nextLine();
        ant = lesFil2.nextInt();

        //Leser eiere, legger regnr i bilBeholderen i Person-klassen
        for (int i = 0; i < ant; i++) {
            regnr = lesFil2.next();
            navn = lesFil2.nextLine();
            //Sjekker om persBeholderen inneholder navnet, og legger til
            //regnr og Kjoretoysobjektet i bilBeholderen i Person-klassen
            if (persBeholder.inneholder(navn)) {
                p = persBeholder.hent(navn);
                if (kjoretoyBeholder.inneholder(regnr) && kjoretoyBeholder.hent(regnr) instanceof Buss) {
                    p.leggTilKjoretoy(regnr, kjoretoyBeholder.hent(regnr));
                } else if (kjoretoyBeholder.inneholder(regnr) && kjoretoyBeholder.hent(regnr) instanceof Personbil) {
                    p.leggTilKjoretoy(regnr, kjoretoyBeholder.hent(regnr));
                } else if (kjoretoyBeholder.inneholder(regnr) && kjoretoyBeholder.hent(regnr) instanceof Lastebil) {
                    p.leggTilKjoretoy(regnr, kjoretoyBeholder.hent(regnr));
                }
            }
        }

        //Leser reperasjoner, legger reperatorer i repBeholderen i Kjoretoy-klassen
        //
        lesFil2.next();
        ant = lesFil2.nextInt();
        for (int i = 0; i < ant; i++) {
            regnr = lesFil2.next();
            navn = lesFil2.nextLine();
            if (persBeholder.inneholder(navn)) {
                p = persBeholder.hent(navn);
                if (p instanceof Mekaniker) {
                }
            }
            //Sjekker om kjoretoysbeholderen allerede inneholder dette regnr
            //hvis ikke legges det til i repBeholderen i Kjoretoy-klassen
            //som enten Buss, Lastebil eller Personbil-objekt
            if (kjoretoyBeholder.inneholder(regnr)) {
                if (kjoretoyBeholder.hent(regnr) instanceof Buss) {
                    if (!kjoretoyBeholder.hent(regnr).inneholder(navn)) {
                        kjoretoyBeholder.hent(regnr).leggTilRep(navn, p);
                    }
                } else if (kjoretoyBeholder.hent(regnr) instanceof Lastebil) {
                    if (!kjoretoyBeholder.hent(regnr).inneholder(navn)) {
                        kjoretoyBeholder.hent(regnr).leggTilRep(navn, p);
                    }
                } else if (kjoretoyBeholder.hent(regnr) instanceof Personbil) {
                    if (!kjoretoyBeholder.hent(regnr).inneholder(navn)) {
                        kjoretoyBeholder.hent(regnr).leggTilRep(navn, p);
                    }
                }
            }
        }
    }


    //Test for henting av bileeire, mekanikere og avgift. Bruker kan taste
    //inn hhv navn og regnr for aa se bileiere og reperasjoner.
    void hentPersoner() {

        tast = new Scanner(System.in);
/*
		valg = "n";
		System.out.println("\nVIL DU SE EN LISTE OVER ALLE PERSONER? (y/n)");
		valg = tast.nextLine();

		if (valg.equals("y")) {
			//Lager et array lik antall objekter i persbeholderen, sender
			//det som parameter til tilArray()-metoden i SELLbeholderen, 
			//og skriver ut verdiene det faar returnert
			System.out.println("Tester tilArray()");
			persArray = new Person[persBeholder.antall()];
			persArray = persBeholder.tilArray(persArray);
			for (int i = 0; i < persArray.length; i++) {
				System.out.println(i+1 + ". " + persArray[i].hentNavn());
			}
		}

		System.out.println("\n================");
		System.out.println("BILEIERE");
		System.out.println("================");
		System.out.println("Tester hent(N n)");
		System.out.println("OPPGI EN BRUKER SOM DU VIL SE HVILKE BILER DE EIER");
		navn = tast.nextLine();

		//Gaar gjennom bilbeholderen i Person-klassen, og skriver
		//ut hver persons biler, hvilken bil som har det storste regnr-
		//nr og det minste regnr
		System.out.println("\nLISTE OVER " + navn + "s BILER");
		System.out.println("------------------------------------------");
		for (Person p : persBeholder) {
			if (persBeholder.inneholder("  " + navn)) {
			fantNavn = true;
				if (p != null) {
					if (p.hentNavn().equals("  " + navn)) {
						antall += p.hentAntallBiler();
						if (p.hentType() instanceof Personbil) {
							System.out.println("Personbil ");
							p.hentRegnr();
							fantBil = true;
						} else if (p.hentType() instanceof Buss) {
							System.out.println("Buss ");
							p.hentRegnr();
							fantBil = true;
						} else if (p.hentType() instanceof Lastebil) {
							System.out.println("Lastebil ");	
							p.hentRegnr();
							fantBil = true;	
						} 
							if (fantBil) {
							System.out.println("Bilen med det hoyeste regnret");
							System.out.println(p.hentStorste());
							System.out.println("Bilen med det laveste regnret");
							System.out.println(p.hentMinste());
						}
					}
				}
			} 
		}
		

		System.out.println("------------------------------------------");
		

		//Skriver feilmelding om enten navnet ikke finnes eller personen
		//ikke eier noen biler
		if (fantNavn != true) {
			System.out.println("\nDenne personen finnes ikke!");
		} 
		else if (fantBil != true) {
			System.out.println("\nDenne personen eier ingen biler!");
		} else {

			System.out.println("\n" + navn + " eier " + antall + " biler");
		}

		valg = "n";
		System.out.println("\nVIL DU FJERNE EN PERSON FRA LISTA? (y/n)");
		valg = tast.nextLine();
		if (valg.equals("y")) {
			System.out.println("Tester fjernElemenet(N n)");
			System.out.println("TAST INN ET NAVN PAA DEN DU VIL FJERNE");
			navn = tast.nextLine();
			//Fjerner elementet fra beholderen
			if (persBeholder.fjernElement("  " + navn)) {
				System.out.println("PERSONEN BLE FJERNET");
			} else {
				System.out.println("PERSONEN FINNES IKKE");
			}
		}
		

		System.out.println("\nTester antall(), hentStorste() og hentMinste() i persBeholderen");
		System.out.println("ANTALL PERSONER: ");
		System.out.println(persBeholder.antall());
		System.out.println("PERSONEN MED DET STORSTE NAVNET");
		System.out.println(persBeholder.hentStorste().hentNavn());
		System.out.println("PERSONEN MED DET MINSTE NAVNET");
		System.out.println(persBeholder.hentMinste().hentNavn());
			
		antall = 1;
		valg = "n";
		System.out.println("\nVIL DU SE EN LISTE OVER ALLE REGNR? (y/n)");
		valg = tast.nextLine();
		//Gaar gjennom beholderen vha en for each-lokke, og skriver ut plassering og regnr
		if (valg.equals("y")) {
			System.out.println("Tester iteratoren");
			for (Kjoretoy k : kjoretoyBeholder) {
				System.out.println(antall + ". " + k.hentRegnr());
				antall++;
			}
		}

		System.out.println("\n================");
		System.out.println("REPARASJONER");
		System.out.println("================");

		System.out.println("\nOPPGI ET NUMMER I REGNR-LISTA FOR AA SE HVEM SOM HAR REPARERT KJORETOYET");

		//Fanger exception om nummer ikke er en int
		try {
			nummer = tast.nextInt();
		} catch (Exception InputMismatchException) {
			System.out.println("Tast inn et heltallsnummer!");
			nummer = 0;
			tast.nextLine();
			nummer = tast.nextInt();
		}	

		//Sier ifra om nummer er storre en antall objekter i beholderen
		if (nummer > antall) {
			System.out.println("TAST INN ET NUMMER MELLOM 1 OG " + antall);
		}

		System.out.println("\nTester hent(int nr)");
		//Skriver ut plassen i regnrlistas reperatorer
		System.out.println("\nLISTE OVER " + kjoretoyBeholder.hent(nummer).hentRegnr() + "s REPARASJONER");
		System.out.println("------------------------------------------");
		antall = 0;
		fantNavn = false;
		fantBil = false;
		if (kjoretoyBeholder.hent(nummer) != null) {
			fantBil = true;
			k = kjoretoyBeholder.hent(nummer);
			k.hentNavn();
			antall += k.hentAntallReperasjoner();
		}

		System.out.println("------------------------------------------");
		//Skriver ut feilmelding om regnr ikke finnes
		if (!fantBil) {
			System.out.println("\nDenne bilen finnes ikke!");
		} else {
			System.out.println("\n" + k.hentRegnr() + " har blitt reparert " + antall + " ganger");
		}
	

		System.out.println("\nTester antall(), hentStorste() og hentMinste() i kjoretoybeholderen");
		System.out.println("ANTALL BILER: ");
		System.out.println(kjoretoyBeholder.antall());
		System.out.println("BILEN MED HOYEST REGNR");
		System.out.println(kjoretoyBeholder.hentStorste().hentRegnr());
		System.out.println("BILEN MED LAVEST REGNR");
		System.out.println(kjoretoyBeholder.hentMinste().hentRegnr());

	
		tast.nextLine();
*/
        valg = "n";
        System.out.println("\nVIL DU SE EN LISTE OVER AVGIFT? (y/n)");
        valg = tast.nextLine();

        if (valg.equals("y")) {
            antall = 1;
            System.out.println("LISTE OVER AVGIFT");

            //Henter avgift for hvert regnr
            for (Kjoretoy k : kjoretoyBeholder) {
                System.out.println("TYPE KJORETOY \t AVGIFT \t REGNR");
                System.out.println(k.beregnAvgift());
                System.out.println("-----------------------------------------");
                antall++;
            }
        }


        valg = "n";
        System.out.println("\nVIL DU SLETTE ALLE BILER OG PERSONER FRA BEHOLDEREN? (y/n)");
        valg = tast.nextLine();

        System.out.println("\nTester fjernAlle()");
        //Kaller paa fjernAlle-metoden i SELLbeholderen, og skriver ut antall
        //etter at operasjonen er utfort
        if (valg.equals("y")) {
            kjoretoyBeholder.fjernAlle();
            persBeholder.fjernAlle();

            System.out.println("Antall biler i beholderen etter sletting: " + kjoretoyBeholder.antall());
            System.out.println("Antall personer i beholderen etter sletting: " + persBeholder.antall());
        }

        System.out.println("Programmet avsluttes!");

    }
}
