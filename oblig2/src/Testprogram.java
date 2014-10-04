/***
 * @author Roar Hoksnes Eriksen
 */

/**
 *Klasse for oppretting av Person-objekter, og tildeling
 *av Bok-objekter til Person-objektene
 ***********************************************/
class Testprogram {

    Person lisa = new Person("Lisa", "krim");
    Person jeg = new Person("Roar", "poesi");
    Person ramzi = new Person("Ramzi", "mat");
    Person emil = new Person("Emil", "sports");

    /**
     *Lager venne-forhold, og Bok-objekter
     ***************************************/

    public void opprett() {
        jeg.blirVennMed(lisa);
        lisa.blirVennMed(ramzi);
        ramzi.blirVennMed(emil);

        vilNoenHaBoka(new Bok("Java"));
        vilNoenHaBoka(new Bok("krim"));
        vilNoenHaBoka(new Bok("historie"));
        vilNoenHaBoka(new Bok("sports"));
        vilNoenHaBoka(new Bok("poesi"));
        vilNoenHaBoka(new Bok("sports"));
        vilNoenHaBoka(new Bok("poesi"));
        vilNoenHaBoka(new Bok("baby"));
        vilNoenHaBoka(new Bok("poesi"));

        jeg.skrivUtAllt();
    }
    /**
     *Tilbyr boker til hvert Person-objekt.
     *Hvis et Person-objekt vil ha boka, settes
     *bok lik null, og neste Person-objekt blir ikke spurt
     *@param Bok-objekt
     ***************************************/
    public void vilNoenHaBoka(Bok b) {
        Bok bok = emil.vilJegHaBoka(b);

        if (bok != null) {
            bok = ramzi.vilJegHaBoka(b);
        }
        if (bok != null) {
            bok = lisa.vilJegHaBoka(b);
        }
        if (bok != null) {
            bok = jeg.vilJegHaBoka(b);
        }
    }
}
