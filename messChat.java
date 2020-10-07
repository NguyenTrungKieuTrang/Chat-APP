package com.example.massa;

import java.util.Date;

public class messChat {
    private String messageText;
    private String messageUser;
    private long messageTime;

    public messChat(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;

    }

    public messChat(){

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }


}
