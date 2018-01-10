package de.paschelino.tell.parts

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

import de.paschelino.tell.parts.Path.Companion.path
import de.paschelino.tell.parts.Segment.Companion.segment
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.dsl.it

object SegmentSpec : Spek({
    describe("Segment creation:") {
        it("creates with non empty token and no slash") {
            assertThat(segment("a").toString(), `is`("/a"))
        }

        it("createWithDifferentNonEmptyTokenNoSlash") {
            assertThat(segment("abc").toString(), `is`("/abc"))
        }

        it("createWithSlashPrefix") {
            assertThat(segment("/a").toString(), `is`("/a"))
        }

        it("createWithMultipleSlashPrefix") {
            assertThat(segment("//a").toString(), `is`("/a"))
        }

        it("createWithSlashPostfix") {
            assertThat(segment("a/").toString(), `is`("/a"))
        }

        it("createWithMultipleSlashPostfix") {
            assertThat(segment("a//").toString(), `is`("/a"))
        }

        it("breaks") {
            assertThat(1, `is`(2))
        }
    }

    describe("Empty segments:") {
        it("anEmptySegmentProducesAnEmptyString") {
            assertThat(segment("").toString(), `is`(""))
        }

        it("definesAConstantForTheEmptySegment") {
            assertThat(Segment.EMPTY, `is`(segment("")))
        }
    }

    describe("Segment addition:") {
        it("addingASegmentProducesAPath") {
            assertThat(segment("a") + segment("b"), `is`(path("/a/b")))
        }
    }
})
