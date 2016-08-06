package com.exinnos.tabssample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Edit user profile fragment page.
 */
public class EditUserProfileFragment extends Fragment {

    private static final String LOG_TAG = EditUserProfileFragment.class.getSimpleName();
    private View rootView;
    private OnEditUserProfileFragmentListener mListener;

    public EditUserProfileFragment() {
        // Required empty public constructor
    }

    public static EditUserProfileFragment newInstance() {
        EditUserProfileFragment fragment = new EditUserProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_edit_user_profile, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.user_profile_save_image_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onUserProfileSaveButtonClicked();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditUserProfileFragmentListener) {
            mListener = (OnEditUserProfileFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEditUserProfileFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public interface OnEditUserProfileFragmentListener {
        void onUserProfileSaveButtonClicked();
    }
}


