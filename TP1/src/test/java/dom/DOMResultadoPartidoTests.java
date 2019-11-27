package dom;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class DOMResultadoPartidoTests {

    @Test
    public void devolverLocal2Goles() throws Exception {
        String filePath = "quilmes_2012_muchos_goles.xml";
        Map<String, ArrayList<Integer>> dicAutorGolLocal = DOMResultadoPartido.dameDiccionarioAutorGol("local",filePath);
        int cantidadGoles =  DOMResultadoPartido.dameCantidadGoles(dicAutorGolLocal);
        Assert.assertEquals("El metodo devolverLocal2Goles no esta devolviendo el valor esperado",
                2,cantidadGoles);
    }

    @Test
    public void devolverVisitante6Goles() throws Exception {
        String filePath = "quilmes_2012_muchos_goles.xml";
        Map<String, ArrayList<Integer>> dicAutorGolLocal = DOMResultadoPartido.dameDiccionarioAutorGol("visitante", filePath);

        int cantidadGolesVis=DOMResultadoPartido.dameCantidadGoles(dicAutorGolLocal);

        Assert.assertEquals("El metodo devolverVisitante6Goles no esta devolviendo el valor esperado",
                6,cantidadGolesVis);
    }

    @Test   //Testeamos ordenameListaDelDiccionarioEnBaseAMinutos()
    public void ordenameDiccionarioVisitanteEnBaseALosMinutosDeLosGoles() throws IOException, SAXException, ParserConfigurationException {
        String filePath = "quilmes_2012_muchos_goles.xml";
        Map<String, ArrayList<Integer>> dicAutorGolVisitante = DOMResultadoPartido.dameDiccionarioAutorGol("visitante", filePath);
        //Tenemos este diccionario:
        //Ezequiel Rescaldani[8, 78]
        //Martín Cauteruccio[5,18,10]
        //Germán Mandarino[55]

        int segundoGolCaute = devolver2doMinutoGolCauteruccio(dicAutorGolVisitante);

        Assert.assertEquals("El 1er assert del metodo ordenameDiccionarioVisitanteEnBaseALosMinutosDeLosGoles no esta devolviendo el valor esperado",
                18,segundoGolCaute);

        //2do ordenamos la lista de cada clave y nos debe quedar asi:
        //Ezequiel Rescaldani[8, 78]
        //Martín Cauteruccio[5, 10, 18]
        //Germán Mandarino[55]

        Map<String, ArrayList<Integer>> dicAutorGolVisitanteOrdenado = DOMResultadoPartido.ordenameListaDelDiccionarioEnBaseAMinutos(dicAutorGolVisitante);
        int segundoGolCauteOrdenado = devolver2doMinutoGolCauteruccio(dicAutorGolVisitanteOrdenado);

        Assert.assertEquals("El 2do assert del metodo ordenameDiccionarioVisitanteEnBaseALosMinutosDeLosGoles no esta devolviendo el valor esperado",
                10, segundoGolCauteOrdenado);
    }

    @Test   //Testeamos ordenameClaveDelDiccionarioEnBaseAMinutos()
    public void devolverUltimoValorMasAltoDelDiccionario() throws Exception {
        Map<String, ArrayList<Integer>> dicDesordenado = new HashMap<String, ArrayList<Integer>>();

        ArrayList<Integer> ejemploLista = new ArrayList<Integer>();
        ejemploLista.add(90);
        ejemploLista.add(100);

        ArrayList<Integer> ejemploLista2 = new ArrayList<Integer>();
        ejemploLista2.add(65);
        ejemploLista2.add(70);

        ArrayList<Integer> ejemploLista3 = new ArrayList<Integer>();
        ejemploLista3.add(100);
        ejemploLista3.add(20);

        ArrayList<Integer> ejemploLista4 = new ArrayList<Integer>();
        ejemploLista4.add(80);
        ejemploLista4.add(100);

        ArrayList<Integer> ejemploLista5 = new ArrayList<Integer>();
        ejemploLista5.add(7);
        ejemploLista5.add(100);

        ArrayList<Integer> ejemploLista6 = new ArrayList<Integer>();
        ejemploLista6.add(75);
        ejemploLista6.add(100);

        dicDesordenado.put("Uno", ejemploLista);
        dicDesordenado.put("Dos", ejemploLista2);
        dicDesordenado.put("Tres", ejemploLista4);
        dicDesordenado.put("Cuatro", ejemploLista3);
        dicDesordenado.put("Cinco", ejemploLista5);
        dicDesordenado.put("Seis", ejemploLista6);

        System.out.println("Diccionario Desordenado:");
        DOMFuncionesAuxiliares.imprimirDiccionario(dicDesordenado);

        System.out.println("Diccionario Ordenado por el 1er valor de las listas");
        Map<String, ArrayList<Integer>> dicOrdenado = DOMResultadoPartido.ordenameClaveDelDiccionarioEnBaseAMinutos(dicDesordenado);
        DOMFuncionesAuxiliares.imprimirDiccionario(dicOrdenado);

        String ultimaKey = this.devolverKeyUltimoValorDiccionario(dicOrdenado); //me debe devolver "cuatro".
        int valorMasGrandeDelDiccionario = dicOrdenado.get(ultimaKey).get(0);  //Hay que pasarlo de Integer a Int porque sino en el assert me tira "llamada a metodo ambigua"
        Assert.assertEquals("El metodo devolverUltimoValorMasAltoDelDiccionario no esta devolviendo el valor esperado",
                100,valorMasGrandeDelDiccionario);
    }

    //***************   Funciones auxiliares para los tests:   *****************

    private Integer devolver2doMinutoGolCauteruccio(Map<String, ArrayList<Integer>> dicAutorGolVisitante) {
        ArrayList<Integer> listaMinutos = dicAutorGolVisitante.get("Martín Cauteruccio"); //Obtenemos la lista.
        return listaMinutos.get(1); //Obtenemos el 2do valor de la lista (indice 1)
    }

    private String devolverKeyUltimoValorDiccionario (Map<String, ArrayList<Integer>> diccionario) {
        Iterator iterador = diccionario.keySet().iterator();
        String keyUltimoValor = "";
        while(iterador.hasNext()){
            String key = (String) iterador.next();
            keyUltimoValor = key;
        }
        return keyUltimoValor;
    }

}
