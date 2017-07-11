package com.example.sid.contextmenu93;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListviewAdapter listviewAdapter;

    //list of names
    private String vname[] = {
            "Pushpa",
            "Latha",
            "Arjun",
            "Kiran",
            "Arnav"
    };
//list of number
    private String vnumber[] = {
            "9988778877",
            "9988778874",
            "7988778877",
            "9988778844",
            "9968778877",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lvCustomList);
        listviewAdapter = new ListviewAdapter(this, vname, vnumber);
        listView.setAdapter(listviewAdapter);

        //listView.setOnItemClickListener(MainActivity);
        registerForContextMenu(listView);
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "Title => " + vname[position] + "=> n Description" + vnumber[position], Toast.LENGTH_SHORT).show();
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0,view.getId(),0,"Call");
        menu.add(0,view.getId(),1,"SMS");
    }

    public boolean onContextItemSelected(MenuItem item) {
//item selected in context menu
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String number;

        number = vnumber[info.position];
//intent for call
        if (item.getTitle() == "Call") {

            Intent call = new Intent(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:" + number));
            startActivity(call);

            //intent for sms
        } else if (item.getTitle() == "SMS") {

            Intent sms = new Intent(Intent.ACTION_VIEW);
            sms.setType("vnd.android-dir/mms-sms");
            sms.putExtra("address", number);
            startActivity(sms);
        }
        else
        {
            return false;
        }
        return true;

    }
}
