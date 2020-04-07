import java.util.Scanner;

public class Library {

    public static void main(String[] args) {
        Library lib = new Library();
        lib.printscreen();
        lib.printviews();
    }

    public void printscreen()
    {
        System.out.println("***********************************************************");
        System.out.println("Welcome To WolfDb Publishing House");
        System.out.println("***********************************************************");
    }

    public void printviews()
    {
        System.out.println("Please specify your role");
        System.out.println("1. Manager");
        System.out.println("2. Editor");
        System.out.println("3. Journalist");
        System.out.println("4. Authors");
        System.out.println("");
        int choice = getinput();
        execute(choice);
    }
    public int getinput()
    {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        scan.close();
        return num;
    }

    public void execute(int choice)
    {
        switch(choice)
        {
            case 1:{
                try {
                    Manager m = new Manager();
                    m.firstmenu();
                } catch (Exception e) {
                    System.out.println("Unable to switch to operations"+ e);
                }
            }
            break;
        case 2:{
                try {
                    Editor e = new Editor();
                    e.firstmenu();
                } catch (Exception e) {
                    System.out.println("Unable to switch to operations"+ e);
                }
            }
            break;
        case 3:{
                try {
                    Journalist j = new Journalist();
                    j.firstmenu();
                } catch (Exception e) {
                    System.out.println("Unable to switch to operations"+ e);
                }
            }
            break;
        case 4:{
                try {
                    Author a = new Author();
                    a.firstmenu();
                } catch (Exception e) {
                    System.out.println("Unable to switch to operations"+ e);
                }
            }
            break;

        default:
                System.out.println("Error in switch block");
            }
        }
    }
    

