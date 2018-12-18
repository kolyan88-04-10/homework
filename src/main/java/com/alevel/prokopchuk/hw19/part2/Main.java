package com.alevel.prokopchuk.hw19.part2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Person> list = readData("result.txt");
        System.out.println(list);
        writeData(list, "result.txt");
    }

    private static void writeData(List<Person> list, String fileName) throws IOException {
        try (FileOutputStream fout = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fout)){
            oos.writeObject(list);
        }
    }

    private static List<Person> readData(String fileName) {
        List<Person> personList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName))){
            personList = (List<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (personList.isEmpty()) {
            personList = defaultList();
        }
        return personList;
    }


    /**
     * method to generate default string of Person
     * if source file not contains list of persons
     * @return
     */
    private static List<Person> defaultList() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Nikolay", 30, Gender.MALE));
        personList.add(new Person("Ivan", 25, Gender.MALE));
        personList.add(new Person("Enakin", 8, Gender.MALE));
        personList.add(new Person("Elena", 28, Gender.FEMALE));
        personList.add(new Person("Leya", 32, Gender.FEMALE));
        return personList;
    }

}
