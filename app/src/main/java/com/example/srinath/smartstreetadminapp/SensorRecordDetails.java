package com.example.srinath.smartstreetadminapp;



        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;
        import com.firebase.client.ValueEventListener;

        import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorRecordDetails extends Fragment {

TextView sname,smodel,smanu,smanudate,stype;
    public SensorRecordDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        View view=inflater.inflate(R.layout.fragment_sensor_record_details, container, false);
        final String str= bundle.getString("SensorName");
        Log.v("SensorName",str);
        Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
sname=(TextView)view.findViewById(R.id.sname);
        smodel=(TextView)view.findViewById(R.id.smodel);
        smanu=(TextView)view.findViewById(R.id.smanu);
        smanudate=(TextView)view.findViewById(R.id.sdate);
        stype=(TextView)view.findViewById(R.id.stype);


        Firebase ref = rootRef.child("Sensors");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<String, String> map = child.getValue(Map.class);


                    String result = map.get("SensorName");

                    if (result.equalsIgnoreCase(str)) {
                        Log.v("Name", map.get("SensorName"));
                        //   Log.v("Date",map.get("SensorDate").toString());
                        Log.v("Model", map.get("SensorModel"));
                        Log.v("Manu", map.get("SensorManufacturer"));
                        Log.v("lastupgraded", map.get("LastUpdated"));
                        Log.v("Status", map.get("SensorStatus"));
                        stype.setText(map.get("SensorType"));
                        sname.setText(map.get("SensorName"));
                        smodel.setText(map.get("SensorModel"));
                        smanu.setText(map.get("SensorManufacturer"));
                        smanudate.setText(map.get("SensorDate"));

                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return view;
    }

}
