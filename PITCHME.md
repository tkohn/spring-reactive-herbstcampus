
### Reaktive Programmierung 
#### mit Java und Spring

+++

### Torsten Kohn
##### Lean Java Expert
##### bei comSysto GmbH in München
<ul class="hide-list-style-type">
  <li><i class="fa fa-twitter-square"></i> <a target="_blank" href="https://twitter.com/TorstenKohn">@TorstenKohn</a></li>
  <i class="fa fa-github-square"></i> <a target="_blank" href="https://github.com/tkohn">tkohn</a></li>
  <i class="fa fa-xing-square"></i> <a target="_blank" href="https://www.xing.com/profile/Torsten_Kohn4">Torsten_Kohn4</a></li>
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

* Programmierparadigma
* Pionierarbeit: Reactive Extensions (Rx) Bibliothek für .NET
* Standardisierung für JVM durch Reactive Streams

> Reactive programming is oriented around data flows and the propagation of change. 
> This means that the underlying execution model will automatically propagate changes through the data flow.

Note:
Programmierparadigma, welches sich an Datenflüsse orientiert.
Änderungen in den Datenflüssen automatisch propagiert.
Beispiel: Excel - Wert in Zelle ändert sich und Summenzelle wird neu berechnet
Interfaces & Interaktionsregeln finden sich auch in Java 9 (Flow) wieder
Source: https://projectreactor.io/docs/core/release/reference/#intro-reactive

+++

### Vorteile

+++

### Nachteile

+++

### Implementierungen

<table>
  <tr>
    <th></th>
    <th>RxJava 2</th>
    <th>Java 9 Flow</th>
    <th>Reactor 3</th> 
  </tr>
  <tr>
    <td>Java Version</td>
    <td>6+</td>
    <td>9+</td>
    <td>8+</td>
  </tr>
    <tr>
      <td>Typen</td>
      <td>...</td>
      <td>...</td>
      <td>Mono, Flux</td>
    </tr>
</table>

---

### Code-Beispiele mit Reactor

+++

### Erzeugen von Publisher

---?code=playground/src/test/java/de/torstenkohn/herbstcampus/playground/FluxAndMonoCreateTests.java
@[56](Mono erzeugen)
@[50](Mono ohne Inhalt erzeugen)
@[43-44](Flux erzeugen)
@[33](Flux als Bereich erzeugen)
@[24-26](Flux mit Iterable erzeugen)

+++

### Interaktion - Subscribe

+++

### Was passiert im Fehlerfall

---

### Reaktive Programmierung mit Spring

+++

