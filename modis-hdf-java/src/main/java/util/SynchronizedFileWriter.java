package util;

import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Incerc sa scriu in fisier din mai multe threaduri
 * 
 * @author geo-track idx264
 * 
 */
public class SynchronizedFileWriter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrintWriter writer;
	private String fileName;

	public SynchronizedFileWriter(String fileName) throws Exception {
		super();
		this.fileName = fileName;
		File file = new File(this.fileName);
		writer = new PrintWriter(file);
		System.out.println("Outoput file:" + file.getCanonicalPath());
	}

	public synchronized void println(String newLine) {
		writer.println(newLine);
		writer.flush();
	}

	public synchronized void print(String text) {
		writer.print(text);
		writer.flush();
	}

	@Override
	protected void finalize() throws Throwable {
		writer.close();
		super.finalize();
	}

	public void println() {
		writer.println();
		writer.flush();

	}

}
