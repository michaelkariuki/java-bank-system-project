package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Loan {
    private LocalDateTime ldt;
    private double principle;
    private double amount;
    private int time;
    private double rate;

    //constructor
    public Loan(double amount, double principle, double rate, int time){
        this.setLdt(LocalDateTime.now());
        this.setAmount(amount);
        this.setPrinciple(principle);
        this.setRate(rate);
        this.setTime(time);
    }



    public double getPrinciple() {
        return principle;
    }

    public void setPrinciple(double principle) {
        this.principle = principle;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public  LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public void formattedInfo(){
        System.out.printf("\n%-28s:%-15s\n", "Loan Amount", this.getPrinciple());
        System.out.printf("%-28s:%s%%\n", "Interest Rate", this.getRate());
        System.out.printf("%-28s:$%.2f\n", "Interest Amount (annually)", this.getAmount());
        System.out.printf("%-28s:%s Years\n", "Time", this.getTime());
        System.out.printf("%-28s:%-15s\n\n\n", "Date Taken", getLdt().toString());
    }
}
