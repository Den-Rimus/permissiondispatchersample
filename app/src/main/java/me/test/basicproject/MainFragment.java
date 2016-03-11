package me.test.basicproject;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.button) void buttonClicked() {
        requirePermission();
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void requirePermission() {
        //
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationale() {
        Snackbar.make(getView(), "Rationale showed", Snackbar.LENGTH_LONG).show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void permissionDenied() {
        Snackbar.make(getView(), "Denied", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MainFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
