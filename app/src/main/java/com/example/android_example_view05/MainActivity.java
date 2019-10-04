package com.example.android_example_view05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//User contentProvider CRUD Data
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAddButton, mSelectButton, mUpdateButton, mDeleteButton;
    private String bookId = null;
    private static final String TAG = "SpiderLine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mAddButton = findViewById(R.id.addBtn);
        mSelectButton = findViewById(R.id.selectBtn);
        mUpdateButton = findViewById(R.id.updateData);
        mDeleteButton = findViewById(R.id.deleteBtn);
        mAddButton.setOnClickListener(this);
        mSelectButton.setOnClickListener(this);
        mUpdateButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                //user ContentProvider insert Data

                insertData();

                break;

            case R.id.selectBtn:
                //user ContentProvider select Data

                selectData();

                break;

            case R.id.updateData:
                //user ContentProvider update Data

                UpdateData();

                break;
            case R.id.deleteBtn:
                //user ContentProvider delete Data

                DeleteData();

                break;
            default:
                break;
        }
    }

    private void insertData() {

        Uri mUri = Uri.parse("content://com.example.databasetest.provider/book");
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("name", "SpiderLine");
        mContentValues.put("author", "Spider");
        mContentValues.put("pages", 1011);
        Uri newUri = getContentResolver().insert(mUri, mContentValues);
        bookId = newUri.getPathSegments().get(1);
    }


    private void selectData() {

        Uri mUri = Uri.parse("content://com.example.databasetest.provider/book");
        Cursor cursor = getContentResolver().query(mUri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String mName = cursor.getString(cursor.getColumnIndex("name"));
                String mAuthor = cursor.getString(cursor.getColumnIndex("author"));
                int mPages = cursor.getInt(cursor.getColumnIndex("pages"));
                Log.e(TAG, "Name:" + mName + "\n" + "Author:" + mAuthor + "\n" + "Pages:" + mPages + "\n");
            }
            cursor.close();
        }
    }


    private void UpdateData() {

        Uri mUri = Uri.parse("content://com.example.databasetest.provider/book/" + bookId);
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("name", "Spider-007");
        getContentResolver().update(mUri, mContentValues, null, null);
    }


    private void DeleteData() {

        Uri mUri = Uri.parse("content://com.example.databasetest.provider/book/" + bookId);
        getContentResolver().delete(mUri, null, null);

    }
}
