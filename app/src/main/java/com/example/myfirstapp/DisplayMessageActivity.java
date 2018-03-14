package com.example.myfirstapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DisplayMessageActivity extends AppCompatActivity
{
    private int brunoToggle = 0;
    private int ubuToggle = 0;
    private int fenwayToggle = 0;
    private ImageView brunoImage;
    private ImageView ubuImage;
    private ImageView fenwayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent  = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String phoneMessage = intent.getStringExtra(MainActivity.EXTRA_PHONEMESSAGE);

        // Capture the layout's TextView and set the String as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message + " number is " + phoneMessage);

        brunoImage = findViewById(R.id.Bruno);
        //Glide.with(this).load(R.drawable.bruno).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.5f).into(brunoImage);
        Glide.with(this).load(R.drawable.bruno).thumbnail(0.5f).into(brunoImage);
        ubuImage = findViewById(R.id.Ubu);
        //Glide.with(this).load(R.drawable.ubu).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.5f).into(ubuImage);
        Glide.with(this).load(R.drawable.ubu).thumbnail(0.5f).into(ubuImage);
        fenwayImage = findViewById(R.id.Fenway);
        //Glide.with(this).load(R.drawable.fenway).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.5f).into(fenwayImage);
        Glide.with(this).load(R.drawable.fenway).thumbnail(0.5f).into(fenwayImage);

        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = true;
        //BitmapFactory.decodeResource(getResources(), R.drawable.bruno, options);
        //brunoImage.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.bruno, 1024, 720));

        //BitmapFactory.decodeResource(getResources(), R.drawable.ubu, options);
        //ubuImage.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.ubu, 1024, 720));
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth)
        {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth)
            {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight)
    {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /** Called when the user taps the Ubu button */
    public void showUbu(View buttonView)
    {
        Intent intent  = new Intent(this, FullScreenDog.class);
        intent.putExtra("dogName", "Ubu");
        startActivity(intent);
        if (ubuToggle == 0 || brunoToggle == 1 || fenwayToggle == 1)
        {
            ubuImage.setVisibility(View.VISIBLE);
            brunoImage.setVisibility(View.GONE);
            fenwayImage.setVisibility(View.GONE);
            ubuToggle = 1;
            brunoToggle = 0;
            fenwayToggle = 0;
        }
        else
        {
            ubuImage.setVisibility(View.GONE);
            ubuToggle = 0;
        }
    }

    /** Called when the user taps the Bruno button */
    public void showBruno(View buttonView)
    {
        Intent intent  = new Intent(this, FullScreenDog.class);
        intent.putExtra("dogName", "Bruno");
        startActivity(intent);
        if (brunoToggle == 0 || ubuToggle == 1 || fenwayToggle == 1)
        {
            brunoImage.setVisibility(View.VISIBLE);
            fenwayImage.setVisibility(View.GONE);
            ubuImage.setVisibility(View.GONE);
            brunoToggle = 1;
            ubuToggle = 0;
            fenwayToggle = 0;
        }
        else
        {
            brunoImage.setVisibility(View.GONE);
            brunoToggle = 0;
        }
    }

    public void showFenway(View buttonView)
    {
        Intent intent  = new Intent(this, FullScreenDog.class);
        intent.putExtra("dogName", "Fenway");
        startActivity(intent);
        if (fenwayToggle == 0 || brunoToggle == 1 || ubuToggle == 1)
        {
            fenwayImage.setVisibility(View.VISIBLE);
            brunoImage.setVisibility(View.GONE);
            ubuImage.setVisibility(View.GONE);
            fenwayToggle = 1;
            brunoToggle = 0;
            ubuToggle = 0;
        }
        else
        {
            fenwayImage.setVisibility(View.GONE);
            fenwayToggle = 0;
        }

    }

    public void fullScreenButton(View buttonView)
    {
        Intent intent  = new Intent(this, FullScreenDog.class);
        startActivity(intent);
    }

}
