package com.example.podsstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.podsstore.aboutpod.AboutActivity;
import com.example.podsstore.aboutpod.ConnectwithPodActivity;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.banner.SellerBannerproductActivity;
import com.example.podsstore.category.CategoryActivity;
import com.example.podsstore.category.SubCategoryActivity;
import com.example.podsstore.category.SubCategoryProductActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.BannerResponse;
import com.example.podsstore.data.response.BusinessCatResponse;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.data.response.TopBrandsProductResponse;
import com.example.podsstore.data.response.TopBrandsResponse;
import com.example.podsstore.drower.AddressesActivity;
import com.example.podsstore.drower.FeedbackActivity;
import com.example.podsstore.drower.HelpAndFAQActivity;
import com.example.podsstore.drower.PrivacyActivity;
import com.example.podsstore.drower.ShowMakeofferActivity;
import com.example.podsstore.drower.TermsActivity;
import com.example.podsstore.getorder.MyOrderActivity;
import com.example.podsstore.login.LoginActivity;
import com.example.podsstore.mainactivityadapters.BestPricedAdapter;
import com.example.podsstore.mainactivityadapters.BestSellingProductAdapter;
import com.example.podsstore.mainactivityadapters.CategoryHorigentalAdapter;
import com.example.podsstore.mainactivityadapters.CustomAdapter;
import com.example.podsstore.mainactivityadapters.TopBrandAdapter;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.productdetails.ProductDetailsActivity;
import com.example.podsstore.profile.ProfileActivity;
import com.example.podsstore.search.SearchActivity;
import com.example.podsstore.topbrands.TopBrandsProductActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvcartsize, tvbestpriceseeall, tvbestsellingseeall, headerusername, tvemail;
    RadioGroup radioGroup1;
    RadioButton home, categories, profile, about;
    private RecyclerView recyclerView, bestsellingproductrv, bestprisedproductrv;
    private CategoryHorigentalAdapter productListAdapter;
    private BestSellingProductAdapter bestSellingProductAdapter;
    BestPricedAdapter bestPricedAdapter;

    TopBrandAdapter topBrandAdapter;
    private ImageView ivallproduct, ivcart, ivtoggle, ivgo;
    EditText search;
    ViewPager viewPager;
    Integer[] imageId = {R.drawable.catc, R.drawable.catb, R.drawable.catd, R.drawable.cata, R.drawable.cate, R.drawable.catf};
    String[] imagesName = {"image1", "image2", "image3", "image4"};
    SliderLayout slider;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 800;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 4000; // time in milliseconds between successive task executions.

    private int dotscount;
    private ImageView[] dots;
    LinearLayout sliderDotspanel;
    CircleImageView profileimage;
    ImageView ivcountry;
    private DrawerLayout dl;

    private Toolbar toolbar;
    private NavigationView nv;
    private RecyclerView topbrandrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManagerss.init(MainActivity.this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();
        slider=findViewById(R.id.slider);



//        getSupportActionBar().setTitle("  Pod");
//        getSupportActionBar().setElevation(0);
//
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.toggle);
        // getMenuInflater().inflate(R.menu.main_menu, menu);

    String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

  //      Log.e( "deviceid", String.valueOf(FirebaseInstanceId.getInstance().getToken()));
        toolbar = findViewById(R.id.toolbar);
        ivcountry = findViewById(R.id.ivcountry);

        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.mainactivity);
        //  t = new ActionBarDrawerToggle(this, dl,toolbar,R.string.Open, R.string.Close);
        Log.e("packagename",String.valueOf(getApplicationContext().getPackageName() ));


        View headerView = nv.getHeaderView(0);
        headerusername = headerView.findViewById(R.id.headerusername);
        tvemail = headerView.findViewById(R.id.tvemail);
        profileimage = headerView.findViewById(R.id.profileimage);


        topbrandrv = findViewById(R.id.topbrandrv);
        tvbestpriceseeall = findViewById(R.id.tvbestpriceseeall);
        tvbestsellingseeall = findViewById(R.id.tvbestsellingseeall);
        sliderDotspanel = findViewById(R.id.SliderDots);
        viewPager = findViewById(R.id.viewpager);

        search = findViewById(R.id.putwaysearch);
        tvcartsize = findViewById(R.id.tvcartsize);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        about = (RadioButton) findViewById(R.id.about);
        home = (RadioButton) findViewById(R.id.homes);
        categories = (RadioButton) findViewById(R.id.categories);
        profile = (RadioButton) findViewById(R.id.profile);
        ivallproduct = findViewById(R.id.ivallproduct);
        ivcart = findViewById(R.id.ivcart);
        ivgo = findViewById(R.id.ivgo);
        ivtoggle = findViewById(R.id.ivtoggle);
        home.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bluehome, 0, 0);
        home.setTextColor(Color.parseColor("#007eff"));
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent in;
                Log.i("matching", "matching inside1 bro" + checkedId);
                switch (checkedId) {
                    case R.id.homes:
                        Log.i("matching", "matching inside1 matching" + checkedId);
                        in = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(in);

                        break;
                    case R.id.categories:
                        Log.i("matching", "matching inside1 watchlistAdapter" + checkedId);

                        in = new Intent(getBaseContext(), CategoryActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);

                        break;
                    case R.id.profile:
                        Log.i("matching", "matching inside1 rate" + checkedId);


                        if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                            in = new Intent(getBaseContext(), ProfileActivity.class);
                            startActivity(in);
                            overridePendingTransition(0, 0);
                        } else {
                            showAlertDialog();
                        }


                        break;

                    case R.id.about:
                        Log.i("matching", "matching inside1 deals" + checkedId);
                        in = new Intent(getBaseContext(), AboutActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    default:
                        break;
                }
            }
        });

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagers
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerss
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManagertop
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        bestprisedproductrv = findViewById(R.id.bestprisedproductrv);
        bestsellingproductrv = findViewById(R.id.bestsellingproductrv);
        topbrandrv = findViewById(R.id.topbrandrv);
        recyclerView = findViewById(R.id.productrv);
        bestSellingProductAdapter = new BestSellingProductAdapter(MainActivity.this);
        bestPricedAdapter = new BestPricedAdapter(MainActivity.this);
        topBrandAdapter = new TopBrandAdapter(MainActivity.this);
        productListAdapter = new CategoryHorigentalAdapter(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        topbrandrv.setLayoutManager(layoutManagertop);


        bestsellingproductrv.setLayoutManager(layoutManagers);
        bestprisedproductrv.setLayoutManager(layoutManagerss);
//      recyclerView.setEmptyView(binding.emptyView);
        productListAdapter.setAdapterListener(adapterListener);
        bestSellingProductAdapter.setAdapterListener(adapterListeners);
        bestPricedAdapter.setAdapterListener(pricedlistner);
        topBrandAdapter.setAdapterListener(adapterListenertop);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 8);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productListAdapter);
        bestsellingproductrv.setAdapter(bestSellingProductAdapter);
        bestprisedproductrv.setAdapter(bestPricedAdapter);
        topbrandrv.setAdapter(topBrandAdapter);
        categorieslist();
        loadDatabestselling();
        loadDatabestprisedproduct();
        isStoragePermissionGranted();
        topbrandlist();
        ivallproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent category = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(category);
                finish();
            }
        });
        ivcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + "///" + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
                if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), AddToCartActivity.class);
                    intent.putExtra("main", "main");
                    startActivity(intent);
                    finish();
                } else {
                    showAlertDialog();
                }

            }
        });
        ivtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dl.open();



            }
        });
        ivgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Fill Some data to search", Toast.LENGTH_SHORT).show();
                } else {
                    Intent searchs = new Intent(getApplicationContext(), SearchActivity.class);
                    searchs.putExtra("data", search.getText().toString());
                    startActivity(searchs);
                    finish();
                }

            }
        });


        PagerAdapter adapter = new CustomAdapter(MainActivity.this, imageId, imagesName);
        viewPager.setAdapter(adapter);

        dotscount = adapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 6) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        tvbestpriceseeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent priced = new Intent(getApplicationContext(), ProductListActivity.class);
                startActivity(priced);
                finish();

            }
        });
        tvbestsellingseeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selling = new Intent(getApplicationContext(), ProductListActivity.class);
                startActivity(selling);
                finish();
            }
        });
        loadDatacart();

        loadDatabanner();
    /*    dl.addDrawerListener(t);

        t.syncState();*/


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nvscan:

                        if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                            Intent nvscan = new Intent(MainActivity.this, MyOrderActivity.class);
                            startActivity(nvscan);
                            dl.closeDrawers();
                        } else {
                            showAlertDialog();
                        }


                        break;
                    case R.id.nvsettings:
                        if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                            Intent nvsettings = new Intent(MainActivity.this, ShowMakeofferActivity.class);
                            startActivity(nvsettings);

                            dl.closeDrawers();
                        } else {
                            showAlertDialog();
                        }

                        break;
                    case R.id.nvhistory:
                        if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                            Intent nvhistory = new Intent(MainActivity.this, AddressesActivity.class);
                            startActivity(nvhistory);
                            dl.closeDrawers();
                        } else {
                            showAlertDialog();
                        }

                        break;
                    case R.id.nvrate:
                        Intent connct = new Intent(MainActivity.this, FeedbackActivity.class);
                        startActivity(connct);
                        dl.closeDrawers();
                        break;
                    case R.id.nvshare:
                        Intent help = new Intent(MainActivity.this, HelpAndFAQActivity.class);
                        startActivity(help);
                        dl.closeDrawers();
                        break;
                    case R.id.nvterm:
                        Intent term = new Intent(MainActivity.this, TermsActivity.class);
                        startActivity(term);
                        dl.closeDrawers();
                        break;

                    case R.id.nvprivacy:
                        Intent privacy = new Intent(MainActivity.this, PrivacyActivity.class);
                        startActivity(privacy);
                        dl.closeDrawers();
                        break;

                    case R.id.nvcategory:
                        Intent cat = new Intent(MainActivity.this, CategoryActivity.class);
                        startActivity(cat);
                        dl.closeDrawers();
                        break;
                    case R.id.nvsolution:
                        if(item.getTitle()=="Log In"){
Intent intent=new Intent(MainActivity.this, LoginActivity.class);
startActivity(intent);
                        }else{
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setCancelable(true);
                            dialog.setTitle("Exit from Pod!");
                            dialog.setMessage("Are you sure you want to exit from POD?");
                            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    //Action for "Delete".
                                    PreferenceManagerss.logout();

                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(getIntent());
                                    overridePendingTransition(0, 0);
                                }
                            })
                                    .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Action for "Cancel".
                                            dialog.cancel();
                                        }
                                    });

                            final AlertDialog alert = dialog.create();
                            alert.show();
                            dl.closeDrawers();
                        }

                        break;
