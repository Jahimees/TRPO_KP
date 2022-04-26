package com.bsuir.trpo.service.student;

import com.bsuir.trpo.datasource.StudentDBService;
import com.bsuir.trpo.service.ActionService;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.constant.ParamConstant.*;

public class CreateStudentService implements ActionService {

    public void createOrUpdateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО студента:");

        String fio = scanner.nextLine();

        if (fio == null || fio.equals("")) {
            System.err.println("ФИО не может быть пустым!");
            return;
        }

        System.out.println("Введите номер группы студента:");
        int group = 0;
        try {
            group = scanner.nextInt();
            if (group > 999999 || group < 100000) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println("Необходимо ввести 6-значное числовое значение!");
            return;
        }

        System.out.println("Введите среднюю оценку учащегося");
        float averageMark = 0;
        try {
            averageMark = scanner.nextFloat();
            if (averageMark > 10 || averageMark < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println("Необходимо ввести дробное число от 0 до 10 (Напр. 5,4)");
            return;
        }

        System.out.println("Введите, активен ли учащийся (1 - да, 2 - нет)");
        int activityInt;
        boolean activity;
        try {
            activityInt = scanner.nextInt();
            if (activityInt > 2 || activityInt < 1) {
                throw new InputMismatchException();
            }
            activity = activityInt == 1;
        } catch (InputMismatchException e) {
            System.err.println("Необходимо ввести 1 или 2");
            return;
        }

        System.out.println("Введите доход на члена семьи (б.р.):");
        float income = 0;
        try {
            income = scanner.nextFloat();
            if (income < 50) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.err.println("Значение не может быть меньше 50");
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
