package core.domain.vrtualproxy;
/*
 * Les classes qui implémenteront Factory<T> représenteront une fabrique de T. Une fabrique de T
 * va etre utilisée par notre virtual proxy générique pour créer un objet de type T.
 *
 * Le code qui va se charger de créer l'objet de type T sera donc dans la factory, pour eviter
 * de devoir mettre du code spécifique dans le VirtualProxyBuilder, qui doit rester générique.
 */
 
public interface Factory<T> {
	T create(int id);
}
