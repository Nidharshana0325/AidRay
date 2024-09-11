package com.example.aidray_bot;

import android.content.Intent; // Import the correct Intent class for Android
import android.net.Uri; // Import for Uri
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private TextView chatWelcomeText;
    private EditText userInputField;
    private Button sendButton;
    private Button emergencyButton; // Added button reference
    private RecyclerView chatRecyclerView;

    private String selectedLanguage;
    private final Map<String, List<String>> patternsMap = new HashMap<>();
    private final Map<String, List<String>> responsesMap = new HashMap<>();
    private ChatAdapter chatAdapter;
    private final List<ChatMessage> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatWelcomeText = findViewById(R.id.chatWelcomeText);
        userInputField = findViewById(R.id.userInputField);
        sendButton = findViewById(R.id.sendButton);
        emergencyButton = findViewById(R.id.emergencyButton); // Initialize the emergency button
        chatRecyclerView = findViewById(R.id.chatRecyclerView);

        // Initialize RecyclerView
        chatAdapter = new ChatAdapter(messageList);
        chatRecyclerView.setAdapter(chatAdapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the selected language from intent
        selectedLanguage = getIntent().getStringExtra("LANGUAGE");

        // Load intents from JSON
        loadIntentsFromJson();

        // Display welcome message
        displayWelcomeMessage();

        // Handle send button click
        sendButton.setOnClickListener(v -> handleUserInput());

        // Handle emergency button click
        emergencyButton.setOnClickListener(v -> dialEmergencyNumber());
    }

    private void loadIntentsFromJson() {
        try {
            // Load the intents JSON from assets
            InputStream is = getAssets().open("intents.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            Type type = new TypeToken<List<ChatIntent>>() {}.getType(); // Updated class name
            List<ChatIntent> intents = gson.fromJson(jsonString.toString(), type);

            for (ChatIntent intent : intents) {
                patternsMap.put(intent.intent, intent.patterns.get(selectedLanguage));
                responsesMap.put(intent.intent, intent.responses.get(selectedLanguage));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load intents", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayWelcomeMessage() {
        if (selectedLanguage != null) {
            switch (selectedLanguage) {
                case "en":
                    chatWelcomeText.setText("Hello, I am your AidRay. How may I help you?");
                    break;
                case "hi":
                    chatWelcomeText.setText("नमस्ते, मैं आपका AidRay हूं। मैं आपकी कैसे मदद कर सकता हूं?");
                    break;
                case "ta":
                    chatWelcomeText.setText("வணக்கம், நான் உங்கள் AidRay. நான் உங்களுக்கு எப்படி உதவ முடியும்?");
                    break;
            }
        }
    }

    private void handleUserInput() {
        String userInput = userInputField.getText().toString().trim();
        if (TextUtils.isEmpty(userInput)) {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add user message to chat
        messageList.add(new ChatMessage(userInput, true));
        chatAdapter.notifyDataSetChanged();
        chatRecyclerView.scrollToPosition(messageList.size() - 1);

        // Find the best matching intent based on user input
        String intent = findBestMatchingIntent(userInput);

        // Get response for the identified intent
        List<String> responses = responsesMap.get(intent);
        if (responses != null) {
            // Simulate bot response
            StringBuilder responseBuilder = new StringBuilder();
            for (int i = 0; i < responses.size(); i++) {
                responseBuilder.append(responses.get(i)).append("\n"); // Append each response on a new line
                if (i >= 4) break; // Limit to the first 5 responses
            }

            // Add the combined response as one message
            messageList.add(new ChatMessage(responseBuilder.toString().trim(), false));

            // Add emergency message
            messageList.add(new ChatMessage("If the condition is severe, contact emergency services.", false));

            chatAdapter.notifyDataSetChanged();
            chatRecyclerView.scrollToPosition(messageList.size() - 1);
        } else {
            // Handle case where intent is not found
            messageList.add(new ChatMessage("Sorry, I didn't understand that", false));
            chatAdapter.notifyDataSetChanged();
            chatRecyclerView.scrollToPosition(messageList.size() - 1);
        }

        userInputField.setText("");
    }

    private String findBestMatchingIntent(String userInput) {
        // Implement a basic pattern matching logic
        for (Map.Entry<String, List<String>> entry : patternsMap.entrySet()) {
            for (String pattern : entry.getValue()) {
                if (userInput.toLowerCase().contains(pattern.toLowerCase())) {
                    return entry.getKey();
                }
            }
        }
        return "unknown"; // Return a default intent if no pattern matches
    }

    private void dialEmergencyNumber() {
        // Start the dialer activity
        Intent intent = new Intent(Intent.ACTION_DIAL); // Use the correct Intent class
        intent.setData(Uri.parse("tel:")); // Start with an empty URI
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No dialer app found", Toast.LENGTH_SHORT).show();
        }
    }

    // Inner class to represent an intent
    private static class ChatIntent {
        String intent;
        Map<String, List<String>> patterns;
        Map<String, List<String>> responses;
    }
}
