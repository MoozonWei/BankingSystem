public class DatabaseTest {
    Database db = Database.getInstance();
    public static void main(String[] args) {
        Database db = Database.getInstance();

//        System.out.println(db.customerArrayList.toString());
//        System.out.println(db.bankAccountArrayList.toString());
//        System.out.println(db.currentAccountArrayList.toString());
//        System.out.println(db.juniorAccountArrayList.toString());
//        System.out.println(db.saverAccountArrayList.toString());
//        System.out.println(db.customerArrayList.get(1).getDateOfBirth());

        //
        BankController.depositSaverFunds_Cl("100001", "1.1");
//        String accNO = "100001";
//        String amount = "1.1";
//        db.bankAccountArrayList.get(BankController.indexOfBankArraylist(accNO)).addBalance(Double.parseDouble(amount));
//        db.saverAccountArrayList.get(BankController.indexOfSaverArraylist(accNO)).addBalance(Double.parseDouble(amount));
        System.out.println(db.bankAccountArrayList.get(BankController.indexOfBankArraylist("100001")).getBalance());


    }


}
