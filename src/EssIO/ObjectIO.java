package EssIO;

import java.util.TreeSet;
/**
 * 
 * @author 普皓群
 *
 * @param <T>
 */
public interface ObjectIO<T>{
	public final String PATH = "save/";
	public TreeSet<T> read();
	public void write(TreeSet<T> inputSet);
}