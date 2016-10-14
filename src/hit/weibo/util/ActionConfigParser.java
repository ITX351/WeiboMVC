package hit.weibo.util;

import hit.weibo.action.Action;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ActionConfigParser {
    static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    static HashMap<String, String> sActionNameToClassMap = new HashMap<>();

    static {
        initMap();
    }

    static Document parse(String filePath) {
        Document document = null;
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            document = builder.parse(new File(filePath));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    static void initMap() {
        ActionConfigParser actionConfigParser = new ActionConfigParser();
        Document doc = actionConfigParser.parse("resources\\action-config.xml");
        Element documentElement = doc.getDocumentElement();
        NodeList actions = documentElement.getElementsByTagName("action");

        for (int i = 0; i < actions.getLength(); i++) {
            Element item = (Element) actions.item(i);
            String name = item.getElementsByTagName("name").item(0).getTextContent();
            String className = item.getElementsByTagName("class").item(0).getTextContent();

            sActionNameToClassMap.put(name, className);
        }
    }

    public static Action newAction(String actionName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String actionClassName = sActionNameToClassMap.get(actionName);
        if (actionClassName == null) {
            throw new NullPointerException("action not found");
        }
        Class<?> actionClass = Class.forName(actionClassName);
        Action newAction = (Action) actionClass.newInstance();
        return newAction;

    }
}
