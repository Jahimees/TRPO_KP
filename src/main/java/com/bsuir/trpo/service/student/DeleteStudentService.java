package com.bsuir.trpo.service.student;

import com.bsuir.trpo.datasource.StudentDBService;
import com.bsuir.trpo.model.Student;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;
import static com.bsuir.trpo.constant.ParamConstant.ID;

public class DeleteStudentService implements StudentService {

    public void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_ID_STUDENT);

        int id = 0;
        try {
            id = scanner.nextInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println(ERROR_ID);
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put(ID, id);

        execute(params);
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        int id = (int) params.get(ID);

        StudentDBService studentDBService = new StudentDBService();
        Student student = studentDBService.getStudent(id);

        if (student.getId() == 0) {
            System.err.println(STUDENT_NOT_EXISTS);
            return new HashMap<>();
        }

        if (!confirmDeletion()) {
            return new HashMap<>();
        }

        studentDBService.removeStudent(id);
        return new HashMap<>();
    }

    public boolean confirmDeletion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(WARNING_DELETE_STUDENT);

        int userInput = 0;
        try {
            userInput = scanner.nextInt();
            if (userInput < 1 || userInput > 2) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(NEED_1_or_2);
            return false;
        }

        return userInput == 1;
    }
}
