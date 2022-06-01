package com.bsuir.trpo.constant;

public class LoggerMessageConstant {
    public static final String DB_FILE_NOT_FOUND = "Невозможно получить соединение... Файл базы данных не найден";
    public static final String DB_DRIVER_NOT_DEFINED = "Невозможно определить драйвер org.sqlite.JDBC";
    public static final String CHECKING_DB_TABLES = "Проверяем таблицы базы данных...";
    public static final String CHECKING_SUCCESSFUL = "Проверка успешно пройдена";
    public static final String DB_NOT_EXISTS = "База данных не существует.";
    public static final String STARTING_INITIALIZATION_SCRIPT = "Запускаю скрипт инициализации...\n";
    public static final String CANNOT_EXECUTE_QUERY_DB_NOT_EXISTS = "Невозможно выполнить запрос. База данных не существует";
    public static final String CRITICAL_ERROR_CANNOT_INITIALIZE_DB = "Критическая ошибка. Не могу инициализировать базу данных...";
    public static final String CREATING_USER_TABLE = "Создание таблицы пользователей...";
    public static final String USER_TABLE_CREATED = "Таблица пользователей создана!\n";
    public static final String CREATING_STUDENT_TABLE = "Создание таблицы списка студентов...";
    public static final String STUDENT_TABLE_CREATED = "Таблица списка студентов создана!\n";
    public static final String STUDENT_CREATED = "Студент успешно создан!";
    public static final String STUDENT_UPDATED = "Студент успешно обновлен!";
    public static final String STUDENT_DELETED = "Студент успешно удален!\n";
    public static final String NOT_EXISTS = " не существует!\n";
    public static final String USER_ACCESSED = " успешно предоставлен доступ к системе!\n";
    public static final String USER_DELETED = "Пользователь успешно удален!\n";
    public static final String ALL_USER_ACCOUNTS = "Все пользовательские аккаунты:";
    public static final String HEADER_USER_ACCOUNTS = " # |   Логин   | Админ ";
    public static final String REGISTRATION_APPLICATION = "ВНИМАНИЕ! На регистрацию была подана только заявка! Вы не можете авторизоваться сейчас!";
    public static final String INVALID_DATA_INPUT = "Введенные данные не соответствуют требованиям! Попробуйте ещё раз";
    public static final String CONFIRM_ACCOUNT_HELP = "Введите логин пользователя, а затем через пробел значение 1 (разрешить доступ) или 2 (запретить доступ)";
    public static final String WARNING_NOT_CONFIRM_ACCOUNT = "Внимание, при запрете доступа, заявка удаляется навсегда!";
    public static final String NO_APPLICATIONS = "\nСейчас нет заявок на регистрацию.";
    public static final String QUERY_FOR_PLACE = "Очередь на получение общежития:";
    public static final String STUDENTS_NOT_FOUND = "По заданным параметрам студенты не найдены!";
    public static final String LIST_NOT_EXISTS = "На данный момент список не существует\n";
    public static final String HEADER_STUDENTS = " ID|      ФИО           | № Группы |Ср.б.|Активность|Доход на ч.";

    public static final String NEED_NUMBER_0_4 = "Необходимо ввести числовое значение (0-4)";
    public static final String NEED_NUMBER_0_3 = "Необходимо ввести числовое значение (0-3)";
    public static final String NEED_NUMBER_1_3 = "Необходимо ввести числовое значение (1-3)";
    public static final String NEED_NUMBER_0_7 = "Необходимо ввести числовое значение (0-7)";
    public static final String NEED_6_DIGITS = "Необходимо ввести 6-значное числовое значение!";
    public static final String NEED_1_or_2 = "Необходимо ввести 1 или 2";
    public static final String NEED_NUMBER = "Необходимо вводить числовое значение!";

    public static final String CHOOSE_MENU_POINT = "\nВыберите один из следующих пунктов:";
    public static final String SHOW_ALL_USER_ACCOUNTS = "1: Просмотреть все учетные записи пользователей";
    public static final String CREATE_NEW_USER_ACCOUNT = "2: Создать новую учетную запись пользователя";
    public static final String EDIT_USER_ACCOUNT = "3: Редактировать учетные записи пользователей (смена пароля, роли, блокировка)";
    public static final String DELETE_USER_ACCOUNT = "4: Удаление учетных записей пользователей";
    public static final String PREVIOUS_MENU = "0: Вернуться на прошлую страницу\n";
    public static final String CHANGE_PASSWORD = "1: Изменить пароль";
    public static final String CHANGE_ROLE = "2: Изменить роль";
    public static final String BLOCK_ACCOUNT = "3: Заблокировать учетную запись";
    public static final String UNBLOCK_ACCOUNT = "4: Разблокировать учетную запись";
    public static final String CONFIRM_APPLICATIONS = "1: Заявки на подтверждение регистрации";
    public static final String MANAGE_ACCOUNTS = "2: Управление учетными записями пользователей";
    public static final String MANAGE_STUDENTS = "3: Управление списками студентов на очередь в общежитие";
    public static final String SYSTEM_EXIT = "0: Выйти из системы";
    public static final String AUTHORIZE = "1: Авторизоваться";
    public static final String REGISTER = "2: Зарегистрироваться";
    public static final String END_WORK = "3: Завершить работу";
    public static final String SHOW_ALL_STUDENTS = "1: Просмотреть всех студентов";
    public static final String ADD_NEW_RECORD = "2: Добавление новой записи";
    public static final String DELETE_RECORD = "3: Удалить запись";
    public static final String EDIT_RECORD = "4: Редактировать запись\n";
    public static final String INDIVIDUAL_TASK_MESSAGE = "5: Вывести список очередности предоставления места в общежитии (Индивидуальное задание)";
    public static final String SEARCHING_DATA = "6: Поиск данных";
    public static final String SORTING = "7: Сортировка\n";
    public static final String SHOW_QUERY_FOR_PLACE = "2: Просмотреть очередность всех студентов, стоящих в очереди на общежитие (Инд. задание)";

