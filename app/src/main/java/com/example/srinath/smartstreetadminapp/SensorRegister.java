package com.example.srinath.smartstreetadminapp;



        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
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
public class SensorRegister extends Fragment {
EditText sensor_name,sensor_model,sensor_manufacturer,sensor_date,sensor_type;
Button submit;
    public SensorRegister() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sensor_register, container, false);
sensor_type=(EditText)view.findViewById(R.id.sensorType);
        sensor_name= (EditText)view.findViewById(R.id.sensorName);
        sensor_model =(EditText)view.findViewById(R.id.sensorModel);
        sensor_manufacturer =(EditText)view.findViewById(R.id.sensorManufacturer);
        sensor_date =(EditText)view.findViewById(R.id.sensorManufacturedDate);
        submit = (Button)view.findViewById(R.id.submit_bt);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getActivity(),
                        "Inside submit", Toast.LENGTH_LONG).show();*/
                Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
                Firebase ref = rootRef.child("Sensors");
                Map<String, String> admin = new HashMap<String, String>();
                admin.put("SensorName", sensor_name.getText().toString());
                final String sensorname=sensor_name.getText().toString();
                admin.put("SensorModel", sensor_model.getText().toString());
                admin.put("SensorManufacturer",sensor_manufacturer.getText().toString());
             //   admin.put("SensorDate",sensor_date.getText().toString());
                admin.put("SensorStatus","Active");
                Calendar c = Calendar.getInstance();

                SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());

                try {

                Date  temp = df2.parse(sensor_date.getText().toString());
                    String tempdate = df.format(temp);
                    admin.put("SensorDate",tempdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

admin.put("SensorType",sensor_type.getText().toString());
                admin.put("LastUpdated",formattedDate);
                admin.put("Deployer","Tom");
                admin.put("DeploymentDate","25-Feb-2015");
                admin.put("TreeName","Tree1-RaspberryPi 1");

                ref.push().setValue(admin, new Firebase.CompletionListener() {


                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {
                           /* Toast.makeText(getActivity(),
                                    "Save was unsuceesful", Toast.LENGTH_LONG).show();*/
                        } else {
                          /*  Toast.makeText(getActivity(),
                                    "Save was suceesful", Toast.LENGTH_LONG).show();*/
                        }
                    }
                 });
             /*   Firebase ref2 = rootRef.child("Admin");
                Map<String, String> admin2 = new HashMap<String, String>();
                admin2.put("Name","Jerry Gao");
                admin2.put("EmailId","Jerry.Gao@gmail.com");
                admin2.put("password","12345");
                admin2.put("contact","6692549871");
                ref2.push().setValue(admin2, new Firebase.CompletionListener() {


                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {
                            Toast.makeText(getActivity(),
                                    "Save was unsuceesful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(),
                                    "Save was suceesful", Toast.LENGTH_LONG).show();
                        }*/
                        SensorRecordDetails fragment = new SensorRecordDetails();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getFragmentManager().beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putString("SensorName",sensorname);
                        fragment.setArguments(bundle);

                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.commit();


                 /*   }
                });*/
            }
        });
        return view;

    }


    }


