package curry;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

/**
 * Created by alex.bostan on 14/07/2016.
 */
public class Closure {
    private  Integer b = 2;
    static List list = Arrays.asList(1, 2, 3, 4, 5);

    public static void main(String[] args) {
//        System.out.println(new Closure().calculate(list.stream(), 3).collect(toList()));
        System.out.println(new Closure().calculate(list.stream(), new Int(3)).collect(toList()));

    }

    private Stream calculate1(Stream stream, Integer a) {
        return stream.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(final Integer t) {
                return t * a + b;
            }
        });
    }

    private  Stream<Integer> calculate(Stream<Integer> stream, final Int a) {
        return stream.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t * a.value + getB();
            }
        });
    }

    private Stream<Integer> calculate(Stream<Integer> stream, Integer a) {
        return stream.map(t -> t * a + b);
    }

    static private class Int {
        public int value;
        public Int(int value) {
            this.value = value;
        }
    }


    private Integer getB(){
        return this.b;
    }
}
