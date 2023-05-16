package com.example.rxjavaexamples;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class MainActivityRxSubject extends AppCompatActivity {
    private final String TAG = "TESTE";


    // Get the last item in observable
    private void asyncSubjectExampleA() {
        Observable<String> observable = Observable.just("A", "B", "C", "D");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        observable.subscribe(asyncSubject);

        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.subscribe(getSecoundObserver());
        asyncSubject.subscribe(getThirdObserver());

    }

    private void asyncSubjectExampleB() {

        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe(getFirstObserver());

        asyncSubject.onNext("A");
        asyncSubject.onNext("B");
        asyncSubject.onNext("C");

        asyncSubject.subscribe(getSecoundObserver());

        asyncSubject.onNext("D");
        asyncSubject.onComplete();

        asyncSubject.subscribe(getThirdObserver());

    }

    // Get the actual item and subsequents items in the observable
    private void behaviourSubjectExampleA() {
        Observable<String> observable = Observable.just("A", "B", "C", "D")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        BehaviorSubject<String> behaviourSubject = BehaviorSubject.create();
        observable.subscribe(behaviourSubject);

        behaviourSubject.subscribe(getFirstObserver());
        behaviourSubject.subscribe(getSecoundObserver());
        behaviourSubject.subscribe(getThirdObserver());
    }

    private void behaviourSubjectExampleB() {

        BehaviorSubject<String> behaviourSubject = BehaviorSubject.create();

        behaviourSubject.subscribe(getFirstObserver());

        behaviourSubject.onNext("A");
        behaviourSubject.onNext("B");
        behaviourSubject.onNext("C");

        behaviourSubject.subscribe(getSecoundObserver());

        behaviourSubject.onNext("D");
        behaviourSubject.onComplete();

        behaviourSubject.subscribe(getThirdObserver());

    }

    // Get subsequent items from observable after subscription
    private void puplishSubjectExampleA() {
        Observable<String> observable = Observable.just("A", "B", "C", "D")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        PublishSubject<String> publishSubject = PublishSubject.create();
        observable.subscribe(publishSubject);

        publishSubject.subscribe(getFirstObserver());
        publishSubject.subscribe(getSecoundObserver());
        publishSubject.subscribe(getThirdObserver());
    }

    private void publishSubjectExampleB() {

        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.subscribe(getFirstObserver());

        publishSubject.onNext("A");
        publishSubject.onNext("B");
        publishSubject.onNext("C");

        publishSubject.subscribe(getSecoundObserver());

        publishSubject.onNext("D");
        publishSubject.onComplete();

        publishSubject.subscribe(getThirdObserver());

    }


    // Get all items to observable regardless of when it was subscribed
    private void replaySubjectExampleA() {
        Observable<String> observable = Observable.just("A", "B", "C", "D")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        ReplaySubject<String> replaySubject = ReplaySubject.create();
        observable.subscribe(replaySubject);

        replaySubject.subscribe(getFirstObserver());
        replaySubject.subscribe(getSecoundObserver());
        replaySubject.subscribe(getThirdObserver());
    }

    private void replaySubjectExampleB() {

        ReplaySubject<String> replaySubject = ReplaySubject.create();

        replaySubject.subscribe(getFirstObserver());

        replaySubject.onNext("A");
        replaySubject.onNext("B");
        replaySubject.onNext("C");

        replaySubject.subscribe(getSecoundObserver());

        replaySubject.onNext("D");
        replaySubject.onComplete();

        replaySubject.subscribe(getThirdObserver());

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        asyncSubjectExampleA();
//        asyncSubjectExampleB();
//        behaviourSubjectExampleA();
//        behaviourSubjectExampleB();
//        puplishSubjectExampleA();
//        publishSubjectExampleB();
//        replaySubjectExampleA();
//        replaySubjectExampleB();
    }

    private Observer<String> getFirstObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe first Observer");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext first Observer " + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError first Observer");

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete first Observer");

            }
        };

        return observer;
    }

    private Observer<String> getSecoundObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe secound Observer");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext second Observer " + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError secound Observer");

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete secound Observer");

            }
        };

        return observer;
    }

    private Observer<String> getThirdObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe third Observer");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext third Observer " + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError third Observer");

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete third Observer");

            }
        };

        return observer;
    }

}
