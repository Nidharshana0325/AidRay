package com.example.aidray_bot;

public class ChatMessage {

    public static final int TYPE_USER = 1;
    public static final int TYPE_BOT = 2;

    private final String text;
    private final boolean isUser;

    public ChatMessage(String text, boolean isUser) {
        this.text = text;
        this.isUser = isUser;
    }

    public String getText() {
        return text;
    }

    public boolean isUser() {
        return isUser;
    }

    public int getType() {
        return isUser ? TYPE_USER : TYPE_BOT;
    }
}
