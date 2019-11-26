import dom.*;
import org.w3c.dom.NodeList;
import sax.SAXHandlerFiguraPartido;
import validator.XmlSchemaValidator;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.util.*;

public class DOMmain {

    //Variable xmlFileName con su getter y setter (la usamos más abajo en muchos métodos):
    private static String xmlFileName;
    public static String getXmlFileName() { return xmlFileName; }
    public static void setXmlFileName(String xmlFilePath) { DOMmain.xmlFileName = xmlFilePath; }

    private static String xmlFilePath;
    public static String getXmlFilePath() { return xmlFilePath; }
    public static void setXmlFilePath(String xmlFilePath) { DOMmain.xmlFilePath = xmlFilePath; }

    //Constantes:
    static final String local  = "local";
    static final String visitante = "visitante";

    //Main (menu principal):
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Scanner inputPaths = new Scanner(System.in);
        System.out.println("Ingrese ruta del archivo XML: ");
        System.out.println("Por ejemplo: /home/federicio/Desktop/PD2-MisReposGithub/TP1/TP1/src/main/resources/quilmes_2012.xml");
        String xmlFilePath = inputPaths.nextLine();
        setXmlFilePath(xmlFilePath);
        //String xmlFilePath = "/home/federicio/Desktop/PD2-MisReposGithub/TP1/TP1/src/main/resources/quilmes_2012.xml";

        System.out.println("Ahora ingrese ruta del archivo XSD (XML-Schema): ");
        System.out.println("Por ejemplo: /home/federicio/Desktop/PD2-MisReposGithub/TP1/TP1/src/main/resources/quilmes_2012.xsd");
        String schemaFilePath = inputPaths.nextLine();
        //String schemaFilePath = "/home/federicio/Desktop/PD2-MisReposGithub/TP1/TP1/src/main/resources/quilmes_2012.xsd";

        // Validamos el XML contra el XML Schema (XSD):
        //Obtenemos el nombre del arhivo xml dado el xmlFilePath usando split y lo seteamos a la variable xmlFileName que utilizaremos más abajo en los distintos métodos:
        String[] vectorXmlFilePath = xmlFilePath.split("/"); //asi vector[0]= vacio, [1]=home, [2]=federicio, [lenght-1]=quilmes.xml.
        String nombreArchivoXML = vectorXmlFilePath[vectorXmlFilePath.length-1]; //siempre en el indice vector.lenght-1 va a quedar el nombre del archivo.
        System.out.println("Nombre del archivo XML es: " + nombreArchivoXML);
        setXmlFileName(nombreArchivoXML);

        boolean validated = XmlSchemaValidator.validateXMLSchema(schemaFilePath, xmlFilePath); //XmlSchemaValidator es la clase dentro del paquete validator (no hace falta hacer el import).
        if (validated==false) {
            System.out.println("El documento XML es inválido dados dichos errores encontrados, terminará la ejecución");
            System.exit(0);
        }
        else{
            System.out.println("El documento XML es válido");
            //Ahora que ya esta validado el XML seguimos...

            Scanner input = new Scanner(System.in);
            boolean salir = false;
            int opcion; //Guardaremos la opcion del usuario.

            while (!salir) {
                System.out.println("Elija la opcion:");
                System.out.println("1-Mostrar la formación del local y visitante"); //Hay que agregar un * al lado del apellido  del jugador por cada gol realizado en el partido y una [C] al lado de cada capitán.
                System.out.println("2-Mostrar la figura del partido");  //Puede ser más de una figura. Como minimo debe haber una. Realizar esto sin recorrer el DOM.
                System.out.println("3-Mostrar resultado del partido");  //Ver como se tiene que detallar en el pdf.
                System.out.println("4-Exportar XML con seccion notas"); //Hay que agregar una nota/comentario, y crear un <notas> en el XML como hija de <partido>
                System.out.println("5-Salir");

                try {
                    System.out.println("Elija una de las opciones");
                    opcion = input.nextInt();

                    switch (opcion) {
                        case 1:
                            imprimirFormacion();
                            break;

                        case 2:
                            imprimirFiguraSAX();   //Mediante SAX (sin recorrer el DOM).
                            break;

                        case 3:
                            imprimirResultadoDelPartido();
                            break;

                        case 4:
                            Scanner input2=new Scanner(System.in);
                            System.out.println("Ingrese la nota / comentario que desee agregar sobre el partido: ");
                            String nota = input2.nextLine();
                            DOMComentarioXML.exportarXMLConNota(nota,getXmlFilePath());
                            System.out.println("Nota agregada correctamente al archivo -quilmes_2012_con_notas.xml-");
                            break;

                        case 5:
                            salir = true;
                            break;

                        default:
                            System.out.println("Solo números entre 1 y 5");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes elegir una opcion");
                    input.next();
                }
            }
        }
    }

