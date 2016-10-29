package com.example.dabin.annotationtest;

import android.app.Activity;
import android.widget.Button;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Dabin on 2016-10-27.
 */

public class ButterSpoon {
    public static void bind(MainActivity activity){
        Class<?> klass = activity.getClass();

        String activityType = activity.getLocalClassName();
        
        

        try {
            for(Field field :klass.getClassLoader().loadClass(klass.getName()).getDeclaredFields())
            {
                if(field.isAnnotationPresent(com.example.dabin.annotationtest.BindView.class)){
                    BindView bindView = field.getAnnotation(BindView.class);
                    int viewID = bindView.viewID();
                    activity.btn  = (Button)activity.findViewById((viewID));

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
