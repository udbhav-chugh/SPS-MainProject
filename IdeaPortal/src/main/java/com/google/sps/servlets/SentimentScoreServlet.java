package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;

/** Servlet responsible for listing tasks. */
@WebServlet("/sentiment-score")
public class SentimentScoreServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response, final long ProjectID) throws IOException {

    List<Comment> comments= getListofCommentObject(final long ProjectID) //PreparedQuery results);

    
    /**
    Create Comment Entity and use put method in case Datastore will be updated 
    with the sentiment score.
    **/
      
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(comments));
  }



private List<Comment> getListofCommentObject(final long ProjectID){
    Query<Entity> query = Query.newEntityQueryBuilder().setKind("Comment").setFilter(PropertyFilter.eq("ProjectID", ProjectID))
        .build();
   
    PreparedQuery results= getQueryResults(query);

    List<Comment> comments= getProjectID_Comment(results);
}


private PreparedQuery getQueryResults(Query<Entity> query){
     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);
    return results;
}


private List<Comment> getProjectID_Comment(PreparedQuery results){
    List<Comment> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      long id = entity.getKey().getId();
      
     
     String commentAuthorId= (String) entity.getProperty("commentAuthorId");
     String suggestion = (String) entity.getProperty("suggestion");
     ArrayList<String> suggestionKeywords =  entity.getProperty("suggestionKeywords");
     long timestamp = (long) entity.getProperty("timestamp");
      
      String text = (String) entity.getProperty("text");
      
      double sentimantAnalysisScore = (double) entity.getProperty("sentimantAnalysisScore");
      sentimantAnalysisScore= sentimantAnalysisScore==0?getSentimentScore(text):sentimantAnalysisScore;


      Comment comment_obj = new Comment(commentAuthorId,text,suggestion,
      suggestionKeywords,sentimantAnalysisScore,timestamp);
      
      comments.add(comment_obj);
}

private double getSentimentScore(String text){
    Document doc =
        Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
    LanguageServiceClient languageService = LanguageServiceClient.create();
    Sentiment sentiment = languageService.analyzeSentiment(doc).getDocumentSentiment();
    double score = sentiment.getScore();

    languageService.close();
    return score;
}

  
}



