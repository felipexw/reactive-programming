package br.com.felipexw.fullapprxjava.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinbaseResponse {
    private Data data;

    @Getter
    @Setter
    public class Data {
        private String base;
        private String currency;
        private String amount;
    }
}
