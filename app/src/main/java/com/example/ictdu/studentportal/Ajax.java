package com.example.ictdu.studentportal;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.*;
import java.net.*;
import java.util.*;

public class Ajax extends AsyncTask <Void, Void, Void> {

    String response = "";
    private String FirstName;
    private String LastName;
    private String Username;
    private String Password;
    SignupActivity signupActivity;

    public Ajax(SignupActivity signupActivity, String firstname, String lastname, String username, String password) {
        this.signupActivity = signupActivity;
        this.FirstName = firstname;
        this.LastName = lastname;
        this.Username = username;
        this.Password = password;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HashMap<String,String> postDataParams = new HashMap<>();
        postDataParams.put("firstname",FirstName);
        postDataParams.put("lastname",LastName);
        postDataParams.put("username",Username);
        postDataParams.put("password",Password);

        URL url = null;
        try {
            url = new URL("http://10.0.2.2/spcf_post/index.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setDoInput(true);
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();

            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while((line = br.readLine()) != null){
                    response += line;
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        signupActivity.btn.setEnabled(true);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(signupActivity, response, duration);
        toast.show();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
