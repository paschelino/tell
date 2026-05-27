package de.paschelino.tell.parts

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CharacterClassesSpec : DescribeSpec({
    describe("ALPHA") {
        for (c in (('A'..'Z') + ('a'..'z'))) {
            it("should match $c") {
                CharacterClasses.ALPHA.matches(c.toString()) shouldBe true
            }
        }
    }
})
