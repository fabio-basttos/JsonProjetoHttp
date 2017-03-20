package com.example.labdesenvolvimento.jsonprojetohttp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lab. Desenvolvimento on 20/03/2017.
 */


public class JSONActivity extends Activity {

        private TextView textJSON;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_json);
            textJSON = (TextView) findViewById(R.id.textJSON);
        }

        public void conectar(View v){
            new DownloadFromOmbBapi().execute();
        }

        private class DownloadFromOmbBapi extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL("http://www.omdbapi.com/?t=Dexter&Season=1");

                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    String result = Util.webToString(urlConnection.getInputStream());

                    return result;
                } catch (Exception e) {
                    Log.e("Error", "Error ", e);
                    return null;
                } finally{
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                textJSON.setText(s);
            }
        }
}
