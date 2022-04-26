package com.bsuir.trpo.service.student;

import com.bsuir.trpo.datasource.StudentDBService;
import com.bsuir.trpo.model.Student;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchStudentService {

    public List<Student> searchStudents() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("По какому параметру найти студента(ов)?");
        System.out.println("1: ФИО");
        System.out.println("2: Номер группы");
        System.out.println("3: Активность");

        int userInput = 0;
        try {
            userInput = scanner.nextInt();
            if (userInput < 1 || userInput > 3) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println("Необходимо ввести число >1 и <3");
            return new ArrayList<>();
        }

        switch (userInput) {
            case 1: return searchByFIO();
            case 2: return searchByGroup();
            case 3: return searchByActivity();
        }

        return new ArrayList<>();
    }

    public List<Student> searchByFIO() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО (фамилию, её часть или инициалы)");

        String fio = scanner.nextLine();
        try {
            if (fio == null || fio.equals("")) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println("Значение не может быть пустым!");
            return new ArrayList<>();
        }

        StudentDBService studentDBService = new StudentDBService();
        List<Student> allStudents = studentDBService.getAllStudents();
        List<Student> resultList = new ArrayList<>();

        for (Student student : allStudents) {
            Pattern p = Pattern.compile(fio, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(student.getFio());

            if(m.find()) {
                resultList.add(student);
            }
        }

        return resultList;
    }

    public List<Student> searchByGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер группы");

        int group = 0;
        try {
            group = scanner.nextInt();
            if (group > 999999 || group < 100000) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println("Значение должно быть шестизначным числом!");
            return new ArrayList<>();
        }

        StudentDBService studentDBService = new StudentDBService();
        return studentDBService.searchByGroup(group);
    }

    public List<Student> searchByActivity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1, для поиска активных студентов, 2 - для неактивных");
        int userInput = 0;
        try {
            userInput = scanner.nextInt();
            if (userInput < 1 || userInput > 2) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println("Необходимо ввести числовое значение 1 или 2!");
            return new ArrayList<>();
        }

        StudentDBService studentDBService = new StudentDBService();
        if (userInput == 1) {
            return studentDBService.getStudentsByActivity(true);
        } else {
            return studentDBService.getStudentsByActivity(false);
        }
    }
}
