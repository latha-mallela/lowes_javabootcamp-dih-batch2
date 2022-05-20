package com.labs.Day8;



class InSuffucientFundsException extends Exception{
    public InSuffucientFundsException(){
        System.out.println("Insufficient amount requested");
    }

    public InSuffucientFundsException(String message){
        System.out.println(message);
    }
}

class BankAccount {
    private int balance = 0;
    private int accountNumber;

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void deposit(int amount) {
        balance += amount;
        System.out.println("amount credited: " + amount);
    }

    public void withdraw(int amount) throws InSuffucientFundsException {
        if(amount <= balance){
            balance -= amount;
            System.out.println("amount debited: "+ amount );
        }else{
            throw new InSuffucientFundsException("sorry you dont have sufficient balance");
        	//throw new InSuffucientFundsException();
        }
    }

    public int getBalance(){
        return balance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }
}

public class Demo3 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(123456);
        System.out.println("Current balance: "+account.getBalance());
        account.deposit(5000);
        try {
            account.withdraw(2000);
        } catch (InSuffucientFundsException e) {
            e.printStackTrace();
        }
        account.deposit(1000);
        try {
            account.withdraw(10000);
        } catch (InSuffucientFundsException e) {
            e.printStackTrace();
        }
    }
}
