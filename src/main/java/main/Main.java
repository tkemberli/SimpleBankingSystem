package main;

import controller.AccountController;
import exception.InsufficientFundsException;
import lombok.val;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        val accountController = new AccountController();
        accountController.addNewAccount("A01");
        accountController.addNewAccount("A02");

        val scanner = new Scanner(System.in);

        var accNumber = "";
        var displayMenu = true;
        while (true) {
            System.out.println("Enter Account number (q to quit): ");
            accNumber = scanner.nextLine();
            if (accNumber.equals("q")) {
                displayMenu = false;
                break;
            }

            try{
                val account = accountController.getAccount(accNumber);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }

        while (displayMenu) {
            System.out.println("Bank menu:\n " +
                    "1- Make Deposit\n" +
                    "2- Make Transfer\n" +
                    "3- Make Withdraw\n" +
                    "4- Display Statement");

            val input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Enter amount: ");
                    val depositAmount = scanner.nextLine();
                    accountController.deposit(accNumber, Double.parseDouble(depositAmount));
                    break;

                case "2":
                    System.out.println("Enter account to transfer to: ");
                    val accountToTransferCode = scanner.nextLine();
                    System.out.println("Enter amount: ");
                    val amountToTransfer = Double.parseDouble(scanner.nextLine());

                    try{
                        accountController.makeTransfer(accNumber, accountToTransferCode, amountToTransfer);
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }

                case "3":
                    System.out.println("Enter amount: ");
                    val withDrawAmount = scanner.nextLine();
                    try{
                        accountController.withdraw(accNumber, Double.parseDouble(withDrawAmount));
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    System.out.println(accountController.getAccount(accNumber).getStatement());

                default:
                    break;

            }
        }

    }

}
