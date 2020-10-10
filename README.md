# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Arbeidsfordeling

Oppgaven er levert av følgende studenter:
* Hassan Mahamed Sheikh, s148134, s148134@oslomet.no
* Alene

Jeg har bruker så langt .... commites

I oppgaven har vi hatt følgende arbeidsfordeling:
* Alene / fant ikke gruppe


# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Min Tilnærming : Min kode er godt kommentert ved at hver funskjon har nok med kommenterar for min egen skyld,
jeg syns dette hjalp meg selv til holde ting ryddig og huske neste dag hvordan jeg har tenkt slike at jeg løser neste 
oppgave bedre. 

* Oppgave 1: Løste ved å implementere  Objects.requireNonNulL og en for-løkke for å plassere hver
            element fra den inkommende liste i en dobbelenkte liste. Kun ikke-null verdier plasseres av forløkken i listen
            ellers kastes en unntak og om a listen er tom kastes det untakk også. Også har vi antall() og tom () som 
            sjekker antall , antall()  returner antall mens tom() sjekker om antall 0 returner true/false.
            
* Oppgave 2: I oppgave 2 bruker jeg en StringbuilderObject også legger til [  begynellsen og ved slutten av objektet 
             legger jeg til ] og mellom dem legger verdie ved gå gjennom listen og legger til ved å implementer en if 
             og while og legger til ikke null verdier mellom de klamerer og sletter tom rom når det er nødvending.
             I omvendtString() bruker jeg samme logikken men starter fra halen og henter inn verdien omvendt. Legginn()
             legger jeg inn verdi i listen avhening om listen er tom eller ikke og kaster unntakk om verdi er tom.
             
* Oppgave 3: I finnNode() kontrollere jeg første om indeksen som er spurt er gyldig ved implentere forhånd skrevet 
             metoden indexKontroll(int x, boolean x) , hvis index er gyldig så bruker jeg en if setning for å se om jeg 
             skal begynne å søke fra venstre eller høyre siden, det gjøres ved antall/2. Så bruker en while og en teller 
             for å travasere listen og finne listen som stemmer med indeksen og returner verdien hvis ikke return null. 
             I sublisten , sjekker om indeksene er lovlig også henter hver verdie fra listen og legger den inn i ny 
             list og returner den listen. fratilKontroll () sjekker indeks og kaster unntakk om nødvendig.

* Oppgave 4: Ganske rett fram løsning har metoder her. I indexTil () Bruker jeg while løkke for gå gjennom listen og 
             bruker en bruker equalt () i if setning  for sjekke verdi finnes , samtidig som  jeg bruker en teller for
             holde rett op index løpet i listen. Hvis verdi finnes i listen så return index/teller  eller return -1.
             I  boolean Innholder() bruker jeg indexTil () for å bekrefte om index finnes ellers return false.   
                                
* Oppgave 5: I legg(indeks, verdi) methode , validere jeg først om indeks og verdi er lovelig etter kraven ellers kaster 
             jeg passende unntak. Så implenteres 2 if setning, 2 else, og 1 else samt en for løkke for finne riktig 
             plass til verdi. Sjekker blandt annet om listen tom, indeks er 0, indeks skal plassers siste, eller mellom
             to noder og så fall betyr jeg pekerne mellom nodene. Legger til enderingog antall til slutt.   

* Oppgave 6: I fjern (indeks) validerer først indeks er innenfor listen hvis ikke kaster jeg IndexOutOfBoundsException 
             og om listen er tom kastes også untakk siden er ikke noe å fjerne. Jeg har implimentert flere else if 
             for å sjekke indeksen som skal fjernes ligger bakerst , først eller et stedt i midten og fjerner noden i 
             isåfall. Og betyr pekeren hvis den ligger  et stedt i midten. Den andre methode fjern ,er gangkse rett fram
             sjekker først om hode er tom først og kaster false, ellers går jeg gjennom listen med while , hvis verdi
             finnes, slett og return true. Ellers return true! 
             
* Oppgave 8: T next(),  kaster untakk hvis iteratorforandring ikke er like endering og hvis ingen element er i listen. 
             Ellers returner verdien til "denne" og "denne" verdie flyttes til neste. I Iterator<T> iterator() returner jeg
             bare kast. Implementer konstruktør for DobbeltLenketListeIterator(indeks), går gjennom listen med while 
             og peker pekern til oppgit indeks. Og lagerer enderinger i iteratorforandringer. 
             Måte lage nullstille methoden fra oppgave 7, da den den kreves i oppgave 8 testen koden! 
             Jeg syns det er rart siden jeg jobbet alene og oppgave 7 skal ikke være nødvendig for å løse 8.
