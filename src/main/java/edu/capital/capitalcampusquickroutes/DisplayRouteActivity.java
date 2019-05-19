package edu.capital.capitalcampusquickroutes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.capital.capitalcampusquickroutes.views.CustomView;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayRouteActivity extends AppCompatActivity {

    CustomView mCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_route);



        Map<Integer, String> vertices = new HashMap<Integer, String>();
        String[] myConnectionsArray = {"2 6,14 4", "1 6,15 3,16 3,3 1", "2 1,4 9,17 7", "3 9,10 1,5 1", "4 1,10 1,6 1", "5 1,7 5,10 1", "6 5,11 2,8 6", "7 6,39 6,9 1", "8 1,66 13", "4 1,5 1,6 1,21 3", "7 2,12 2", "11 2,13 1,34 1", "12 1,35 1", "1 4,15 3,24 3", "14 3,16 2,2 3,26 1", "15 2,2 3,75 11", "3 7,18 1,28 4", "17 1,19 1", "18 1,20 2", "19 2,29 1,31 4", "10 3,22 2,31 1", "21 2,34 2,32 1", "36 1", "14 3,25 1,76 6", "24 1,26 1", "15 1,27 1", "26 1", "17 4,29 1,50 1", "28 1,20 1,52 1,30 4", "29 4,31 2,49 1,43 1", "20 4,21 1,32 2,43 3,30 2", "31 2,22 1,33 2,40 1", "32 2,34 1,35 1", "22 2,12 1,33 1", "33 1,13 1,36 1,42 1", "35 1,23 1,37 1", "36 1,38 1,47 3", "37 1,39 2", "38 2,8 6,39 6", "32 1,41 1,43 1", "40 1,42 1", "41 1,35 1,44 1", "30 2,31 3,40 1,45 2,48 1", "45 2,42 1,46 1", "48 2,43 2,44 2,46 2,53 3", "45 2,44 1,47 2,53 2", "46 2,37 3,55 2,53 2", "43 1,45 2,71 4", "30 1,72 1", "28 1,51 2,74 3", "50 2,52 1", "51 1,29 1", "45 3,46 2,47 2,55 2,54 1", "53 1,57 1", "53 2,47 2,56 1", "55 1,58 1", "70 2,54 1,62 2,99 7", "56 1,59 2", "58 2,61 1,60 1,39 6", "61 1,59 1,66 1,64 1", "59 1,60 1,62 3", "57 2,61 3,63 2", "62 2", "60 1,103 5,65 1", "66 1,64 1,69 2,105 8", "60 1,9 13,67 365 1", "66 3,68 1", "67 1", "65 2", "71 2,57 2,98 8", "72 3,48 4,70 2,82 3", "73 2,49 1,71 3", "74 5,72 2,81 3", "75 1,50 3,73 5,79 1", "76 8,16 11,74 1,78 1", "24 6,75 8,77 1", "76 1,78 8,83 1", "77 8,75 1,79 1,85 1", "78 1,74 1,80 1,93 8", "79 1,81 1", "80 1,73 3,82 2", "81 2,71 3,96 3", "77 1,84 7,86 4", "83 7,85 1", "84 1,78 1,87 4", "83 4,87 8", "86 8,85 4,91 5", "89 1", "88 1,90 1", "89 1,91 1,92 1", "90 1,87 5,93 1,92 1", "90 1,1 1,93 1,95 4", "91 1,79 8,96 5,94 1,92 1", "93 1,97 5", "92 4", "93 5,82 3,98 3,97 1", "94 5,96 1", "96 3,70 8,100 1", "57 7,103 4,100 1", "98 1,99 1,102 2", "102 1", "100 2,101 1,104 2", "99 4,64 5,104 1", "102 2,103 1,105 1", "104 1,65 8"};
        for (int i = 0; i < 105; ++i) {
            vertices.put(i, myConnectionsArray[i]);
        }



        // set up an array to hold the names of valid locations on the map
        Resources res = getResources();
        String[] locationNames = res.getStringArray(R.array.locations);



        // set up a hashmap that maps the name of a legitimate location that the user can travel to the vertex number that goes with it.
        Integer[] legitLocationsVertices = {69, 23, 68, 22, 67, 90, 49, 29, 101, 98, 11, 95, 72, 41, 63, 49, 10, 78, 19, 38, 54, 96, 15, 26, 58, 71, 11, 82, 80, 51, 84, 75, 65, 65, 102, 97, 88, 77, 70, 75};
        Map<String, Integer> verticesMappedToNames = new HashMap<String, Integer>();
        for (int i = 0; i < locationNames.length; ++i) {
            verticesMappedToNames.put(locationNames[i], legitLocationsVertices[i]);
        }



        // set up the matrices for holding the path and parent information
        int infinity = 100000;
        int[][] path = new int[105][105];
        int[][] parent = new int[105][105];

        // Set all the values in the path and the parent to infinity to mark that it hasn't been set yet
        for (int k = 0; k < 105; ++k) {
            for (int i = 0; i < 105; ++i) {
                path[k][i] = infinity;
                parent[k][i] = infinity;
            }
        }

        // Set up the initial path and parent using our connections array
        for (int k = 0; k < 105; ++k) {
            String[] vertexConnections = myConnectionsArray[k].split(",");
            path[k][k] = 0;
            for (int i = 0; i < vertexConnections.length; ++i) {
                String[] vertexAndWeight = vertexConnections[i].split(" ");
                int connectingVertex = (Integer.parseInt(vertexAndWeight[0]) - 1); // We subtract 1 at the end here because I messed up and all the number representations for the vertices in myConnectionsArray are 1 greater than they should be because I just went off of my hand drawn graph which labels the vertices 1-105
                int weight = Integer.parseInt(vertexAndWeight[1]);

                // set the path and the parent according to their connections
                path[k][connectingVertex] = weight;
                path[connectingVertex][k] = weight;

                parent[k][connectingVertex] = k;
                parent[connectingVertex][k] = connectingVertex;
            }
        }

        // Use floyd's algorithm to find the shortest paths between two points
        for (int k = 0; k < 105; ++k) {
            for (int i = 0; i < 105; ++i) {
                for (int j = 0; j < 105; ++j) {
                    // if the path just found is shorter than the one already found then update it.
                    if (path[i][j] > path[i][k] + path[k][j]) {
                        path[i][j] = path[i][k] + path[k][j];
                        parent[i][j] = parent[k][j];
                    }
                }
            }
        }


        // Figured out bundling here --> https://stackoverflow.com/questions/8452526/android-can-i-use-putextra-to-pass-multiple-values
        Intent moveToDisplayRoute = getIntent();
        Bundle extras = moveToDisplayRoute.getExtras();

        // Grab the destination and the location names and vertices from the spinners
        String currentLocation, destinationLocation;
        int currentLocationVertex, destinationLocationVertex;
        if (extras != null) {
            currentLocation = extras.getString("CURRENT_LOCATION");
            destinationLocation = extras.getString("DESTINATION_LOCATION");
            // We subtract 1 at the end here because I messed up and all the number representations for the vertices in myConnectionsArray are 1 greater than they should be because I just went off of my hand drawn graph which labels the vertices 1-105
            currentLocationVertex = verticesMappedToNames.get(currentLocation) - 1;
            destinationLocationVertex = verticesMappedToNames.get(destinationLocation) - 1;
        }
        else {
            currentLocation = "";
            destinationLocation = "";
            currentLocationVertex = 0;
            destinationLocationVertex = 0;
        }

        // get the shortest path
        List<Integer> shortestPath = new ArrayList<Integer>();
        shortestPath.add(destinationLocationVertex);
        int dl = destinationLocationVertex;
        while (dl != currentLocationVertex) {
            shortestPath.add(parent[currentLocationVertex][dl]);
            dl = parent[currentLocationVertex][dl];
        }

        GlobalVariables.shortestPath = shortestPath;

        mCustomView = (CustomView) findViewById(R.id.routeDisplay);


        System.out.println("Current Location: " + currentLocation);
        System.out.println("Destination Location: " + destinationLocation);
        System.out.println(shortestPath);


//        PhotoView mapPhotoView = (PhotoView) findViewById(R.id.mapPhotoView);
//        mapPhotoView.setImageResource(R.drawable.capital_university_map);

    }
}
