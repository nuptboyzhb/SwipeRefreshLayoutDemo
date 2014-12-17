package net.mobctrl.swiperefresh;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author Zheng Haibo
 * @web http://www.mobctrl.net
 *
 */
public class MainActivity extends Activity {

	private SwipeRefreshLayout mSwipeRefreshLayout;
	private ListView mListView;
	private ArrayList<String> list = new ArrayList<String>();
	private TextView tv_tips;

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout);

		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		mListView = (ListView) findViewById(R.id.listview);
		tv_tips = (TextView) findViewById(R.id.tv_tips);
		mListView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData()));
		mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		mSwipeRefreshLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						tv_tips.setVisibility(View.VISIBLE);
						tv_tips.setText("waiting...");
						processOnRefresh();
					}
				});
	}

	private ArrayList<String> getData() {
		list.add("A");
		list.add("B");
		list.add("Cos");
		list.add("Source");
		list.add("Linux kernel");
		list.add("Alibaba Group");
		return list;
	}

	public void processOnRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				tv_tips.setVisibility(View.GONE);
				mSwipeRefreshLayout.setRefreshing(false);
			}
		}, 5000);
	}

}
