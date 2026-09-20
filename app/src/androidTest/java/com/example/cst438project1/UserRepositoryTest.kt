package com.example.cst438project1

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.room.Room
import com.example.cst438project1.data.local.AppDatabase
import com.example.cst438project1.data.local.UserEntity
import com.example.cst438project1.data.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {
    private lateinit var db: AppDatabase
    private lateinit var repo: UserRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repo = UserRepository(db.userDao())
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertAndFetchUser() = runBlocking {
        val user = UserEntity(
            username = "Random1",
            password = "Random2",
            profileImage = android.R.drawable.ic_menu_camera,
            profileColor = 0xFF00FF00,
            recentSearch = ""
        )
        repo.insertUser(user)
        val fetched = repo.getUserByUsername("Random1")
        Assert.assertNotNull(fetched)
        Assert.assertEquals("Random1", fetched?.username)
    }

    @Test
    fun updateUsername() = runBlocking {
        val user = UserEntity(
            username = "Random1",
            password = "Random2",
            profileImage = android.R.drawable.ic_menu_camera,
            profileColor = 0xFF00FF00,
            recentSearch = ""
        )

        repo.insertUser(user)
        val inserted = repo.getUserByUsername("Random1")!!
        repo.updateUsername(inserted.id, "NewRandom")
        val updated = repo.getUserByUsername("NewRandom")
        Assert.assertNotNull(updated)
        Assert.assertEquals("NewRandom", updated?.username)
    }

    @Test
    fun updatePassword() = runBlocking {
        val user = UserEntity(
            username = "Random1",
            password = "Random2",
            profileImage = android.R.drawable.ic_menu_camera,
            profileColor = 0xFF00FF00,
            recentSearch = ""
        )
        repo.insertUser(user)
        val inserted = repo.getUserByUsername("Random1")!!
        repo.updatePassword(inserted.id, "NewPass")
        val updated = repo.getUserByUsername("Random1")
        Assert.assertEquals("NewPass", updated?.password)
    }

    @Test
    fun deleteUser() = runBlocking {
        val user = UserEntity(
            username = "Random1",
            password = "Random2",
            profileImage = android.R.drawable.ic_menu_camera,
            profileColor = 0xFF00FF00,
            recentSearch = ""
        )
        repo.insertUser(user)
        val inserted = repo.getUserByUsername("Random1")!!
        repo.deleteUser(inserted.id)
        val deleted = repo.getUserByUsername("Random1")
        Assert.assertNull(deleted)
    }
}
