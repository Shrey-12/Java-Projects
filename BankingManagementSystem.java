import java.util.ArrayList;
import java.util.Scanner;

public class BankingManagementSystem {
    static Scanner sc=new Scanner(System.in);
    static class Account{
        private int accountNumber;
        private String accountType;
        private String serviceBranchIFSC;
        private float minimumBalance;
        private float availableBalance;
        private int customerID;
        private String customerName;
        static private int totalAccountCreated=0;

        public Account(){
            this.accountNumber=-1;

        }
        public void setDetails(){
            System.out.println("Enter account number: ");
            this.accountNumber=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter account Type: Savings,Current,Salary: ");
            this.accountType=sc.nextLine();
            System.out.println("Enter service Branch IFSC: ");
            this.serviceBranchIFSC=sc.nextLine();
            System.out.println("Enter minimum Balance: ");
            this.minimumBalance=sc.nextInt();
            this.availableBalance=minimumBalance;
            System.out.println("Enter customer ID ");
            this.customerID=sc.nextInt();
            System.out.println("Enter customer Name: ");
            sc.nextLine();
            this.customerName=sc.nextLine();
            totalAccountCreated++;
        }
        public String getDetails(int accountNumber){
            return "Account Number: "+accountNumber+"\nAccount Type: "+this.accountType+"\nserviceBranchIFSC"+this.serviceBranchIFSC+"\nminimum Balance: "+this.minimumBalance+"\nAvailable Balance: "+this.availableBalance+"\ncustomerID: "+this.customerID+"\ncustomer Name: "+this.customerName+"\n";
        }
        public void updateDetails(int accountNumber){
            if(accountNumber==this.accountNumber){
                int k=1;
                while(k!=4){
                    System.out.println("Update menu:\n1.accountType\n2.minimumBalance\n3.customerName\n4.exit\n");
                    k=sc.nextInt();
                    switch (k) {
                        case 1 -> {
                            System.out.println("Enter new account Type: ");
                            this.accountType = sc.nextLine();
                        }
                        case 2 -> {
                            System.out.println("Enter new minimum Balance: ");
                            this.minimumBalance = sc.nextInt();
                        }
                        case 3 -> {
                            System.out.println("Enter new customer Name: ");
                            sc.nextLine();
                            this.customerName = sc.nextLine();
                        }
                    }

                }
            }
        }


        public void deposit(int accountNumber,float amount){
            if(accountNumber==this.accountNumber){
                availableBalance+=amount;
                System.out.println(amount+" has been deposited!");
            }
        }

        public void withdraw(int accountNumber,float amount){
            if(accountNumber==this.accountNumber) {
                if (availableBalance-amount > minimumBalance) {
                    availableBalance -= amount;
                    System.out.println(amount + " has been withdrawn!");
                } else {
                    System.out.println("You have reached your minimum balance!");
                }
            }
        }

        public void compareAccount(Account account1,Account account2){
            if(account1.availableBalance>account2.availableBalance){
                System.out.println("Account number: "+account1.accountNumber+" has more balance than account number: "+account2.accountNumber);
            }
            else if(account1.availableBalance<account2.availableBalance)
                System.out.println("Account number: "+account2.accountNumber+" has more balance than account number: "+account1.accountNumber);
            else
                System.out.println("Account number: "+account1.accountNumber+" and"+account2.accountNumber+" have equal balance");
        }

        public static int totalAccount(){
            return totalAccountCreated;
        }

        static void updateDetails(ArrayList<Account>arr, int accountNumber) {
            for (Account account : arr) {
                account.updateDetails(accountNumber);
            }
        }

        static void getDetails(ArrayList<Account>arr, int accountNumber){
            for (Account account : arr) {
                System.out.println(account.getDetails(accountNumber));
            }
        }

        static void Deposit(ArrayList<Account>arr,int accountNumber){
            sc.nextLine();
            System.out.println("Enter amount to be deposited: ");
            float amount=sc.nextFloat();
            for (Account account : arr) {
                account.deposit(accountNumber, amount);
            }
        }

        static void Withdraw(ArrayList<Account>arr,int accountNumber){
            sc.nextLine();
            System.out.println("Enter amount to be Withdrawn: ");
            float amount=sc.nextFloat();
            for (Account account : arr) account.withdraw(accountNumber, amount);

        }

        static void CompareAccounts(ArrayList<Account>arr,int accountNumber1,int accountNumber2){
            int accountIndex1=-1,accountIndex2=-1;
            for(int i=0;i<arr.size();i++){
                if(arr.get(i).accountNumber==accountNumber1){
                    accountIndex1=i;
                }
                if(arr.get(i).accountNumber==accountNumber2){
                    accountIndex2=i;
                }
            }
            if(accountIndex2==-1 || accountIndex1==-1) {
                System.out.println("account not found");
            }
            else {
                arr.get(accountIndex1).compareAccount(arr.get(accountIndex1),arr.get(accountIndex2));
            }
        }

        public static void menu(ArrayList<Account>arr,int accountNumber){
            int k=1;
            while(k!=5){
                System.out.println("1.Update Details\n2.Get Details\n3.Deposit\n4.Withdraw\n5.Exit\n");
                k=sc.nextInt();
                switch (k) {
                    case 1 -> updateDetails(arr, accountNumber);
                    case 2 -> getDetails(arr, accountNumber);
                    case 3 -> Deposit(arr, accountNumber);
                    case 4 -> Withdraw(arr, accountNumber);
                }
            }
        }

        public static void main(String[] args) {
            //Array of objects for Account class
            ArrayList<Account>arr=new ArrayList<>();
            int accountNumber;

            int m=1;
            while(m!=6){
                System.out.println("1.Add account\n2.Delete account\n3.Modify details of an account\n4.compare accounts\n5.totalAccountsCreated\n6.exit\n");
                m=sc.nextInt();
                switch (m) {
                    case 1 -> {
                        Account ac = new Account();
                        ac.setDetails();
                        arr.add(ac);
                        System.out.println("Account has been added!");
                    }
                    case 2 -> {
                        System.out.println("Enter account Number");
                        accountNumber = sc.nextInt();
                        for (int i = 0; i < arr.size(); i++) {
                            if (arr.get(i).accountNumber == accountNumber) {
                                arr.remove(i);
                                System.out.println("Account has been removed");
                            }
                        }
                    }
                    case 3 -> {
                        System.out.println("Enter account number");
                        accountNumber = sc.nextInt();
                        for (int i = 0; i < arr.size(); i++) {
                            if (arr.get(i).accountNumber == accountNumber) {
                                menu(arr, accountNumber);
                            } else
                                System.out.println("Account not found to update!");
                        }
                    }
                    case 4 -> {
                        System.out.println("Enter account numbers to compare: ");
                        int accountNumber1 = sc.nextInt();
                        int accountNumber2 = sc.nextInt();
                        CompareAccounts(arr, accountNumber1, accountNumber2);
                    }
                    case 5 -> System.out.println("Total accounts are " + Account.totalAccount());
                }
            }

        }

    }
}
