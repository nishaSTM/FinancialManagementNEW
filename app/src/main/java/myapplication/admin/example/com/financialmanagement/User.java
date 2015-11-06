    package myapplication.admin.example.com.financialmanagement;

    import java.io.Serializable;

    public class User implements Serializable {
        public String note;
        public String transaction_date;
        public String typeOfItem;
        public String sourceOfPayment;
        public int money;
        public String month;
        public String year;
        public String day;


        public User(String note, String transaction_date,String typeOfItem, String sourceOfPayment,int money,String month,String year,String day) {
           this.note = note;
           this.transaction_date = transaction_date;
           this.typeOfItem = typeOfItem;
           this.sourceOfPayment = sourceOfPayment;
           this.money = money;
           this.month=month;
           this.year=year;
           this.day=day;
        }


    }


