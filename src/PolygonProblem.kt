class PolygonProblem {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val polygon1 = arrayOf(
                Point(1, 1),
                Point(1, 5),
                Point(10, 1),
                Point(10, 5)
            )
            val n = polygon1.size
            val p = Point(5, 6)
            if (isInside(polygon1, n, p)) {
                println("Yes")
            } else {
                println("No")
            }
        }

        var INF = 10000

        class Point(var x: Int, var y: Int)

        // Given three colinear points p, q, r,
        // the function checks if point q lies
        // on line segment 'pr'
        private fun onSegment(p: Point, q: Point, r: Point): Boolean {
            return q.x <= p.x.coerceAtLeast(r.x) &&
                    q.x >= p.x.coerceAtMost(r.x) &&
                    q.y <= p.y.coerceAtLeast(r.y) &&
                    q.y >= p.y.coerceAtMost(r.y)
        }

        // To find orientation of ordered triplet (p, q, r).
        // The function returns following values
        // 0 --> p, q and r are colinear
        // 1 --> Clockwise
        // 2 --> Counterclockwise
        private fun orientation(p: Point, q: Point, r: Point): Int {
            val `val` = ((q.y - p.y) * (r.x - q.x)
                    - (q.x - p.x) * (r.y - q.y))
            if (`val` == 0) {
                return 0 // colinear
            }
            return if (`val` > 0) 1 else 2 // clock or counterclock wise
        }

        // The function that returns true if
        // line segment 'p1q1' and 'p2q2' intersect.
        private fun doIntersect(
            p1: Point, q1: Point,
            p2: Point, q2: Point
        ): Boolean {
            // Find the four orientations needed for
            // general and special cases
            val o1 = orientation(p1, q1, p2)
            val o2 = orientation(p1, q1, q2)
            val o3 = orientation(p2, q2, p1)
            val o4 = orientation(p2, q2, q1)

            // General case
            if (o1 != o2 && o3 != o4) {
                return true
            }

            // Special Cases
            // p1, q1 and p2 are colinear and
            // p2 lies on segment p1q1
            if (o1 == 0 && onSegment(p1, p2, q1)) {
                return true
            }

            // p1, q1 and p2 are colinear and
            // q2 lies on segment p1q1
            if (o2 == 0 && onSegment(p1, q2, q1)) {
                return true
            }

            // p2, q2 and p1 are colinear and
            // p1 lies on segment p2q2
            if (o3 == 0 && onSegment(p2, p1, q2)) {
                return true
            }

            // p2, q2 and q1 are colinear and
            // q1 lies on segment p2q2
            return o4 == 0 && onSegment(p2, q1, q2)

            // Doesn't fall in any of the above cases
        }

        // Returns true if the point p lies
        // inside the polygon[] with n vertices
        private fun isInside(polygon: Array<Point>, n: Int, p: Point): Boolean {
            // There must be at least 3 vertices in polygon[]
            if (n < 3) return false

            // Create a point for line segment from p to infinite
            val extreme: Point = Point(INF, p.y)

            // Count intersections of the above line with sides of polygon
            var count = 0
            var i = 0
            do {
                if (doIntersect(polygon[i], polygon[(i + 1) % n], p, extreme)) {
                    if (onSegment(polygon[i], p, polygon[(i + 1) % n])) return true
                    count++
                }
                i = (i + 1) % n
            } while (i != 0)

            // Return true if count is odd, false otherwise
            return count % 2 == 1 // Same as (count%2 == 1)
        }

    }
}