package com.bsuir.trpo.model;

import static com.bsuir.trpo.constant.ParamConstant.*;

public class Student {
    private int id;
    private String fio;
    private int group;
    private float averageMark;
    private boolean activity;
    private float income;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public float getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public Object getValueByFieldName(String fieldName) {
        switch (fieldName) {
            case ID:
                return getId();
            case FIO:
                return getFio();
            case GROUP:
                return getGroup();
            case AVERAGE_MARK:
                return getAverageMark();
            case ACTIVITY:
                return isActivity();
            case INCOME:
                return getIncome();
        }
        return null;
    }
}
