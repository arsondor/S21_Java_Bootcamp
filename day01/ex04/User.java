
public class User {
    private final Integer identifier;
    private String name;
    private Integer balance;
    private final TransactionsLinkedList transactionList;

    public User(String name, Integer balance) {
        identifier = UserIdsGenerator.getInstance().generateId();
        setName(name);
        setBalance(balance);
        transactionList = new TransactionsLinkedList();
    }

    public Integer getId() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        if (balance < 0) balance = 0;
        this.balance = balance;
    }

    public TransactionsLinkedList getTransactionList() {
        return transactionList;
    }
}