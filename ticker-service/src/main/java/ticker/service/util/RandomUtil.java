package ticker.service.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    public static <E> E choice(Collection<? extends E> coll) {
        if (coll.size() == 0) {
            throw new IllegalArgumentException("Collection is empty");
        }

        int index = ThreadLocalRandom.current().nextInt(coll.size());
        if (coll instanceof List) { // optimization
            return ((List<? extends E>) coll).get(index);
        } else {
            Iterator<? extends E> iter = coll.iterator();

            for (int i = 0; i < index; i++) {
                iter.next();
            }

            return iter.next();
        }
    }

    public static double bump(double num) {
        return num + (num * ThreadLocalRandom.current().nextDouble(-1.0, 1.0));
    }

    public static long bump(long num) {
        return (long) (num + (num * ThreadLocalRandom.current().nextDouble(-1.0, 1.0)));
    }
}
