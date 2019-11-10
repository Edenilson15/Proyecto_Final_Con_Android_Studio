package com.example.menu_buscar;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class busqueda extends ContentProvider {

        List<String> cities;

        @Override
        public boolean onCreate() {
            return false;
        }

        @Override
        public Cursor query(Uri uri, String[] projection, String selection,
                            String[] selectionArgs, String sortOrder) {
            if (cities == null || cities.isEmpty()){
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://dl.dropboxusercontent.com/u/6802536/cidades.json")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String jsonString = response.toString().string();
                    JSONArray jsonArray = new JSONArray(jsonString);

                    cities = new ArrayList<>();

                    int lenght = jsonArray.length();
                    for (int i = 0; i < lenght; i++) {
                        String city = jsonArray.getString(i);
                        cities.add(city);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            MatrixCursor cursor = new MatrixCursor(
                    new String[] {
                            BaseColumns._ID,
                            SearchManager.SUGGEST_COLUMN_TEXT_1,
                            SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID
                    }
            );
            if (cities != null) {
                String query = uri.getLastPathSegment().toUpperCase();
                int limit = Integer.parseInt(uri.getQueryParameter(SearchManager.SUGGEST_PARAMETER_LIMIT));

                int lenght = cities.size();
                for (int i = 0; i < lenght && cursor.getCount() < limit; i++) {
                    String city = cities.get(i);
                    if (city.toUpperCase().contains(query)){
                        cursor.addRow(new Object[]{ i, city, i });
                    }
                }
            }
            return cursor;
        }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private class OkHttpClient {
        public Executor newCall(Request request) {
            return null;
        }
    }

    private class Response {

        public void body() {
        }
    }

}


    //Other methods are empty...


