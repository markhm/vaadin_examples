package v14example.vaadin.ui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/** Tree node property holder
 * Provides a backing data structure for counting node and
 * leaf occurrences towards building a Prefuse tree.
 *
 */
public class TreeNodePropertyHolder
{
    /** Logger*/
    private static Log log = LogFactory.getLog(TreeNodePropertyHolder.class);

    /** Property for the */
    public static final String COUNT = "count";
    public static final String MODIFICATION_DATE = "modification_date";

    /** Map for holding specific properties per position node.
     *
     * */
    private Map<String, Map<String, Object>> treePropertyHolder = null;

    public TreeNodePropertyHolder()
    {
        treePropertyHolder = new TreeMap<String, Map<String, Object>>();
    }

    /**Returns all positions that have been added.
     * @return
     */
    public Set<String> positionSet()
    {
        return treePropertyHolder.keySet();
    }

    public void putProperty(String position, String identifier, Object object)
    {
        Map<String, Object> propertyHolder = getPropertyHolder(position);
        propertyHolder.put(identifier, object);
    }

    public Object getProperty(String position, String identifier)
    {
        Map<String, Object> propertyHolder = getPropertyHolder(position);
        if (propertyHolder == null)
        {
            throw new RuntimeException("No property set for position "+position);
        }
        else
        {
            return propertyHolder.get(identifier);
        }
    }

//    /**Returns the LocalDateTime property at the key location.
//     *
//     * @param position
//     * @return
//     * @deprecated to be replaced
//     */
//    public LocalDateTime getDateProperty(String position)
//    {
//        // log.info("Trying to find '"+key+"' and property is: "+datePropertyHolder.getProperty(key));
//        Map<String, Object> propertyHolder = treePropertyHolder.get(position);
//
//        if (propertyHolder != null)
//        {
//            return (LocalDateTime) propertyHolder.get(MODIFICATION_DATE);
//        }
//        else
//        {
//            log.warn("Could not find MODIFICATION_DATE for "+position);
//            return null;
//        }
//
//        // return datePropertyHolder.get(position);
//    }

//    public int getNumberOf(String position)
//    {
//        Map<String, Object> propertyHolder = treePropertyHolder.get(position);
//        return (int) propertyHolder.get(COUNT);
//        // return .get(position);
//    }

    public Map<String, Object> getPropertyHolder(String position)
    {
        Map<String, Object> propertyHolder = treePropertyHolder.get(position);
        if (propertyHolder == null)
        {
            propertyHolder = new TreeMap<String, Object>();
            treePropertyHolder.put(position, propertyHolder);
        }

        return propertyHolder;
    }


    /**Adds a tree leaf with an associated date
     *
     * @param position
     * @param modificationDate
     * @deprecated to be replaced
     */
    public void putProperty(String position, LocalDateTime modificationDate)
    {
        // log.info("Adding '"+leafId+"' with datePropertyHolder: "+lastTouched);
        // datePropertyHolder.put(leafId, modificationDate);

        Map<String, Object> propertyHolder = getPropertyHolder(position);
        propertyHolder.put(MODIFICATION_DATE, modificationDate);
    }

    /**Adds a tree packageName, separated by '/'
     *
     * @param position
     * @deprecated to be replaced. By what...?
     */
    public void add(String position)
    {
        throw new RuntimeException("This should no longer be called");

        // Map<String, Object> propertyHolder = getPropertyHolder(position);
        // Integer currentAmount = (Integer) propertyHolder.get(COUNT);

//        Integer currentAmount = treePositionMap.get(position);
//
//        if (currentAmount == null)
//        {
//            // propertyHolder.put(COUNT, 1);
//            treePositionMap.put(position, 1);
//        }
//        else
//        {
//            currentAmount++;
//            // propertyHolder.put(COUNT, currentAmount);
//            treePositionMap.put(position, currentAmount);
//        }
    }

    /**
     * @deprecated imp
     */
    public void printMap()
    {
        log.info("******************");
        log.info("");
        int counter = 0;

        Set<String> keyset = treePropertyHolder.keySet();
        Iterator<String> setIterator = keyset.iterator();
        while (setIterator.hasNext())
        {
            String pakcage = setIterator.next();

            Integer value = (Integer) treePropertyHolder.get(pakcage).get(COUNT);;
            counter = counter + value;
            log.info("Found "+value+" classes in "+pakcage);
        }

        log.info("");
        log.info("Found a total of "+counter+" classes");
        log.info("******************");
    }

    public void printStatistics()
    {
        // log.info("******************************");
        log.info("TreeNodePropertyHolder contains "+ treePropertyHolder.keySet().size()+" document entries.");
        // log.info("******************************");
    }

    public void print()
    {
        log.info("------------------------------------");
        log.info("**** printPropertyPositionSet()");
        printPropertyPositionSet();
    }


    /**Print the position set, based on the properties.
     *
     */
    public void printPropertyPositionSet()
    {
        Iterator<String> setIterator = treePropertyHolder.keySet().iterator();
        while (setIterator.hasNext())
        {
            String pakcage = setIterator.next();
            log.info("treePropertyHolder position: "+pakcage);
        }
        log.info("Found "+ treePropertyHolder.keySet().size() +" packages");
        log.info("------------------------------------");
        log.info("");
        log.info("");
    }

    public static final String LAST_MODIFIER = "last_modifier";

    public void printLastModifierEntries()
    {
        int counter = 0;

        Iterator<String> setIterator = treePropertyHolder.keySet().iterator();
        while (setIterator.hasNext())
        {
            String pakcage = setIterator.next();
            Map<String, Object> propertyHolder = treePropertyHolder.get(pakcage);
            Set<String> propertySet = propertyHolder.keySet();
            Iterator<String> propertyIterator = propertySet.iterator();
            while (propertyIterator.hasNext())
            {
                String property = propertyIterator.next();
                if (property.equals(LAST_MODIFIER))
                {
                    log.info("Found entry for package:property " + pakcage + " : " + property);
                    counter++;
                }
            }
        }

        log.info("");
        log.info("*******************");
        log.info("Counted a total of "+counter+" entries");
    }
}
