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
    private val _visionTitleInput = MutableLiveData<String>()
    val visionTitleInput =
            _visionTitleInput as LiveData<String>
    private val _visionDescriptionInput = MutableLiveData<String>()
    val visionDescriptionInput =
            _visionDescriptionInput as LiveData<String>
    private val _visionReasonInput = MutableLiveData<String>()
    val visionReasonInput = _visionReasonInput as LiveData<String>
    private val _visionPriorityToggle = MutableLiveData<Boolean>()
    val visionPriorityToggle =
            _visionPriorityToggle as LiveData<Boolean>
    private val _inputErrorMessage = MutableLiveData<String>()
    val inputErrorMessage =
            _inputErrorMessage as LiveData<String>

    private var currentlySetVision: Vision? = null

    fun setCurrentVision(vision: Vision){
        _visionTitleInput.value = vision.userFields.title
        _visionDescriptionInput.value = vision.userFields.description
        _visionReasonInput.value = vision.userFields.reason
        _visionPriorityToggle.value = PriorityConverter().getBoolean(
                vision.userFields.priority
        )

        currentlySetVision = vision.copy()
    }

    fun addVision(){
        if (_visionTitleInput.value == null){
            _inputErrorMessage.value = "タイトルを入力してください"
            return
        }
        val priority = PriorityConverter().getPriority(_visionPriorityToggle.value)
        val visionUserFields = VisionUserFields(
                _visionTitleInput.value!!,
                _visionDescriptionInput.value,
                _visionReasonInput.value,
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

    fun setVisionTitle(inputText: String){
        _visionTitleInput.value = inputText
    }

    fun setVisionDescription(inputText: String){
        _visionDescriptionInput.value = inputText
    }

    fun setVisionReason(inputText: String){
        _visionReasonInput.value = inputText
    }

    fun toggleVisionPriority(toggle: Boolean){
        _visionPriorityToggle.value = toggle
    }
}