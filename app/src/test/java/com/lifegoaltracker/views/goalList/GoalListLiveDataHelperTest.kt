package com.lifegoaltracker.views.goalList

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
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
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalQueryParameters
import com.lifegoaltracker.repository.goal.GoalRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GoalListLiveDataHelperTest {
    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    private lateinit var goalListLiveDataHelper: GoalListLiveDataHelper
    @Mock
    lateinit var queryHelper: GoalListQueryHelper
    @Mock
    lateinit var repository: GoalRepository
    @Mock
    lateinit var observer: Observer<List<Goal>>
    
    private val arbitraryDate = Date(Year(2018), Month.JULY, WeekOfMonth.WEEK_ONE)

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        goalListLiveDataHelper = GoalListLiveDataHelper(queryHelper)
    }

    @Test
    fun getGoals(){
        val weekQuery = GoalQueryParameters(arbitraryDate, arbitraryDate, GoalSpan.ONE_WEEK)
        val weekGoal = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val weekLiveData = MutableLiveData<List<Goal>>()
        weekLiveData.value = (listOf(weekGoal))
        val date = Date(Year(2018), Month.JANUARY, null)
        Mockito.`when`(queryHelper.getWeekGoalsQuery(date)).thenReturn(weekQuery)
        Mockito.`when`(repository.getGoals(weekQuery)).thenReturn(weekLiveData)

        val monthQuery = GoalQueryParameters(arbitraryDate, arbitraryDate, GoalSpan.ONE_MONTH)
        val monthGoal = Goal(ID(1),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, null), GoalSpan.ONE_MONTH)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val monthLiveData = MutableLiveData<List<Goal>>()
        monthLiveData.value = (listOf(monthGoal))
        Mockito.`when`(queryHelper.getMonthGoalsQuery(date)).thenReturn(monthQuery)
        Mockito.`when`(repository.getGoals(monthQuery)).thenReturn(monthLiveData)

        val threeMonthQuery = GoalQueryParameters(arbitraryDate, arbitraryDate, GoalSpan.THREE_MONTHS)
        Mockito.`when`(queryHelper.getThreeMonthGoalsQuery(date)).thenReturn(threeMonthQuery)
        Mockito.`when`(repository.getGoals(threeMonthQuery)).thenReturn(MutableLiveData<List<Goal>>())

        val yearQuery = GoalQueryParameters(arbitraryDate, arbitraryDate, GoalSpan.ONE_YEAR)
        Mockito.`when`(queryHelper.getYearGoalsQuery(date)).thenReturn(yearQuery)
        Mockito.`when`(repository.getGoals(yearQuery)).thenReturn(MutableLiveData<List<Goal>>())

        val liveData = goalListLiveDataHelper.getAllGoalsLiveData(repository, date)
        liveData.observeForever(observer)
        assertTrue(liveData.value!! == listOf(weekGoal, monthGoal))
    }
}