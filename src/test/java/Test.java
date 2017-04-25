import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Bob on 2017/4/25.
 */
@Setter
@Getter
@ToString
public class Test {
  private String name;
  private int age;

  public static void main(String[] args) {
    Test test = new Test();
    test.setName("name");
    test.setAge(20);
    System.out.println(test);
  }

}
