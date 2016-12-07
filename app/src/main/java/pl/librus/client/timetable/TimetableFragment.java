package pl.librus.client.timetable;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.librus.client.R;
import pl.librus.client.api.Event;
import pl.librus.client.api.Lesson;
import pl.librus.client.api.LibrusData;
import pl.librus.client.api.Timetable;
import pl.librus.client.ui.MainActivity;
import pl.librus.client.ui.MainFragment;

public class TimetableFragment extends MainFragment {
    private final String TAG = "librus-client-log";
    private TabLayout tabLayout;

    public static TimetableFragment newInstance(LibrusData cache) {

        Bundle args = new Bundle();

        Timetable timetable = cache.getTimetable();
        List<Event> events = cache.getEvents();

        for (Event event : events) {
            Lesson lesson = timetable.getLesson(event.getDate(), event.getLessonNumber());
            if (lesson != null) {
                lesson.setEvent(event);
            }
        }

        args.putSerializable("data", timetable);
        TimetableFragment fragment = new TimetableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_timetable, container, false);
        tabLayout = (TabLayout) LayoutInflater.from(getContext()).inflate(R.layout.tabs, null);
        MainActivity activity = (MainActivity) getActivity();
        ViewPager viewPager = (ViewPager) root.findViewById(R.id.container);

        Timetable timetable = (Timetable) getArguments().getSerializable("data");

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), timetable);

        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setCurrentItem(TimetableUtils.getDefaultTab(), true);

        tabLayout.setupWithViewPager(viewPager);
        activity.addTabs(tabLayout);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity) getActivity()).removeTabs(tabLayout);
    }

    @Override
    public void refresh(LibrusData cache) {
        Log.d(TAG, "TimetableFragment update()");
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final Timetable timetable;

        SectionsPagerAdapter(FragmentManager fm, Timetable timetable) {
            super(fm);
            this.timetable = timetable;
        }

        @Override
        public Fragment getItem(int position) {
            return TimetablePageFragment.newInstance(timetable.getSchoolDay(TimetableUtils.getTabDate(position)));
        }

        @Override
        public int getCount() {
            return TimetableUtils.getDayCount();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TimetableUtils.getTabTitle(position, false, true);
        }
    }
}
