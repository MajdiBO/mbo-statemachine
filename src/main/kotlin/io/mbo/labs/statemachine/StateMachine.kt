package io.mbo.labs.statemachine

import io.mbo.labs.statemachine.exception.IllegalTransitionException

/**
 * a state is a stored information
 */
interface State

/**
 * an event is an entity that cause a transition between states
 */
interface Event

/**
 * Transition from a state to another by an event
 */
data class Transition<S: State, E: Event>(val from: S, val to: S, val event: E)

/**
 * Finite state machines: Models for any system with a limited number of conditional states of being.
 */
class StateMachine<S : State, E : Event>(var currentState: S, private val transitions: List<Transition<S, E>>) {
    fun onEvent(event: E) {
        val transition = transitions.find { it.from == currentState && it.event == event }
            ?: throw IllegalTransitionException("A transition from $currentState by event $event is not allowed")

        currentState = transition.to
    }
}

