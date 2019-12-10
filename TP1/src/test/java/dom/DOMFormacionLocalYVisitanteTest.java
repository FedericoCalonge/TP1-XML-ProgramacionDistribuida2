package dom;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Collection;

public class DOMFormacionLocalYVisitanteTest {

    @Test
    public void dameNombreYPosicionUltimosJugadoresEquiposLocalYVisitante() throws Exception {
        DOMFormacionLocalYVisitante domFormacionLocalYVisitante = new DOMFormacionLocalYVisitante();
        String nameFile = "quilmes_2012.xml";

        Collection<Node> playersNodesLocal = domFormacionLocalYVisitante.devolverFormacion("local", nameFile);
        Collection<Node> playersNodesVis = domFormacionLocalYVisitante.devolverFormacion("visitante", nameFile);

        String jugadorLocal = "";
        String posicionJugadorLocal = "";

        String jugadorVis = "";
        String posicionJugadorVis = "";

        //Obtenemos el nombre y posicion del ultimo jugador Local y Vis:
        for (Node player : playersNodesLocal) {
            Element element = (Element) player;
            posicionJugadorLocal = element.getAttribute("posicion");
            jugadorLocal = player.getFirstChild().getNodeValue();
        }

        for (Node player : playersNodesVis) {
            Element element = (Element) player;
            posicionJugadorVis = element.getAttribute("posicion");
            jugadorVis = player.getFirstChild().getNodeValue();
        }

        Assert.assertEquals("El método dameNombreYPosicionUltimosJugadoresEquiposLocalYVisitante no está devolviendo lo esperado (jugador L)",
                "Alan Moreno", jugadorLocal);
        Assert.assertEquals("El método dameNombreYPosicionUltimosJugadoresEquiposLocalYVisitante no está devolviendo lo esperado (posicionJugador L)",
                "delantero", posicionJugadorLocal);
        Assert.assertEquals("El método dameNombreYPosicionUltimosJugadoresEquiposLocalYVisitante no está devolviendo lo esperado (jugador V)",
                "Martín Cauteruccio", jugadorVis);
        Assert.assertEquals("El método dameNombreYPosicionUltimosJugadoresEquiposLocalYVisitante no está devolviendo lo esperado (posicionJugador V)",
                "delantero", posicionJugadorVis);
    }

    @Test
    public void dameNombreYPosicionPrimerosJugadoresEquiposLocalYVisitante() throws Exception {
        DOMFormacionLocalYVisitante domFormacionLocalYVisitante = new DOMFormacionLocalYVisitante();
        String nameFile = "quilmes_2012.xml";

        Collection<Node> playersNodesLocal = domFormacionLocalYVisitante.devolverFormacion("local", nameFile);
        Collection<Node> playersNodesVis = domFormacionLocalYVisitante.devolverFormacion("visitante", nameFile);

        String jugadorLocal = "";
        String posicionJugadorLocal = "";

        String jugadorVis = "";
        String posicionJugadorVis = "";

        //Obtenemos el nombre y posicion del primer jugador Local y Vis:
        for (Node player : playersNodesLocal) {
            Element element = (Element) player;
            posicionJugadorLocal = element.getAttribute("posicion");
            jugadorLocal = player.getFirstChild().getNodeValue();
            break;
        }

        for (Node player : playersNodesVis) {
            Element element = (Element) player;
            posicionJugadorVis = element.getAttribute("posicion");
            jugadorVis = player.getFirstChild().getNodeValue();
            break;
        }

        Assert.assertEquals("El método dameNombreYPosicionPrimerosJugadoresEquiposLocalYVisitante no está devolviendo lo esperado (jugador L)",
                "Emanuel Guirado", jugadorLocal);
        Assert.assertEquals("El método dameNombreYPosicionPrimerosJugadoresEquiposLocalYVisitante no está devolviendo lo esperado (posicionJugador L)",
                "arquero", posicionJugadorLocal);
        Assert.assertEquals("El método dameNombreYPosicionPrimerosJugadoresEquiposLocalYVisitante no está devolviendo lo esperado (jugador V)",
                "Emanuel Tripodi", jugadorVis);
        Assert.assertEquals("El método dameNombreYPosicionPrimerosJugadoresEquiposLocalYVisitante no está devolviendo lo esperado (posicionJugador V)",
                "arquero", posicionJugadorVis);

    }
}
