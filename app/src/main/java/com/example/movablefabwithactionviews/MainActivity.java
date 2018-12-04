package com.example.movablefabwithactionviews;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements MovableFloatingActionButton.IMovable, View.OnClickListener {
    private static final String TAG = "ALT";
    MovableFloatingActionButton fab;
    private ConstraintLayout actionView;
    private ImageView fab1, fab2, fab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionView = findViewById(R.id.linearView);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);

    }

    @Override
    public void handleClick(int x, int y) {
        if (actionView.getVisibility() == View.INVISIBLE) {
            int fabX = x, fabY = y;
            if (x == 0 && y == 0) { //If move is not initiated we will get x and y as 0, so need to get the fab x and y position.
                fabX = (int) fab.getX();
                fabY = (int) fab.getY();
            }
            float expandViewWidth = actionView.getWidth();
            float expandViewHeight = actionView.getHeight();

            actionView.setX(fabX + fab.getWidth() - expandViewWidth);
            actionView.setY(fabY - expandViewHeight);
            actionView.invalidate();
            actionView.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        } else {
            actionView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean checkActionViewVisiblity() {
        return actionView.getVisibility() != View.VISIBLE;
    }


    @Override
    public int getViewToBeDisplayedHeight() {
        return actionView.getHeight();
    }

    @Override
    public int getViewToBeDisplayedWidth() {
        return actionView.getWidth() - fab.getWidth();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab1:
                Toast.makeText(this, "fab1 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab2:
                Toast.makeText(this, "fab2 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab3:
                Toast.makeText(this, "fab3 clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
