package or.example.rdesmarket_v10.utilidades;

import java.util.ArrayList;

public class Utilidades {
    public static ArrayList<String> lista = null;
    public static String seleccion="8:00";


    public void llenarLista(){
        lista = new ArrayList<String>();
        for (int i = 8;i<=12;i++ ){
            lista.add(i+":"+"00");
            for (int j=10;j<=50;j=j+10){
                lista.add(i+":"+j);   //AQUI LO LLENO DE EJEMPLO ESTO SE DEBE LLENAR SEGUN LA BASE DE DATOS
            }
        }
    }
    public static ArrayList<String> getLista() {
        return lista;
    }
}
