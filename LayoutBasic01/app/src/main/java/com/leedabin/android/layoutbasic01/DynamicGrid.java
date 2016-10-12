package com.leedabin.android.layoutbasic01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class DynamicGrid extends AppCompatActivity {

    GridLayout grid;
    Button button;
    int count =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_grid);

        button = (Button)findViewById(R.id.btn_make);
        grid = (GridLayout) findViewById(R.id.gridView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button newButton = new Button(DynamicGrid.this);
                newButton.setText(count+"");
                RelativeLayout.LayoutParams buttonParam = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                grid.addView(newButton,buttonParam);
                grid.setColumnCount(4);
                count++;

                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        grid.removeView(view);
                    }
                });
            }
        });
    }

}
