package com.bsuir.trpo.constant;

public final class SQLConstant {
    public static final String CREATE_USER_TABLE = "CREATE TABLE if not exists 'user' (" +
            "'login' text PRIMARY KEY, " +
            "'salted_hash_password' text," +
            "'salt' text," +
            "'role' BOOLEAN," +
            "'access' BOOLEAN);";

    public static final String CREATE_STUDENT_LIST = "CREATE TABLE if not exists 'student_list' (" +
            "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            "'fio' text," +
            "'group' INTEGER," +
            "'average_mark' FLOAT," +
            "'activity' BOOLEAN," +
            "'income' FLOAT)";

    public static final String INSERT_INTO_USER = "INSERT INTO user('login', 'salted_hash_password', 'salt', 'role', 'access') VALUES (?, ?, ?, ?, ?)";

    public static final String SELECT_FROM_USER = "SELECT * FROM 'user'";
    public static final String SELECT_FROM_USER_BY_LOGIN = "SELECT * FROM 'user' WHERE login=?";
}
