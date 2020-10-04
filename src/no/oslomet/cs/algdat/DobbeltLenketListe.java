package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
       // throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {

        Objects.requireNonNull(a,"Tabellen er null");
        for (int i=0; i<a.length;i++){
            // hvis verdi er ikke null gå videre
            if(a[i]!=null){
                Node ny_node = new Node(a[i]);

                // hvis den lenked list er tom, så lag en ny node som hode
                if (hode==null){
                    hode=ny_node;
                    hale = ny_node;
                }else {
                    hale.neste=ny_node;
                    ny_node.forrige=hale;
                    hale=ny_node;
                }
                endringer++;
                antall++;

            }
        }
    }

    public Liste<T> subliste(int fra, int til){
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
       if(antall==0){
           return true;
       }
       else return false;
    }

    @Override
    public boolean leggInn(T verdi) {
        // kaster exception ved null verdier
        Objects.requireNonNull(verdi, "Tabellen er null!");
        Node node = new Node(verdi);
        if(hode == null) {
            hode = node;
            hale = hode;
        } else {
            hale.neste = node;
            node.forrige = hale;
            hale = node;
        }
        endringer++;
        antall++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    // Oppgave 2
    @Override
    public String toString() {
        // lager ny StringBuilder object og setter
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        // henter verdiene til alle node og setter dem in stringbuilder object hvis de ikke er tom
        if (antall > 0){
            Node <T> nyN = hode;
            while (nyN !=null){
                builder.append(nyN.verdi);
                builder.append(", ");
                nyN = nyN.neste;
            }
            // slett siste komma fra builder objektet
            builder.delete(builder.length() - 1, builder.length());
        }
        //slett mellomrom før man legger til ] hvis og bare hvis den koden  gikk in while  loopen og la til elementer
        if(builder.length()>1){
            builder.delete(builder.length() - 1, builder.length());
        }
        // ellers legg til ]
        builder.append("]");
        return builder.toString();
    }

    public String omvendtString() {
        // akkurat samme metode som toString men bare baklengs starter på hode, vi går fra hale
        // lager ny StringBuilder object og setter
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        // henter verdiene til alle node og setter dem in stringbuilder object hvis de ikke er tom
        if (antall > 0){
            Node <T> nyN = hale;
            while (nyN !=null){
                builder.append(nyN.verdi);
                builder.append(", ");
                nyN = nyN.forrige;
            }
            // slett siste komma fra builder objektet
            builder.delete(builder.length() - 1, builder.length());
        }
        //slett mellomrom før man legger til ] hvis og bare hvis den koden  gikk in while  loopen og la til elementer
        if(builder.length()>1){
            builder.delete(builder.length() - 1, builder.length());
        }
        builder.append("]");
        return builder.toString();
    }

    // Oppgave 3A
    // private methode for å finne tilhørende node til en indeks
    private Node<T> finnNode(int indeks){
        // hvis indeks er mindre enn anntall / 2 så skal letting start fra hode og mot høyre ellers motsatt
        if(indeks <antall /2 ){
            Node <T> node = hode;
            int teller = 0;
            while (node!=null){
                // når teller er like indeks har vi funnet noden
                if (teller==indeks){
                    return node;
                }
                // øke teller
                teller++;
                // peke til neste node
                node =node.neste;

            }
        }else {
            // Nå gjør vi bare det motsatte av forigge if koden og starter fra hale
            // går mot venstre
            Node <T> node = hale;
            int teller = 0;
            while (node!=null){
                // når teller er like indeks har vi funnet noden
                if (teller==indeks){
                    return node;
                }
                // øke teller
                teller++;
                // peke til neste node
                node =node.forrige;

            }


        }
        return null;
    }

        /*public void indeksKontroll (int indeks){

    }*/


    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


