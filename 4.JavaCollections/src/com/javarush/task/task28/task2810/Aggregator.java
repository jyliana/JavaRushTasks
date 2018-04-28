package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.*;
import com.javarush.task.task28.task2810.view.HtmlView;


public class Aggregator {

    public static void main(String[] args) {
        Provider provider = new Provider(new HHStrategy());
        Provider provider1 = new Provider(new RabotaStrategy());
        Provider provider2 = new Provider(new WorkStrategy());
        HtmlView view = new HtmlView();
        Model model = new Model(view, provider, provider1, provider2);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
