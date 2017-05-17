package nsutanto.model;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;

import java.net.*;
import java.io.*;



/**
 * Created by Nico on 5/13/17.
 */

public class RestClient {

    public RestClient() {

    }

    public boolean SignIn(String email) throws Exception {

        boolean retVal = false;
        LoginInfo loginInfo = new LoginInfo(email);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(loginInfo);

        URL url = new URL("http://nsutantoshoppingapp.azurewebsites.net/api/Account/LoginByToken");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonStr.getBytes());
        os.flush();


        // read the response
        System.out.println("Response Code: " + conn.getResponseCode());

        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");

        UserInfo userInfo = gson.fromJson(result, UserInfo.class);

        conn.disconnect();
        retVal = true;
        return retVal;
    }

    public void RegisterUser(String email) throws Exception {

        RegisterInfo regInfo = new RegisterInfo(email);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(regInfo);

        URL url = new URL("http://nsutantoshoppingapp.azurewebsites.net/api/Account/Register");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonStr.getBytes());
        os.flush();

        // read the response
        System.out.println("Response Code: " + conn.getResponseCode());
        //InputStream in = new BufferedInputStream(conn.getInputStream());
        //String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
        //System.out.println(response);

        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
        //BufferedReader br = new BufferedReader(new InputStreamReader(
        //       (conn.getInputStream())));
        //String output;
        //while ((output = br.readLine()) != null) {
        //    System.out.println(output);
        //}

        conn.disconnect();
    }
}


