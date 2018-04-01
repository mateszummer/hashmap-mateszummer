import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashingTheMap lul =  new HashingTheMap<>();
        for (int i = 0; i <17; i++) {
            lul.add(i,i);
        }
        System.out.println(lul.getValue(2));
        lul.remove(2);
        System.out.println(3);

    }
}
