import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        int balance = 100;
        String name = "User";
        TransactionsLinkedList list = new TransactionsLinkedList();

        for (int i = 0; i < 5; i++) {
            User sender = new User(name, balance);
            User recipient = new User(name, balance);
            Transaction transaction = new Transaction(sender, recipient, 100);
            list.addTransaction(transaction);
        }

        Transaction[] arrayOfTransaction = list.toArray();
        int length = arrayOfTransaction.length;
        System.out.println("Length: " + length + '\n');

        for (Transaction item : arrayOfTransaction) {
            System.out.println(item.getId());
            System.out.println(item.getSender());
            System.out.println(item.getRecipient());
            System.out.println(item.getTypeTransfer());
            System.out.println(item.getTransferAmount());
            System.out.println();
        }

        try {
            list.deleteTransaction(UUID.randomUUID());
        } catch (TransactionNotFoundException e) {
            System.out.println("UUID not found");
        }
    }
}