package dom;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DOMFiguraPartido {

    public static NodeList dameFiguraDelPartidoDOM(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        ClassLoader classLoader = DOMFiguraPartido.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        // Procesamos el archivo XML:
        Document doc = builder.parse(new InputSource(inputStream));
        NodeList nodeListFigura = doc.getElementsByTagName("figura");

        if (nodeListFigura.getLength() == 0) {
            throw new SinFiguraException("No hay figuras en el xml " + filename);
        }

        return nodeListFigura;
    }
}
