package com.example.srinath.smartstreetadminapp;



        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;
        import com.firebase.client.ValueEventListener;

        import java.util.ArrayList;
        import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorInteraction extends Fragment {


    public SensorInteraction() {
        // Required empty public constructor
    }

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> sensoradapter;
    ListView list;
    TextView tree1,tuser1,tuser2,tree2,t2user1,t2user2;
Boolean flag= true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sensor_interaction, container, false);
        Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
        tree1=(TextView) view.findViewById(R.id.Tree1);
        tuser1= (TextView)view.findViewById(R.id.tree1user1);

        tree2=(TextView)view.findViewById(R.id.Tree2);
        t2user1=(TextView)view.findViewById(R.id.tree2user1);
        t2user2=(TextView)view.findViewById(R.id.tree2user2);
        Firebase ref = rootRef.child("SensorInteraction");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<String, String> map = child.getValue(Map.class);
                  String users[] =map.get("User").split(",");
                    String sensor = map.get("Sensors");
                    Log.v("Sensors",sensor);
                    Log.v("users:",users[0]);
                    if(flag){
                        tree1.setText(map.get("Sensors"));
                        tuser1.setText(users[0]);

flag = false;
                    }else{
                        tree2.setText(map.get("Sensors"));
                        t2user1.setText(users[0]);
                        t2user2.setText(users[1]);
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
