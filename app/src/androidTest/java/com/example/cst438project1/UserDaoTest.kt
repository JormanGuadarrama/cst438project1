package com.example.cst438project1

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.room.Room
import com.example.cst438project1.data.local.AppDatabase
import com.example.cst438project1.data.local.UserDao
import com.example.cst438project1.data.local.UserEntity
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.userDao()
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
        dao.insertUser(user)
        val fetched = dao.getUserByUsername("Random1")
        Assert.assertNotNull(fetched)
        Assert.assertEquals("Random1", fetched?.username)
        Assert.assertEquals("Random2", fetched?.password)
    }

    @Test
    fun updateRecentSearch() = runBlocking {
        val user = UserEntity(
            username = "Random1",
            password = "Random2",
            profileImage = android.R.drawable.ic_menu_camera,
            profileColor = 0xFF00FF00,
            recentSearch = ""
        )
        dao.insertUser(user)
        val inserted = dao.getUserByUsername("Random1")!!
        dao.updateRecentSearch(inserted.id, "A,B,C")
        val updated = dao.getUserByUsername("Random1")
        Assert.assertEquals("A,B,C", updated?.recentSearch)
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
        dao.insertUser(user)
        val inserted = dao.getUserByUsername("Random1")!!
        dao.updateUsername(inserted.id, "NewRandom")
        val updated = dao.getUserByUsername("NewRandom")
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
        dao.insertUser(user)
        val inserted = dao.getUserByUsername("Random1")!!
        dao.updatePassword(inserted.id, "NewPass")
        val updated = dao.getUserByUsername("Random1")
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
        dao.insertUser(user)
        val inserted = dao.getUserByUsername("Random1")!!
        dao.deleteUser(inserted.id)
        val deleted = dao.getUserByUsername("Random1")
        Assert.assertNull(deleted)
    }
}
