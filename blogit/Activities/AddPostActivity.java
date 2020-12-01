package com.example.blogit.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.blogit.Model.Blog;
import com.example.blogit.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class AddPostActivity extends AppCompatActivity {
    public ImageButton Image;
    public EditText PostTitle;
    public EditText PostDesc;
    public Button createButton;
    public StorageReference Storage;
    public DatabaseReference PostDatabase;
    public FirebaseUser User;
    public FirebaseAuth Auth;
    public ProgressDialog progressDialog;
    private static final int GALLERY_CODE=1;
    public Uri ImageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        progressDialog = new ProgressDialog(this);

        Auth= FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();
        Storage = FirebaseStorage.getInstance().getReference();

        PostDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");

        Image = findViewById(R.id.imageButton);
        PostTitle = findViewById(R.id.postTitle);
        PostDesc = findViewById(R.id.postDesc);
        createButton = findViewById(R.id.createButton);

        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();

            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK){
            assert data != null;
            ImageUri = data.getData();
            Image.setImageURI(ImageUri);

        }
    }

    private void startPosting() {
        progressDialog.setMessage("Creating");
        progressDialog.show();

        final String titleVal = PostTitle.getText().toString().trim();
        final String descVal = PostDesc.getText().toString().trim();


//        if (!TextUtils.isEmpty(titleVal) && !TextUtils.isEmpty(descVal) && ImageUri!=null){
//            //start uploading...
//            final StorageReference filepath = Storage.child("Blog_Images").
//                    child(ImageUri.getLastPathSegment());
//            filepath.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                    //String downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
//                    //Uri downloadUrl = taskSnapshot.getUploadSessionUri();
//                     //Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
//
//
//
//                    if (taskSnapshot.getMetadata() != null) {
//                        if (taskSnapshot.getMetadata().getReference() != null) {
//                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
//                            downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//                                    String imageUrl = uri.toString();
//                                    //createNewPost(imageUrl);
//                                }
//                            });
//                        }
//                    }



//                    DatabaseReference newPost = PostDatabase.push();
//
//                    Map<String, String> dataToSave = new HashMap<>();
//                    dataToSave.put("title", titleVal);
//                    dataToSave.put("desc", descVal);
//                    String image = dataToSave.put("image", downloadUrl.toString());
//                    dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
//                    dataToSave.put("userid", User.getUid());
//
//                    newPost.setValue(dataToSave);


                    progressDialog.dismiss();
                    Toast.makeText(AddPostActivity.this, "Created!", Toast.LENGTH_LONG).show();


               }
//            });
//
//
//
//                }
//
//            }
}
