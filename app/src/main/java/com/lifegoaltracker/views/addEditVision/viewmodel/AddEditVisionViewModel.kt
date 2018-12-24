package com.lifegoaltracker.views.addEditVision.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lifegoaltracker.model.vision.*
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.vision.VisionRepository
import com.lifegoaltracker.utils.date.DateGenerator
import com.lifegoaltracker.utils.uiDisplay.PriorityConverter
import javax.inject.Inject

class AddEditVisionViewModel
@Inject constructor(private val repository: VisionRepository,
                    private val dateGenerator: DateGenerator): ViewModel() {
    val titleInput = MutableLiveData<String>()
    val descriptionInput = MutableLiveData<String>()
    val reasonInput = MutableLiveData<String>()
    val priorityToggle = MutableLiveData<Boolean>()

    private val _inputErrorMessage = MutableLiveData<String>()
    val inputErrorMessage: LiveData<String>
        get() = _inputErrorMessage

    private var currentlySetVision: Vision? = null

    fun setCurrentVision(vision: Vision){
        //if we already have set a vision,
        //we should keep the currently set version
        //as the latest UI state
        if (currentlySetVision?.id == vision.id){
            return
        }
        titleInput.value = vision.userFields.title
        descriptionInput.value = vision.userFields.description
        reasonInput.value = vision.userFields.reason
        priorityToggle.value =
            PriorityConverter().getBoolean(
                    vision.userFields.priority
            )

        currentlySetVision = vision.copy()
    }

    fun addVision(){
        val titleInput = titleInput.value
        if (titleInput == null){
            _inputErrorMessage.value = "タイトルを入力してください"
            return
        }
        val priority = PriorityConverter().getPriority(priorityToggle.value)
        val visionUserFields = VisionUserFields(
                titleInput,
                descriptionInput.value,
                reasonInput.value,
                //low priority is less obtrusive to the user than high priority
                priority ?: Priority.LOW
        )
        currentlySetVision?.let {
            val editedVision = Vision(
                    it.id.copy(),
                    visionUserFields,
                    VisionProperties(dateGenerator.getCurrentTimestamp()),
                    it.status.copy()
                    )
            repository.editVision(editedVision)
        } ?: run {
            val newVision = Vision(
                    ID(null),
                    visionUserFields,
                    VisionProperties(dateGenerator.getCurrentTimestamp()),
                    VisionStatus(false)
            )
            repository.addVision(newVision)
        }
    }
}