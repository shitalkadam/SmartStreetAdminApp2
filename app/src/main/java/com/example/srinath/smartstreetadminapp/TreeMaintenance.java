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

        import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;
        import com.firebase.client.ValueEventListener;

        import java.util.ArrayList;
        import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TreeMaintenance extends Fragment {


    public TreeMaintenance() {
        // Required empty public constructor
    }

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> sensoradapter;
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tree_maintenance, container, false);
        list = (ListView) view.findViewById(R.id.tree_list);
        Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
        Firebase ref = rootRef.child("Trees");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Map<String, String>> TreeList = new ArrayList<Map<String, String>>();


                for (DataSnapshot child : dataSnapshot.getChildren())
                {
                    Map<String, String> map = child.getValue(Map.class);
                    String name = "Hello";
                    name = map.get("TreeName").toString();
                    Log.v("name is:", name);
                    listItems.add(name);
                    if (name != null)
                    {
                        TreeList.add(map);

                    } else
                        Log.v("name", "name");
                }

                Log.v("Size:", Integer.toString(TreeList.size()));
                Log.v("Size of list:", Integer.toString(listItems.size()));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }




        });

        listItems.add("Tree");
        sensoradapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.listitemview, listItems);
        list.setAdapter(sensoradapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> p, View view, int position, long id) {
                TreeMaintenanceDetails fragment = new TreeMaintenanceDetails();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                Log.v("Position",Integer.toString(position));
                String data = listItems.get(position);
                Log.v("Data:",data);
                Bundle bundle = new Bundle();
                bundle.putString("TreeName", data);

                fragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            } });

        return view;
    }

}
