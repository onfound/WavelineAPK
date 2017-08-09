package com.example.ilyad.testvk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MenuActivity extends Activity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imageView = findViewById(R.id.buttonPlay);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.play);
        imageView.setAnimation(animation);
    }

    public void clickPlay(View view) {
        Intent intent = new Intent(MenuActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
