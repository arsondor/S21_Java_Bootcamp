import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsService transactionsService = new TransactionsService();
        User us1 = new User("Mike", 1000);
        User us2 = new User("Anton", 1000);
        transactionsService.addUser(us1);
        transactionsService.addUser(us2);
        System.out.println("Balance " + us1.getName() + ": " + transactionsService.getUserBalance(1));
        System.out.println("Balance " + us2.getName() + ": " + transactionsService.getUserBalance(2));
        transactionsService.executeTransaction(1, 2, 250);
        System.out.println("Success");
        System.out.println("Balance " + us1.getName() + ": " + transactionsService.getUserBalance(1));
        System.out.println("Balance " + us2.getName() + ": " + transactionsService.getUserBalance(2));

        System.out.println("\nBalance " + us1.getName() + ": " + transactionsService.getUserBalance(1));
        System.out.println("Balance " + us2.getName() + ": " + transactionsService.getUserBalance(2));
        try {
            transactionsService.executeTransaction(1, 2, -1600);
        } catch (IllegalTransactionException e) {
            System.out.println("Exception");
        }
        System.out.println("Balance " + us1.getName() + ": " + transactionsService.getUserBalance(1));
        System.out.println("Balance " + us2.getName() + ": " + transactionsService.getUserBalance(2));

        try {
            transactionsService.getUserBalance(3);
        } catch (UserNotFoundException e) {
            System.out.println("\nException\n");
        }

        Transaction transaction = transactionsService.getTransactionFromUser(1)[0];

        System.out.println(transaction.getId() + " " + transaction.getSender().getName() + " " +
                transaction.getRecipient().getName() + " " + transaction.getTransferAmount());

        transaction = transactionsService.getTransactionFromUser(2)[0];

        System.out.println(transaction.getId() + " " + transaction.getSender().getName() + " " +
                transaction.getRecipient().getName() + " " + transaction.getTransferAmount() + "\n");

        transactionsService.deleteTransactionFromUser(1, transaction.getId());

        Transaction[] transactionArray = transactionsService.getUnpairedTransaction();
        for (Transaction i : transactionArray) {
            System.out.println(i.getId() + " " + i.getSender().getName() + " " + i.getRecipient().getName() + " " +
                    i.getTransferAmount());
        }
    }
}