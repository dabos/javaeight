package curry;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

/**
 * Created by alex.bostan on 14/07/2016.
 */
//f(3, 4, 5) = 3 * 4 + 5 = 17
// f(3, y, z) = g(y, z) = 3 * y + z
// g(4, z) = h(z) = 3 * 4 + z
public class Curry {
    private int b = 2;
     static List list = Arrays.asList(1, 2, 3, 4, 5);

//    private static List<Integer> calculate(List<Integer> list, Integer a) {
//        return list.map(new Function<Integer, Function<Integer, Function<Integer, Integer>>>() {
//            @Override
//            public Function<Integer, Function<Integer, Integer>> apply(final Integer x) {
//                return new Function<Integer, Function<Integer, Integer>>() {
//                    @Override
//                    public Function<Integer, Integer> apply(final Integer y) {
//                        return new Function<Integer, Integer>() {
//                            @Override
//                            public Integer apply(Integer t) {
//                                return x + y * t;
//                            }
//                        };
//                    }
//                };
//            }
//        }.apply(b).apply(a));
//    }

    private Stream<Integer> calculate(Stream<Integer> stream, Integer a) {
        return stream.map(((Function<Integer, Function<Integer, Function<Integer, Integer>>>)
            x -> y -> t -> x + y * t).apply(b).apply(a));
    }

//    private Stream<Integer> calculate1(Stream<Integer> stream, Integer a) { type can not be inferred
//        return stream.map((x -> y -> t -> x + y * t).apply(b).apply(a));
//    }

    interface F3 extends Function<Integer, Function<Integer, Function<Integer, Integer>>> {}
    private Stream<Integer> calculate1(Stream<Integer> stream, Integer a) {
        return stream.map(((F3) x -> y -> z -> x + y * z).apply(b).apply(a));
    }

    static Function<Integer, Function<Integer, Function<Integer, Integer>>> calculation = x -> y -> z -> x + y * z;

    static Function<Integer, Integer> calculation(Integer x, Integer y) {
        return calculation.apply(x).apply(y);}

    public <A, B, C> Function<A, Function<B, C>> curry(final BiFunction<A, B, C> f) {
        return (A a) -> (B b) -> f.apply(a, b);
    }

    public <A, B, C> BiFunction<A, B, C> uncurry(Function<A, Function<B, C>> f) {
        return (A a, B b) -> f.apply(a).apply(b);
    }

    public static void main(String[] args) {
        Curry curry = new Curry();
        System.out.println(list.stream().map(calculation(2, 3)).collect(toList()));
    }
}