//                    case R.id.mycart:
//                        Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();break;
//                    default:
//                        return true;
                }


                return true;

            }
        });



        Menu nav_Menu = nv.getMenu();

        if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {

        } else {

            nav_Menu.findItem(R.id.nvsolution).setTitle("Log In");
        }


        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(),"profle",Toast.LENGTH_LONG).show();
                if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showAlertDialog();
                }
            }
        });
        headerusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showAlertDialog();
                }

            }
        });
        tvemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showAlertDialog();
                }
            }
        });


        profileloadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (slider != null){
            slider.startAutoCycle();
        }
        loadDatacart();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @SuppressLint("CheckResult")
    private void loadDatacart() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + "///" + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getcartdetails(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<CartResponse> list = response.body();

                    tvcartsize.setText(String.valueOf(list.size()));
                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
            }
        });
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAGss", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("TAG", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }

    @SuppressLint("CheckResult")
    private void topbrandlist() {
        // binding.progress.setVisibility(View.VISIBLE);
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().gettopbrands()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<TopBrandsResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<TopBrandsResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<TopBrandsResponse> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.size()));
                            topBrandAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                });
    }

    private TopBrandAdapter.AdapterListener adapterListenertop = data -> {
        // Toast.makeText(getApplicationContext(), data.getImageurl(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, TopBrandsProductActivity.class);

   i.putExtra("userid", data.getSellerid().toString());
        startActivity(i);


    };

    @SuppressLint("CheckResult")
    private void categorieslist() {
        // binding.progress.setVisibility(View.VISIBLE);
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getbusinesscat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<BusinessCatResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<BusinessCatResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<BusinessCatResponse> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.size()));
                            productListAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                });
    }

    private CategoryHorigentalAdapter.AdapterListener adapterListener = data -> {
        // Toast.makeText(getApplicationContext(), data.getImageurl(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, SubCategoryActivity.class);
        i.putExtra("userid", data.getId().toString());
        i.putExtra("subcategory", data.getProductname().toString());
        startActivity(i);


    };

    @SuppressLint("CheckResult")
    private void loadDatabestselling() {
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getbestsellingproducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<TopBrandsProductResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<TopBrandsProductResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<TopBrandsProductResponse> list = response.body();
                            Log.e("getbestseddd", String.valueOf(list.toString()));
                            bestSellingProductAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("geterror", String.valueOf(e.getMessage()));

                    }
                });
    }


    @SuppressLint("CheckResult")
    private void loadDatabestprisedproduct() {
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getbestpricedproduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<TopBrandsProductResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<TopBrandsProductResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<TopBrandsProductResponse> list = response.body();
                            Log.e("getbestprised", String.valueOf(list.toString()));
                            bestPricedAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("geterror", String.valueOf(e.getMessage()));

                    }
                });
    }

    private BestSellingProductAdapter.AdapterListener adapterListeners = data -> {
        // Toast.makeText(getApplicationContext(), data.getImageurl(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid", String.valueOf(data.getId()));
        startActivity(i);


    };
    private BestPricedAdapter.AdapterListener pricedlistner = data -> {
        // Toast.makeText(getApplicationContext(), data.getImageurl(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid", String.valueOf(data.getId()));
        startActivity(i);


    };

  /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_item).setEnabled(true);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        /*Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();*/

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("Exit from Pod!");
        dialog.setMessage("Are you sure you want to exit from application?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                }
        )
                .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.alertlogin, null);


        alertDialog.setView(customLayout);
        TextView btnsave = (TextView) customLayout.findViewById(R.id.tvsave);
        ImageView cut = customLayout.findViewById(R.id.ivcut);
        //     EditText et =customLayout.findViewById(R.id.etmobile);


        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);
                finish();
