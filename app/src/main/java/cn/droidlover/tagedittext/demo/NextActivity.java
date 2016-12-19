package cn.droidlover.tagedittext.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends AppCompatActivity {

    ListView listView;
    DemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        listView = (ListView) findViewById(R.id.listView);
        if (adapter == null) {
            adapter = new DemoAdapter(this);
        }
        listView.setAdapter(adapter);

        List<String> data = new ArrayList<>();
        data.add("adfasf");
        data.add("adfasf");
        data.add("adfasf");
        data.add("adfasf");
        data.add("adfasf");
        data.add("adfasf");
        data.add("adfasf");
        data.add("adfasf");
        data.add("adfasf");

        adapter.setData(data);

    }

    public static void lauch(Activity activity) {
        Intent intent = new Intent(activity, NextActivity.class);
        activity.startActivity(intent);
    }
}
