package com.example.aidray_bot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button languageButton;
    private Button startChatButton;
    private String selectedLanguage = "en"; // Default language

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languageButton = findViewById(R.id.languageButton);
        startChatButton = findViewById(R.id.startChatButton);

        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Language Selection activity or dialog
                Intent intent = new Intent(MainActivity.this, LanguageSelectionActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        startChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Chat Activity
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("LANGUAGE", selectedLanguage);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            selectedLanguage = data.getStringExtra("LANGUAGE");
        }
    }
}
