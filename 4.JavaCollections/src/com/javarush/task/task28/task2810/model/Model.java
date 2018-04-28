package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private Provider[] providers;
    private View view;

    public Model(View view, Provider... providers) {
        if (view == null || providers == null || providers.length == 0) {
            throw new IllegalArgumentException();
        }
        this.providers = providers;
        this.view = view;
    }

    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            for (Provider provider : providers) {
                vacancies.addAll(provider.getJavaVacancies(city));
            }
        } catch (NullPointerException e) {
        }
        view.update(vacancies);
    }
}
