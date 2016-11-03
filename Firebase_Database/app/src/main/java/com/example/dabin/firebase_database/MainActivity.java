package com.example.dabin.firebase_database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference rootRef;
    DatabaseReference userRef;


    EditText et_name;
    EditText et_email;
    EditText et_id;
    Button btn_add;

    TextView tv_name;
    TextView tv_id;
    TextView tv_email;
    ListView listView;
    ArrayList<Map<String,User>> datas = new ArrayList<>();
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///db connection
        database = FirebaseDatabase.getInstance();
        // reference point (참조 포인트) 없을 시 생성
        rootRef = database.getReference();
        et_email = (EditText) findViewById(R.id.et_email);
        et_name = (EditText) findViewById(R.id.et_name);
        et_id = (EditText) findViewById(R.id.et_id);
        btn_add = (Button) findViewById(R.id.btn_user);

        listView = (ListView) findViewById(R.id.listview);
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = et_id.getText().toString().trim();
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();

                if (!"".equals(uid) && !"".equals(name) && !"".equals(email)) {
                    writeNewUser(uid, name, email);
                    et_email.setText("");
                    et_id.setText("");
                    et_name.setText("");

                } else {
                    Toast.makeText(MainActivity.this, "아이디, 이름, 이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        userRef = database.getReference("users");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot users) {
                datas = new ArrayList<>();
                Toast.makeText(MainActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                for (DataSnapshot userData : users.getChildren()) {
                    Map<String,User> data = new HashMap<String, User>();

                    String userId = userData.getKey();
                    User user = userData.getValue(User.class);
                    data.put(userId, user);

                    datas.add(data);


                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        rootRef.child("users").child(userId).setValue(user);
        /*
            root - user -michael - name  : 누구
                                 - email : 어디
                        -jackson - name : 누구
                                 - email : 어디
         */


    }


    class CustomAdapter extends BaseAdapter {
        LayoutInflater inflater;


        CustomAdapter() {
            inflater = getLayoutInflater();

        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        TextView tv_name;
        TextView tv_id;
        TextView tv_email;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_list, null);
            }
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_email = (TextView) convertView.findViewById(R.id.tv_email);
            tv_id = (TextView) convertView.findViewById(R.id.tv_id);

            Map<String,User> data = datas.get(position);
            String uid = data.keySet().iterator().next();
            User user = data.get(uid);

            tv_name.setText(user.username);
            tv_email.setText(user.email);
            tv_id.setText(uid);


            return convertView;
        }
    }
}
