package com.babel;

/**
 * Hello world!
 *
 */

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        final String DIRECCION =  "https://api.chucknorris.io/jokes/random";
        Scanner teclado = new Scanner(System.in);
        String decision;
        do {
            System.out.println("Quieres recibir una broma o crearla: (r-c) ");
            decision= teclado.nextLine();

            if ("R".equalsIgnoreCase(decision)) {
                String json;
                try {
                    json = getJSON(DIRECCION);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (json != null) {
                    Gson gson = new Gson();
                    Jokes broma = gson.fromJson(json, Jokes.class);

                    System.out.println(broma);
                }
            } else if ("C".equalsIgnoreCase(decision)) {
                String icon_url, id,url, value;

                System.out.println("Introduce la url del icono: ");
                icon_url = teclado.nextLine();

                System.out.println("Introduce la id: ");
                id = teclado.nextLine();

                System.out.println("Introduce la url: ");
                url = teclado.nextLine();

                System.out.println("Introduce la broma: ");
                value = teclado.nextLine();

                Jokes broma = new Jokes(icon_url,id,url,value);

                Gson gson = new Gson();


                System.out.println(gson.toJson(broma));
            }
        }while ("R".equalsIgnoreCase(decision) || "C".equalsIgnoreCase(decision));


    }

    public static String getJSON(String direccion) throws IOException, ProtocolException, MalformedURLException {
        URL enlace = new URL(direccion);
        HttpURLConnection conexionURL = (HttpURLConnection) enlace.openConnection();
        conexionURL.setRequestMethod("GET");
        InputStream is = conexionURL.getInputStream();
        byte[] arrStream = is.readAllBytes();
        String json = "";
        for (byte b : arrStream) {
            json += (char) b;

        }
        return json;
    }

}
