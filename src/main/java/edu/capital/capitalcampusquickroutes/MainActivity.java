package edu.capital.capitalcampusquickroutes;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up current location Spinner
        Spinner currentLocationSpinner = (Spinner) findViewById(R.id.currentLocationSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        currentLocationSpinner.setAdapter(adapter);
        currentLocationSpinner.getOnItemSelectedListener();


        final Intent moveToDisplayRoute = new Intent(getApplicationContext(), DisplayRouteActivity.class);
        // Figured out bundling here --> https://stackoverflow.com/questions/8452526/android-can-i-use-putextra-to-pass-multiple-values
        final Bundle extras = new Bundle();

        //----------------------------------------------------------------

        currentLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String currentLocation = parent.getItemAtPosition(position).toString();
                // Figured out bundling here --> https://stackoverflow.com/questions/8452526/android-can-i-use-putextra-to-pass-multiple-values
                extras.putString("CURRENT_LOCATION", currentLocation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //----------------------------------------------------------------

        // Set up destination location spinner
        Spinner destinationLocationSpinner = (Spinner) findViewById(R.id.destinationLocationSpinner);
        // Apply the adapter to the spinner
        destinationLocationSpinner.setAdapter(adapter);

        destinationLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String destinationLocation = parent.getItemAtPosition(position).toString();
                // Figured out bundling here --> https://stackoverflow.com/questions/8452526/android-can-i-use-putextra-to-pass-multiple-values
                extras.putString("DESTINATION_LOCATION", destinationLocation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //----------------------------------------------------------------

        Button findPathButton = (Button) findViewById(R.id.findPath);
        findPathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Figured out bundling here --> https://stackoverflow.com/questions/8452526/android-can-i-use-putextra-to-pass-multiple-values
                moveToDisplayRoute.putExtras(extras);
                startActivity(moveToDisplayRoute);
            }
        });

    }

}
