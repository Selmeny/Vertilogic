package com.dicoding.paul.vertilogic.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class UserModel implements Parcelable {
    private int id;
    private String name;
    private String userName;
    private String email;
    private String adressStreet;
    private String addressSuite;
    private String addressCity;
    private String addressZipCode;
    private String addressGeoLat;
    private String addRessGeoLng;
    private String phone;
    private String website;
    private String companyName;
    private String companyCatchPhrase;
    private String companyBs;

    private String TAG = UserModel.class.getSimpleName();

    public UserModel(JSONObject object) {
        try {
            int id = object.getInt("id");
            String name = object.getString("name");
            String userName = object.getString("username");
            String email = object.getString("email");
            String addressStreet = object.getJSONObject("address").getString("street");
            String addressSuite = object.getJSONObject("address").getString("suite");
            String addressCity = object.getJSONObject("address").getString("city");
            String addressZipCode = object.getJSONObject("address").getString("zipcode");
            String addressGeoLat = object.getJSONObject("address").getJSONObject("geo").getString("lat");
            String addressGeoLng = object.getJSONObject("address").getJSONObject("geo").getString("lng");
            String phone = object.getString("phone");
            String website = object.getString("website");
            String companyName = object.getJSONObject("company").getString("name");
            String companyCatchPhrase = object.getJSONObject("company").getString("catchPhrase");
            String companyBs = object.getJSONObject("company").getString("bs");

            this.id = id;
            this.name = name;
            this.userName = userName;
            this.email = email;
            this.adressStreet = addressStreet;
            this.addressSuite = addressSuite;
            this.addressCity = addressCity;
            this.addressZipCode = addressZipCode;
            this.addressGeoLat = addressGeoLat;
            this.addRessGeoLng = addressGeoLng;
            this.phone = phone;
            this.website = website;
            this.companyName = companyName;
            this.companyCatchPhrase = companyCatchPhrase;
            this.companyBs = companyBs;

            Log.d(TAG, "new user: " + name);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "object extracting failed " + object);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdressStreet() {
        return adressStreet;
    }

    public void setAdressStreet(String adressStreet) {
        this.adressStreet = adressStreet;
    }

    public String getAddressSuite() {
        return addressSuite;
    }

    public void setAddressSuite(String addressSuite) {
        this.addressSuite = addressSuite;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressGeoLat() {
        return addressGeoLat;
    }

    public void setAddressGeoLat(String addressGeoLat) {
        this.addressGeoLat = addressGeoLat;
    }

    public String getAddRessGeoLng() {
        return addRessGeoLng;
    }

    public void setAddRessGeoLng(String addRessGeoLng) {
        this.addRessGeoLng = addRessGeoLng;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCatchPhrase() {
        return companyCatchPhrase;
    }

    public void setCompanyCatchPhrase(String companyCatchPhrase) {
        this.companyCatchPhrase = companyCatchPhrase;
    }

    public String getCompanyBs() {
        return companyBs;
    }

    public void setCompanyBs(String companyBs) {
        this.companyBs = companyBs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.userName);
        dest.writeString(this.email);
        dest.writeString(this.adressStreet);
        dest.writeString(this.addressSuite);
        dest.writeString(this.addressCity);
        dest.writeString(this.addressZipCode);
        dest.writeString(this.addressGeoLat);
        dest.writeString(this.addRessGeoLng);
        dest.writeString(this.phone);
        dest.writeString(this.website);
        dest.writeString(this.companyName);
        dest.writeString(this.companyCatchPhrase);
        dest.writeString(this.companyBs);
        dest.writeString(this.TAG);
    }

    protected UserModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.userName = in.readString();
        this.email = in.readString();
        this.adressStreet = in.readString();
        this.addressSuite = in.readString();
        this.addressCity = in.readString();
        this.addressZipCode = in.readString();
        this.addressGeoLat = in.readString();
        this.addRessGeoLng = in.readString();
        this.phone = in.readString();
        this.website = in.readString();
        this.companyName = in.readString();
        this.companyCatchPhrase = in.readString();
        this.companyBs = in.readString();
        this.TAG = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}

