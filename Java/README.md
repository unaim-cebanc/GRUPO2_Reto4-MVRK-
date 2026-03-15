# Introducción

Aquí se encuentra nuestro programa de Java desarrollado por el grupo número 2 MVRK y sus integrantes Unai Manterola, Aimar García, Aroa Hernandez y Unax Gahona. A continuación se encuentra una explicación de como funciona el programa y como está estructurado.

## Página de inicio de sesión
Lo primero que uno vé cuando ejecuta el programa. Es el formulario de inicio de sesión que el usuario tiene que completar para acceder a las herramientas principales de la aplicación.

### Clases principales:

#### InicioFrame.java
Contiene todos los componentes visuales del Frame y controla sus respectivas funcionalidades tales como la acción de cerrar la aplicación desde el menú.

**Autores:**
- Unai Manterola: Apartado visual (Apariencia del Frame, campos de texto, menú etc.) y funcionalidad de inicio de sesión.
- Aroa Hernandez: Traducciones y funciones para intercambiar de idioma el texto de los elementos del Frame.

#### Database.java:
Practicamente todo el programa revuelve alrededor de esta clase, todas las acciones que requieran conexión con la base de datos se hacen desde esta clase. En este caso, se utiliza para confirmar las credenciales de usuario que se introducen.

**Autores:**
- Unai Manterola: Método para validación de credenciales de usuario y conexion a la base de datos.
- Aroa Hernandez: Método(s) que permiten eliminar elementos de las tablas de la base de datos.
- Unax Gahona: Métodos que permiten insertar datos a las tablas de la base de datos.
- Aimar Garcia: Método(s) que permiten exportar las tablas a .csv
> [!NOTE]
> La función de exportación de datos se desarrolló con la ayuda de la IA por motivos de complicación
---

## Página principal
Aquí es donde ocurre todo lo importante y donde el usuario puede interactuar de manera directa con la base de datos para realizar un total de tres cosas, añadir elementos a las tablas, eliminar elementos de las tablas o exportarlas a .csv para su posterior análisis con el programa complementario de python.

### Clases principlaes:

#### MainFrame.java
Ventana que visualiza el usuario, está compuesto por Internal Frames, es decir, es un Frame (una ventana) en la que se pueden crear otros frames "hijos" desde uno principal. Contiene botones y menues con los que el usuario puede interactuar para realizar las acciones previamente citadas.

**Autores:**
- Unai Manterola: Apartado visual (Apariencia del Frame, menú etc.) e interactibilidad (métodos que controlan que accion tomar al hacer clic en algún botón u opción del menú: hacer clic --> abrir ventana).
- Aroa Hernandez: Traducción de los diferentes elementos del Frame y método que crea el InternalFrame para eliminar los datos de la base de datos.
- Unax Gahona: Métodos que crean los diferentes InternalFrames para añadir elementos a cada tabla de la base de datos.
- Aimar: Método que permite exportar los .csv y apariencia de los botónes.

#### InternalFrame.java
Es la clase "padre" desde la que se crean todos los InternalFrame de MainFrame.java.

**Autores:**
>[!NOTE]
>Esta clase está sacada de la documentación de Oracle y no ha sido desarrollada por ninguno de los integrantes del grupo.

#### InternalFrame???.java
Creadas a partir de InternalFrame.java, estas clases contienen la apariencia de cada uno de estos InternalFrame. Cada uno esta ligado a la tabla de la base de datos con el mismo nombre y se usan a la hora de ingresar los elementos a estos, puesto que hacer solamente una clase que nos permitiera insertar elementos a todas las tablas nos acabo siendo demasiado complicado.
**Autores:**
- Unax Gahona: Usando una referencia proporcionada por Unai, Unax completó todas y cada una de estas clases.

#### Database.java
*Ver:* [Database.java](#databasejava)
