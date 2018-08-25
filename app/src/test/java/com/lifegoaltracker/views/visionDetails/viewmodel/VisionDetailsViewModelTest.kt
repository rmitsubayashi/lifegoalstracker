package com.lifegoaltracker.views.visionDetails.viewmodel

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
import com.lifegoaltracker.model.vision.*
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalRepository
import com.lifegoaltracker.repository.vision.VisionRepository
import com.lifegoaltracker.views.goalList.GoalListLiveDataHelper
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class VisionDetailsViewModelTest {
    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var goalRepository: GoalRepository
    @Mock
    lateinit var visionRepository: VisionRepository
    @Mock
    lateinit var goalListLiveDataHelper: GoalListLiveDataHelper
    @Mock
    lateinit var observer: Observer<List<VisionDetailsRecyclerViewItem>>

    lateinit var visionDetailsRecyclerViewList: VisionDetailsRecyclerViewList

    lateinit var viewModel: VisionDetailsViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        visionDetailsRecyclerViewList = VisionDetailsRecyclerViewList()
        viewModel = VisionDetailsViewModel(
                visionRepository, goalRepository,
                goalListLiveDataHelper, visionDetailsRecyclerViewList)
    }

    @Test
    fun getRecyclerViewList_shouldGet(){
        val vision1 = Vision(ID(0), VisionUserFields("title","desc","reason", Priority.HIGH),
                VisionProperties(0), VisionStatus())
        val vision : MutableLiveData<Vision> = MutableLiveData()
        vision.value = vision1
        Mockito.`when`(visionRepository.getVision(ID(0))).thenReturn(vision)

        val goal1 = Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goalList : MediatorLiveData<List<Goal>> = MediatorLiveData()
        goalList.value = arrayListOf(goal1)
        Mockito.`when`(goalListLiveDataHelper.getAllVisionGoalsLiveData(goalRepository, ID(0)))
                .thenReturn(goalList)
        viewModel.setVisionID(ID(0))

        val liveData : LiveData<List<VisionDetailsRecyclerViewItem>> = viewModel.fetchRecyclerViewItems()
        liveData.observeForever(observer)
        assertNotNull(liveData.value)
        val duplicateList = VisionDetailsRecyclerViewList()
        duplicateList.set(vision1, listOf(goal1))
        assertEquals(duplicateList.items, liveData.value)
    }
}