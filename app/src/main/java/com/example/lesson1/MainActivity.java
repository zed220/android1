package com.example.lesson1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModel();
        setContentView(R.layout.activity_main);

       /* helloTv = (TextView)findViewById(R.id.hello_tv);
        Button helloBtn = (Button) findViewById(R.id.hello_btn);

        helloBtn.setOnClickListener(onClickListener);*/
    }

/*    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            helloTv.setText("Hi!");
        }
    };*/
}
