package com.vagrant.usefultest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.vagrant.usefultest.R;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

	private ListView listView;
	private View footerView;

	private int lastItem;
	private ProgressBar progressBar;

	private Handler handler = new Handler();
	private BaseAdapter listAdapter;

	private List<String> DATA = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_second, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		listView = (ListView)getView().findViewById(R.id.fragment_second_list);
		footerView = LayoutInflater.from(getActivity()).inflate(R.layout.secondframent_footer, null);
		progressBar = (ProgressBar)footerView.findViewById(R.id.loading_animation);
		listView.addFooterView(footerView);
		for (int i = 0; i < 20; i++) {
			DATA.add(i, "项目" + i);
		}
		listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,DATA);
		listView.setAdapter(listAdapter);

		listView.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
					if (view.getLastVisiblePosition() == view.getCount() - 1) {
						loadData();
					}
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				lastItem = firstVisibleItem + visibleItemCount - 1;
			}
		});
	}

	private void loadData() {
		progressBar.setVisibility(View.VISIBLE);
		handler.post(new Runnable() {
			@Override
			public void run() {
				load();
				progressBar.setVisibility(View.GONE);
				listAdapter.notifyDataSetChanged();
			}
		});
	}
	private void load() {
		int count = listAdapter.getCount() + 1;
		for (int i = count; i < count + 20; i++) {
			DATA.add("更新项目" + i);
		}
	}
}
