package com.codestub.tsms.model;

/**
 * This is a POJO class for Conversation
 * Created by ganesh on 20/4/16.
 */
public class Conversation {
    private String contactName, senderPhNo, abstractConvo;

    public Conversation(String senderPhNo, String abstractConvo) {
        this.senderPhNo = senderPhNo;
        this.abstractConvo = abstractConvo;
    }

    public void setSenderPhNo(String senderPhNo) {
        this.senderPhNo = senderPhNo;
    }

    public String getSenderPhNo() {
        return senderPhNo;
    }

    public void setAbstractConvo(String abstractConvo) {
        this.abstractConvo = abstractConvo;
    }

    public String getAbstractConvo() {
        return abstractConvo;
    }
}
