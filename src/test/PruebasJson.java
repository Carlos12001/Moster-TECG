package test;

import com.google.gson.Gson;
import model.game.Player;

import java.io.*;

public class PruebasJson {
    public static void main(String[] args) {
        PruebasJson.cuartoEjemplo();
    }

    private static void primerEjemplo(){
        String jsonRead = new String();
        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/test/data/Ejemplo3.json"));

            String linea = "";
            //Lee linea por linea el archivo
            while ((linea = br.readLine()) != null){
                jsonRead += linea;
            }
        } catch (FileNotFoundException e) {System.out.println(e.getMessage());;
        } catch (IOException e) {System.out.println(e.getMessage());;
        }

        System.out.println(jsonRead);

        Gson gson = new Gson();
        Player player = gson.fromJson(jsonRead, Player.class);

        System.out.println("\nEl objeto Persona quedo: \n" + player);

    }

    private static void segundoEjemplo(){
        String jsonRead = new String();
        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/test/data/Ejemplo2.json"));

            String linea = "";
            //Lee linea por linea el archivo
            while ((linea = br.readLine()) != null){
                jsonRead += linea;
            }
        } catch (FileNotFoundException e) {System.out.println(e.getMessage());;
        } catch (IOException e) {System.out.println(e.getMessage());;
        }

        System.out.println(jsonRead);

        Gson gson = new Gson();
        Player player = gson.fromJson(jsonRead, Player.class);

        System.out.println("\n\nEl objeto Persona quedo: \n" + player.getName());

    }

    private static void tercerEjemplo(){
        Gson gson = new Gson();
        Player obj = new  Player("Juan");
        String jsonString = gson.toJson(obj);
        System.out.println(jsonString);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/test/data/DatosPlayer.json"))) {
            bw.write(jsonString);
            System.out.println("Fichero creado");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void cuartoEjemplo(){
        Gson gson = new Gson();
        Player obj = new  Player("Juan");
        String jsonString = gson.toJson(obj);
        System.out.println(jsonString);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/test/data/DatosPlayer2.json"))) {
            bw.write(jsonString);
            System.out.println("Fichero creado");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
