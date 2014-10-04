/***
 * @author Roar Hoksnes Eriksen
 */

/**
 *Generisk klasse for aa ta vaere paa antall objekter i en beholder
 ***********************************************/
public class StorBeholder <T> {
    private T [] alle = (T[]) new Object[100];
    private int antall;

    /**
     *Oppdaterer alle-arrayen og oeker antall med 1
     *hver gang den blir kalt
     *@param det
     ***************************************/
    public void settInn(T det) {
        alle[antall] = det;
        antall++;
    }

    /**
     *Tar ut objektet, og returnerer antallet som naa er i beholderen
     *@return T-objekt alle[antall]
     ***************************************/
    public T taUt() {
        antall--;
        return alle[antall];
    }

    /**
     *Henter verdien til int-variabelen antall
     *@return int antall
     ***************************************/
    public int hentAntall() {

        return antall;
    }
}