package com.nxuanthuong.androidmultiimagepicker.adapters;

import android.content.Context;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nxuanthuong.androidmultiimagepicker.R;
import com.nxuanthuong.androidmultiimagepicker.models.Picture;

import java.util.List;

public class GalleryItemAdapter extends RecyclerView.Adapter<GalleryItemAdapter.GalleryItemViewHolder> {


    private List<Picture> pictures;
    private Context context;

    public GalleryItemAdapter(Context context,List<Picture> pictures){
        this.context=context;
        this.pictures=pictures;
    }

    @NonNull
    @Override
    public GalleryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.item_gallery_picture,parent,false);
        return  new GalleryItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryItemViewHolder holder, int position) {
        holder.bind(pictures.get(position));
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class GalleryItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPictureItem;
        TextView textViewSelectCount;

        public GalleryItemViewHolder(View itemView) {
            super(itemView);
            imageViewPictureItem=itemView.findViewById(R.id.imageViewPictureItem);
            textViewSelectCount=itemView.findViewById(R.id.item_gallery_slect_count);
        }

        public void bind(Picture picture) {
            RequestOptions options = new RequestOptions().skipMemoryCache(true).override(200,200).placeholder(R.drawable.ic_launcher_background);
//                    .centerCrop()
//                    .placeholder(R.drawable.ic_camera)
//                    .error(R.drawable.ic_send)
//                    .priority(Priority.HIGH);
            Glide.with(context)
                    .load(picture.getPath())
                    .apply(options)
                    .into(imageViewPictureItem);

            if(picture.getSelectCount()>0){
                textViewSelectCount.setText(picture.getSelectCount()+"");
            }else{
                textViewSelectCount.setText("");
            }
        }
    }
}