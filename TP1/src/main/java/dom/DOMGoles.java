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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DOMGoles {

    public static Map<String, Integer> dameDiccionarioAutorCantidadGoles(String localOVisitante, String filename) throws ParserConfigurationException, IOException, SAXException {
        ClassLoader classLoader = DOMGoles.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new InputSource(inputStream)); //DOM

        NodeList listaGoles = doc.getElementsByTagName("goles");
        Node nNodeGoles = listaGoles.item(0);  //Porque siempre va a haber 1 solo tag "goles".

        //Para ver si hay goles evaluamos el tagVisitanteOLocal:
        Node tagVisitanteOLocal = DOMFuncionesAuxiliares.getUniqueNodeByNameGoles(nNodeGoles.getChildNodes(), localOVisitante); //Obtengo el hijo de goles (visitante o local), que tambien va a ser uno solo.

        //Si NO hay goles...
        if (tagVisitanteOLocal == null) {
            Map<String, Integer> diccionarioJugGoles = new HashMap<String, Integer>();      //Al ser un HashMap los valores se guardaran sin orden.
            return diccionarioJugGoles;                                       //Retornamos un dic vacio.
        }

        //Si hay goles...
        else {
            Collection<Node> colleccionGoles = DOMFuncionesAuxiliares.getNodesByName(tagVisitanteOLocal.getChildNodes(), "gol");  //le pedimos todos los nodos "gol" (hijos de visitante o local) y armamos una coleccion de nodos.

            //Creamos un diccionario (es un objeto map) para guardar el jugador que hizo los goles y la cantidad de goles:
            Map<String, Integer> diccionarioJugGoles = new HashMap<String, Integer>();      //Al ser un HashMap los valores se guardaran sin orden.

            //Recorremos la coleccion de nodos previamente creada (colleccionGoles) y vamos añadiendo el autor al diccionario (diccionarioJugGoles):
            for (Node gol : colleccionGoles) {
                Node autorNodo = DOMFuncionesAuxiliares.getUniqueNodeByName(gol.getChildNodes(), "autor"); //nodo autor hijo de gol.
                String autorValor = autorNodo.getFirstChild().getNodeValue();
                if (diccionarioJugGoles.containsKey(autorValor)) {
                    int cantidadGoles = diccionarioJugGoles.get(autorValor);
                    diccionarioJugGoles.put(autorValor, cantidadGoles + 1); //Con el método put añadimos al autor y sus goles / su gol al diccionario.
                }
                else {
                    diccionarioJugGoles.put(autorValor, 1);
                }
            }
            return diccionarioJugGoles;
        }
    }
}

