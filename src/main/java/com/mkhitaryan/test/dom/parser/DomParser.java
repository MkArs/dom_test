package com.mkhitaryan.test.dom.parser;

import com.mkhitaryan.test.dom.entity.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class DomParser {
    private List<Currency> currencyList = new LinkedList<>();

    public List<Currency> getCurrencyList(String url) {
        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(url);
            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("Valute");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    // get Valute's attribute
                    String id = element.getAttribute("ID");
                    // get text
                    String numCode = element.getElementsByTagName("NumCode").item(0).getTextContent();
                    String charCode = element.getElementsByTagName("CharCode").item(0).getTextContent();
                    String nominal = element.getElementsByTagName("Nominal").item(0).getTextContent();
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String value = element.getElementsByTagName("Value").item(0).getTextContent();

                    Currency currency = new Currency();

                    currency.setId(id);
                    currency.setNominal(parseInt(nominal));
                    currency.setName(name);
                    currency.setValue(new BigDecimal(value.replace(',', '.')));
                    currency.setNumcode(numCode);
                    currency.setCharcode(charCode);

                    currencyList.add(currency);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return currencyList;
    }

    public void clearCurrencyList() {
        currencyList.clear();
    }

}
