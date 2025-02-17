
public class Program {
    public static void main(String [] argv){
        User us1 = new User("Anna", 31);
        User us2 = new User("Mike", 31);
        User us3 = new User("Artem", 31);
        User us4 = new User("Maks", 31);

        System.out.println(us1.getId());
        System.out.println(us2.getId());
        System.out.println(us3.getId());
        System.out.println(us4.getId());

    }
}
