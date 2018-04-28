package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;

        while (true) {
            try {
                Document document = getDocument(searchString, page);
                Elements elements = document.getElementsByClass("job");
                if (!elements.isEmpty()) {
                    for (Element element : elements) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.getElementsByAttributeValue("class", "title").text());
                        vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                        vacancy.setUrl("https://moikrug.ru" + element.select("a[class=job_icon]").attr("href"));
                        vacancy.setSiteName(URL_FORMAT);

                        String salary = element.getElementsByAttributeValue("class", "salary").text();
                        String city = element.getElementsByAttributeValue("class", "location").text();
                        vacancy.setSalary(salary.length() == 0 ? "" : salary);
                        vacancy.setCity(city.length() == 0 ? "" : city);

                        vacancies.add(vacancy);
                    }
                } else break;
                page++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36").referrer("https://www.google.com/").get();
    }
}
