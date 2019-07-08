package com.example.vcfr67.simpleapplication.tools

class SingleEvent<T>(private val eventData: T) {
    private var isEventHandled = false

    fun getEventIfNotHandled(): T? {
        return if (isEventHandled) {
            null
        } else {
            isEventHandled = true
            eventData
        }
    }

    fun viewEvent(): T = eventData

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SingleEvent<*>) return false

        if (eventData != other.eventData) return false
        if (isEventHandled != other.isEventHandled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = eventData?.hashCode() ?: 0
        result = 31 * result + isEventHandled.hashCode()
        return result
    }

    override fun toString(): String {
        return "SingleEvent(eventData=$eventData, isEventHandled=$isEventHandled)"
    }
}
