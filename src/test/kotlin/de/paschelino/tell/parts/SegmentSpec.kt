package de.paschelino.tell.parts

import de.paschelino.tell.parts.Path.Companion.path
import de.paschelino.tell.parts.Segment.Companion.segment
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SegmentSpec : DescribeSpec({
    describe("Segment creation:") {
        it("creates with non empty token and no slash") {
            segment("a").toString() shouldBe "/a"
        }

        it("createWithDifferentNonEmptyTokenNoSlash") {
            segment("abc").toString() shouldBe "/abc"
        }

        it("createWithSlashPrefix") {
            segment("/a").toString() shouldBe "/a"
        }

        it("createWithMultipleSlashPrefix") {
            segment("//a").toString() shouldBe "/a"
        }

        it("createWithSlashPostfix") {
            segment("a/").toString() shouldBe "/a"
        }

        it("createWithMultipleSlashPostfix") {
            segment("a//").toString() shouldBe "/a"
        }
    }

    describe("Empty segments:") {
        it("anEmptySegmentProducesAnEmptyString") {
            segment("").toString() shouldBe ""
        }

        it("definesAConstantForTheEmptySegment") {
            Segment.EMPTY shouldBe segment("")
        }
    }

    describe("Segment addition:") {
        it("addingASegmentProducesAPath") {
            segment("a") + segment("b") shouldBe path("/a/b")
        }
    }
})
