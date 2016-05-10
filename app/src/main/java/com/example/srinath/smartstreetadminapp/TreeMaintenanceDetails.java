package com.example.srinath.smartstreetadminapp;



        import android.os.Bundle;
        import android.support.design.widget.CoordinatorLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
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
public class TreeMaintenanceDetails extends Fragment {
FloatingActionButton submit,save;

String TreeId;
    EditText status,lastupgraded,model,name,dname,ddate,dtree,sensor1,sensor2,sensor3;
    public TreeMaintenanceDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tree_maintenance_details, container, false);
        Bundle bundle = this.getArguments();
        final String str= bundle.getString("TreeName");
        Log.v("Str",str);
        name= (EditText) view.findViewById(R.id.Ttree);
        status=(EditText) view.findViewById(R.id.Tstatus);
        model=(EditText) view.findViewById(R.id.Tmodel);
        lastupgraded=(EditText) view.findViewById(R.id.Tdate);
        dname=(EditText) view.findViewById(R.id.Tdeployer);
        //dtree=(EditText) view.findViewById(R.id.Tname);
        ddate=(EditText) view.findViewById(R.id.Tdeployerdate);
        sensor1=(EditText) view.findViewById(R.id.sensor1);
        sensor2=(EditText) view.findViewById(R.id.sensor2);
        sensor3=(EditText) view.findViewById(R.id.sensor3);
        submit=(FloatingActionButton)view.findViewById(R.id.share_bt);
        save=(FloatingActionButton)view.findViewById(R.id.save);
        //Log.v("In next fragment:", str);
        Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/");



        Firebase ref = rootRef.child("Trees");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<String, String> map = child.getValue(Map.class);


                    String result = map.get("TreeName");
                    Log.v("result", result);
                    if (result.equalsIgnoreCase(str)) {
                        Log.v("Name", map.get("TreeName"));
                        Log.v("Date", map.get("TreeDate"));
                        Log.v("Model", map.get("TreeModel"));
                        Log.v("Manu", map.get("TreeManufacturer"));
                        Log.v("lastupgraded", map.get("LastUpdated"));
                        Log.v("Status", map.get("TreeStatus"));
                        model.setText(map.get("TreeModel"));
                        lastupgraded.setText(map.get("LastUpdated"));
                        status.setText(map.get("TreeStatus"));
                        name.setText(map.get("TreeName"));
                        dname.setText(map.get("Deployer"));
                        ddate.setText(map.get("DeploymentDate"));
                        TreeId = child.getKey();
                        String temp[] = map.get("Sensor").split(",");
                        Log.v("Length", "l" + temp.length);
                        if (temp.length == 1) {
                            sensor1.setText(temp[0]);
                        }
                        if (temp.length == 2) {
                            sensor1.setText(temp[0]);
                            sensor2.setText(temp[1]);
                        }
                        if (temp.length == 3) {
                            sensor1.setText(temp[0]);
                            sensor2.setText(temp[1]);
                            sensor3.setText(temp[2]);

                            Log.v("ID", TreeId);}
                       /* Firebase rootRef2 = new Firebase("https://smartstreetadminapp2.firebaseio.com/DeploymentTree/");
                        rootRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot2) {
                                for (DataSnapshot child2 : dataSnapshot2.getChildren()) {
                                    if (child2.getKey().equalsIgnoreCase(TreeId)) {
                                        Map<String, ArrayList<String>> map2 = child2.getValue(Map.class);
                                        ArrayList<String> arr1 = map2.get("Deployer");
                                        ArrayList<String> arr2 = map2.get("DeploymentDate");
                                        ArrayList<String> arr3 = map2.get("Sensor");
                                        dname.setText(arr1.get(0));
                                        ddate.setText(arr2.get(0));
                                        if(arr3.size()==1){
                                            sensor1.setText(arr3.get(0));
                                        }
                                        if(arr3.size()==2){
                                            sensor1.setText(arr3.get(0));
                                            sensor2.setText(arr3.get(1));
                                        }
                                        if(arr3.size()==3){
                                            sensor1.setText(arr3.get(0));
                                            sensor2.setText(arr3.get(1));
                                            sensor3.setText(arr3.get(2));

                                        }

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });*/
                      /*  Firebase rootRef2 = new Firebase("https://smartstreetadminapp2.firebaseio.com/TreeDeployment/");
                        rootRef2.addValueEventListener(new ValueEventListener() {


                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot2) {
                                for (DataSnapshot child2 : dataSnapshot2.getChildren()) {
                                    if(child2.getKey().equalsIgnoreCase(TreeId))
                                    {
                                        Map<String, String> map2 = child2.getValue(Map.class);
                                        Log.v("Deployer",map2.get("Deployer"));
                                       /* ddate.setText(map2.get("DeploymentDate"));
                                        dtree.setText(map2.get("TreeName"));
                                        dname.setText(map2.get("Deployer"));*/
                             /*       }
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }




                }*/
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
               /* dtree.setFocusable(true);
                dtree.setFocusableInTouchMode(true);
                dtree.setClickable(true);
                dtree.setFocusableInTouchMode(true);
                dtree.setCursorVisible(true);
                ddate.setFocusable(true);*/
                ddate.setFocusableInTouchMode(true);
                ddate.setClickable(true);
                ddate.setFocusableInTouchMode(true);
                ddate.setCursorVisible(true);
                dname.setFocusable(true);
                dname.setFocusableInTouchMode(true);
                dname.setClickable(true);
                dname.setFocusableInTouchMode(true);
                dname.setCursorVisible(true);
                sensor1.setFocusable(true);
                sensor1.setFocusableInTouchMode(true);
                sensor1.setClickable(true);
                sensor1.setFocusableInTouchMode(true);
                sensor1.setCursorVisible(true);
                sensor2.setFocusable(true);
                sensor2.setFocusableInTouchMode(true);
                sensor2.setClickable(true);
                sensor2.setFocusableInTouchMode(true);
                sensor2.setCursorVisible(true);
                sensor3.setFocusable(true);
                sensor3.setFocusableInTouchMode(true);
                sensor3.setClickable(true);
                sensor3.setFocusableInTouchMode(true);
                sensor3.setCursorVisible(true);
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
            public void onClick(View v) {
                Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/Trees");

                Firebase ref = rootRef.child(TreeId);
                ref.child("Deployer").setValue(dname.getText().toString());
                Log.v("Inside save", dname.getText().toString());
                ref.child("DeploymentDate").setValue(ddate.getText().toString());
                ref.child("TreeStatus").setValue(status.getText().toString());

                ref.child("TreeModel").setValue(model.getText().toString());
                ref.child("TreeName").setValue(name.getText().toString());
                Calendar c = Calendar.getInstance();
                StringBuilder builder = new StringBuilder();

                builder.append(sensor1.getText().toString()+",");
                builder.append(sensor2.getText().toString());
                String arr = builder.toString();
                ref.child("Sensor").setValue(arr);
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
               /* Firebase rootRef2 = new Firebase("https://smartstreetadminapp2.firebaseio.com/");
                Firebase ref2 = rootRef2.child("DeploymentTree/"+TreeId);
                ArrayList<String>  Ddate = new ArrayList<String>();
                ArrayList<String>  Sensor = new ArrayList<String>();
                ArrayList<String>  Deployer = new ArrayList<String>();
                Map<String, ArrayList<String>> admin = new HashMap<String,ArrayList<String>>();
Ddate.add("20-Mar-2016");
Sensor.add("LightTree");
                Sensor.add("Sensor1");
                Sensor.add("Sensor2");
                Deployer.add("Larry");




                admin.put("DeploymentDate", Ddate);
                admin.put("Sensor", Sensor);
                admin.put("Deployer", Deployer);

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
                });*/
              /*  Firebase rootRef = new Firebase("https://smartstreetadminapp2.firebaseio.com/Trees");
                rootRef.keepSynced(true);
                Firebase ref = rootRef.child(TreeId);
                ref.child("TreeStatus").setValue("InActive");
                ref.child("TreeModel").setValue("3");
                Calendar c = Calendar.getInstance();


                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());


                ref.child("LastUpdated").setValue(formattedDate);
                Firebase rootRef2 = new Firebase("https://smartstreetadminapp2.firebaseio.com/DeploymentTree/");
                rootRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot2) {
                        for (DataSnapshot child2 : dataSnapshot2.getChildren()) {
                            if (child2.getKey().equalsIgnoreCase(TreeId)) {
                                Map<String, ArrayList<String>> map2 = child2.getValue(Map.class);
                                ArrayList<String> arr1 = map2.get("Deployer");
                                ArrayList<String> arr2 = map2.get("DeploymentDate");
                                ArrayList<String> arr3 = map2.get("Sensor");
                                dname.setText(arr1.get(0));
                                ddate.setText(arr2.get(0));
                                if (arr3.size() == 1) {
                                    sensor1.setText(arr3.get(0));
                                }
                                if (arr3.size() == 2) {
                                    sensor1.setText(arr3.get(0));
                                    sensor2.setText(arr3.get(1));
                                }
                                if (arr3.size() == 3) {
                                    sensor1.setText(arr3.get(0));
                                    sensor2.setText(arr3.get(1));
                                    sensor3.setText(arr3.get(2));

                                }

                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }*/
        });
        return view;
    }


}
