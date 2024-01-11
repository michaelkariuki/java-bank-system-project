package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    //Variables
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private String name;
    private double amount;
    private String acType;
    private double loanPercentage;
    private LinkedList<Loan> Loans;
    private LinkedList<MiniStatementObject> miniStatements;


    //Constructors
//    public Account(){}

    public Account(String nm, double amt, String actype, double lnPerc){
        this.id = count.incrementAndGet();
        this.setName(nm);
        this.setAmount(amt);
        this.setAcType(actype);
        this.setLoanPercentage(lnPerc);
        this.setLoans(new LinkedList<>());
        this.setMiniStatements(new LinkedList<>());
    }

    //Functions
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public double getLoanPercentage() {
        return loanPercentage;
    }

    public void setLoanPercentage(double loanPercentage) {
        this.loanPercentage = loanPercentage;
    }

    public LinkedList<Loan> getLoans() {
        return Loans;
    }

    public void setLoans(LinkedList<Loan> loans) {
        Loans = loans;
    }

    private LinkedList<MiniStatementObject> getMiniStatements() {
        return miniStatements;
    }

    public void setMiniStatements(LinkedList<MiniStatementObject> miniStatements) {
        this.miniStatements = miniStatements;
    }

    public void formattedInfo(int count) {
        System.out.printf("************************************\n");
        System.out.printf("%-2d: **********%s**********\n",count,this.getName().toUpperCase(Locale.ROOT));
        System.out.printf("************************************\n");
        System.out.printf("%-15s:%-6s\n", "Amount", this.getAmount());
        System.out.printf("%-15s:%-6s\n", "Account type", this.getAcType());
        System.out.printf("%-15s:%-6s\n\n\n\n", "Loan percentage", this.getLoanPercentage());
    }

    public void formattedInfo() {
        System.out.printf("************************************\n");
        System.out.printf("**********%s**********\n",this.getName().toUpperCase(Locale.ROOT));
        System.out.printf("************************************\n");
        System.out.printf("%-15s:%-6s\n", "Amount", this.getAmount());
        System.out.printf("%-15s:%-6s\n", "Account type", this.getAcType());
        System.out.printf("%-15s:%-6s\n\n\n\n", "Loan percentage", this.getLoanPercentage());
    }

    public void getLoanInfo(){
        if(Loans.isEmpty()){
            System.out.println("NO LOANS TAKEN\n\n");
            return;
        }
        for(Loan l: Loans) l.formattedInfo();
    }

    public void allInfo(){
        this.formattedInfo();
        System.out.println("======LOANS======");
        this.getLoanInfo();
    }

    public void displayMiniStatement(){
        System.out.println("======ACCOUNT MINI-STATEMENT======\n");

        if(!miniStatements.isEmpty()){
            for(MiniStatementObject miniObj: this.getMiniStatements()){
                miniObj.formattedInfo();
            }

            this.calcTotaltransactions();
            return;
        }

        System.out.println("NO DATA TO DISPLAY\n");
    }

    public void addMinistatement(MiniStatementObject mini){
        miniStatements.add(mini);
    }

    public void calcTotaltransactions(){
        double tDeposit, tWithdraw;
        tDeposit = tWithdraw = 0;

        for(MiniStatementObject mini: this.getMiniStatements()){
            if(mini.getActionType().equals("Deposit")){
                tDeposit += mini.getAmount();
            }else{
                tWithdraw += mini.getAmount();
            }

        }

        System.out.println("===================================");
        System.out.printf("Total Deposits : %.2f\n", tDeposit);
        System.out.printf("Total Withdrawals : %.2f\n", tWithdraw);
        System.out.println("===================================");

    }

    private HashMap<String, Double> returnTotaltransactions(){
        double tDeposit, tWithdraw;
        HashMap<String, Double> results = new HashMap<>();

        tDeposit = tWithdraw = 0;

        for(MiniStatementObject mini: this.getMiniStatements()){
            if(mini.getActionType().equals("Deposit")){
                tDeposit += mini.getAmount();
            }
            tWithdraw += mini.getAmount();
        }

        results.put("D", tDeposit);
        results.put("W", tWithdraw);

        return results;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount + '\'' +
                ", acType='" + acType + '\'' +
                ", loanPercentage=" + loanPercentage +
                '}';
    }



}
