package de.torstenkohn.herbstcampus.playground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;


public class FluxAndMonoSubscribeTest {

  @Test
  public void simpleSubscriberTest() {
    Flux<String> flux = Flux.just("Java", "Go", "Assembler",
      "php", "Ada", "Kotlin", "Clojure")
      .doOnNext(System.out::println)
      .filter(name -> name.startsWith("A"))
      .map(String::toUpperCase);

    flux.subscribe(item -> System.out.println("Subscriber: " + item));
  }


  @Test
  public void coldSubscriberTest() {
    Flux<String> flux = Flux.just("Go", "Java", "Clojure")
      .doOnNext(System.out::println)
      .filter(name -> name.startsWith("C"))
      .map(String::toUpperCase);

    flux.subscribe(name ->
      System.out.println("subscribe 1: " + name));

    flux.subscribe(name ->
      System.out.println("subscribe 2: " + name));
  }


  @Test
  public void hotSubscriberTest() {
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
  }


}
