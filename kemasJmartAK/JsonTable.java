package kemasJmartAK;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


public class JsonTable <T> extends Vector<T>{
	public final String filepath;
	private static final Gson gson = new Gson();
	
	public JsonTable(Class<T> clazz, String filepath) throws IOException {
		this.filepath = filepath;
		
		@SuppressWarnings("unchecked")
		Class<T[]> array = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
		T[] loaded = JsonTable.readJson(array, filepath);
		
		Collections.addAll(this, loaded);
	}
	
	public static <T> T readJson(Class<T> clazz, String filepath) throws FileNotFoundException {
		final JsonReader read = new JsonReader(new FileReader(filepath));
		return gson.fromJson(read, clazz);
	}
	
	public void writeJson() throws IOException{
		writeJson(this, this.filepath);
	}
	
	public static void writeJson(Object object, String filepath) throws IOException{
		final FileWriter penulis = new FileWriter(filepath);
		penulis.write(gson.toJson(object));
		penulis.close();
	}
}
