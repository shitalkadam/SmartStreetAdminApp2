package com.example.srinath.smartstreetadminapp;




        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TreeRegister extends Fragment {
    EditText sensor_name,sensor_model,sensor_manufacturer,sensor_date;
    Button submit;

    public TreeRegister() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tree_register, container, false);

        sensor_name= (EditText)view.findViewById(R.id.sensorName);
        sensor_model =(EditText)view.findViewById(R.id.sensorModel);
        sensor_manufacturer =(EditText)view.findViewById(R.id.sensorManufacturer);
        sensor_date =(EditText)view.findViewById(R.id.sensorManufacturedDate);
        submit = (Button)view.findViewById(R.id.submit_bt);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Toast.makeText(getActivity(),
                        "Inside submit", Toast.LENGTH_LONG).show();*/
                Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
                Firebase ref = rootRef.child("Trees");
                Map<String, String> admin = new HashMap<String, String>();
                admin.put("TreeName", sensor_name.getText().toString());
                final String treename= sensor_name.getText().toString();
                admin.put("TreeModel", sensor_model.getText().toString());
                admin.put("TreeManufacturer",sensor_manufacturer.getText().toString());

                admin.put("TreeStatus","Active");

                Calendar c = Calendar.getInstance();

                SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());

                try {

                    Date temp = df2.parse(sensor_date.getText().toString());
                    String tempdate = df.format(temp);
                    admin.put("TreeDate",tempdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                admin.put("LastUpdated",formattedDate);
                admin.put("Deployer","Rhodes");
                admin.put("DeploymentDate","08-Feb-2016");
                StringBuilder builder = new StringBuilder();

                    builder.append("LED Sensor,");
                builder.append("Speaker 1");
                String arr = builder.toString();
                admin.put("Sensor",arr);
                ref.push().setValue(admin, new Firebase.CompletionListener() {


                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {
                            /*Toast.makeText(getActivity(),
                                    "Save was unsuceesful", Toast.LENGTH_LONG).show();*/
                        } else {
                            /*Toast.makeText(getActivity(),
                                    "Save was suceesful", Toast.LENGTH_LONG).show();*/
                        }
                        TreeRecordDetails fragment = new TreeRecordDetails();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getFragmentManager().beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putString("TreeName",treename);
                        fragment.setArguments(bundle);

                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.commit();



                    }
                });                  // do something
            }
        });
        return view;
        // Inflate the layout for this fragment
    }

}
