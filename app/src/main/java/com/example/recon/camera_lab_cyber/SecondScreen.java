package com.example.recon.camera_lab_cyber;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondScreen extends Activity {

    static final Integer CAMERA = 0x5;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView capture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.second_layout);

            //Show the name inserted
        set_textView();
//        Intent activityCalled = getIntent();
//        String prev_Activity = activityCalled.getExtras().getString("Name");
//        TextView callingMessage = (TextView)findViewById(R.id.HI);
//        callingMessage.append(" " + prev_Activity);
        //

//        Button camera_btn = (Button)findViewById(R.id.camera);
        capture = (ImageView)findViewById(R.id.image_view_camera);

//        camera_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                dispatchTakePictureIntent();
//            }
//        });

    }


    public void ask(View v){
        switch (v.getId()){
            case R.id.camera:
                askForPermission(Manifest.permission.CAMERA,CAMERA);
//                onRequestPermissionsResult();
//                dispatchTakePictureIntent();
//                int arr[] = {0};
//                String a[] = {Manifest.permission.CAMERA};
//                onRequestPermissionsResult(5,a,arr);
                break;

            default:
                break;
        }
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(SecondScreen.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(SecondScreen.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(SecondScreen.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(SecondScreen.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            dispatchTakePictureIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            switch (requestCode) {
                //Camera
                case 5:
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                    break;
            }

            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }

    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Bundle extras = data.getExtras();

        try {
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            capture.setImageBitmap(imageBitmap);
        }
        catch (RuntimeException e){
            System.out.println(e);

        }
    }

    private void set_textView(){
        Intent activityCalled = getIntent();
        String prev_Activity = activityCalled.getExtras().getString("Name");
        TextView callingMessage = (TextView)findViewById(R.id.HI);
        callingMessage.append(" " + prev_Activity);

    }
}

