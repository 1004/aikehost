package com.aike.xky.core.base;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.aike.xky.R;

public class NativePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_page);
        Bundle extras = getIntent().getExtras();
        String aNative = extras.getString("native");
        TextView mTvText = findViewById(R.id.tv_text);
        mTvText.setText(aNative);
    }
}