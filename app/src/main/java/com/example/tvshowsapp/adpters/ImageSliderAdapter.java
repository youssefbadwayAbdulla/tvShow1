package com.example.tvshowsapp.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowsapp.R;
import com.example.tvshowsapp.databinding.ItemConyanierSliderImageBinding;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderHolder> {
    private String[] sliderImage;

    public ImageSliderAdapter(String[] sliderImage) {
        this.sliderImage = sliderImage;
    }

    @NonNull
    @Override
    public ImageSliderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageSliderHolder(DataBindingUtil.inflate(LayoutInflater.
                from(parent.getContext()), R.layout.item_conyanier_slider_image,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderHolder holder, int position) {
        holder.bindSlidImage(sliderImage[position]);

    }

    @Override
    public int getItemCount() {
        return sliderImage.length;
    }

    class ImageSliderHolder extends RecyclerView.ViewHolder {
        ItemConyanierSliderImageBinding binding;
        public ImageSliderHolder(@NonNull   ItemConyanierSliderImageBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bindSlidImage(String imageURL){
            binding.setImageURL(imageURL);
        }
    }
}
