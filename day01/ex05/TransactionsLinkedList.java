import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private static class Node {
        private Node prev;
        private Node next;
        private final Transaction data;

        Node(Transaction data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public TransactionsLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addTransaction(Transaction tr) {
        Node temp = new Node(tr);
        if (head == null) {
            head = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
        }
        tail = temp;
        size++;
    }

    public Transaction deleteTransaction(UUID id) throws TransactionNotFoundException {
        boolean flag = false;
        Transaction removed = null;
        if (id != null) {
            Node temp = head;
            while (temp != null) {
                if (temp.data.getId().equals(id)) {
                    removed = temp.data;
                    if (temp.prev == null && temp.next == null) {
                        head = null;
                        tail = null;
                    } else if (temp.prev == null) {
                        head = temp.next;
                        head.prev = null;
                    } else if (temp.next == null) {
                        tail = temp.prev;
                        tail.next = null;
                    } else {
                        temp.prev.next = temp.next;
                        temp.next.prev = temp.prev;
                    }
                    size--;
                    flag = true;
                }
                temp = temp.next;
            }
        }
        if (!flag) throw new TransactionNotFoundException("UUID not found");
        return removed;
    }


    public Transaction[] toArray() {
        if (size != 0) {
            Transaction[] array = new Transaction[size];
            Node temp = head;
            int i = 0;
            while (temp != null) {
                array[i] = temp.data;
                temp = temp.next;
                i++;
            }
            return array;
        } else {
            return null;
        }
    }
}
