package com.example.android.bluetoothchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class VRConductorClient {
    public void send(String message) {
        try {
            URL url = new URL("http://vrconductor.appspot.com/activity");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            BufferedWriter out =
                    new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            out.write(message);
            out.flush();
            out.close();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }
            in.close();
        } catch (MalformedURLException ex) {
            // a real program would need to handle this exception
        } catch (IOException ex) {
            ex.printStackTrace();
            // a real program would need to handle this exception
        }
    }
}
