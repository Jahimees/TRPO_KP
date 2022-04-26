package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.StudentDBService;
import com.bsuir.trpo.model.Student;
import com.bsuir.trpo.service.student.SearchStudentService;
import com.bsuir.trpo.service.student.SortStudentService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.bsuir.trpo.ConsoleUserInterface.AUTHORIZATION_REGISTRATION_MENU_SERVICE_NAME;
import static com.bsuir.trpo.service.menu.StudentListManagerMenuService.printStudents;

public class UserMenuService extends AccountMenuService {

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
                System.err.println("Необходимо ввести числовое значение (0-4)");
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
                    StudentDBService studentDBService = new StudentDBService();
                    System.out.println("Очередь на получение общежития:");
                    printStudents(new SortStudentService().prioritySort(studentDBService.getAllStudents()));
                    break;
                }
                case "3": {
                    List<Student> studentList = new SearchStudentService().searchStudents();
                    if (studentList.isEmpty()) {
                        System.out.println("По заданным параметрам студенты не найдены!");
                        break;
                    }

                    printStudents(studentList);
                    break;
                }
                case "4": {
                    StudentDBService studentDBService = new StudentDBService();
                    List<Student> studentList = studentDBService.getAllStudents();
                    printStudents(new SortStudentService().sort(studentList));
                    break;
                }
                case "0": {
                    setActiveUser(null);
                    ConsoleUserInterface.getInstance().setCurrentMenu(AUTHORIZATION_REGISTRATION_MENU_SERVICE_NAME);
                    flag = false;
                }
            }
        }
    }

    @Override
    protected void printMenu() {
        System.out.println("Выберите один из следующих пунктов:");
        System.out.println("1: Просмотреть список всех студентов, стоящих в очереди на общежитие");
        System.out.println("2: Просмотреть очередность всех студентов, стоящих в очереди на общежитие (Инд. задание)");
        System.out.println("3: Поиск данных");
        System.out.println("4: Сортировка данных\n");
        System.out.println("0: Выйти из системы");
    }
}
