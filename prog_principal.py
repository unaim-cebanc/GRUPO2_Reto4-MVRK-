import pandas as pd

# Diccionario de archivos CSV 
archivos_sedes = {
    
    '1': 'Consumo_de_Energia.csv',
    '2': 'Control_de_Residuos.csv',
    '3': 'Emisiones.csv',
    '4': 'Salas_de_Servidores.csv',
    '5': 'Sedes.csv',
    '6': 'Sistemas_de_Refrigeracion.csv'
}

# Muestra el menú de archivos disponibles 
def mostrar_menu_archivos():
    print("\n" + "▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌")
    print("▐       ARCHIVOS DISPONIBLES                      ▌")
    print("▐" + "="*49 + "▌")
    print("▐ 1. Consumo_de_Energia.csv                       ▌")
    print("▐ 2. Control_de_Residuos.csv                      ▌")
    print("▐ 3. Emisiones.csv                                ▌")
    print("▐ 4. Salas_de_Servidores.csv                      ▌")
    print("▐ 5. Sedes.csv                                    ▌")
    print("▐ 6. Sistemas_de_Refrigeracion.csv                ▌")
    print("▐ 0. Volver al menú principal                     ▌")
    print("▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌")

#menu para elegir las opciones
def menu_generico():
    while True:
        mostrar_menu_archivos()
        opcion = input("\nElige un archivo (1-6) o 0 para volver: ").strip()
        
        if opcion == '0':
            print("Volviendo al menú principal...")
            break
        #==============================ayudado con IA========================================
        if opcion in archivos_sedes:
            try:
                df = pd.read_csv(archivos_sedes[opcion])
                print(f"\n Contenido de {archivos_sedes[opcion]}:")
                print(df.to_string(index=False))  
                input("\nPresiona Enter para continuar...")
            except FileNotFoundError:
                print(" Archivo no encontrado. Verifica que esté en la misma carpeta.")
            except Exception as e:
                print(f" Error al leer archivo: {e}")
        #===============================Fin ayuda IA=========================================
        else:
            print(" Opción no válida.")

def buscar_por_sede():
    print("en proceso")

    # Menu principal con bucle infinito hasta salir.
def main():
    
    while True:
        print("\n" + "▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌")
        print("▐ MENÚ PRINCIPAL - GESTIÓN DE SEDES               ▌")
        print("▐"+"="*49 + "▌")
        print("▐ 1. Buscar información por sede                  ▌")
        print("▐ 2. Buscar información genérica                  ▌")
        print("▐ 3. Salir                                        ▌")
        print("▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌")
        
        opcion = input("Elige una opción (1-3): ").strip()
        
        if opcion == '1':
            buscar_por_sede()
        elif opcion == '2':
            menu_generico()
        elif opcion == '3':
            print(" ¡Hasta luego!")
            break
        else:
            print(" Opción no válida. Intenta de nuevo.")

if __name__ == "__main__":
    main()
