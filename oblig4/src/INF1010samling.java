/***
 * @author Roar Hoksnes Eriksen - roarher
 */

//Grensesnittet som SELLbeholder skal implementere
interface INF1010samling<N extends Comparable<N>,V> extends Iterable<V> {
    public void leggInn(N n, V v);
    public int antall();
    public V hent(N n);
    public V hent(int nr);
    public V hentMinste();
    public V hentStorste ();
    public boolean inneholder(N n);
    public boolean fjernElement(N n);
    public void fjernAlle ();
    public V[] tilArray(V[] a);
}
