package oml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * @author Grigoras Cristinel grig77@gmail.com
 */
public class PrintOmlSurfaceFile implements Serializable {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(PrintOmlSurfaceFile.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] arg) {
		try {
			PrintOmlSurfaceFile prt = new PrintOmlSurfaceFile();
			prt.readSurseText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readSurseText(String string) throws Exception {
		Reader textFileReader = new InputStreamReader(new FileInputStream(
				string));
		Scanner rn = new Scanner(textFileReader);
		int contor = 0;
		while (rn.hasNextLine()) {
			String line = rn.nextLine();
			contor++;
			if (contor <= 1) {
				continue;
			}
			Scanner rnl = new Scanner(line);
			rnl.useDelimiter(",");
		}
	}

	private final File outputFile;

	public PrintOmlSurfaceFile() throws Exception {
		super();
		outputFile = new File("");
		outputFile.createNewFile();
		System.out.println("Output file:" + outputFile.getCanonicalPath());
	}

	int contorGasite = 0;

	private void scriePrimaLinie(PrintWriter printer) {
		printer.println("6 lines of optional text before data are listed.;;;;;;;;;;;");
		printer.println("Area sources.;;;;;;;;;;;");
		printer.println(";;;;;;;;;;;");
		printer.println("TextID;xkoor;ykoor;L1;L2;alfa;Hs;Hb;Emiss1;Emiss2;Emiss3;Time variation");
		printer.println("string;real;real;real;real;real;real;real;real;real;real;Integer");
		printer.println("max 8 char;m ;m     ;m;m;deg.;m ;m ;g/s;g/s;g/s;(1-5)");
	}
}
