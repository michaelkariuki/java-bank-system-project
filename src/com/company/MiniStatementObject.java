package com.company;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//***************************************************************
//IMPORTANT!!!!!
//***************************************************************
//Sender and receiver functionality in the ministatement
//***************************************************************

public class MiniStatementObject {
    private double amount;
    private String actionType;
    private LocalDateTime date = LocalDateTime.now();
//    private String sender = "";

    private final static Map<String, String> actionTypes = Stream.of(new Object[][]{
//            {"T", "Transfer"},
            {"W", "Withdrawal"},
            {"D", "Deposit"},
    }).collect(Collectors.collectingAndThen(Collectors.toMap(p -> (String)p[0], p-> (String)p[1]),
            Collections::<String, String>unmodifiableMap));

    public MiniStatementObject(double amount, String actionType) {
        this.setAmount(amount);
        this.setActionType(actionTypes.get(actionType));
    }

    //Functions
    public void formattedInfo(){
        System.out.printf("************************************\n");
        System.out.printf("%-15s:%-6s\n", "Action Type", this.getActionType());
        System.out.printf("%-15s:%-6s\n", "Amount", this.getAmount());
        System.out.printf("%-15s:%-6s\n", "Date", this.getDate().toString());
        System.out.printf("************************************\n\n");
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    private void setDate(LocalDateTime date) {
        this.date = date;
    }


}

//public MiniStatementObject()
//    public MiniStatementObject(double amount, String actionType, String sender) {
//        this.setAmount(amount);
//        this.setActionType(actionTypes.get(actionType));
//        this.setSender(sender);
//    }

// FormattedInfo()
//        if(this.getSender().length() < 1){
//            System.out.printf("************************************\n");
//            System.out.printf("%-15s:%-6s\n", "Action Type", this.getActionType());
//            System.out.printf("%-15s:%-6s\n", "Amount", this.getAmount());
//            System.out.printf("%-15s:%-6s\n", "Date", this.getDate().toString());
//            System.out.printf("************************************\n\n");
//
//            return;
//        }
//
//        System.out.printf("************************************\n");
//        System.out.printf("%-15s:%-6s\n", "Action Type", this.getActionType());
//        System.out.printf("%-15s:%-6s\n", "Sender", this.getSender());
//        System.out.printf("%-15s:%-6s\n", "Amount", this.getAmount());
//        System.out.printf("%-15s:%-6s\n", "Date", this.getDate().toString());
//        System.out.printf("************************************\n\n");

//getSender()
//    public String getSender() {
//        return sender;
//    }
//
// setSender()
//    public void setSender(String sender) {
//        this.sender = sender;
//    }