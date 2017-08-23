package de.torstenkohn.herbstcampus.playground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

/**
 * @author Torsten Kohn
 */
public class FluxAndMonoSubscribeTest {

  @Test
  public void simpleSubscriberTest() {
    Flux<String> flux = Flux.just("Java", "Go", "Assembler",
    "php", "Ada")
    .doOnNext(System.out::println)
    .filter(name -> name.startsWith("A"))
    .map(String::toUpperCase);
    flux.subscribe(name ->
    System.out.println("Subscriber: " + name));
  }


  @Test
  public void coldSubscriberTest() {
    Flux<String> flux = Flux.just("Go", "Java",
    "C", "JavaScript")
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
    UnicastProcessor<String> hot =
    UnicastProcessor.create();

    hot.onNext("Java");
    hot.onNext("C++");

    Flux<String> flux = hot.publish()
    .autoConnect()
    .map(String::toUpperCase);

    hot.onNext("Go");

    flux.subscribe(name ->
    System.out.println("subscribe 1: " + name));
    hot.onNext("Scala");
    hot.onNext("TypeScript");

    flux.subscribe(name ->
    System.out.println("subscribe 2: " + name));
    hot.onNext("Closure");
    hot.onNext("php");
  }


}
