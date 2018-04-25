
import org.junit.Assert
import org.junit.Test

class TamperingTest {
    @Test
    fun testValid1() {
        val blockchain = BlockChain
        blockchain.mineBlock("block1")
        blockchain.mineBlock("block2")
        val test = blockchain.isChainValid()


        val expected = true
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testTampered1() {
        val blockchain = BlockChain
        blockchain.mineBlock("block1")
        blockchain.mineBlock("block2")
        blockchain.chain.get(1).data = "tampered!"
        val test = blockchain.isChainValid()


        val expected = false
        Assert.assertEquals(expected, test)
    }
}