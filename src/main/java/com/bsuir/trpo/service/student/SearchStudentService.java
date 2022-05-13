package com.bsuir.trpo.service.student;

import com.bsuir.trpo.datasource.StudentDBService;
import com.bsuir.trpo.model.Student;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;

public class SearchStudentService implements StudentService {

    public List<Student> searchStudents() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(PARAMETER_FOR_SEARCHING_STUDENT);
        System.out.println(FIO_MENU);
        System.out.println(GROUP_NUMBER_MENU);
        System.out.println(ACTIVITY_MENU);

        int userInput = 0;
        try {
            userInput = scanner.nextInt();
            if (userInput < 1 || userInput > 3) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(NEED_NUMBER_1_3);
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
        System.out.println(INPUT_FIO_PART);

        String fio = scanner.nextLine();
        try {
            if (fio == null || fio.equals("")) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(VALUE_CANNOT_BE_EMPTY);
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
        System.out.println(INPUT_GROUP);

        int group = 0;
        try {
            group = scanner.nextInt();
            if (group > 999999 || group < 100000) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(NEED_6_DIGITS);
            return new ArrayList<>();
        }

        StudentDBService studentDBService = new StudentDBService();
        return studentDBService.searchByGroup(group);
    }

    public List<Student> searchByActivity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(SEARCHING_BY_ACTIVITY_HELPER);
        int userInput = 0;
        try {
            userInput = scanner.nextInt();
            if (userInput < 1 || userInput > 2) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(NEED_1_or_2);
            return new ArrayList<>();
        }

        StudentDBService studentDBService = new StudentDBService();
        if (userInput == 1) {
            return studentDBService.getStudentsByActivity(true);
        } else {
            return studentDBService.getStudentsByActivity(false);
        }
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        return null;
    }
}
