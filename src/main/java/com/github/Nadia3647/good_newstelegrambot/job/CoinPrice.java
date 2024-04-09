package com.github.Nadia3647.good_newstelegrambot.job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class CoinPrice {
    private static final String URL = "https://cennyemonety.ru/monety-rossii.html";

    public String getCoinPrice(String nominal, String year, String mint) {
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements tables = doc.select("table.table_narrow");
            for (Element table : tables) {
                String tableTitle = table.select("th").first().text();
                String nominalValue = tableTitle.split(" ")[0];
                if (nominalValue.equals(nominal)) {
                    Elements rows = table.select("tbody").select("tr");

                    String yearw = "";
                    for (Element row : rows) {
                        Elements columns = row.select("td");

                        if (nominal.equals("10")) {
                            if (columns.size() == 3) {
                                yearw = columns.get(0).text();
                            }

                            if (yearw.equals(year) &&  columns.get(columns.size() - 2).text().equalsIgnoreCase(mint)) {
                                return parsePrice(columns.get(columns.size() - 1).text());
                            }
                        } else {
                            if (columns.size() == 4) {
                                yearw = columns.get(0).text();
                            }

                            if (yearw.equals(year) &&  columns.get(columns.size() - 3).text().equalsIgnoreCase(mint)) {
                                return parsePrice(columns.get(columns.size() - 1).text());
                            }
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Цена не найдена";
    }
    private String parsePrice(String priceText) {
        if (priceText.endsWith(" тыс.")) {
            return priceText.replace(" тыс.", "") + "000";
        } else {
            return priceText;
        }
    }
}