package com.labs.Day3;

class Book {
    public String title;
    public String author;
    public int price;

    public void printBookInfo()
    {
        System.out.println("Book: Title = "+title +" , Author = "+author+" , Price = "+price);
    }
}
public class Demo5 {
    public static void main(String[] args) {

        Book bookObj = new Book();
        bookObj.title = "Learn Java in 2 weeks";
        bookObj.author = "Mark Smith";
        bookObj.price = 100;

        //System.out.println("Book: Title = "+bookObj.title +" , Author = "+bookObj.author+" , Price = "+bookObj.price);

        Book bookObj2 = new Book();
        bookObj2.title = "Learn HTML";
        bookObj2.author = "Paul";
        bookObj2.price = 200;

        //System.out.println("Book: Title = "+bookObj2.title +" , Author = "+bookObj2.author+" , Price = "+bookObj2.price);

        bookObj.printBookInfo();
        bookObj2.printBookInfo();
    }
}
