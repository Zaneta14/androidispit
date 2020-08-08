package com.example.ispit_9_2017;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

public class DoAsyncTask extends AsyncTask<Void, Void, String> {
    private String sharedPrefFile = "com.example.ispit_9_2017";
    SharedPreferences sharedPreferences;
    Context context;

    public DoAsyncTask(Context ctx) {
        context=ctx;
    }
    @Override
    protected String doInBackground(Void... voids) {
        String myUrl="https://jsonplaceholder.typicode.com/todos/1";
        InputStream inputStream=null;
        BufferedReader reader=null;
        HttpURLConnection conn=null;
        String response=null;
        try {
            URL url=new URL(myUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            inputStream=conn.getInputStream();
            StringBuffer builder=new StringBuffer();
            reader=new BufferedReader(new InputStreamReader(inputStream));
            String s;
            while ((s=reader.readLine())!=null) {
                builder.append(s+'\n');
            }
            response=builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject=new JSONObject(s);
            String userId=  jsonObject.getString("userId");
            String id=  jsonObject.getString("id");
            String title=  jsonObject.getString("title");
            String completed=  jsonObject.getString("completed");
            String resp="userId="+userId+", id="+id+", title="+title+", completed="+completed;
            sharedPreferences = context.getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
            SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
            preferencesEditor.putString("response", resp);
            preferencesEditor.apply();
            Log.d("ZANETA", resp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
