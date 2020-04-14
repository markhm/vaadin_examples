package v14example.vaadin.ui.controller;

public abstract class NamespaceExtractor
{
//    private static Log log = LogFactory.getLog(NamespaceExtractor.class);
//
    protected TreeNodePropertyHolder treeNodePropertyHolder = null;
//
//    // protected Tree tree = null;
//
    public NamespaceExtractor()
    {
        treeNodePropertyHolder = new TreeNodePropertyHolder();
    }

    public TreeNodePropertyHolder getTreeNodePropertyHolder()
    {
        return treeNodePropertyHolder;
    }

    //
//    public void processTokenSet(Node parentNode, String pakcage, String separator)
//    {
//        // if (log.isInfoEnabled()) log.info("Processing line item '" + pakcage + "'");
//
//        StringTokenizer tokenizer = new StringTokenizer(pakcage, separator);
//        List<String> tokens = new ArrayList<String>();
//        while(tokenizer.hasMoreElements())
//        {
//            String token = tokenizer.nextToken();
//            tokens.add(token);
//        }
//
//        processTokenList(parentNode, tokens);
//    }
//
//    public Tree getTree()
//    {
//        return tree;
//    }
//
//    /** Process a List of the tokens
//     *
//     * @param parentNode
//     * @param tokenList
//     */
//    protected void processTokenList(final Node parentNode, final List<String> tokenList)
//    {
//        // Set<String> tokens = treeNodePropertyHolder.positionSet();
//        // log.info("Processing tokens"+"("+tokenList.size()+") with first token: "+tokenList.getProperty(0));
//
//        Node currentNode = null;
//        Iterator iterator = tokenList.iterator();
//        while (iterator.hasNext())
//        {
//            String currentToken = (String) iterator.next();
//            // log.info("Current token is: "+currentToken);
//
//            Iterator children = parentNode.children();
//            while (children.hasNext())
//            {
//                Node potentialNode = (Node) children.next();
//                String nodeName = (String) potentialNode.get(SourceData.NAME);
//                if (nodeName.startsWith(currentToken))
//                {
//                    // log.info("Found node "+currentToken+" for parent "+parentNode.getProperty("name"));
//                    currentNode = potentialNode;
//                    // int currentValue = currentNode.getInt(SourceData.NUMBER_OF_CLASSES);
//                    // currentValue++;
//                    // currentNode.setInt(SourceData.NUMBER_OF_CLASSES, currentValue);
//                    if (tokenList.size() > 1)
//                    {
//                        tokenList.remove(0);
//                    }
//                    processTokenList(currentNode, tokenList);
//                }
//            }
//            if (currentNode == null)
//            {
//                // log.info("Creating new node "+currentToken+". with parent "+parentNode.getProperty("name"));
////                currentNode = SourceData.createNode(tree, parentNode);
////                currentNode.set(SourceData.NAME, (currentToken)); // currentNode.set(SourceData.COLUMN_NAME, (currentToken + "."));
//                if (tokenList.size() > 1)
//                {
//                    // log.info("About to remove token: "+tokens.getProperty(0));
//                    tokenList.remove(0);
//
//                    processTokenList(currentNode, tokenList);
//                }
//                else return;
//            }
//        }
//    }
}
