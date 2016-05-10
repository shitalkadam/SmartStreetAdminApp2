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
public class AdminProfile extends Fragment {

    TextView name,contact,email,pass;
    public AdminProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_admin_profile, container, false);
        Bundle bundle = this.getArguments();
        final String str= bundle.getString("UserId");
        Log.v("User",str);
        Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
        name=(TextView)view.findViewById(R.id.name);
        contact=(TextView)view.findViewById(R.id.contact);
        email=(TextView)view.findViewById(R.id.email);
        pass=(TextView)view.findViewById(R.id.password);



        Firebase ref = rootRef.child("Admin");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren())
                {
                Map<String, String> map = child.getValue(Map.class);


                String result = map.get("EmailId");

                if (result.equalsIgnoreCase(str)) {

                    //   Log.v("Date",map.get("SensorDate").toString());
                    Log.v("Model", map.get("EmailId"));
                    Log.v("Manu", map.get("Name"));
                    Log.v("lastupgraded", map.get("contact"));
                    Log.v("Status", map.get("password"));
                    name.setText(map.get("Name"));
                    contact.setText(map.get("contact"));
                    email.setText(map.get("EmailId"));
                    pass.setText(map.get("password"));
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
