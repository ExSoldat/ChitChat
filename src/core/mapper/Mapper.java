package core.mapper;

import java.util.ArrayList;

public interface Mapper<T> {
	//CRUD methods
	
	public boolean create(T object);
	public ArrayList<T> read();
	public T readById(int id);
	public boolean update(T object);
	public boolean delete(T object);
}
