package validator;
import org.junit.Assert;
import org.junit.Test;

import validator.XmlSchemaValidator;


public class XMLSchemaValidatorTest {

    @Test
    public void validandoArchivoXMLCorrecto() throws Exception {
        XmlSchemaValidator xmlSchemaValidator = new XmlSchemaValidator();
        String xmlFilePath = "src/test/resources/quilmes_2012.xml";
        String schemaFilePath = "src/test/resources/quilmes_2012.xsd";

        boolean validated = xmlSchemaValidator.validateXMLSchema(schemaFilePath, xmlFilePath); //XmlSchemaValidator es la clase dentro del paquete validator (no hace falta hacer el import).

        Assert.assertTrue(validated);
    }

    @Test
    public void validandoArchivoXMLFechaIncorrecta() throws Exception {
        XmlSchemaValidator xmlSchemaValidator = new XmlSchemaValidator();
        String xmlFilePath = "src/test/resources/quilmes_2012_dia_fecha_incorrecto.xml";
        String schemaFilePath = "src/test/resources/quilmes_2012.xsd";

        boolean validated = xmlSchemaValidator.validateXMLSchema(schemaFilePath, xmlFilePath); //XmlSchemaValidator es la clase dentro del paquete validator (no hace falta hacer el import).

        Assert.assertFalse(validated);
    }

    @Test
    public void validandoArchivoXML12JugadoresLocales() throws Exception {
        XmlSchemaValidator xmlSchemaValidator = new XmlSchemaValidator();
        String xmlFilePath = "src/test/resources/quilmes_2012_12_jugadores_locales.xml";
        String schemaFilePath = "src/test/resources/quilmes_2012.xsd";

        boolean validated = xmlSchemaValidator.validateXMLSchema(schemaFilePath, xmlFilePath); //XmlSchemaValidator es la clase dentro del paquete validator (no hace falta hacer el import).

        Assert.assertFalse(validated);
    }

    @Test
    public void validando0Figuras() throws Exception {
        XmlSchemaValidator xmlSchemaValidator = new XmlSchemaValidator();
        String xmlFilePath = "src/test/resources/quilmes_2012_sin_figuras.xml";
        String schemaFilePath = "src/test/resources/quilmes_2012.xsd";

        boolean validated = xmlSchemaValidator.validateXMLSchema(schemaFilePath, xmlFilePath); //XmlSchemaValidator es la clase dentro del paquete validator (no hace falta hacer el import).

        Assert.assertFalse(validated);
    }

    @Test
    public void validando0Capitanes() throws Exception {
        XmlSchemaValidator xmlSchemaValidator = new XmlSchemaValidator();
        String xmlFilePath = "src/test/resources/quilmes_2012_sin_capitanes.xml";
        String schemaFilePath = "src/test/resources/quilmes_2012.xsd";

        boolean validated = xmlSchemaValidator.validateXMLSchema(schemaFilePath, xmlFilePath); //XmlSchemaValidator es la clase dentro del paquete validator (no hace falta hacer el import).

        Assert.assertFalse(validated);
    }

    @Test
    public void validandoMas1Capitan() throws Exception {
        XmlSchemaValidator xmlSchemaValidator = new XmlSchemaValidator();
        String xmlFilePath = "src/test/resources/quilmes_2012_mas_1_capitan.xml";
        String schemaFilePath = "src/test/resources/quilmes_2012.xsd";

        boolean validated = xmlSchemaValidator.validateXMLSchema(schemaFilePath, xmlFilePath); //XmlSchemaValidator es la clase dentro del paquete validator (no hace falta hacer el import).

        Assert.assertFalse(validated);
    }

}
