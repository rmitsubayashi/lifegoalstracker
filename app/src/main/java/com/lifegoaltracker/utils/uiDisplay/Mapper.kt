package com.lifegoaltracker.utils.uiDisplay

class Mapper<DisplayType, ModelType>constructor(private val map: List<Pair<DisplayType, ModelType>>) {
    fun getDisplay(of: DisplayType?): ModelType?{
        for (pair in map){
            if (pair.first == of){
                return pair.second
            }
        }
        return null
    }

    fun getModel(of: ModelType?): DisplayType? {
        for (pair in map){
            if (pair.second == of){
                return pair.first
            }
        }
        return null
    }
}