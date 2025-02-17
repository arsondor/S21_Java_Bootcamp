import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction tr) throws TransactionNotFoundException;

    void deleteTransaction(UUID id);

    Transaction[] toArray();
}
