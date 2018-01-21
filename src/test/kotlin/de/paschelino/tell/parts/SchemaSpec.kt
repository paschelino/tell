package de.paschelino.tell.parts

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.dsl.it

object SchemaSpec : Spek({
    describe("Creates canonical schemes:") {
        it("accepts lower case alphanumeric characters") {
            assertThat(Schema("a").toString(), `is`("a"))
            assertThat(Schema("b").toString(), `is`("b"))
        }

        it("accepts upper case alphanumeric characters and lowers them") {
            assertThat(Schema("A").toString(), `is`("a"))
            assertThat(Schema("B").toString(), `is`("b"))
        }

        // TODO: spec helpers, to select characters from a character class / not from that class

        val specialCharacters = listOf("-", "+", ".") + (0..9).map(Int::toString).toList()
        for(s in specialCharacters) {
            it("may not start with the $s") {
                var thrown : MalformedException? = null
                try {
                    Schema(s)
                } catch (e: MalformedException) {
                    thrown = e
                }
                assertThat(thrown, `is`(instanceOf(MalformedException::class.java)))
                assertThat(thrown?.message, `is`("Schema may not start with '$s'!"))
            }

            it("accepts the $s, if it's not the first char") {
                assertThat(Schema("a$s").toString(), `is`("a$s"))
            }
        }
    }
})