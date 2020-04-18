package com.example.lombi.cf; /**
 * Created by lombi on 21/06/18.
 */



import static com.example.lombi.cf.Utilis.*;

public class CodiceFiscale {

    private String nome;
    private String cognome;
    private int giorno;
    private int mese;
    private int anno;
    private String sesso;
    private String comuneDiNascita;


    public CodiceFiscale(String nome, String cognome, int giorno, int mese, int anno, String sesso, String comuneDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
        this.sesso = sesso;
        this.comuneDiNascita = comuneDiNascita;

    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCognome() {
        return cognome;
    }


    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public int getMese() {
        return mese;
    }


    public void setMese(int mese) {
        this.mese = mese;
    }


    public int getAnno() {
        return anno;
    }


    public void setAnno(int anno) {
        this.anno = anno;
    }


    public String getSesso() {
        return sesso;
    }


    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getComuneDiNascita() {
        return comuneDiNascita;
    }


    public void setComuneDiNascita(String comuneDiNascita) {
        this.comuneDiNascita = comuneDiNascita;
    }


    public String calcola() throws Exception {
        String codiceCognome = this.calcolaCodiceCognome(this.cognome);
        String codiceNome = this.calcolaCodiceNome(this.nome);
        String codiceDataNascitaESesso = this.calcolaCodiceDataNascitaESesso(this.anno, this.mese, this.giorno, this.sesso);

        String risultato = codiceCognome + codiceNome + codiceDataNascitaESesso;

        String carattereDiControllo = this.calcolaCarattereDiControllo(risultato);

        risultato += carattereDiControllo;

        return risultato;
    }


    private String calcolaCodiceCognome(String cognome) {
        String codiceCognome;
        int numeroConsonanti;
        cognome = cognome.toUpperCase();

        if (cognome.length() >= 3) {

            numeroConsonanti = getNumeroConsonanti(cognome);

            if (numeroConsonanti >= 3) {

                codiceCognome = getPrimeConsonanti(cognome, 3);
            } else {
                if (BuildConfig.DEBUG) {

                }
                codiceCognome = getPrimeConsonanti(cognome, numeroConsonanti);
                codiceCognome += getPrimeVocali(cognome, 3 - numeroConsonanti);
            }
        } else {
            if (BuildConfig.DEBUG) {

            }
            int numeroCaratteri = cognome.length();
            codiceCognome = cognome + nXChar(3 - numeroCaratteri);
        }


        return codiceCognome;
    }


    private String calcolaCodiceNome(String nome) {
        String codiceNome;
        int numeroConsonanti;
        nome = cognome.toUpperCase();

        if (nome.length() >= 3) {

            numeroConsonanti = getNumeroConsonanti(nome);

            if (numeroConsonanti >= 4) {
                codiceNome = getConsonanteI(nome, 1) + getConsonanteI(nome, 3) + getConsonanteI(nome, 4);
            } else if (numeroConsonanti >= 3) {

                codiceNome = getPrimeConsonanti(nome, 3);
            } else {

                codiceNome = getPrimeConsonanti(nome, numeroConsonanti);
                codiceNome += getPrimeVocali(nome, 3 - numeroConsonanti);
            }
        } else {

            int numeroCaratteri = nome.length();
            codiceNome = nome + nXChar(3 - numeroCaratteri);
        }


        return codiceNome;
    }


    private String calcolaCodiceDataNascitaESesso(int anno, int mese, int giorno, String sesso) {
        String codiceDataNascitaESesso;
        String codiceAnno;
        String codiceMese;
        String codiceGiornoESesso;

        codiceAnno = calcolaCodiceAnno(anno);
        codiceMese = calcolaCodiceMese(mese);
        codiceGiornoESesso = calcolaCodiceGiornoESesso(giorno, sesso);

        codiceDataNascitaESesso = codiceAnno + codiceMese + codiceGiornoESesso;

        return codiceDataNascitaESesso;
    }


    private String calcolaCodiceAnno(int anno) {
        return Integer.toString(anno).substring(2);
    }


    private String calcolaCodiceMese(int mese) {
        String risultato;
        mese++; //I mesi iniziano da 1
        switch (mese) {
            case 1:
                risultato = "A";
                break;
            case 2:
                risultato = "B";
                break;
            case 3:
                risultato = "C";
                break;
            case 4:
                risultato = "D";
                break;
            case 5:
                risultato = "E";
                break;
            case 6:
                risultato = "H";
                break;
            case 7:
                risultato = "L";
                break;
            case 8:
                risultato = "M";
                break;
            case 9:
                risultato = "P";
                break;
            case 10:
                risultato = "R";
                break;
            case 11:
                risultato = "S";
                break;
            case 12:
                risultato = "T";
                break;
            default:
                risultato = "";
                break;
        }
        return risultato;
    }


    private String calcolaCodiceGiornoESesso(int giorno, String sesso) {
        String codiceGiorno = String.format("%02d", giorno);

        if (sesso.equals("F")) {
            int codiceGiornoIntero;
            codiceGiornoIntero = Integer.parseInt(codiceGiorno);
            codiceGiornoIntero += 40;
            codiceGiorno = Integer.toString(codiceGiornoIntero);
        }

        return codiceGiorno;
    }


    private String calcolaCarattereDiControllo(String codice) throws Exception {

        //Passaggio 1 (suddivisione dispari e pari)
        String pari = getStringaPari(codice);
        String dispari = getStringaDispari(codice);

        //Passaggio 2 (conversione valori)
        int sommaDispari = conversioneCaratteriDispari(dispari);
        int sommaPari = conversioneCaratteriPari(pari);

        //Passaggio 3 (somma, divisione e conversione finale)
        int somma = sommaDispari + sommaPari;
        int resto = (int) somma % 26;


        return Integer.toString(resto);
    }

    private int conversioneCaratteriDispari(String string) {
        int risultato = 0;
        for (int i = 0; i < string.length(); i++) {
            char carattere = string.charAt(i);
            switch (carattere) {
                case '0':
                case 'A':
                    risultato += 1;
                    break;
                case '1':
                case 'B':
                    risultato += 0;
                    break;
                case '2':
                case 'C':
                    risultato += 5;
                    break;
                case '3':
                case 'D':
                    risultato += 7;
                    break;
                case '4':
                case 'E':
                    risultato += 9;
                    break;
                case '5':
                case 'F':
                    risultato += 13;
                    break;
                case '6':
                case 'G':
                    risultato += 15;
                    break;
                case '7':
                case 'H':
                    risultato += 17;
                    break;
                case '8':
                case 'I':
                    risultato += 19;
                    break;
                case '9':
                case 'J':
                    risultato += 21;
                    break;
                case 'K':
                    risultato += 2;
                    break;
                case 'L':
                    risultato += 4;
                    break;
                case 'M':
                    risultato += 18;
                    break;
                case 'N':
                    risultato += 20;
                    break;
                case 'O':
                    risultato += 11;
                    break;
                case 'P':
                    risultato += 3;
                    break;
                case 'Q':
                    risultato += 6;
                    break;
                case 'R':
                    risultato += 8;
                    break;
                case 'S':
                    risultato += 12;
                    break;
                case 'T':
                    risultato += 14;
                    break;
                case 'U':
                    risultato += 16;
                    break;
                case 'V':
                    risultato += 10;
                    break;
                case 'W':
                    risultato += 22;
                    break;
                case 'X':
                    risultato += 25;
                    break;
                case 'Y':
                    risultato += 24;
                    break;
                case 'Z':
                    risultato += 23;
                    break;
            }
        }
        return risultato;
    }


    private int conversioneCaratteriPari(String string) {
        int risultato = 0;
        for (int i = 0; i < string.length(); i++) {
            char carattere = string.charAt(i);
            int numero = Character.getNumericValue(carattere);

            if (Character.isLetter(carattere)) {
                //Se è una lettera
                numero = carattere - 65;
                risultato += numero;
            } else {
                //Se è un numero
                risultato += numero;
            }


            return risultato;
        }
    return risultato;
    }
}
