import java.text.SimpleDateFormat;
import java.util.Date;

public class BankControllerTest {

    public static void main(String[] args) {
        Database db = Database.getInstance();
        for (BankAccount bankAccount:db.bankAccountArrayList) {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(bankAccount.getCustomer().getDateOfBirth());
                System.out.println(BankController.getAge(date));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
