package com.example.khuang.permissionform;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by k.huang on 2017/4/14.
 */

public class EasyPermissionsActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    public static final String[] perms = {Manifest.permission.CAMERA};
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EasyPermissions.hasPermissions(EasyPermissionsActivity.this,perms)){
                    Toast.makeText(EasyPermissionsActivity.this,"拥有该权限，去拍照吧",Toast.LENGTH_SHORT).show();

                }else {
                    EasyPermissions.requestPermissions(EasyPermissionsActivity.this,
                            "拍照需要摄像头权限",0,perms);
                }

            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.e("tag","requestCode:" + requestCode +"," + perms.size());
        if (requestCode == 0){
            for (int i = 0 ;i < perms.size();i++){
                Toast.makeText(EasyPermissionsActivity.this,perms.get(i)+"获得权限成功",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.e("tag","requestCode:" + requestCode +"," + perms.size());
        if (requestCode == 0    ){
            for (int i = 0 ;i < perms.size();i++){
                Toast.makeText(EasyPermissionsActivity.this,perms.get(i)+"获得权限失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,EasyPermissionsActivity.this);
    }


    @AfterPermissionGranted(0)
    private void showCamera(){
        Intent intent = new Intent(); //调用照相机
        intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
        startActivity(intent);
    }
}
