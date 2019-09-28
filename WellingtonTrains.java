// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 - 2019T2, Assignment 2
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/**
 * WellingtonTrains
 * A program to answer queries about Wellington train lines and timetables for
 *  the train services on those train lines.
 * 
 * See the assignment page for a description of the program and what you have to do.
 */

public class WellingtonTrains{
    //Fields to store the collections of Stations and Lines
    /*# YOUR CODE HERE */
    Map <String, Station> stat = new HashMap<String, Station>();
    Map <String, TrainLine> trainline = new HashMap <String, TrainLine> ();

    // Fields for the suggested GUI.
    private String stationName;            // station to get info about, or to start journey from
    private String lineName;               // train line to get info about.
    private String destinationName;  
    private int startTime = 0;                  // time for enquiring about

    /**
     * main method:  load the data and set up the user interface
     */
    public static void main(String[] args){
        WellingtonTrains wel = new WellingtonTrains();
        wel.loadData();   // load all the data
        wel.setupGUI();   // set up the interface
    }

    /**
     * Load data files
     */
    public void loadData(){
        loadStationData();
        UI.println("Loaded Stations");
        loadTrainLineData();
        UI.println("Loaded Train Lines");
        // The following is only needed for the Completion and Challenge
        loadTrainServicesData();
        UI.println("Loaded Train Services");
    }

    /**
     * User interface has buttons for the queries and text fields to enter stations and and train line
     * You will need to implement the methods here.
     */
    public void setupGUI(){
        UI.addButton("All Stations",   this::listAllStations);
        UI.addButton("All Lines",      this::listAllTrainLines);
        UI.addTextField("Station",     (String name) -> {this.stationName=name;});
        UI.addTextField("Train Line",  (String name) -> {this.lineName=name;});
        UI.addTextField("Destination", (String name) -> {this.destinationName=name;});
        UI.addTextField("Time (24hr)", (String time) ->
            {try{this.startTime=Integer.parseInt(time);}catch(Exception e){UI.println("Enter four digits");}});
        UI.addButton("Lines of Station",    () -> {listLinesOfStation(this.stationName);});
        UI.addButton("Stations on Line",    () -> {listStationsOnLine(this.lineName);});
        UI.addButton("Stations connected?", () -> {checkConnected(this.stationName, this.destinationName);});
        UI.addButton("Next Services",       () -> {findNextServices(this.stationName, this.startTime);});
        UI.addButton("Find Trip",           () -> {findTrip(this.stationName, this.destinationName, this.startTime);});
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);
    }

    // Methods for loading data and answering queries

    public void listAllStations(){
        //core
        for (String variableName : stat.keySet())
        {

            UI.println("Name: " + variableName);
        }
    }

    public void listAllTrainLines(){
        //core
        for (String variableName : trainline.keySet())
        {

            UI.println("Name: " + variableName);
        }

    }

    public void loadStationData(){
        String filename = "data/stations.data";
        File stations = new File(filename);
        try{
            Scanner sc = new Scanner (stations);
            while (sc.hasNext()){
                String name = sc.next();
                stat.put(name, new Station(name, sc.nextInt(), sc.nextDouble()));
                UI.println("stationName:" + stat.get(name).getName());
            }
        }
        catch (IOException e){

        }

    }

    public void loadTrainLineData(){
        String filename = "data/train-lines.data";
        File trainlines = new File(filename);
        try{
            Scanner sc = new Scanner (trainlines);
            while (sc.hasNext()){
                String name = sc.next();
                trainline.put(name, new TrainLine(name));

                File[] fileArray = new File("data/").listFiles();
                for(File f: fileArray) // loop thru all files
                {

                    if(f.getName().endsWith("stations.data")) // to deal with the .txt files.
                    {

                        try{
                            Scanner s = new Scanner(f); // to read the files
                            while(s.hasNext()) {
                                String stationName = s.next();
                                trainline.get(name).addStation(stat.get(stationName));
                                //stat.get(stationName).addTrainLine(trainline.get(name));
                                UI.println("tr: " + trainline.get(name).toString());
                                UI.println("st: " + stat.get(stationName).toString());
                            }
                        }
                        catch(IOException e){}
                    }

                }
            }
        }
        catch (IOException e){}

    }

    public void loadTrainServicesData(){
    }

    public void listLinesOfStation(String stationName){
        //core
    }

    public void listStationsOnLine(String lineName){
        //core
    }

    public void checkConnected(String stationName, String destination){
        //core
    }

    public void findNextServices(String stationName, int startTime){

    }

    public void findTrip(String stationName, String destinationName, int startTime){

    }

    // Utility method to help with reading data files
    /** Read all lines in a file into a list of Strings */
    public ArrayList<String> readAllLines(String filename){
        try{
            ArrayList<String> ans = new ArrayList<String>();
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()){
                ans.add(sc.nextLine());
            }
            sc.close();
            return ans;
        }
        catch(IOException e){UI.println("Fail: " + e); return null;}
    }

}
