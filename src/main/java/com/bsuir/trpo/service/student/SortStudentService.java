package com.bsuir.trpo.service.student;

import com.bsuir.trpo.model.Student;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class SortStudentService {

    public List<Student> prioritySort(List<Student> studentList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите минимальную зарплату на сегодняшний день:");

        float minSalary;

        try {
            minSalary = scanner.nextFloat();
            if (minSalary < 1) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println("Минимальная зарплата должна быть дробным числовым значением больше 1");
            return new ArrayList<>();
        }

        List<Student> sortedList = sortByActivity(studentList);
        sortedList = sortByAverageMark(sortedList, false);
        List<Student> resultList = new ArrayList<>();

        for (Student student : sortedList) {
            if (student.getIncome() <= minSalary * 2) {
                resultList.add(student);
            }
        }

        for (Student student : sortedList) {
            if (student.getIncome() > minSalary * 2) {
                resultList.add(student);
            }
        }

        return resultList;
    }

    public List<Student> sort(List<Student> studentList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите по какому критерию отсортировать студентов:");
        System.out.println("1: По ФИО");
        System.out.println("2: По активности");
        System.out.println("3: По среднему баллу");

        int userInput = 0;
        try {
            userInput = scanner.nextInt();
            if (userInput < 1 || userInput > 3) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println("Необходимо ввести число от 1 до 3");
            return new ArrayList<>();
        }

        switch (userInput) {
            case 1: return sortByFio(studentList);
            case 2: return sortByActivity(studentList);
            case 3: return sortByAverageMark(studentList, false);
        }

        return new ArrayList<>();
    }

    public List<Student> sortByActivity(List<Student> studentList) {
        List<Student> resultList = new ArrayList<>();

        for (Student student : studentList) {
            if (student.isActivity()) {
                resultList.add(student);
            }
        }

        for (Student student : studentList) {
            if (!student.isActivity()) {
                resultList.add(student);
            }
        }

        return resultList;
    }

    public List<Student> sortByAverageMark(List<Student> studentList, boolean isASC) {
        Student[] studentArray = studentList.toArray(new Student[0]);
        boolean needIteration = true;
        if (isASC) {
            while (needIteration) {
                needIteration = false;
                for (int i = 1; i < studentArray.length; i++) {
                    if (studentArray[i].getAverageMark() < studentArray[i - 1].getAverageMark()) {
                        Student tmpStudent = studentArray[i];
                        studentArray[i] = studentArray[i - 1];
                        studentArray[i - 1] = tmpStudent;
                        needIteration = true;
                    }
                }
            }
        } else {
            while (needIteration) {
                needIteration = false;
                for (int i = 1; i < studentArray.length; i++) {
                    if (studentArray[i].getAverageMark() > studentArray[i - 1].getAverageMark()) {
                        Student tmpStudent = studentArray[i];
                        studentArray[i] = studentArray[i - 1];
                        studentArray[i - 1] = tmpStudent;
                        needIteration = true;
                    }
                }
            }
        }

        return Arrays.asList(studentArray);
    }

    public List<Student> sortByFio(List<Student> studentList) {
        List<Student> resultList = new ArrayList<>(studentList);
        Collections.sort(resultList, Comparator.comparing(Student::getFio));

        return resultList;
    }
}
