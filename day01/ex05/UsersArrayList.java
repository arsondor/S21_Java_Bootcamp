public class UsersArrayList implements UsersList {
    private int size = 0;
    private User[] users = new User[10];

    public void addUser(User user) {
        if (size <= users.length) {
            User[] temp_users = new User[size + 10];
            System.arraycopy(users, 0, temp_users, 0, users.length);
            this.users = temp_users;
        }
        this.users[size] = user;
        size++;
    }

    public User getUserById(int j) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getId() == j) {
                return users[i];
            }
        }
        throw new UserNotFoundException("Id not found");
    }

    public User getUserByIndex(int i) throws UserNotFoundException {
        if (i <= this.size && i >= 1) {
            return users[i - 1];
        }
        throw new UserNotFoundException("Index not found");
    }

    public int getSize() {
        return size;
    }

}
