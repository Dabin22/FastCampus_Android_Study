package com.example.dabin.firebase_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class BbsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference postRef;
    DatabaseReference rootRef;

    EditText et_bbs_title;
    EditText et_bbs_name;
    EditText et_bbs_content;
    Button btn_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs);

        database = FirebaseDatabase.getInstance();
        postRef = database.getReference("posts");
        rootRef = database.getReference();

        et_bbs_title = (EditText)findViewById(R.id.et_bbs_title);
        et_bbs_name = (EditText)findViewById(R.id.et_bbs_name);
        et_bbs_content = (EditText)findViewById(R.id.et_bbs_content);
        btn_post = (Button)findViewById(R.id.btn_post);

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_bbs_title.getText().toString();
                String name = et_bbs_name.getText().toString();
                String content = et_bbs_content.getText().toString();

                writeNewPost("posted memo",name,title,content);

            }
        });
    }
    private void writeNewPost(String userId, String username, String title, String body) {
        //postRef 가 가르키는 posts에 유일한 키값을 생ㅅ어해서 가져온다.
        String key = postRef.child("posts").push().getKey();
        //글을 쓴 내용을 Post 객체로 만들어주고
        Post post = new Post(userId, username, title, body);
        //해당 객체를 맵으로 변환
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        rootRef.updateChildren(childUpdates);
    }
}
