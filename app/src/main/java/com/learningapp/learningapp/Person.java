package com.learningapp.learningapp;
// Very simple data model for use in our app.
public class Person {

    private int id = 0;
    private String name = " ";
    private int age = 0;

    Person(int i, String n, int a) {
        this.id = i;
        this.name = n;
        this.age = a;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
