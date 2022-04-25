package com.bsuir.trpo.service.menu;

import com.bsuir.trpo.ConsoleUserInterface;
import com.bsuir.trpo.datasource.StudentDBService;
import com.bsuir.trpo.model.Student;
import com.bsuir.trpo.service.student.CreateStudentService;
import com.bsuir.trpo.service.student.DeleteStudentService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.bsuir.trpo.ConsoleUserInterface.*;

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
                System.err.println("Необходимо ввести числовое значение (0-7)");
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
                    new CreateStudentService().createStudent();
                    break;
                }
                case "3": {
                    new DeleteStudentService().deleteStudent();
                    break;
                }
                case "4": {

                }
                case "5": {

                }
                case "6": {

                }
                case "7": {

                }
                case "0": {
                    ConsoleUserInterface.getInstance().setCurrentMenu(ADMIN_MENU_SERVICE_NAME);
                    flag = false;
                }
            }
        }
    }

    private void printStudents(List<Student> studentList) {
        System.out.println("Список студентов, стоящих в очереди на общежитие:");
        if (studentList.isEmpty()) {
            System.out.println("На данный момент список не существует\n");
            return;
        }
        System.out.println(" ID|      ФИО      | № Группы |Ср.б.|Активность|Доход на ч.");
        for (Student student : studentList) {
            System.out.println(" " + student.getId() + " | " + student.getFio() + " | " + student.getGroup() + " | " + student.getAverageMark() + " | " + (student.isActivity() ? "  Да  " : "  Нет ") + " | " + student.getIncome());
        }
        System.out.println("\n");
    }

    @Override
    protected void printMenu() {
        System.out.println("Выберите один из следующих пунктов:");
        System.out.println("1: Просмотреть все записи");
        System.out.println("2: Добавление новой записи");
        System.out.println("3: Удалить запись");
        System.out.println("4: Редактировать запись\n");
        System.out.println("5: Индивидуальное задание");
        System.out.println("6: Поиск данных");
        System.out.println("7: Сортировка\n");
        System.out.println("0: Назад");
    }
}
