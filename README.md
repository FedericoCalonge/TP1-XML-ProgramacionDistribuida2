# TPS-ProgramacionDistribuida2

>Trabajo Práctico 1 -XML & XMLSchema & DOM- para la materia Programación Distribuida 2 (PD2), de la Universidad Nacional de Avellaneda.
>
>Profesor: Juan Lagostena.
>
>Autor: Federico Calonge.

### Requirements
-JAVA 11.
-Maven.

### Consigna
Leer el archivo "Consigna.pdf".
Programa de lecura, escritura y validación de un archivo XML.
El archivo XML describe un partido y su resultado (con elementos equipos, jugadores, goles, etc.).
Test con JUnit y uso de TRAVIS CI.

### Uso
*Main a ejecutar: en src/main/java/DOMMain.
*Estando parados en /TP1/TP1... utilizar el siguiente comando de Maven para que compile el proyecto y luego lo ejecute: 
>mvn compile exec:java -Dexec.mainClass="DOMmain"

### Mejoras
Si bien se realizó un diseño con división de responsabilidades (10 clases para Dom, 1 para SAX y otra para el Validator: cada una cubriendo una responsabilidad específica), faltó un diseño más “objetoso”. Siguiendo esto, se podrìan crear Clases que involucren el "negocio" del proyecto: Equipo, Jugador, Resultado; cada Clase con sus respectivos métodos (por ej. para Jugador podrìa existir un método marcarGol() donde se registre el nombre de cada jugador y minuto en que marcó un gol y luego usar esta información en la clase Resultado; la clase Equipo podría tener métodos para registrar los jugadores locales y visitantes en 2 listas de clase Jugador, etc.). 

