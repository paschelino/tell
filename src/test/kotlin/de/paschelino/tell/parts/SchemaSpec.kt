package de.paschelino.tell.parts

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SchemaSpec : DescribeSpec({
    describe("Creates canonical schemes:") {
        it("accepts lower case alphanumeric characters") {
            Schema("a").toString() shouldBe "a"
            Schema("b").toString() shouldBe "b"
        }

        it("accepts upper case alphanumeric characters and lowers them") {
            Schema("A").toString() shouldBe "a"
            Schema("B").toString() shouldBe "b"
        }

        val specialCharacters = listOf("-", "+", ".") + (0..9).map(Int::toString)
        for (s in specialCharacters) {
            it("may not start with the $s") {
                val thrown = shouldThrow<MalformedException> {
                    Schema(s)
                }
                thrown.message shouldBe "Schema may not start with '$s'!"
            }

            it("accepts the $s, if it's not the first char") {
                Schema("a$s").toString() shouldBe "a$s"
            }
        }
    }
})
