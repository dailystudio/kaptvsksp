package com.dailystudio.codegen.kapt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.dailystudio.codegen.kapt.core.db.User
import com.dailystudio.codegen.kapt.core.db.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    companion object {
        val LAST_NAMES = arrayOf(
            "Kelley", "Carter", "Salazar", "Osborne", "Santiago",
            "Luna", "Wilson", "Craig", "Willis", "Abeln"
        )

        val FIRST_NAMES = arrayOf(
            "Tanya", "Miriam", "Julie", "Leona", "Mabel",
            "Emelia", "Sofia", "Haleema", "Francis", "Elin"
        )

        val RANDOM = Random(System.currentTimeMillis())
        const val NAMES_COUNT = 50
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateUsers()
    }


    private fun generateUsers() {
        lifecycleScope.launch(Dispatchers.IO) {
            val database = UserDatabase.getDatabase(this@MainActivity)

            database.clearAllTables()

            for (i in 0 until NAMES_COUNT) {
                val fIndex = RANDOM.nextInt(FIRST_NAMES.size)
                val lIndex = RANDOM.nextInt(LAST_NAMES.size)

                val user = User(0,
                    FIRST_NAMES[fIndex],
                    LAST_NAMES[lIndex])

                database.userDao().insert(user)
                delay(50)
            }
        }
    }

}
