package saptarshi.com.summertask_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    List<AddData> data = new ArrayList<>();
    String first="",second="";
    int position;
    boolean removeMethodInvoked=false;
    TextView test;
    private Realm mDatabase;
    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase=Realm.getDefaultInstance();
        loadAdapter();  //TO-DO: Database filling, ie Saving, Retrieving and updating.

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public void addToList(MenuItem menuItem){


        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder.setView(promptsView);

        final EditText titleInput = (EditText) promptsView
                .findViewById(R.id.edit_title);

        final EditText titleDescription = (EditText) promptsView
                .findViewById(R.id.edit_description);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                  first = titleInput.getEditableText().toString();
                                  second = titleDescription.getEditableText().toString();
                                loadAdapter();

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();


    }
    private void loadAdapter() {
            List<AddData> data = fillWithData();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            adapter = new DataAdapter(data,mDatabase);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public List<AddData> fillWithData(){
        mDatabase.beginTransaction();
        mDatabase.where(AddData.class).findAll();
        if(!(first.equals("") && second.equals(""))) {
            data.add(new AddData(first, second));
        }
        mDatabase.copyToRealm(data);
        mDatabase.commitTransaction();
        if(mDatabase!=null) {
            RealmResults<AddData> results = mDatabase.where(AddData.class).findAll();
            if (!results.isEmpty()){
                data.clear();
                data.addAll(results);
            }
        }
        return data;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mDatabase.close();
        mDatabase=null;
    }

}
