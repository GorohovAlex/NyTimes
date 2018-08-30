package view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smile.nytimes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import api.AppDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pojo.Result;
import pojo.ResultRoom;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Result> mResultList = new ArrayList<>();

    public void addAll(List<Result> resultList) {
        mResultList.clear();
        mResultList.addAll(resultList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = mResultList.get(position);
        holder.tvTitle.setText(result.getTitle());

        String url = "";

        if(result.getMedia() != null)
        url = result.getMedia().get(0).getMediaMetadata().get(0).getUrl();

        if(url!="")
        Picasso.get()
                .load(url)
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_favorite)
        ImageView ivFavorite;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.tv_title)
        public void onClickTitle(View v){
            Intent intent = new Intent(itemView.getContext(),ArticleActivity.class);
            intent.putExtra("url",mResultList.get(getLayoutPosition()).getUrl());
            itemView.getContext().startActivity(intent);
        }

        @OnClick(R.id.iv_favorite)
        public void onClickFavorite(View v) {
            if (ivFavorite.getTag() == null) {
                ivFavorite.setImageResource(R.drawable.ic_favorite);
                ivFavorite.setTag(true);
                addFavorite(mResultList.get(getLayoutPosition()));
                Toast.makeText(v.getContext(), "Add Favorite", Toast.LENGTH_SHORT).show();
            } else {
                ivFavorite.setTag(null);
                ivFavorite.setImageResource(R.drawable.ic_favorite_border);
                delFavorite(mResultList.get(getLayoutPosition()));
                Toast.makeText(v.getContext(), "Remove Favorite", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addFavorite(final Result result) {
        final AppDatabase db = NyTimesApplication.getInstance().getDatabase();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.i("Room", "AddFavorite");
                ResultRoom resultRoom = new ResultRoom(result);
                try {
                    db.resultDao().insert(resultRoom);
                }catch (Throwable t){};

                return null;
            }
        }.execute();
    }

    private void delFavorite(Result result) {
        final AppDatabase db = NyTimesApplication.getInstance().getDatabase();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.resultDao().delete(new ResultRoom());
                return null;
            }
        }.execute();
    }
}
