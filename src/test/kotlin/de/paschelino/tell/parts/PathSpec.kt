package de.paschelino.tell.parts

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

import de.paschelino.tell.parts.Path.Companion.path
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.dsl.it

object PathSpec : Spek({
    describe("Empty pathes:") {
        it("anEmptyStringProducesAnEmptyPath") {
            assertThat(path("").segments.size, `is`(0))
            assertThat(path(""), `is`(Path.EMPTY))
        }
    }
})
