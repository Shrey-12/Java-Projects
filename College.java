
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

abstract class User {
    static Scanner sc=new Scanner(System.in);

    final private String name;
    private LocalDate dateOfBirth;
    private int userID;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public int getBirthYear(){
        return dateOfBirth.getYear();
    }

    public int getUserID() {return userID;}

    public User(String name,LocalDate dateOfBirth,int userID){
        this.name=name;
        this.dateOfBirth=dateOfBirth;
        this.userID=userID;
    }

    public void setDetails(ArrayList<User>userBase){
//        System.out.println("Enter name: ");
//        this.name=sc.nextLine();
        System.out.println("Enter date of Birth in yyyy-mm-dd format: ");

        String tempDOB=sc.nextLine();
        this.dateOfBirth=LocalDate.parse(tempDOB);

        sc.nextLine();
        System.out.println("Enter user Id: ");
        this.userID=sc.nextInt();
        sc.nextLine();
    }

    public void showDetails(int birthYear){
        if(birthYear==this.dateOfBirth.getYear())
        {
            System.out.println("Name: "+this.name);
            System.out.println("DATE OF BIRTH: "+this.dateOfBirth);
            System.out.println("user ID: "+this.userID);
        }
    }
}

final class Student extends User{

    private int rollNumber;
    private int semester;
    private float cpi;

    public Student(String name,LocalDate dateOfBirth,int userID,int rollNumber,int semester,float cpi){
        super(name,dateOfBirth,userID);
        this.rollNumber=rollNumber;
        this.semester=semester;
        this.cpi=cpi;
    }

