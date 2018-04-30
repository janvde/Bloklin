import bloklin.utils.DificultyUtil
import org.junit.Assert
import org.junit.Test

class DificultyUtilTest {
    @Test
    fun testGetDificultyString0() {
        val test = DificultyUtil.getDificultyString(0)
        val expected = "0"
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testGetDificultyString1() {
        val test = DificultyUtil.getDificultyString(1)
        val expected = "0"
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testGetDificultyString2() {
        val test = DificultyUtil.getDificultyString(2)
        val expected = "00"
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testGetDificultyString5() {
        val test = DificultyUtil.getDificultyString(5)
        val expected = "00000"
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testGetDificulty1() {
        val test = DificultyUtil.getDificulty("dfgh76s976g87d6fgdfg")
        val expected = 0
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testGetDificulty2() {
        val test = DificultyUtil.getDificulty("0fgh76s976g87d6fgdfg")
        val expected = 1
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testGetDificulty3() {
        val test = DificultyUtil.getDificulty("00gh76s976g87d6fgdfg")
        val expected = 2
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testGetDificulty4() {
        val test = DificultyUtil.getDificulty("")
        val expected = 0
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testGetDificulty5() {
        val test = DificultyUtil.getDificulty("0000")
        val expected = 4
        Assert.assertEquals(expected, test)
    }
}