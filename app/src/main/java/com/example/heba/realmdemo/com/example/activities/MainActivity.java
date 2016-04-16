package com.example.heba.realmdemo.com.example.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heba.realmdemo.com.example.pojo.Person;
import com.example.heba.realmdemo.R;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnSave , btnGet;
    EditText txt , txtAge , txtId;
    Realm myRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindComponents();

        btnGet.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    private void bindComponents() {
        btnGet = (Button)findViewById(R.id.btnGet);
        btnSave = (Button)findViewById(R.id.btnSave);
        txt = (EditText)findViewById(R.id.txt);
//        txtAge = (EditText)findViewById(R.id.txtAge);
//        txtId = (EditText)findViewById(R.id.txtId);
        myRealm = Realm.getInstance(getApplicationContext());

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btnSave){
            myRealm.beginTransaction();

            // Create an object
            Person person = myRealm.createObject(Person.class);

            // Set its fields
            person.setName(txt.getText().toString());
            person.setAge(12);
            person.setId(3);

            myRealm.commitTransaction();
        }else if(id == R.id.btnGet){
            RealmResults<Person> results2 =
                    myRealm.where(Person.class)
                            .greaterThan("age", 10)
                            .findAll();
            Toast.makeText(view.getContext(),"Person is : " + results2.get(0) , Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
