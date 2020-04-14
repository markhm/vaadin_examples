package v14example.vaadin.ui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SimpleFileNamespaceExtractor extends NamespaceExtractor // implements TreeSource
{
    private static Log log = LogFactory.getLog(SimpleFileNamespaceExtractor.class);

    protected String fileName = null;

    protected Set<String> fileResultSet = new TreeSet<String>();

    public SimpleFileNamespaceExtractor(String fileName)
    {
        this.fileName = fileName;
    }

    ////    public Tree parse()
////    {
////        // tree = SourceData.initializeTree(fileName.toString());
////
////        // build the fileResultSet in the super class
////        readFile();
////
////        // map the line entries and store in the treeNodePropertyHolder
////        mapLineEntries();
////
////        // build the TreeNodePropertyHolder in the super class and build the Tree from that
////        buildTree();
////
////        return tree;
////    }
//
////    public Tree getTree()
////    {
////        return tree;
////    }
//
    protected void mapLineEntries()
    {
        Iterator<String> iterator = fileResultSet.iterator();
        while (iterator.hasNext())
        {
            String string = iterator.next();
            parseLine(string);
        }
    }
//
    protected void buildTree()
    {
        // final Node parentNode = tree.getRoot();

        // process packageSet to Prefuse Tree
        Iterator<String> setIterator = treeNodePropertyHolder.positionSet().iterator();
        while (setIterator.hasNext())
        {
            String pakcage = (String) setIterator.next();
            //processTokenSet(parentNode, pakcage, "/");
        }
    }

    protected void parseLine(String line)
    {
        StringBuilder result = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(line, "/");

        while (tokenizer.hasMoreElements())
        {
            String element = tokenizer.nextToken();

            result.append(element);
            if (tokenizer.hasMoreElements())
            {
                result.append("/");
            }
        }

        log.info("Combined result: "+result.toString());

        treeNodePropertyHolder.putProperty(result.toString(), "full_name", result);

        // treeNodePropertyHolder.add(result.toString());
        // log.info(result.toString());
    }

    protected void readFile()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String currentLine;

            while ((currentLine = br.readLine()) != null)
            {
                fileResultSet.add(currentLine);
            }

        } catch (IOException e)
        {
            log.error(e);
        }
    }
}
