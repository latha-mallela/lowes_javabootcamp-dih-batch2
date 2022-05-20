package com.labs.Day8;



public class Demo5 {
    public static void main(String[] args) {
        // case 1: exception not occurred
        try {
            int c = 10 / 5;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Case 1: finally called");
        }

        // case 2: exception occurred but not handled
        try {
            int c = 20 / 0;
            System.out.println(c);
        }catch (Exception ex){

        }
        finally {
            System.out.println("Case 2: finally called");
        }

        // case 3: exception occurred but handled
        try {
            int c = 20 / 0;
            System.out.println(c);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Case 3: finally called");
        }
    }
}
