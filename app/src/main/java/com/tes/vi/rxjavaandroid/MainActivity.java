package com.tes.vi.rxjavaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.observers.Observers;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_hello)
    TextView tv_hello;

    @BindView(R.id.et_input)
    EditText et_input;

    @BindView(R.id.btn_test)
    Button btn_tes;

    Subscription obSubc;
    Subscription btnSubc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
    }

    private void init(){

        btnSubc = RxView.clicks(btn_tes)
                .subscribe(aVoid -> tesRxjava());
    }

    private void tesRxjava(){

        obSubc = Observable.just("Awesome day ~ ")
                .map(s->s+ et_input.getText().toString())
                .subscribe(s -> tv_hello.setText(s));
    }

    @Override
    protected void onDestroy() {

        obSubc.unsubscribe();
        btnSubc.unsubscribe();

        super.onDestroy();
    }
}
