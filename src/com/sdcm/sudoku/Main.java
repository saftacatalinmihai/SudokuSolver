package com.sdcm.sudoku;

/**
 * Created by mihai on 09.01.2016.
 *
 */
import  rx.Observable;
import java.util.Arrays;
import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String [] args){

        Observable o1 = Observable.just(Arrays.asList(1,2,3), "Hello Again", 1);

        Observable o2 = Observable.create( o -> {
            o.onNext("Hello");
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                o.onError(e);
//            }
            o.onNext("Again");
            o.onCompleted();
        });
        o2.mergeWith(o1).subscribe(
                System.out::println,
                e -> System.out.println("Error" + e),
                () -> System.out.println("Done")
        );

        o2.zipWith(o1,
                (a,b) -> Arrays.asList(a,b)).subscribe(
                System.out::println,
                e -> System.out.println("Error" + e),
                () -> System.out.println("Done")
        );

        o2.onErrorResumeNext(Observable.just("Mock"));

    }
}
