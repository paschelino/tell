package de.paschelino.tell.parts

import de.paschelino.tell.parts.Path.Companion.path
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PathTest {
    @DisplayName("Factory for raw strings")
    @Nested class Raw {
        @Test fun anEmptyStringProducesAnEmptyPath() {
            assertThat(path("").segments.size, `is`(0))
            assertThat(path(""), `is`(Path.EMPTY))
        }
    }

}