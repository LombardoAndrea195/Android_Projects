package com.example.lombi.cf;

/**
 * Created by lombi on 21/06/18.
 */

public class Utilis {
    private static final String VOCALI = "AEIOU";


    public static int getNumeroConsonanti(String string){
        int consonanti = 0;
        for(int i = 0; i < string.length(); i++){
            if(!isVocale(string.charAt(i))){
                consonanti++;
            }
        }
        return consonanti;
    }


    public static String getPrimeConsonanti(String string, int numero){
        String consonanti = "";
        for(int i = 0; i < string.length(); i++){
            if(!isVocale(string.charAt(i))){
                if(consonanti.length() < numero){
                    consonanti += Character.toString(string.charAt(i));
                }
            }
        }
        return consonanti;
    }

    public static String getConsonanteI(String string, int i){
        int contatoreConsonanti = 0;
        for(int j = 0; j < string.length(); j++){
            if(!isVocale(string.charAt(j))){
                contatoreConsonanti++;
                if(contatoreConsonanti == i){
                    return Character.toString(string.charAt(j));
                }
            }
        }
        return null;
    }




    public static String getPrimeVocali(String string, int numero){
        String vocali = "";
        for(int i = 0; i < string.length(); i++){
            if(isVocale(string.charAt(i))){
                if(vocali.length() < numero){
                    vocali += Character.toString(string.charAt(i));
                }
            }
        }
        return vocali;
    }


    public static String nXChar(int n){
        String risultato = "";
        for(int i = 0; i < n; i++){
            risultato += "X";
        }
        return risultato;
    }


    public static String eliminaSpaziBianchi(String string){
        return string.replaceAll("\\s+","");
    }



    public static boolean isVocale(char character){
        return VOCALI.contains(Character.toString(character));
    }


    public static String getStringaPari(String string){
        String risultato = "";
        for(int i = 0; i < string.length(); i++){
            if((i+1) % 2 == 0){
                risultato += Character.toString(string.charAt(i));
            }
        }
        return risultato;
    }



    public static String getStringaDispari(String string){
        String risultato = "";
        for(int i = 0; i < string.length(); i++){
            if((i+1) % 2 == 1){
                risultato += Character.toString(string.charAt(i));
            }
        }
        return risultato;
    }




}
