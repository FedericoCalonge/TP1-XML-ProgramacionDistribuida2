package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DOMComentarioXML {

    public static void exportarXMLConNota(String nota, String pathXMLOriginal) {
        try {
            Document docOriginal = crearDOMApartirDelXML(pathXMLOriginal);  //Obtenemos el DOM original (del XML original).
            Document docModificado = escribirArchivo(docOriginal,nota);     //Modificamos el DOM anterior.
            guardarArchivo(docModificado,pathXMLOriginal);                  //Con el DOM anterior modificado ahora creamos un nuevo XML.
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMComentarioXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException | IOException | SAXException ex) {
            Logger.getLogger(DOMComentarioXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Document crearDOMApartirDelXML(String pathXMLOriginal) throws ParserConfigurationException, IOException, SAXException {
        File archivoOriginal = new File(pathXMLOriginal);
        InputStream inputStream = new FileInputStream(archivoOriginal);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new InputSource(inputStream)); //Creamos el DOM a partir del XML fuente.

        return doc;
    }

    //Ahora modificamos el dom agregando el comentario como hijo de <partido>:
    public static Document escribirArchivo(Document doc, String comentario){
        Element notas = doc.createElement("notas");  // Creamos el elemento principal notas.
        notas.setTextContent(comentario);               // Establecemos el contenido de notas.
        Node nodoPartido = doc.getDocumentElement();    // Obtenemos el nodo XML principal (<partido>).
        nodoPartido.appendChild(notas);                 // Hacemos al elemento notas que sea hijo directo del nodo XML principal (<partido>).
        doc.normalizeDocument();
        return doc;                                     // Devolvemos el mismo doc pero modificado.
    }

    //Envia el documento XML a un nuevo archivo XML:
    public static void guardarArchivo(Document docModificado, String pathXMLOriginal) throws TransformerException {
        // Creamos el objeto transformador:
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        Integer iterador = 0;
        String cadenaAConcatenar = "";
        String[] vectorXmlFilePath2 = pathXMLOriginal.split("/");
        while((vectorXmlFilePath2.length)-1>iterador){ //-1 ya que sacamos el "quilmes_2012.xml".
            cadenaAConcatenar = cadenaAConcatenar.concat(vectorXmlFilePath2[iterador]+"/");
            iterador++;
        }
        String pathXMLConNotas = cadenaAConcatenar.concat("quilmes_2012_con_notas.xml"); //Y ahora le agregamos el xml nuevo para que quede el nuevo path completo:

        File archivoNuevoXML = new File(pathXMLConNotas);           // Archivo donde almacenaremos el nuevo XML.
        DOMSource source = new DOMSource(docModificado);
        StreamResult result = new StreamResult(archivoNuevoXML);    // Resultado, el cual almacena en el archivo indicado
        transformer.transform(source, result);                      // Transformamos de la fuente DOM a el resultado, lo que almacena tod0 en el archivo
    }
}
