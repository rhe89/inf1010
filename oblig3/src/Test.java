
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
