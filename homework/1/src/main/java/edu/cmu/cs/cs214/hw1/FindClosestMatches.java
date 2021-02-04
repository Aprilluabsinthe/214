package edu.cmu.cs.cs214.hw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The closest match to each document in a set
 */
public class FindClosestMatches {
    private FindClosestMatch fcm;
    private List<Double> cossimList;
    private Map<String,String> bestmatches;

    /**
     * default constructor
     */
    public FindClosestMatches(){
        this.cossimList = new ArrayList<Double>();
        this.fcm = new FindClosestMatch();
        this.bestmatches = new HashMap<>();
    }

    /**
     * constructor with arguments
     * @param websites the websites to be copies
     */
    public FindClosestMatches(String[] websites){
        fcm = new FindClosestMatch(websites);
        cossimList = new ArrayList<Double>();
        this.bestmatches = new HashMap<>();
    }

    /**
     * for visit list length in fcm
     * @return list length
     */
    public int getListLength(){
        return this.fcm.getListLength();
    }

    /**
     * get document from list by index
     * @param i index
     * @return Document in list
     */
    public Document getDoc(int i) {
        return this.fcm.getDoc(i);
    }

    /**
     * get the index of one document in a list
     * @param doc a given document
     * @return index in i=list
     */
    public int getDocIndex(Document doc) {
        return this.fcm.getDocIndex(doc);
    }

    /**
     * give the index in list, return the String url
     * @param i index
     * @return url
     */
    public String getUrl(int i) {
        return this.fcm.getUrl(i);
    }

    /**
     * get the index in list
     * @param url given website url
     * @return index in list
     */
    public int getUrlIndex(String url) {
        return this.fcm.getUrlIndex(url);
    }


    /**
     * for visit private member cossinList
     * @param index index in cossimList
     * @return cossim cache
     */
    public double getCossim(int index) {
        return this.cossimList.get(index);
    }

    /**
     * for visit bestmatches map
     * @return Map bestmatches
     */
    public Map<String,String> getMap() {
        return this.bestmatches;
    }

    /**
     * put cosine similarity information into map
     */
    public void putMap(){
        int numurl = getListLength();
        for(int i = 0 ; i < numurl ; i++){
            double cossimi = this.fcm.backwardMatch(i);
            int[] index = this.fcm.getMinIndex();
            if( !bestmatches.containsValue(index[0])){
                this.bestmatches.put(this.fcm.getUrl(index[0]) , this.fcm.getUrl(index[1]));
                this.bestmatches.put(this.fcm.getUrl(index[1]) , this.fcm.getUrl(index[0]));
                this.cossimList.add(cossimi);
                this.cossimList.add(cossimi);
            }
        }
    }

    /**
     * main function
     * @param args argument
     */
    public static void main(String[] args){
        String[] websites = {
                "https://en.wikipedia.org/wiki/Bee-eater",
                "https://en.wikipedia.org/wiki/Coraciidae",
                "https://en.wikipedia.org/wiki/Aung_San_Suu_Kyi",
                "https://en.wikipedia.org/wiki/Thein_Sein",
                "https://en.wikipedia.org/wiki/Schloss_Freudenberg",
                "https://en.wikipedia.org/wiki/Wiesbaden",
                "https://en.wikipedia.org/wiki/1947_West_Virginia_State_Yellow_Jackets_football_team",
                "https://en.wikipedia.org/wiki/1947_Howard_Bison_football_team",
                "https://en.wikipedia.org/wiki/List_of_astronomical_interferometers_at_visible_and_infrared_wavelengths",
                "https://en.wikipedia.org/wiki/List_of_telescope_types",
                "https://en.wikipedia.org/wiki/Twelve_Point_Buck",
                "https://en.wikipedia.org/wiki/UK_Independent_Singles_and_Albums_Charts",
        };
        FindClosestMatches fcms = new FindClosestMatches(websites);
        fcms.putMap();
        int index = 0;
        for( Map.Entry<String,String> entry: fcms.getMap().entrySet()){
            System.out.printf("\nthe best matches for url:%s\nis url:%s\nwith cosine similarity:%f\n", entry.getKey(),entry.getValue(),fcms.getCossim(index++));
        }
    }

}
