package com.tes.vi.rxjavaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.observers.Observers;

public class MainActivity extends AppCompatActivity {

    TextView tv_hello;
    EditText et_input;
    Button btn_tes;

    Subscription obSubc;
    Subscription btnSubc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){

        tv_hello = (TextView)findViewById(R.id.tv_hello);
        et_input  = (EditText)findViewById(R.id.et_input);
        btn_tes = (Button)findViewById(R.id.btn_test);

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
