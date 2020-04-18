package com.example.andrea.myrunner2;



/*
This class is a model class which is used for create an object of Gara. A model class is built with a constructor
 */

public class Gara {

    private int id;//It means the identify code
    private String nome;//It means the Title name which we can identify the class Gara
    private String data;//It means the date where the event is starting
    private String scadenzaIscrizione;//It means a date by which runners an sign up
    private String localita;//It means a specific district where the race
    private int distanza;// It means how long is the distance which runners must overcome
    private String urlGara;//It let them(runners) to sign up or see information
    private String commento;// It  means the are more information about race
    private String picture;// It allow to distinguish the different races

    public Gara() {
    }

    public Gara(int id, String nome, String data, String scadenzaIscrizione, String localita, int distanza, String urlGara, String commento, String picture) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.scadenzaIscrizione = scadenzaIscrizione;
        this.localita = localita;
        this.distanza = distanza;
        this.urlGara = urlGara;
        this.commento = commento;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getScadenzaIscrizione() {
        return scadenzaIscrizione;
    }


    public String getNome(){
        return this.nome;
    }
    public String getData(){
        return this.data;
    }
    public String getLocalita(){
        return this.localita;
    }
    public int getDistanza(){
        return this.distanza;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public void setUrlGara(String urlGara) {
        this.urlGara = urlGara;
    }

    public void setScadenzaIscrizione(String scadenzaIscrizione) {
        this.scadenzaIscrizione = scadenzaIscrizione;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommento() {
        return commento;
    }

    public String getUrlGara() {
        return urlGara;
    }
}
