
package com.paigesoftware.preferencescreen

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import java.text.SimpleDateFormat
import java.util.*

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

            val preference = findPreference<SwitchPreference>("push")

            preference?.summary = "Hello World"

            preference?.setOnPreferenceClickListener {
                it.summary = "voila!"
                true
            }

            preference?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { myPreference, newValue ->

                val isChecked = newValue as Boolean
                if(isChecked) {
                    showTimePicker(preference!!)
                    println("Is Checked")
                } else {
                    println("Is not checked")
                }
                true
            }

        }


        private fun showTimePicker(preference: Preference) {
            val calendar = Calendar.getInstance()
            val hours = calendar.get(Calendar.HOUR_OF_DAY)
            val minutes = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(requireContext(), R.style.ThemeOverlay_MaterialComponents_Dialog, object: TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    val calendarResult = Calendar.getInstance()
                    calendarResult.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendarResult.set(Calendar.MINUTE, minute)
                    calendarResult.timeZone = TimeZone.getDefault()
                    val simpleDateFormat = SimpleDateFormat("k:mm a", Locale.getDefault())
                    val time: String = simpleDateFormat.format(calendarResult.time)

                    preference.summary = time

                }

            }, hours, minutes, false)

            timePickerDialog.show()

        }

    }


}