    public static final String CANNOT_CLOSE_CONNECTION = "Невозможно закрыть соединение";
    public static final String CANNOT_EXECUTE_QUERY = "Невозможно выполнить запрос";

    public static final String INPUT_STUDENT_FIO = "Введите ФИО студента:";
    public static final String VALUE_CANNOT_BE_EMPTY = "Значение не может быть пустым!";
    public static final String INPUT_GROUP = "Введите номер группы студента:";
    public static final String INPUT_AVERAGE_MARK = "Введите среднюю оценку учащегося";
    public static final String ERROR_AVERAGE_MARK = "Необходимо ввести дробное число от 0 до 10 (Напр. 5,4)";
    public static final String INPUT_ACTIVITY = "Введите, активен ли учащийся (1 - да, 2 - нет)";
    public static final String INPUT_INCOME = "Введите доход на члена семьи (б.р.):";
    public static final String ERROR_INCOME = "Значение должно быть числовым и не может быть меньше 50";
    public static final String INPUT_ID_STUDENT = "Введите id студента";
    public static final String ERROR_ID = "Id должно быть числом > 0";
    public static final String STUDENT_NOT_EXISTS = "Такого студента не существует";
    public static final String INPUT_FIO_PART = "Введите ФИО (фамилию, её часть или инициалы)";
    public static final String SEARCHING_BY_ACTIVITY_HELPER = "Введите 1, для поиска активных студентов, 2 - для неактивных";
    public static final String INPUT_MIN_SALARY = "Введите минимальную зарплату на сегодняшний день:";
    public static final String MIN_SALARY_ERROR = "Минимальная зарплата должна быть дробным числовым значением больше 1";

    public static final String WARNING_DELETE_STUDENT = "ВНИМАНИЕ! Вы действительно хотите удалить студента? (1 - да, 2 - нет)";
    public static final String WARNING_DELETE_ACCOUNT = "ВНИМАНИЕ! Вы действительно хотите удалить пользователя? (1 - да, 2 - нет)";

    public static final String PARAMETER_FOR_SEARCHING_STUDENT = "По какому параметру найти студента(ов)?";
    public static final String FIO_MENU = "1: ФИО";
    public static final String GROUP_NUMBER_MENU = "2: Номер группы";
    public static final String ACTIVITY_MENU = "3: Активность";

    public static final String SORTING_MENU_HEADER = "Введите по какому критерию отсортировать студентов:";
    public static final String BY_FIO = "1: По ФИО";
    public static final String BY_ACTIVITY = "2: По активности";
    public static final String BY_AVERAGE_MARK = "3: По среднему баллу";

    public static final String PLEASE_AUTHORIZE = "Пожалуйста, авторизуйтесь.";
    public static final String INPUT_LOGIN = "Введите логин: ";
    public static final String INPUT_PASSWORD = "Введите пароль: ";
    public static final String CHECKING_INPUT_DATA = "Проверяем введенные данные...";
    public static final String AUTHORIZE_SUCCESSFUL = "Авторизация прошла успешно!";
    public static final String LOGIN_OR_PASSWORD_INCORRECT = "Логин или пароль неверны! Попробуйте ещё раз! " +
            "\n(Возможно у Вашего аккаунта нет доступа, обратитесь к администратору)";
    public static final String INPUT_LOGIN_FOR_CHANGE = "\nВведите логин аккаунта, на котором будет изменен доступ";
    public static final String CANNOT_CHANGE_ACCESS = "Невозможно изменить доступ самому себе!";
    public static final String USER_NOT_EXISTS = "Пользователя не существует!";
    public static final String BLOCKED = " заблокирован";
    public static final String UNBLOCKED = " разблокирован";
    public static final String USER_LOG = "Пользователь ";
    public static final String PASSWORD_CHANGED = "Пароль пользователя успешно изменен!";
    public static final String CHANGE_ROLE_HELPER = "Введите 1, чтобы аккаунт стал администраторским, 0 - пользовательским";
    public static final String VALUES_EMPTY_OR_INCORRECT = "Значения пусты, либо являются неверными!";
    public static final String CANNOT_CHANGE_ROLE = "ВНИМАНИЕ. ВЫ НЕ МОЖЕТЕ ИЗМЕНИТЬ РОЛЬ ЭТОГО ПОЛЬЗОВАТЕЛЯ, ТАК КАК ОН ПОСЛЕДНИЙ АДМИНИСТРАТОР!";
    public static final String ROLE_LOG = "Роль ";
    public static final String CHANGED_ROLE = " успешно изменена на роль";
    public static final String ADMIN_LOG = " администратора";
    public static final String USERA_LOG = " пользователя";

    public static final String REGISTER_SUCCESSFUL = "Регистрация прошла успешно!";
    public static final String USER_ALREADY_EXISTS = "Невозможно зарегистрировать пользователя. Пользователь с таким именем уже существует!";

    public static final String WELCOME_WORDS = "\n--Добро пожаловать в программу распределения мест в общежитии!--\n";
    public static final String CHECKING_SYSTEM = "=============Проверка системы=============";
    public static final String CHECKING_COMPLETE = "=============Проверка завершена=============";
    public static final String SYSTEM_CREATED_JUST_NOW = "Система была только что создана! Пожалуйста, зарегистрируйте аккаунт администратора!";

}
