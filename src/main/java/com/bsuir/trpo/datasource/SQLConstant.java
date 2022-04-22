package com.bsuir.trpo.datasource;

public final class SQLConstant {
    public static final String CREATE_USER_TABLE = "CREATE TABLE if not exists 'user' (" +
            "'login' VARCHAR(45) PRIMARY KEY, " +
            "'salted_hash_password' text," +
            "'salt' VARCHAR(45)," +
            "'role' BOOLEAN," +
            "'name' VARCHAR(45)," +
            "'surname' VARCHAR(45)," +
            "'access' BOOLEAN);";

    public static final String CREATE_STUDENT_LIST = "CREATE TABLE if not exists 'student_list' (" +
            "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            "'fio' text," +
            "'group' INTEGER," +
            "'average_mark' FLOAT," +
            "'activity' BOOLEAN," +
            "'income' FLOAT)";

    public static final String SELECT_FROM_USER = "SELECT * FROM 'user'";
}