    public boolean rollnoIsPresent(ArrayList<User>userBase,int roll){
        for (User user : userBase) {
            if (user instanceof Student) {
                if (((Student) user).rollNumber == roll) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    final public void setDetails(ArrayList<User>userBase){
        super.setDetails(userBase);
        System.out.println("Enter rollNumber: ");
        int roll=sc.nextInt();

        //program to check if the roll number is unique
        while(rollnoIsPresent(userBase,roll)){
            System.out.println("Roll number is already present try re entering: ");
            roll=sc.nextInt();
        }
        this.rollNumber=roll;

        System.out.println("Enter semester number: ");
        this.semester=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter CPI: ");
        cpi= sc.nextFloat();
    }

    //getters
    public int getRollNumber() {
        return rollNumber;
    }

    @Override
    final public void showDetails(int birthYear){
        super.showDetails(birthYear);
        if(birthYear==getDateOfBirth().getYear())
        {
            System.out.println("rollNumber: "+this.rollNumber);
            System.out.println("semester Number: "+this.semester);
            System.out.println("CPI: "+this.cpi);
        }
    }

}

final class Staff extends User{
    int employeeID;
    String sectionName;
    String designation;

    public int getEmployeeID() {
        return employeeID;
    }

    public Staff(String name,LocalDate dateOfBirth,int userID,int employeeID,String sectionName,String designation){
        super(name,dateOfBirth,userID);
        this.employeeID=employeeID;
        this.sectionName=sectionName;
        this.designation=designation;

    }

    public boolean checkDesignation(Staff s1, Staff s2){
        return s1.designation.equals(s2.designation);
    }

    public boolean employeeIDisPresent(ArrayList<User>userBase,int ID){
        for (User user : userBase) {
            if (user instanceof Staff) {
                if (((Staff) user).employeeID == ID) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    final public void setDetails(ArrayList<User>userBase){
        super.setDetails(userBase);
        System.out.println("Enter Employee ID: ");
        int ID=sc.nextInt();
        while(employeeIDisPresent(userBase,ID)){
            System.out.println("Employee ID is already present try re entering: ");
            ID=sc.nextInt();
        }
        sc.nextLine();
        this.employeeID=ID;
        System.out.println("Enter sectionName: ");
        this.sectionName=sc.nextLine();
        System.out.println("Enter Designation: ");
        this.sectionName=sc.nextLine();
    }

    @Override
    final public void showDetails(int birthYear){
        super.showDetails(birthYear);
        if(birthYear==getDateOfBirth().getYear())
        {
            System.out.println("Employee ID: "+this.employeeID);
            System.out.println("Section Name: "+this.sectionName);
            System.out.println("Designation: "+this.designation);
        }


    }
}

final class Faculty extends User{
    int employeeID;
    String departmentName;
    int noOfPublications;

    public int getEmployeeID() {
        return employeeID;
    }

    public Faculty(String name, LocalDate dateOfBirth, int userID, int employeeID, String departmentName, int noOfPublications){
        super(name,dateOfBirth,userID);
        this.employeeID=employeeID;
        this.departmentName=departmentName;
        this.noOfPublications=noOfPublications;
    }

    public boolean checkDepartment(Faculty f1,Faculty f2){
        return f1.departmentName.equals(f2.departmentName);
    }

    public boolean employeeIDisPresent(ArrayList<User>userBase,int ID){
        for (User user : userBase) {
            if (user instanceof Staff) {
                if (((Staff) user).employeeID == ID) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    final public void setDetails(ArrayList<User>userBase){
        super.setDetails(userBase);
        System.out.println("Enter Employee ID: ");
        int ID=sc.nextInt();
        while(employeeIDisPresent(userBase,ID)){
            System.out.println("Employee ID is already present try re entering: ");
            ID=sc.nextInt();
        }
        this.employeeID=ID;
        sc.nextLine();

        System.out.println("Enter department Name: ");
        this.departmentName=sc.nextLine();
        System.out.println("Enter number of publications: ");
        this.noOfPublications=sc.nextInt();
        sc.nextLine();
    }

    @Override
    final public void showDetails(int birthYear){
        super.showDetails(birthYear);
        if(birthYear==getDateOfBirth().getYear())
        {
            System.out.println("employeeID: "+this.employeeID);
            System.out.println("department Name: "+this.departmentName);
            System.out.println("number of publications: "+this.noOfPublications);
        }

    }
}

public class College{
    static Scanner sc=new Scanner(System.in);

    public static Student studentInput(){
        System.out.println("Enter name: ");
        String name=sc.nextLine();
        System.out.println("Enter date of Birth in yyyy-mm-dd format: ");
        String tempDOB=sc.nextLine();
        LocalDate dateOfBirth=LocalDate.parse(tempDOB);

        System.out.println("Enter userID");
        int userID=sc.nextInt();
        System.out.println("Enter rollNumber");
        int rollNumber=sc.nextInt();
        System.out.println("Enter semester: ");
        int semester=sc.nextInt();
        System.out.println("Enter CPI: ");
        sc.nextLine();
        float cpi=sc.nextFloat();
        return new Student(name,dateOfBirth,userID,rollNumber,semester,cpi);
    }

    public static Staff staffInput(){
        System.out.println("Enter name: ");
        String name=sc.nextLine();
        System.out.println("Enter date of Birth in yyyy-mm-dd format: ");

        String tempDOB=sc.nextLine();
        LocalDate dateOfBirth=LocalDate.parse(tempDOB);
        System.out.println("Enter userID");
        int userID=sc.nextInt();
        System.out.println("Enter employeeID: ");
        int employeeID=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter section Name: ");
        String sectionName=sc.nextLine();
        System.out.println("Enter designation: ");
        String designation=sc.nextLine();
        return new Staff(name,dateOfBirth,userID,employeeID,sectionName,designation);

    }

    public static Faculty facultyInput(){
        System.out.println("Enter name: ");
        String name=sc.nextLine();
        System.out.println("Enter date of Birth in yyyy-mm-dd format: ");

        String tempDOB=sc.nextLine();
        LocalDate dateOfBirth=LocalDate.parse(tempDOB);
        System.out.println("Enter userID");
        int userID=sc.nextInt();
        System.out.println("Enter employeeID");
        int employeeID=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter department name");
        String department=sc.nextLine();
        System.out.println("Enter noOfPublications");
        int noOfPublications=sc.nextInt();
        sc.nextLine();
        return new Faculty(name,dateOfBirth,userID,employeeID,department,noOfPublications);

    }
    public static void userChoice(ArrayList<User>userBase){
        System.out.println("Choose user:\n0.User\n1.Student\n2.Staff\n3.Faculty\n");
        int choice=sc.nextInt();
        sc.nextLine();
        if(choice==0){
            System.out.println("Object of an abstract class cannot be created");
            userChoice(userBase);
        }
        switch (choice) {
            case 1 -> userBase.add(studentInput());
            case 2 -> userBase.add(staffInput());
            case 3 -> userBase.add(facultyInput());
        }
    }

    public static int searchRollno(ArrayList<User>userBase,int rollNo){
        for (int index=0;index<userBase.size();index++) {
            User user=userBase.get(index);
            if (user instanceof Student) {
                if (((Student) user).getRollNumber() ==rollNo ) {
                    return index;
                }
            }
        }
        return -1;
    }

    public static int searchEmployeeIDStaff(ArrayList<User>userBase,int EmployeeId){
        for (int index=0;index<userBase.size();index++) {
            User user=userBase.get(index);
            if (user instanceof Staff) {
                if (((Staff) user).getEmployeeID() ==EmployeeId ) {
                    return index;
                }
            }
        }
        return -1;
    }

    public static int searchEmployeeIDFaculty(ArrayList<User>userBase,int EmployeeId){
        for (int index=0;index<userBase.size();index++) {
            User user=userBase.get(index);
            if (user instanceof Faculty) {
                if (((Faculty) user).getEmployeeID() ==EmployeeId ) {
                    return index;
                }
            }
        }
        return -1;
    }

//    public static int searchUserID(ArrayList<User>userBase,int UserID){
//        for (int index=0;index<userBase.size();index++) {
//            User user=userBase.get(index);
//                if (user.getUserID() ==UserID) {
//                    return index;
//                }
//            }
//        return -1;
//    }

    public static void setDetailsStudents(ArrayList<User>userBase){
        System.out.println("Enter roll no: ");
        int rollno=sc.nextInt();
        sc.nextLine();
        int index=searchRollno(userBase,rollno);
        if(index==-1){
            System.out.println("Student is not present");
        }
        else{
            Student s= (Student) userBase.get(index);
            s.setDetails(userBase);

        }
    }

    public static void setDetailsFaculty(ArrayList<User>userBase){
        System.out.println("Enter employee ID: ");
        int employeeID=sc.nextInt();
        sc.nextLine();
        int index=searchEmployeeIDFaculty(userBase,employeeID);
        if(index==-1){
            System.out.println("Faculty is not present");
        }
        else{
            Faculty f= (Faculty) userBase.get(index);
            f.setDetails(userBase);
        }
    }

    public static void setDetailsStaff(ArrayList<User>userBase){
        System.out.println("Enter employee ID: ");
        int employeeID=sc.nextInt();
        sc.nextLine();
        int index=searchEmployeeIDStaff(userBase,employeeID);
        if(index==-1){
            System.out.println("Staff is not present");
        }
        else{
            Staff s= (Staff) userBase.get(index);
            s.setDetails(userBase);
        }
    }

    public static void setDetailsMain(ArrayList<User>userBase){
        System.out.println();
        System.out.println("Choose user:\n1.Student\n2.Staff\n3.Faculty\n");
        int choice=sc.nextInt();
        sc.nextLine();
        if(choice<1||choice>3)
            System.out.println("Invalid Input");
        switch (choice) {
            case 1-> setDetailsStudents(userBase);
            case 2->setDetailsStaff(userBase);
            case 3->setDetailsFaculty(userBase);
        }
    }

    public static void showDetailsStudent(ArrayList<User>userBase){
        System.out.println("Enter rollno: ");
        int rollno=sc.nextInt();
        sc.nextLine();
        int index=searchRollno(userBase,rollno);
        if(index==-1){
            System.out.println("Student is not present");
        }
        else{
            Student s= (Student) userBase.get(index);
            s.showDetails(s.getBirthYear());
        }
    }

    public static void showDetailsStaff(ArrayList<User>userBase){
        System.out.println("Enter employeeID");
        int employeeID=sc.nextInt();
        sc.nextLine();
        int index=searchEmployeeIDStaff(userBase,employeeID);
        if(index==-1){
            System.out.println("Staff is not present");
        }
        else{
            Staff s= (Staff) userBase.get(index);
            s.showDetails(s.getBirthYear());
        }
    }

    public static void showDetailsFaculty(ArrayList<User>userBase){
        System.out.println("Enter Employee ID: ");
        int employeeID=sc.nextInt();
        sc.nextLine();
        int index=searchEmployeeIDFaculty(userBase,employeeID);
        if(index==-1){
            System.out.println("Faculty is not present");
        }
        else{
            Faculty f= (Faculty) userBase.get(index);
            f.showDetails(f.getBirthYear());
        }
    }

    public static void showDetailsMain(ArrayList<User>userBase){
        System.out.println();
        System.out.println("Choose user:\n1.Student\n2.Staff\n3.Faculty\n");
        int choice=sc.nextInt();
        sc.nextLine();
        if(choice<1||choice>3)
            System.out.println("Invalid Input");
        switch (choice) {
            case 1-> showDetailsStudent(userBase);
            case 2->showDetailsStaff(userBase);
            case 3->showDetailsFaculty(userBase);
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter number of Users in database: \n");
        int n=sc.nextInt();
        sc.nextLine();
       ArrayList<User>userBase= new ArrayList<>(n);

        int k=1;
        while(k!=5){
            System.out.println();
            System.out.println("1)Create Object of User,Student,Staff,Faculty\n2)set details\n3)Display details\n");
            k=sc.nextInt();
            switch (k){
                case 1->userChoice(userBase);
                case 2->setDetailsMain(userBase);
                case 3->showDetailsMain(userBase);
            }
        }

    }
}
