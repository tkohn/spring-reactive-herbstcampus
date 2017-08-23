
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
</ul>
+++

### Inhalt

* Einleitung
 * Was ist Reaktive Programmierung?
 * Vor- und Nachteile
 * Implementierungen
* Code-Beispiele mit Reactor
* Demo - Reactive Programmierung mit Spring

---

### Einleitung

+++

### Was ist Reaktive Programmierung?

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
- Implementierungsrichtlinien

+++

### Vergleich Implementierungen

<table>
  <tr>
    <th></th>
    <th>RxJava 2</th>
    <th>Java 9 Flow</th>
    <th>Reactor 3</th> 
  </tr>
  <tr>
    <td>Java</td>
    <td>6+</td>
    <td>9+</td>
    <td>8+</td>
  </tr>
  <tr>
    <td>Publisher</td>
    <td>Flowable, Observable, Single, Maybe, Completable</td>
    <td>implement</td>
    <td>Mono, Flux</td>
   </tr>
  <tr>
    <td>Subscriber</td>
    <td>...</td>
    <td>implement</td>
    <td>...</td>
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

+++

### Interaktion - Subscribe

+++

### Was passiert im Fehlerfall

---

### Reaktive Programmierung mit Spring

+++

### Container

ab Servlet Version 3.1

- Tomcat
- Jetty
- Netty
- Undertow

+++

### Datenbanken 

- MongoDB
- Apache Cassandra
- Redis
