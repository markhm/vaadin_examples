package v14example.vaadin.ui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PermanentLinkExtractor extends SimpleFileNamespaceExtractor
{
    private static Log log = LogFactory.getLog(PermanentLinkExtractor.class);

    public PermanentLinkExtractor(String fileName)
    {
        super(fileName);
    }

    public void parse()
    {
        // tree = SourceData.initializeTree(getAbsRoot());

        // build the fileResultSet in the super class
        readFile();

        // printResultSet();

        // System.exit(-1);
        // map the line entries and store in the treeNodePropertyHolder
        mapLineEntries();

        printTreePropertyHolder();

        // build the TreeNodePropertyHolder in the super class and build the Tree from that
        buildTree();

        // return tree;
    }

    private void printTreePropertyHolder()
    {
        for (String element : treeNodePropertyHolder.positionSet())
        {
            log.info(element);
        }
    }

    private void printResultSet()
    {
        for (String element : fileResultSet)
        {
            log.info(element);
        }
    }

    public static void main(String[] args)
    {
        // new PermanentLinkExtractor("").unitTest();
        // new PermanentLinkExtractor("").urlEncode();
    }

    private void urlEncode()
    {
        String testUrlBase = "https://docs.kombit.dk/kdi/integration/SF1234/v1.0/";
        String testFilename = "SF1234 - Integrationsbeskrivelse til Sags- og Dokumentindeks v2.8.pdf";

        log.info("String = " + testUrlBase + testFilename);
        String encodedString = "encoding failed";
        try
        {
            encodedString = URLEncoder.encode(testFilename, "UTF-8");
        }
        catch(Exception e)
        {
            log.error(e);
        }
        log.info("Encoded string: " + testUrlBase + encodedString);
    }


    public static String parseSimple(String line)
    {
        String combinedResult = "error";

        // skipping comment lines
        if (line.startsWith("#") || line.length() < 5) return combinedResult;

        // log.info("Parsing line (simple):" + line);

        // Help here: https://regexr.com/

        String regularExpression = "https?://(([a-z]*[.]?[-]?)*)(([a-zA-Z0-9.-[/]_[+]])*)";

        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(line);

        String firstPart = null;
        String secondPart = null;

        while (matcher.find())
        {
            // log.info(matcher.group(0));

            firstPart = matcher.group(1);
            // log.info(firstPart);

            secondPart = matcher.group(3);
            // log.info(secondPart);
        }

        if (firstPart != null && secondPart != null)
        {
            String firstResult = processFirstPart(firstPart);
            // log.info("First part: " + firstPart);
            String secondResult = processSecondPart(secondPart);
            // log.info("Second part: " + secondResult);

            combinedResult = firstResult + secondResult;

            // log.info("Parsed result: " + combinedResult);
            // treeNodePropertyHolder.putProperty(combinedResult, "full_name", combinedResult);
        }
        else
        {
            log.error("Regular expression could not parse part of " + line);
        }

        return combinedResult;

        // treeNodePropertyHolder.add(combinedResult);
    }

    public void parseComplex(String line)
    {
        if (line.startsWith("#") || line.length() < 5) return;

        log.info("Parsing line (complex):" + line);

        String regularExpression = "((https?|ftp|mailto|gopher|telnet|file):((\\/\\/)|(\\\\)|)+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(line);

        String firstPart = null;
        String secondPart = null;

        while (matcher.find())
        {
            // log.info(matcher.group(0));

            firstPart = matcher.group(1);
            // log.info(firstPart);

            secondPart = matcher.group(3);
            // log.info(secondPart);
        }

        if (firstPart != null && secondPart != null)
        {
            String firstResult = processFirstPart(firstPart);
            log.info("First part: " + firstPart);
            String secondResult = processSecondPart(secondPart);
            log.info("Second part: " + secondResult);

            String combinedResult = firstResult + secondResult;

            log.info("Parsed result: " + combinedResult);
            treeNodePropertyHolder.putProperty(combinedResult, "full_name", combinedResult);

        }
        else
        {
            log.error("Regular expression could not parse part of " + line);
        }

    }

    private static String processFirstPart(String firstPart)
    {
        return firstPart + "/";
//        Stack<String> stack = new Stack<String>();
//
//        StringTokenizer tokenizer = new StringTokenizer(firstPart, ".");
//        while (tokenizer.hasMoreElements())
//        {
//            String element = tokenizer.nextToken();
//            stack.add(element);
//        }
//
//        StringBuilder result = new StringBuilder();
//        while (!stack.empty())
//        {
//            String element = stack.pop();
//            result.append(element);
//            result.append("/");
//        }
//        return result.toString();
    }

    private static String processSecondPart(String secondPart)
    {
        StringBuilder result = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(secondPart, "/");
        while (tokenizer.hasMoreElements())
        {
            String element = tokenizer.nextToken();
            result.append(element);
            if (tokenizer.hasMoreElements())
            {
                result.append("/");
            }
        }

        return result.toString();
    }
}
