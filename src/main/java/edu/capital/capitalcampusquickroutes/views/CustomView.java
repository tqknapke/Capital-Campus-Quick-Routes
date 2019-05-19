package edu.capital.capitalcampusquickroutes.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import edu.capital.capitalcampusquickroutes.GlobalVariables;

public class CustomView extends View {

    // Declare all the different points and paints you need to make the map up here
    List<Point> pointsList = new ArrayList<Point>();
    int[] pointPositions = {1367,15, 1373,437, 1375,517, 1395,1161, 1395,1231, 1400,1300, 1407,1633, 1415,2010, 1411,2089, 1370,1233, 1353,1633, 1255,1577, 1215,1660, 1187,59, 1263,349, 1253,488, 1251,570, 1251,648, 1105,784, 1083,932, 1200,1242, 1211,1404, 1205,1750, 1003,100, 973,175, 997,219, 890,329, 993,665, 981,903, 1003,1233, 1135,1235, 1133,1407, 1135,1575, 1207,1575, 1135,1641, 1135,1755, 1135,1841, 1135,1905, 1135,2010, 1063,1411, 1065,1550, 1057,1643, 960,1407, 1025,1643, 967,1515, 967,1643, 970,1843, 930,1410, 910,1230, 885,713, 905,817, 905,915, 875,1710, 810,1710, 873,1840, 870,1907, 750,1705, 847,1905, 840,2010, 750,2010, 750,1941, 751,1845, 661,1867, 696,2010, 696,2085, 754,2090, 750,2407, 780,2435, 696,2357, 700,1505, 687,1407, 735,1221, 687,1120, 725,760, 725,673, 723,155, 665,175, 667,700, 670,777, 650,965, 507,1013, 513,1253, 575,210, 595,587, 580,730, 430,260, 433,785, 367,507, 365,557, 327,647, 350,813, 280,841, 271,930, 120,983, 37,930, 327,1267, 237,1257, 325,1515, 403,1700, 300,1697, 353,1845, 290,1849, 401,2010, 295,2010, 293,2089}; //TODO vertex 70

    Paint blackPaint = new Paint();
    Paint redPaint = new Paint();

    Map<Integer, String> vertices = new HashMap<Integer, String>();
    String[] myConnectionsArray = {"2 6,14 4", "1 6,15 3,16 3,3 1", "2 1,4 9,17 7", "3 9,10 1,5 1", "4 1,10 1,6 1", "5 1,7 5,10 1", "6 5,11 2,8 6", "7 6,39 6,9 1", "8 1,66 13", "4 1,5 1,6 1,21 3", "7 2,12 2", "11 2,13 1,34 1", "12 1,35 1", "1 4,15 3,24 3", "14 3,16 2,2 3,26 1", "15 2,2 3,75 11", "3 7,18 1,28 4", "17 1,19 1", "18 1,20 2", "19 2,29 1,31 4", "10 3,22 2,31 1", "21 2,34 2,32 1", "36 1", "14 3,25 1,76 6", "24 1,26 1", "15 1,27 1", "26 1", "17 4,29 1,50 1", "28 1,20 1,52 1,30 4", "29 4,31 2,49 1,43 1", "20 4,21 1,32 2,43 3,30 2", "31 2,22 1,33 2,40 1", "32 2,34 1,35 1", "22 2,12 1,33 1", "33 1,13 1,36 1,42 1", "35 1,23 1,37 1", "36 1,38 1,47 3", "37 1,39 2", "38 2,8 6,39 6", "32 1,41 1,43 1", "40 1,42 1", "41 1,35 1,44 1", "30 2,31 3,40 1,45 2,48 1", "45 2,42 1,46 1", "48 2,43 2,44 2,46 2,53 3", "45 2,44 1,47 2,53 2", "46 2,37 3,55 2,53 2", "43 1,45 2,71 4", "30 1,72 1", "28 1,51 2,74 3", "50 2,52 1", "51 1,29 1", "45 3,46 2,47 2,55 2,54 1", "53 1,57 1", "53 2,47 2,56 1", "55 1,58 1", "70 2,54 1,62 2,99 7", "56 1,59 2", "58 2,61 1,60 1,39 6", "61 1,59 1,66 1,64 1", "59 1,60 1,62 3", "57 2,61 3,63 2", "62 2", "60 1,103 5,65 1", "66 1,64 1,69 2,105 8", "60 1,9 13,67 365 1", "66 3,68 1", "67 1", "65 2", "71 2,57 2,98 8", "72 3,48 4,70 2,82 3", "73 2,49 1,71 3", "74 5,72 2,81 3", "75 1,50 3,73 5,79 1", "76 8,16 11,74 1,78 1", "24 6,75 8,77 1", "76 1,78 8,83 1", "77 8,75 1,79 1,85 1", "78 1,74 1,80 1,93 8", "79 1,81 1", "80 1,73 3,82 2", "81 2,71 3,96 3", "77 1,84 7,86 4", "83 7,85 1", "84 1,78 1,87 4", "83 4,87 8", "86 8,85 4,91 5", "89 1", "88 1,90 1", "89 1,91 1,92 1", "90 1,87 5,93 1,92 1", "90 1,1 1,93 1,95 4", "91 1,79 8,96 5,94 1,92 1", "93 1,97 5", "92 4", "93 5,82 3,98 3,97 1", "94 5,96 1", "96 3,70 8,100 1", "57 7,103 4,100 1", "98 1,99 1,102 2", "102 1", "100 2,101 1,104 2", "99 4,64 5,104 1", "102 2,103 1,105 1", "104 1,65 8"};



    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

    }

    public void swapColor(Canvas canvas) {


    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < 105; ++i) {
            vertices.put(i, myConnectionsArray[i]);
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


        // Set the color of the paints that we will use black for the initial path as a whole
        blackPaint.setColor(Color.BLACK);
        redPaint.setColor(Color.RED);

        // finish the creation of the list of points.
        for(int i = 0; i < pointPositions.length; i+=2) {
            Point newPoint = new Point(pointPositions[i], pointPositions[i+1]);
            pointsList.add(newPoint);
        }

        // Draw the vertices of the map of campus
        for (int i = 0; i < pointsList.size(); ++i) {
            canvas.drawCircle((float) pointsList.get(i).x, (float) pointsList.get(i).y, 10, blackPaint);
        }



        // Draw the connections between the vertices for the map of campus
        for (int i = 0; i < 105; ++i) {
            for (int j = 0; j < i; ++j) {
                if (path[i][j] != 100000) {
                    // if both of the vertices are in the shortest path list then use red for the line
                    if (GlobalVariables.shortestPath.contains(i) && GlobalVariables.shortestPath.contains(j)) {
                        canvas.drawLine((float) pointsList.get(i).x, (float) pointsList.get(i).y, (float) pointsList.get(j).x, (float) pointsList.get(j).y, redPaint);
                    }
                    // else it's not part of the shortest path so it should not be highlighted
                    else {
                        canvas.drawLine((float) pointsList.get(i).x, (float) pointsList.get(i).y, (float) pointsList.get(j).x, (float) pointsList.get(j).y, blackPaint);
                    }

                }
            }
        }

    }
}
