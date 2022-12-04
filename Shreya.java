package labExam;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;


public class Shreya{
    static Scanner sc=new Scanner(System.in);

    public static Admin AdminInput(){
        System.out.println("enter userId: ");
        int userId=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter name: ");
        String name=sc.nextLine();
        System.out.println("Enter date of birth : ");
        String dateOfBirth=sc.nextLine();
        System.out.println("Enter address: ");
        String address=sc.next();
        sc.nextLine();
        System.out.println("Enter PAN: ");
        String PAN=sc.nextLine();
        System.out.println("Enter date of joining");
        String dateOfJoining=sc.next();
        System.out.println("Enter salary: ");
        int salary=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter permissible operations");
        String permissibleOperations=sc.nextLine();
        return new Admin(userId,name,dateOfBirth,address,PAN,dateOfJoining,salary,permissibleOperations);


    }

    public static General GeneralInput(){
        System.out.println("enter userId: ");
        int userId=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter name: ");
        String name=sc.nextLine();
        System.out.println("Enter date of birth : ");
        String dateOfBirth=sc.nextLine();
        System.out.println("Enter address: ");
        String address=sc.nextLine();
        System.out.println("Enter PAN: ");
        String PAN=sc.nextLine();
        System.out.println("Enter date of joining");
        String dateOfJoining=sc.next();
        System.out.println("Enter salary: ");
        int salary=sc.nextInt();
        sc.nextLine();
        System.out.println("duty hours");
        int dutyHours=sc.nextInt();
        return new General(userId,name,dateOfBirth,address,PAN,dateOfJoining,salary,dutyHours);

    }

    public static Item itemInput(ArrayList<Item>itemList){
        System.out.println("Enter item code");
        int itemCode=sc.nextInt();
        if(Admin.searchItemNo(itemList, itemCode)!=-1){
            System.out.println("item is already present");
            return new Item(-1,0,0);
        }
        System.out.println("Enter price");
        int price=sc.nextInt();
        System.out.println("Enter available quantity");
        int availableQuantity=sc.nextInt();
        return new Item(itemCode,price,availableQuantity);
    }

    public static void AdminMenu(Admin m,ArrayList<Item>itemList){
        int choice=0;
        while(choice!=4) {
            System.out.println("1.addNewStock\n2.deleteStock\n3.modifyStock\n4.exit");
            choice=sc.nextInt();
            switch (choice) {
                case 1:
                    m.addNewStock(itemList);
                    break;
                case 2:
                    System.out.println("Enter item code");
                    int itemCode=sc.nextInt();
                    m.deleteStock(itemCode,itemList);
                    break;
                case 3:
                    System.out.println("Enter itemCode");
                    itemCode=sc.nextInt();
                    System.out.println("Enter price");
                    int price=sc.nextInt();
                    System.out.println("Enter available quantity: ");
                    int availableQuantity=sc.nextInt();
                    m.modifyStock(itemCode,itemList,price,availableQuantity);
                    break;
            }
        }
    }

    public static void GeneralMenu(General g,ArrayList<Item>itemList,ArrayList<Sell>sellList,ArrayList<Return>returnList){
        int choice=0;
        while(choice!=5){
            System.out.println("1.sellItem\n2.returnItem\n3.displayStock\n4.displaySell\n5.exit");
            choice=sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter item Code");
                    int itemCode = sc.nextInt();
                    System.out.println("Enter quantity");
                    int quantity = sc.nextInt();
                    System.out.println("Date of sell");
                    LocalDate sellDate=inputDate();
                    g.sellItem(sellList, itemList, itemCode, quantity,sellDate);
                }
                case 2 -> {
                    System.out.println("Enter item Code");
                    int itemCode = sc.nextInt();
                    System.out.println("Enter quantity");
                    int quantity = sc.nextInt();
                    g.returnItem(returnList, itemList, itemCode,quantity);
                }
                case 3 -> {
                    System.out.println("Enter Item Code : ");
                    int itemCode = sc.nextInt();
                    sc.nextLine();
                    g.displayStock(itemCode, itemList);
                }
                case 4 -> {
                    System.out.println("Enter Start Date : ");
                    LocalDate startDate = inputDate();
                    System.out.println("Enter End Date : ");
                    LocalDate endDate = inputDate();
                    g.displaySell(sellList, startDate, endDate);
                }

            }
        }
    }

    public static LocalDate inputDate(){
        int dayOfMonth = sc.nextInt();
        int month = sc.nextInt();
        int year = sc.nextInt();
        return LocalDate.of(year, month, dayOfMonth);
    }
    public static void main(String[] args) {
        ArrayList<Item>itemList=new ArrayList<>();
        ArrayList<Sell>sellList=new ArrayList<>();
        ArrayList<Return>returnList=new ArrayList<>();

        String type=args[0];
        String name=args[1];

        if(type.equals("Admin")){
            Admin m = AdminInput();
            AdminMenu(m, itemList);
        } else if (type.equals("General")) {
            General g = GeneralInput();
            GeneralMenu(g, itemList, sellList, returnList);
        }
    }


}




