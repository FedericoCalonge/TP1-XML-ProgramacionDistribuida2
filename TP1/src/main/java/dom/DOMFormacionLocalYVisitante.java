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
import java.util.Collection;

public class DOMFormacionLocalYVisitante {

    public static Collection<Node> devolverFormacion(String localOVisitante, String filename) throws IOException, SAXException, ParserConfigurationException {
        //Abrimos y cargamos el XML en un input stream:
        ClassLoader classLoader = DOMFormacionLocalYVisitante.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);

        //Creamos el parser y parseamos el input stream:
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new InputSource(inputStream)); //Asi creamos tod0 el ARBOL DOM. Y despues de eso con doc.algo podemos agarrar cosas.

        //Obtenemos una coleccion playersNodes con los jugadores locales o visitantes:
        NodeList nodeList = doc.getElementsByTagName(localOVisitante);
        final Node nodeLocalOVisitante = nodeList.item(0);  //(0) --> Sacamos el 1er item!. De la lista sacamos el item 0 (ya que sabemos que hay 1 solo visitante/local).
        Node formacion = DOMFuncionesAuxiliares.getUniqueNodeByName(nodeLocalOVisitante.getChildNodes(), "formacion"); //Con getChildNodes() podemos obtener los hijos de visitante (osea formacion, captain o dt). En este caso "formacion" es el nodo que queremos traer.
        //Creamos una COLECCION ya que es una interfaz ITERABLE con un for:
        Collection<Node> playersNodes = DOMFuncionesAuxiliares.getNodesByName(formacion.getChildNodes(), "jugador");   //Le pedimos todos los nodos "jugador" (hijos de formacion)
        return playersNodes;
    }

}
