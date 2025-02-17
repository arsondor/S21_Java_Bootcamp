import java.util.UUID;

public class TransactionsService {
    private final UsersList users;

    public TransactionsService() {
        users = new UsersArrayList();
    }

    public void addUser(User user) {
        users.addUser(user);
    }

    public Integer getUserBalance(Integer userId) throws UserNotFoundException {
        User user = users.getUserById(userId);
        return users.getUserById(user.getId()).getBalance();
    }

    public String getUserName(Integer userId) throws UserNotFoundException {
        User user = users.getUserById(userId);
        return users.getUserById(user.getId()).getName();
    }

    public void executeTransaction(Integer senderId, Integer recipientId, Integer amount)
            throws IllegalTransactionException, UserNotFoundException {
        User sender = users.getUserById(senderId);
        User recipient = users.getUserById(recipientId);
        if ((amount >= 0 && sender.getBalance() < amount) || (amount < 0 && recipient.getBalance() < -amount)) {
            throw new IllegalTransactionException("The sender does not have enough balance.");
        } else {
            Transaction temp = new Transaction(sender, recipient, -amount);
            sender.getTransactionList().addTransaction(temp);
            temp = new Transaction(temp);
            recipient.getTransactionList().addTransaction(temp);
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
        }
    }

    public Transaction deleteTransactionFromUser(Integer idUser, UUID id)
            throws TransactionNotFoundException, UserNotFoundException {
        User user = users.getUserById(idUser);
        return user.getTransactionList().deleteTransaction(id);
    }

    public Transaction[] getTransactionFromUser(Integer id) throws UserNotFoundException {
        User user = users.getUserById(id);
        return user.getTransactionList().toArray();
    }

    public Transaction[] getUnpairedTransaction() {
        Transaction[] list = getAllTransaction();
        TransactionsLinkedList unpairedTransactions = new TransactionsLinkedList();
        for (int i = 0; list != null && i < list.length; i++) {
            boolean flag = false;
            for (int j = 0; j < list.length; j++) {
                if (j == i) continue;
                if (list[i].getId() == list[j].getId()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                unpairedTransactions.addTransaction(list[i]);
            }
        }
        return unpairedTransactions.toArray();
    }

    private Transaction[] getAllTransaction() {
        TransactionsLinkedList list = new TransactionsLinkedList();
        for (int i = 1; i <= users.getSize(); i++) {
            User user = users.getUserByIndex(i);
            Transaction[] transactions = user.getTransactionList().toArray();
            if (transactions != null) {
                for (Transaction transaction : transactions) {
                    list.addTransaction(transaction);
                }
            }
        }
        return list.toArray();
    }
}
