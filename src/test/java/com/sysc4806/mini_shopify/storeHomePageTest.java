package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class storeHomePageTest {

    @Test
    public void testHtmlElements() throws IOException {
        File input = new File("src/main/resources/templates/storeHomePage.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        assertEquals("Mini-Shopify", doc.title());

        Element nameInput = doc.getElementById("name");
        Element ownerInput = doc.getElementById("owner");
        Element categoryInput = doc.getElementById("category");
        Element descriptionInput = doc.getElementById("description");

        assertNotNull(nameInput);
        assertEquals("Store Name", nameInput.attr("placeholder"));

        assertNotNull(ownerInput);
        assertEquals("Owner", ownerInput.attr("placeholder"));

        assertNotNull(categoryInput);
        assertEquals("Category", categoryInput.attr("placeholder"));

        assertNotNull(descriptionInput);
        assertEquals("Description", descriptionInput.attr("placeholder"));

        Element storeList = doc.getElementById("store-list");
        assertNotNull(storeList);
    }
}
