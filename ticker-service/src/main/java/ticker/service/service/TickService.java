package ticker.service.service;

import com.google.common.collect.EvictingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ticker.service.service.model.Tick;
import ticker.service.util.RandomUtil;

import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TickService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TickService.class);

    private final Collection<String> symbols = new HashSet<>();
    private final Map<String, EvictingQueue<Tick>> history = new ConcurrentHashMap<>();

    public TickService() {
        initialize();

        Flux.interval(Duration.ofMillis(1000L))
                .flatMap(aLong -> Flux.range(1, 5).map(cnt -> RandomUtil.choice(symbols)))
                .map(s -> {
                    Tick prevTick = history.get(s).peek();
                    return new Tick(s, prevTick.getName(),
                            RandomUtil.bump(prevTick.getPrice()),
                            RandomUtil.bump(prevTick.getVolume()));

                })
                .subscribeOn(Schedulers.newElastic("price-worker"))
                .subscribe(tick -> history.get(tick.getName()).add(tick));
    }

    private void initialize() {
        LOGGER.info("Initializing Market Data...");

        symbols.add("MMM");
        symbols.add("AXP");
        symbols.add("AAPL");
        symbols.add("BA");
        symbols.add("CAT");
        symbols.add("CVX");
        symbols.add("CSCO");
        symbols.add("KO");
        symbols.add("DIS");
        symbols.add("DWDP");
        symbols.add("XOM");
        symbols.add("GE");
        symbols.add("GS");
        symbols.add("HD");
        symbols.add("IBM");
        symbols.add("INTC");
        symbols.add("JNJ");
        symbols.add("JPM");
        symbols.add("MCD");
        symbols.add("MRK");
        symbols.add("MSFT");
        symbols.add("NKE");
        symbols.add("PFE");
        symbols.add("PG");
        symbols.add("TRV");
        symbols.add("UTX");
        symbols.add("UNH");
        symbols.add("VZ");
        symbols.add("V");
        symbols.add("WMT");

        symbols.forEach(s -> history.put(s, EvictingQueue.create(10)));

        history.get("MMM").add(new Tick("MMM","3M",203.42,3361205L));
        history.get("AXP").add(new Tick("AXP","American Express",100.5,2694319L));
        history.get("AAPL").add(new Tick("AAPL","Apple",187.36,23198391L));
        history.get("BA").add(new Tick("BA","Boeing",344.5,4345158L));
        history.get("CAT").add(new Tick("CAT","Caterpillar",152.61,4667841L));
        history.get("CVX").add(new Tick("CVX","Chevron",128.72,11442408L));
        history.get("CSCO").add(new Tick("CSCO","Cisco",46.04,20437248L));
        history.get("KO").add(new Tick("KO","Coca-Cola",41.78,9295141L));
        history.get("DIS").add(new Tick("DIS","Disney",99.97,18342390L));
        history.get("DWDP").add(new Tick("DWDP","DowDupont Inc.",66.03,10312284L));
        history.get("XOM").add(new Tick("XOM","Exon Mobile",79.93,17141377L));
        history.get("GE").add(new Tick("GE","General Electric",14.62,50864953L));
        history.get("GS").add(new Tick("GS","Goldman Sachs",241.73,2433527L));
        history.get("HD").add(new Tick("HD","Home Depot",186.36,3392251L));
        history.get("IBM").add(new Tick("IBM","IBM",142.61,4195121L));
        history.get("INTC").add(new Tick("INTC","Intel",54.34,19820761L));
        history.get("JNJ").add(new Tick("JNJ","Johnson & Johnson",123.51,6808357L));
        history.get("JPM").add(new Tick("JPM","JPMorgan Chase",113.41,11367431L));
        history.get("MCD").add(new Tick("MCD","McDonald's",164.24,2762488L));
        history.get("MRK").add(new Tick("MRK","Merk",57.95,8286273L));
        history.get("MSFT").add(new Tick("MSFT","Microsoft",96.94,27325508L));
        history.get("NKE").add(new Tick("NKE","Nike",67.95,7955462L));
        history.get("PFE").add(new Tick("PFE","Pfizer",35.17,19383337L));
        history.get("PG").add(new Tick("PG","Procter & Gamble",72.37,7527785L));
        history.get("TRV").add(new Tick("TRV","Travelers Companies Inc.",129.75,1164909L));
        history.get("UTX").add(new Tick("UTX","United Technologies",123.11,3551825L));
        history.get("UNH").add(new Tick("UNH","UnitedHealth",229.21,3504188L));
        history.get("VZ").add(new Tick("VZ","Verizon",46.38,23081083L));
        history.get("V").add(new Tick("V","Visa",130.84,6119361L));
        history.get("WMT").add(new Tick("WMT","Wal-Mart",83.06,32257436L));

        LOGGER.info("Market Data Initialization Completed");
    }

    public Flux<Tick> getAll() {
        return Flux.interval(Duration.ofMillis(1000L))
                .onBackpressureDrop()
                .map(aLong -> {
                   return null;
                });
    }

    public Flux<Tick> get(String symbol) {
        return null;
    }
}
