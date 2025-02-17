public interface UsersList {
    void addUser(User user);

    User getUserById(int i) throws UserNotFoundException;

    User getUserByIndex(int i) throws UserNotFoundException;

    int getSize();

}
