package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";
    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Lviv");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document;
        try {
            document = getDocument();
            Element element = document.getElementsByClass("template").first();
            Element copyElement = element.clone();
            copyElement.removeAttr("style");
            copyElement.removeClass("template");

            document.select("tr[class=vacancy]").remove().not("tr[class=vacancy template");

            for (Vacancy vacancy : vacancies) {
                Element clonedElement = copyElement.clone();
                clonedElement.getElementsByClass("city").first().text(vacancy.getCity());
                clonedElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                clonedElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                clonedElement.getElementsByTag("a").first().text(vacancy.getTitle()).attr("href", vacancy.getUrl());

                element.before(clonedElement.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return document.html();
    }

    private void updateFile(String string) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
