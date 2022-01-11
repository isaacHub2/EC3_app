package com.ec3_ihb_app.network;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import com.ec3_ihb_app.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CourseEntry {
    private static final String TAG = CourseEntry.class.getSimpleName();

    public final String title;
    public final Uri dynamicUrl;
    public final String url;

    public CourseEntry(String title, String dynamicUrl, String url){
        this.title = title;
        this.dynamicUrl = Uri.parse(dynamicUrl);
        this.url = url;
    }

    public static List<CourseEntry> initCourseEntryList (Resources resources){
        InputStream inputStream = resources.openRawResource(R.raw.courses);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;

            while ((pointer = reader.read(buffer)) != -1){
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception){
            Log.e(TAG, "HUBO UN ERROR AL MOMENTO DE LEER Y ESCRIBIR EL ARCHIVO");

        } finally {
            try {
                inputStream.close();
            } catch (IOException exception){
                Log.e(TAG, "Hubo un error al cerrar el input stream", exception);
            }
        }

        String jsonCoursesString = writer.toString();

        Gson gson = new Gson();
        Type courseListType = new TypeToken<ArrayList<CourseEntry>>(){

        }.getType();

        return gson.fromJson(jsonCoursesString, courseListType);
    }
}
