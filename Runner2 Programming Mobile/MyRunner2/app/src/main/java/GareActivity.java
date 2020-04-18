package com.example.andrea.myrunner2;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



import org.json.JSONArray;
import java.util.ArrayList;


/*
We use this java class to describe the third activity in which we have one ListView that is updated by the CustomAdapter,this let us store inside of the ListView the picture and others note
 */
public class GareActivity extends AppCompatActivity {

    public static final String my_url = "http://10.200.126.71/";
    //public static final String my_url="http://192.168.1.95/";

    ListView simpleList;

    String txtJSON;
    String mail;
    String pass;
    String Item[] = {"STRALIVIGNO", "MONTE ROSA EST HIMALAYAN TRAIL", "ULTRAMARATONA DEL GRAN SASSO", "TRE CIME EXPERIENCE", "MARATONA DEL MUGELLO", "ROME HALF MARATHON VIA PACIS", "MEZZA MARATONA DEI CASTELLI ROMANI", "SORRENTO POSITANO ULTRAMARATONA"};
    String SubItem[] = {"Data: 2018/07/21 15:00, Località: Livigno(SO), Distanza: 21000 m ",
            "Data: 2018/07/29 04:00, Località: Macugnaga (VCO), Distanza: 60000 m ",
            "Data: 2018/07/29 09:30, Località: Santo Stefano di Sessanio (AQ), Distanza: 60000 m ",
            "Data: 2018/09/09 08:00, Località: Misurina (BL), Distanza: 42000 m ",
            "Data: 2018/09/22 14:30 15:00, Località: Borgo San Lorenzo (FI), Distanza: 42195 m ",
            "Data: 2018/09/23 09:00, Località: ROMA (RM), Distanza: 21097 m ",
            "Data: 2018/10/07 09:30, Località: ALBANO LAZIALE (RM), Distanza: 20000 m ",
            "Data: 2018/12/02 07:00, Località: SORRENTO(NA) , Distanza: 54000 m ",};
    int flags[] = {R.drawable.stralivigno, R.drawable.monterosa, R.drawable.ultramaratonagransasso, R.drawable.trecime, R.drawable.mugello, R.drawable.roma, R.drawable.castelli, R.drawable.costiera};
    // Make sure flags images are stored in drawable folder present inside res folder with correct naming.
    public static ArrayList<Gara> gare = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gare_layout);

        simpleList = (ListView) findViewById(R.id.ListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), Item, SubItem, flags);
        simpleList.setAdapter(customAdapter);
        Bundle extras = getIntent().getExtras();
        mail = extras.getString("mail");
        pass = extras.getString("pass");
        System.out.println(mail);
        txtJSON =  extras.getString("stringaJSON");


        try {



            JSONArray array = new JSONArray(txtJSON);
            //WE PARSE THE INFORMATION OF JSONOBJECT INTO GARE CLASS
            for(int i=0;i<array.length();i++){

                Gara gara = new Gara();
                int id = array.getJSONObject(i).getInt("idrace");
                String nome = array.getJSONObject(i).getString("Description");
                int distanza = array.getJSONObject(i).getInt("Distance");
                String localita = array.getJSONObject(i).getString("Location");
                String scadenza = array.getJSONObject(i).getString("Before");
                String data = array.getJSONObject(i).getString("Date");
                String urlgara = array.getJSONObject(i).getString("url");
                String commento = array.getJSONObject(i).getString("note");
                String urlimmagine = array.getJSONObject(i).getString("picture");
                gara.setId(id);
                gara.setCommento(commento);
                gara.setData(data);
                gara.setDistanza(distanza);
                gara.setLocalita(localita);
                gara.setNome(nome);
                gara.setPicture(urlimmagine);
                gara.setScadenzaIscrizione(scadenza);
                gara.setUrlGara(urlgara);
                gare.add(gara);


            }
            System.out.println(gare);
            simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                //ACCORDING TO THE ELEMENT OF THE LISTVIEW THAT WE SELECT WITH A CLICK, WE MOVE TO THE PREVIOUS ACTIVITY WITH INFORMATION THAT DEPENDS ABOUT RACE CHOICE
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    //THIS HAS BEEN MADE THROUGH THE VALUE OF THE ADAPTER WHICH WANTS TO ITS INTERNAL IN ITS
                    // INITIALIZATION THE VALUE POSITION THAT WE HAVE EXPLOITED IN THE SWITCH
                    switch (position) {
                        //IN ACCORDANCE WITH THE VALUE PRESENT, WE SEND WITH "EXTRA" THE INFORMATION TO THE NEXT ACTIVITY.
                        // THESE INFORMATIONS ARE RECAPITED FROM THE ARRAY OF RACES
                        case 0:

                            Gara g1 = gare.get(0);
                            Intent intent = new Intent(GareActivity.this, InfoGaraActivity.class);
                            intent.putExtra("usermail",mail);
                            intent.putExtra("password",pass);
                            intent.putExtra("id",g1.getId());
                            intent.putExtra("commento",g1.getCommento());
                            intent.putExtra("data",g1.getData());
                            intent.putExtra("distanza",g1.getDistanza());
                            intent.putExtra("localita",g1.getLocalita());
                            intent.putExtra("nome",g1.getNome());
                            intent.putExtra("scadenza",g1.getScadenzaIscrizione());
                            intent.putExtra("url",g1.getUrlGara());
                            intent.putExtra("immagine",g1.getPicture());
                            startActivity(intent);

                            break;
                        case 1:
                            Gara g2 = gare.get(1);

                            Intent intent1 = new Intent(GareActivity.this, InfoGaraActivity.class);
                            intent1.putExtra("usermail",mail);
                            intent1.putExtra("password",pass);
                            intent1.putExtra("id",g2.getId());
                            intent1.putExtra("commento",g2.getCommento());
                            intent1.putExtra("data",g2.getData());
                            intent1.putExtra("distanza",g2.getDistanza());
                            intent1.putExtra("localita",g2.getLocalita());
                            intent1.putExtra("nome",g2.getNome());
                            intent1.putExtra("scadenza",g2.getScadenzaIscrizione());
                            intent1.putExtra("url",g2.getUrlGara());
                            intent1.putExtra("immagine",g2.getPicture());
                            startActivity(intent1);
                            break;
                        case 2:
                            Gara g3 = gare.get(2);
                            Intent intent2 = new Intent(GareActivity.this, InfoGaraActivity.class);
                            intent2.putExtra("usermail",mail);
                            intent2.putExtra("password",pass);
                            intent2.putExtra("id",g3.getId());
                            intent2.putExtra("commento",g3.getCommento());
                            intent2.putExtra("data",g3.getData());
                            intent2.putExtra("distanza",g3.getDistanza());
                            intent2.putExtra("localita",g3.getLocalita());
                            intent2.putExtra("nome",g3.getNome());
                            intent2.putExtra("scadenza",g3.getScadenzaIscrizione());
                            intent2.putExtra("url",g3.getUrlGara());
                            intent2.putExtra("immagine",g3.getPicture());
                            startActivity(intent2);
                            break;
                        case 3:
                            Gara g4 = gare.get(3);
                            Intent intent3 = new Intent(GareActivity.this, InfoGaraActivity.class);
                            intent3.putExtra("usermail",mail);
                            intent3.putExtra("password",pass);
                            intent3.putExtra("id",g4.getId());
                            intent3.putExtra("commento",g4.getCommento());
                            intent3.putExtra("data",g4.getData());
                            intent3.putExtra("distanza",g4.getDistanza());
                            intent3.putExtra("localita",g4.getLocalita());
                            intent3.putExtra("nome",g4.getNome());
                            intent3.putExtra("scadenza",g4.getScadenzaIscrizione());
                            intent3.putExtra("url",g4.getUrlGara());
                            intent3.putExtra("immagine",g4.getPicture());
                            startActivity(intent3);
                            break;
                        case 4:
                            Gara g5 = gare.get(4);
                            Intent intent4 = new Intent(GareActivity.this, InfoGaraActivity.class);
                            intent4.putExtra("usermail",mail);
                            intent4.putExtra("password",pass);
                            intent4.putExtra("id",g5.getId());
                            intent4.putExtra("commento",g5.getCommento());
                            intent4.putExtra("data",g5.getData());
                            intent4.putExtra("distanza",g5.getDistanza());
                            intent4.putExtra("localita",g5.getLocalita());
                            intent4.putExtra("nome",g5.getNome());
                            intent4.putExtra("scadenza",g5.getScadenzaIscrizione());
                            intent4.putExtra("url",g5.getUrlGara());
                            intent4.putExtra("immagine",g5.getPicture());
                            startActivity(intent4);
                            break;
                        case 5:
                            Gara g6 = gare.get(5);
                            Intent intent5 = new Intent(GareActivity.this, InfoGaraActivity.class);
                            intent5.putExtra("usermail",mail);
                            intent5.putExtra("password",pass);
                            intent5.putExtra("id",g6.getId());
                            intent5.putExtra("commento",g6.getCommento());
                            intent5.putExtra("data",g6.getData());
                            intent5.putExtra("distanza",g6.getDistanza());
                            intent5.putExtra("localita",g6.getLocalita());
                            intent5.putExtra("nome",g6.getNome());
                            intent5.putExtra("scadenza",g6.getScadenzaIscrizione());
                            intent5.putExtra("url",g6.getUrlGara());
                            intent5.putExtra("immagine",g6.getPicture());
                            startActivity(intent5);
                            break;
                        case 6:
                            Gara g7 = gare.get(6);
                            Intent intent6 = new Intent(GareActivity.this, InfoGaraActivity.class);
                            intent6.putExtra("usermail",mail);
                            intent6.putExtra("password",pass);
                            intent6.putExtra("id",g7.getId());
                            intent6.putExtra("commento",g7.getCommento());
                            intent6.putExtra("data",g7.getData());
                            intent6.putExtra("distanza",g7.getDistanza());
                            intent6.putExtra("localita",g7.getLocalita());
                            intent6.putExtra("nome",g7.getNome());
                            intent6.putExtra("scadenza",g7.getScadenzaIscrizione());
                            intent6.putExtra("url",g7.getUrlGara());
                            intent6.putExtra("immagine",g7.getPicture());
                            startActivity(intent6);
                            break;
                        case 7:
                            Gara g8 = gare.get(7);
                            Intent intent7 = new Intent(GareActivity.this, InfoGaraActivity.class);
                            intent7.putExtra("usermail",mail);
                            intent7.putExtra("password",pass);
                            intent7.putExtra("id",g8.getId());
                            intent7.putExtra("commento",g8.getCommento());
                            intent7.putExtra("data",g8.getData());
                            intent7.putExtra("distanza",g8.getDistanza());
                            intent7.putExtra("localita",g8.getLocalita());
                            intent7.putExtra("nome",g8.getNome());
                            intent7.putExtra("scadenza",g8.getScadenzaIscrizione());
                            intent7.putExtra("url",g8.getUrlGara());
                            intent7.putExtra("immagine",g8.getPicture());
                            startActivity(intent7);
                            break;
                    }

                }

                ;

            });
        }
        catch (Exception e){

            e.printStackTrace();
        }

    }



}