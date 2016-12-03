package core.domain.vrtualproxy;
/*
 * Les classes qui impl�menteront Factory<T> repr�senteront une fabrique de T. Une fabrique de T
 * va etre utilis�e par notre virtual proxy g�n�rique pour cr�er un objet de type T.
 *
 * Le code qui va se charger de cr�er l'objet de type T sera donc dans la factory, pour eviter
 * de devoir mettre du code sp�cifique dans le VirtualProxyBuilder, qui doit rester g�n�rique.
 */
 
public interface Factory<T> {
	T create(int id);
}
