package br.com.felipexw.fullapprxjava.observer;

import br.com.felipexw.fullapprxjava.model.CoinbaseResponse;
import io.reactivex.observers.DefaultObserver;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class ConsolePrintObserver   extends DefaultObserver {
    @Override
    public void onNext(Object o) {
        Mono<CoinbaseResponse> responseMono = (Mono<CoinbaseResponse>) o;

        responseMono.subscribe(coinbaseResponse -> {
            System.out.println("[" + LocalDateTime.now() + " " + coinbaseResponse.getData().getBase()
            + " Buy price: $" + coinbaseResponse.getData().getAmount() +
                    " " + coinbaseResponse.getData().getCurrency());
        });
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("ConsolePrintObserver.onError " + e.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }
}
