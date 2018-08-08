package com.marcosholgado.droidcon18.plugin.actions.jira.network

data class Transition(val id: String, val name: String)

data class TransitionsResponse(val transitions: List<Transition>)