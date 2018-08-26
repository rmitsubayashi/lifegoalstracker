package com.lifegoaltracker.repository.vision

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.GoalProperties
import com.lifegoaltracker.model.goal.GoalStatus
import com.lifegoaltracker.model.goal.GoalUserFields
import com.lifegoaltracker.model.goal.dueDate.DueDate
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Year
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.model.vision.*
import com.lifegoaltracker.repository.Database
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalDao
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class VisionDaoTest {
    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    private lateinit var database: Database
    private lateinit var visionDao: VisionDao
    private lateinit var goalDao: GoalDao

    @Mock
    private lateinit var observerVisions: Observer<List<Vision>>
    @Mock
    private lateinit var observerVision: Observer<Vision>
    @Mock
    private lateinit var observerVisionName: Observer<List<VisionName>>
    @Mock
    private lateinit var observerGoalDao: Observer<Goal>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, Database::class.java)
                .allowMainThreadQueries()
                .build()
        visionDao = database.visionDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun query_exists_shouldReturn(){
        val vision1 = Vision(ID(1), VisionUserFields("title","desc","reason", Priority.LOW),
                VisionProperties(0), VisionStatus())
        visionDao.postVision(vision1)
        visionDao.queryVisions().observeForever(observerVisions)
        Mockito.verify(observerVisions).onChanged(listOf(vision1))
    }

    @Test
    fun query_empty_shouldReturnEmpty(){
        visionDao.queryVisions().observeForever(observerVisions)
        Mockito.verify(observerVisions).onChanged(emptyList())
    }

    @Test
    fun queryVisionNames_shouldReturnVisionName(){
        val vision1 = Vision(ID(1), VisionUserFields("title","desc","reason", Priority.LOW),
                VisionProperties(0), VisionStatus())
        visionDao.postVision(vision1)
        visionDao.queryVisionNames().observeForever(observerVisionName)
        val visionName1 = VisionName(ID(1),"title")
        Mockito.verify(observerVisionName).onChanged(listOf(visionName1))
    }

    @Test
    fun querySpecificVision_exists_shouldReturn(){
        val vision1 = Vision(ID(1), VisionUserFields("title","desc","reason", Priority.LOW),
                VisionProperties(0), VisionStatus())
        visionDao.postVision(vision1)
        visionDao.queryVision(ID(1)).observeForever(observerVision)
        Mockito.verify(observerVision).onChanged(vision1)
    }

    @Test
    fun querySpecificVision_doesNotExist_shouldReturnNull(){
        val vision1 = Vision(ID(1), VisionUserFields("title","desc","reason", Priority.LOW),
                VisionProperties(0), VisionStatus())
        visionDao.postVision(vision1)
        visionDao.queryVision(ID(0)).observeForever(observerVision)
        Mockito.verify(observerVision).onChanged(null)
    }

    @Test
    fun insert_nullableFields_shouldReturnNullFields(){
        val vision1 = Vision(ID(1), VisionUserFields("title","desc","reason", Priority.LOW),
                VisionProperties(0), VisionStatus())
        visionDao.postVision(vision1)
        visionDao.queryVision(ID(1)).observeForever(observerVision)
        Mockito.verify(observerVision).onChanged(vision1)
    }

    @Test
    fun remove_canRemove_remove(){
        val vision1 = Vision(ID(1), VisionUserFields("title","desc","reason", Priority.LOW),
                VisionProperties(0), VisionStatus())
        visionDao.postVision(vision1)
        visionDao.remove(vision1)
        visionDao.queryVision(ID(1)).observeForever(observerVision)
        Mockito.verify(observerVision).onChanged(null)
    }

    @Test
    fun remove_hasGoal_removesGoal(){
        val vision1 = Vision(ID(1), VisionUserFields("title","desc","reason", Priority.LOW),
                VisionProperties(0), VisionStatus())
        visionDao.postVision(vision1)
        val goal1 = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(1)),
                GoalStatus()
        )
        goalDao = database.goalDao()
        goalDao.postGoal(goal1)
        visionDao.remove(vision1)
        goalDao.queryByID(ID(0)).observeForever(observerGoalDao)
        val goal1Deleted = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(1)),
                GoalStatus(false, true)
        )
        Mockito.verify(observerGoalDao).onChanged(goal1Deleted)
    }
}