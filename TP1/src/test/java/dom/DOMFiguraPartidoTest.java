package dom;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.NodeList;
import java.util.ArrayList;

public class DOMFiguraPartidoTest {

    //Tests para la forma 1 para sacar la figura (mediante DOM):
    //Tests para la forma 2: en SAXHandlerFiguraPartidoTest.
    @Test
    public void forma1Dame1Figura() throws Exception {

        DOMFiguraPartido domFiguraEquipo = new DOMFiguraPartido();
        NodeList nodeListFigura = domFiguraEquipo.dameFiguraDelPartidoDOM("quilmes_2012.xml");
        String figura = nodeListFigura.item(0).getFirstChild().getNodeValue(); //Asi obtenemos la figura (suponiendo que hay solo una). Hacemos el getFirstChild ya que el texto "Martin Cauteruccio" es un nodo HIJO del tag <figura>
        Assert.assertEquals("El método forma1Dame1Figura no está devolviendo lo esperado",
                "Martín Cauteruccio", figura);
    }

    @Test
    public void forma1Dame2Figuras() throws Exception {
        DOMFiguraPartido domFiguraEquipo = new DOMFiguraPartido();
        NodeList nodeListFigura = domFiguraEquipo.dameFiguraDelPartidoDOM("quilmes_2012_2_figuras.xml");

        //String figura1 = nodeListFigura.item(0).getFirstChild().getNodeValue();
        //String figura2 = nodeListFigura.item(1).getFirstChild().getNodeValue();

        //Creamos una lista donde guardaremos las figuras:
        ArrayList<String> listaFiguras = new ArrayList<String>();

        Integer numeroFiguras = nodeListFigura.getLength();
        System.out.println(numeroFiguras);
        Integer iterador=0;
        while (numeroFiguras>0){
            listaFiguras.add(iterador, nodeListFigura.item(iterador).getFirstChild().getNodeValue()); //añadimos en la posicion iterador del vector a la figura correspondiente.
            numeroFiguras--;
            iterador++;
        }

        Assert.assertEquals("El método forma1Dame2Figuras para la figura1 no está devolviendo lo esperado",
                "Martín Cauteruccio", listaFiguras.get(0));
        Assert.assertEquals("El método forma1Dame2Figuras para la figura2 no está devolviendo lo esperado",
                "Ezequiel Rescaldani", listaFiguras.get(1));
    }

}