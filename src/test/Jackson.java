package test;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import model.game.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Jackson {
    public static void main(String[] args) {
        MyOject ob1 = new MyOject();


        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper mapper = new ObjectMapper();
        File jackson ;

        try {
            jackson =  new File(System.getProperty("user.dir")+"/src/data/Update.json");

            // get Oraganisation object as a json string
            mapper.writeValue(jackson,ob1);



            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/data/Update.json"));
            String jsonRead = new String();
            String linea = "";


            //String

            //Lee linea por linea el archivo
            while ((linea = br.readLine()) != null){
                jsonRead += linea;
            }

            //retorno el str
            System.out.println(jsonRead);

            ////El que recibe

            //Metodo lecturarecibido

            //Cambiar de string a Jackson a Objeto
            MyOject on = mapper.readValue(jsonRead, MyOject.class);

            System.out.println(on.getAtrib1());




        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();




        }
    }
}
