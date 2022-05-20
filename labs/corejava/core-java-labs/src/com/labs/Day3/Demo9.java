package com.labs.Day3;

class Car{
	public String brand;
	public String year;
	public String model;
	public String color;
	public String engineType;
	public int price;
	
	public Car(String brand, String year, String model, String color, String engineType, int price)
	{
		this.brand = brand;
		this.year = year;
		this.model = model;
		this.color = color;
		this.engineType = engineType;
		this.price = price;
		
	}
	public Car(Car car)
	{
		this.brand = car.brand;
		this.year = car.year;
		this.model = car.model;
		this.color = car.color;
		this.engineType = car.engineType;
		this.price = car.price;		
	}
	public Car(Car car, int price)
	{
		this.brand = car.brand;
		this.year = car.year;
		this.model = car.model;
		this.color = car.color;
		this.engineType = car.engineType;
		this.price = price;		
	}
}

public class Demo9 {
	
	public static void main(String args[]) {
		
		Car car1 = new Car("Audi","2022","A6","Black","Petrol",10000);
		Car car2 = new Car(car1);
		Car car3 = new Car(car1, 20000);
		
		System.out.println("Car1 object: "+car1.brand+" "+car1.year+" "+car1.model+" "+car1.engineType+" "+car1.price);
		System.out.println("Car2 object: "+car2.brand+" "+car2.year+" "+car2.model+" "+car2.engineType+" "+car2.price);
		System.out.println("Car3 object: "+car3.brand+" "+car3.year+" "+car3.model+" "+car3.engineType+" "+car3.price);
	}
}

