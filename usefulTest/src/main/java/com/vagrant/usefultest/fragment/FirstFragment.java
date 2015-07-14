package com.vagrant.usefultest.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.vagrant.usefultest.R;
import com.vagrant.usefultest.activity.ActionDetailActivity;
import com.vagrant.usefultest.adapter.ActionListAdapter;
import com.vagrant.usefultest.dummydata.Cheeses;
import com.vagrant.usefultest.entity.ActionBean;
import com.vagrant.usefultest.network.volley.VolleyManager;
import com.vagrant.usefultest.view.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class FirstFragment extends SwipeRefreshListFragment {
    private static final String LOG_TAG = FirstFragment.class.getSimpleName();
    private static final int LIST_ITEM_COUNT = 20;
    private SwipeRefreshLayout swipeRefreshLayout;
    private XListView actionListView;
    private PtrFrameLayout ptrFrameLayout;
    private List<ActionBean.Actions> actionsList = new ArrayList<ActionBean.Actions>();
    private ActionListAdapter actionListAdapter;
    private VolleyManager volleyManager;
    private int pageno = 1;
    private View contentView;

    public static final String ACTIONDETAIL_KEY = "com.vagrant.usefultest.firstfragment.aid";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        volleyManager = VolleyManager.getInstance(getActivity());
        // Notify the system to allow an options menu for this fragment.
        setHasOptionsMenu(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), ActionDetailActivity.class);
        intent.putExtra(ACTIONDETAIL_KEY, actionsList.get(position).getId());
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_first, container, false);
        return contentView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * Create an ArrayAdapter to contain the data for the ListView. Each item in the ListView
         * uses the system-defined simple_list_item_1 layout that contains one TextView.
         */
//		ListAdapter adapter = new ArrayAdapter<String>(
//				getActivity(),
//				android.R.layout.simple_list_item_1,
//				android.R.id.text1,
//				Cheeses.randomList(LIST_ITEM_COUNT));
//
//		// Set the adapter between the ListView and its backing data.
//		setListAdapter(adapter);
        setListAdapter(actionListAdapter);

        initView();
        autoRefresh();
        // BEGIN_INCLUDE (setup_refreshlistener)
        /**
         * Implement {@link SwipeRefreshLayout.OnRefreshListener}. When users do the "swipe to
         * refresh" gesture, SwipeRefreshLayout invokes
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}. In
         * {@link SwipeRefreshLayout.OnRefreshListener#onRefresh onRefresh()}, call a method that
         * refreshes the content. Call the same method in response to the Refresh action from the
         * action bar.
         */
//		setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//			@Override
//			public void onRefresh() {
//				Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
//				initiateRefresh();
//			}
//		});
        // END_INCLUDE (setup_refreshlistener)
    }

    // BEGIN_INCLUDE (initiate_refresh)

    /**
     * By abstracting the refresh process to a single method, the app allows both the
     * SwipeGestureLayout onRefresh() method and the Refresh action item to refresh the content.
     */
    private void initiateRefresh() {
        Log.i(LOG_TAG, "initiateRefresh");

        /**
         * Execute the background task, which uses {@link android.os.AsyncTask} to load the data.
         */
        new DummyBackgroundTask().execute();
    }

    // END_INCLUDE (initiate_refresh)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    private void autoRefresh() {
        actionListView.autoRefresh();

        getActions();
    }
    private void initView() {
//		swipeRefreshLayout = (SwipeRefreshLayout)getView().findViewById(R.id.swiperefresh);
//		swipeRefreshLayout.setOnRefreshListener(new MyRefreshListener());
        actionListView = (XListView) getView().findViewById(android.R.id.list);
        actionListView.setPullLoadEnable(true);
        actionListAdapter = new ActionListAdapter(getActivity(), actionsList);
        actionListView.setAdapter(actionListAdapter);
        actionListView.setPullRefreshEnable(true);
        actionListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
//				initiateRefresh();
                pageno = 1;
                getActions();
            }

            @Override
            public void onLoadMore() {
                loadMoreAction();
            }
        });
//		ptrFrameLayout = (PtrFrameLayout)getView().findViewById(R.id.ptr_framelayout);
//		ptrFrameLayout.setPtrHandler(new PtrHandler() {
//			@Override
//			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
////				return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//				return true;
//			}
//
//			@Override
//			public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
//				initiateRefresh();
//			}
//		});
    }

    private void onLoadEnd(XListView list) {
        list.stopRefresh();
        list.stopLoadMore();
        list.setRefreshTime(getTime());
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    private void getActions() {
        String url = "http://birdboy.cn/SearchAAPI";
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "4");
        params.put("sort", "uptime");
        params.put("pageno", String.valueOf(pageno));

        volleyManager.getPojoWithVolley(url, params, ActionBean.class, new Response.Listener<ActionBean>() {
            @Override
            public void onResponse(ActionBean response) {
                Log.e("firstfragment", String.valueOf(response.isSuccess()));
                onLoadEnd(actionListView);
                if (pageno == 1) {
                    actionsList.clear();
                }
                if (response.isSuccess()) {
                    List<ActionBean.Actions> actionListTemp = response.getResult().getActions();
                    Log.e("firstfragment", String.valueOf(actionListTemp.size()));
                    if (actionListTemp != null && actionListTemp.size() > 0) {
                        if (actionListTemp.size() < 12) {
                            actionListView.setPullLoadEnable(false);
                        }
                        actionsList.addAll(actionListTemp);
                        actionListAdapter.notifyDataSetChanged();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FirstFragment.this.getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                onLoadEnd(actionListView);
            }
        });
    }

    private void loadMoreAction() {
        ++pageno;
        getActions();
    }

    //更新列表
    private void updateList() {

        //更新完毕，取消进度条
        swipeRefreshLayout.setRefreshing(false);
    }

    //刷新监听器
    private class MyRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
            //实际更新操作的方法, 更新完成后需要调用setRefreshing(false)方法取消进度条的显示
//			updateList();
//			initiateRefresh();
        }
    }
    // BEGIN_INCLUDE (refresh_complete)

    /**
     * When the AsyncTask finishes, it calls onRefreshComplete(), which updates the data in the
     * ListAdapter and turns off the progress bar.
     */
    private void onRefreshComplete(List<String> result) {
        Log.i(LOG_TAG, "onRefreshComplete");

        // Remove all items from the ListAdapter, and then replace them with the new items
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
        adapter.clear();
        for (String cheese : result) {
            adapter.add(cheese);
        }
        onLoadEnd(actionListView);
        // Stop the refreshing indicator
//		setRefreshing(false);
//		swipeRefreshLayout.setRefreshing(false);
//		ptrFrameLayout.refreshComplete();

    }

    // END_INCLUDE (refresh_complete)

    /**
     * Dummy {@link AsyncTask} which simulates a long running task to fetch new cheeses.
     */
    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<String>> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds

        @Override
        protected List<String> doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a new random actionListView of cheeses
            return Cheeses.randomList(LIST_ITEM_COUNT);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete(result);
        }
    }
}
