package com.labs.Day3;

class Book2 {
    public String title;
    public String author;
    public int price;

    public Book2(String title,String author,int price)
    {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public void printBookInfo()
    {
        System.out.println("Book: Title = "+title +" , Author = "+author+" , Price = "+price);
    }
}
public class Demo6 {
    public static void main(String[] args) {

        Book2 obj1 = new Book2("Title 1","Author 1",100);
        obj1.printBookInfo();

        Book2 obj2 = new Book2("Title 2","Author 2",200);
        obj2.printBookInfo();

    }
}
