package io.mbo.labs.statemachine

import io.mbo.labs.statemachine.exception.IllegalTransitionException
import io.mbo.labs.statemachine.samples.DocumentEvent
import io.mbo.labs.statemachine.samples.DocumentState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StateMachineTest{
    private val stateMachine: StateMachine<DocumentState, DocumentEvent> = StateMachine(
        currentState = DocumentState.DRAFT,
        transitions = listOf(
            Transition(DocumentState.DRAFT, DocumentState.MODERATION, DocumentEvent.PUBLISH),
            Transition(DocumentState.MODERATION, DocumentState.DRAFT, DocumentEvent.REVIEW),
            Transition(DocumentState.MODERATION, DocumentState.PUBLISHED, DocumentEvent.APPROVED),
            Transition(DocumentState.PUBLISHED, DocumentState.DRAFT, DocumentEvent.EXPIRED)
            )
    )

    @Test
    fun `check transition success`(){
        assertEquals(DocumentState.DRAFT, stateMachine.currentState)

        stateMachine.onEvent(DocumentEvent.PUBLISH)
        assertEquals(DocumentState.MODERATION, stateMachine.currentState)

        stateMachine.onEvent(DocumentEvent.REVIEW)
        assertEquals(DocumentState.DRAFT, stateMachine.currentState)

    }

    @Test
    fun `check transition failed`(){
        assertEquals(DocumentState.DRAFT, stateMachine.currentState)

        assertThrows<IllegalTransitionException> { stateMachine.onEvent(DocumentEvent.APPROVED) }
    }

}