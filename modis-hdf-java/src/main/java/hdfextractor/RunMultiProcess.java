package hdfextractor;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class RunMultiProcess {
   public static void main(String[] args) throws IOException, InterruptedException {
      ProcessBuilder prb = new ProcessBuilder("mvn.bat", "-Dappmodis.satelit=MOD", "-Dappmodis.an=2012",
            "-Phead-extract", "compile", "exec:java");
      prb.redirectOutput(Redirect.INHERIT);
      prb.redirectError(Redirect.INHERIT);
      Process proc = prb.start();
      proc.waitFor();
   }
}
