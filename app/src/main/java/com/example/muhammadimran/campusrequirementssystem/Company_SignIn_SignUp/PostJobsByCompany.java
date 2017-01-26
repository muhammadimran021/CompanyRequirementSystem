package com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.muhammadimran.campusrequirementssystem.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class PostJobsByCompany extends AppCompatActivity {
    private ImageButton postImage;
    private EditText description;
    private Button Post;
    private Uri mImageUri = null;
    private static final int Gallery_Request = 1;
    private ProgressDialog mprogress;
    private StorageReference mStoarge;
    private DatabaseReference mData;
    private Uri FirebaseUri;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_jobs_by_company);
        postImage = (ImageButton) findViewById(R.id.companyPost);
        description = (EditText) findViewById(R.id.PostDescription);
        Post = (Button) findViewById(R.id.PostButton);

        mStoarge = FirebaseStorage.getInstance().getReference();
        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        postImage.setOnClickListener(view -> {
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, Gallery_Request);

        });
        Post.setOnClickListener(view -> {
            startPosting();
        });


    }

    private void startPosting() {
        mprogress = new ProgressDialog(this);
        mprogress.setMessage("Posting to blog..");
        mprogress.show();

        final String desc_value = description.getText().toString().trim();

        if (!TextUtils.isEmpty(desc_value) && !TextUtils.isEmpty(desc_value) && mImageUri != null) {

            StorageReference filepath = mStoarge.child("Images").child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(taskSnapshot -> {

                FirebaseUri = taskSnapshot.getDownloadUrl();

                String UId = mAuth.getCurrentUser().getUid().toString();
                mData.child("company-info").child(UId).child("fname").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("TAG", dataSnapshot.getValue().toString());
                        String currentusername = dataSnapshot.getValue().toString();
                        PostModel post = new PostModel(currentusername,desc_value, FirebaseUri.toString());
                        mData.child("company-post").push().setValue(post);
                        finish();
                        mprogress.dismiss();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Request && resultCode == RESULT_OK) {
            Uri ImageUri = data.getData();
            CropImage.activity(ImageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mImageUri = result.getUri();

                postImage.setImageURI(mImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
}
