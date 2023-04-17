package com.ajayvamsee.alarm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ajayvamsee.alarm.databinding.ActivityMainBinding
import com.zubair.alarmmanager.builder.AlarmBuilder
import com.zubair.alarmmanager.enums.AlarmType
import com.zubair.alarmmanager.interfaces.IAlarmListener
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(),IAlarmListener {

    private var builder: AlarmBuilder? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        builder = AlarmBuilder().with(this)
            .setTimeInMilliSeconds(TimeUnit.SECONDS.toMillis(1))
            .setId("UPDATE_INFO_SYSTEM_SERVICE")
            .setAlarmType(AlarmType.REPEAT)

        binding.btnStart.setOnClickListener {
            Log.i("Alarm", "you clicked on start button")
            builder?.setAlarm()
        }

        binding.btnStop.setOnClickListener {
            builder?.removeListener(this)
        }
    }

    override fun onResume() {
        super.onResume()
        builder?.addListener(this)
    }
    override fun onPause() {
        super.onPause()
        builder?.removeListener(this)
    }

    override fun perform(context: Context, intent: Intent) {
        Log.i("Alarm", "Do your work here")
    }

}