/***
 * @author Roar Hoksnes Eriksen
 */

//Subklasse for Buss-objekter
class Buss extends Kjoretoy{
    Buss(String regnr, int takst) {
        super(regnr, takst);
    }

    public String hentNavnPaaType() {
        return "Buss\t";
    }

    public double beregnAvgift() {
		/*
		if (regnr != "Avskiltet") {
			for (Person p : repBeholder) {
				if (erEierOgIkkeMek(p)) {
					System.out.println("Bilen har blitt reparert av sin eier som ikke er mekaniker! Bilen avskiltes!");
					regnr = "Avskiltet";
					return 0;
				} else if (erEier(p)) {
					System.out.println("Denne bilen har blitt reparert av sin eier");
				} 
				if (gangerRep == 0 || erMekaniker(p)) {
					System.out.println(hentRegnr());
					return takst * 0.034;
		 		} else {
		 			System.out.println(hentRegnr());
		 			return takst * 0.012;
				}
			}
		} */return 0;
    }
}