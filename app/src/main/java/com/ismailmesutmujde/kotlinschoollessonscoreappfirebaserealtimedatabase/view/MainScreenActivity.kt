package com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.R
import com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.adapter.LessonScoresRecyclerViewAdapter
import com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlinschoollessonscoreappfirebaserealtimedatabase.model.LessonScores


class MainScreenActivity : AppCompatActivity() {

    private lateinit var bindingMainScreen : ActivityMainScreenBinding
    private lateinit var lessonScoreList:ArrayList<LessonScores>
    private lateinit var adapter: LessonScoresRecyclerViewAdapter
    private lateinit var refLessonScores : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainScreen = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = bindingMainScreen.root
        setContentView(view)

        bindingMainScreen.toolbar.title = "Lesson Score App"
        bindingMainScreen.toolbar.subtitle = "Average : 0"
        setSupportActionBar(bindingMainScreen.toolbar)

        bindingMainScreen.recyclerView.setHasFixedSize(true)
        bindingMainScreen.recyclerView.layoutManager = LinearLayoutManager(this)

        val db = FirebaseDatabase.getInstance()
        refLessonScores = db.getReference("lessonscores")

        lessonScoreList = ArrayList()
        /*
        val l1 = LessonScores(1,"History", 40, 80)
        val l2 = LessonScores(2,"Chemistry", 70, 50)
        val l3 = LessonScores(3,"Physics",30,60)

        lessonScoreList.add(l1)
        lessonScoreList.add(l2)
        lessonScoreList.add(l3)*/
        adapter = LessonScoresRecyclerViewAdapter(this, lessonScoreList)
        bindingMainScreen.recyclerView.adapter = adapter

        allLessonScores()

        bindingMainScreen.fab.setOnClickListener {
            val intent = Intent(this@MainScreenActivity, LessonScoreRecordScreenActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun allLessonScores() {
        refLessonScores.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                lessonScoreList.clear()
                var sum = 0

                for (c in snapshot.children) {
                    val lessonScore = c.getValue(LessonScores::class.java)
                    if (lessonScore != null) {
                        lessonScore.lesson_id = c.key
                        lessonScoreList.add(lessonScore)
                        sum += (lessonScore.score1!! + lessonScore.score2!!) / 2
                    }
                }
                adapter.notifyDataSetChanged()

                if (sum != 0) {
                    bindingMainScreen.toolbar.subtitle = "Average : ${sum/lessonScoreList.size}"
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}