package de.paschelino.tell.parts

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

import org.hamcrest.CoreMatchers.`is`
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
    }
})