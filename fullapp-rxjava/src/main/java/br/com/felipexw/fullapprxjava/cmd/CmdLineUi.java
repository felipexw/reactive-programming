package br.com.felipexw.fullapprxjava.cmd;

import br.com.felipexw.fullapprxjava.observer.ConsolePrintObserver;
import br.com.felipexw.fullapprxjava.service.CoinbaseService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CmdLineUi implements CommandLineRunner {
    @Autowired
    private CoinbaseService coinbaseService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\nReacting Programming with RxJava");

        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(ticket -> coinbaseService.getCryptoPrice(("BTC-USD")))
                .subscribe(new ConsolePrintObserver());

        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(ticket -> coinbaseService.getCryptoPrice(("ETH-USD")))
                .subscribe(new ConsolePrintObserver());
    }
}
