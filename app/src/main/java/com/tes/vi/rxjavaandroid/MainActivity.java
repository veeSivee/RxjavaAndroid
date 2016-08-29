package com.tes.vi.rxjavaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.observers.Observers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_hello)
    TextView tv_hello;

    @BindView(R.id.et_input)
    EditText et_input;

    @BindView(R.id.btn_test)
    Button btn_tes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        tesRxjava();
    }

    private void tesRxjava(){

        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Awesome day ~ ");
                        subscriber.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                viewLog("Complete");
            }

            @Override
            public void onError(Throwable e) {
                viewLog(e.toString());
            }

            @Override
            public void onNext(String s) {
                viewLog(s);
            }
        };

        myObservable.subscribe(mySubscriber);
    }

    private void viewLog(String s){
        Log.i("RxJava",s);
    }
}
