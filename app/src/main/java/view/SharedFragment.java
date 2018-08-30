package view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smile.nytimes.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import api.NyTimesApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import pojo.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("ValidFragment")
class SharedFragment extends Fragment {
    @BindView(R.id.rv_most_news)
    RecyclerView rvMostNews;
    NewsAdapter newsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ButterKnife.bind(this, view);

        newsAdapter = new NewsAdapter();
        rvMostNews.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvMostNews.setAdapter(newsAdapter);
        load();
        return view;
    }

    private void load() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NyTimesApplication.BASE_PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        NyTimesApi nyTimesApi = retrofit.create(NyTimesApi.class);

        nyTimesApi.mostShared().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsAdapter.addAll(response.body().getResults());
                newsAdapter.notifyDataSetChanged();
                Log.d("Retrofit","Ok");
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("Retrofit",t.getMessage());
            }
        });
    }
}
