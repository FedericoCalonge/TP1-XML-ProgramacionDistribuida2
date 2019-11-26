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
import java.util.*;

public class DOMResultadoPartido {

    public static Map<String, ArrayList<Integer>> dameDiccionarioAutorGol(String localOVisitante, String filename) throws ParserConfigurationException, IOException, SAXException {
        ClassLoader classLoader = DOMFormacionLocalYVisitante.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new InputSource(inputStream)); //DOM

        NodeList listaGoles = doc.getElementsByTagName("goles");
        Node nNodeGoles = listaGoles.item(0);     //Porque siempre va a haber 1 solo tag "goles".

        //Para ver si hay goles evaluamos el tagVisitanteOLocal.
        Node tagVisitanteOLocal = DOMFuncionesAuxiliares.getUniqueNodeByNameGoles(nNodeGoles.getChildNodes(), localOVisitante); //Obtengo el hijo de goles (visitante o local) que tambien va a ser uno solo.

        //Si NO hay goles...
        if (tagVisitanteOLocal==null){
            Map<String, ArrayList<Integer>> diccionarioJugMin = new HashMap<String, ArrayList<Integer>>();   //Al ser un HashMap los valores se guardaran sin orden.
            return diccionarioJugMin;                                               //Devolvemos el diccionario vacio.
        }

        //Si hay goles...
        else{
            Collection<Node> colleccionGoles = DOMFuncionesAuxiliares.getNodesByName(tagVisitanteOLocal.getChildNodes(), "gol");  //le pedimos todos los nodos "gol" (hijos de visitante o local) y armamos una coleccion de nodos.

            //Creamos un Diccionario de clave --> String Jugador y Valor--> una LISTA con los minutos que metio los goles:
            Map<String, ArrayList<Integer>> diccionarioJugMin = new HashMap<String, ArrayList<Integer>>();  //al ser un HashMap los valores se guardaran sin orden.

            //Recorremos la coleccion de nodos previamente creada (colleccionGoles) y vamos añadiendo el autor y el minuto al diccionario (diccionarioJugMin):
            for (Node gol : colleccionGoles) {
                Node minuto = DOMFuncionesAuxiliares.getUniqueNodeByName(gol.getChildNodes(), "minuto"); //nodo minuto hijo de gol.
                String minutoValor = minuto.getFirstChild().getNodeValue(); //getFirtstChild porque lo que esta dentro del nodo (18 o 23 en caso de los minutos) ES OTRO NODO.
                Integer minutoValorInt = Integer.parseInt(minutoValor);     //Casteamos el String anterior a int para añadirlo al diccionario.
                Node autorNodo = DOMFuncionesAuxiliares.getUniqueNodeByName(gol.getChildNodes(), "autor"); //nodo autor hijo de gol.
                String autorValor = autorNodo.getFirstChild().getNodeValue();

                if (diccionarioJugMin.containsKey(autorValor)) {            //Si el diccionario tiene o no al jugador significa que fue ingresado previamente... entonces agarramos su vector de minutos y le añadimos el nuevo minuto. Y a su vez este vector lo añadimos al diccionario.
                    ArrayList<Integer> listaMinutos = diccionarioJugMin.get(autorValor); //Obtenemos la lista de minutos dado el autor.
                    listaMinutos.add(minutoValorInt);                       //Añadimos otro minuto a la lista.
                    diccionarioJugMin.put(autorValor, listaMinutos);        //Añadimos esa nueva lista al diccionario como valor para la clave de ese jugador.
                }

                else { //Si el diccionario no tiene al jugador simplemente añadimos el minuto a la lista y la lista al diccionario.
                    ArrayList<Integer> listaMinutos = new ArrayList<Integer>();   //Definimos una nueva lista de Ints.
                    listaMinutos.add(minutoValorInt);                       //Añadimos el minuto a la lista.
                    diccionarioJugMin.put(autorValor, listaMinutos);        //Añadimos esa lista al diccionario como valor para la clave de ese jugador.
                }
            } //fin for colleccionGoles.

            return diccionarioJugMin;
        }
    }

    public static Integer dameCantidadGoles(Map<String, ArrayList<Integer>> diccionarioJugMin) {
        Iterator iterador = diccionarioJugMin.keySet().iterator();
        Integer contadorGoles = 0;
        Integer contadorGolesLista = 0;
        while(iterador.hasNext()){
            String key = (String) iterador.next();
            ArrayList<Integer> listaMinutos = diccionarioJugMin.get(key); //Obtenemos la lista.
            contadorGolesLista=listaMinutos.size();
            contadorGoles=contadorGoles+contadorGolesLista;
        }
        return contadorGoles;
    }

    public static Map<String, ArrayList<Integer>> ordenameListaDelDiccionarioEnBaseAMinutos(Map<String, ArrayList<Integer>> dicAutorGolLocalOVisitante) {
        //Ordenamos los valores del diccionario (listas) en base a los minutos.
        //Pasando por ej. de Cauteruccio [50,2,18] a Cauteruccio [2,18,50].
        Iterator iterador = dicAutorGolLocalOVisitante.keySet().iterator();
        while(iterador.hasNext()){
            String key = (String) iterador.next();
            ArrayList<Integer> listaMinutos = dicAutorGolLocalOVisitante.get(key); //Obtenemos la lista.
            Collections.sort(listaMinutos);
            dicAutorGolLocalOVisitante.put(key,listaMinutos);
        }
        return dicAutorGolLocalOVisitante;
    }

    public static Map<String, ArrayList<Integer>> ordenameClaveDelDiccionarioEnBaseAMinutos(Map<String, ArrayList<Integer>> dicDesordenado) {
        //Aca ordenamos las CLAVES del diccionario (nombres de los jugadores) en base al minuto donde hizo el 1er gol (1er valor de la lista).
        //Por ej. si tenemos a Pepe [5,10] y a Juan [2,20] nos pone en el Diccionario primero a Juan (por el 2) y luego a Pepe (por el 5).

        // 1. Convertimos el diccionario Map a una LISTA Map.
        List<Map.Entry<String, ArrayList<Integer>>> list = new LinkedList<Map.Entry<String, ArrayList<Integer>>>(dicDesordenado.entrySet());

        // 2. Ordenamos la lista con Collections.sort() provista por "Comparator". Luego ponemos o1 y o2 en diferentes ordenes.
        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<Integer>>>() {  //Creamos un comparador para poder comparar los valores en la lista.
            public int compare(Map.Entry<String, ArrayList<Integer>> o1,
                               Map.Entry<String, ArrayList<Integer>> o2) {
                return (o1.getValue().get(0)).compareTo(o2.getValue().get(0));            //Con getValue() obtenemos el valor del diccionario (osea la lista) y con get(0) obtemos el 1er valor de la lista (indice 0).
            }
        });

        // 3. Recorremos la sortedList y la ponemos dentro de una nueva insersion al en el Map LinkedHashMap
        Map<String, ArrayList<Integer>> dicOrdenado = new LinkedHashMap<String, ArrayList<Integer>>();
        for (Map.Entry<String, ArrayList<Integer>> entry : list) {
            dicOrdenado.put(entry.getKey(), entry.getValue());
        }

        return dicOrdenado;
    }
}
