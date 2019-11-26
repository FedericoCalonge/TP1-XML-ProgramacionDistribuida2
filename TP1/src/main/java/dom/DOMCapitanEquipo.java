package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DOMCapitanEquipo {

    public static String dameCapitan(String localOVisitante, String filePath) throws IOException, SAXException, ParserConfigurationException {

        ClassLoader classLoader = DOMFormacionLocalYVisitante.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new InputSource(inputStream)); //DOM

        NodeList nodeList = doc.getElementsByTagName(localOVisitante);
        final Node nodeLocalOVisitante = nodeList.item(0);
        Node capitan = DOMFuncionesAuxiliares.getUniqueNodeCapitan(nodeLocalOVisitante.getChildNodes(), "capitan");       //Con getChildNodes() podemos obtener los hijos de local o visitante (osea formacion, captain o dt). En este caso "capitan" es el nodo que queremos traer.
        //return capitan.getNodeName() me devuelve "capitan".
        //SABER: En el DOM el texto de un elemento es otro nodo. O sea, en el ejemplo este: <capitan>Mario Vera</capitan>
        //       --> En el dom, el nodo de tipo elemento "capitan" tiene un hijo de tipo texto que dice "Mario Vera".
        return capitan.getFirstChild().getNodeValue();//Obtengo el hijo de tipo de texto.
    }
}
