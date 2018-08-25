package com.lifegoaltracker.repository.goal

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
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
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.model.vision.VisionProperties
import com.lifegoaltracker.model.vision.VisionStatus
import com.lifegoaltracker.model.vision.VisionUserFields
import com.lifegoaltracker.repository.Database
import com.lifegoaltracker.repository.ID
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class GoalDaoTest {
    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    private lateinit var database: Database
    private lateinit var goalDao: GoalDao

    @Mock
    private lateinit var observer: Observer<List<Goal>>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, Database::class.java)
                .allowMainThreadQueries()
                .build()
        goalDao = database.goalDao()
        //since goals are linked to the vision by foreign key constraint,
        // we need a vision that the goals are linked to
        //(goals can't exist without a corresponding vision)
        val vision = Vision(ID(0), VisionUserFields("title","desc","reason"),
                VisionProperties(0), VisionStatus())
        database.visionDao().postVision(vision)
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun queryHistory_exists_shouldReturn(){
        val goal = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus(true, false)
        )
        goalDao.postGoal(goal)
        goalDao.queryCompletedGoals().observeForever(observer)
        verify(observer).onChanged(listOf(goal))
    }

    @Test
    fun queryHistory_doesNotExist_shouldNotReturn(){
        val goal = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus(false, false)
        )
        goalDao.postGoal(goal)
        goalDao.queryCompletedGoals().observeForever(observer)
        verify(observer).onChanged(emptyList())
    }

    @Test
    fun queryGoals_exists_shouldReturn(){
        val goal = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        goalDao.postGoal(goal)
        goalDao.queryGoals(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE),
                Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_TWO),
                GoalSpan.ONE_WEEK).observeForever(observer)
        verify(observer).onChanged(listOf(goal))
    }

    @Test
    fun queryGoals_doesNotExist_shouldNotReturn(){
        val goal = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus(true, false)
        )
        goalDao.postGoal(goal)
        goalDao.queryGoals(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_TWO),
                Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_THREE),
                GoalSpan.ONE_WEEK).observeForever(observer)
        verify(observer).onChanged(emptyList())
    }

    @Test
    fun queryVisionGoals_exists_shouldReturn(){
        val goal = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        goalDao.postGoal(goal)
        goalDao.queryVisionGoals(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE),
                Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_TWO),
                GoalSpan.ONE_WEEK, ID(0)).observeForever(observer)
        verify(observer).onChanged(listOf(goal))
    }

    @Test
    fun queryVisionGoals_visionDoesNotExist_shouldNotReturn(){
        val goal = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        goalDao.postGoal(goal)
        goalDao.queryVisionGoals(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE),
                Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_TWO),
                GoalSpan.ONE_WEEK, ID(1)).observeForever(observer)
        verify(observer).onChanged(emptyList())
    }

    @Test
    fun queryGoals_existsButIsDeleted_shouldNotReturn(){
        val goal = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus(false, true)
        )
        goalDao.postGoal(goal)
        goalDao.queryGoals(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE),
                Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_TWO),
                GoalSpan.ONE_WEEK).observeForever(observer)
        verify(observer).onChanged(emptyList())
    }

    @Test
    fun queryGoalHistory_existsButIsDeleted_shouldNotReturn(){
        val goal = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus(true, true)
        )
        goalDao.postGoal(goal)
        goalDao.queryCompletedGoals().observeForever(observer)
        verify(observer).onChanged(emptyList())
    }
}

