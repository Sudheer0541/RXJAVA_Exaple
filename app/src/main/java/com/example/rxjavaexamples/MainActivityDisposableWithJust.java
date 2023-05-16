package com.example.rxjavaexamples;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityDisposableWithJust extends AppCompatActivity {

    private final static String TAG = "TESTE";
    private String[] hello = {"Hello A", "Hello B", "Hello C"};

    private Observable<String[]> observable;
    private DisposableObserver<String[]> disposeObserver;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configRx();
    }

    private boolean configRx() {
        return compositeDisposable.add(configObservable());
    }

    private DisposableObserver configObservable() {
        observable = Observable.just(hello);
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(configAndGetObserver());
    }

    private DisposableObserver configAndGetObserver() {
        disposeObserver = new DisposableObserver<String[]>() {
            @Override
            public void onNext(@NonNull String[] s) {
                Log.d(TAG, "came in onNext: " + s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "came in onError");

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "came in onComplete");
            }
        };

        return disposeObserver;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}