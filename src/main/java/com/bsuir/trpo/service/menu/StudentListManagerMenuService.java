package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.StudentDBService;
import com.bsuir.trpo.model.Student;
import com.bsuir.trpo.service.student.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.bsuir.trpo.ConsoleUserInterface.*;
import static com.bsuir.trpo.constant.LoggerMessageConstant.*;

public class StudentListManagerMenuService extends MenuService {
    @Override
    protected String userInput() {
        int userInput;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextInt();

                if (userInput < 0 || userInput > 7) {
                    throw new InputMismatchException();
                }

                break;
            } catch (InputMismatchException e) {
                System.err.println(NEED_NUMBER_0_7);
            }
        }
        return userInput + "";
    }

    @Override
    public void chooseMenuAction() {
        boolean flag = true;
        while (flag) {
            printMenu();
            String choose = userInput();

            switch (choose) {
                case "1": {
                    printStudents(new StudentDBService().getAllStudents());
                    break;
                }
                case "2": {
                    new CreateStudentService().createOrUpdateStudent();
                    break;
                }
                case "3": {
                    new DeleteStudentService().deleteStudent();
                    break;
                }
                case "4": {
                    new UpdateStudentService().createOrUpdateStudent();
                    break;
                }
                case "5": {
                    StudentDBService studentDBService = new StudentDBService();
                    System.out.println(QUERY_FOR_PLACE);
                    printStudents(new SortStudentService().prioritySort(studentDBService.getAllStudents()));
                    break;
                }
                case "6": {
                    List<Student> studentList = new SearchStudentService().searchStudents();
                    if (studentList.isEmpty()) {
                        System.out.println(STUDENTS_NOT_FOUND);
                        break;
                    }

                    printStudents(studentList);
                    break;
                }
                case "7": {
                    StudentDBService studentDBService = new StudentDBService();
                    List<Student> studentList = studentDBService.getAllStudents();
                    printStudents(new SortStudentService().sort(studentList));
                    break;
                }
                case "0": {
                    ConsoleUserInterface.getInstance().setCurrentMenu(ADMIN_MENU_SERVICE_NAME);
                    flag = false;
                }
            }
        }
    }

    public static void printStudents(List<Student> studentList) {
        System.out.println(QUERY_FOR_PLACE);
        if (studentList.isEmpty()) {
            System.out.println(LIST_NOT_EXISTS);
            return;
        }
        System.out.println(HEADER_STUDENTS);
        for (Student student : studentList) {
            String fio = student.getFio();
            for (int i = 0; i < 18 - student.getFio().length(); i++) {
                fio += " ";
            }

            System.out.println(" " + student.getId() + " | " + fio + " |  " + student.getGroup() + "  | " + student.getAverageMark() + " |  " + (student.isActivity() ? "  Да  " : "  Нет ") + "  | " + student.getIncome());
        }
        System.out.println("\n");
    }

    @Override
    protected void printMenu() {
        System.out.println(CHOOSE_MENU_POINT);
        System.out.println(SHOW_ALL_STUDENTS);
        System.out.println(ADD_NEW_RECORD);
        System.out.println(DELETE_RECORD);
        System.out.println(EDIT_RECORD);
        System.out.println(INDIVIDUAL_TASK_MESSAGE);
        System.out.println(SEARCHING_DATA);
        System.out.println(SORTING);
        System.out.println(PREVIOUS_MENU);
    }
}
