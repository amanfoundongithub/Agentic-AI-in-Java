package com.ai.agent.tools.dto;

import java.util.List;
import java.util.Map;

public class GoogleSearchResults {

    public String kind;
    public Url url;
    public Queries queries;
    public Context context;
    public SearchInformation searchInformation;
    public List<Item> items;

    public static class Url {
        public String type;
        public String template;
    }

    public static class Queries {
        public List<Request> request;
        public List<Request> nextPage;

        public static class Request {
            public String title;
            public String totalResults;
            public String searchTerms;
            public Integer count;
            public Integer startIndex;
            public String inputEncoding;
            public String outputEncoding;
            public String safe;
            public String cx;
        }
    }

    public static class Context {
        public String title;
    }

    public static class SearchInformation {
        public Double searchTime;
        public String formattedSearchTime;
        public String totalResults;
        public String formattedTotalResults;
    }

    public static class Item {
        public String kind;
        public String title;
        public String htmlTitle;
        public String link;
        public String displayLink;
        public String snippet;
        public String htmlSnippet;
        public String formattedUrl;
        public String htmlFormattedUrl;
        public PageMap pagemap;

        public static class PageMap {
            public List<CseThumbnail> cse_thumbnail;
            public List<MetaTag> metatags;
            public List<CseImage> cse_image;

            public static class CseThumbnail {
                public String src;
                public String width;
                public String height;
            }

            public static class MetaTag {
                public Map<String, String> meta;
            }

            public static class CseImage {
                public String src;
            }
        }
    }
}
