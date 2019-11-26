package validator;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class XmlSchemaValidator {

    //Validamos un documento XML con un Schema XML (XSD).
    //Retorna el Document (DOM) del archivo XML.
    // throws SAXException --> En caso de detectar un error de validación.
    // throws IOException --> En caso de error al obtener la información desde los archivos  (no debería producirse).

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        boolean validated=true;
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println(" Error/es encontrado/s: "+ "\n" + e.getMessage());
            //e.printStackTrace();
            validated=false;
          }
        return validated;
    }
}