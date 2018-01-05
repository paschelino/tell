package de.paschelino.tell.parts

import de.paschelino.tell.parts.Path.Companion.path
import de.paschelino.tell.parts.Segment.Companion.segment
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class SegmentTest {
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

    @Test fun anEmptySegmentProducesAnEmptyString() {
        assertThat(segment("").toString(), `is`(""))
    }

    @Test fun definesAConstantForTheEmptySegment() {
        assertThat(Segment.EMPTY, `is`(segment("")))
    }

    @Test fun addingASegmentProducesAPath() {
        assertThat(segment("a") + segment("b"), `is`(path("/a/b")))
    }

    @Test fun bla() {
        assertThat("a", `is`("b"))
    }
}