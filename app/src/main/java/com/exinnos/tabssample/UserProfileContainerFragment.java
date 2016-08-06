package com.exinnos.tabssample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

public class UserProfileContainerFragment extends Fragment {

    private View rootView;
    private UserProfileFragment userProfileFragment;

    public UserProfileContainerFragment() {
        // Required empty public constructor
    }

    public static UserProfileContainerFragment newInstance() {
        UserProfileContainerFragment fragment = new UserProfileContainerFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_profile_container, container, false);

        if (savedInstanceState == null) {
            userProfileFragment = UserProfileFragment.newInstance();

            getChildFragmentManager().beginTransaction()
                    .replace(R.id.user_profile_container, userProfileFragment)
                    .commitAllowingStateLoss();
        }

        return rootView;
    }


    public void replaceFragment(Fragment fragment, boolean addToBackStack) {

        if (addToBackStack) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.user_profile_container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.user_profile_container, fragment)
                    .commit();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

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
}
