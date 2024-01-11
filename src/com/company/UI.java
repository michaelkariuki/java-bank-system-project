package com.company;

import java.util.Locale;
import java.util.Scanner;

public class UI {
    private Scanner scanner = new Scanner(System.in); //for getting input
    private int selection;
    private Bank bank;
    private int userIdx;

    //Constructor
    public UI(){}
    public UI(Bank bank){ this.bank = bank;}

    //functions
    public int selector(){
        System.out.printf("Insert a number here: ");
        selection = scanner.nextInt();
        System.out.printf("\n\n\n\n");
        return selection;
    }

    public void exit(){
        System.exit(0);
    }

    public void mainScreen(){
        while(true){
            this.clearScreen();
            System.out.println("************************************");
            System.out.printf("*******WELCOME TO %s*******\n", bank.getBankName());
            System.out.println("************************************");
            System.out.println("1. login\n2. View accounts\n3. Exit");

            switch (this.selector()){
                case 1:
                    login();
                    break;
                case 2:
                    viewAccounts(bank);
                    break;
                case 3:
                    this.exit();
                    break;
                default:
                    break;
            }
        }
    }

    public void viewAccounts(Bank bank){
        while(true){
            this.clearScreen();
            bank.displayAccounts();
            System.out.println("Insert 1 to go back...");
            if(this.selector() == 1){
                return;
            }
        }
    }

    public void login(){
        String uname;
        boolean isAuth;

        while(true){
            this.clearScreen();
            System.out.printf("Insert username here (or 1 to goBack) : ");
            uname = scanner.next();
            System.out.printf("\n");

            if(uname.matches("\\d+") && Integer.parseInt(uname) == 1) return;

            isAuth = this.authenticateUser(bank, uname);

            if(isAuth){
                this.accountMenu(userIdx, bank);
            }

        }
    }

    private boolean authenticateUser(Bank bank, String uname){
        int idx;
        idx = bank.findUserByName(uname);
        userIdx = Math.max(idx, -1);
        return userIdx > -1;
    }

    public void accountMenu(int userIdx, Bank bank){
        Account ac = bank.findUserByIndex(userIdx);

        while(true){
            this.clearScreen();
            System.out.println("************************************");
            System.out.printf("WELCOME %s\n", ac.getName().toUpperCase(Locale.ROOT));
            System.out.println("************************************");
            System.out.println("1. View Account\n2. Deposit\n3. Withdraw\n4. Take loan\n5. Mini-statement\n6. Logout");

            switch (this.selector()){
                case 1:
                    this.viewAcDetails(ac);
                    break;
                case 2:
                    this.depositWithdraw(bank, userIdx, "d");
                    break;
                case 3:
                    this.depositWithdraw(bank, userIdx, "w");
                    break;
                case 4:
                    this.takeLoan(bank, userIdx);
                    break;
                case 5:
                    this.miniStatement(ac);
                    break;
                case 6:
                    return;
                default:
                    break;
            }
        }
    }

    public void viewAcDetails(Account ac){
        while(true){
            this.clearScreen();
            ac.allInfo();

            System.out.println("Key in  1 to go back");

            if(this.selector() == 1) return;
        }
    }

    public void depositWithdraw(Bank bank, int userIdx, String type){
        this.clearScreen();
        String amount = "";
        String sectionName = type.equals("w") ? "Withdraw" : "Deposit";
        while(true){

            System.out.printf("Insert amount to %s here (or 0 to goBack) : ", sectionName);
            amount = scanner.next();
            System.out.printf("\n");

            if(amount.matches("\\d+")){
                int amt = Integer.parseInt(amount);
                if(amt == 0){
                    return;
                }else if(sectionName.equals("Withdraw")){
                    bank.withdraw(userIdx,  (double) amt);
                }else{
                    bank.deposit(userIdx, (double) amt);
                }
            }

        }
    }

    public void takeLoan(Bank bank, int userIdx){
        this.clearScreen();
        String amount;
        int time = 0;
        double amt = 0;

        while(true){
            System.out.printf("Insert loan amount to take (or 0 to goBack) : ");
            amount = scanner.next();

            if(amount.matches("^\\\\d*\\\\.\\\\d+|\\\\d+\\\\.\\\\d*$|\\d+")){
                if(!amount.contains(".") && Integer.parseInt(amount) == 0 ){
                    return;
                }

                amt = this.parseAmount(amount);

                while(true){
                    System.out.printf("Insert duration (years) for loan (or 0 to goBack) : ");
                    time = scanner.nextInt();

                    if (time != 0) {
                        bank.giveLoan(userIdx, amt, time);
                    }
                    break;
                }
            }


        }
    }

    public void miniStatement(Account ac){
        while(true){
            this.clearScreen();
            ac.displayMiniStatement();

            System.out.println("Key in 1 to go back");

            if(this.selector() == 1) return;
        }
    }

    private double parseAmount(String amount){
        double result;
        if (!amount.contains(".")) {
            amount += ".0";
        }
        result = Double.parseDouble(amount);

        return result;
    }

    private void clearScreen(){
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }



}

//MAIN SCREEN ✔
/*
 * 1. viewAc ✔
 * 2. login ✔
 * 3. exit ✔
 */

//VIEW ACCOUNTS ✔
/*
 * 1. displayAccounts() ✔
 * 2. goBack() ✔
 */

//LOGIN
/*
 * 1. username() ✔
 * 2. goBack() ✔
 */

//username() (ACCOUNT MENU) ✔
/*
 * 1. View Account ✔
 * 2. Deposit ✔
 * 3. Withdraw ✔
 * 4. Take loan
 * 5. Mini-statement
 * 6. Logout() ✔
 */

//VIEW ACCOUNT ✔
/*
 * 1. displayAcInfo() ✔
 * 2. goBack() ✔
 */

//DEPOSIT/WITHDRAW ✔
/*
 * 1. insertAmount() ✔
 * 2. calcNewAmount() ✔
 * 3. displayNewAmount() (getAmount()) ✔
 * 4. goBack() ✔
 */

//TAKE LOAN ✔
/*
 * 1. insertAmount() ✔
 * 2. insertTime() ✔
 * 3. calcNewAmount() (Bank : Amount+interest => loan) (Account => loan + Amount) ✔
 * 4. displayNewAmount() (getAmount()) ✔
 * 5. goBack() ✔
 */

//MINI-STATEMENT ✔
/*
 * 1. displayAcTransactions() ✔
 * 2. goBack() ✔
 */

//EXIT ✔
/*
 * 1. exit() ✔
 */