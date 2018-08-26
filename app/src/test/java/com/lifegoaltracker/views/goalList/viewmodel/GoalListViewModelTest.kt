package com.lifegoaltracker.views.goalList.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
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
import com.lifegoaltracker.model.vision.VisionName
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalRepository
import com.lifegoaltracker.repository.vision.VisionRepository
import com.lifegoaltracker.views.goalList.GoalListLiveDataHelper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GoalListViewModelTest {

    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var goalListLiveDataHelper: GoalListLiveDataHelper
    @Mock
    lateinit var visionRepository: VisionRepository
    @Mock
    lateinit var goalRepository: GoalRepository
    lateinit var goalListRecyclerViewList: GoalListRecyclerViewList

    @Mock
    lateinit var observer: Observer<List<GoalListRecyclerViewItem>>

    lateinit var viewModel: GoalListViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        goalListRecyclerViewList = GoalListRecyclerViewList()
        viewModel = GoalListViewModel(visionRepository, goalRepository,
                goalListLiveDataHelper, goalListRecyclerViewList)
    }

    @Test
    fun getVisionNames_shouldGet(){
        val visionName1 = VisionName(ID(1),"vision 1")
        val visionNames : MutableLiveData<List<VisionName>> = MutableLiveData()
        visionNames.value = arrayListOf(visionName1)

        Mockito.`when`(visionRepository.getVisionNames()).thenReturn(visionNames)
        val liveData : LiveData<List<VisionName>> = viewModel.fetchVisionNames()
        assertNotNull(liveData.value)
        assertEquals(1, liveData.value?.size)
    }

    @Test
    fun filterList_filterable_shouldFilter(){
        val goal1 = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal2 = Goal(ID(1),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(1)),
                GoalStatus()
        )
        val goalList : MediatorLiveData<List<Goal>> = MediatorLiveData()
        goalList.value = arrayListOf(goal1, goal2)

        Mockito.`when`(goalListLiveDataHelper.getAllGoalsLiveData(goalRepository))
                .thenReturn(goalList)
        val liveData : LiveData<List<GoalListRecyclerViewItem>> = viewModel.fetchGoalList()
        liveData.observeForever(observer)
        assertNotNull(liveData.value)
        assertTrue(liveData.value!!.contains(
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.GOAL,goal2,null)
        ))
        assertTrue(liveData.value!!.contains(
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.GOAL,goal1,null)
        ))
        viewModel.selectByVisionID(ID(1))
        assertTrue(liveData.value!!.contains(
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.GOAL,goal2,null)
        ))
        assertFalse(liveData.value!!.contains(
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.GOAL,goal1,null)
        ))
    }

    @Test
    fun filterList_filterTwice_shouldFilter(){
        val goal1 = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal2 = Goal(ID(1),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(1)),
                GoalStatus()
        )
        val goalList : MediatorLiveData<List<Goal>> = MediatorLiveData()
        goalList.value = arrayListOf(goal1, goal2)
        Mockito.`when`(goalListLiveDataHelper.getAllGoalsLiveData(goalRepository))
                .thenReturn(goalList)
        val liveData : LiveData<List<GoalListRecyclerViewItem>> = viewModel.fetchGoalList()
        liveData.observeForever(observer)
        viewModel.selectByVisionID(ID(0))
        assertFalse(liveData.value!!.contains(
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.GOAL,goal2,null)
        ))
        assertTrue(liveData.value!!.contains(
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.GOAL,goal1,null)
        ))
        viewModel.selectByVisionID(ID(1))
        assertTrue(liveData.value!!.contains(
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.GOAL,goal2,null)
        ))
        assertFalse(liveData.value!!.contains(
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.GOAL,goal1,null)
        ))
    }

    @Test
    fun getList_listNotLoaded_returnsLoading(){
        val goalList : MediatorLiveData<List<Goal>> = MediatorLiveData()
        Mockito.`when`(goalListLiveDataHelper.getAllGoalsLiveData(goalRepository))
                .thenReturn(goalList)
        val liveData : LiveData<List<GoalListRecyclerViewItem>> = viewModel.fetchGoalList()
        liveData.observeForever(observer)
        assertEquals(GoalListRecyclerViewItemType.LOADING, liveData.value?.get(0)?.itemType)
    }

    @Test
    fun filterList_listNotLoaded_returnsLoading(){
        val goalList : MediatorLiveData<List<Goal>> = MediatorLiveData()
        assertNull(goalList.value)
        Mockito.`when`(goalListLiveDataHelper.getAllGoalsLiveData(goalRepository))
                .thenReturn(goalList)
        val liveData : LiveData<List<GoalListRecyclerViewItem>> = viewModel.fetchGoalList()
        liveData.observeForever(observer)
        viewModel.selectByVisionID(ID(0))
        assertEquals(GoalListRecyclerViewItemType.LOADING, liveData.value?.get(0)?.itemType)
    }

    @Test
    fun filterList_allFilterable_returnsEmptyList(){
        val goal1 = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(1)),
                GoalStatus()
        )
        val goal2 = Goal(ID(1),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(1)),
                GoalStatus()
        )
        val goalList : MediatorLiveData<List<Goal>> = MediatorLiveData()
        goalList.value = arrayListOf(goal1, goal2)

        Mockito.`when`(goalListLiveDataHelper.getAllGoalsLiveData(goalRepository))
                .thenReturn(goalList)
        val liveData : LiveData<List<GoalListRecyclerViewItem>> = viewModel.fetchGoalList()
        liveData.observeForever(observer)
        viewModel.selectByVisionID(ID(0))
        assertEquals(GoalListRecyclerViewItemType.EMPTY, liveData.value?.get(0)?.itemType)
    }
}