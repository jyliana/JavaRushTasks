package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkStrategy implements Strategy {
    private static final String URL_FORMAT = "https://www.work.ua/jobs-lviv-java+junior/?ss=1&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 1;

        while (true) {
            try {
                Document document = getDocument(page);
                Elements elements = document.select("[class=card card-hover card-visited wordwrap job-link]");
                if (!elements.isEmpty()) {
                    for (Element element : elements) {
                        Vacancy vacancy = new Vacancy();
                        String[] line = element.after("<span>· </span>").text().split("·");
                        vacancy.setTitle(element.select("[class=add-bottom-sm]").text().split(",")[0]);
                        vacancy.setCompanyName(element.getElementsByTag("b").first().text());
                        vacancy.setCity(line[1]);
                        vacancy.setSiteName("https://work.ua");
                        vacancy.setUrl(vacancy.getSiteName() + element.getElementsByAttribute("href").attr("href"));

                        Element salaryElement = element.select("[class=nowrap]").first();
                        String salary = "";
                        if (salaryElement != null) {
                            salary = salaryElement.text();
                        }
                        vacancy.setSalary(salary);

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

    protected Document getDocument(int page) throws IOException {
        String url = String.format(URL_FORMAT, page);
        return Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36").referrer("https://lviv.hh.ua/").get();
    }
}
