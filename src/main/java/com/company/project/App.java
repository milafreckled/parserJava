package com.company.project;

/**
 * Hello world!
 *
 */
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class App 
{
    public static void main( String[] args )
    {

        try {
            // Here we create a document object and use JSoup to fetch the website
            Document doc = Jsoup.connect("https://www.codetriage.com/?language=Java").get();

            // With the document fetched, we use JSoup's title() method to fetch the title
            System.out.printf("Title: %s\n", doc.title());
            Elements repositories = doc.getElementsByClass("repo-item");
            for (Element repository : repositories) {
                String repositoryTitle = repository.getElementsByClass("repo-item-title").text();
                String repositoryIssues = repository.getElementsByClass("repo-item-issues").text();
                String repositoryDescription = repository.getElementsByClass("repo-item-description").text();
                String repositoryGithubName = repository.getElementsByClass("repo-item-full-name").text();
                String repositoryGithubLink = "https://github.com/" + repositoryGithubName.replaceAll("[()]", "");
                System.out.println(repositoryTitle + " - " + repositoryIssues);
                System.out.println("\t" + repositoryDescription);
                System.out.println("\t" + repositoryGithubLink);
                System.out.println("\n");
            }
            // In case of any IO errors, we want the messages written to the console
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
