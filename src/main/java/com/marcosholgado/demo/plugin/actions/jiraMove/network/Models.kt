package com.marcosholgado.demo.plugin.actions.jiraMove.network

data class Transition(val id: String, val name: String = "") {
    override fun toString(): String = name
}

data class TransitionsResponse(val transitions: List<Transition>)

data class TransitionData(val transition: Transition)

data class Comment(val body: String)

data class SearchResponse(val issues: List<Issue>)

data class Issue(val key: String, val fields: Field, val transitions: List<Transition>) {
    override fun toString(): String = "$key ${fields.summary}"
}

data class Field(val summary: String, val description: Description?)

data class Description(val content: List<DescriptionContent>)

data class DescriptionContent(val type: String, val content: List<Content>)

data class Content(val type: String, val text: String, val marks: List<Mark>?)

data class Mark(val type: String)

data class AssigneeData(val name: String)