import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashingTheMap lul =  new HashingTheMap<>();
        for (int i = 0; i <140; i++) {
            lul.add(i,i);
        }
        System.out.println(lul.getValue(3));
    }
}
