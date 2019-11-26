package dom;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Collection;

public class DOMFormacionLocalYVisitanteTest {

    @Test
    public void dameNombreUltimoJugadorEquipoLocal() throws Exception {
        DOMFormacionLocalYVisitante domFormacionLocalYVisitante = new DOMFormacionLocalYVisitante();
        String nameFile = "quilmes_2012.xml";
        Collection<Node> playersNodes = domFormacionLocalYVisitante.devolverFormacion("local", nameFile);
        String jugador = "";
        for (Node player : playersNodes) {
            //Obtenemos el nombre del jugador:
            jugador = player.getFirstChild().getNodeValue();
        }
        Assert.assertEquals("El método dameNombreUltimoJugadorEquipoLocal no está devolviendo lo esperado",
                "Alan Moreno", jugador);
    }

    @Test
    public void damePosicionUltimoJugadorEquipoVisitante() throws Exception {
        DOMFormacionLocalYVisitante domFormacionLocalYVisitante = new DOMFormacionLocalYVisitante();
        String nameFile = "quilmes_2012.xml";
        Collection<Node> playersNodes = domFormacionLocalYVisitante.devolverFormacion("visitante", nameFile);
        String posicionJugador = "";
        for (Node player : playersNodes) {
            Element element = (Element) player;
            posicionJugador = element.getAttribute("posicion");
        }
        Assert.assertEquals("El método damePosicionUltimoJugadorEquipoVisitante no está devolviendo lo esperado",
                "delantero", posicionJugador);
    }
}
