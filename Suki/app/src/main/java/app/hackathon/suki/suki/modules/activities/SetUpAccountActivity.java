package app.hackathon.suki.suki.modules.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

import app.hackathon.suki.suki.R;
import app.hackathon.suki.suki.databinding.ActivitySetupAccountBinding;
import app.hackathon.suki.suki.helpers.ConstantsHelper;
import app.hackathon.suki.suki.modules.ImagePicker;

public class SetUpAccountActivity extends AppCompatActivity {
    private ActivitySetupAccountBinding mBinding;
    private static final int PICK_IMAGE_ID = 666;
    static final Integer WRITE_EXST = 0x3;
    static final Integer READ_EXST = 0x4;
    static final Integer CAMERA = 0x5;

    boolean isPersonal=false;
    Uri tempUri;


    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_setup_account);

        View toolbar = findViewById(R.id.layout_header);
        TextView textView = toolbar.findViewById(R.id.text_headerTitle);
        textView.setText(R.string.setupAcc);

        mBinding.buttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPersonal= true;
                selectImage();
            }
        });
        mBinding.buttonUploadStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isPersonal= false;
                selectImage();
            }
        });

        mBinding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.linearPersonal.setVisibility(View.GONE);
                mBinding.linearBusiness.setVisibility(View.VISIBLE);
                mBinding.progressTwo.setBackground(getResources().getDrawable(R.drawable.bg_circular_filled));
                mBinding.progressTwo.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });

        mBinding.buttonNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetUpAccountActivity.this, SetUpProductsActivity.class );
                startActivity(intent);
                finish();
            }
        });

        mBinding.progressOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.linearPersonal.setVisibility(View.VISIBLE);
                mBinding.linearBusiness.setVisibility(View.GONE);
                mBinding.progressTwo.setBackground(getResources().getDrawable(R.drawable.bg_circular_clear));
                mBinding.progressTwo.setTextColor(Color.parseColor("#464646"));
            }
        });

        permissions();


    }

    private void selectImage(){
        Intent chooseImageIntent = ImagePicker.getPickImageIntent(getApplicationContext());
        startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try{
            switch(requestCode) {
                case PICK_IMAGE_ID:
                    Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                    Log.i("TAG","Bitmap Result"+bitmap);
                    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream1);
                    byte[] byteArray = byteArrayOutputStream1.toByteArray();
                    String imageEncodedCamera;
                    imageEncodedCamera = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    if (isPersonal){
                        mBinding.imgIdPhoto.setVisibility(View.VISIBLE);
                        mBinding.imgIdPhoto.setImageBitmap(bitmap);
                        tempUri = getImageUri(getApplicationContext(),bitmap);
                    }else{
                        mBinding.imgStorePhoto.setVisibility(View.VISIBLE);
                        mBinding.imgStorePhoto.setImageBitmap(bitmap);
                    }
                    // TODO use bitmap
                    break;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
                    break;
            }

        }catch (Exception e){
            Log.i("TAG","Bitmap Error"+e.toString());

        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "AdLotteryImage", null);
        return Uri.parse(path);
    }

    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            Log.e("realPath", "getRealPathFromURI Exception : " + e.toString());
            return "";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void permissions(){
        askForPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXST);
        askForPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
        askForPermission(android.Manifest.permission.CAMERA,CAMERA);
    }
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(SetUpAccountActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(SetUpAccountActivity.this, permission)) {
                ActivityCompat.requestPermissions(SetUpAccountActivity.this, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(SetUpAccountActivity.this, new String[]{permission}, requestCode);
            }
        } else {
//            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            Log.d("Already Granted: ",permission);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try{
            if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                permissions();
            }else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e("UpdateAccountActivity",e.toString());
        }
    }
    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    public void uploadFile(){
        String path = getRealPathFromURI(getApplicationContext(),tempUri);
        File file = new File(path);

        if (tempUri != null) {
            showLoadingPrompt(true);

            //getting the storage reference
            StorageReference sRef = storageReference.child(ConstantsHelper.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(tempUri));

            //adding the file to reference
            sRef.putFile(tempUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();

                            //creating the upload object to store uploaded image details
                            Upload upload = new Upload(editTextName.getText().toString().trim(), taskSnapshot.getDownloadUrl().toString());

                            //adding an upload to firebase database
                            String uploadId = mDatabase.push().getKey();
                            mDatabase.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            //display an error if no file is selected
        }
    }
    protected void hideKeyboard() {
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                mBinding.hehe.getWindowToken(), 0);
    }
    private void showLoadingPrompt(boolean isShown) {
        if (isShown) {
            mBinding.buttonSign.setEnabled(false);
            mBinding.buttonCreate.setEnabled(false);
            mBinding.progressBarLoading.bringToFront();
            mBinding.progressBarLoading.setVisibility(View.VISIBLE);
        } else {
            mBinding.buttonSign.setEnabled(true);
            mBinding.buttonCreate.setEnabled(true);
            mBinding.progressBarLoading.setVisibility(View.GONE);
        }
    }
}
