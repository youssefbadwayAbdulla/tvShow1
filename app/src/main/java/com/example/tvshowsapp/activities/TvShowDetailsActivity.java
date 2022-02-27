package com.example.tvshowsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tvshowsapp.R;
import com.example.tvshowsapp.adpters.ImageSliderAdapter;
import com.example.tvshowsapp.databinding.ActivityTvShowDetailsBinding;
import com.example.tvshowsapp.repositories.TvShowDetailsRepository;
import com.example.tvshowsapp.responses.TvShowDetailsResponse;

import java.util.Locale;

public class TvShowDetailsActivity extends AppCompatActivity {
    ActivityTvShowDetailsBinding binding;
    TvShowDetailsRepository detailsRepository;
    private static final String TAG = "TvShowDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details);
        doInitialization();
    }

    private void doInitialization() {
        detailsRepository = ViewModelProviders.of(this).get(TvShowDetailsRepository.class);
        binding.imageBack.setOnClickListener(view -> onBackPressed());
        getTvShowDetails();
    }

    private void getTvShowDetails() {
        binding.setIsLoading(true);
        String tvShowId = String.valueOf(getIntent().getIntExtra("id", -1));
        detailsRepository.getTvShowDetails(tvShowId);
        detailsRepository.mutableLiveData
                .observe(this, tvShowDetailsResponse -> {
                    binding.setIsLoading(false);
                    if (tvShowDetailsResponse.getTvShowDetails() != null) {
                        if (tvShowDetailsResponse.getTvShowDetails().getPictures() != null) {
                            loadImageSlider(tvShowDetailsResponse.getTvShowDetails().getPictures());
                        }
                        binding.setTvShowImageURL(tvShowDetailsResponse.getTvShowDetails().getImage_path());
                    }
                    binding.imageTvShow.setVisibility(View.VISIBLE);
                    binding.setDescription(String.valueOf(HtmlCompat.fromHtml(tvShowDetailsResponse.getTvShowDetails().getDescription(),
                            HtmlCompat.FROM_HTML_MODE_LEGACY)));
                    binding.textDescription.setVisibility(View.VISIBLE);
                    binding.tvReadMore.setVisibility(View.VISIBLE);
                    binding.tvReadMore.setOnClickListener(view -> {
                        if(binding.tvReadMore.getText().toString().equals("Read More")){
                            binding.textDescription.setMaxLines(Integer.MAX_VALUE);
                            binding.textDescription.setEllipsize(null);
                            binding.tvReadMore.setText(R.string.read_less);

                        }else {
                            binding.textDescription.setMaxLines(4);
                            binding.textDescription.setEllipsize(TextUtils.TruncateAt.END);
                            binding.tvReadMore.setText(R.string.read_more);
                        }
                    });
                    binding.setRating(String.format(Locale.getDefault(),
                            "%.2f",
                            Double.parseDouble(tvShowDetailsResponse.getTvShowDetails().getRating())));
                    if(tvShowDetailsResponse.getTvShowDetails().getGenres()!=null){
                        binding.setGenre(tvShowDetailsResponse.getTvShowDetails().getGenres()[0]);
                    }else{
                        binding.setGenre("N/A");
                    }
                    binding.setRuntime(tvShowDetailsResponse.getTvShowDetails().getRuntime()+" Min");
                    binding.viewDivider1.setVisibility(View.VISIBLE);
                    binding.viewDivider2.setVisibility(View.VISIBLE);
                    binding.linearMisc.setVisibility(View.VISIBLE);
                    binding.btnWebsite.setOnClickListener(view -> {
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(tvShowDetailsResponse.getTvShowDetails().getUrl()));
                        startActivity(intent);
                    });
                    binding.btnWebsite.setVisibility(View.VISIBLE);
                    binding.btnEpisodes.setVisibility(View.VISIBLE);
                    loadBasicTVShowDetails();

                });



    }

    private void loadImageSlider(String[] sliderImage) {
        binding.sliderViewPager.setOffscreenPageLimit(1);
        binding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImage));
        binding.sliderViewPager.setVisibility(View.VISIBLE);
        binding.viewFadingEdge.setVisibility(View.VISIBLE);
        setupSliderIndicators(sliderImage.length);
        binding.sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSliderIndicators(position);
            }
        });
    }

    private void setupSliderIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutparams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.background_slider_indicator_inactive));
            indicators[i].setLayoutParams(layoutparams);
            binding.linearSliderIndicators.addView(indicators[i]);

        }
        binding.linearSliderIndicators.setVisibility(View.VISIBLE);
        setCurrentSliderIndicators(0);
    }

    private void setCurrentSliderIndicators(int position) {
        int childCount = binding.linearSliderIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) binding.linearSliderIndicators.getChildAt(i);
            if(i==position){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background_slider_indicator_active));
            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background_slider_indicator_inactive));
            }
        }
    }
    private void loadBasicTVShowDetails(){
        binding.setTvShowName(getIntent().getStringExtra("name"));
        binding.setStatus(getIntent().getStringExtra("status"));
        binding.setNetworkCountry(getIntent().getStringExtra("network")+"("+getIntent().getStringExtra("country")+")");
        binding.setStartedData(getIntent().getStringExtra("startDate"));
        binding.textName.setVisibility(View.VISIBLE);
        binding.textStarted.setVisibility(View.VISIBLE);
        binding.textNetworkCountry.setVisibility(View.VISIBLE);
        binding.textStatus.setVisibility(View.VISIBLE);
    }
}