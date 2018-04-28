package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Model;

public class Controller {

    /*private Provider[] providers;*/
    private Model model;

    public Controller(Model model) {
        if (model == null) throw new IllegalArgumentException();

        this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }

    /*public Controller(Provider... providers) {
        if (providers == null || providers.length == 0) {
            throw new IllegalArgumentException();
        }
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }

    public void scan() {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            for (Provider provider : providers) {
                vacancies.addAll(provider.getJavaVacancies(null));
            }
        } catch (NullPointerException e) {
        }

        System.out.println(vacancies.size());
    }*/
}
