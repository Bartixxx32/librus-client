package pl.librus.client.announcements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import pl.librus.client.R;
import pl.librus.client.api.Announcement;
import pl.librus.client.api.Teacher;
import pl.librus.client.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnnouncementDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnnouncementDetailsFragment extends Fragment {
    private static final String ARG_ANNOUNCEMENT = "librus-client:AnnouncementDetailsFragment:announcement";
    private static final String ARG_AUTHOR = "librus-client:AnnouncementDetailsFragment:author";
    private Announcement announcement;
    private Teacher author;

    public AnnouncementDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param announcement Announcement to show
     * @return A new instance of fragment AnnouncementDetailsFragment.
     */
    public static AnnouncementDetailsFragment newInstance(Announcement announcement, Teacher author) {
        AnnouncementDetailsFragment fragment = new AnnouncementDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ANNOUNCEMENT, announcement);
        args.putSerializable(ARG_AUTHOR, author);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            announcement = (Announcement) getArguments().getSerializable(ARG_ANNOUNCEMENT);
            author = (Teacher) getArguments().getSerializable(ARG_AUTHOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_announcement_details, container, false);

        ((MainActivity) getActivity()).setBackArrow(true);

        TextView titleTextView = (TextView) root.findViewById(R.id.fragment_announcement_details_top_panel);
        TextView contentTextView = (TextView) root.findViewById(R.id.fragment_announcement_details_bottom_panel);
        TextView authorTextView = (TextView) root.findViewById(R.id.two_line_list_item_title);
        TextView dateTextView = (TextView) root.findViewById(R.id.two_line_list_item_content);
        View background = root.findViewById(R.id.fragment_announcement_details);
        View info = root.findViewById(R.id.fragment_announcement_details_info);
        titleTextView.setText(announcement.getSubject());
        contentTextView.setText(announcement.getContent());
        authorTextView.setText(author.getName());
        dateTextView.setText(announcement.getStartDate().toString("EEEE, d MMMM yyyy", new Locale("pl")));

        background.setTransitionName("announcement_background_" + announcement.getId());
        info.setTransitionName("announcement_info_" + announcement.getId());

        AnnouncementUtils.markAsRead(announcement.getId(), getContext());
        return root;
    }

}
