package com.lifegoaltracker.utils.filter

interface IFilter<T> {
    fun selectFrom(originalList: List<T>): List<T>
}