/***
 * @author Roar Hoksnes Eriksen
 */
class LagStruktur {
    public Person jeg;
    public Person emil;
    public Person lisa;
    public Person ramzi;

    void leggTilObjekter() {
        //Oppretter Person-objektene
        //og sender med navn og antall bekjente
        jeg = new Person("Roar", 3);
        emil = new Person("Emil", 2);
        lisa = new Person("Lisa", 2);
        ramzi = new Person("Ramzi", 3);

        //Oppretter tilstandene for hvert Person-objekt

        jeg.blirKjentMed(lisa);
        jeg.blirKjentMed(emil);
        jeg.blirKjentMed(ramzi);
        emil.blirKjentMed(jeg);
        emil.blirKjentMed(ramzi);
        emil.blirForelsketI(jeg);
        emil.blirUvennMed(lisa);
        lisa.blirKjentMed(emil);
        lisa.blirKjentMed(ramzi);
        lisa.blirForelsketI(ramzi);
        lisa.blirUvennMed(jeg);
        ramzi.blirKjentMed(jeg);
        ramzi.blirKjentMed(emil);
        ramzi.blirKjentMed(lisa);
        ramzi.blirUvennMed(emil);
        ramzi.blirForelsketI(lisa);

        // Tester metodene som er gitt i opppgaveteksten
        Person[]lisaKjenner = lisa.hentKjenninger();
        System.out.println(lisaKjenner[1].hentBestevenn().hentNavn());
        skrivUt();

    }
    //Metode som skriver ut alle tilstandene for hvert
    //Person-objekt
    void skrivUt() {
        jeg.skrivUtAlt();
        emil.skrivUtAlt();
        lisa.skrivUtAlt();
        ramzi.skrivUtAlt();
    }
}
