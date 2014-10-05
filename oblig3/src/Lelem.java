
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
