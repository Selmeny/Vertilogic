package com.dicoding.paul.vertilogic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.dicoding.paul.vertilogic.model.UserModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_detail_personal_info) TextView detailPersonalInfo;
    @BindView(R.id.tv_detail_name) TextView detailName;
    @BindView(R.id.tv_detail_username) TextView detailUserName;
    @BindView(R.id.tv_detail_email) TextView detailEmail;
    @BindView(R.id.tv_detail_phone) TextView detailPhone;
    @BindView(R.id.tv_detail_website) TextView detailWebsite;
    @BindView(R.id.tv_detail_company_info) TextView detailCompanyInfo;
    @BindView(R.id.tv_detail_company_name) TextView detailCompanyName;
    @BindView(R.id.tv_detail_company_catchphrase) TextView detailCompanyCatchPhrase;
    @BindView(R.id.tv_detail_company_bs) TextView detailCompanyBs;
    @BindView(R.id.tv_detail_address) TextView detailAddress;
    @BindView(R.id.tv_detail_address_street) TextView detailAddressStreet;
    @BindView(R.id.tv_detail_address_suite) TextView detailAdressSuite;
    @BindView(R.id.tv_detail_address_city) TextView detailAddressCity;
    @BindView(R.id.tv_detail_address_zipcode) TextView detailAddressZipCode;

    private String locationLat;
    private String locationLng;

    public static String EXTRA_USER = "extra_user";
    private static String TAG = DetailActivity.class.getSimpleName();

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showDetail();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    //Load data from model into view components
    private void showDetail() {
        UserModel userModel = getIntent().getParcelableExtra(EXTRA_USER);
        if (userModel != null) {
            try {
                detailName.setText(userModel.getName());
                detailUserName.setText(userModel.getUserName());
                detailEmail.setText(userModel.getEmail());
                detailPhone.setText(userModel.getPhone());
                detailWebsite.setText(userModel.getWebsite());

                detailCompanyName.setText(userModel.getCompanyName());
                detailCompanyCatchPhrase.setText(userModel.getCompanyCatchPhrase());
                detailCompanyBs.setText(userModel.getCompanyBs());

                detailAddressStreet.setText(userModel.getAdressStreet());
                detailAdressSuite.setText(userModel.getAddressSuite());
                detailAddressCity.setText(userModel.getAddressCity());
                detailAddressZipCode.setText(userModel.getAddressZipCode());

                //Get location coordinate
                locationLat = userModel.getAddressGeoLat();
                locationLng = userModel.getAddRessGeoLng();

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Load failed: " + userModel);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in location and move the camera
        double locLat = Double.parseDouble(locationLat);
        double locLng = Double.parseDouble(locationLng);

        LatLng location = new LatLng(locLat, locLng);
        mMap.addMarker(new MarkerOptions().position(location).title("Address"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }
}
