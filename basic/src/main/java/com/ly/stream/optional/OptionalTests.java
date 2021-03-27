package com.ly.stream.optional;

import java.util.Optional;

/**
 * liyang 2021-03-26
 * 使用 Optional 特性
 *
 * Optional 相当于对 value 进行了一层包装
 */
public class OptionalTests {
    public static void main(String[] args) {

        /**
         * public static<T> Optional<T> empty()
         * Returns an empty {@code Optional} instance.  No value is present for this Optional.
         *
         * public boolean isPresent()
         * Return {@code true} if there is a value present, otherwise {@code false}.
         */
        System.out.println(Optional.empty().isPresent() ? "not null" : "is null");


        /**
         * public static <T> Optional<T> ofNullable(T value)
         *
         * Returns an {@code Optional} describing the specified value, if non-null,
         * otherwise returns an empty {@code Optional}.
         */
        String s1 = null;
        String s2 = "Hello world";
        Optional<String> a = Optional.ofNullable(s1);
        Optional<String> b = Optional.ofNullable(s2);


        /**
         * public void ifPresent(Consumer<? super T> consumer)
         *
         * If a value is present, invoke the specified consumer with the value, otherwise do nothing.
         */
        a.ifPresent(System.out::println);
        b.ifPresent(System.out::println); // <=> b.ifPresent(value -> System.out.println());


        /**
         * public T get()
         *
         * If a value is present in this {@code Optional}, returns the value,
         * otherwise throws {@code NoSuchElementException}.
         */
//        System.out.println(a.get()); // java.util.NoSuchElementException: No value present
        System.out.println(b.get());


        /**
         * public T orElse(T other)
         *
         * Return the value if present, otherwise return {@code other}.
         */
        System.out.println(a.orElse("Hi optional"));
        System.out.println(b.orElse("Hi optional"));


        /**
         * public T orElseGet(Supplier<? extends T> other)
         *
         * Return the value if present, otherwise invoke {@code other} and return
         * the result of that invocation.
         *
         * This method can be replaced by method orElse().
         */
        System.out.println(a.orElseGet(() -> "hi => orElseGet"));
        System.out.println(b.orElseGet(() -> "hi => orElseGet"));


        /**
         * public Optional<T> filter(Predicate<? super T> predicate)
         *
         * If a value is present, and the value matches the given predicate,
         * return an {@code Optional} describing the value, otherwise return an
         * empty {@code Optional}.
         */
        System.out.println(a.filter(x -> "Hello world".equals(x)).orElse("1：hi => filter - orElse"));
        System.out.println(b.filter(x -> "Hello world".equals(x)).orElse("2：hi => filter - orElse"));
        System.out.println(b.filter(x -> "Hello world2".equals(x)).orElse("3：hi => filter - orElse"));


        /**
         * public<U> Optional<U> map(Function<? super T, ? extends U> mapper)
         *
         * If a value is present, apply the provided mapping function to it,
         * and if the result is non-null, return an {@code Optional} describing the
         * result.  Otherwise return an empty {@code Optional}.
         */
        System.out.println(a.map(s -> 2021).orElse(1999));
        System.out.println(b.map(s -> 2022).orElse(2000));


        /**
         * public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)
         *
         *
         * If a value is present, apply the provided {@code Optional}-bearing
         * mapping function to it, return that result, otherwise return an empty
         * {@code Optional}.  This method is similar to {@link #map(Function)},
         * but the provided mapper is one whose result is already an {@code Optional},
         * and if invoked, {@code flatMap} does not wrap it with an additional
         * {@code Optional}.
         */
        System.out.println(a.flatMap(s -> Optional.ofNullable("hi => flatMap")).orElse("hi => flatMap - orElse"));
        System.out.println(b.flatMap(s -> Optional.ofNullable("hi => flatMap")).orElse("hi => flatMap - orElse"));


        /**
         * public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X
         *
         * Return the contained value, if present, otherwise throw an exception
         * to be created by the provided supplier.
         */
        System.out.println(b.orElseThrow(() -> new RuntimeException("UNKNOWN")));
        System.out.println(a.orElseThrow(() -> new RuntimeException("UNKNOWN")));

    }
}
