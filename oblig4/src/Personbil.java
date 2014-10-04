/***
 * @author Roar Hoksnes Eriksen
 */

//Subklasse for Personbil-objekter
class Personbil extends Kjoretoy{
    Personbil(String regnr, int takst) {
        super(regnr, takst);
    }

    public String hentNavnPaaType() {
        return "Personbil";
    }

    //Gaar gjennom objektets repBeholder og sjekker
    //om p er eier eller mekaniker, og setter den boolske verdien
    //deretter. Saa sjekkes dette i avgiftsutregningen. Bilens
    //regnr settes til "Avskiltet" om en av personene som har
    //reparert bilen er eier men ikke mekaniker
    String eier;
    public double beregnAvgift() {
        Person k;
        for (Person p : repBeholder) {
            k = repBeholder.hent(p.navn);
            if (k.regnr == regnr && k.erEier()) {
                erEier = true;
                System.out.println("hei");
            }
        }
        System.out.println("Navn:" + erEier);
        if (regnr != "Avskiltet") {
            if (erEier && !erMekaniker()) {
                System.out.println("Bilen har blitt reparert av sin eier som ikke er mekaniker! Bilen avskiltes!");
                regnr = "Avskiltet";
                return 0;
            } else if (erEier) {
                System.out.println("Denne bilen har blitt reparert av sin eier");
            }
            if (gangerRep == 0 || erMekaniker()) {
                System.out.println(hentRegnr());
                return takst * 0.05;
            } else if (gangerRepAvMek > (gangerRep / 2)) {
                System.out.println(hentRegnr());
                return takst * 0.075;
            } else {
                System.out.println(hentRegnr());
                return takst * 0.10;
            }


        }return 0;
    }
}
