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
    '6': 'sistemas_refrigeracion.csv',
    '7': 'empleados.csv',
    '8': 'usuarios.csv'
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
    
    #==============================ayudado con IA========================================
def hacer_busqueda_sedes(num_sede):
    system("cls")
    mostrar_menu_archivos()
    opcion = input("\nElige un archivo (1-6) o 0 para volver: ").strip()
    if opcion == "0":
        main()
    if opcion == "6":
        try:
            df1 = pd.read_csv(archivos_sedes["4"])
            df2 = pd.read_csv(archivos_sedes[opcion])
            df_filtrado = pd.merge(df1, df2, left_on='id_sala_servidor', right_on='id_sala_servidor', how='inner')
            df_filtrado = df_filtrado[df_filtrado["id_sede"] == num_sede]
            print(f"\n Contenido de {archivos_sedes[opcion]}:")
            print(df_filtrado.to_string(index=False))  
            input("\nPresiona Enter para continuar...")
        except FileNotFoundError:
            print(" Archivo no encontrado. Verifica que esté en la misma carpeta.")
        except Exception as e:
            print(f" Error al leer archivo: {e}")
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

def analisis_energia():
    try:
        df_consumo = pd.read_csv(archivos_sedes['1'])
        df_sedes   = pd.read_csv(archivos_sedes['5'])
    except Exception as e:
        print(f" Error al leer los archivos: {e}")
        input("\nPresiona Enter para continuar...")
        return

        #==============================ayudado con IA========================================

    # Calcular kWh total por sede
    df_merge = pd.merge(df_consumo, df_sedes, on='id_sede', how='inner')
    totales  = df_merge.groupby(['id_sede', 'nombre_sede'])['kwh_totales'].sum().reset_index()
    media    = totales['kwh_totales'].mean()
    #===============================Fin ayuda IA=========================================

    while True:
        system("cls")
        print("\n▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌")
        print("▐ ANÁLISIS DE CONSUMO ENERGÉTICO                  ▌")
        print("▐"+"="*49+"▌")
        print("▐ 1. Ver consumo por sede                         ▌")
        print("▐ 2. Ver resumen global                           ▌")
        print("▐ 0. Volver al menú principal                     ▌")
        print("▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌")
        opcion = input("\nElige una opción (0-2): ").strip()

        if opcion == '0':
            break

        elif opcion == '1':
            system("cls")
            print("\n▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌")
            print("▐ SELECCIONA UNA SEDE                             ▌")
            print("▐"+"="*49+"▌")
            for _, row in df_sedes.iterrows():
                linea = f"▐  {row['id_sede']:2}. {row['nombre_sede']}"
                print(linea.ljust(50) + "▌")
            print("▐   0. Volver                                     ▌")
            print("▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌")
            try:
                sel = int(input("\nNúmero de sede: ").strip())
            except ValueError:
                continue
            if sel == 0:
                continue

            fila = totales[totales['id_sede'] == sel]
            if fila.empty:
                print(" No hay datos para esa sede.")
                input("\nPresiona Enter para continuar...")
                continue

            nombre = fila.iloc[0]['nombre_sede']
            kwh    = fila.iloc[0]['kwh_totales']

            print(f"\n  Sede  : {nombre}")
            print(f"  Total : {kwh:.0f} kWh")
            print(f"  Media : {media:.0f} kWh  (resto de sedes)")
            print("-"*40)
            if kwh <= media * 0.85:
                print("  ✔ Consumo eficiente.")
                print("  Buen trabajo, seguid así. Vuestra sede")
                print("  consume menos que la media. ¡Sin")
                print("  contaminación innecesaria!")
            elif kwh <= media * 1.15:
                print("  ~ Consumo normal.")
                print("  Estáis en la media. Pequeños ajustes")
                print("  como apagar equipos fuera de horario")
                print("  pueden marcar la diferencia.")
            else:
                print("  ! Consumo elevado.")
                print("  Esta sede consume más que la media.")
                print("  Revisad los equipos de mayor gasto y")
                print("  estableced un plan de ahorro energético.")
            print("-"*40)
            input("\nPresiona Enter para continuar...")
        #==============================ayudado con IA========================================

        elif opcion == '2':
            system("cls")
            print("\n▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌")
            print("▐ RESUMEN GLOBAL DE CONSUMO                       ▌")
            print("▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌")
            print(f"\n  {'Sede':<25} {'kWh Total':>10}  Estado")
            print("  " + "-"*45)
            for _, row in totales.iterrows():
                kwh = row['kwh_totales']
                if kwh <= media * 0.85:
                    estado = "✔ Eficiente"
                elif kwh <= media * 1.15:
                    estado = "~ Normal"
                else:
                    estado = "! Elevado"
                print(f"  {row['nombre_sede']:<25} {kwh:>10.0f}  {estado}")
            print("  " + "-"*45)
            print(f"  {'Media por sede':<25} {media:>10.0f}")
            input("\n\nPresiona Enter para continuar...")
    #===============================Fin ayuda IA=========================================

        else:
            print(" Opción no válida.")
            input("\nPresiona Enter para continuar...")


def main():
    
    while True:
        system("cls")
        print("\n▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌")
        print("▐ MENÚ PRINCIPAL - GESTIÓN DE SEDES               ▌")
        print("▐"+"="*49 + "▌")
        print("▐ 1. Buscar información por sede                  ▌")
        print("▐ 2. Buscar información genérica                  ▌")
        print("▐ 3. Análisis de consumo energético               ▌")
        print("▐ 4. Salir                                        ▌")
        print("▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌")
        
        opcion = input("Elige una opción (1-4): ").strip()
        
        if opcion == '1':
            buscar_por_sede()
        elif opcion == '2':
            menu_generico()
        elif opcion == '3':
            analisis_energia()
        elif opcion == '4':
            print(" ¡Hasta luego!")
            break
        else:
            print(" Opción no válida. Intenta de nuevo.")

if __name__ == "__main__":
    main()