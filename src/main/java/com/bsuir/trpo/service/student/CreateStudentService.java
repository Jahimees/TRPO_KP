package com.bsuir.trpo.service.student;

import com.bsuir.trpo.datasource.StudentDBService;
import com.bsuir.trpo.service.ActionService;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.constant.LoggerMessageConstant.*;
import static com.bsuir.trpo.constant.ParamConstant.*;

public class CreateStudentService implements ActionService {

    public void createOrUpdateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_STUDENT_FIO);

        String fio = scanner.nextLine();

        if (fio == null || fio.equals("")) {
            System.err.println(VALUE_CANNOT_BE_EMPTY);
            return;
        }

        System.out.println(INPUT_GROUP);
        int group = 0;
        try {
            group = scanner.nextInt();
            if (group > 999999 || group < 100000) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(NEED_6_DIGITS);
            return;
        }

        System.out.println(INPUT_AVERAGE_MARK);
        float averageMark = 0;
        try {
            averageMark = scanner.nextFloat();
            if (averageMark > 10 || averageMark < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(ERROR_AVERAGE_MARK);
            return;
        }

        System.out.println(INPUT_ACTIVITY);
        int activityInt;
        boolean activity;
        try {
            activityInt = scanner.nextInt();
            if (activityInt > 2 || activityInt < 1) {
                throw new InputMismatchException();
            }
            activity = activityInt == 1;
        } catch (InputMismatchException e) {
            System.err.println(NEED_1_or_2);
            return;
        }

        System.out.println(INPUT_INCOME);
        float income = 0;
        try {
            income = scanner.nextFloat();
            if (income < 50) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println(ERROR_INCOME);
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put(FIO, fio);
        params.put(GROUP, group);
        params.put(AVERAGE_MARK, averageMark);
        params.put(ACTIVITY, activity);
        params.put(INCOME, income);

        execute(params);
    }

    @Override
    public HashMap<String, Object> execute(HashMap<String, Object> params) {
        String fio = (String) params.get(FIO);
        int group = (int) params.get(GROUP);
        float averageMark = (float) params.get(AVERAGE_MARK);
        boolean activity = (boolean) params.get(ACTIVITY);
        float income = (float) params.get(INCOME);

        StudentDBService studentDBService = new StudentDBService();
        studentDBService.createStudent(fio, group, averageMark, activity, income);

        return new HashMap<>();
    }
}
