package lws.com.liwushuo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import lws.com.liwushuo.R;
import lws.com.liwushuo.view.fragment.BangdanFragment;
import lws.com.liwushuo.view.fragment.FenleiFragment;
import lws.com.liwushuo.view.fragment.MineFragment;
import lws.com.liwushuo.view.fragment.ShouyeFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ShouyeFragment shouyeFragment = new ShouyeFragment();
    private BangdanFragment bangdanFragment = new BangdanFragment();
    private FenleiFragment fenleiFragment = new FenleiFragment();
    private MineFragment mineFragment = new MineFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((RadioGroup) findViewById(R.id.rg_main)).setOnCheckedChangeListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,shouyeFragment).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_shouye:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, shouyeFragment).commit();
                break;
            case R.id.rb_bangdan:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, bangdanFragment).commit();
                break;
            case R.id.rb_fenlei:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fenleiFragment).commit();
                break;
            case R.id.rb_mine:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, mineFragment).commit();
                break;
        }
    }
}
