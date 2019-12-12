package pl.itcreate;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //
        //UserUtil.getUser(2);
        //UserUtil.createUser("POlsja","holownia");
        // UserUtil.updateUser(2,"Piotr","Ania");
        //UserUtil.deleteUser(2);
        //UserUtil.loginUser("email@wp.pl","1234678");
        //UserUtil.loginUser("eve.holt@reqres.in","cityslicka");
        boolean ex = false;
        while (true) {
            if(ex) {
                break;
            }
            displayUserMenu();
            Scanner scanner = new Scanner(System.in);
            int optionSelected = scanner.nextInt();
            switch (optionSelected) {
                case 1:
                    UserUtil.getUsers();
                    break;
                case 2:
                    System.out.println("Type id to search user:");
                    int userID = scanner.nextInt();
                    UserUtil.getUser(userID);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Type name for new user:");
                    String newName = scanner.nextLine();
                    System.out.println("Type job name for new user:");
                    String newJob = scanner.nextLine();
                    UserUtil.createUser(newName, newJob);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("Type id for updated user:");
                    int updateID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Type name for updated user");
                    String updateName = scanner.nextLine();
                    System.out.println("Type job for updated user");
                    String updateJob = scanner.nextLine();
                    UserUtil.updateUser(updateID,updateName,updateJob);
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.println("Type id for delete user:");
                    int deleteID = scanner.nextInt();
                    UserUtil.deleteUser(deleteID);
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.println("Type email:");
                    String loginName = scanner.nextLine();
                    System.out.println("Type password:");
                    String loginPassword= scanner.nextLine();
                    UserUtil.loginUser(loginName, loginPassword);
                    break;
                case 0:
                    ex=true;
                    break;
            }
        }
    }

    private static void displayUserMenu() {
        System.out.println("--------------------");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by ID");
        System.out.println("3. Create new user");
        System.out.println("4. Update user");
        System.out.println("5. Delete user");
        System.out.println("6. Login user");
        System.out.println("0. Exit");
        System.out.println("Choose an option");
        System.out.println("--------------------");
    }
}
