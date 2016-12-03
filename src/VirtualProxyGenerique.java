import java.lang.reflect.*;
import java.util.*;

/*
 * Dans cet exemple, on veut montrer comment creer un virtual proxy generique, qui va pouvoir servir 
 * chaque fois qu'on aura besoin de virtual proxy pour une classe donn�e. On va utiliser la reflexion
 * pour pouvoir faire cela sans devoir refaire un VirtualProxy specifique pour chaque classe.
 *
 * Ainsi, pour n'importe quel type (classe) T, chaque fois qu'un objet instance de T sera attendu
 * (par exemple pour un attribut d'objet du domaine), on pourra � la place renvoyer un virtual proxy,
 * qui va initialiser/remonter/construire l'objet r�el la premiere fois qu'une m�thode sera appel�e.
 *
 * Pour ce faire, on va creer deux choses, un builder de virtual proxy VirtualProxyBuilder<T> qui va 
 * pouvoir construire un virtual proxy pour le type T (T �tant le type de l'objet "r�el" qui sera derri�re
 * le virtual proxy).
 *
 * On va aussi cr�er une interface Factory<T> qui va permettre de repr�senter des fabriques qui vont g�rer
 * l'initialisation de l'objet T.
 *
 *
 */
 


class VirtualProxyGenerique {

    /*
     * Les classes qui impl�menteront Factory<T> repr�senteront une fabrique de T. Une fabrique de T
     * va etre utilis�e par notre virtual proxy g�n�rique pour cr�er un objet de type T.
     *
     * Le code qui va se charger de cr�er l'objet de type T sera donc dans la factory, pour eviter
     * de devoir mettre du code sp�cifique dans le VirtualProxyBuilder, qui doit rester g�n�rique.
     */
     
    static interface Factory<T> {
        T create();
    }
    
    /*
     * Le VirtualProxyBuilder<T> est une classe qui va construire un virtual proxy pour un objet de type T.
     * Attention a ne pas confondre le VirtualProxyBuilder<T>, et le virtual proxy (qui est l'objet renvoye
     * par la methode getProxy())
     */
    static class VirtualProxyBuilder<T> implements InvocationHandler {
        /* C'est l'objet "reel", celui pour lequel on fait le proxy. Il est null au debut, il sera cree lors du premier appel de methode. */
        T realObject = null; 
        
        /* C'est la fabrique qui nous permettra de creer l'objet realObject, au moment opportun. */
        Factory<T> factory; 
        
        /*
         * iface c'est l'objet qui va representer le type T.
         * Ca sera egal a T.class, sauf qu'on ne peut pas ecrire ca en Java a cause du type erasure.
         */
        Class<?> iface;
        /*
         * On passe iface (qui doit repr�senter le type T), par exemple si on cree un VirtualProxyBuilder<Toto>
         * alors iface doit etre Toto.class. Si on ne respecte pas ceci, on aura une exception lors de l'appel
         * de getProxy()
         */
        public VirtualProxyBuilder(Class<?> iface, Factory<T> factory) {
            this.iface = iface;
            this.factory = factory;
        }
        
        /*
         * getProxy() va creer un virtual proxy de T, et le retourner.
         * On appelle Proxy.newProxyInstance(), qui fait partie de l'API reflexion Java.
         * Le @SuppressWarnings() est la pour �viter le warning � propos du cast (T),
         * en effet rien ne permet au compilateur Java d'�tre sur que au moment de l'execution
         * le retour de Proxy.newProxyInstance() sera bien de type T.
         *
         * Par contre nous on peut en etre sur a partir du moment o� iface represente bien le type T.
         */
        public T getProxy() {
            @SuppressWarnings("unchecked")
            T r  = (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface }, this);
            System.out.println("PROXY: On a cree le virtual proxy!");
            return r;
        }

        /*
         * Cette methode sera appellee automatiquement (via la reflexion Java) chaque fois qu'une methode
         * va etre appel�e sur le virtual proxy (c-a-d sur l'objet retourn� par getProxy())
         *
         * On va simplement relayer l'appel (a l'aide de la reflexion) sur l'objet r�el, realObject.
         *
         * Si realObject est null, alors on le cr�� � l'aide de la factory.
         */
 	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
 	        System.out.println("PROXY: On a appelle la methode " + method.getName() + " sur le virtual proxy!");
 	        if (realObject == null) {
 	            System.out.println("PROXY: On initialise l'objet proxyfi� maintenant");
 	            realObject = factory.create();
 	        }
 	        System.out.println("PROXY: On appelle la methode sur l'objet reel.");
 	        return method.invoke(realObject, args);
  	}
    }
    
    
    /* 
     * C'est une factory simple pour List<Integer> qui va simplement creer une liste {1,2,3}.
     */
    static class NumFactory implements Factory<List<Integer> > {
        public List<Integer> create() {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(1);
            al.add(2);
            al.add(3);
            return al;
            /*
             * Dans une situation r�elle, ici, on pourrait cr�er notre objet en appelant le DataMapper pour le
             * r�cuperer depuis la base de donn�es.
             */
        }
    }

    /* Tout un tas d'exceptions peuvent etre lancees par les methodes liees a la Reflexion. pour plus d'information
     * consulter la doc java */
    static public void main(String args[]) throws IllegalAccessException, InvocationTargetException, Throwable {
        
        /* 
         * On cree notre fabrique dont le role est de construire une liste d'integer.
         * En pratique, cette fabrique peut faire ce qu'on veut, remonter des objets depuis la B.D.D, etc.
         */
        
        NumFactory nf = new NumFactory(); 
        
        /*
         * On fait appel au VirtualProxyBuilder pour generer un virtualproxy pour le type List<Integer>,
         * qui va etre associe a notre fabrique de type NumFactory.
         */
         
        List<Integer> maListe = new VirtualProxyBuilder<List<Integer> >(List.class, nf).getProxy();

        /* 
         * Ici notre variable maListe est en fait un proxy, mais peut �tre utilis�e comme une liste d'integers classique. 
         *
         * L'objet r�el (de type List<Integer>) n'est pas encore cr�� � ce moment la. Il le sera uniquement des qu'on va
         * essayer d'appeler une m�thode sur maListe. C'est int�ressant car ca permet de reporter la cr�ation de l'objet,
         * (le chargement depuis la base de donn�es) jusqu'au dernier moment : on ne fera pas de remont�e "couteuse"
         * depuis la B.D.D tant qu'on n'est pas certain que c'est n�cessaire.
         * 
         */
        
        for (Integer i : maListe) {
            /* 
             * Des qu'on veut iterer sur maListe, la methode maListe.iterator() est appellee.
             * Ceci provoque l'appel (via le systeme de reflexion) de la methode invoke() du VirtualProxyBuilder.
             * La methode invoke() fera appel � la fabrique NumFactory pour cr�er l'objet r�el (liste)
             * puis va appeler la methode iterator() sur l'objet r�el.
             */
            System.out.println(i);
        }
        
    }
}
