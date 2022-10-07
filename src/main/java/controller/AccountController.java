package controller;

import account.Account;
import account.CheckingAccount;
import exception.InsufficientFundsException;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class AccountController {
    private Map<String, Account> accounts;

    public AccountController(){
        this.accounts = new HashMap<>();
    }

    public void addNewAccount(String code){
        if(accounts.containsKey(code)){
            System.out.println("Account " + code +" already exists");
            return;
        }

        accounts.put(code, new CheckingAccount(code));
    }

    public void deposit(String accountCode, double value) throws IndexOutOfBoundsException{
        val account = getAccount(accountCode);
        account.deposit(value);
    }

    public void withdraw(String accountCode, double value) throws IndexOutOfBoundsException, InsufficientFundsException {
        val account = getAccount(accountCode);
        account.withdraw(value);
    }

    public void printStatement(String accountCode){
        val account = getAccount(accountCode);
        System.out.println("[Date]/[Amount]/[newBalance]");
        System.out.println(account.getStatement());
    }

    public void makeTransfer(String from, String to, double value) throws IndexOutOfBoundsException, InsufficientFundsException {
        val fromAccount = getAccount(from);
        val toAccount = getAccount(to);

        fromAccount.withdraw(value);
        toAccount.deposit(value);
    }

    public Account getAccount(String code) throws IndexOutOfBoundsException{
        val account = accounts.get(code);
        if(account == null) throw new IndexOutOfBoundsException("Account " + code + " does not exists.");

        return account;
    }
}
