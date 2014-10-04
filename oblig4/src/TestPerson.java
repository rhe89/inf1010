
/**
 * Used as the V object in collections in the test class
 */
class TestPerson {
    String navn;

    TestPerson(String n) {
        navn = n;
    }

    public String toString() {
        return "TestPerson: " + navn;
    }
}
