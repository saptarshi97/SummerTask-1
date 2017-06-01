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

public class MainActivity extends AppCompatActivity {
    List<AddData> data = new ArrayList<>();
    String first="",second="";
    int position;
    boolean removeMethodInvoked=false;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAdapter();

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
        if (!removeMethodInvoked) {
            List<AddData> data = fillWithData();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            DataAdapter adapter = new DataAdapter(data);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else {
             //List<AddData> data= removeData();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            DataAdapter adapter = new DataAdapter(data);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            removeMethodInvoked=false;
        }
    }
    public List<AddData> fillWithData(){

        if(!(first.equals("") && second.equals(""))) {
            data.add(new AddData(first, second));
        }
        return data;
    }

    /*public void removeItem(int p){
        //LinearLayout vwParentRow = (LinearLayout)v.getParent();
        removeMethodInvoked=true;
         position=p;
         loadAdapter();
    }
    public List<AddData> removeData(){
        data.remove(position);
        return data;
    }*/
}
