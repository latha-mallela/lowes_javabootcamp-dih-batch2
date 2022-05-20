package com.labs.Day8;

class UserAlreadyLoggedInException extends Exception{

}

class Authentication {

    private boolean isloggedIn = false;

    public void login(String email, String password) throws UserAlreadyLoggedInException {
        if (!isloggedIn) {
            if (email.equals("admin") && password.equals("123")) {
                isloggedIn = true;
                System.out.println("login successfull");
            } else {
                System.out.println("login failed!, try again");
            }
        } else {
            throw new UserAlreadyLoggedInException();
        }
    }

}

public class Demo4 {
    public static void main(String[] args) {
        Authentication auth = new Authentication();
        try {
            auth.login("admin", "123");
        } catch (UserAlreadyLoggedInException e) {
            e.printStackTrace();
        }
        try {
            auth.login("admin", "123");
        } catch (UserAlreadyLoggedInException e) {
            System.out.println("hey! you already logged in");
        }

    }
}
