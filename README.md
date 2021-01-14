### Reference

[https://gogorchg.tistory.com/entry/Android-Preferencefragment-deprecated](https://gogorchg.tistory.com/entry/Android-Preferencefragment-deprecated)

### Gradle

```kotlin
implementation "androidx.preference:preference-ktx:1.1.1"
```

### Define `res/xml/preferences.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">

    <PreferenceCategory
        android:title="Login Information">
        <!-- EditText Dialog-->
        <EditTextPreference
            android:key="edt_username"
            android:title="Username"
            android:summary="please enter your username here!"
            android:dialogTitle="Enter your name"/>
        <!-- EditText Dialog-->
        <EditTextPreference
            android:key="edt_password"
            android:title="Password"
            android:summary="Enter your password"
            android:dialogTitle="Enter your password"/>
    </PreferenceCategory>

    <PreferenceCategory
        android:title="Settings">

        <!-- CheckBox-->
        <CheckBoxPreference
            android:key="chkBox"
            android:title="Keep me Logged in"
            android:summary="On/Off"
            android:defaultValue="true"/>

        <!-- List -->
        <ListPreference
            android:key="list"
            android:title=""
            android:summary="Choose your phone language here"
            android:entries="@array/ListLanguages_Labels"
            android:entryValues="@array/ListLanguages_Values"/>

    </PreferenceCategory>

</PreferenceScreen>
```

- Prepare for array values in `res/values/array.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    
    <string-array name="ListLanguages_Labels">
        <item>Dari/Farsi</item>
        <item>Pashto</item>
        <item>Engligh</item>
        <item>Hindi</item>
        <item>Urdu</item>
    </string-array>
    
    <string-array name="ListLanguages_Values">
        <item>Dari/Farsi</item>
        <item>Pashto</item>
        <item>Engligh</item>
        <item>Hindi</item>
        <item>Urdu</item>
    </string-array>
    
</resources>
```

### Define theme in `res/values/themes`

```xml
<style name="AppCompatTheme" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
        <item name="preferenceTheme">@style/PreferenceThemeOverlay</item>
  </style>
```

```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Theme.PreferenceScreen" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
        <!-- Primary brand color. -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/white</item>
        <!-- Secondary brand color. -->
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_700</item>
        <item name="colorOnSecondary">@color/black</item>
        <!-- Status bar color. -->
        <item name="android:statusBarColor" tools:targetApi="l">?attr/colorPrimaryVariant</item>
        <!-- Customize your theme here. -->
    </style>

    <style name="AppCompatTheme" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
        <item name="preferenceTheme">@style/PreferenceThemeOverlay</item>
    </style>

</resources>
```

### Prepare for Preference Screen

```kotlin
class MyPreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, SettingFragment())
            .commit()
    }

    class SettingFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.preferences)

        }
    }

}
```

### Get Preference

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show_information.setOnClickListener {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val username = sharedPreferences.getString("edt_username", "")
            val password = sharedPreferences.getString("edt_password", "")
            val checked = sharedPreferences.getBoolean("chkBox", false)
            val list = sharedPreferences.getString("list", "") //list도 string 으로 가져온다.
            show_text_data.text = "$username, $password $checked $list"
        }

        btn_store_information.setOnClickListener {
            val intent = Intent(this@MainActivity, MyPreferenceActivity::class.java)
            startActivity(intent)
        }

    }

}
```