package project.trustuff.net;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
        case 0:

            return new Home();
        case 1:

            return new Sell();
        case 2:

            return new Profile();
        }
        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3;
    }

}
