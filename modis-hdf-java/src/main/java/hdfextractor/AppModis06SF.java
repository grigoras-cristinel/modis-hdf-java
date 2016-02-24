package hdfextractor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 * Aplicatie care extrage datele de spre nori din HDF in coordonatele de statii
 * date si le pune in folderul out pentru fiecare statie. Modificata pentru
 * fisiere hdf intr-un singur folder. Filtreaza fisierele din folder si asa
 * poate rula pe mai multe fire de executie.
 * 
 * @author Grigoras Cristinel
 * 
 * 
 */
public class AppModis06SF {
	public static String HDFFILES_FOLDER = ConfigurationLocalComputer.CALE_DATE_SATELIT
			+ "MOD06/";

	/**
	 * Doi parametri primul e satelitul iar al doilea e anul
	 * 
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int test = 0;
		int contor = 0;
		System.out.println("args" + Arrays.toString(args));
		if (args.length < 2) {
			// System.exit(1);
		}
		String anii[] = new String[] { args[1] };
		String sateliti[] = new String[] { args[0] };
		// String anii[] = new String[] { "2008" };
		// String sateliti[] = new String[] { "MOD" };
		HashMap<String, HashSet<String>> historyProcesare = new HashMap<String, HashSet<String>>();
		File bkupFisereRulate = new File("d:/grig/temp/"
				+ AppModis06SF.class.getSimpleName() + "historyProcesare.dmp");
		if (bkupFisereRulate.exists()) {
			try {
				FileInputStream fin = new FileInputStream(bkupFisereRulate);
				ObjectInputStream oin = new ObjectInputStream(fin);
				historyProcesare = (HashMap<String, HashSet<String>>) oin
						.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String runPath = ConfigurationLocalComputer.CALE_DATE_SATELIT
				+ "/extras/" + AppModis06SF.class.getSimpleName() + "/";
		String runPathProgress = ConfigurationLocalComputer.CALE_DATE_SATELIT
				+ "/extras/" + AppModis06SF.class.getSimpleName() + "R1/";
		File folderRunPathProgress = new File(runPathProgress);
		folderRunPathProgress.mkdirs();
		for (int j = 0; j < anii.length; j++) {
			String anul = anii[j];
			for (int k = 0; k < sateliti.length; k++) {
				contor = 0;
				String satelit = sateliti[k];
				if (!historyProcesare.containsKey(anul + satelit)) {
					historyProcesare.put(anul + satelit, new HashSet<String>());
				}
				HashSet<String> tt = historyProcesare.get(anul + satelit);
				String outputPath = runPath;
				String pathname_output = outputPath;
				HashMap<String, PrintWriter> outputFiles = new HashMap<String, PrintWriter>();
				TreeMap<String, double[]> statii = StatiiMeteoModis04Aerosol.statii;
				for (String siteName : statii.keySet()) {
					File folderOutput = new File(pathname_output);
					if (!folderOutput.exists()) {
						folderOutput.mkdirs();
					}
					File fileOutput = new File(pathname_output + satelit
							+ "06_" + anul + "_" + siteName + ".txt");
					System.out.println("Output file:" + fileOutput);
					try {
						PrintWriter pw = new PrintWriter(fileOutput);
						pw.println(Mod06TO.longHeader());
						outputFiles.put(siteName, pw);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				String inputPath = HDFFILES_FOLDER;
				File folderInput = new File(inputPath);
				System.out.println("HDF files source folder:" + folderInput);
				FileFilter fileFilter = new WildcardFileFilter(satelit
						+ "06_L2.A" + anul + "*.hdf");
				try {
					File[] deCitit = folderInput.listFiles(fileFilter);
					for (int i = 0; i < deCitit.length; i++) {
						File file = deCitit[i];
						if (file.isDirectory()) {
							continue;
						}
						final String name = file.getName();
						if (!name.endsWith("hdf")) {
							continue;
						}
						if (folderRunPathProgress.list(new FilenameFilter() {
							@Override
							public boolean accept(File dir, String nume) {
								if (name.equals(nume)) {
									return true;
								}
								return false;
							}
						}).length > 0) {
							System.out.println(i
									+ "\t File hdf found in folder run:" + name
									+ " ");
							continue;
						}
						if (tt.contains(name)) {
							System.out.println(i
									+ "\t File hdf found prelucrat:" + name
									+ " ");
							continue;
						}
						H4FileWorkerMod06 cfile = null;
						try {
							cfile = new H4FileWorkerMod06(
									file.getCanonicalPath(), statii);
						} catch (Exception e) {
							System.out.println("File open exception:"
									+ file.getCanonicalPath());
							continue;
						}
						System.out.print(i + "\t File hdf executed:" + name
								+ " :");
						boolean ok = false;
						for (String siteName : statii.keySet()) {
							System.out.print(" " + siteName);
							if (cfile.hasPosition(siteName)) {
								Mod06TO mod06to = cfile.valori.get(siteName);
								PrintWriter pw = outputFiles.get(siteName);
								pw.println(mod06to.toLongLine());
								System.out.print(" ");
								ok = true;
							} else {
								System.out.print("? ");
								File tomake = new File(folderRunPathProgress,
										name);
								tomake.createNewFile();
							}
						}
						System.out.println(";");
						cfile.close();
						for (PrintWriter pws : outputFiles.values()) {
							pws.flush();
						}
						if (ok) {
							contor++;
							if (test > 0 && contor > test) {
								break;
							}
						} else {
							historyProcesare.get(anul + satelit).add(name);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Exceptie in:" + e);
				} finally {
				}
			}
		}
		try {
			FileOutputStream fin = new FileOutputStream(bkupFisereRulate);
			ObjectOutputStream oin = new ObjectOutputStream(fin);
			oin.writeObject(historyProcesare);
			fin.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End");
	}
}
