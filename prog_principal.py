import pandas as pd
from os import system
from sys import exit

# Diccionario de archivos CSV 
archivos_sedes = {
    '1': 'consumo_energia.csv',
    '2': 'control_residuos.csv',
    '3': 'emisiones.csv',
    '4': 'sala_servidores.csv',
    '5': 'sedes.csv',
    '6': 'sistemas_refrigeracion.csv'
}

# Muestra el menú de archivos disponibles 
def mostrar_menu_archivos():
    print("▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌")
    print("▐       ARCHIVOS DISPONIBLES                      ▌")
    print("▐" + "="*49 + "▌")
    print("▐ 1. consumo_energia.csv                          ▌")
    print("▐ 2. control_residuos.csv                         ▌")
    print("▐ 3. emisiones.csv                                ▌")
    print("▐ 4. sala_servidores.csv                          ▌")
    print("▐ 5. sedes.csv                                    ▌")
    print("▐ 6. sistemas_refrigeracion.csv                   ▌")
    print("▐ 0. Volver al menú principal                     ▌")
    print("▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌")

#menu para elegir las opciones
def menu_generico():
    while True:
        system("cls")
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
    system("cls")
    print("\n▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌")
    print("▐ MENÚ BUSQUEDAS - SEDES                          ▌")
    print("▐"+"="*49 + "▌")
    print("▐ 1. Sede Aurora                                  ▌")
    print("▐ 2. Centro Boreal                                ▌")
    print("▐ 3. Edificio Horizonte                           ▌")
    print("▐ 4. Sede Atlántica                               ▌")
    print("▐ 5. Torre Innovar                                ▌")
    print("▐ 6. Campus Solaris                               ▌")
    print("▐ 7. Oficina Nébula                               ▌")
    print("▐ 8. Complejo Prisma                              ▌")
    print("▐ 9. Sede Vanguardia                              ▌")
    print("▐ 10. Hub Continental                             ▌")
    print("▐ 0. Volver al menú principal                     ▌")
    print("▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌")
    opcion = int(input("Sobre que sede quieres hacer una búsqueda: "))
    match opcion:
        case opcion if 1 <= opcion <= 10:
            hacer_busqueda_sedes(opcion)
        case 0:
            main()
    # Menu principal con bucle infinito hasta salir.

def hacer_busqueda_sedes(num_sede):
    system("cls")
    mostrar_menu_archivos()
    opcion = input("\nElige un archivo (1-6) o 0 para volver: ").strip()
    if opcion == "0":
        main()
    if opcion in archivos_sedes:
        try:
            df = pd.read_csv(archivos_sedes[opcion])
            df_filtrado = df[df["id_sede"] == num_sede]
            print(f"\n Contenido de {archivos_sedes[opcion]}:")
            print(df_filtrado.to_string(index=False))  
            input("\nPresiona Enter para continuar...")
        except FileNotFoundError:
            print(" Archivo no encontrado. Verifica que esté en la misma carpeta.")
        except Exception as e:
            print(f" Error al leer archivo: {e}")
    #===============================Fin ayuda IA=========================================
    else:
        print(" Opción no válida.")

def main():
    
    while True:
        system("cls")
        print("\n▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌")
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
