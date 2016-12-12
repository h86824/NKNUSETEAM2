package EssIO;

import java.util.ArrayList;
/**
 * 
 * @author 普皓群
 *
 * @param <T>
 */
public interface ObjectIO<T>{
	public final String PATH = "";
	public ArrayList<T> read();
	public void write(ArrayList<T> inputList);
}