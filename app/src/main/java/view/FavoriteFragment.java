package view;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
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

import java.util.ArrayList;
import java.util.List;

import api.AppDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;
import pojo.Result;
import pojo.ResultRoom;

@SuppressLint("ValidFragment")
class FavoriteFragment extends Fragment {
    @BindView(R.id.rv_most_news)
    RecyclerView rvMostNews;
    NewsAdapter newsAdapter;
    List<Result> resultList = new ArrayList<>();

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
        final AppDatabase db = NyTimesApplication.getInstance().getDatabase();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<ResultRoom> resultRoom = new ArrayList<>();
                resultList.clear();
                try {
                    resultRoom = db.resultDao().getAll();
                } catch (Throwable t) {
                    Log.i("Room",t.toString());
                };
                for (ResultRoom e : resultRoom) {
                    resultList.add(new Result(e));
                    Log.i("Room", resultList.toString());
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                newsAdapter.addAll(resultList);
                newsAdapter.notifyDataSetChanged();
                Log.i("Room", "onPostExecute");
            }
        }.execute();
    }
}
