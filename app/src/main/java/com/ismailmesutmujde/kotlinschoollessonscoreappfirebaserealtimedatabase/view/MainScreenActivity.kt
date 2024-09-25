package com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.R
import com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.adapter.LessonScoresRecyclerViewAdapter
import com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.model.LessonScores


class MainScreenActivity : AppCompatActivity() {

    private lateinit var bindingMainScreen : ActivityMainScreenBinding
    private lateinit var lessonScoreList:ArrayList<LessonScores>
    private lateinit var adapter: LessonScoresRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainScreen = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = bindingMainScreen.root
        setContentView(view)

        bindingMainScreen.toolbar.title = "Lesson Score App"
        setSupportActionBar(bindingMainScreen.toolbar)

        bindingMainScreen.recyclerView.setHasFixedSize(true)
        bindingMainScreen.recyclerView.layoutManager = LinearLayoutManager(this)

        lessonScoreList = ArrayList()

        val l1 = LessonScores(1,"History", 40, 80)
        val l2 = LessonScores(2,"Chemistry", 70, 50)
        val l3 = LessonScores(3,"Physics",30,60)

        lessonScoreList.add(l1)
        lessonScoreList.add(l2)
        lessonScoreList.add(l3)

        adapter = LessonScoresRecyclerViewAdapter(this, lessonScoreList)
        bindingMainScreen.recyclerView.adapter = adapter


        bindingMainScreen.fab.setOnClickListener {
            val intent =
                Intent(this@MainScreenActivity, LessonScoreRecordScreenActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}