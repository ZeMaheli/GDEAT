package com.gdeat.http.pipeline.authentication

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.opentest4j.AssertionFailedError


class RequiresAuthenticationTests {

    @Test
    fun `RequiresAuthentication has FUNCTION and CLASS as its targets (Target Annotation)`() {
        val targetAnnotation = RequiresAuthentication::class.annotations.find { it is Target } as? Target

        assertNotNull(targetAnnotation)
        if (targetAnnotation != null) {
            assertEquals(
                listOf(AnnotationTarget.FUNCTION),
                targetAnnotation.allowedTargets.toList()
            )
        }else throw AssertionFailedError()
    }
}
