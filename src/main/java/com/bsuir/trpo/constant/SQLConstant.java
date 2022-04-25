package com.bsuir.trpo.constant;

public final class SQLConstant {
    public static final String CREATE_USER_TABLE = "CREATE TABLE if not exists 'user' (" +
            "'login' text PRIMARY KEY, " +
            "'salted_hash_password' text," +
            "'salt' text," +
            "'role' BOOLEAN," +
            "'access' BOOLEAN);";

    public static final String CREATE_STUDENT_LIST = "CREATE TABLE if not exists 'student' (" +
            "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            "'fio' text," +
            "'group' INTEGER," +
            "'average_mark' FLOAT," +
            "'activity' BOOLEAN," +
            "'income' FLOAT)";

    public static final String INSERT_INTO_USER = "INSERT INTO user('login', 'salted_hash_password', 'salt', 'role', 'access') VALUES (?, ?, ?, ?, ?)";

    public static final String SELECT_FROM_USER = "SELECT * FROM 'user'";
    public static final String SELECT_FROM_USER_BY_LOGIN = "SELECT * FROM 'user' WHERE login=?";
    public static final String SELECT_FROM_USER_WITHOUT_ACCESS = "SELECT * FROM 'user' WHERE access=0";
    public static final String DELETE_USER = "DELETE FROM 'user' WHERE login=?";
    public static final String SET_ACCESS_BY_LOGIN = "UPDATE 'user' SET access=? WHERE login=?";
    public static final String UPDATE_USER_PASSWORD = "UPDATE 'user' SET salted_hash_password=?, salt=? WHERE login=?";
    public static final String UPDATE_USER_ROLE = "UPDATE 'user' SET role=? WHERE login=?";
    public static final String SELECT_ALL_ADMINS = "SELECT * FROM 'user' WHERE role=true";
    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM 'student'";
    public static final String INSERT_INTO_STUDENT = "INSERT INTO student('fio', 'group', 'average_mark', 'activity', 'income') VALUES (?,?,?,?,?)";
    public static final String DELETE_STUDENT = "DELETE FROM 'student' WHERE id=?";
    public static final String GET_STUDENT_BY_ID = "SELECT * FROM 'student' WHERE id=?";
}
