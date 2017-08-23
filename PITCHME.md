---

### Reaktive Programmierung 
#### mit Java und Spring

+++

### Inhalt

* Vorstellung
 * Über mich
 * comSysto
* Einleitung
 * Was ist Reaktive Programmierung?
 * Vor- und Nachteile
 * Implementierungen
* Code-Beispiele mit Reactor
* Reactive Programmierung mit Spring

---

### Vorstellung

+++

### Torsten Kohn
##### Lean Java Expert
##### bei comSysto GmbH in München

* <i class="fa fa-twitter-square"></i> [@TorstenKohn](https://twitter.com/TorstenKohn)
* <i class="fa fa-github-square"></i> [tkohn](https://github.com/tkohn)
* <i class="fa fa-xing-square"></i> [Torsten_Kohn4](https://www.xing.com/profile/Torsten_Kohn4)

<img class="profile-picture" src="/assets/profil_tk.jpg" alt="Profilbild von Torsten Kohn">
<img class="profile-picture" src="/assets/logo-normal-jpg.jpg" alt="Firmenlogo von comSysto GmbH">

---

### Einleitung

+++

### Was ist Reaktive Programmierung?

* Programmierparadigma
* Pionierarbeit: Reactive Extensions (Rx) Bibliothek für .NET
* Standardisierung durch Reactive Streams:
 * Interfaces & Interaktionsregeln für die JVM

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

* RxJava
* Java 9 - Flow API
* Reactor

---

### Code-Beispiele mit Reactor

+++

### Erzeugen von Publisher

```
Mono<String> mono = Mono.just("Josh");

Mono<String> emptyMono = Mono.empty();

Flux<String> flux = Flux.just("Bryan", "Dominic");

Flux<Integer> numbersFromOneToTen = Flux.range(1, 10);

List<String> names = Arrays.asList("Bryan", "Dominic");
Flux<String> flux = Flux.fromIterable(names);
```
@[1](Mono erzeugen)
@[3](Mono ohne Inhalt erzeugen)
@[5](Flux erzeugen)
@[7](Flux als Bereich erzeugen)
@[9-10](Flux mit Iterable erzeugen)

+++

### Interaktion - Subscribe

+++

### Was passiert im Fehlerfall

---

### Reaktive Programmierung mit Spring

+++

