package com.lifegoaltracker.utils.uiDisplay

class SpinnerItems () {
    private var list: List<String>? = null
    private var selectedItem: String? = null
    private var tempSelectedItem: String? = null

    constructor(list: List<String>, defaultValue: String): this(){
        setList(list, defaultValue)
    }

    fun selectItem(item: String){
        if (list == null){
            //wait until the list has been initialized.
            //this occurs when we get the list/item
            // asynchronously
            tempSelectedItem = item
            return
        }
        //the list is mutable, but will never be null again,
        // so manually cast to un-nullable list
        if ((list as List<String>).contains(item)) {
            selectedItem = item
        }
    }

    fun getList(): List<String>?{
        return list?.toList()
    }

    fun getSelectedItem(): String? {
        return selectedItem
    }

    fun setList(list: List<String>, defaultValue: String){
        this.list = list.toList()

        tempSelectedItem.let {
            if ((this.list as List<String>).contains(it)){
                selectedItem = it
                return
            }
        }

        selectedItem = defaultValue
    }

}