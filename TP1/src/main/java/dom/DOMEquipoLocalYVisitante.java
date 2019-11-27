package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DOMEquipoLocalYVisitante {

    public static String dameEquipo(String localOVisitante, String pathFile) throws ParserConfigurationException, IOException, SAXException {
        //El equipo local o visitante es un atributo "club" en la etiqueta <local> o <visitante>, la cual es hijo
        //de <equipos> que a su vez es hijo de root <partido>. Tenemos que retornar el valor del atributo "club".

        ClassLoader classLoader = DOMFormacionLocalYVisitante.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(pathFile);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new InputSource(inputStream));

        NodeList listaLocalVisitante = doc.getElementsByTagName(localOVisitante);
        Node nNode = listaLocalVisitante.item(0);  //Obtenemos el 1er tag local/visitante.
        Element eElement = (Element) nNode;
        String atributoClub = eElement.getAttribute("club");

        return atributoClub;

    }
}
