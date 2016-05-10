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
        import android.widget.Toast;

        import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;
        import com.firebase.client.ValueEventListener;

        import java.util.ArrayList;
        import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorMaintenance extends Fragment {


    public SensorMaintenance() {
        // Required empty public constructor
    }
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> sensoradapter;
    ListView list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sensor_maintenance, container, false);
        Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
        rootRef.keepSynced(true);
        list = (ListView) view.findViewById(R.id.tree_list);

      Firebase ref = rootRef.child("Sensors");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Map<String, String>> sensorList = new ArrayList<Map<String, String>>();


                for (DataSnapshot child : dataSnapshot.getChildren())
                {
                    Map<String, String> map = child.getValue(Map.class);
                    String name = "Hello";
                    Log.v("Inside detaisl",map.get("SensorName").toString());
                    name = map.get("SensorName").toString();
                    Log.v("name is:", name);
                        listItems.add(name);
                    if (name != null)
                    {
                        sensorList.add(map);

                    } else
                        Log.v("name", "name");
                }

                Log.v("Size:", Integer.toString(sensorList.size()));
                Log.v("Size of list:", Integer.toString(listItems.size()));
                //Log.v("Element in 1 is:", listItems.get(1));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }




        });


listItems.add("Sensor");
        sensoradapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.listitemview, listItems);
        list.setAdapter(sensoradapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> p, View view, int position, long id) {
                SensorMaintenanceDetails fragment = new SensorMaintenanceDetails();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();


                Log.v("Position",Integer.toString(position));
String data = listItems.get(position);
                Log.v("Data:",data);
                Bundle bundle = new Bundle();
                bundle.putString("SensorName", data);

                fragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            } });

        return view;
    }

}
