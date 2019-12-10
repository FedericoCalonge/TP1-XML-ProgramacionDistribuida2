package dom;

import org.junit.Assert;
import org.junit.Test;
import java.util.Map;

public class DOMGolesTest {

    @Test
    public void golesVisitanteYLocales() throws Exception {
        //Escenario: Quilmes 6 (Cauteruccio x 3, Rescaldani x 2, Mandarino) - Brown 2 (Aciar y Vera)
        DOMGoles domGoles = new DOMGoles();
        String filename = "quilmes_2012_muchos_goles.xml";

        Map<String, Integer> diccionarioGolesVisitante = domGoles.dameDiccionarioAutorCantidadGoles("visitante", filename);
        Map<String, Integer> diccionarioGolesLocal = domGoles.dameDiccionarioAutorCantidadGoles("local", filename);

        int cantidadGolesDicMandarino = diccionarioGolesVisitante.get("Germán Mandarino");
        int cantidadGolesDicRescaldani = diccionarioGolesVisitante.get("Ezequiel Rescaldani");
        int cantidadGolesDicCauteruccio = diccionarioGolesVisitante.get("Martín Cauteruccio");

        int cantidadGolesDicAciar= diccionarioGolesLocal.get("Walter Aciar");
        int cantidadGolesDicVera= diccionarioGolesLocal.get("Mario Vera");

        int autoresDistintosDeGolesVisitante = 3;
        int autoresDistintosDeGolesLocal= 2;

        //Cantidad de goles:
        Assert.assertEquals("Hay 3 autores distintos de goles para el visitante y eso no se refleja en el diccionario", autoresDistintosDeGolesVisitante,
                diccionarioGolesVisitante.size());
        Assert.assertEquals("Hay 2 autores distintos de goles para el local y eso no se refleja en el diccionario", autoresDistintosDeGolesLocal,
                diccionarioGolesLocal.size());

        //Goles visitantes:
        Assert.assertEquals("La cantidad de goles de Mandarino es incorrecta",
                cantidadGolesDicMandarino, 1);
        Assert.assertEquals("La cantidad de goles de Rescaldani es incorrecta",
                cantidadGolesDicRescaldani, 2);
        Assert.assertEquals("La cantidad de goles de Cauteruccio es incorrecta",
                cantidadGolesDicCauteruccio, 3);

        //Goles locales:
        Assert.assertEquals("La cantidad de goles de Aciar es incorrecta",
                cantidadGolesDicAciar, 1);
        Assert.assertEquals("La cantidad de goles de Vera es incorrecta",
                cantidadGolesDicVera, 1);
    }

    @Test
    public void devolver1GolVisitate0Local() throws Exception {
        //Escenario: Quilmes 1 (Rescaldani) - Brown 0.
        DOMGoles domGoles = new DOMGoles();
        String filename = "quilmes_2012_1_gol_vis.xml";

        Map<String, Integer> diccionarioGolesVisitante = domGoles.dameDiccionarioAutorCantidadGoles("visitante", filename);
        Map<String, Integer> diccionarioGolesLocal = domGoles.dameDiccionarioAutorCantidadGoles("local", filename);

        int cantidadGolesDicRescaldani = diccionarioGolesVisitante.get("Ezequiel Rescaldani");

        //Cantidad de goles:
        Assert.assertEquals("La cantidad de goles para el visitante no es la correcta",
                diccionarioGolesVisitante.size(), 1);
        Assert.assertEquals("La cantidad de goles para el local no es la correcta",
                diccionarioGolesLocal.size(), 0);

        //Gol visitante:
        Assert.assertEquals("La cantidad de goles de Rescaldani es incorrecta",
                cantidadGolesDicRescaldani, 1);
    }

    @Test
    public void devolver1GolLocal0Visitante() throws Exception {
        //Escenario: Quilmes 0 - Brown 1 (Aciar).
        DOMGoles domGoles = new DOMGoles();
        String filename = "quilmes_2012_1_gol_local.xml";

        Map<String, Integer> diccionarioGolesVisitante = domGoles.dameDiccionarioAutorCantidadGoles("visitante", filename);
        Map<String, Integer> diccionarioGolesLocal = domGoles.dameDiccionarioAutorCantidadGoles("local", filename);

        int cantidadGolesDicAciar = diccionarioGolesLocal.get("Walter Aciar");

        //Cantidad de goles:
        Assert.assertEquals("La cantidad de goles para el visitante no es la correcta",
                diccionarioGolesVisitante.size(), 0);
        Assert.assertEquals("La cantidad de goles para el local no es la correcta",
                diccionarioGolesLocal.size(), 1);

        //Gol local:
        Assert.assertEquals("La cantidad de goles de Aciar es incorrecta",
                cantidadGolesDicAciar, 1);
    }

    @Test
    public void devolver0GolesVisYLocal() throws Exception {
        DOMGoles domGoles = new DOMGoles();
        String filename = "quilmes_2012_sin_goles.xml";

        Map<String, Integer> DicGolesVisitante = domGoles.dameDiccionarioAutorCantidadGoles("visitante", filename);
        Map<String, Integer> DicGolesLocal = domGoles.dameDiccionarioAutorCantidadGoles("local", filename);

        //Cantidad de goles:
        Assert.assertEquals("El metodo devolver0Goles no esta devolviendo el valor esperado para el local",
                DicGolesLocal.size(), 0);
        Assert.assertEquals("El metodo devolver0Goles no esta devolviendo el valor esperado para el visitante",
                DicGolesVisitante.size(), 0);
    }

}
