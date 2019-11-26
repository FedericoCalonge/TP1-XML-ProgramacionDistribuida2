package dom;
import org.junit.Assert;
import org.junit.Test;

public class DOMEquipoLocalYVisitanteTest {
    @Test
    public void dameEquipoLocal() throws Exception {
        DOMEquipoLocalYVisitante domEquipoLocalYVisitante = new DOMEquipoLocalYVisitante();
        String pathFile = "quilmes_2012.xml";
        String equipo = domEquipoLocalYVisitante.dameEquipo("local", pathFile);

        Assert.assertEquals("El método dameEquipoLocal no está devolviendo lo esperado",
                "Guilermo Brown", equipo);
    }

    @Test
    public void dameEquipoVisitante() throws Exception {
        DOMEquipoLocalYVisitante domEquipoLocalYVisitante = new DOMEquipoLocalYVisitante();
        String pathFile = "quilmes_2012.xml";
        String equipo = domEquipoLocalYVisitante.dameEquipo("visitante",pathFile);

        Assert.assertEquals("El método dameEquipoLocal no está devolviendo lo esperado",
                "Quilmes", equipo);
    }

}
