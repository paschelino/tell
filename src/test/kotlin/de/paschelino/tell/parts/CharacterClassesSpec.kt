package de.paschelino.tell.parts

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.dsl.it

object CharacterClassesSpec : Spek({
    describe("ALPHA") {
        for(c in (('A'..'Z') + ('a'..'z'))) {
            it("should match $c") {
                assertThat(CharacterClasses.ALPHA.matches(c.toString()), `is`(true))
            }
        }
    }
})