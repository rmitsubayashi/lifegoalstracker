package com.lifegoaltracker.views.goalHistory.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
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
import com.lifegoaltracker.repository.vision.mockGetVisionNames
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
class GoalHistoryViewModelTest {

    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var goalRepository: GoalRepository
    @Mock
    lateinit var visionRepository: VisionRepository

    @Mock
    lateinit var observer: Observer<List<Goal>>

    lateinit var viewModel: GoalHistoryViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = GoalHistoryViewModel(goalRepository, visionRepository)
    }

    @Test
    fun getVisionNames_shouldGet(){
        val visionName1 = VisionName(ID(1),"vision 1")
        val visionNames = arrayListOf(visionName1)
        visionRepository.mockGetVisionNames(visionNames)
        viewModel.assertVisionNamesFetched(visionNames)
    }

    @Test
    fun filterList_filterable_shouldFilter(){
        val goal1 = createGoalWithVisionID(0)
        val goal2 = createGoalWithVisionID(1)
        val goalList : MutableLiveData<List<Goal>> = MutableLiveData()
        goalList.value = arrayListOf(goal1, goal2)

        Mockito.`when`(goalRepository.getGoalHistory()).thenReturn(goalList)
        val liveData : LiveData<List<Goal>> = viewModel.fetchGoalList()
        liveData.observeForever(observer)
        assertNotNull(liveData.value)
        assertEquals(2, liveData.value?.size)
        viewModel.selectByVisionID(ID(1))
        assertEquals(1, liveData.value?.size )
        assertEquals(ID(1), liveData.value?.get(0)?.id)
    }

    @Test
    fun filterList_filterTwice_shouldFilter() {
        val goal1 = createGoalWithVisionID(0)
        val goal2 = createGoalWithVisionID(1)
        val goalList: MutableLiveData<List<Goal>> = MutableLiveData()
        goalList.value = arrayListOf(goal1, goal2)

        Mockito.`when`(goalRepository.getGoalHistory()).thenReturn(goalList)
        val liveData: LiveData<List<Goal>> = viewModel.fetchGoalList()
        liveData.observeForever(observer)
        viewModel.selectByVisionID(ID(1))
        assertEquals(1, liveData.value?.size)
        assertEquals(ID(1), liveData.value?.get(0)?.properties?.visionID)
        viewModel.selectByVisionID(ID(0))
        assertEquals(1, liveData.value?.size)
        assertEquals(ID(0), liveData.value?.get(0)?.properties?.visionID)
    }

    @Test
    fun getList_listNotLoaded_returnsEmptyList(){
        val goalList : MutableLiveData<List<Goal>> = MutableLiveData()
        assertNull(goalList.value)
        Mockito.`when`(goalRepository.getGoalHistory()).thenReturn(goalList)
        val liveData : LiveData<List<Goal>> = viewModel.fetchGoalList()
        liveData.observeForever(observer)
        assertNotNull(liveData.value)
        assertEquals(0, liveData.value?.size)
    }

    @Test
    fun filterList_listNotLoaded_returnsEmptyList(){
        val goalList : MutableLiveData<List<Goal>> = MutableLiveData()
        assertNull(goalList.value)
        Mockito.`when`(goalRepository.getGoalHistory()).thenReturn(goalList)
        val liveData : LiveData<List<Goal>> = viewModel.fetchGoalList()
        liveData.observeForever(observer)
        viewModel.selectByVisionID(ID(0))
        assertEquals(0, liveData.value?.size )
        assertNotNull(liveData.value)
        assertEquals(0,liveData.value?.size)
    }

    private fun createGoalWithVisionID(id: Long): Goal{
        return Goal(ID(1),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2018), Month.AUGUST,WeekOfMonth.WEEK_ONE),GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(id)),
                GoalStatus()
        )
    }
}