class User{
    static Scanner sc=new Scanner(System.in);
    private int userID;
    private String name;
    private String dateOfBirth;
    private String address;
    private String PAN;

    public User(int userID,String name,String dateOfBirth,String address,String PAN){
        this.userID=userID;
        this.name=name;
        this.dateOfBirth=dateOfBirth;
        this.address=address;
        this.PAN=PAN;
    }
}
class Admin extends User{
    private String dateOfJoining;
    private int salary;
    private String permissibleOperations;

    public Admin(int userID, String name, String dateOfBirth, String address, String PAN,String dateOfJoining, int salary, String permissibleOperations){
        super(userID,name,dateOfBirth,address,PAN);
        this.dateOfJoining=dateOfJoining;
        this.salary=salary;
        this.permissibleOperations=permissibleOperations;
    }

    public static int searchItemNo(ArrayList<Item>itemList, int itemCode){
        for (int index=0;index<itemList.size();index++) {
            if (itemList.get(index).getItemCode()==itemCode) {
                return index;
            }
        }
        return -1;
    }
    public void addNewStock(ArrayList<Item>itemList){
        Item i = Shreya.itemInput(itemList);
        if(i.getItemCode()==-1){
            return;
        }
        itemList.add(i);
    }

    public void deleteStock(int itemCode,ArrayList<Item>itemList){
        int index=searchItemNo(itemList,itemCode);
        if(itemList.get(index).getAvailableQuantity()!=0){
            System.out.println("Quantity not 0!");

        }
        else{
            itemList.remove(index);
            System.out.println("Item: "+itemCode+" has been removed");
        }
    }

    public void modifyStock(int itemCode,ArrayList<Item>itemList,int price,int availableQuantity){
        int index=searchItemNo(itemList,itemCode);
        if(index==-1){
            System.out.println("Item not found");
        }
        else{
            System.out.println("1.Modify price\n2.Modify available Quantity\n");
            int choice=sc.nextInt();
            switch (choice) {
                case 1 -> itemList.get(index).setPrice(price);
                case 2 -> itemList.get(index).setAvailableQuantity(availableQuantity);
            }
        }

    }
}
class General extends User{
    private String dateOfJoining;
    private int salary;
    private int dutyHourPerDay;

    public General(int userID,String name,String dateOfBirth,String address,String PAN,String dateOfJoining,int salary,int dutyHourPerDay){
        super(userID,name,dateOfBirth,address,PAN);
        this.dateOfJoining=dateOfJoining;
        this.salary=salary;
        this.dutyHourPerDay=dutyHourPerDay;
    }

    public static int searchItemNo(ArrayList<Item>itemList, int itemCode){
        for (int index=0;index<itemList.size();index++) {
            if (itemList.get(index).getItemCode()==itemCode) {
                return index;
            }
        }
        return -1;
    }

    public void sellItem(ArrayList<Sell>sellList,ArrayList<Item>itemList,int itemCode,int quantity,LocalDate sellDate){
        Sell s=new Sell();
        sellList.add(s);
        int index=searchItemNo(itemList,itemCode);
        if(index==-1){
            System.out.println("Item not found");
            return;
        }
        int price=itemList.get(index).getPrice();
        int availableQuantity=itemList.get(index).getAvailableQuantity();

        availableQuantity=availableQuantity-quantity;
        s.setItemCode(itemCode);
        s.setQuantity(availableQuantity);
        s.setDateOfSell(sellDate);
        (new Admin(0,null,null,null,null,null,0,null)).modifyStock(itemCode,itemList,price,availableQuantity);
    }

