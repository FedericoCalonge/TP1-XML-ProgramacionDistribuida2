package dom;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DOMComentarioXMLTest {

    @Test
    //Ingresamos una nota al XML "quilmes_2012_con_notas" y assertamos su contenido.
    public void devolverContenidoIngresadoEnXMLQuilmes2012ConNotas() throws ParserConfigurationException, IOException, SAXException {
        DOMFuncionesAuxiliares domFuncionesAuxiliares = new DOMFuncionesAuxiliares();
        String rutaAbsoluta = new File("archivoejemplo.xml").getAbsolutePath();
        //En rutaAbsoluta obtenemos una ruta del tipo: /home/federicio/Desktop/PD2-MisReposGithub/TP1/TP1/archivoejemplo.xml
        //Con el metodo de abajo vamos a borrar el archivo (archivoejemplo.xml) y concatenarle /src/main/resources/quilmes_2012.xml para que vaya a la ubicacion correcta del xml.
        String rutaAbsolutaCorrectaXML= obtenerRutaNueva(rutaAbsoluta);
        //System.out.println(rutaAbsolutaCorrectaXML); //Como debe quedar (y quedò): /home/federicio/Desktop/PD2-MisReposGithub/TP1/TP1/src/main/resources/quilmes_2012.xml

        DOMComentarioXML domComentarioXML = new DOMComentarioXML();
        domComentarioXML.exportarXMLConNota("Nota de prueba",rutaAbsolutaCorrectaXML); //Testeando este metodo estamos testeando además: crearDOMApartirDelXML(), escribirArchivo(docOriginal,nota) y guardarArchivo(docModificado).

        //Agarramos el xml....
        ClassLoader classLoader = DOMFormacionLocalYVisitante.class.getClassLoader(); //desde una clase de /java (NO de /test)...
        InputStream inputStream = classLoader.getResourceAsStream("quilmes_2012_con_notas.xml");   //obtenemos al xml "quilmes_2012_con_notas.xml" (Ya que en /test no esta creado este xml).
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new InputSource(inputStream));

        //Agarramos las notas del xml...
        NodeList listaNotas = doc.getElementsByTagName("notas");
        Node nNodeNotas = listaNotas.item(0);  //Porque siempre va a haber 1 solo tag "notas".
        String notaXML = nNodeNotas.getFirstChild().getNodeValue(); //Obtengo el hijo (El texto de "notas").

        Assert.assertEquals("El método devolverContenidoIngresadoEnXMLQuilmes2012ConNotas no está devolviendo lo esperado",
                "Nota de prueba", notaXML);
    }

    public String obtenerRutaNueva(String rutaAbsoluta){
        Integer iterador = 0;
        String cadenaAConcatenar = "";
        String[] vectorXmlFilePath2 = rutaAbsoluta.split("/");
        //System.out.println(vectorXmlFilePath2.length);
            while((vectorXmlFilePath2.length)-1>iterador){ //-1 ya que sacamos el "archivoejemplo.xml".
            //System.out.println(vectorXmlFilePath2[iterador]);
            cadenaAConcatenar = cadenaAConcatenar.concat(vectorXmlFilePath2[iterador]+"/");
            //System.out.println(iterador);
            iterador++;
            }

        //System.out.println("Ruta del archivo XML es: " + cadenaAConcatenar); // --> /home/federicio/Desktop/PD2-MisReposGithub/TP1/TP1/src/main/resources/
        //Y ahora le agregamos el xml nuevo para que quede el nuevo path completo:
        String pathXMLNueva = cadenaAConcatenar.concat("src/main/resources/quilmes_2012.xml");
        return pathXMLNueva;
    }

}