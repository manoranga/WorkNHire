package com.example.heyshan.worknhire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class JobList extends AppCompatActivity {
    Button btnGoProfile;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private List<UserModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        for(int i =0 ; i<10;i++){
            UserModel userModel = new UserModel(
                    "1" + i+1,
                    "Kalana" +i+1,
                    "123",
                    "kalana@gmail.com",
                    "Heshan"+i+1,
                    "071445458");
            list.add(userModel);
        }

        adapter = new JobListAdapter(list,this);
        recyclerView.setAdapter(adapter);
//        final String[] employee = {"Achintha", "Kalana", "Theekshana", "Vishmantha"};
//
//        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employee);
//
//        ListView theListView = (ListView) findViewById(R.id.simpleListView);
//
//        theListView.setAdapter(theAdapter);
//
//        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(JobList.this, employee[position].toString(), Toast.LENGTH_SHORT).show();
//            }
//        });


   }


}
