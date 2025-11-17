package com.ai.agent.mcp.tool.impl;

import com.ai.agent.mcp.tool.Tool;
import com.ai.agent.mcp.tool_query.impl.WebScrapingQuery;
import com.ai.agent.mcp.tool_result.impl.WebScrapingResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebScraperTool implements Tool<WebScrapingResult, WebScrapingQuery> {

    // Logger for logging tool requests
    private static final Logger LOGGER = LoggerFactory.getLogger(WebScraperTool.class);

    @Value("${web_scraper.name}")
    private String toolName;

    @Value("${web_scraper.description}")
    private String toolDescription;

    @Override
    public String name() {
        return toolName;
    }

    @Override
    public String description() {
        return toolDescription;
    }

    @Override
    public Class<WebScrapingQuery> queryClass() {
        return WebScrapingQuery.class;
    }

    @Override
    public WebScrapingResult execute(WebScrapingQuery query) {

        LOGGER.info("Received Request for Web Scraping on {}", query.getUrl());
        WebScrapingResult result = new WebScrapingResult();

        try {

            String url = query.getUrl();

            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();

            result.setText(doc.body().text());

        } catch (Exception e) {
            LOGGER.error("Error in Web Scraping: {}", e.getMessage());
            result.setText(null);
        } finally {
            LOGGER.info("Request for Web Scraping Completed");
        }

        return result;
    }
}
