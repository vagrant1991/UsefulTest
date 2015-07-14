package com.vagrant.usefultest.activity;

import android.os.Bundle;

import com.vagrant.usefultest.R;
import com.vagrant.usefultest.fragment.FirstFragment;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2015-6-25.
 */
public class ActionDetailActivity extends SwipeBackActivity {

    private int actionId;
    private SwipeBackLayout swipeBackLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);

        actionId = getIntent().getExtras().getInt(FirstFragment.ACTIONDETAIL_KEY);

        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
}
