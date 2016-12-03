package core.domain.vrtualproxy;

import java.util.ArrayList;
import java.util.List;

import core.domain.Hobby;
import core.domain.User;
import core.persistence.mapper.UserMapper;


public class UserFactory implements Factory<User>{
	/*static class NumFactory implements Factory<List<Integer> > {
        public List<Integer> create() {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(1);
            al.add(2);
            al.add(3);
            return al;
            
             Dans une situation réelle, ici, on pourrait créer notre objet en appelant le DataMapper pour le
             récuperer depuis la base de données.
        
        }
    }*/
	
	public User create(int id) {
		return UserMapper.getInstance().readById(id);
	}
}
