import java.util.Scanner;
import java.util.Arrays;
public class Main {

    public static void check_ID(int ID, String name, int[] student_ID, String[] student_name) {//method to add new student in system
        int index;//index of array
        boolean flag = true;//flag
        for (int i = 0; i < student_ID.length; i++) {
            if (ID == student_ID[i]) {//if you find ID in the list , student is already exist ,give user index and don't enter him again using flag
                System.out.println("Student is already in the list ,ID index: " + i);
                flag = false;
            }
        }
        if (flag) {//this flag mean: if student is not exist in system
            for (int i = 0; i < student_name.length; i++) {//loop to check frist element has value : 0
                if (student_ID[i] == 0) {
                    student_ID[i] = ID;
                    student_name[i] = name;
                    flag = false;
                    System.out.println("Student \"includng name and ID\" is saved suceessfully");
                    System.out.println("name: " + name + "\nID: " + ID);
                    break;//get out of loop
                }
            }
        }

    }


    public static void set_courses(String[] courses) {//method to set 5 courses
        Scanner input = new Scanner(System.in);//new Scanner
        System.out.println("you are allowed to enter 5 courses");
        for (int i = 0; i < courses.length; i++) {//loop allow user to enter 5 courses
            System.out.println("enter course with index " + i);
            courses[i] = input.nextLine();
        }
        System.out.println("courses added successfully, the courses is: ");
        for (int i = 0; i < courses.length; i++) {//print courses
            System.out.print(courses[i] + "   ");
        }

    }


    public static void grades(int id, int[] stdentid, String[] courses, double[][] gradeing) {//method to enter grade to student
        int index = 0;//index to get student ID index
        Scanner input = new Scanner(System.in);

        boolean frist_flag = true;//flag to check ID " avoid logical error

        outer:
//label
        while (frist_flag) {
            for (int i = 0; i < stdentid.length; i++) {//for loop to check id
                if (id == stdentid[i]) {
                    System.out.println("Student is in the list ,ID index:" + i);
                    index = i;
                    frist_flag = false;//if you find ID index then save it in index V , get out of while loop , flag = false
                    break outer;
                }
            }
            if (frist_flag) {
                System.out.println("ID not found , you need to declare ID or input valid ID\nenter ID: ");
                id = input.nextInt();
            }

        }


        System.out.println("the available courses is: ");//say the avaliable courses
        for (int i = 0; i < 5; i++) {//loop
            System.out.print(courses[i] + " with index: " + i + "    ");
        }

        boolean grade_flag = true;

        while (grade_flag) {
            System.out.print("\nenter course index you need to grade: ");//user enter course index he need to grade
            int grade = input.nextInt();


            boolean check_grade = true;
            System.out.println("enter the grade");//user enter grade

            while (check_grade) {
                gradeing[index][grade] = input.nextInt();//user enter grade (index is index of ID , grade is index of course)
                if (gradeing[index][grade] >= 0 && gradeing[index][grade] <= 100) {//if statement to check
                    check_grade = false;
                    System.out.println("grade successful");
                } else {
                    System.out.println("grade failed,enter grade between 0 and 100 ");
                    check_grade = true;
                }


            }
            System.out.println("want to grade another course?\n enter 1 to accept , enter 0 to reject ");//ask user to grade another course
            String choice = input.next();
            if (choice.equals("1")) {
                grade_flag = true;
            } else if (choice.equals("0")) {
                grade_flag = false;
            } else {
                System.out.println("you added invalid choice , so the answe is rejected");
                grade_flag = false;
            }
        }
    }


    public static void Student_report(int ID, int[] iDArr, String[] name, double[][] gradeing,String[] courses) {
        int student_id = 0;
        int index_1 = 0;
        for (int i = 0; i < iDArr.length; i++) {
            if (iDArr[i] == ID) {
                student_id = iDArr[i];
                index_1 = i;
                break;
            }
        }
        System.out.println("student: " + name[index_1] + "(ID: " + iDArr[index_1] + ")\n" + "-----------------------------\n");
        for (int i = 0; i < gradeing[0].length; i++) {
            System.out.println(courses[i] + "\t:" + gradeing[index_1][i]);
        }
        System.out.println("-----------------------------");
        double sum=0;
        int count=0;
        for (int i = 0; i < courses.length; i++) {
            if (gradeing[index_1][i]!=-1)
                sum += gradeing[index_1][i];
            else
                count++;
        }

        if(courses.length==count)
            System.out.println("Average: 0");
        else
            System.out.println("Average: " + sum/(courses.length-count));

    }

    public static void summary(double[][] grading,String[] course_name){
        for (int i = 0; i < grading[0].length; i++) {
            double sum=0;
            int count=0;
            for (int j = 0; j < grading.length; j++) {
                if (grading[j][i]==-1)
                    count++;
                else
                    sum+=grading[j][i];
            }
            if(count-grading.length==0)
                System.out.println("Average of courses: " + course_name[i]+" "+ sum/(grading.length-count));
            else
                System.out.println("Average of courses: 0 ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int Max_student=100;
        String[] student_name=new String[Max_student];
        int[] student_id=new int[Max_student];
        String[] course=new String[5];
        double[][] gradeBook=new double[Max_student][5];



        for (int i=0;i<Max_student;i++){//set all marks as -1 (not graded yet)
            for (int j=0;j<5;j++){
                gradeBook[i][j]=-1;
            }
        }
        int choice;

        do {
            choice=scanner.nextInt();
            System.out.println("enter: ");
            System.out.println("1.Student Registration");
            System.out.println("2. Course Setup (Admin)");
            System.out.println("3. Grade Entry");
            System.out.println("4.Student report");
            System.out.println("5..department summary");
            System.out.println("6.exit");



            switch (choice){
                case 1:
                    System.out.println("enter student ID: ");
                    int ID=scanner.nextInt();
                    System.out.println("enter student name: ");
                    String name=scanner.next();
                    check_ID(ID,name,student_id,student_name);
                    break;
                case 2:
                    set_courses(course);
                    break;
                case 3:
                    System.out.println("enter student ID: ");
                    int StudentID=scanner.nextInt();
                    grades(StudentID,student_id,course,gradeBook);
                    break;
                case 4:
                    System.out.println("enter student ID: ");
                    int ID_StUdent=scanner.nextInt();
                    Student_report(ID_StUdent,student_id,student_name,gradeBook,course);
                    break;
                case 5:
                    summary(gradeBook,course);
                    break;
                default:
            }
        }while(choice!=6);

        System.out.println("thanks for using program");


    }}