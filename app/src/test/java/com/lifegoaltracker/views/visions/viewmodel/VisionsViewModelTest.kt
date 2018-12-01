package com.lifegoaltracker.views.visions.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.lifegoaltracker.model.vision.*
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.vision.VisionRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class VisionsViewModelTest {
    @Rule
    @JvmField
    var instantExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var visionRepository: VisionRepository

    lateinit var viewModel: VisionsViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = VisionsViewModel(visionRepository)
    }

    @Test
    fun getVisions_shouldGet(){
        val vision1 = Vision(ID(0), VisionUserFields("title","desc","reason", Priority.HIGH),
                VisionProperties(0), VisionStatus())
        val visions : MutableLiveData<List<Vision>> = MutableLiveData()
        visions.value = listOf(vision1)
        Mockito.`when`(visionRepository.getVisions()).thenReturn(visions)
        //since this is a variable, re-initialize so we can properly mock
        viewModel = VisionsViewModel(visionRepository)
        val liveData = viewModel.visionsList
        assertEquals(listOf(vision1), liveData.value)
    }
}