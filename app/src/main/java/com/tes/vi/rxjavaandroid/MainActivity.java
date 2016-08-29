package com.tes.vi.rxjavaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observers.Observers;

public class MainActivity extends AppCompatActivity {

    TextView tv_hello;
    EditText et_input;
    Button btn_tes;

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

        btn_tes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tesRxjava();
            }
        });
    }

    private void tesRxjava(){

        Observable<String> myObservable = Observable.just("Awesome day ~ " + et_input.getText().toString());

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                tv_hello.setText(s);
            }
        };

        myObservable.subscribe(onNextAction);
    }

}
