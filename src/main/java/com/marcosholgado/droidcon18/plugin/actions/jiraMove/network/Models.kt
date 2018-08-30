package com.marcosholgado.droidcon18.plugin.actions.jiraMove.network

data class Transition(val id: String, val name: String = "") {
    override fun toString(): String = name
}

data class TransitionsResponse(val transitions: List<Transition>)

data class TransitionData(val transition: Transition)

data class Comment(val body: String)