package v14example.vaadin.ui.controller;

import elemental.json.Json;
import elemental.json.JsonException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class StratificationUtil
{
    private static Log log = LogFactory.getLog(StratificationUtil.class);

    private TreeNodePropertyHolder treeNodePropertyHolder = null;

    private List<String> permalinks;

    private JSONObject result = null;

    public StratificationUtil()
    {
        treeNodePropertyHolder = new TreeNodePropertyHolder();

        result = new JSONObject();
    }

    protected void mapLineEntries()
    {
        Iterator<String> iterator = permalinks.iterator();
        while (iterator.hasNext())
        {
            String string = iterator.next();
            parseLine(PermanentLinkExtractor.parseSimple(string));
        }
    }
    public JSONObject stratify(List<String> permalinks) throws JSONException
    {
        result.put("name", "root");

        this.permalinks = permalinks;

        // map the line entries and store in the treeNodePropertyHolder
        mapLineEntries();

        // build the actual tree from the treeNodePropertyHolder
        buildTree();

        return result;
    }

    protected void parseLine(String line)
    {
        // log.info("Parsing line: "+line);
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

        // log.info("Combined result: "+result.toString());

        treeNodePropertyHolder.putProperty(result.toString(), "full_name", result);
    }

    protected void buildTree()
    {
        try
        {
            Iterator<String> setIterator = treeNodePropertyHolder.positionSet().iterator();
            while (setIterator.hasNext())
            {
                String pakcage = (String) setIterator.next();
                processTokenSet(result, pakcage, "/");
            }
        }
        catch (JSONException exception)
        {
            log.error(exception);
        }

    }

    public void processTokenSet(JSONObject parentObject, String pakcage, String separator) throws JSONException
    {
        // if (log.isInfoEnabled()) log.info("Processing line item '" + pakcage + "'");

        StringTokenizer tokenizer = new StringTokenizer(pakcage, separator);
        List<String> tokens = new ArrayList<>();
        while(tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            tokens.add(token);
        }

        processTokenList(parentObject, tokens);
    }

    private JSONArray getChildren(final JSONObject parentNode) throws JSONException
    {
        JSONArray children = null;
        try
        {
            children = (JSONArray) parentNode.get("children");
        }
        catch (JSONException je)
        {
            children = new JSONArray();
            parentNode.put("children", children);
        }

        return children;
    }

    protected void processTokenList(final JSONObject parentNode, final List<String> tokenList) throws JSONException
    {
        Set<String> tokens = treeNodePropertyHolder.positionSet();
        // log.info("Processing tokens"+"("+tokenList.size()+") with first token: "+tokenList.get(0));

        JSONObject currentNode = null;

        for (String currentToken : tokenList)
        {
            JSONArray children = getChildren(parentNode);

            for (int i = 0; i < children.length(); i++)
            {
                JSONObject potentialNode = (JSONObject) children.get(i);
                String nodeName = (String) potentialNode.get("name");
                if (nodeName.startsWith(currentToken))
                {
                    // log.info("Found node "+currentToken+" for parent "+parentNode.getProperty("name"));
                    currentNode = potentialNode;
                    // int currentValue = currentNode.getInt(SourceData.NUMBER_OF_CLASSES);
                    // currentValue++;
                    // currentNode.setInt(SourceData.NUMBER_OF_CLASSES, currentValue);
                    if (tokenList.size() > 1)
                    {
                        tokenList.remove(0);
                    }
                    processTokenList(currentNode, tokenList);
                }
            }

            if (currentNode == null)
            {
                // logAll(parentNode, currentNode, currentToken);

                currentNode = new JSONObject();
                currentNode.put("name", currentToken);

                children.put(currentNode);
                // log.info("Creating new node "+currentToken+" with parent "+parentNode.get("name"));

                if (tokenList.size() > 1)
                {
                    tokenList.remove(0);

                    processTokenList(currentNode, tokenList);
                }
                else return;
            }
        }
    }

    private void logAll(JSONObject parentNode, JSONObject currentNode, String token)
    {
        log.info("");
        log.info("token: "+token);

        if (parentNode != null)
        {
            log.info("parentNode: "+parentNode);
        }
        if (currentNode != null)
        {
            log.info(" currentNode: "+currentNode);
        }
    }
}
