/***
 * @author Roar Hoksnes Eriksen
 */

class Oblig4 {
	public static void main(String[] args) {
		//Kjorer testen til Erlend Vestad
		TestSamling t = new TestSamling();
		t.runTests();

		//Kjorer min egen test
		LesData l = new LesData();
		l.les();
		l.hentPersoner();
	} 
}
