import org.junit.Assert
import org.junit.Test

class DificultyUtilTest {
    @Test
    fun testDificulty0() {
        val test = DificultyUtil.getDificultyString(0)
        val expected = "0"
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testDificulty1() {
        val test = DificultyUtil.getDificultyString(1)
        val expected = "0"
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testDificulty2() {
        val test = DificultyUtil.getDificultyString(2)
        val expected = "00"
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testDificulty5() {
        val test = DificultyUtil.getDificultyString(5)
        val expected = "00000"
        Assert.assertEquals(expected, test)
    }
}