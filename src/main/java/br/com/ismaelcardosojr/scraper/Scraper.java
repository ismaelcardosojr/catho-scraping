package br.com.ismaelcardosojr.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.ismaelcardosojr.vacancy.Vacancy;

public class Scraper {

    public List<Vacancy> obtainAllVacancies() throws IOException {
        List<Vacancy> allVacancies = new ArrayList<>();

        for (int pageNum = 1; true; pageNum++) {
            List<Vacancy> pageVacancies = this.obtainPageVacancies(pageNum);

            if (pageVacancies == null) {
                break;
            }

            allVacancies.addAll(pageVacancies);
        }

        return allVacancies;
    }

    private List<Vacancy> obtainPageVacancies(int pageNum) throws IOException {
        Document html = obtainHTML(pageNum);
        Element searchResult = html.selectFirst("div.sc-dymIpo.jTJkHV h1");

        if (searchResult != null) {
            return null;
        }

        List<Vacancy> pageVacancies = new ArrayList<>();
        Elements vacancies = html.select("#search-result ul li > article");
        
        for (Element newVacancy : vacancies) {
            Vacancy currentVacancy = this.createVacancy(newVacancy);

            if (currentVacancy.containsNullAttribute()) {
                continue;
            }

            pageVacancies.add(currentVacancy);
        }

        return pageVacancies;
    }

    private Document obtainHTML(int pageNum) throws IOException {
        Connection session = Jsoup
                .connect("https://www.catho.com.br/vagas/desenvolvedor/?q=Desenvolvedor&page=" + pageNum);
                
        return session.get();
    }

    private Vacancy createVacancy(Element vacancy) {
        String title = this.find(vacancy, "h2.Title__Heading-sc-14fvmc0-0.fGTSAd.sc-eTuwsz.fQFFJd a", false);
        String company = this.find(vacancy, "p.sc-hXRMBi.eSTWDZ", false);
        String salary = this.find(vacancy, "div.sc-exAgwC.jprlMh", false);
        String location = this.find(vacancy, "button.sc-bMVAic.hbAeKw a", false);
        String url = this.find(vacancy, "h2.Title__Heading-sc-14fvmc0-0.fGTSAd.sc-eTuwsz.fQFFJd a", true);

        return new Vacancy(title, company, salary, location, url);
    }

    private String find(Element vacancy, String query, boolean needsAttribute) {
        Element element = vacancy.selectFirst(query);

        if (element == null) {
            return null;
        }

        if (needsAttribute) {
            return element.attr("href");
        }

        return element.ownText();
    }

}