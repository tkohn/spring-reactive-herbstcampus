---

### Reaktive Programmierung 
#### mit Java und Spring

+++

### Inhalt

* Vorstellung
** Über mich
** comSysto
* Einleitung
** Was ist Reaktive Programmierung?
** Vor- und Nachteile
** Implementierungen
* Code-Beispiele mit Reactor
* Reactive Programmierung mit Spring

---

### Vorstellung

+++

### Torsten

+++

### comSysto

---

### Einleitung

+++

### Was ist Reaktive Programmierung?

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
    @Test
    public void creatingFluxWithJust(){
        Flux<String> flux = Flux.just("Bryan", "Dominic", "Arlyne", "Truman", "Audie");
        Mono<Long> numberOfEntries = flux.count();
        assertThat(numberOfEntries.block()).isEqualTo(5);
    }
```

---?code=playground/src/test/java/de/torstenkohn/herbstcampus/playground/FluxAndMonoCreateTests.java&lang=java
@[22-28]
@[20-39]
@[41-46]
@[49-52]
+++

### Interaktion

+++

### Was passiert im Fehlerfall

---

### Reaktive Programmierung mit Spring