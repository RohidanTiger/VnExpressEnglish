package vnexpressenglish.chickenzero.ht.com.vnexpressenglish;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.config.Contants;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.customize.DialogLoading;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.service.FragmentStackManager;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.view.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {
    public SCApplication mApplication;
    public FragmentStackManager fragmentStackManager;
    private int currentStackSize = 0;
    public BaseFragment currentFragment;
    protected DialogLoading mLoadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mApplication = (SCApplication) getApplication();
        mLoadingDialog = new DialogLoading(MainActivity.this);
        mLoadingDialog.setCancelable(false);
        initFragmentStackManager();
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        setTitle(R.string.str_news);
        pushFragments(new HomeFragment(),null,true);
    }

    @Override
    public void onBackPressed() {
        if (currentStackSize > 1 && !currentFragment.canPressBack()) {
            if (fragmentStackManager.pop(true))
                return;
        } else{
            finish();
        }
    }

    private void initFragmentStackManager() {
        fragmentStackManager = FragmentStackManager.forContainer(this,
                R.id.container, new FragmentStackManager.Callback() {
                    @Override
                    public void onStackChanged(int stackSize,
                                               Fragment topFragment) {
                        currentFragment = (BaseFragment) topFragment;
                        currentStackSize = stackSize;
                        Log.d("FragmentManager",
                                "===FRAGMENT STACK MANAGER: STACK CHANGED : New Size: "
                                        + stackSize
                                        + " . Current Fragment : "
                                        + topFragment.getClass()
                                        .getSimpleName());
                    }
                });

        fragmentStackManager.setDefaultAnimation(R.anim.slide_in_right,
                R.anim.slide_out_left, R.anim.slide_in_left,
                R.anim.slide_out_right);
    }

    // Default tab is TAB_HOME

    public void pushFragments(Fragment fragment, Bundle bundle,
                              boolean shouldAdd) {
        pushFragments(Contants.TAB_HOME, fragment, bundle, true, shouldAdd);
    }

    public void pushFragments(Fragment fragment, boolean shouldAnimate,
                              boolean shouldAdd) {
        pushFragments(Contants.TAB_HOME, fragment, null, shouldAnimate,
                shouldAdd);
    }

    public void pushFragments(Fragment fragment, Bundle bundle,
                              boolean shouldAnimate, boolean shouldAdd) {
        pushFragments(Contants.TAB_HOME, fragment, bundle, shouldAnimate,
                shouldAdd);
    }

    public void pushFragments(String tag, Fragment fragment, Bundle bundle,
                              boolean shouldAnimate, boolean shouldAdd) {
        fragmentStackManager.push(fragment,
                fragment.getClass().getSimpleName(), bundle, shouldAnimate,
                shouldAdd);
    }

    public void popFragments() {
        popFragments(true);
    }

    public void popFragments(boolean isSlideBack) {
        fragmentStackManager.pop(isSlideBack);
    }
    //
    // Clear all current fragment
    public void clearAllPreviousFragment() {
        fragmentStackManager.clearAllScreen();
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
    public void setActionBarTitle(int titleID){
        getSupportActionBar().setTitle(getString(titleID));
    }

    public void showLoading() {
        try {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mLoadingDialog.show();
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoading(int strResId) {
        showLoading();
    }


    public void hideLoading() {

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mLoadingDialog != null) {
                        mLoadingDialog.hide();
                    }
                } catch (Exception error) {
                    error.printStackTrace();
                }
            }
        });
    }


    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