    public void returnItem(ArrayList<Return>returnList,ArrayList<Item>itemList,int itemCode,int quantity){
        Return r=new Return();
        returnList.add(r);
        int index=searchItemNo(itemList,itemCode);
        if(index!=-1){
            Item i=itemList.get(index);
            if(i instanceof FoodItem){
                System.out.println("Food items cannot be returned");
                return;
            }
            int price=itemList.get(index).getPrice();
            int availableQuantity=itemList.get(index).getAvailableQuantity();
            availableQuantity=availableQuantity+quantity;
            (new Admin(0,null,null,null,null,null,0,null)).modifyStock(itemCode,itemList,price,availableQuantity);
        }
        System.out.println("item not found");
    }

    public void displayStock(int itemCode,ArrayList<Item>itemList){
        int index=searchItemNo(itemList,itemCode);
        int price=itemList.get(index).getPrice();
        int availableQuantity=itemList.get(index).getAvailableQuantity();
        System.out.println("Price is: "+price);
        System.out.println("Available quantity is: "+availableQuantity);

    }
    public void displaySell(ArrayList<Sell>sellList, LocalDate startDate, LocalDate endDate){
        for(int i=0;i<sellList.size();i++){
            LocalDate date=sellList.get(i).getDateOfSell();
            if(date.isAfter(startDate) && date.isBefore(endDate)){
                sellList.get(i).showDetailsSell(sellList.get(i).getItemCode());

            }

        }

    }



}
class Item{
    private int itemCode;
    private int price;
    private int availableQuantity;

    public Item(){

    }

    public Item(int itemCode,int price,int availableQuantity){
        this.itemCode=itemCode;
        this.price=price;
        this.availableQuantity=availableQuantity;
    }

    public void showDetails(int itemCode){
        if(this.itemCode==itemCode){
            System.out.println("price "+price);
            System.out.println("available Quantity "+availableQuantity);
        }

    }
    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getItemCode() {
        return itemCode;
    }

    public int getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}
class FoodItem extends Item{

    private String dateOfExpiry;
    public FoodItem(){

    }

    public FoodItem(int itemCode,int price, int availableQuantity,String dateOfExpiry){
        super(itemCode,price,availableQuantity);
        this.dateOfExpiry=dateOfExpiry;
    }

}
class NonFoodItem extends Item{
    private int returnCount;
    public NonFoodItem(){

    }
    public NonFoodItem(int itemCode,int price, int availableQuantity,int returnCount){
        super(itemCode,price,availableQuantity);
        this.returnCount=returnCount;
    }

}
class Sell{
    private LocalDate dateOfSell;
    private int itemCode;
    private int quantity;
    private int returnAmount;

    public LocalDate getDateOfSell() {
        return dateOfSell;
    }

    public void setDateOfSell(LocalDate dateOfSell) {
        this.dateOfSell = dateOfSell;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(int returnAmount) {
        this.returnAmount = returnAmount;
    }

    public Sell(){

    }

    public Sell(LocalDate dateOfSell,int itemCode,int quantity,int returnAmount){
        this.dateOfSell=dateOfSell;
        this.itemCode=itemCode;
        this.quantity=quantity;
        this.returnAmount=returnAmount;
    }

    public void showDetailsSell(int itemCode){
        if(this.itemCode==itemCode){
            System.out.println("itemCode "+itemCode);
            System.out.println("quantity "+quantity);
        }

    }

}
class Return{
    private String dateOfReturn;
    private int itemCode;
    private int quantity;
    private int returnAmount;

    public String getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(int returnAmount) {
        this.returnAmount = returnAmount;
    }

    public Return(){

    }

    public Return(String dateOfReturn,int itemCode,int quantity,int returnAmount){
        this.dateOfReturn=dateOfReturn;
        this.itemCode=itemCode;
        this.quantity=quantity;
        this.returnAmount=returnAmount;
    }

}
