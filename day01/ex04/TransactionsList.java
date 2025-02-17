import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction tr) throws TransactionNotFoundException;

    Transaction deleteTransaction(UUID id);

    Transaction[] toArray();
}
