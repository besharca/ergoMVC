package com.lil.demo.youtube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
 
@Service
@SuppressWarnings("unused")
public class Search {

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
 
    private static YouTube youtube;
 

    public  List<String> search(String getInputQuery) { 
    	
        try {
            // This object is used to make YouTube Data API requests. The last argument is required, but since we don't need anything  initialized when the HttpRequest is initialized, we override  the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();
  
            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // API KEY
            String apiKey = "AIzaSyBiFEI2vSitxBajLcGVYRKW_ueLqnsdQ64";
            search.setKey(apiKey);
            search.setQ(getInputQuery);

            // Restrict the search results to only include videos. See: https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            
            if (searchResultList != null) {
            	List<String> resultList = getUrlList(searchResultList.iterator());
            	return resultList;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        
        return null;
    }

 
    private List<String> getUrlList(Iterator<SearchResult> iteratorSearchResults) {
    	List<String> listResult = new ArrayList<String>();
    	
        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
            //	Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

/*                System.out.println(" Video Id " + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
                System.out.println(rId.getVideoId());*/
                listResult.add(rId.getVideoId());
            }
        }
        return listResult;
    }
}