package com.abner.ming.abnermvp.httptest;

import com.abner.ming.vipmvp.rxretrofit.model.IModel;

import java.util.List;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public class TestBean implements IModel{
    private int count;
    private int total;
    private List<Subjects> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public static class Subjects{
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
