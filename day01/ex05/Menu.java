import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private boolean dev = false;
    private final TransactionsService transactionsService;
    Scanner scanner;

    public Menu(String str) {
        if (str.equals("--profile=dev")) {
            dev = true;
        }
        transactionsService = new TransactionsService();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            if (dev) {
                scanDevMenu();
            } else {
                scanDefMenu();
            }
        }
    }

    private void printMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. Show user balance");
        System.out.println("3. Perform a transaction");
        System.out.println("4. Show all transactions for a user");
        if (dev) {
            System.out.println("5. DEV – remove a transaction by ID");
            System.out.println("6. DEV – check transaction validity");
            System.out.println("7. Finish execution");
        } else {
            System.out.println("5. Finish execution");
        }
    }

    private void scanDevMenu() {
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            scanner.nextLine();
            switch (n) {
                case (1):
                    addUser();
                    break;
                case (2):
                    showUserBalance();
                    break;
                case (3):
                    executeTransaction();
                    break;
                case (4):
                    showTransactionsUser();
                    break;
                case (5):
                    deleteTransaction();
                    break;
                case (6):
                    getUnpairedTransactions();
                    break;
                case (7):
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Only digits");
            scanner.nextLine();
        }
    }

    private void scanDefMenu() {
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            scanner.nextLine();
            switch (n) {
                case (1):
                    addUser();
                    break;
                case (2):
                    showUserBalance();
                    break;
                case (3):
                    executeTransaction();
                    break;
                case (4):
                    showTransactionsUser();
                    break;
                case (5):
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Only digits");
            scanner.nextLine();
        }
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        String str = scanner.nextLine();
        String[] arr = str.split(" ");
        if (arr.length == 2) {
            int balance = Integer.parseInt(arr[1]);
            User user = new User(arr[0], balance);
            transactionsService.addUser(user);
            System.out.println("User with id = " + user.getId() + " is added");
        } else {
            System.out.println("Invalid format");
        }
    }

    private void showUserBalance() {
        System.out.println("Enter a user ID");
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            try {
                System.out.println(transactionsService.getUserName(n) + " - " + transactionsService.getUserBalance(n));
            } catch (UserNotFoundException e) {
                System.out.println("User not found");
            }
        } else {
            System.out.println("Invalid format");
        }
    }

    private void executeTransaction() {
        System.out.println("Enter a sender ID, a recipient ID, and a " + "transfer" + " amount");
        String str = scanner.nextLine();
        String[] arr = str.split(" ");
        if (arr.length == 3) {
            try {
                int sender = Integer.parseInt(arr[0]);
                int recipient = Integer.parseInt(arr[1]);
                int balance = Integer.parseInt(arr[2]);
                transactionsService.executeTransaction(sender, recipient, balance);
                System.out.println("The transfer is completed");
            } catch (NumberFormatException e) {
                System.out.println("Invalid format");
            } catch (UserNotFoundException e) {
                System.out.println("User not found");
            } catch (IllegalTransactionException e) {
                System.out.println("The sender does not have enough balance.");
            }
        } else {
            System.out.println("Invalid format");
        }
    }

    private void showTransactionsUser() {
        System.out.println("Enter a user ID");
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            try {
                Transaction[] temp = transactionsService.getTransactionFromUser(n);
                for (int i = 0; temp != null && i < temp.length; i++) {
                    if (temp[i].getTypeTransfer() == Transaction.Transfer.CREDIT) {
                        System.out.println(
                                "To " + temp[i].getRecipient().getName() + "(id = " + temp[i].getRecipient().getId() +
                                        ") " + temp[i].getTransferAmount() + " with id = " + temp[i].getId());
                    } else {
                        System.out.println(
                                "From " + temp[i].getRecipient().getName() + "(id = " + temp[i].getRecipient().getId() +
                                        ") " + temp[i].getTransferAmount() + " with id = " + temp[i].getId());
                    }
                }
            } catch (UserNotFoundException e) {
                System.out.println("User not found");
            }
        } else {
            System.out.println("Invalid format");
        }
    }

    private void deleteTransaction() {
        System.out.println("Enter a user ID and a transfer ID");
        String str = scanner.nextLine();
        String[] arr = str.split(" ");
        if (arr.length == 2) {
            try {
                int i = Integer.parseInt(arr[0]);
                UUID id = UUID.fromString(arr[1]);
                Transaction temp = transactionsService.deleteTransactionFromUser(i, id);
                System.out.println(
                        "Transfer to " + temp.getSender().getName() + "(id = " + temp.getSender().getId() + ") " +
                                temp.getTransferAmount() + " removed");
            } catch (UserNotFoundException e) {
                System.out.println("User not found");
            } catch (TransactionNotFoundException e) {
                System.out.println("Transaction not found");
            } catch (NumberFormatException e) {
                System.out.println("Invalid format");
            }
        } else {
            System.out.println("Invalid format");
        }
    }

    private void getUnpairedTransactions() {
        Transaction[] temp = transactionsService.getUnpairedTransaction();
        for (int i = 0; temp != null && i < temp.length; i++) {
            System.out.println(temp[i].getSender().getName() + "(id = " + temp[i].getSender().getId() +
                    ") has an unacknowledged transfer id = " + temp[i].getId() + "from " +
                    temp[i].getRecipient().getName() + "(id = " + temp[i].getRecipient().getId() + ") for " +
                    temp[i].getTransferAmount());
        }
    }
}


