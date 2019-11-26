package com.bookwego.applyCouponActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.bookwego.R;
import com.bookwego.applyCouponActivity.adapter.CouponAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyCouponActivity extends AppCompatActivity {

    @BindView(R.id.recycler_coupon)
    RecyclerView recycler_coupon;

    @BindView(R.id.img_back)
    ImageView img_back;

    CouponAdapter couponAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_coupon);

        ButterKnife.bind(this);
        Initialization();
    }

    private void Initialization() {
        couponAdapter = new CouponAdapter(this);
        recycler_coupon.setLayoutManager(new LinearLayoutManager(this));
        recycler_coupon.setAdapter(couponAdapter);

    }

    @OnClick(R.id.img_back)
    public void img_back() {
        finish();
    }
}
