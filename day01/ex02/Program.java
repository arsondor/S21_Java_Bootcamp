public class Program {
    public static void main(String[] args) {
        User us1 = new User("Artem", 600);
        User us2 = new User("Petya", 400);
        User us3 = new User("Anya", 366);
        User us4 = new User("Nastya", 600);
        User us5 = new User("Danil", 400);
        User us6 = new User("Oleg", 366);
        User us7 = new User("Dasha", 680);
        User us8 = new User("Mike", 400);
        User us9 = new User("Dima", 366);
        User us10 = new User("John", 600);
        User us11 = new User("Sergey", 400);
        User us12 = new User("Lena", 366);
        UsersArrayList usersList = new UsersArrayList();
        usersList.addUser(us1);
        usersList.addUser(us2);
        usersList.addUser(us3);
        usersList.addUser(us4);
        usersList.addUser(us5);
        usersList.addUser(us6);
        usersList.addUser(us7);
        usersList.addUser(us8);
        usersList.addUser(us9);
        usersList.addUser(us10);
        usersList.addUser(us11);
        System.out.println("Size of list: " + usersList.getSize());
        User temp = usersList.getUserByIndex(5);
        System.out.println("User with index 5: " + temp.getName());
        temp = usersList.getUserById(2);
        System.out.println("User with id 2: " + temp.getName());
        try {
            temp = usersList.getUserById(12);
        } catch (UserNotFoundException e) {
            System.out.println("Id 12 not found");
        }
    }
}