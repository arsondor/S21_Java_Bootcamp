public class Program {
    public static void main(String[] args) {
        User sender = new User("Kate", 1000);
        User recipient = new User("Mike", 1000);
        Transaction first = new Transaction(sender, recipient, -500);
        System.out.println("Transaction: " + first.getId());
        System.out.println("Sender: " + first.getSender().getName());
        System.out.println("Sender ID: " + first.getSender().getId());
        System.out.println("Recipient: " + first.getRecipient().getName());
        System.out.println("Recipient ID: " + first.getRecipient().getId());
        System.out.println("Transfer Category: " + first.getTypeTransfer());
        System.out.println("Transfer Amount: " + first.getTransferAmount());

        sender.setBalance(sender.getBalance() + first.getTransferAmount());
        recipient.setBalance(recipient.getBalance() - first.getTransferAmount());


        System.out.println("Sender's New Balance: " + sender.getBalance());
        System.out.println("Recipient's New Balance: " + recipient.getBalance());

    }
}
