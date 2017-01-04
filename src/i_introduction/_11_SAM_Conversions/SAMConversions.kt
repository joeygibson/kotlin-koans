package i_introduction._11_SAM_Conversions

import util.TODO
import util.doc11
import java.util.*

fun todoTask11(): Nothing = TODO(
        """
        Task 11.
        When an object implements a SAM interface (one with a Single Abstract Method), you can pass a lambda instead.
        Read more about SAM conversions in the blog posts about Kotlin.

        Rewrite the previous example changing an object expression to a lambda.
    """,
        documentation = doc11()
)

fun task11(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, { x, y ->
        if (x == null && y == null) 0
        else if (x == null) -1
        else if (y == null) 1
        else y.compareTo(x)
    })
    return arrayList
}
