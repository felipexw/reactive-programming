package br.com.felipexw.fullapprxjava.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import br.com.felipexw.fullapprxjava.model.CoinbaseResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class Printer extends AbstractActor {

    public Printer() {
    }

    public static Props props() {
        return Props.create(Printer.class, () -> new Printer());
    }

    public static class CryptoPrice {
        public final Mono<CoinbaseResponse> message;

        public CryptoPrice(Mono<CoinbaseResponse> message) {
            this.message = message;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(CryptoPrice.class, msg ->
                msg.message.subscribe(coinbaseResponse -> {
                    final String line = "[" + LocalDateTime.now() + "] "
                            + coinbaseResponse.getData().getBase() +
                            " Buy price: $" +
                            coinbaseResponse.getData().getAmount() + " "
                             + coinbaseResponse.getData().getCurrency();
                    System.out.println(line);
                }))
                .build();
    }
}
