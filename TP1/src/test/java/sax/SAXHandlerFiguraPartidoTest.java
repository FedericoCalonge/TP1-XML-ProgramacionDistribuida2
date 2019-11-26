package sax;

import dom.DOMFiguraPartido;
import dom.SinFiguraException;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SAXHandlerFiguraPartidoTest {

    //Tests para la forma 2 para sacar la figura (mediante SAX):
    //Tests para la forma 1: en DOMFiguraEquipoTest.
    @Test
    public void forma2DameFiguraCaminoFeliz() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();  //Creamos una instancia del parser SAX
        ClassLoader classLoader = SAXHandlerFiguraPartidoTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("quilmes_2012.xml");
        SAXParser saxParser = factory.newSAXParser();
        SAXHandlerFiguraPartido hSAXFigura = new SAXHandlerFiguraPartido();

        try{ saxParser.parse(inputStream,hSAXFigura); }
        catch (IOException e) { e.printStackTrace(); }

        Assert.assertEquals("El método forma2DameFiguraCaminoFeliz no está devolviendo lo esperado",
                1, hSAXFigura.getnFigura());

    }

    @Test
    public void forma2DameFiguraNoHayFigura() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();  //Creamos una instancia del parser SAX
        ClassLoader classLoader = SAXHandlerFiguraPartidoTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("quilmes_2012_sin_figura.xml");
        SAXParser saxParser = factory.newSAXParser();
        SAXHandlerFiguraPartido hSAXFigura = new SAXHandlerFiguraPartido();

        try{ saxParser.parse(inputStream,hSAXFigura); }
        catch (IOException e) { e.printStackTrace(); }

        Assert.assertEquals("El método forma2DameFiguraNoHayFigura no está devolviendo lo esperado",
                0, hSAXFigura.getnFigura());
       }

    @Test
    public void forma2DameFiguraHayDosFiguras() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();  //Creamos una instancia del parser SAX
        ClassLoader classLoader = SAXHandlerFiguraPartidoTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("quilmes_2012_2_figuras.xml");
        SAXParser saxParser = factory.newSAXParser();
        SAXHandlerFiguraPartido hSAXFigura = new SAXHandlerFiguraPartido();

        try{ saxParser.parse(inputStream,hSAXFigura); }
        catch (IOException e) { e.printStackTrace(); }

        Assert.assertEquals("El método forma2DameFiguraHayDosFiguras no está devolviendo lo esperado",
                2, hSAXFigura.getnFigura());

    }

}
