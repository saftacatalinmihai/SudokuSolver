package com.sdcm.sudoku;

/**
 * Created by mihai on 09.01.2016.
 *
 */
import  rx.Observable;
import rx.subjects.BehaviorSubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {

    public static void main(String [] args){
        try {

        Observable.concat(
                Observable.interval(100, TimeUnit.MILLISECONDS).take(3),
                Observable.interval(500, TimeUnit.MILLISECONDS).take(4),
                Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
                ).scan(0, (acc, v) -> acc + 1).timeInterval()
                .debounce(150, TimeUnit.MILLISECONDS)
                .subscribe(new PrintSubscriber());

            in.read();



//        Observable<String> o1 = Observable.just("a");
//        Observable<String> o2 = Observable.just("b");
//
//        Observable<String> o12 = Observable.merge(Arrays.asList(o1,o2));
//        o12.subscribe(new PrintSubscriber());


//            Observable.interval(1, TimeUnit.SECONDS).subscribe(out::println);
//            Observable.using(
//                    () -> new Scanner(in),
//                    (scanner) -> Observable.create(o -> {
//                        try {
//                            while (true) {
//                                o.onNext(scanner.next());
//                            }
//                        } catch (Exception ex) {
//                            o.onError(ex);
//                        }
//                    }),
//                    Scanner::close
//            ).subscribe(new PrintSubscriber());
//

//            Observable.switchOnNext(
//                    Observable.interval(100, TimeUnit.MILLISECONDS)
//                            .map(i ->
//                                    Observable.interval(30, TimeUnit.MILLISECONDS)
//                                            .map(i2 -> i.)
//                            )
//            )
//                    .take(9)
//                    .subscribe(System.out::println);
//            Observable.interval(100, TimeUnit.MILLISECONDS).take(5)
//                    .delay(1000, TimeUnit.MILLISECONDS)
//                    .timeInterval()
//                    .subscribe(System.out::println);
//
//            Observable.interval(100, TimeUnit.MILLISECONDS).take(5)
//                    .delay(i -> Observable.timer( 700, TimeUnit.MILLISECONDS))
////                    .delay(700, TimeUnit.MILLISECONDS)
//                    .timeInterval()
//                    .subscribe(System.out::println);
//            Observable
//                    .interval(100, TimeUnit.MILLISECONDS)
//                    .buffer(250, TimeUnit.MILLISECONDS)
//                    .subscribe(new PrintSubscriber());

//            Observable.switchOnNext(
//                    Observable.interval(1200, TimeUnit.MILLISECONDS)
//                    .map( o ->  Observable.interval(300, TimeUnit.MILLISECONDS).map(i -> o))
//            )
//                    .subscribe(new PrintSubscriber());

//            Observable.range(0, 5)
//                    .concatMap(i ->
//                            Observable.interval(300, TimeUnit.MILLISECONDS).take(4)
//                                    .map(l -> i))
//                    .subscribe(System.out::println);

//            Observable.combineLatest(
//                    Observable.interval(1, TimeUnit.SECONDS),
//                    Observable.interval(500, TimeUnit.MILLISECONDS),
//                (i1, i2) -> i1 + " : " + i2
//            ).timeInterval().subscribe(new PrintSubscriber());

//            Observable<Long> values = Observable.interval(500, TimeUnit.MILLISECONDS);
//
//            values
//                    .take(5)
////                    .doOnEach(System.out::println)
//                    .repeatWhen(ob -> {
//                        ob.subscribe();
//                        return Observable.interval(2, TimeUnit.SECONDS);
////                        return ob
////                                .doOnEach(System.out::println)
////                                .take(2);
//                    })
////                    .doOnEach(System.out::println)
//                    .subscribe(new PrintSubscriber("repeatWhen"));



//            Observable<String> strings = Observable.just(
//                    "asd", "awafw", "asvc", "bsdf", "bsdf", "csads", "cs"
//            );
//
//            Observable.concat(strings.groupBy(v -> v.charAt(0)))
//                    .subscribe(new PrintSubscriber());

//            Observable<Character> vals = Observable.using(
//                    () -> {
//                        String res = "SomeString";
//                        return res;
//                    },
//                    (resource) -> Observable.create(o -> {
//                        for (Character c : resource.toCharArray()){
//                            o.onNext(c);
//                        }
//                        o.onError(new Exception("Psych"));
//                        o.onCompleted();
//                    }),
//                    (res) -> System.out.println("Done")
//            );
//
//            vals.retryWhen(o -> o.take(2).delay(100, TimeUnit.MILLISECONDS)).subscribe(new PrintSubscriber("vals: "));


//            Observable<Integer> source = Observable.create( o -> {
//                o.onNext(1);
//                o.onNext(2);
//                o.onError(new Exception("Failed"));
//            });
//
//            source
//                    .retryWhen( o -> o
//                            .take(2)
//                            .delay(1, TimeUnit.SECONDS))
//                    .timeInterval()
//                    .subscribe(new PrintSubscriber("Exception: "));
//
//            System.in.read();


//            Observable<String> o = Observable.just("a", "b");
//            o.doOnEach(new PrintSubscriber("Log"))
//                    .map(s -> s.toUpperCase())
//                    .subscribe(new PrintSubscriber("Process"));

//            Observable.just(100, 150)
//                    .flatMap(i ->
//                            Observable.interval(i, TimeUnit.MILLISECONDS).map(v -> i)
//                    )
//                    .subscribe(new PrintSubscriber("flatMap"));

//            Observable<Integer> values = Observable.range(0,30);
//
//            values.filter( i -> 0 < i && i <= 26)
//                    .map( i -> Character.valueOf((char)(i+64)))
////                    .flatMap(i -> {
////                        if (0 < i && i <= 26)
////                            return Observable.just(Character.valueOf((char)(i+64)));
////                        else
////                            return Observable.empty();
////                    })
//                    .subscribe(new PrintSubscriber("flatMap"));

//            Observable<Integer> i = Observable.range(1, 3);
//            i.flatMap( n -> Observable.range(0, n)).subscribe(new PrintSubscriber("flatMap"));
//            Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);
//
//            values.take(3)
//                    .timeInterval()
//                    .subscribe(new PrintSubscriber("TimeInterval"));
//

//            Observable<String> values = Observable.just(
//                    "first",
//                    "second",
//                    "third",
//                    "forth",
//                    "fifth",
//                    "sixth"
//            );
//            values.groupBy(w -> w.charAt(0))
//                    .subscribe( g -> g.last()
//                            .subscribe( v -> System.out.println(g.getKey() + ": " + v)));
//
//            values.groupBy(w -> w.charAt(0))
//                    .flatMap(g -> g.last().map(v -> g.getKey() + ": " + v))
//                    .subscribe( new PrintSubscriber("Values"));
//            Observable<Integer> vals = Observable.range(0, 5);
//            vals.subscribe(new PrintSubscriber("Values"));
//            vals.count().subscribe(new PrintSubscriber("Count"));


//            Observable.empty().defaultIfEmpty(1).isEmpty().subscribe(System.out::println, System.out::println, () -> System.out.println("Done"));

//            Observable<Long> ints = Observable.interval(100, TimeUnit.MILLISECONDS).take(100);
//            ints.subscribe(new PrintSubscriber("Ints"));
//            ints.scan((i1, i2) -> i1 + i2).subscribe(new PrintSubscriber("IntsSum"));
//
//            ints.all( i -> i < 10).subscribe(System.out::println, System.out::println, () -> System.out.println("Done"));
//            ints.subscribe(System.out::println);
//            Observable<Long> vals = Observable.interval(100, TimeUnit.MILLISECONDS);
//            Observable<Long> timeout = Observable.timer(5, TimeUnit.SECONDS);
//
//            vals.takeUntil(timeout).subscribe(System.out::println);

//            Observable<Long> ints = Observable.interval(100, TimeUnit.MILLISECONDS);
//            ints.take(250, TimeUnit.MILLISECONDS)
//                    .subscribe(System.out::println);
//            Observable<Integer> ints = Observable.from(Arrays.asList(1,2,3,4,4,4,5,3,4,2,2,2,1));
//            Subscription s = ints.distinctUntilChanged().subscribe(System.out::println);
//            s.unsubscribe();

//            Observable<Long> ticks = Observable.interval(1000, TimeUnit.MILLISECONDS);
//            Subscription s = ticks.subscribe(
//                    v -> System.out.println("Val: " + v),
//                    e -> System.out.println("Err: " + e),
//                    () -> System.out.println("Completed")
//            );
//        try {
//            Observable<Long> o = Observable.defer(() -> Observable.just(System.currentTimeMillis()));
//            o.subscribe(System.out::println);
//            Thread.sleep(100);
//            o.subscribe(System.out::println);

//        Observable<String> o = Observable.just("a", "b", "c");
//        Subscription s = o.subscribe(
//                v -> System.out.println("Val: " + v),
//                e -> System.out.println("Err: " + e),
//                () -> System.out.println("Completed")
//                );
//        s.unsubscribe();

//        Subject<Integer, Integer> values = ReplaySubject.create();
//        Subscription subscription1 = values.subscribe(
//                v -> System.out.println("First: " + v),
//                e -> System.out.println("First: " + e),
//                () -> System.out.println("Completed")
//        );
//        values.onNext(0);
//        values.onNext(1);
//        values.onCompleted();
//        values.onNext(2);
//        subscription1.unsubscribe();

//        ReplaySubject<Integer> s = ReplaySubject.createWithTime(1, TimeUnit.MILLISECONDS, Schedulers.computation());
//        s.subscribe(v -> System.out.println("Early: " + v));
//        s.onNext(0);
//        s.onNext(1);
//        s.subscribe(v -> System.out.println("Late: " + v));
//        s.onNext(2);

//        Function f = (c) -> {
//            Observable o1 = Observable.just(Arrays.asList(1, 2, 3), "Hello Again", 1);
//
//            Observable o2 = Observable.create(o -> {
//                o.onNext("Hello");
////            try {
////                Thread.sleep(1000);
////            } catch (Exception e) {
////                o.onError(e);
////            }
//                o.onNext("Again");
//                o.onCompleted();
//            });
//            o2.mergeWith(o1).subscribe(
//                    System.out::println,
//                    e -> System.out.println("Error" + e),
//                    () -> System.out.println("Done")
//            );
//
//            o2.zipWith(o1,
//                    (a, b) -> Arrays.asList(a, b)).subscribe(
//                    System.out::println,
//                    e -> System.out.println("Error" + e),
//                    () -> System.out.println("Done")
//            );
//
//            o2.onErrorResumeNext(Observable.just("Mock"));
//            return 1;
//        };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
