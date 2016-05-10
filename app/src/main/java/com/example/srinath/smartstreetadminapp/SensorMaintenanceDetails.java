package com.example.srinath.smartstreetadminapp;




        import android.os.Bundle;
        import android.support.design.widget.CoordinatorLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.Fragment;
        import android.text.InputType;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;
        import com.firebase.client.ValueEventListener;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.HashMap;
        import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorMaintenanceDetails extends Fragment {

EditText status,lastupgraded,name,dname,ddate,dtree,sensor1,sensor2,sensor3;
    EditText model;
String SensorId;

    FloatingActionButton submit,save;

    public SensorMaintenanceDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sensor_maintenance_details, container, false);
        name= (EditText) view.findViewById(R.id.sname);
        status=(EditText) view.findViewById(R.id.status);
        model=(EditText) view.findViewById(R.id.smodel);
        lastupgraded=(EditText) view.findViewById(R.id.date);
        dname=(EditText) view.findViewById(R.id.dname);
        dtree=(EditText) view.findViewById(R.id.dtree);
        ddate=(EditText) view.findViewById(R.id.ddate);
        submit=(FloatingActionButton)view.findViewById(R.id.share_bt);
        save=(FloatingActionButton)view.findViewById(R.id.save);
        Bundle bundle = this.getArguments();
        final String str= bundle.getString("SensorName");
        Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
        Firebase ref = rootRef.child("Sensors");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren())
                {
                    Map<String, String> map = child.getValue(Map.class);


                 String result= map.get("SensorName");

                   if( result.equalsIgnoreCase(str)){
                     /*   Log.v("Name",map.get("SensorName"));
                        Log.v("Date",map.get("SensorDate").toString());
                        Log.v("Model",map.get("SensorModel"));
                        Log.v("Manu",map.get("SensorManufacturer"));
                        Log.v("lastupgraded",map.get("LastUpdated"));
                        Log.v("Status", map.get("SensorStatus"));*/
                        model.setText(map.get("SensorModel"));
                        lastupgraded.setText(map.get("LastUpdated"));
                        status.setText(map.get("SensorStatus"));
                        name.setText(map.get("SensorName"));
                       ddate.setText(map.get("DeploymentDate"));
                       dtree.setText(map.get("TreeName"));
                       dname.setText(map.get("Deployer"));
                        SensorId = child.getKey();
                        /*Firebase rootRef2 = new Firebase("https://smartstreetadminapp2.firebaseio.com/Deployment/");
                        rootRef2.addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(DataSnapshot dataSnapshot2) {
                               for (DataSnapshot child2 : dataSnapshot2.getChildren()) {
                                   if(child2.getKey().equalsIgnoreCase(SensorId))
                                   {
                                        Map<String, String> map2 = child2.getValue(Map.class);
                                        ddate.setText(map2.get("DeploymentDate"));
                                        dtree.setText(map2.get("TreeName"));
                                        dname.setText(map2.get("Deployer"));
                                   }
                               }
                           }
                           @Override
                           public void onCancelled(FirebaseError firebaseError) {

                           }
                       });*/

                   }
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }




        });
          submit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  model.setFocusable(true);
                  model.setFocusableInTouchMode(true);
                  model.setClickable(true);
                  model.setFocusableInTouchMode(true);
                  model.setCursorVisible(true);
                  status.setFocusable(true);
                  status.setFocusableInTouchMode(true);
                  status.setClickable(true);
                  status.setFocusableInTouchMode(true);
                  status.setCursorVisible(true);
                  name.setFocusable(true);
                  name.setFocusableInTouchMode(true);
                  name.setClickable(true);
                  name.setFocusableInTouchMode(true);
                  name.setCursorVisible(true);
                  dtree.setFocusable(true);
                  dtree.setFocusableInTouchMode(true);
                  dtree.setClickable(true);
                  dtree.setFocusableInTouchMode(true);
                  dtree.setCursorVisible(true);
                  ddate.setFocusable(true);
                  ddate.setFocusableInTouchMode(true);
                  ddate.setClickable(true);
                  ddate.setFocusableInTouchMode(true);
                  ddate.setCursorVisible(true);
                  dname.setFocusable(true);
                  dname.setFocusableInTouchMode(true);
                  dname.setClickable(true);
                  dname.setFocusableInTouchMode(true);
                  dname.setCursorVisible(true);
                  CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) save.getLayoutParams();
                  p.setAnchorId(View.NO_ID);
                  save.setLayoutParams(p);
                  save.setVisibility(View.VISIBLE);
                  CoordinatorLayout.LayoutParams p2 = (CoordinatorLayout.LayoutParams) submit.getLayoutParams();
                  p2.setAnchorId(View.NO_ID);
                  submit.setLayoutParams(p2);
                  submit.setVisibility(View.GONE);

              }
          });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/Sensors");

                Firebase ref = rootRef.child(SensorId);
                ref.child("Deployer").setValue(dname.getText().toString());
                Log.v("Inside save", dname.getText().toString());
                ref.child("DeploymentDate").setValue(ddate.getText().toString());
                ref.child("SensorStatus").setValue(status.getText().toString());
                ref.child("TreeName").setValue(dtree.getText().toString());
                ref.child("SensorModel").setValue(model.getText().toString());
                ref.child("SensorName").setValue(name.getText().toString());
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                ref.child("LastUpdated").setValue(formattedDate);
                CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) save.getLayoutParams();
                p.setAnchorId(View.NO_ID);
                save.setLayoutParams(p);
                save.setVisibility(View.GONE);
                CoordinatorLayout.LayoutParams p2 = (CoordinatorLayout.LayoutParams) submit.getLayoutParams();
                p2.setAnchorId(View.NO_ID);
                submit.setLayoutParams(p2);
                submit.setVisibility(View.VISIBLE);
            }
        });
       /* submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setCursorVisible(true);
                Log.v("Inside onclick", "True");
                model.setFocusableInTouchMode(true);
                model.setInputType(InputType.TYPE_CLASS_TEXT);
                model.requestFocus();
                model.setFocusable(true);

                Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/users");
                rootRef.keepSynced(true);
                Firebase ref = rootRef.child(SensorId);
                ref.child("SensorStatus").setValue("InActive");
                ref.child("SensorModel").setValue("3");
                Calendar c = Calendar.getInstance();


                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());


                ref.child("LastUpdated").setValue(formattedDate);
                Firebase rootref2=new Firebase("https://smartstreetadminapp2.firebaseio.com/Deployment");
                Firebase ref2=rootref2.child(SensorId);
                ref2.child("Deployer").setValue("Tom");
                ref2.child("DeploymentDate").setValue("25-Dec-2016");
                ref2.child("TreeName").setValue("Tree 3");
                /*Firebase rootRef2 = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
                Firebase ref2 = rootRef2.child("Deployment/"+SensorId);

                Map<String, String> admin = new HashMap<String, String>();

                admin.put("DeploymentDate", "30-Jan-2016");
                admin.put("TreeName", "Tree2");
                admin.put("Deployer", "Roy");

                ref2.setValue(admin, new Firebase.CompletionListener() {


                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {
                            Toast.makeText(getActivity(),
                                    "Save was unsuceesful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(),
                                    "Save was suceesful", Toast.LENGTH_LONG).show();
                        }
                    }
                });*//*


            }
        });*/
        return view;
    }

}

