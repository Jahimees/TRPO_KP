package com.bsuir.trpo.service.student;

import com.bsuir.trpo.datasource.StudentDBService;
import com.bsuir.trpo.model.Student;
import com.bsuir.trpo.service.ActionService;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.bsuir.trpo.constant.ParamConstant.ID;

public class DeleteStudentService implements ActionService {

    public void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id удаляемого студента");

        int id = 0;
        try {
            id = scanner.nextInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("Id должно быть числом > 0");
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

        if (student == null) {
            System.err.println("Такого студента не существует");
            return new HashMap<>();
        }

        studentDBService.removeStudent(id);
        return new HashMap<>();
    }
}
