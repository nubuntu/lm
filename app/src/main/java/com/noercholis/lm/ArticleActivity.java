package com.noercholis.lm;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by nubuntu on 08/06/2015.
 */
public class ArticleActivity extends AppCompatActivity  implements ObservableScrollView.Callback {

    private ObservableScrollView mScrollView;

    private ImageView mImageView;
    private FrameLayout mImageFrameLayout;

    private Toolbar mToolbar;
    private LinearLayout mToolbarLinearLayout;

    private LinearLayout mContentLinearLayout;
    public static final int EXTRA_ID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set content view
        setContentView(R.layout.activity_article);

        // view matching
        mScrollView = (ObservableScrollView) findViewById(R.id.notify_scroll_view);

        mImageView = (ImageView) findViewById(R.id.image_view);
        mImageFrameLayout = (FrameLayout) findViewById(R.id.image_frame_layout);

        mContentLinearLayout = (LinearLayout) findViewById(R.id.content_linear_layout);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarLinearLayout = (LinearLayout) findViewById(R.id.toolbar_linear_layout);

        // more setup
        setupScrollView();
        setupToolbar();
    }

    private void setupScrollView() {
        mScrollView.setCallback(this);

        ViewTreeObserver viewTreeObserver = mScrollView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // get size
                    int toolbarLinearLayoutHeight = mToolbarLinearLayout.getHeight();
                    int imageHeight = mImageView.getHeight();

                    // adjust image frame layout height
                    ViewGroup.LayoutParams layoutParams = mImageFrameLayout.getLayoutParams();
                    if (layoutParams.height != imageHeight) {
                        layoutParams.height = imageHeight;
                        mImageFrameLayout.setLayoutParams(layoutParams);
                    }

                    // adjust top margin of content linear layout
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) mContentLinearLayout.getLayoutParams();
                    if (marginLayoutParams.topMargin != toolbarLinearLayoutHeight + imageHeight) {
                        marginLayoutParams.topMargin = toolbarLinearLayoutHeight + imageHeight;
                        mContentLinearLayout.setLayoutParams(marginLayoutParams);
                    }

                    // call onScrollChange to update initial properties.
                    onScrollChanged(0, 0, 0, 0);
                }
            });
        }
    }

    private void setupToolbar() {
        // set ActionBar as Toolbar
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);
        TextView title=(TextView)mToolbar.findViewById(R.id.toolbar_text);
        title.setText("Pengumuman Komisi Januari 2015 dipending karena sesuatu..");

        //mToolbar.setTitleTextColor(getResources().getColor(R.color.accent));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onScrollChanged(int left, int top, int oldLeft, int oldTop) {
        // get scroll y
        int scrollY = mScrollView.getScrollY();

        // choose appropriate y
        float newY = Math.max(mImageView.getHeight(), scrollY);

        // translate image and toolbar
        ViewCompat.setTranslationY(mToolbarLinearLayout, newY);
        ViewCompat.setTranslationY(mImageFrameLayout, scrollY * 0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
