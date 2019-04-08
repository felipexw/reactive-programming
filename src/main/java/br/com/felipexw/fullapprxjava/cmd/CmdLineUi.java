package br.com.felipexw.fullapprxjava.cmd;

import br.com.felipexw.fullapprxjava.service.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdLineUi implements CommandLineRunner {
    @Autowired
    private CoinbaseService coinbaseService;

    @Override
    public void run(String... args) throws Exception {
        coinbaseService.getCryptoPrice("BTC-USD")
                .subscribe(coinbaseResponse -> System.out.println(coinbaseResponse.getData().getAmount()));
    }
}
