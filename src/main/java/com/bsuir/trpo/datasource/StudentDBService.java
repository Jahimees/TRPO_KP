package com.bsuir.trpo.datasource;

import com.bsuir.trpo.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.bsuir.trpo.constant.ParamConstant.*;
import static com.bsuir.trpo.constant.SQLConstant.*;

public class StudentDBService {

    public List<Student> getAllStudents() {
        Connection connection = DataSource.getConnection();
        List<Student> studentList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENTS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                fillStudentFields(student, resultSet);

                studentList.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Невозможно выполнить запрос");
            System.err.println(e.getMessage());
            return new ArrayList<>();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Невозможно закрыть соединение");
                System.err.println(e.getMessage());
                return new ArrayList<>();
            }
        }

        return studentList;
    }

    public Student getStudent(int id) {
        Connection connection = DataSource.getConnection();
        Student student = new Student();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_STUDENT_BY_ID);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                fillStudentFields(student, resultSet);
                return student;
            }
        } catch (SQLException e) {
            System.err.println("Невозможно выполнить запрос");
            System.err.println(e.getMessage());
            return new Student();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Невозможно закрыть соединение");
                System.err.println(e.getMessage());
                return new Student();
            }
        }

        return student;
    }

    public void createStudent(String fio, int group, float averageMark, boolean activity, float income) {
        Connection connection = DataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_STUDENT);
            statement.setString(1, fio);
            statement.setInt(2, group);
            statement.setFloat(3, averageMark);
            statement.setBoolean(4, activity);
            statement.setFloat(5, income);

            statement.execute();

        } catch (SQLException e) {
            System.err.println("Невозможно выполнить запрос");
            System.err.println(e.getMessage());
            return;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Невозможно закрыть соединение");
                System.err.println(e.getMessage());
                return;
            }
        }

        System.out.println("Студент " + fio + " успешно создан!");
    }

    public void updateStudent(String fio, int group, float averageMark, boolean activity, float income, int id) {
        Connection connection = DataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT);
            statement.setString(1, fio);
            statement.setInt(2, group);
            statement.setFloat(3, averageMark);
            statement.setBoolean(4, activity);
            statement.setFloat(5, income);
            statement.setInt(6, id);

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Невозможно выполнить запрос");
            System.err.println(e.getMessage());
            return;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Невозможно закрыть соединение");
                System.err.println(e.getMessage());
                return;
            }
        }

        System.out.println("Студент " + fio + " успешно обновлен!");
    }

    public void removeStudent(int id) {
        Connection connection = DataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            System.err.println("Невозможно выполнить запрос");
            System.err.println(e.getMessage());
            return;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Невозможно закрыть соединение");
                System.err.println(e.getMessage());
                return;
            }
        }
        System.out.println("Студент успешно удален!\n");
    }


    public List<Student> getStudentsByActivity(boolean isActive) {
        Connection connection = DataSource.getConnection();
        List<Student> studentList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_STUDENTS_BY_ACTIVITY);
            statement.setBoolean(1, isActive);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                fillStudentFields(student, resultSet);
                studentList.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Невозможно выполнить запрос");
            System.err.println(e.getMessage());
            return new ArrayList<>();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Невозможно закрыть соединение");
                System.err.println(e.getMessage());
                return new ArrayList<>();
            }
        }

        return studentList;
    }

    public List<Student> searchByGroup(int group) {
        Connection connection = DataSource.getConnection();
        List<Student> resultList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_STUDENTS_BY_GROUP);
            statement.setInt(1, group);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                fillStudentFields(student, resultSet);
                resultList.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Невозможно выполнить запрос");
            System.err.println(e.getMessage());
            return new ArrayList<>();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Невозможно закрыть соединение");
                System.err.println(e.getMessage());
                return new ArrayList<>();
            }
        }


        return resultList;
    }

    private void fillStudentFields(Student student, ResultSet resultSet) throws SQLException {
        student.setId(resultSet.getInt(ID));
        student.setFio(resultSet.getString(FIO));
        student.setGroup(resultSet.getInt(GROUP));
        student.setAverageMark(resultSet.getFloat(AVERAGE_MARK));
        student.setActivity(resultSet.getBoolean(ACTIVITY));
        student.setIncome(resultSet.getFloat(INCOME));
    }
}
