package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

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
        // sjekker om tabellen er null
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

    public List<T> subliste(int fra, int til){
        // lager en blank liste
        List<T> nodeList = new ArrayList<>();
        // metode for å validere fra og til
        fratilKontrol(fra, til); //sjekker om de indeksene er lovlig

        int teller = 1;
        Node<T> Nyn = hode;
        // traverse alle nodene
        while (Nyn != null) {
            // hvis teller er større enn fra og til eller like til
            // lage verdi i list
            if (teller > fra && teller <= til) {
                nodeList.add(Nyn.verdi);
            }
            // øker teller
            teller++;
            Nyn = Nyn.neste;
        }
        // return list
        return  nodeList;
    }
    // valider fra og til indeks for sublist metoden
    private void fratilKontrol(int fra, int til) {
        //  kast IndexOutOfBoundsException hvis  fra er negative
        if (fra < 0) {
            throw new IndexOutOfBoundsException("fra kan ikke være negative");
        }
        // kast unntakk hvis fra er større enn til
        if (fra > til) {
            throw new IllegalArgumentException("fra " + fra + " kan ikke være større enn " + til);
        }
        // kast unntak hvis fr er støørre en list lengde
        if (fra > antall) {
            throw new IndexOutOfBoundsException("indeks " + fra + " er ikke valid");
        }
        // kast unntakk hvis til er større enn antall
        if (til > antall) {
            throw new IndexOutOfBoundsException("indeks " + til + " er ikke valid");
        }
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

        // sjekk om verdi er null hvis null kast untakk
        if (verdi == null) {
            throw new NullPointerException("Verdi kan ikke være null");
        }
        // sjekker index, hvis den er negative og større enn antall kast untakk
        if (indeks < 0 || indeks > antall) {
            throw new IndexOutOfBoundsException("ugyldig verdi");
        }

        // lage ny node
        Node<T> node = new Node(verdi);
        // trenger logikken nede for å plassere verdi riktig

        // sjekker om listen er tom ved sjekke om hoden er tom
        if (hode == null) {
            // hvis sant, både hode og hale peker til sammen node
            hode = node;
            hale = hode;
        } else if (indeks == 0) {
            // hvis indeks er 0, det betyr plasser i starten av listen
            node.neste = hode;
            hode.forrige = node;
            hode = node;
        }
        else if (antall == indeks) {
            // men hvis index er like antall d.v.s lag en ny node i på slutten av listen
            hale.neste = node;
            node.forrige = hale;
            hale = node;
        }
        else {
            // hvis det forrige kravene er ikke møt, så betyr det plasser mellom to noder
            // lag ny node først
            Node<T> nyNode = hode;
            // går gjennom listen fra index 1 til en mindre indeks
            // og legger til node i mellom
            for (int count = 1; count < indeks; count++)
                nyNode = nyNode.neste;
            node.neste = nyNode.neste;
            nyNode.neste = node;
            node.forrige = nyNode;
            node.neste.forrige = node;
        }

        // legger til enderinger og antall
        endringer ++;
        antall++;





    }

    @Override
    public boolean inneholder(T verdi) {
        // sjekk om verdi finnes i listen ved bruke indeksTil.
        //Hvis ikke returnerer -1, returner true
        if (indeksTil(verdi) != -1) {
            return true;
        }
        // verdi finnes ikke return fasle
        return false;
    }

    @Override
    public T hent(int indeks) {
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {

        // teller hvilke indeks vi er på
        int indeksTeller = 0;
        // lager hode til peker
        Node<T> nyN = hode;
        // går gjennom verdier til jeg finner en peker som er har like
        while (nyN != null) {
            // hvis de verdine matcher i listen returner indeks ved indeksTeller
            if (nyN.verdi.equals(verdi)) {
                // return index
                return indeksTeller;
            }
            //  gå til neste verdi og øke teller
            nyN = nyN.neste;
            indeksTeller++;
        }
        // ikke funnet en matchende verdie returner -1
        return -1;

    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        // sjekker for null verdi og kaster unntak isåfall
        Objects.requireNonNull(nyverdi, "Tabellen er null!");

        // validerer indeks
        indeksKontroll(indeks, false);

        Node<T> nyN = hode;
        int teller = 0;
        // traverse all the node and find and update the given index
        while (nyN != null) {
            if (teller == indeks) {
                // hvis indeks matcher en posisjon, bytt den gammle node med den nye verdi
                T temp = nyN.verdi;
                nyN.verdi = nyverdi;
                endringer++;
                // returner den gammle node
                return temp;
            }
            teller++;
            nyN = nyN.neste;
        }
        // ikke funnet noe
        return null;
    }

    @Override
    public boolean fjern(T verdi) {
        /* returner false hvis verdi er null eller
        * Ellers se gjennom traverse gjennom listen og slett
        * verdi som fra listen */
        if (hode == null) {
            return false;
        } else {
            Node<T> nyn = hode;
            while (nyn != null) {
                if (nyn.verdi.equals(verdi)) {
                    del(nyn);
                    return true;
                }
                nyn = nyn.neste;
            }
        }
        return false;

    }
    // methode som betyr peker / sletter node fra listen, kalles fra fjern () metodene
    public void del(Node node1) {
        // Sjekker om noden er først , dvs sin forrige peker er null bland annet
        if(node1 == hode && hode.neste == null && hode.forrige == null) {
            // hvis sant nullstill hode og hale
            hode=null;
            hale = null;
            antall--;
        } else if(node1 == hode && hode.neste != null) {
            hode = hode.neste;
            hode.forrige = null;
            antall--;
            endringer++;
        } else if(node1 == hale && hale.forrige != null) {
            hale = hale.forrige;
            hale.neste=null;
            antall--;
            endringer++;
        } else {
            node1.forrige.neste = node1.neste;
            node1.neste.forrige = node1.forrige;
            antall--;
            endringer++;
        }
    }

    @Override
    public T fjern(int indeks) {
        fratilKontrol(indeks,antall);
        // sjekker om index er negative eller større en antall
        // kaster unntakk isåfall
        if (indeks < 0 || indeks >= antall) {
            throw new IndexOutOfBoundsException("Ugyldig index");
        }
        Node<T> current = hode;
        // hvis hode er null så kaster jeg untakk , det er ingenting å fjerne
        if(hode == null) {
            throw new IndexOutOfBoundsException("Ingnen verdi funnnet ");
        }
        // hvis index er null så betyr det slett første node
        else if (indeks == 0) {
            // lager hode i temp
            Node<T> temp = hode;
           // sender noden til sletting
            del(temp);
           /*
            hode = hode.nest
            hode.forrige = null;
            antall--;
            enderinger++;*/
            return temp.verdi;
        } else if (indeks == (antall-1)) {
            // her sletter vi bakerst
            Node<T> temp = hale;
            hale = hale.forrige;
            hale.neste = null;
            antall--;
            endringer ++;
            return temp.verdi;
        } else {
            int count = 1;
            // går gjennom listen til vi finner den angitt indeksen , når vi den så lager vi den og sletter nederst
            while(count <= indeks) {
                current = current.neste;
                count++;
            }
            current.forrige.neste = current.neste;
            current.neste.forrige = current.forrige;
            antall--;
            endringer++;
            // etter sletting legger jeg til enderinger og trekker fra antall
        }
        return current.verdi;

    }

    @Override
    public void nullstill() {
        // nullstiller listen
        Node<T> temp1 = hode;
        while (temp1 != null) {
            Node<T> temp2 = temp1.neste;
            temp1.forrige = null;
            temp1.neste = null;
            temp1.verdi = null;
            temp1 = temp2;
        }
        hode = hale = null;
        antall = 0;
        endringer++;
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
        indeksKontroll(indeks,false); // validerer index nå og kaster unntakk ved feil index

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
            int teller = antall; // fikset begynnt teller fra bakerst ikke 0!
            while (node!=null){
                // når teller er like indeks har vi funnet noden
                teller--;
                if (teller==indeks){
                    return node;
                }
                // her var det forsinkelse i koden som er fjernet  nå, trenger ikke å oke teller her.
                // peke til neste node
                node =node.forrige;

            }
        }
        return null;
    }


    @Override // denne methode returner en ny objekt av DobbeltlenketListeIterator
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        // validerer indeks
        indeksKontroll(indeks, false);
        //
        //returnerer en ny objekt av DobbeltLenketListeIterator basert på index
        return new DobbeltLenketListeIterator(indeks);
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

            iteratorendringer = endringer;
            Node<T> ptr = hode;
            int teller = 0;
            // går gjennom hver node til den er null
            while (ptr != null) {
                // hvis teller er like indeks
                if (teller == indeks) {
                    //returner peker
                    denne = ptr;
                    break;
                }
                // øk teller
                teller++;
                // pek pekern til neste node
                ptr = ptr.neste;
            }
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){

            // hvis iteratorChanges er ikke like endering kast unntakk
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("concurrent modifikasjon er ikke tillat");
            }
            // sjekker om det er ingen elementer, kast NoSuchElementException exception
            if (!hasNext()) {
                throw new NoSuchElementException("Ingen element funnet ");
            } else {
                // returnet den neste verdi
                Node<T> temp = denne;
                T value = temp.verdi;
                denne = denne.neste;
                return value;
            }
        }

        @Override
        public void remove(){
            // sjekker om nest element finnes gjennom hasNext method

            if (!hasNext()) {
                // kast NoSuchElementException unntakk hvis hasNext returnener false
                throw new NoSuchElementException("no element found ");
            }
            // slett første nod
            hode = hode.neste;
            hode.forrige = null;
            antall--;
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