alert.dismiss();
            }
        });
        alert.show();
    }

//    @SuppressLint("CheckResult")
//    private void profileloadData() {
//
//        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)
//        );
//
//        ApiClient.getApiClient().profile(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<ProfileResponses>() {
//            @Override
//            public void onResponse(Call<ProfileResponses> call, Response<ProfileResponses> response) {
//                View headerView = nv.getHeaderView(0);
//                headerusername = headerView.findViewById(R.id.headerusername);
//                tvemail = headerView.findViewById(R.id.tvemail);
//                profileimage = headerView.findViewById(R.id.profileimage);
//                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
//                Log.e("getprofile", String.valueOf(response.code()));
//                if (response.isSuccessful()) {
//                    ProfileResponses list = response.body();
////                    for (int i = 0; i < list.getAddress().size(); i++) {
////                        // tvaddress.setText(list.getAddress().get(i).getAddressline1().toString()+", "+list.getAddress().get(i).getAddressline2().toString()+"\n"+list.getAddress().get(i).getAddressline3().toString());
////
////                    }
//
//                   // for (int i = 0; i < list.getData().size(); i++) {
//                        Log.e("getprofilesss", String.valueOf(list.getData().getUserimageurl()));
//                        GlideUrl glideUrl = new GlideUrl(list.getData().getUserimageurl(),
//                                new LazyHeaders.Builder()
//                                        .addHeader("Authorization", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN))
//
//                                        .build());
//
//                        Glide.with(getApplicationContext())
//                                .load(glideUrl)
//                                .into(profileimage);
//                  //  }
//                    if(list.getCountryname()==null) {
//                        //Toast.makeText(getApplicationContext(),"no image",Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        GlideUrl glideUrls = new GlideUrl(list.getCountryname().getImageurl().toString(),
//                                new LazyHeaders.Builder()
//                                        .addHeader("Authorization", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN))
//
//                                        .build());
//
//                        Glide.with(getApplicationContext())
//                                .load(glideUrl)
//                                .into(ivcountry);
//                    }
//
//                    headerusername.setText(list.getUsername());
//
//                    tvemail.setText(list.getUseremailid());
//                    profileimage.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                    headerusername.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                    tvemail.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProfileResponses> call, Throwable t) {
//                Log.e("onerrors", t.getMessage());
//            }
//        });

     @SuppressLint("CheckResult")
    private void profileloadData() {
         Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"????"+getIntent().getStringExtra("userid") );

         ApiClient.getApiClient(). profile(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<ProfileResponses>>() {
             @Override
             public void onResponse(Call<List<ProfileResponses>> call, Response<List<ProfileResponses>> response) {

                 // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                 Log.e("getMaterialMasters",String.valueOf(response.code()) );
                 if (response.isSuccessful()) {
                     List<ProfileResponses> list = response.body();

            // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
            Log.e("getprofile", String.valueOf(response.code()));
            if (response.isSuccessful()) {

//                  for (int i = 0; i < list.getAddress().size(); i++) {
//                      // tvaddress.setText(list.getAddress().get(i).getAddressline1().toString()+", "+list.getAddress().get(i).getAddressline2().toString()+"\n"+list.getAddress().get(i).getAddressline3().toString());
//
//                  }

               for (int i = 0; i < list.size(); i++) {
                   if(list.get(i).getData()!=null){
                       Log.e("getprofilesss", String.valueOf(list.get(i).getData().userimageurl));
                       GlideUrl glideUrl = new GlideUrl(list.get(i).getData().userimageurl,
                               new LazyHeaders.Builder()
                                       .addHeader("Authorization", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN))

                                       .build());

                       Glide.with(getApplicationContext())
                               .load(glideUrl)
                               .into(profileimage);

                   }

                   headerusername.setText(list.get(i).getUsername());

                   tvemail.setText(list.get(i).getUseremailid());

                if(list.get(0).countryname==null) {
                    //Toast.makeText(getApplicationContext(),"no image",Toast.LENGTH_LONG).show();
                }
                else{
                    GlideUrl glideUrls = new GlideUrl(list.get(i).getCountryname().getImageurl().toString(),
                            new LazyHeaders.Builder()
                                    .addHeader("Authorization", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN))

                                    .build());

                    Glide.with(getApplicationContext())
                            .load(glideUrls)
                            .into(ivcountry);
                }

               }


            }
//                    for (int i = 0; i < list.size(); i++) {
                 //    placeorder("1", String.valueOf(list.get(0).getId().toString()), String.valueOf(list.get(0).getProdname()), String.valueOf(list.get(0).getImageurl()), getIntent().getStringExtra("getbuynowqty"), String.valueOf(list.get(0).getPrice()), String.valueOf(list.get(0).getPrice().toString()));

                     //   }
                     if (list != null) {


                     }

                 }
             }

             @Override
             public void onFailure(Call<List<ProfileResponses>> call, Throwable t) {
                 Log.e("onerrors",t.getMessage());
             }
         });
    }
    @SuppressLint("CheckResult")
    private void loadDatabanner() {

        // binding.progress.setVisibility(View.VISIBLE);
        Log.e("getProductMasterssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN));
        ApiClient.getApiClient().getbanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<BannerResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<BannerResponse>> response) {

                        Log.d("onSuccess: ",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<BannerResponse> list = response.body();
                            Log.e("getProduct", String.valueOf(list.size()));

                             uploadbanner(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("geterre", String.valueOf(e.getMessage()));

                    }
                });
    }

    public void uploadbanner(List<BannerResponse> list)
    {
        for (BannerResponse s : list) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.image(s.getBannerimgurl()).setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Log.d("dddddddsss",String.valueOf(s.getId()+" "+s.getBannername()));

                    if(s.getAndroidmove()==null){

                    }else{
                        if(s.getAndroidmove().equalsIgnoreCase("bestseller")){
                            Intent i=new Intent(getApplicationContext(), SellerBannerproductActivity.class);
                            startActivity(i);
                        }
                    }
                    if(s.getCatid()!=null && s.getSubcatid()!=null){
                        Intent i=new Intent(getApplicationContext(), SubCategoryProductActivity.class);
                       i.putExtra("userid", s.getSubcatid().toString());
                       i.putExtra("catid",s.getCatid().toString());
                       startActivity(i);
                      // Toast.makeText(getApplicationContext(),s.getSubcatid().toString()+"///"+s.getCatid().toString(),Toast.LENGTH_LONG).show();
                    }else if(s.getCatid()!=null){
                        Intent i=new Intent(getApplicationContext(), SubCategoryActivity.class);
                       i.putExtra("userid",s.getCatid().toString());
                        startActivity(i);
                    }

                    if(s.getProductid()==null){

                    }else {
                        Intent i=new Intent(getApplicationContext(), ProductDetailsActivity.class);
                        i.putExtra("userid",s.getProductid().toString());
                        startActivity(i);
                    }

                }
            });
            slider.addSlider(sliderView);
        }

        slider.movePrevPosition(false);
        slider.moveNextPosition(false);
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setClickable(true);
        slider.setFocusableInTouchMode(false);
        slider.setFocusable(false);
        slider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
    Log.d("ddddddd",String.valueOf(position));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        slider.getPagerIndicator().setDefaultIndicatorColor(Color.parseColor("#01309A"), Color.parseColor("#cecdcd"));
        // slider.setPresetTransformer(SliderLayout.Transformer.Default);
        //slider.setPresetIndicator(com.daimajia.slider.library.SliderLayout.PresetIndicators.Center_Bottom);
        slider.setDuration(8000);
    }
    protected void onStop() {

        slider.stopAutoCycle();

        super.onStop();
    }

}
