package EssIO;

import java.util.ArrayList;

public interface ObjectIO<T>{
	public ArrayList<T> read();
	public void write(ArrayList<T> inputList);
}