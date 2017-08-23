package de.torstenkohn.herbstcampus.playground;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author Torsten Kohn
 */
public class ErrorHandlingTests {

  @Test
  public void errorHandling() {
    Flux.range(1, 10)
    .map(n -> {
      if (n % 3 == 0) {
        throw new IllegalArgumentException("Wrong input: " + n);
      }
      return n;
    })
    .map(n -> String.valueOf(n + 10))
    .subscribe(
    value -> System.out.println("value: " + value),
    error -> System.out.println("error" + error.getMessage())
    );
  }

  @Test
  public void errorHandlingResume() {
    Flux.range(1, 10)
    .map(n -> {
      if (n == 3) {
        throw new IllegalArgumentException("Wrong input: " + n);
      }
      return "a number: " + n;
    })
    .onErrorResume(e -> e.getMessage().contains("3"), e -> Flux.just("42"))
    .subscribe(
    value -> System.out.println("value: " + value),
    error -> System.out.println("error" + error.getMessage())
    );
  }

  @Test
  public void errorHandlingRetry() throws InterruptedException {
    Flux.interval(Duration.ofMillis(250))
    .map(input -> {
      if (input < 3) return input;
      throw new RuntimeException("It is broken...");
    })
    .retry(3)
    .subscribe(System.out::println,
    System.err::println);

    Thread.sleep(5100);
  }

}
