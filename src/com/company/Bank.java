package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bank {
    //Variables
    private String BankName;
    private double GlobalAmount;
    private ArrayList<Account> Accounts;

    private final static Map<String, Double> acTypes = Stream.of(new Object[][]{
            {"Savings", 10.0},
            {"Current", 15.0},
    }).collect(Collectors.collectingAndThen(Collectors.toMap(p -> (String)p[0], p-> (Double)p[1]),
            Collections::<String, Double>unmodifiableMap));

    //Constructors
    public Bank(String bankName){
        this.setBankName(bankName.toUpperCase(Locale.ROOT));
        this.setAccounts(new ArrayList<Account>());
    };

    public Bank(String bankName, ArrayList accounts){
        this.setBankName(bankName.toUpperCase(Locale.ROOT));
        this.setAccounts(accounts);
    }


    //Functions
    public String getBankName(){
        return BankName;
    }

    public void setBankName(String name){
        BankName = name;
    }

    public double getGlobalAmount() {
        return GlobalAmount;
    }

    public void setGlobalAmount(double globalAmount) {
        GlobalAmount = globalAmount;
    }

    public void setAccounts(ArrayList<Account> accounts){
        Accounts = accounts;
        this.calcGlobalAmount(Accounts);
   }

    public ArrayList<Account> getAccounts() {
        return Accounts;
    }

    public static Map<String, Double> getAcTypes() {
        return acTypes;
    }

    private void calcGlobalAmount(ArrayList<Account> accounts){
        double result = 0;

        for(Account ac: accounts){
            result+= ac.getAmount();
        }

        setGlobalAmount(result);
    }

    private void addAccount(Account ac){
        Accounts.add(ac);
        this.calcGlobalAmount(Accounts);
    }

    private void remAccount(int accountId){
        Accounts.removeIf(ac -> (accountId  == ac.getId()));
        this.calcGlobalAmount(Accounts);
    }

    public void deposit(int accountIdx, double amount){
        Account ac =  Accounts.get(accountIdx);
        MiniStatementObject obj = new MiniStatementObject(amount, "D");

        ac.setAmount((ac.getAmount() + amount));
        ac.addMinistatement(obj);

        this.setGlobalAmount((GlobalAmount + amount));
        this.displayMsg("d", ac.getAmount());

    }

    public void withdraw(int accountIdx, double amount){
        Account ac =  Accounts.get(accountIdx);

        if(amount <= ac.getAmount()){
            MiniStatementObject obj = new MiniStatementObject(amount, "W");

            ac.setAmount((ac.getAmount() - amount));
            ac.addMinistatement(obj);

            this.setGlobalAmount((GlobalAmount - amount));
            this.displayMsg("w", ac.getAmount());
        }else{
            this.displayMsg("wE", ac.getAmount());

        }

    }

    public void displayMsg(String s, double amount){
        if(s.equals("d")){
            System.out.println("**********************************");
            System.out.println("----DEPOSIT SUCCESSFUL----");
            System.out.printf("New Amount: %s\n", amount);
            System.out.println("**********************************");
        }else if(s.equals("w")){
            System.out.println("**********************************");
            System.out.println("----WITHDRAW SUCCESSFUL----");
            System.out.printf("New Amount: %s\n", amount);
            System.out.println("**********************************");
        }else if(s.equals("wE")){
            System.out.println("**********************************");
            System.out.println("----YOU DO NOT HAVE SUFFICIENT FUNDS----");
            System.out.println("**********************************");
        }
    }

    public void displayAccounts(){
        for(int i=0; i<Accounts.size(); i++){
            Accounts.get(i).formattedInfo(i+1);
        }
    }

    public int findUserByName(String uname){
        for(int i=0; i<Accounts.size(); i++){
            if(Accounts.get(i).getName().equals(uname)){
                return i;
            }
        }

        return -1;
    }

    public Account findUserByIndex(int idx){
       try{
           return Accounts.get(idx);
       }catch (IndexOutOfBoundsException e){
           System.out.println(e.getCause().toString());
       }
       return null;
    }

    //Amount to be paid to the bank per year
    public double simpleInterest(int userIdx, double principle, int time){
        double amount;
        Account ac = Accounts.get(userIdx);

        amount = (principle * ac.getLoanPercentage() * (double) time)/100;

        return amount;
    }

    public void giveLoan(int userIdx, double principle, int time){
        Account ac = Accounts.get(userIdx);
        double amount = this.simpleInterest(userIdx, principle, time);

        Loan loan = new Loan(((principle + amount)/(double) time), principle, ac.getLoanPercentage(), time);
        ac.getLoans().add(loan);
        this.deposit(userIdx, loan.getPrinciple());
        loan.formattedInfo();
    }

    @Override
    public String toString() {
        return "Bank{\n" +
                "BankName='" + BankName + '\'' +
                "\nGlobalAmount=" + GlobalAmount +
                "\nAccounts=" + Accounts.toString() +
                "\nAcTypes=" + acTypes.toString() +
                "\n}";
    }
}
