package br.com.felipexw.fullapprxjava.service;

import br.com.felipexw.fullapprxjava.model.CoinbaseResponse;
import reactor.core.publisher.Mono;

public interface CoinbaseService {
    Mono<CoinbaseResponse> getCryptoPrice(String price);
}
