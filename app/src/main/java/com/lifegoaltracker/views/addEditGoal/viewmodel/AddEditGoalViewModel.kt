package com.lifegoaltracker.views.addEditGoal.viewmodel

import android.arch.lifecycle.*
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
import com.lifegoaltracker.utils.uiDisplay.DateConverter
import com.lifegoaltracker.utils.date.DateGenerator
import com.lifegoaltracker.utils.uiDisplay.GoalSpanConverter
import com.lifegoaltracker.utils.uiDisplay.SpinnerItems
import javax.inject.Inject

class AddEditGoalViewModel @Inject constructor(private val goalRepository: GoalRepository,
                                               private val visionRepository: VisionRepository,
                                               private val dateGenerator: DateGenerator) : ViewModel(){
    val visions: MediatorLiveData<List<String>> = MediatorLiveData()
    val visionSelection: MutableLiveData<String> = MutableLiveData()
    val datePickerEnabledState: MutableLiveData<DatePickerEnabledState> = MutableLiveData()
    //we replace three month and one month visibility
    val threeMonthVisibility: MutableLiveData<Boolean> = MutableLiveData()

    val weeks: MutableLiveData<List<String>> = MutableLiveData()
    val months: MutableLiveData<List<String>> = MutableLiveData()
    val threeMonths: MutableLiveData<List<String>> = MutableLiveData()
    val years: MutableLiveData<List<String>> = MutableLiveData()
    val weekSelection: MutableLiveData<String> = MutableLiveData()
    val monthSelection: MutableLiveData<String> = MutableLiveData()
    val threeMonthsSelection: MutableLiveData<String> = MutableLiveData()
    val yearSelection: MutableLiveData<String> = MutableLiveData()

    val goalSpans: MutableLiveData<List<String>> = MutableLiveData()
    val goalSpanSelection: MutableLiveData<String> = MutableLiveData()

    val goalDescriptionInput: MutableLiveData<String> = MutableLiveData()
    val goalReasonInput: MutableLiveData<String> = MutableLiveData()

    val inputErrorMessage: MutableLiveData<String> = MutableLiveData()

    private var allVisionNames: List<VisionName>? = null
    //if the user is editing
    private var currentlySetGoal: Goal? = null
    //if the user is creating a new vision
    private var currentlySetVisionID: ID? = null
    
    init {
        val dropdownItems = AddEditGoalDropdownItems()
        goalSpans.value = dropdownItems.getGoalSpans()
        goalSpanSelection.value = GoalSpanConverter().getString(GoalSpan.ONE_WEEK)
        weeks.value = dropdownItems.getWeeks()
        weekSelection.value = DateConverter().getWeekString(WeekOfMonth.WEEK_ONE)
        months.value = dropdownItems.getMonths()
        monthSelection.value = DateConverter().getMonthString(Month.JANUARY)
        threeMonths.value = dropdownItems.getThreeMonths()
        threeMonthsSelection.value = DateConverter().getThreeMonthString(Month.MARCH)
        years.value = dropdownItems.getYears()
        yearSelection.value = dropdownItems.getYears()[0]
        visions.value = emptyList()
        visionSelection.value = ""
        fetchVisionNames()
    }

    fun setCurrentVision(visionID: ID){
        this.currentlySetVisionID = visionID
        allVisionNames?.let{
            for (visionName in it){
                if (visionName.id === currentlySetVisionID){
                    visionSelection.value = visionName.title
                    break
                }
            }
        }
    }

    //if the user is editing a goal, we should set that as the preset value
    fun setCurrentGoal(goal: Goal){
        goalDescriptionInput.value = goal.userFields.description
        goalReasonInput.value = goal.userFields.reason

        val date = goal.userFields.dueDate.date
        val dateConverter = DateConverter()
        if (date.week != null) {
            dateConverter.getWeekString(date.week)?.let {
                weekSelection.value =it
            }

        }
        if (date.month != null) {
            dateConverter.getMonthString(date.month)?.let {
                monthSelection.value = it
            }
            if (goal.userFields.dueDate.span == GoalSpan.THREE_MONTHS) {
                dateConverter.getThreeMonthString(date.month)?.let {
                    threeMonthsSelection.value = it
                }
            }
        }
        //don't need null checking because all dates have a year value
        yearSelection.value = date.year.yearValue.toString()

        toggleGoalSpanVisibility(GoalSpanConverter().getString(goal.userFields.dueDate.span)!!)

        //since we only have a reference to the vision ID,
        // we need to wait until we get all the user's vision names.
        allVisionNames?.let{
            for (visionName in it){
                if (goal.properties.visionID == visionName.id){
                    visionSelection.value = visionName.title
                    break
                }
            }
        } //if vision names aren't loaded yet, set when we load it

        currentlySetGoal = goal.copy()
    }

    fun addGoal(){
        if (!validateInput()){
            return
        }

        val userFields = GoalUserFields(
                goalDescriptionInput.value!!,
                goalReasonInput.value,
                getDueDate()
        )
        val goal = Goal(
                ID(null),
                userFields,
                // TODO get selected ID
                GoalProperties(dateGenerator.getCurrentTimestamp(), ID(1)),
                GoalStatus(false, false)
        )
        goalRepository.addGoal(goal)
    }

    fun editGoal(){
        if (!validateInput()){
            return
        }

        val userFields = GoalUserFields(
                goalDescriptionInput.value!!,
                goalReasonInput.value,
                getDueDate()
        )
        val goal = Goal(
                ID(null),
                userFields,
                // TODO get selected ID
                GoalProperties(dateGenerator.getCurrentTimestamp(), ID(1)),
                GoalStatus(false, false)
        )
        goalRepository.updateGoal(goal)
    }

    private fun validateInput(): Boolean {
        if (goalDescriptionInput.value == null){
            inputErrorMessage.value = "タイトルを入力してください"
            return false
        }

        if (!InputDateValidator().isValidInput(getDueDate())){
            inputErrorMessage.value = "正しい日にちを選択してください"
            return false
        }

        return true
    }

    private fun fetchVisionNames(){
        val visionNames = visionRepository.getVisionNames()
        visions.addSource(visionNames){
            names -> names?.let{setVisionNames(names)}
        }
    }

    private fun setVisionNames(visionNames: List<VisionName>){
        allVisionNames = visionNames.toList()
        val visionNameStrings = mutableListOf<String>()
        for (visionName in visionNames){
            visionNameStrings.add(visionName.title)
        }

        var defaultValue = visionNameStrings[0]
        currentlySetGoal?.let{
            for (visionName in visionNames){
                if (it.properties.visionID == visionName.id){
                    defaultValue = visionName.title
                    break
                }
            }
        }
        currentlySetVisionID?.let {
            for (visionName in visionNames){
                if (visionName.id == currentlySetVisionID){
                    defaultValue = visionName.title
                    break
                }
            }
        }
        visions.value = visionNameStrings
        visionSelection.value = defaultValue
    }

    fun toggleGoalSpanVisibility(displayedText: String){
        val span = GoalSpanConverter().getSpan(displayedText)
        if (span == null){
            inputErrorMessage.value = "不正な値が入力されました"
            return
        }
        datePickerEnabledState.value?.toggleState(span)
        threeMonthVisibility.value =
                span == GoalSpan.THREE_MONTHS
    }

    private fun getDueDate(): DueDate{
        val span = GoalSpanConverter().getSpan(goalSpanSelection.value!!)
                as GoalSpan
        val dateConverter = DateConverter()
        return DueDate(
                Date(
                        Year(yearSelection.value!!.toInt()),
                        when (span){
                            GoalSpan.THREE_MONTHS ->
                                dateConverter.getThreeMonth(threeMonthsSelection.value)
                            GoalSpan.ONE_YEAR ->
                                null
                            else ->
                                dateConverter.getMonth(monthSelection.value)
                        },
                        when (span){
                            GoalSpan.ONE_WEEK ->
                                dateConverter.getWeek(weekSelection.value)
                            else ->
                                null
                        }
                ),
                span
        )
    }
}