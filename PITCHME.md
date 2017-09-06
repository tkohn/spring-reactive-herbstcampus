
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
  <li><i class="fa fa-info-circle"></i> <a target="_blank" href="https://comsysto.com/veranstaltung/arbeiten-bei-comsysto">22.09. Tag der offenen Tür</a></li>
</ul>
+++

### Inhalt

* Einführung
 * Was ist Reaktive Programmierung?
 * Reactive Streams Spezifikation
 * Vergleich Implementierungen
* Code-Beispiele - Reactor
* Reaktive Programmierung mit Spring
 * Demo
 * Voraussetzungen
* Fazit

---

## Einführung

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

+++

### Publisher<T>

```
public void subscribe(Subscriber<? super T> s);

```

- stellt eine Anzahl von (unbegrenzten) Elementen bereit |
- Subscriber können diese Elemente konsumieren |
- Subscriber können dynamisch und zu unterschiedlichen Zeiten Elemente erhalten |

Note:
Zusätzlich gibt es Implementierungsrichtlinien.
RxJava, Reactor und Java 9 halten sich an der Spezifikation
Beispiel Reactor: Flux, Mono

- 

+++

### Subscription

```
public void request(long n);

public void cancel();

```

- stellt 1:1 Lebenszyklus eines Subscriber dar
- kann nur einmal von einem Subscriber verwendet werden

Note:

-

+++

### Subscriber<T>

```
public void onSubscribe(Subscription s);

public void onNext(T t);

public void onError(Throwable t);

public void onComplete();

```

Note:

-

+++

### Processor

- erbt von Publisher und Subscriber

Note:
Verhält sich gleizeitig als Publisher und Subscriber und hat jeweils die Regeln einzuhalten.
Beispiel Reactor: 

+++

## Buzzwords

- Backpressure
- hot & cold

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

### Code-Beispiele - Reactor

+++

### Publisher

```
Flux<String> flux = Flux.just("Java", "Go", "Assembler",
  "php", "Ada", "Kotlin", "Clojure")
  .doOnNext(System.out::println)
  .filter(name -> name.startsWith("A"))
  .map(String::toUpperCase);
```

- keine Ausgabe vorhanden |



+++


### Publisher subscribe

```Java
Flux<String> flux = Flux.just("Java", "Go", "Assembler",
  "php", "Ada", "Kotlin", "Clojure")
  .doOnNext(System.out::println)
  .filter(name -> name.startsWith("A"))
  .map(String::toUpperCase);
  
  flux.subscribe(item -> System.out.println("Subscriber: " + item));
```

```Text |
Java
Go
Assembler
Subscriber: ASSEMBLER
php
Ada
Subscriber: ADA
Kotlin
Clojure
```

+++

### cold Publisher

```
Flux<String> flux = Flux.just("Go", "ColdFusion",
  "Java", "C", "JavaScript", "Clojure")
  .doOnNext(System.out::println)
  .filter(name -> name.startsWith("C"))
  .map(String::toUpperCase);

flux.subscribe(name ->
  System.out.println("subscribe 1: " + name));

flux.subscribe(name ->
  System.out.println("subscribe 2: " + name));
```

```Text |
Go
Java
Clojure
subscribe 1: CLOJURE
Go
Java
Clojure
subscribe 2: CLOJURE
```

+++

### hot Publisher

```Java
UnicastProcessor<String> hot = UnicastProcessor.create();
Flux<String> flux = hot.publish().autoConnect()
  .map(String::toUpperCase);
flux.subscribe(name ->
  System.out.println("subscribe 1: " + name));
hot.onNext("Go");
hot.onNext("Scala");
flux.subscribe(name ->
  System.out.println("subscribe 2: " + name));
hot.onNext("Clojure");
hot.onNext("Java");
```

``` Text |
subscribe 1: GO
subscribe 1: SCALA
subscribe 1: CLOJURE
subscribe 2: CLOJURE
subscribe 1: JAVA
subscribe 2: JAVA
```


### Error Handling

---

## Reaktive Programmierung 
### mit Java und Spring

- Spring Framework von Pivotal |
- Open Source - Apache License |
- nutzt Reactor |

+++

### Demo

+++

### unterstütze Container

ab Servlet Version 3.1

- Tomcat
- Jetty
- Netty
- Undertow

+++

### unterstütze Datenbanken

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

- eingesetzte Technologien
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

