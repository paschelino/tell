package de.paschelino.tell.parts

import de.paschelino.tell.parts.Path.Companion.path
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PathSpec : DescribeSpec({
    describe("Empty pathes:") {
        it("anEmptyStringProducesAnEmptyPath") {
            path("").segments.size shouldBe 0
            path("") shouldBe Path.EMPTY
        }
    }
})
