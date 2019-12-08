package io.mbo.labs.statemachine.samples

import io.mbo.labs.statemachine.Event
import io.mbo.labs.statemachine.State

enum class DocumentState : State { DRAFT, MODERATION, PUBLISHED }

enum class DocumentEvent : Event { PUBLISH, REVIEW, APPROVED, EXPIRED }