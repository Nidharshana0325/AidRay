package com.example.aidray_bot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LanguageSelectionActivity extends AppCompatActivity {

    private Button englishButton;
    private Button tamilButton;
    private Button hindiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);

        englishButton = findViewById(R.id.selectEnglishButton);
        tamilButton = findViewById(R.id.selectTamilButton);
        hindiButton = findViewById(R.id.selectHindiButton);

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnLanguage("en");
            }
        });

        tamilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnLanguage("ta");
            }
        });

        hindiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnLanguage("hi");
            }
        });
    }

    private void returnLanguage(String language) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("LANGUAGE", language);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
