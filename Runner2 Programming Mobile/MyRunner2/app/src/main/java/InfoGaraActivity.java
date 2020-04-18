package com.example.andrea.myrunner2;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//this activity is to show some more informations about a race: a big picture, an url, an expiration date and a note
public class InfoGaraActivity extends Activity {
    Button submit;
    String nome,data,località,immagine,urlxx,scadenza,commento,result;
    int distanza,id;
    ImageView imgview;
    TextView t1;
    TextView t2;
    TextView t3;
    public static final String my_url = "http://10.200.126.71/";
    String my_url2 ="Iscrizioni.JSON";

    Context context;

    public static String content;
    public String mail;
    public String pass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schermata_gara);
        submit=(Button)findViewById(R.id.buttonSubmit);
        t1 = (TextView)findViewById(R.id.textViewUrl);
        t2 = (TextView)findViewById(R.id.textViewData);
        t3 = (TextView)findViewById(R.id.textViewCommento);

        Bundle extras = getIntent().getExtras();
        mail = extras.getString("usermail");
        System.out.println(mail);
        pass = extras.getString("password");

        if(extras!=null){
            int message8=extras.getInt("distanza");
            String message7=extras.getString("data");
            String message6=extras.getString("nome");
            int message5=extras.getInt("id");
            String message4= extras.getString("localita");
            String messageImmagine = extras.getString("immagine");
            String message1 = extras.getString("url");
            String message2 = extras.getString("scadenza");
            String message3 = extras.getString("commento");
            nome=message6;
            distanza=message8;
            data=message7;
            id=message5;
            località=message4;
            immagine=messageImmagine;
            urlxx=message1;
            scadenza=message2;
            commento=    message3;
            new ScaricaImmagineTask ().execute(messageImmagine);
            t1.setText(message1);
            t2.setText(message2);
            t3.setText(message3);
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonPostRequest j=new JsonPostRequest();
                j.execute();
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoGaraActivity.this);
                builder.setMessage(getString(R.string.stringaConferma) + ":\n" + Html.fromHtml("<center>" + getString(R.string.stringaconfermaurl)+my_url+ my_url2 + "</center>"))
                        .setCancelable(false)
                        .setPositiveButton(R.string.confermapost,
                                new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(my_url+ my_url2));
                                startActivity(viewIntent);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

    }
    //this class is used to send user and race informations to a json file with a POST request.
    private class JsonPostRequest extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://10.200.126.71/server.php");

            try {
                // Add your data

                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("email", mail);
                    jsonParam.put("password", pass);
                    jsonParam.put("idrace", id);

                    jsonParam.put("Description", nome);
                    jsonParam.put("Location", località);
                    jsonParam.put("Distance", distanza);
                    jsonParam.put("Date", data);
                    jsonParam.put("Before", scadenza);
                    jsonParam.put("url", urlxx);
                    jsonParam.put("Note", commento);
                    jsonParam.put("picture", immagine);
                    String requestBody = jsonParam.toString();



                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(11);
                    //nameValuePairs.add(new BasicNameValuePair("frmName", requestBody));

                    nameValuePairs.add(new BasicNameValuePair("email",mail ));
                    nameValuePairs.add(new BasicNameValuePair("password", pass));
                    nameValuePairs.add(new BasicNameValuePair("idrace", Integer.toString(id)));
                    nameValuePairs.add(new BasicNameValuePair("Description",nome ));
                    nameValuePairs.add(new BasicNameValuePair("Location", località));
                    nameValuePairs.add(new BasicNameValuePair("Distance", Integer.toString(distanza)));
                    nameValuePairs.add(new BasicNameValuePair("Date", data));
                    nameValuePairs.add(new BasicNameValuePair("Before", scadenza));
                    nameValuePairs.add(new BasicNameValuePair("url", urlxx));
                    nameValuePairs.add(new BasicNameValuePair("Note", commento));
                    nameValuePairs.add(new BasicNameValuePair("picture", immagine));


                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    // Execute HTTP Post Request
                    HttpResponse response = httpclient.execute(httppost);
                    Log.v("Post Status", "Code: " + response.getStatusLine().getStatusCode());
                }catch (JSONException e){
                    e.printStackTrace();
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
            return null;
        }

    }
    //this class opens an http session to download the picture associated to a specific url
    private InputStream ApriConnessioneHttp(String urlString) throws IOException
    {
        InputStream in = null;
        int risposta = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException(getString(R.string.noHttp));

        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            risposta = httpConn.getResponseCode();

            if (risposta == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex) {
            Log.d("Connessione", ex.getLocalizedMessage());
            throw new IOException(getString(R.string.errorconnection));
        }
        return in;
    }
    //downloads the picture and returns a Bitmap
    private Bitmap ScaricaImmagine(String URL)
    {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = ApriConnessioneHttp(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        }
        catch (IOException e1) {
            Log.d("Servizio web", e1.getLocalizedMessage());
        }
        return bitmap;
    }
    //set the ImageView with the picture downloaded from the web
    private class ScaricaImmagineTask extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            return ScaricaImmagine(urls[0]);
        }
        protected void onPostExecute(Bitmap result) {
            ImageView img = (ImageView) findViewById(R.id.imageGara);
            img.setImageBitmap(result);
        }
    }





}