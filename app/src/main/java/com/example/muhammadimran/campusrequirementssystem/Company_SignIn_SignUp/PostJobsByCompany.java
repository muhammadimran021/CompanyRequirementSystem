package com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.muhammadimran.campusrequirementssystem.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class PostJobsByCompany extends AppCompatActivity {
    ImageButton postImage;
    EditText description;
    Button Post;
    private Uri mImageUri = null;
    private static final int Gallery_Request = 1;

    private ProgressDialog mprogress;

    private StorageReference mStoarge;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_jobs_by_company);
        postImage = (ImageButton) findViewById(R.id.companyPost);
        description = (EditText) findViewById(R.id.PostDescription);
        Post = (Button) findViewById(R.id.PostButton);

        mStoarge = FirebaseStorage.getInstance().getReference();
        mData = FirebaseDatabase.getInstance().getReference();

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
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                PostModel post = new PostModel(desc_value, downloadUrl.toString());
                mData.child("company-post").push().setValue(post);
                finish();
                mprogress.dismiss();


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
