package com.bsuir.trpo.service.student;

import com.bsuir.trpo.datasource.StudentDBService;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;
import static com.bsuir.trpo.constant.ParamConstant.*;

public class UpdateStudentService extends CreateStudentService {

    private int id;

    @Override
    public void createOrUpdateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_ID_STUDENT);

        try {
            id = scanner.nextInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(ERROR_ID);
            return;
        }

        super.createOrUpdateStudent();
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        String fio = (String) params.get(FIO);
        int group = (int) params.get(GROUP);
        float averageMark = (float) params.get(AVERAGE_MARK);
        boolean activity = (boolean) params.get(ACTIVITY);
        float income = (float) params.get(INCOME);

        StudentDBService studentDBService = new StudentDBService();
        if (studentDBService.getStudent(id) == null) {
            System.err.println(STUDENT_NOT_EXISTS);
            return new HashMap<>();
        }

        studentDBService.updateStudent(fio, group, averageMark, activity, income, id);

        return new HashMap<>();
    }
}
