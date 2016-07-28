package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.wangjie.shadowviewhelper.ShadowProperty;
import com.wangjie.shadowviewhelper.ShadowViewHelper;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cn.com.hz_project.view.fragment.HomeFragment;
import cn.com.hz_project.view.fragment.PersonFragment;
import cn.com.hz_project.view.fragment.SheZhiFragment;
import cn.com.hz_project.view.fragment.SortFragment;
import cn.com.hz_project.view.fragment.UpDateFragment;
import cn.com.projectdemos.R;

public class ViewPagerActivity extends FragmentActivity {
    private static final int FILE_SELECT_CODE = 0X111;
    public final static int num = 4;
    public static FragmentManager fragmentManager;
    public static FragmentTransaction transaction;
    private RadioGroup radioGroup;

    private Fragment homeFragment = new HomeFragment();
    private Fragment sortFragment = new SortFragment();
    private Fragment personFragment = new PersonFragment();
    private Fragment shezhiFragment = new SheZhiFragment();

    private RadioButton qq;
    public static String lujing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_view_pager);
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        ((RadioButton) radioGroup.findViewById(R.id.radio0)).setChecked(true);

        fragmentManager.beginTransaction().add(R.id.content, homeFragment).commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        transaction = fragmentManager.beginTransaction();
                        if (!homeFragment.isAdded()) {
                            transaction.add(R.id.content, homeFragment);
                        }
                        if (personFragment.isAdded()) {
                            transaction.hide(personFragment);
                        }
                        if (sortFragment.isAdded()) {
                            transaction.hide(sortFragment);
                        }
                        if (shezhiFragment.isAdded()){
                            transaction.hide(shezhiFragment);
                        }

                        transaction.show(homeFragment);
                        transaction.commit();

                        break;

                    case R.id.radio1:
                        transaction = fragmentManager.beginTransaction();
                        if (!sortFragment.isAdded()) {
                            transaction.add(R.id.content, sortFragment);
                        }
                        Logger.e("homeFragment"+homeFragment.isAdded());
                        if (personFragment.isAdded()) {
                            transaction.hide(personFragment);
                        }
                        if (homeFragment.isAdded()) {
                            transaction.hide(homeFragment);
                        }
                        if (shezhiFragment.isAdded()) {
                            transaction.hide(shezhiFragment);
                        }
                        transaction.show(sortFragment);
                        transaction.commit();

                        break;
                    case R.id.radio2:
                        transaction = fragmentManager.beginTransaction();
                        if (!personFragment.isAdded()) {
                            transaction.add(R.id.content, personFragment);
                        }
                        if (sortFragment.isAdded()) {
                            transaction.hide(sortFragment);
                        }
                        if (homeFragment.isAdded()) {
                            transaction.hide(homeFragment);
                        }
                        if (shezhiFragment.isAdded()) {
                            transaction.hide(shezhiFragment);
                        }

                        transaction.show(personFragment);
                        transaction.commit();

                        break;
                    case R.id.radio3:
                        transaction = fragmentManager.beginTransaction();
                        if (!shezhiFragment.isAdded()) {
                            transaction.add(R.id.content, shezhiFragment);

                        }
                        if (sortFragment.isAdded()) {
                            transaction.hide(sortFragment);
                        }
                        if (homeFragment.isAdded()){
                            transaction.hide(homeFragment);
                        }
                        if (personFragment.isAdded()) {
                            transaction.hide(personFragment);
                        }

                        transaction.show(shezhiFragment);
                        transaction.commit();

                        break;
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = null;

        if (resultCode == Activity.RESULT_OK) {
            uri = data.getData();

        }
        UpDateFragment.lujing.setText(getImageAbsolutePath(ViewPagerActivity.this, uri));
        lujing = getImageAbsolutePath(ViewPagerActivity.this, uri);

    }

    public static String getImageAbsolutePath(Activity context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


}
