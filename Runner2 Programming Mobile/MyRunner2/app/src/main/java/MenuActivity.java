package com.example.andrea.myrunner2;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/*

This class allows us to choose what information we want to access, then through an asyncktask,
 a thread working in the background makes a get to a server that allows us to download the json file
 */


public class MenuActivity extends ListActivity {
    ListView list;
    ProgressDialog pd;
    String txtJson;
    //public static  final String my_url ="http://192.168.1.95/";

    public static final String my_url = "http://10.200.126.71/";
    String my_url2 ="gare.txt";
    String[] items = {
            "Elenco Soci",
            "Prossime Gare",
            "Risultati",
    } ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        list = (ListView)getListView();

        //this method lets us lounch the AsyncTask
        startThread(my_url, my_url2);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        list.setAdapter(adapter);
        Bundle extras = getIntent().getExtras();
        final String mail = extras.getString("email");
        System.out.println(mail);
        final String pass = extras.getString("password");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> av, View v,
                                              int position, long id) {
                String text = "Position: " + position +
                        "\nData: " + items[position];

                //With this switch wis made about the name that we can see to the display
                switch (text.substring(18))
                //let us to take a substring of text

                {
                    case "Elenco Soci":
                        break;
                    case "Prossime Gare":
                        //this is the only case we had to implement for our project
                        Intent intent2 = new Intent(MenuActivity.this, GareActivity.class);
                        intent2.putExtra("mail",mail);
                        intent2.putExtra("pass",pass);
                        intent2.putExtra("stringaJSON",txtJson);
                        startActivity(intent2);
                        break;

                    case "Risultati":
                        break;


                }
            }

        });
    }

    /*
    This class let us to download a json
     */
    public class MyFetcherJsonFunction extends AsyncTask<String,String,String > {




        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MenuActivity.this);
            pd.setMessage("Please wait");

            pd.setCancelable(false);
            pd.show();
        }
        protected String doInBackground(String... params) {



            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                //we use doInBackground reate the connection throws  two params the IP+package(which is decode in UTF-8)
                String url0 = params[0] + java.net.URLEncoder.encode(params[1], "UTF-8");

                //we create now the url and we try to openConnection,after that we use a InputStream which is like a buffer
                // where we can pass the data of the file ,line to line
                java.net.URL url = new java.net.URL(url0);
                System.out.println(url);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);

                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            txtJson =  result;

        }
    }
    //this let us to launch the class of AsyncTask
    public void startThread(String text,String text1){
        new MyFetcherJsonFunction().execute(text, text1);
    }
}
