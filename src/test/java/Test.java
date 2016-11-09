import java.util.UUID;

/**
 * Created by Bob on 2016/10/10.
 */
public class Test {
  public static void main(String[] args) {
    String replace = UUID.randomUUID().toString().replace("-", "");
    System.out.println(replace);
  }
}
