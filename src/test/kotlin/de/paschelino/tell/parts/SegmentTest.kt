package de.paschelino.tell.parts

import de.paschelino.tell.parts.Segment.Companion.segment
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SegmentTest {
    @DisplayName("Segment factory")
    @Nested class SegmentFactory {
        @Test fun createWithEmptyToken() {
            assertThat(segment(""), `is`(Segment.EMPTY))
        }

        @Test fun emptyTokenResultsInSlash() {
            assertThat(segment("").toString(), `is`("/"))
        }

        @Test fun createWithNonEmptyTokenNoSlash() {
            assertThat(segment("a").toString(), `is`("/a"))
        }

        @Test fun createWithDifferentNonEmptyTokenNoSlash() {
            assertThat(segment("abc").toString(), `is`("/abc"))
        }

        @Test fun createWithSlashPrefix() {
            assertThat(segment("/a").toString(), `is`("/a"))
        }

        @Test fun createWithMultipleSlashPrefix() {
            assertThat(segment("//a").toString(), `is`("/a"))
        }

        @Test fun createWithSlashPostfix() {
            assertThat(segment("a/").toString(), `is`("/a"))
        }

        @Test fun createWithMultipleSlashPostfix() {
            assertThat(segment("a//").toString(), `is`("/a"))
        }
    }
}