package br.com.felipexw.fullapprxjava.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import br.com.felipexw.fullapprxjava.service.CoinbaseService;

public class PriceRequestor extends AbstractActor {
    private final ActorRef printerActor;
    private final CoinbaseService coinbaseService;

    public PriceRequestor(ActorRef printerActor, CoinbaseService coinbaseService) {
        this.printerActor = printerActor;
        this.coinbaseService = coinbaseService;
    }

    public static Props props(ActorRef printerActor, CoinbaseService coinbaseService) {
        return Props.create(PriceRequestor.class, () -> new PriceRequestor(printerActor, coinbaseService));
    }

    public static class GetThisCryptoPrice {
        public final String whatPrice;

        public GetThisCryptoPrice(String whatPrice) {
            this.whatPrice = whatPrice;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(GetThisCryptoPrice.class, what ->
                printerActor
                        .tell(new Printer
                                .CryptoPrice(coinbaseService.getCryptoPrice(what.whatPrice)), getSelf()))
                .build();
    }
}
