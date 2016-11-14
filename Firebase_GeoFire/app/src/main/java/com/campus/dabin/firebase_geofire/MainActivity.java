package com.campus.dabin.firebase_geofire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("path/to/geofire");
        GeoFire geoFire = new GeoFire(ref);
        String key = ref.push().getKey();
        geoFire.setLocation(key, new GeoLocation(38.7853889, -123.4056973));
        String key2 = ref.push().getKey();
        geoFire.setLocation(key2, new GeoLocation(38.7853889, -123.4066973));
        String key3 = ref.push().getKey();
        geoFire.setLocation(key3, new GeoLocation(38.7853889, -123.4076973));
        String key4 = ref.push().getKey();
        geoFire.setLocation(key4, new GeoLocation(37.7953889, -122.4056973), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if (error != null) {
                    System.err.println("There was an error saving the location to GeoFire: " + error);
                } else {
                    System.out.println("key =" + key);

                    System.out.println("Location saved on server successfully!");
                }
            }
        });

        //특정 범위내의 firebase 키값을 가져오기
        GeoLocation center = new GeoLocation(37.7832, -122.4056);
        float range = 2.6f;
        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(37.7832, -122.4056), range);

        //좌표를 담을 그릇을 세팅
        final Set<String> nearby = new HashSet<>();

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                System.out.println(String.format("Key %s entered the search area at [%f,%f]", key, location.latitude, location.longitude));
                nearby.add(key);
            }

            @Override
            public void onKeyExited(String key) {
                System.out.println(String.format("Key %s is no longer in the search area", key));
                nearby.remove(key);
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                System.out.println(String.format("Key %s moved within the search area to [%f,%f]", key, location.latitude, location.longitude));
            }

            @Override
            public void onGeoQueryReady() {
                System.out.println("Room count =" + nearby.size());
                for(String key: nearby){
                    System.out.println("key = " +key);
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                System.err.println("There was an error with this query: " + error);
            }
        });
    }
}
