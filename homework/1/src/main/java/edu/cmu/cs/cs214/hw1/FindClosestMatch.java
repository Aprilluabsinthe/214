package edu.cmu.cs.cs214.hw1;

/**
 * Takes a list of URLs on the command line and prints the two URL whose web
 * pages have the highest cosine similarity. Prints a stack trace if any of the
 * URLs are invalid, or if an exception occurs while reading data from the URLs.
 */
public class FindClosestMatch {

  /**
   * main function
   * @param args the list of strings from command line
   * */
  public static void main(String[] args){
    String[] websites = {
            "https://en.wikipedia.org/wiki/Bee-eater",
            "https://en.wikipedia.org/wiki/Coraciidae",
            "https://en.wikipedia.org/wiki/Aung_San_Suu_Kyi",
            "https://en.wikipedia.org/wiki/Schloss_Freudenberg",
            "https://en.wikipedia.org/wiki/1947_West_Virginia_State_Yellow_Jackets_football_team",
            "https://en.wikipedia.org/wiki/List_of_astronomical_interferometers_at_visible_and_infrared_wavelengths",
            "https://en.wikipedia.org/wiki/Twelve_Point_Buck"
};

    int numUrl = websites.length;
    double cosSim = 0.0;
    String url1 = "", url2 = "";

    for (int i = 0; i < numUrl; i++) {
      Document doc1 = new Document(websites[i]);
      for(int j = i+1; j < numUrl; j++){
        Document doc2 = new Document(websites[j]);
        double temp = doc1.cosineSimilarity(doc2);
        if( temp > cosSim){
          cosSim = temp;
          url1 = websites[i];
          url2 = websites[j];
        }
      }
    }
    System.out.printf("The two most similar URLs are:\n%s\n%s\nCosine Similarity = %f",url1,url2,cosSim);
  }
}
