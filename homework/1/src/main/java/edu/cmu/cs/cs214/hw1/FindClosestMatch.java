package edu.cmu.cs.cs214.hw1;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes a list of URLs on the command line and prints the two URL whose web
 * pages have the highest cosine similarity. Prints a stack trace if any of the
 * URLs are invalid, or if an exception occurs while reading data from the URLs.
 */
public class FindClosestMatch {
    private List<String> webList;
    private List<Document> docList;
    private int listLength;
    private int minIndex1;
    private int minIndex2;

    /**
     * the default constructor for class
     */
    public FindClosestMatch() {
        this.listLength = 0;
        this.webList = new ArrayList<String>();
        this.docList = new ArrayList<Document>();
        this.minIndex1 = 0;
        this.minIndex2 = 0;
    }

    /**
     * class constructor
     * @param websites a list of website urls
     */
    public FindClosestMatch(String[] websites) {
        this.listLength = websites.length;
        this.webList = new ArrayList<String>();
        this.docList = new ArrayList<Document>();
        this.minIndex1 = 0;
        this.minIndex2 = 0;

        for(int i = 0 ; i < this.listLength ; i++){
            String web = websites[i];
            webList.add(web);
            docList.add(new Document(web));
        }
    }

    /**
     * get document from list by index
     * @param i index
     * @return Document in list
     */
    public Document getDoc(int i) {
        return this.docList.get(i);
    }

    /**
     * get the index of one document in a list
     * @param doc a given document
     * @return index in i=list
     */
    public int getDocIndex(Document doc) {
        return this.docList.indexOf(doc);
    }

    /**
     * give the index in list, return the String url
     * @param i index
     * @return url
     */
    public String getUrl(int i) {
        return this.webList.get(i);
    }

    /**
     * get the index in list
     * @param url given website url
     * @return index in list
     */
    public int getUrlIndex(String url) {
        return this.webList.indexOf(url);
    }

    /**
     * get the coordinate of two indexed that will generate biggest similarity
     * @return int[] of indexes
     */
    public int[] getMinIndex() {
        int[] pair = new int[2];
        pair[0] = minIndex1;
        pair[1] = minIndex2;
        return pair;
    }

    /**
     * get the length of the class list
     * @return length
     */
    public int getListLength() {
        return this.listLength;
    }

    /**
     * find bestFind for given index
     * @return double cosine similaty
     */
    public double bestFit() {
        double maxSim = 0.0;
        for(int i = 0; i < this.listLength; i++){
            Document doc1 = getDoc(i);
            for( int j = i+1; j < this.listLength; j++){
                Document doc2 = getDoc(j);
                double temp = doc1.cosineSimilarity(doc2);
                if( temp > maxSim){
                    maxSim = temp;
                    this.minIndex1 = i;
                    this.minIndex2 = j;
                }
            }
        }
        return maxSim;
    }

    /**
     * bestfit for one specific index
     * @param index the index of website to be matched
     * @return double, similarity
     */
    public double backwardMatch(int index) {
        if(index >= this.listLength){
            return 0.0;
        }
        double maxSim = 0.0;
        Document doc1 = getDoc(index);
        for( int i = index + 1; i < this.listLength; i++){
            Document doc2 = getDoc(i);
            double temp = doc1.cosineSimilarity(doc2);
            if( temp > maxSim){
                maxSim = temp;
                this.minIndex1 = index;
                this.minIndex2 = i;
            }
        }
        return maxSim;
    }

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

    FindClosestMatch fcm = new FindClosestMatch(websites);

    double cosSim = fcm.bestFit();
    int[] index = fcm.getMinIndex();
    String url1 = fcm.getUrl(index[0]);
    String url2 = fcm.getUrl(index[1]);

    System.out.printf("The two most similar URLs are:\n%s\n%s\nCosine Similarity = %f",url1,url2,cosSim);
    System.out.printf("\nWebsite1 content:\n%s",url1.toString());
    System.out.printf("\nWebsite2 content:\n%s",url2.toString());

  }
}
