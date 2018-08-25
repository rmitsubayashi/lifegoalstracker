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
    private val visionPicker: MediatorLiveData<SpinnerItems> = MediatorLiveData()
    private val datePickerEnabledState: MutableLiveData<DatePickerEnabledState> = MutableLiveData()
    //we replace three month and one month visibility
    private val threeMonthVisibility: MutableLiveData<Boolean> = MutableLiveData()

    private val weekPicker: MutableLiveData<SpinnerItems> = MutableLiveData()
    private val monthPicker: MutableLiveData<SpinnerItems> = MutableLiveData()
    private val threeMonthPicker: MutableLiveData<SpinnerItems> = MutableLiveData()
    private val yearPicker: MutableLiveData<SpinnerItems> = MutableLiveData()

    private val goalSpanSelection: MutableLiveData<SpinnerItems> = MutableLiveData()

    private val goalDescriptionInput: MutableLiveData<String> = MutableLiveData()
    private val goalReasonInput: MutableLiveData<String> = MutableLiveData()

    private val inputErrorMessage: MutableLiveData<String> = MutableLiveData()
    
    init {
        val dropdownItems = AddEditGoalDropdownItems()
        goalSpanSelection.value = SpinnerItems(dropdownItems.getGoalSpans(),
                GoalSpanConverter().getString(GoalSpan.ONE_WEEK)?:dropdownItems.getGoalSpans()[0]
        )
        weekPicker.value = SpinnerItems(dropdownItems.getWeeks(),
                DateConverter().getWeekString(WeekOfMonth.WEEK_ONE)?:dropdownItems.getWeeks()[0]
        )
        monthPicker.value = SpinnerItems(dropdownItems.getMonths(),
                DateConverter().getMonthString(Month.JANUARY)?:dropdownItems.getMonths()[0]
        )
        threeMonthPicker.value = SpinnerItems(dropdownItems.getThreeMonths(),
                DateConverter().getThreeMonthString(Month.MARCH)?:dropdownItems.getThreeMonths()[0]
        )
        yearPicker.value = SpinnerItems(dropdownItems.getYears(),
                dropdownItems.getYears()[0]
        )
        visionPicker.value = SpinnerItems()
    }

    //if the user is editing a goal, we should set that as the preset value
    fun setCurrentGoal(goal: Goal){
        goalDescriptionInput.value = goal.userFields.description
        goalReasonInput.value = goal.userFields.reason

        val date = goal.userFields.dueDate.date
        val dateConverter = DateConverter()
        if (date.week != null) {
            dateConverter.getWeekString(date.week)?.let {
                weekPicker.value?.selectItem(it)
            }

        }
        if (date.month != null) {
            dateConverter.getMonthString(date.month)?.let {
                monthPicker.value?.selectItem(it)
            }
            if (goal.userFields.dueDate.span == GoalSpan.THREE_MONTHS) {
                dateConverter.getThreeMonthString(date.month)?.let {
                    threeMonthPicker.value?.selectItem(it)
                }
            }
        }
        //don't need null checking because all dates have a year value
        yearPicker.value?.selectItem(date.year.yearValue.toString())

        toggleGoalSpanSelection(GoalSpanConverter().getString(goal.userFields.dueDate.span)!!)

        //since we only hav a reference to the vision ID,
        // we need to wait until we get all the user's vision names
        // to be able to display this
    }

    fun addGoal(){
        if (goalDescriptionInput.value == null){
            inputErrorMessage.value = "タイトルを入力してください"
            return
        }

        val inputDueDate = getDueDate()
        if (!InputDateValidator().isValidInput(inputDueDate)){
            inputErrorMessage.value = "正しい日にちを選択してください"
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
                GoalProperties(dateGenerator.getCurrentTimestamp(), ID(visionPicker.value?.getSelectedItem()?.toLong())),
                GoalStatus(false, false)
        )
        goalRepository.addGoal(goal)
    }

    fun editGoal(goal: Goal){
        goalRepository.updateGoal(goal)
    }

    fun fetchVisionPicker(): LiveData<SpinnerItems>{
        val visionNames = visionRepository.getVisionNames()
        visionPicker.addSource(visionNames){
            names -> names?.let{setVisionNames(names)}
        }
        return visionPicker
    }

    private fun setVisionNames(visionNames: List<VisionName>){
        //visionPicker.addSource()
    }

    fun getGoalSpanSelection(): LiveData<SpinnerItems> {
        return goalSpanSelection
    }

    fun toggleGoalSpanSelection(displayedText: String){
        goalSpanSelection.value?.selectItem(displayedText)
        val span = GoalSpanConverter().getSpan(displayedText)
        if (span == null){
            inputErrorMessage.value = "不正な値が入力されました"
            return
        }
        datePickerEnabledState.value?.toggleState(span)
        threeMonthVisibility.value =
                span == GoalSpan.THREE_MONTHS
    }

    fun getWeekPicker(): LiveData<SpinnerItems>{
        return weekPicker
    }

    fun toggleWeekPicker(displayedText: String){
        weekPicker.value?.selectItem(displayedText)
    }

    fun getMonthPicker(): LiveData<SpinnerItems>{
        return monthPicker
    }

    fun toggleMonthPicker(displayedText: String){
        monthPicker.value?.selectItem(displayedText)
    }

    fun getThreeMonthPicker(): LiveData<SpinnerItems>{
        return threeMonthPicker
    }

    fun toggleThreeMonthPicker(displayedText: String){
        threeMonthPicker.value?.selectItem(displayedText)
    }

    fun getYearPicker(): LiveData<SpinnerItems>{
        return yearPicker
    }

    fun toggleYearPicker(displayedText: String){
        yearPicker.value?.selectItem(displayedText)
    }

    fun getGoalDescriptionInput(): LiveData<String>{
        return goalDescriptionInput
    }

    fun setGoalDescriptionInput(inputText: String){
        goalDescriptionInput.value = inputText
    }

    fun getGoalReasonInput(): LiveData<String>{
        return goalReasonInput
    }

    fun setGoalReasonInput(inputText: String){
        goalReasonInput.value = inputText
    }

    private fun getDueDate(): DueDate{
        val span = GoalSpanConverter().getSpan(goalSpanSelection.value?.getSelectedItem()!!)
                as GoalSpan
        val dateConverter = DateConverter()
        return DueDate(
                Date(
                        Year(yearPicker.value?.getSelectedItem()!!.toInt()),
                        when (span){
                            GoalSpan.THREE_MONTHS ->
                                dateConverter.getThreeMonth(threeMonthPicker.value?.getSelectedItem())
                            GoalSpan.ONE_YEAR ->
                                null
                            else ->
                                dateConverter.getMonth(monthPicker.value?.getSelectedItem())
                        },
                        when (span){
                            GoalSpan.ONE_WEEK ->
                                dateConverter.getWeek(weekPicker.value?.getSelectedItem())
                            else ->
                                null
                        }
                ),
                span
        )
    }
}