    public static void imprimirFormacion() throws IOException, SAXException, ParserConfigurationException {
        //Imprimimos la formacion del local:
        String equipoLocal = DOMEquipoLocalYVisitante.dameEquipo(local, getXmlFileName());
        System.out.println("Formacion del local: " +equipoLocal );
        DOMFuncionesAuxiliares domFuncionesAuxLocal  = new DOMFuncionesAuxiliares();
        domFuncionesAuxLocal.imprimirFormacion(local, getXmlFileName());

        System.out.println("------------------------------------------------");

        //Imprimimos la formacion del visitante:
        String equipoVisitante = DOMEquipoLocalYVisitante.dameEquipo(visitante, getXmlFileName());
        System.out.println("Formacion del visitante: " + equipoVisitante);
        DOMFuncionesAuxiliares domFuncionesAuxVisitante  = new DOMFuncionesAuxiliares();
        domFuncionesAuxVisitante.imprimirFormacion(visitante, getXmlFileName());
    }

    public static void imprimirFiguraSAX() throws SAXException, ParserConfigurationException {
        System.out.println("Figura/s del partido:");
        SAXParserFactory factory = SAXParserFactory.newInstance();  //Creamos una instancia del parser SAX
        ClassLoader classLoader = DOMmain.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(getXmlFileName());

        SAXParser saxParser = factory.newSAXParser();
        SAXHandlerFiguraPartido hSAXFigura = new SAXHandlerFiguraPartido();

        try{saxParser.parse(inputStream,hSAXFigura);
        } catch (IOException e) {
            e.printStackTrace();
          }
    }

    public static void imprimirResultadoDelPartido() throws IOException, SAXException, ParserConfigurationException {

        //Equipo local:
        String equipoLocal = DOMEquipoLocalYVisitante.dameEquipo(local, getXmlFileName());
        Map<String, ArrayList<Integer>> dicAutorGolLocal = DOMResultadoPartido.dameDiccionarioAutorGol(local, getXmlFileName());
        //Hasta aca tenemos un diccionario del tipo {"Walter Aciar" --> [90], ...}
        Integer cantidadGolesLocal=DOMResultadoPartido.dameCantidadGoles(dicAutorGolLocal);
        //Ordenamos las listas de cada jugador (o sea los VALORES del diccionario) en base a los minutos:
        Map<String, ArrayList<Integer>> dicAutorGolLocalOrdenadoPorValor = DOMResultadoPartido.ordenameListaDelDiccionarioEnBaseAMinutos(dicAutorGolLocal);
        //Ahora ordenamos las CLAVES del diccionario (jugadores) en base al 1er minuto que marco el gol el jugador:
        Map<String, ArrayList<Integer>> dicAutorGolLocalOrdenadoPorValorYPorClave = DOMResultadoPartido.ordenameClaveDelDiccionarioEnBaseAMinutos(dicAutorGolLocalOrdenadoPorValor);

        //Imprimimos:
        System.out.println(equipoLocal + " " + cantidadGolesLocal);
        //DOMFuncionesAuxiliares.imprimirDiccionario(dicAutorGolLocal); //Sin orden.
        //DOMFuncionesAuxiliares.imprimirDiccionario(dicAutorGolLocalOrdenadoPorValor); //Ordenado por valor.
        DOMFuncionesAuxiliares.imprimirDiccionario(dicAutorGolLocalOrdenadoPorValorYPorClave); //Ordenado por valor y por clave.

        //Equipo visitante:
        String equipoVisitante = DOMEquipoLocalYVisitante.dameEquipo(visitante, getXmlFileName());
        Map<String, ArrayList<Integer>> dicAutorGolVisitante = DOMResultadoPartido.dameDiccionarioAutorGol(visitante, getXmlFileName());
        //Hasta aca tenemos un diccionario del tipo {"Martin Cauteruccio" --> [18,23], ...}
        Integer cantidadGolesVisitante=DOMResultadoPartido.dameCantidadGoles(dicAutorGolVisitante);
        //Ordenamos las listas de cada jugador (o sea los VALORES del diccionario) en base a los minutos:
        Map<String, ArrayList<Integer>> dicAutorGolVisitanteOrdenadoPorValor = DOMResultadoPartido.ordenameListaDelDiccionarioEnBaseAMinutos(dicAutorGolVisitante);
        //Ahora ordenamos las CLAVES del diccionario (jugadores) en base al 1er minuto que marco el gol el jugador:
        Map<String, ArrayList<Integer>> dicAutorGolVisitanteOrdenadoPorValorYPorClave = DOMResultadoPartido.ordenameClaveDelDiccionarioEnBaseAMinutos(dicAutorGolVisitanteOrdenadoPorValor);

        //Imprimimos:
        System.out.println(equipoVisitante + " " + cantidadGolesVisitante);
        //DOMFuncionesAuxiliares.imprimirDiccionario(dicAutorGolVisitante); //Sin orden.
        //DOMFuncionesAuxiliares.imprimirDiccionario(dicAutorGolVisitanteOrdenadoPorValor); //Ordenado por valor.
        DOMFuncionesAuxiliares.imprimirDiccionario(dicAutorGolVisitanteOrdenadoPorValorYPorClave); //Ordenado por valor y por clave.
    }
}