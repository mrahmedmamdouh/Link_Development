package com.linkdev.linkdevelopment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.linkdev.linkdevelopment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setNavigationDrawer();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.explore:
                setToast(getString(R.string.explore));
                break;
            case R.id.live:
                setToast(getString(R.string.live_chat));
                break;
            case R.id.gallery:
                setToast(getString(R.string.gallery));
                break;
            case R.id.wish_list:
                setToast(getString(R.string.wish_list));
                break;
            case R.id.e_magazine:
                setToast(getString(R.string.e_magazine));
                break;
        }
        return true;
    }

    private void setNavigationDrawer() {
        binding.navView.setNavigationItemSelectedListener(this);
    }

    private void setToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
