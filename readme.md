# Proyecto de Gestión de Sedes

## Descripción General

Este programa en **Python** permite gestionar, analizar y visualizar datos relacionados con nuestras diversas **sedes corporativas**, a partir de múltiples archivos **CSV**.  
Los datos incluyen información sobre **consumo energético, emisiones, gestión de residuos, sistemas de refrigeración y salas de servidores**, con el objetivo de evaluar y mejorar la **eficiencia y sostenibilidad** de nuestras instalaciones.

El sistema ofrece una interfaz de consola intuitiva que organiza las funciones en menús claros y permite realizar búsquedas, verificaciones automáticas y análisis de forma sencilla.

---

## Funcionamiento del Programa

### Menú Principal

Al iniciar el programa, el usuario accede a un menú principal con tres opciones:

1. **Buscar información por sede** → Permite seleccionar una sede concreta y consultar todos los datos relacionados en los distintos CSV.  
2. **Buscar información genérica** → Permite explorar el contenido completo de cada archivo sin aplicar filtros.  
3. **Salir** → Cierra el programa de forma segura.

El programa está diseñado para ser totalmente **interactivo**: basta introducir el número de la opción deseada y seguir las indicaciones que aparecen en pantalla.

Ademas cuenta con un analisis de errores y un control de datos, en el que si el programa detecta algun dato anormal en algun csv, te avisara de ello.

---

### Estructura de Archivos

El programa trabaja con los siguientes archivos CSV, definidos en un diccionario central:

```python
archivos_sedes = {
    '1': 'consumo_energia.csv',
    '2': 'control_residuos.csv',
    '3': 'emisiones.csv',
    '4': 'sala_servidores.csv',
    '5': 'sedes.csv',
    '6': 'sistemas_refrigeracion.csv'
}
