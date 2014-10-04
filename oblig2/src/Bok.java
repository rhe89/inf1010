
/**
 * @author Roar Hoksnes Eriksen
 *
 *Tar vare paa Bok-objekter
 ***********************************************/

public class Bok {
    private String kat;

    /**
     *Tar vare paa et Bok-objekt, og setter kat lik dennes
     *****************************************/
    Bok (String kat) {
        this.kat = kat;
    }

    /**
     *Henter Bok-objektens kategori
     *@return String kat
     ***************************************/
    public String kategori() {
        return kat;
    }
}
