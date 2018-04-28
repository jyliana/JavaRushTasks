package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        Document doc = null;
        int page = 0;
        while (true) {
            try {
                doc = getDocument(searchString, page);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
            if (elements.size() == 0) break;

            for (Element element : elements) {

                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(element.select("[data-qa=vacancy-serp__vacancy-title]").first().text());

                Element salaryElement = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                String salary = "";
                if (salaryElement != null) {
                    salary = salaryElement.text();
                }
                vacancy.setSalary(salary);

                vacancy.setCity(element.select("[data-qa=vacancy-serp__vacancy-address]").first().text());
                vacancy.setCompanyName(element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text());
                vacancy.setSiteName("http://hh.ua/");
                vacancy.setUrl(element.select("[data-qa=\"vacancy-serp__vacancy-title\"]").first().attr("href"));
                vacancies.add(vacancy);
            }
            page++;
        }


        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);

        return Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36").referrer("https://lviv.hh.ua/").get();

    }
}
