package ticker.service.service;

import com.google.common.collect.EvictingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ticker.service.service.model.Tick;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TickService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TickService.class);

    private final Map<String, EvictingQueue<Tick>> history = new ConcurrentHashMap<>();

    public TickService() {
        initialize();

        Flux.interval(Duration.ofMillis(1000L))
                .subscribeOn(Schedulers.newElastic("worker"))
                .subscribe(v -> {
                    LOGGER.info("Updating Prices");
                });
    }

    private void initialize() {
        LOGGER.info("Initializing Market Data...");

        history.put("MMM", EvictingQueue.create(10));
        history.put("AXP", EvictingQueue.create(10));
        history.put("AAPL", EvictingQueue.create(10));
        history.put("BA", EvictingQueue.create(10));
        history.put("CAT", EvictingQueue.create(10));
        history.put("CVX", EvictingQueue.create(10));
        history.put("CSCO", EvictingQueue.create(10));
        history.put("KO", EvictingQueue.create(10));
        history.put("DIS", EvictingQueue.create(10));
        history.put("DWDP", EvictingQueue.create(10));
        history.put("XOM", EvictingQueue.create(10));
        history.put("GE", EvictingQueue.create(10));
        history.put("GS", EvictingQueue.create(10));
        history.put("HD", EvictingQueue.create(10));
        history.put("IBM", EvictingQueue.create(10));
        history.put("INTC", EvictingQueue.create(10));
        history.put("JNJ", EvictingQueue.create(10));
        history.put("JPM", EvictingQueue.create(10));
        history.put("MCD", EvictingQueue.create(10));
        history.put("MRK", EvictingQueue.create(10));
        history.put("MSFT", EvictingQueue.create(10));
        history.put("NKE", EvictingQueue.create(10));
        history.put("PFE", EvictingQueue.create(10));
        history.put("PG", EvictingQueue.create(10));
        history.put("TRV", EvictingQueue.create(10));
        history.put("UTX", EvictingQueue.create(10));
        history.put("UNH", EvictingQueue.create(10));
        history.put("VZ", EvictingQueue.create(10));
        history.put("V", EvictingQueue.create(10));
        history.put("WMT", EvictingQueue.create(10));

        history.get("MMM").add(new Tick("MMM","3M",203.42,3361205L,0F));
        history.get("AXP").add(new Tick("AXP","American Express",100.5,2694319L,0F));
        history.get("AAPL").add(new Tick("AAPL","Apple",187.36,23198391L,0F));
        history.get("BA").add(new Tick("BA","Boeing",344.5,4345158L,0F));
        history.get("CAT").add(new Tick("CAT","Caterpillar",152.61,4667841L,0F));
        history.get("CVX").add(new Tick("CVX","Chevron",128.72,11442408L,0F));
        history.get("CSCO").add(new Tick("CSCO","Cisco",46.04,20437248L,0F));
        history.get("KO").add(new Tick("KO","Coca-Cola",41.78,9295141L,0F));
        history.get("DIS").add(new Tick("DIS","Disney",99.97,18342390L,0F));
        history.get("DWDP").add(new Tick("DWDP","DowDupont Inc.",66.03,10312284L,0F));
        history.get("XOM").add(new Tick("XOM","Exon Mobile",79.93,17141377L,0F));
        history.get("GE").add(new Tick("GE","General Electric",14.62,50864953L,0F));
        history.get("GS").add(new Tick("GS","Goldman Sachs",241.73,2433527L,0F));
        history.get("HD").add(new Tick("HD","Home Depot",186.36,3392251L,0F));
        history.get("IBM").add(new Tick("IBM","IBM",142.61,4195121L,0F));
        history.get("INTC").add(new Tick("INTC","Intel",54.34,19820761L,0F));
        history.get("JNJ").add(new Tick("JNJ","Johnson & Johnson",123.51,6808357L,0F));
        history.get("JPM").add(new Tick("JPM","JPMorgan Chase",113.41,11367431L,0F));
        history.get("MCD").add(new Tick("MCD","McDonald's",164.24,2762488L,0F));
        history.get("MRK").add(new Tick("MRK","Merk",57.95,8286273L,0F));
        history.get("MSFT").add(new Tick("MSFT","Microsoft",96.94,27325508L,0F));
        history.get("NKE").add(new Tick("NKE","Nike",67.95,7955462L,0F));
        history.get("PFE").add(new Tick("PFE","Pfizer",35.17,19383337L,0F));
        history.get("PG").add(new Tick("PG","Procter & Gamble",72.37,7527785L,0F));
        history.get("TRV").add(new Tick("TRV","Travelers Companies Inc.",129.75,1164909L,0F));
        history.get("UTX").add(new Tick("UTX","United Technologies",123.11,3551825L,0F));
        history.get("UNH").add(new Tick("UNH","UnitedHealth",229.21,3504188L,0F));
        history.get("VZ").add(new Tick("VZ","Verizon",46.38,23081083L,0F));
        history.get("V").add(new Tick("V","Visa",130.84,6119361L,0F));
        history.get("WMT").add(new Tick("WMT","Wal-Mart",83.06,32257436L,0F));

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
