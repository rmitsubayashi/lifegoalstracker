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
    private val viewState = MutableLiveData<AddEditVisionViewState>()
    private val _inputErrorMessage = MutableLiveData<String>()
    val inputErrorMessage =
            _inputErrorMessage as LiveData<String>

    private var currentlySetVision: Vision? = null

    fun setCurrentVision(vision: Vision){
        viewState.value = AddEditVisionViewState(
                vision.userFields.title,
                vision.userFields.description,
                vision.userFields.reason,
                PriorityConverter().getBoolean(
                        vision.userFields.priority
                )
        )

        currentlySetVision = vision.copy()
    }

    fun addVision(){
        val currentViewState = viewState.value

        if (currentViewState?.visionTitleInput == null){
            _inputErrorMessage.value = "タイトルを入力してください"
            return
        }
        val priority = PriorityConverter().getPriority(currentViewState.visionPriorityToggle)
        val visionUserFields = VisionUserFields(
                currentViewState.visionTitleInput,
                currentViewState.visionDescriptionInput,
                currentViewState.visionReasonInput,
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

    fun getViewState(): LiveData<AddEditVisionViewState>{
        if (viewState.value == null){
            viewState.value = AddEditVisionViewState()
        }
        return viewState
    }

    fun setVisionTitle(inputText: String){
        viewState.value = viewState.value?.copy(visionTitleInput = inputText)
    }

    fun setVisionDescription(inputText: String){
        viewState.value = viewState.value?.copy(visionDescriptionInput = inputText)
    }

    fun setVisionReason(inputText: String){
        viewState.value = viewState.value?.copy(visionReasonInput = inputText)
    }

    fun toggleVisionPriority(toggle: Boolean){
        viewState.value = viewState.value?.copy(visionPriorityToggle = toggle)
    }
}

data class AddEditVisionViewState (
        val visionTitleInput: String? = "",
        val visionDescriptionInput: String? = "",
        val visionReasonInput: String? = "",
        val visionPriorityToggle: Boolean? = true
)