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
public class TreeRecordDetails extends Fragment {
TextView tname,tmodel,tmanu,tdate;

    public TreeRecordDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        View view=inflater.inflate(R.layout.fragment_tree_record_details, container, false);
        final String str= bundle.getString("TreeName");
        Log.v("TreeName", str);
        Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
        tname=(TextView)view.findViewById(R.id.tname);
        tmodel=(TextView)view.findViewById(R.id.tmodel);
        tmanu=(TextView)view.findViewById(R.id.tmanu);
        tdate=(TextView)view.findViewById(R.id.tdate);



        Firebase ref = rootRef.child("Trees");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<String, String> map = child.getValue(Map.class);


                    String result = map.get("TreeName");

                    if (result.equalsIgnoreCase(str)) {




                        tname.setText(map.get("TreeName"));
                        tmodel.setText(map.get("TreeModel"));
                        tmanu.setText(map.get("TreeManufacturer"));
                        tdate.setText(map.get("TreeDate"));

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


