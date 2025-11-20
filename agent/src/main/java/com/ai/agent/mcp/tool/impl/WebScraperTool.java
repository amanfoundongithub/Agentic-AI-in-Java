package com.ai.agent.mcp.tool.impl;

import com.ai.agent.mcp.tool.ToolAbstract;
import com.ai.agent.mcp.tool_query.impl.WebScrapingQuery;
import com.ai.agent.mcp.tool_result.impl.WebScrapingResult;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebScraperTool extends ToolAbstract<WebScrapingResult, WebScrapingQuery> {

    @Value("${web_scraper.name}")
    private String toolName;

    @Value("${web_scraper.description}")
    private String toolDescription;

    @PostConstruct
    public void init() {
        this.name = toolName;
        this.description = toolDescription;
        this.queryClass = WebScrapingQuery.class;
    }

    @Override
    protected WebScrapingResult processingLogic(WebScrapingQuery query) throws Exception {
        WebScrapingResult result = new WebScrapingResult();
        String url = query.getUrl();

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10000)
                .get();

        result.setText(doc.body().text());
        return result;
    }

    @Override
    protected WebScrapingResult processingError() {
        return new WebScrapingResult();
    }

}
