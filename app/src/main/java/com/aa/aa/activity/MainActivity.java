package com.aa.aa.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.aa.aa.R;
import com.aa.aa.base.BaseActivity;
import com.aa.aa.base.BaseFragment;
import com.aa.aa.fragment.FifthFragment;
import com.aa.aa.fragment.FirstFragment;
import com.aa.aa.fragment.FourthFragment;
import com.aa.aa.fragment.SecondFragment;
import com.aa.aa.fragment.ThirdFragment;
import com.aa.aa.widget.SpecialTab;
import com.aa.aa.widget.SpecialTabRound;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends BaseActivity {

    private final static int defaultPosition = 2;
    private BaseFragment[] mFragments = new BaseFragment[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragments[0] = FirstFragment.newInstance();
        mFragments[1] = SecondFragment.newInstance();
        mFragments[2] = ThirdFragment.newInstance();
        mFragments[3] = FourthFragment.newInstance();
        mFragments[4] = FifthFragment.newInstance();

        loadMultipleRootFragment(R.id.main_container, defaultPosition,
                mFragments[0],
                mFragments[1],
                mFragments[2],
                mFragments[3],
                mFragments[4]
        );
        PageNavigationView pageBottomTabLayout = (PageNavigationView) findViewById(R.id.tab);

//        NavigationController mNavigationController = pageBottomTabLayout.material()
//                .addItem(R.drawable.ic_ondemand_video_black_24dp, "AAAA", Color.RED)
//                .addItem(R.drawable.ic_home_black_24dp, "BBBB", Color.RED)
//                .addItem(R.drawable.ic_dashboard_black_24dp, "CCCC", Color.RED)
//                .addItem(R.drawable.ic_notifications_black_24dp, "DDDD", Color.RED)
//                .setDefaultColor(Color.WHITE)//未选中状态的颜色
//                .build();
        NavigationController mNavigationController = pageBottomTabLayout.custom()
                .addItem(newItem(R.drawable.ic_ondemand_video_black_24dp, R.drawable.ic_ondemand_video_black_24dp, "aaa"))
                .addItem(newItem(R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp, "bbb"))
                .addItem(newRoundItem(R.drawable.ic_recovery, R.drawable.ic_recovery_checked, "ccc"))
                .addItem(newItem(R.drawable.ic_dashboard_black_24dp, R.drawable.ic_dashboard_black_24dp, "ddd"))
                .addItem(newItem(R.drawable.ic_dashboard_black_24dp, R.drawable.ic_dashboard_black_24dp, "eee"))
                .build();
        mNavigationController.setSelect(defaultPosition);
        mNavigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                showHideFragment(mFragments[index], mFragments[old]);
            }

            @Override
            public void onRepeat(int index) {
                Log.i("asd", "onRepeat selected: " + index);
            }
        });
        //设置消息数
        mNavigationController.setMessageNumber(1, 8);
        mNavigationController.setMessageNumber(2, 10);

        //设置显示小圆点
        mNavigationController.setHasMessage(0, true);
    }

    /**
     * 正常tab
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(Color.BLACK);
        mainTab.setTextCheckedColor(Color.RED);
        return mainTab;
    }

    /**
     * 圆形tab
     */
    private BaseTabItem newRoundItem(int drawable, int checkedDrawable, String text) {
        SpecialTabRound mainTab = new SpecialTabRound(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(Color.BLACK);
        mainTab.setTextCheckedColor(Color.RED);
        return mainTab;
    }
}
