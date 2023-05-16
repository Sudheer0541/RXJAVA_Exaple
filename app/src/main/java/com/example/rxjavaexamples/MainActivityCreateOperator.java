package com.example.rxjavaexamples;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjavaexamples.model.Student;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityCreateOperator extends AppCompatActivity {

    public static final String TAG = "TESTE";
    private Observable<Student> observable;
    private DisposableObserver<Student> observer;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        observable = Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Student> emitter) throws Throwable {

                ArrayList<Student> studetsArraylist = getStudents();
                for (Student student : studetsArraylist) {
                    student.setEmail(student.getEmail() + " :-)");
                    emitter.onNext(student);
                }

                emitter.onComplete();

            }
        });

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getAndConfigObserver());

    }

    private DisposableObserver getAndConfigObserver() {
        observer = new DisposableObserver<Student>() {
            @Override
            public void onNext(@NonNull Student student) {
                Log.d(TAG, "Student email: " + student.getEmail());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "INTO onComplete");
            }
        };
        compositeDisposable.add(observer);
        return observer;
    }

    ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Student a = new Student("Joao", "joao@email.com", 30, "2020-04-01");
        Student b = new Student("Maria", "maria@email.com", 25, "2020-04-01");
        Student c = new Student("Ebert", "ebinho@email.com", 32, "2020-04-01");
        Student d = new Student("Falcra", "falcra@email.com", 28, "2020-04-01");

        students.add(a);
        students.add(b);
        students.add(c);
        students.add(d);

        return students;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
