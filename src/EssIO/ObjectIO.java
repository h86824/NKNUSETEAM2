package EssIO;

import java.util.TreeSet;
/**
 * 所有物件的IO格式 
 * @author 普皓群
 *
 * @param <T>
 */
public interface ObjectIO<T>{
	/*儲存路徑*/
	public final String PATH = "save/";
	/*讀取檔案方法*/
	public TreeSet<T> read();
	/*儲存檔案方法*/
	public void write(TreeSet<T> inputSet);
}