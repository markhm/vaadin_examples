package devops;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/** Perform and parses a clean ls of the list of jars in /Users/mark/git/kdi-tools-ui/target/unpack/BOOT-INF/lib
 * to be included in the build.xml
 *
 */
public class JarListExtractor
{
    private static Log log = LogFactory.getLog(JarListExtractor.class);

    private String filename = null;

    public JarListExtractor(String filename)
    {
        this.filename = filename;
    }

    public static void main(String[] args) throws Exception
    {
        // list all jar libraries
        // Process process = Runtime.getRuntime().exec("ls /Users/mark/git/kdi-tools-ui/target/unpack/BOOT-INF/lib");
        Process process = Runtime.getRuntime().exec("ls /Users/mark/git/vaadin_examples/target/unpack/BOOT-INF/lib");

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        String string = null;
        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        while ((string = stdInput.readLine()) != null)
        {
            if (! string.contains("vaadin_examples"))
            {
                System.out.println("<pathelement location=\"./target/unpack/BOOT-INF/lib/" + string + "\" />");
            }
        }

        // String filename = "/Users/mark/IdeaProjects/viz-tools/examples/src/main/resources/dk.mhm/jarList_kdi-tools-ui.txt";
        String filename = "/Users/mark/IdeaProjects/viz-tools/examples/src/main/resources/dk.mhm/jarList_vaadin_examples.txt";

//        FlatFileParser fileParser = new FlatFileParser(filename);
//
//        fileParser.parse();
    }

    private void parse() throws Exception
    {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename))))
        {
            String currentLine;

            while ((currentLine = br.readLine()) != null)
            {
                System.out.println("<pathelement location=\"./target/unpack/BOOT-INF/lib/"+currentLine.substring(50)+"\" />");
            }
        } catch (IOException e)
        {
            log.error(e);
        }
    }
}
