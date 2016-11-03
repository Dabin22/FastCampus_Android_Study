package com.example.dabin.firebase_auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "FirebaseAuth";
    EditText et_email;
    EditText et_password;
    Button btn_signIn;
    Button btn_signUp;
    TextView tv_log;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ListView listView;
    ArrayList<Map<String, User>> datas = new ArrayList<>();
    CustomAdapter customAdapter;

    FirebaseDatabase database;
    DatabaseReference rootRef;
    DatabaseReference userRef;
    ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot users) {
                datas = new ArrayList<>();
                Toast.makeText(MainActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                for (DataSnapshot userData : users.getChildren()) {
                    Map<String, User> data = new HashMap<String, User>();

                    String userId = userData.getKey();
                    User user = userData.getValue(User.class);
                    data.put(userId, user);

                    datas.add(data);


                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "오류입니다.", Toast.LENGTH_SHORT).show();
            }
        };
        database = FirebaseDatabase.getInstance();
        // reference point (참조 포인트) 없을 시 생성
        rootRef = database.getReference();
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        btn_signUp = (Button) findViewById(R.id.btn_signUp);
        tv_log = (TextView) findViewById(R.id.textView);

        //1. 인증객체 가져오기
        mAuth = FirebaseAuth.getInstance();
        //2. 리스너 설정
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    userRef.addValueEventListener(valueEventListener);
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    userRef.removeEventListener(valueEventListener);
                    datas = new ArrayList<>();
                    customAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        //신규 계정 생성
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (!"".equals(email) && !"".equals(password)) {
                    addUser(email, password);
                } else {
                    Toast.makeText(MainActivity.this, "Email과 Pssword를 입력하셔야 합니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 5. 로그인
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_signIn.getText().toString().equals("SIGN IN")) {
                    String email = et_email.getText().toString().trim();
                    String password = et_password.getText().toString().trim();
                    if (!"".equals(email) && !"".equals(password)) {
                        signIn(email, password);

                    } else {
                        Toast.makeText(MainActivity.this, "Email과 Pssword를 입력하셔야 합니다", Toast.LENGTH_SHORT).show();
                    }

                }else if(btn_signIn.getText().toString().equals("SIGN OUT")){
                    mAuth.signOut();
                    et_email.setText("");
                    et_email.setHint("EMAIL ADDRESS");
                    et_password.setText("");
                    et_password.setHint("PASSWORD");
                    btn_signIn.setText("SIGN IN");
                    Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        userRef = database.getReference("users");



        listView = (ListView) findViewById(R.id.listview);

        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        userRef.addValueEventListener(valueEventListener);
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "사용자 로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            btn_signIn.setText("SIGN OUT");

                        } else {
                            Toast.makeText(MainActivity.this, "사용자 로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            et_email.setText("");
                            et_email.setHint("EMAIL ADDRESS");
                            et_password.setText("");
                            et_password.setHint("PASSWORD");
                        }
                    }
                });
    }

    public void addUser(final String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "사용자 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    mAuth.sendPasswordResetEmail(email);
                } else {
                    Toast.makeText(MainActivity.this, "사용자 등록에 완료하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //3. 리스너 해제 및 재등록 처리
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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

            Map<String, User> data = datas.get(position);
            String uid = data.keySet().iterator().next();
            User user = data.get(uid);

            tv_name.setText(user.username);
            tv_email.setText(user.email);
            tv_id.setText(uid);


            return convertView;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuth.signOut();
    }
}
