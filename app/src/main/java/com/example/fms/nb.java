package com.example.fms;

public class nb {
//
//    public class Admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//
//        String B = "Students";
//        String D = "Add Students";
//        TextView Heading;
//        private DrawerLayout drawer;
//        NavigationView navigationView;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//
//            Toolbar toolbar =findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
//            drawer = findViewById(R.id.drawer_layout);
//            Heading = findViewById(R.id.heading);
//            navigationView = findViewById(R.id.nav_view);
//            navigationView.setNavigationItemSelectedListener(this);
//            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//            drawer.addDrawerListener(toggle);
//            toggle.syncState();
//            if (savedInstanceState == null) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VStudents()).commit();
//                navigationView.setCheckedItem(R.id.nav_students);
//                Heading.setText(B);
//            }
//        }
//
//
//
//        public void onBackPressed() {
//            if (drawer.isDrawerOpen( GravityCompat.START)) {
//                drawer.closeDrawer(GravityCompat.START);
//            } else {
//                super.onBackPressed();
//            }
//        }
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.nav_add_students :
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Add_Students()).commit();
//                    Heading.setText(D);
//                    break;
//                case R.id.nav_logout :
//                    startActivity(new Intent(getApplicationContext(), ALogin.class));
//                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//                    finish();
//                    break;
//                case R.id.nav_students :
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VStudents()).commit();
//                    Heading.setText(B);
//                    break;
//            }
//            drawer.closeDrawer(GravityCompat.START);
//            return true;
//        }


//<item
//    android:id="@+id/nav_students"
//    android:icon="@drawable/ic_st"
//    android:title="@string/menu_home" />
//        <item
//    android:id="@+id/nav_add_students"
//    android:icon="@drawable/ic_ad"
//    android:title="@string/menu_gallery" />
//        <item
//    android:id="@+id/nav_logout"
//    android:icon="@drawable/ic_log"
//    android:title="@string/menu_slideshow" />
//
//

}
    //Date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        getContext(),
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                        mDateSetListener,
//                        year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });
//
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                Log.d(TAG, "onDateSet: yyy/mm/dd: " + year+ "-" + month + "-" + day);
//
//                String date =  year+ "-" + month + "-" + day;
//                Date.setText(date);
//
//            }
//        };



//    DatePickerDialog.OnDateSetListener mDateSetListener;
//    private static final String TAG = "Fees";



//
//<Button
//                android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:id="@+id/btnupdate"
//                        android:text="Update"></Button>
//<Button
//                android:layout_width="match_parent"
//                        android:layout_height="wrap_content"
//                        android:id="@+id/btndel"
//                        android:text="DELETE"></Button>Button