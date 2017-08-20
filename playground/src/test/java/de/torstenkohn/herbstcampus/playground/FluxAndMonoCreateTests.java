package de.torstenkohn.herbstcampus.playground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Test class to show, how to create Publisher of type {@link Mono} or {@link Flux}
 *
 * ℹ️ Note: The method call {@link Mono#block()} is synchronous and blocks indefinitely.
 *
 * @author Torsten Kohn
 */
public class FluxAndMonoCreateTests {

    @Test
    public void creatingFluxFromIterable(){
        List<String> names = Arrays.asList("Bryan", "Dominic", "Arlyne", "Truman", "Audie");
        Flux<String> flux = Flux.fromIterable(names);
        Mono<Long> numberOfEntries = flux.count();
        assertThat(numberOfEntries.block()).isEqualTo(5);
    }

    @Test
    public void creatingFluxRangeFromOneToTen(){
        Flux<Integer> numbersFromOneToTen = Flux.range(1, 10);
        numbersFromOneToTen
                .map(number ->{
                    assertThat(number).isBetween(1, 10);
                    return number;
                })
                .subscribe(System.out::println);
    }

    @Test
    public void creatingFluxWithJust(){
        Flux<String> flux = Flux.just("Bryan", "Dominic", "Arlyne", "Truman", "Audie");
        Mono<Long> numberOfEntries = flux.count();
        assertThat(numberOfEntries.block()).isEqualTo(5);
    }

    @Test
    public void creatingEmptyMono(){
        Mono<String> emptyMono = Mono.empty();
        assertThat(emptyMono.block()).isNull();
    }

    @Test
    public void creatingMonoWithJust(){
        Mono<String> mono = Mono.just("Josh");
        assertThat(mono.block()).isEqualTo("Josh");
    }

    @Test
    public void creatingMonoFromCallable(){
        Callable<String> callable = () -> "Peter";
        Mono<String> mono = Mono.fromCallable(callable);
        assertThat(mono.block()).isEqualTo("Peter");
    }

}
