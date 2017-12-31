package de.paschelino.tell

import de.paschelino.tell.parts.*
import de.paschelino.tell.parts.Path.Companion.path
import de.paschelino.tell.parts.Segment.Companion.segment
import org.junit.jupiter.api.Test
import org.hamcrest.MatcherAssert.*
import org.hamcrest.CoreMatchers.*

class UriTest {
    @Test fun complexUrlTest() {
        val schema = Schema("https")
        val authority : Authority = UserInfo("leia.organa") + Host("www.example.org") + Port(8080)
        val subPath = Path(listOf(segment("to"))) + segment("/a") + segment("/resource/")
        val path = path("some/path") + subPath
        val hierPart : HierPart = authority + path
        val query : Query = Param("some", "query") + Param("with", "params")
        val fragment = Fragment("fragment")
        val uri: Uri = schema + hierPart + query + fragment
        assertThat(
                uri.toString(),
                `is`("https://leia.organa@www.example.org:8080/some/path/to/a/resource?some=query&with=params#fragment")
        )
    }

    @Test fun urlWithoutUserInfoTest() {
        val schema = Schema("https")
        val authority : Authority = Host("www.example.org") + Port(8080)
        val path = path("some") + path("path")
        val hierPart : HierPart = authority + path
        val query : Query = Param("some", "query") + Param("with", "params")
        val fragment = Fragment("fragment")
        val uri: Uri = schema + hierPart + query + fragment
        assertThat(
                uri.toString(),
                `is`("https://www.example.org:8080/some/path?some=query&with=params#fragment")
        )
    }

    @Test fun urlWithSimpleHostAndPathTest() {
        val schema = Schema("https")
        val path = path("some") + path("path")
        val hierPart : HierPart = Host("www.example.org") + path
        val query : Query = Param("some", "query") + Param("with", "params")
        val fragment = Fragment("fragment")
        val uri: Uri = schema + hierPart + query + fragment
        assertThat(
                uri.toString(),
                `is`("https://www.example.org/some/path?some=query&with=params#fragment")
        )
    }

    @Test fun urlWithSimpleHostTest() {
        val schema = Schema("https")
        val hierPart : HierPart = Host("www.example.org")
        val query : Query = Param("some", "query") + Param("with", "params")
        val fragment = Fragment("fragment")
        val uri: Uri = schema + hierPart + query + fragment
        assertThat(
                uri.toString(),
                `is`("https://www.example.org?some=query&with=params#fragment")
        )
    }

    @Test fun urlWithPathTest() {
        val schema = Schema("https")
        val hierPart : HierPart = path("some") + path("path")
        val query : Query = Param("some", "query") + Param("with", "params")
        val fragment = Fragment("fragment")
        val uri: Uri = schema + hierPart + query + fragment
        assertThat(
                uri.toString(),
                `is`("https:/some/path?some=query&with=params#fragment")
        )
    }


}