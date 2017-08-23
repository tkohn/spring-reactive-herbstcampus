
# Reaktive Programmierung 
## mit Java und Spring

+++

## Torsten Kohn
### Lean Java Expert
### bei comSysto in München
<ul class="hide-list-style-type">
  <li><i class="fa fa-twitter-square"></i> <a target="_blank" href="https://twitter.com/TorstenKohn">@TorstenKohn</a></li>
  <li><i class="fa fa-github-square"></i> <a target="_blank" href="https://github.com/tkohn">tkohn</a></li>
  <li><i class="fa fa-xing-square"></i> <a target="_blank" href="https://www.xing.com/profile/Torsten_Kohn4">Torsten_Kohn4</a></li>
  <li><i class="fa fa-building-o"></i> <a target="_blank" href="https://comsysto.com/">comSysto</a></li>
</ul>
+++

### Inhalt

* Einführung
 * Was ist Reaktive Programmierung?
 * Vor- und Nachteile
 * Implementierungen
* Code-Beispiele mit Reactor
* Demo - Reactive Programmierung mit Spring

---

# Einführung

+++

## Was ist Reaktive Programmierung?

> Reactive programming is oriented around data flows and the propagation of change. 
> This means that the underlying execution model will automatically propagate changes through the data flow.

<ul>
    <li class="fragment">Programmierparadigma</li>
    <li class="fragment">Pionierarbeit: Reactive Extensions (Rx) für .NET</li>
    <li class="fragment">Standardisierung für JVM durch <a target="_blank" href="https://github.com/reactive-streams/reactive-streams-jvm">Reactive Streams</a></li>
</ul>

Note:
Programmierparadigma, welches sich an Datenflüsse orientiert.
Änderungen in den Datenflüssen automatisch propagiert.
Beispiel: Excel - Wert in Zelle ändert sich und Summenzelle wird neu berechnet
Observer pattern + Iterator pattern + functional programming = reactive programming
Source: https://projectreactor.io/docs/core/release/reference/#intro-reactive

+++

## [Reactive Streams](https://github.com/reactive-streams/reactive-streams-jvm) Spezifikation
### Interfaces

- Publisher
- Subscriber
- Subscription
- Processor

Note:
Zusätzlich gibt es Implementierungsrichtlinien.
RxJava, Reactor und Java 9 halten sich an der Spezifikation

+++

## Buzzwords

- Backpressure
- hot & cold Publisher

Note:
Backpressure
 - PUSH: Publisher schickt schneller Daten als der Subscriber Verarbeiten kann -> Subscriber informiert Publisher darüber 
hot vs cold
- cold: Eine 'cold'-Sequenz startet immer einen neuen Subscriber mit den Daten
-  hot: Bei 'hot' erhölt man die Daten ab dem Punkt an dem man subscribed, man bekommt daher nicht alle Daten mit

+++

### Vergleich Implementierungen

<table>
  <tr>
    <th></th>
    <th>RxJava 2</th>
    <th>Java 9 Flow</th>
    <th>Reactor 3</th> 
  </tr>
  <tr class="fragment">
    <td>Java</td>
    <td>6+</td>
    <td>9+</td>
    <td>8+</td>
  </tr>
  <tr class="fragment">
    <td>Publisher</td>
    <td>Flowable, Observable, Single, Maybe, Completable</td>
    <td>selbst implementieren</td>
    <td>Mono, Flux</td>
   </tr>
  <tr class="fragment">
    <td>Subscriber</td>
    <td>...</td>
    <td>selbst implementieren</td>
    <td>viele vorhanden</td>
   </tr>
</table>

Note:
...

---

### Code-Beispiele mit Reactor

+++

### Erzeugen von Publisher

---?code=playground/src/test/java/de/torstenkohn/herbstcampus/playground/FluxAndMonoCreateTests.java

@[58](Mono erzeugen)
@[52](Mono ohne Inhalt erzeugen)
@[44-45](Flux erzeugen)
@[33](Flux als Bereich erzeugen)
@[24-26](Flux mit Iterable erzeugen)

+++?code=playground/src/test/java/de/torstenkohn/herbstcampus/playground/FluxAndMonoSubscribeTest.java

### Interaktion - Subscribe

@[14-20]
@[26-36]
@[42-62]

+++

### Was passiert im Fehlerfall

---

## Reaktive Programmierung 
### mit Java und Spring

- Spring Framework von Pivotal |
- Open Source - Apache License |
- nutzt Reactor |

+++

### Demo

+++

### Container Voraussetzungen

ab Servlet Version 3.1

- Tomcat
- Jetty
- Netty
- Undertow

+++

### Datenbanken Voraussetzungen

- MongoDB
- Apache Cassandra
- Redis

Note:
JDBC soll evtl. in einer reaktiven Version kommen

---

# Fazit

Note:
TODO - Zusammenfassung

+++

### Wann lohnt sich der Einsatz?

- Technologien entscheiden
- Wissen der Mitarbeiter

Note:
Wenn an unterester Stelle eine reaktive Komponente (MongoDB) verwendet wird, 
dann sollte man darauf aufbauen und reaktiv Entwickeln

+++

## Quellen

* [Reactor 3 Reference Guide](https://projectreactor.io/docs/core/release/reference/)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [JDK 9 Flow API](https://community.oracle.com/docs/DOC-1006738)
* [InfoQ - Reactor by Example](https://www.infoq.com/articles/reactor-by-example)
* [Devoxx - Video: Reactive Spring](https://www.youtube.com/watch?v=TZUZgU6rsNY)
* [Spring - Notes on Reactive Programming](https://spring.io/blog/2016/06/07/notes-on-reactive-programming-part-i-the-reactive-landscape)

