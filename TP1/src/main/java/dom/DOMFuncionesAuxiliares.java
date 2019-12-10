package dom;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class DOMFuncionesAuxiliares {

    public static Collection<Node> getNodesByName(NodeList nodeList, String name) {
        List<Node> nodeListToReturn = new ArrayList<>();
        for (int idx = 0 ; idx < nodeList.getLength(); idx++) {
            Node node = nodeList.item(idx);
            if (node.getNodeName().equals(name)) {  //Nos quedamos con los n nodos que en getNodeName sea igual (equals) al name que le pasamos por parametro.
                nodeListToReturn.add(node);
            }
        }
        return nodeListToReturn;
    }

    public static Node getUniqueNodeByName(NodeList nodeList, String name) {
        Collection<Node> nodesFound = getNodesByName(nodeList, name);
        if (!nodesFound.isEmpty() && nodesFound.size() > 1) {
            throw new IllegalStateException("Se encontró más de 1 nodo con ese nombre");
        }
        return nodesFound.iterator().next();
    }

    public static Node getUniqueNodeByNameGoles(NodeList nodeList, String name) {
        Collection<Node> nodesFound = getNodesByName(nodeList, name);
        if (!nodesFound.isEmpty() && nodesFound.size() > 1) {
            throw new IllegalStateException("Se encontró más de 1 nodo con ese nombre");
        }
        if(nodesFound.isEmpty()){
            //Creamos un nodo item vacio y lo devolvemos (en caso que no hayan goles del local o el visitante):
            Node item = null;
            return item;
        }
        return nodesFound.iterator().next();
    }

    public static Node getUniqueNodeCapitan(NodeList nodeList, String name) {
        Collection<Node> nodesFound = getNodesByName(nodeList, name);
        if (!nodesFound.isEmpty() && nodesFound.size() > 1) {
            throw new IllegalStateException("Se encontró más de 1 nodo con ese nombre");
        }
        return nodesFound.iterator().next();
    }

    public static void imprimirFormacion(String localOVisitante, String pathXML) throws ParserConfigurationException, SAXException, IOException {
        Collection<Node> playersNodes = DOMFormacionLocalYVisitante.devolverFormacion(localOVisitante,pathXML);  //guardamos en nuestra variable playersNodes de tipo Collection a la formacion local o visitante.
        for (Node player : playersNodes) {
            //Obtenemos el atributo Posicion de los jugadores locales o visitantes:
            Element element = (Element) player;
            String atributoPosicion = element.getAttribute("posicion");

            String jugador = player.getFirstChild().getNodeValue();
            String capitan = DOMCapitanEquipo.dameCapitan(localOVisitante, pathXML);

            //Requerimiento de los goles (* = 1 gol):
            Map<String, Integer> diccionarioAutorCantidadGOles = DOMGoles.dameDiccionarioAutorCantidadGoles(localOVisitante,pathXML);  //retornamos un diccionario (clave autor, valor los goles que hizo).

            //Si NO marco goles y ES capitan (agregamos solo la "[C]"):
            if (!(diccionarioAutorCantidadGOles.containsKey(jugador)) && (jugador.equals(capitan))) {  //Atencion: "Equals" SI, "==" NO.
                System.out.println(jugador + " [C]" + " - " + atributoPosicion);  //Para imprimir un * por cada gol que hizo.
            }

            //Si marco goles y NO es capitan (agregamos los * pero no la "[C]"):
            else if ((diccionarioAutorCantidadGOles.containsKey(jugador)) && (diccionarioAutorCantidadGOles.get(jugador) > 0) && !(jugador.equals(capitan))) {  //Atencion: "Equals" SI, "==" NO.
                Integer numeroGoles = diccionarioAutorCantidadGOles.get(jugador);
                String cantidadGoles = new String(new char[numeroGoles]).replace("\0", "*"); //Forma sencilla para repetir una cadena. Donde numeroGoles es el número de veces que desea repetir la cadena y * es la cadena para repetir.
                System.out.println(jugador + cantidadGoles + " - " + atributoPosicion);  //Para imprimir un * por cada gol que hizo.
            }

            //Si marco goles y ES capitan (agregamos los * y la "[C]"):
            else if ((diccionarioAutorCantidadGOles.containsKey(jugador)) && (diccionarioAutorCantidadGOles.get(jugador) > 0) && (jugador.equals(capitan))) {  //Atencion: "Equals" SI, "==" NO.
                Integer numeroGoles = diccionarioAutorCantidadGOles.get(jugador);
                String cantidadGoles = new String(new char[numeroGoles]).replace("\0", "*"); //Forma sencilla para repetir una cadena. Donde numeroGoles es el número de veces que desea repetir la cadena y * es la cadena para repetir.
                System.out.println(jugador + cantidadGoles + " - " + "[C]" + " - " + atributoPosicion);  //Para imprimir un * por cada gol que hizo.
            } else { //Si NO marco goles ni es capitan lo imprimo normal:
                System.out.println(jugador + " - " + atributoPosicion);
            }
        }
    }

    public static void imprimirDiccionario(Map<String, ArrayList<Integer>> diccionario) {
        Iterator iterador = diccionario.keySet().iterator();
        while(iterador.hasNext()){
            String key = (String) iterador.next();
            System.out.println(key + diccionario.get(key));
        }
    }

}
