package com.company;

import java.util.*;


public class Main {
    private static String a;

    public static void main(String[] args) {
        String[] names = {"John", "James", "Mary", "Nathaniel", "Ian"};
        double[] amounts = {15000, 20000, 35000, 100000, 60000};
        ArrayList<Account> accounts;

        accounts = generateAccounts(names, amounts);
        Bank b = new Bank("happy bank", accounts);
        UI ui= new UI(b);

        ui.mainScreen();;
    }

    //Picks a random account type for a new account (Check Bank class)
    private static Map getRandomAcType(){
        Random generator = new Random();
        Map <String, Double> randomAc = new HashMap<>();

        ArrayList<String> keys = new ArrayList<>(Bank.getAcTypes().keySet());
        String randomKey = keys.get(generator.nextInt(keys.size()));

        randomAc.put(randomKey, Bank.getAcTypes().get(randomKey));

        return randomAc;
    }

    //Generates mocks accounts for the bank object
    private static ArrayList<Account> generateAccounts(String[] names, double[] amounts){
        ArrayList<Account> result = new ArrayList<>();
        Account account = null;
        Map randAc;
        Iterator it;

        for(int i=0; i<names.length; i++){
            randAc = getRandomAcType();
            it = randAc.entrySet().iterator();

            while(it.hasNext()){
                Map.Entry pair = (Map.Entry) it.next();
                account = new Account(names[i], amounts[i], (String)pair.getKey(), (Double)pair.getValue());
            }
            result.add(account);
        }

        return result;
    }
}
