//package Aplicacion;
//
//import Persistencia.*;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class aplicacionCliente {
//
//    static conexionOracle co;
//
//    public static void main(String[] args) throws SQLException {
//
//        try {
//            co = new conexionOracle();
//            System.out.println("Conexion realizada con éxito\n");
//            System.out.println("Menú de Opciones");
//            System.out.println("----------------");
//            System.out.println("1. Expertos por nacionalidad");
//            System.out.println("2. Insertar un experto");
//            System.out.println("3. Insertar una colaboración");
//            System.out.println();
//            System.out.print("¿Qué quieres hacer?  ");
//            Scanner sc = new Scanner(System.in);
//            String opcion = sc.nextLine();
//            switch (opcion) {
//                case "1":
//                    ejercicio1("EEUU");
//                    break;
//                case "2":
//                    ejercicio2();
//                    break;
//                case "3":
//                    ejercicio3();
//                    break;
//            }
//
//            co.desconexion();
//        } catch (SQLException e) {
//            System.out.println("Error al operar con la BD: " + e.getMessage());
//            //co.finTransaccionRollback();
//        } catch (Exception e) {
//            System.out.println("Error en el programa principal: " + e.getMessage());
//            co.finTransaccionRollback();
//        }
//    }
//
//    public static void ejercicio1(String p) throws SQLException {
//
//        ArrayList<experto> array;
//        manejaExperto m = new manejaExperto(co);
//        array = m.listaExpertosPorPais(p);
//
//        for (int i = 0; i < array.size(); i++) {
//            experto e = array.get(i);
//            System.out.println(e.getCodExperto() + "  " + e.getNombre() + "  " + e.getPais());
//        }
//    }
//
//    public static void ejercicio2() throws SQLException {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Inserta codigo ");
//        String codigo = sc.nextLine();
//        System.out.print("Inserta nombre ");
//        String nombre = sc.nextLine();
//        System.out.print("Inserta pais ");
//        String pais = sc.nextLine();
//        System.out.print("Inserta sexo ");
//        String sexo = sc.nextLine();
//        System.out.print("Inserta Especialidad ");
//        String Espe = sc.nextLine();
//        manejaExperto m = new manejaExperto(co);
//        m.insertaExperto(new experto(codigo, nombre, pais, sexo, Espe));
//        System.out.print("..... \n");
//        System.out.print("Insertado \n");
//        ejercicio1(pais);
//
//    }
//
//    public static void ejercicio3() throws SQLException,Exception {
//        co.inicioTransaccion();
//        Scanner sc = new Scanner(System.in);
//         
//        manejaColabora mC=new manejaColabora(co);
//        manejaExperto mE=new manejaExperto((conexionOracle) co);
//        manejaCaso mCa=new manejaCaso(co);
//        
//        System.out.println("Ejercicio ");
//        /*
//        Vamos a controlar el experto
//        */
//       
//       System.out.print("Inserta codigo Experto ");
//        String codigoE = sc.nextLine();
//        
//        if(!mE.existeExperto(codigoE)){
//        System.out.println("Insertar Datos Experto");
//        System.out.print("Inserta nombre ");
//        String nombre = sc.nextLine();
//        System.out.print("Inserta pais ");
//        String pais = sc.nextLine();
//        System.out.print("Inserta sexo ");
//        String sexo = sc.nextLine();
//        System.out.print("Inserta Especialidad ");
//        String Espe = sc.nextLine();
//            System.out.println("prueba "+codigoE+"  "+nombre);
//      
//        mE.insertaExperto(new experto(codigoE, nombre, pais, sexo, Espe));
//        System.out.print("..... \n");
//        System.out.print("Insertado Experto \n");
//        }
//        
//        /*
//        vamos a controlar el caso
//        */
//        
//        System.out.print("Inserta datos de Caso ");
//        String codigoC = sc.nextLine();
//        String fechaIni=null;
//        
//        if(!mCa.existeCaso(codigoC)){
//            System.out.println("Insertar Datos Caso");
//        System.out.print("Inserta nombre Caso ");
//        String nombre = sc.nextLine();
//        System.out.print("Inserte fecha inicio ");
//        fechaIni = sc.nextLine();
//        System.out.print("Inserta fecha fin");
//        String fechaFin = sc.nextLine();
//        caso ca=new caso(codigoC,nombre,fechaIni,fechaFin);
//        mCa.insertaCaso(ca);
//        System.out.print("..... \n");
//        System.out.print("Insertado Experto \n");
//        }
//        else{//tengo que leer la fecha de la tabla
//        System.out.print("Inserta la fecha del caso ");
//        fechaIni = sc.nextLine();
//        }
//        
//        /*
//        vamos a insertar los elementos a colabora
//        */
//        
//       colabora c=new colabora(codigoE,codigoC,fechaIni,"ejercicio 3");
//        mC.insertaColaboracion(c);
//        System.out.println("Colaboracion insertada");
//        co.finTransaccionCommit();
//        
//        
//    }
//}
