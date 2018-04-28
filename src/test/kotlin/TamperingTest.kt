
import bloklin.chain.Block
import bloklin.chain.BlockChain
import org.junit.Assert
import org.junit.Test

class TamperingTest {
    @Test
    fun testValidBlock1() {
        val chain = BlockChain()
        val block1 = Block(0, "0", "genesis data")
        chain.addBlock(block1)
        val test = chain.isBlockValid(block1)


        val expected = true
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testValidBlock2() {
        val chain = BlockChain()
        var block1 = Block(0, "0", "genesis data")
        block1.hash = "8920F06BF4D22760A587DC6AFF3977EF3F65FE0127022F346D796230122A3A91"
        block1.timeStamp = 1524930909671
        val block2 = Block(1, "8920F06BF4D22760A587DC6AFF3977EF3F65FE0127022F346D796230122A3A91", "some datas")
        block2.hash = "D6DFC950601B4FE4D1A4FD027C9C5872137F6F96CDB5B3CD6AEFA9C64C51B600"
        block2.timeStamp = 1524930909683
        block2.nonce = 571

        chain.addBlock(block1)
        chain.addBlock(block2)

        val test = chain.isBlockValid(block2)


        val expected = true
        Assert.assertEquals(expected, test)
    }


    @Test
    fun testTamperedBlock1() {
        val chain = BlockChain()
        var block1 = Block(0, "0", "genesis data")
        block1.hash = "8920F06BF4D22760A587DC6AFF3977EF3F65FE0127022F346D796230122A3A91"
        block1.timeStamp = 1524930909671
        val block2 = Block(1, "8920F06BF4D22760A587DC6AFF3977EF3F65FE0127022F346D796230122A3A91", "hackorz")
        block2.hash = "D6DFC950601B4FE4D1A4FD027C9C5872137F6F96CDB5B3CD6AEFA9C64C51B600"
        block2.timeStamp = 1524930909683
        block2.nonce = 571

        chain.addBlock(block1)
        chain.addBlock(block2)

        val test = chain.isBlockValid(block2)


        val expected = false
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testValidChain1() {
        val chain = BlockChain()
        val block1 = Block(0, "0", "genesis data")
        chain.addBlock(block1)
        val test = chain.isChainValid()


        val expected = true
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testValidChain2() {
        val chain = BlockChain()
        var block1 = Block(0, "0", "genesis data")
        block1.hash = "8920F06BF4D22760A587DC6AFF3977EF3F65FE0127022F346D796230122A3A91"
        block1.timeStamp = 1524930909671
        val block2 = Block(1, "8920F06BF4D22760A587DC6AFF3977EF3F65FE0127022F346D796230122A3A91", "some datas")
        block2.hash = "D6DFC950601B4FE4D1A4FD027C9C5872137F6F96CDB5B3CD6AEFA9C64C51B600"
        block2.timeStamp = 1524930909683
        block2.nonce = 571

        chain.addBlock(block1)
        chain.addBlock(block2)
        val test = chain.isChainValid()


        val expected = true
        Assert.assertEquals(expected, test)
    }

    @Test
    fun testTampered() {
        val chain = BlockChain()
        var block1 = Block(0, "0", "genesis data")
        block1.hash = "8920F06BF4D22760A587DC6AFF3977EF3F65FE0127022F346D796230122A3A91"
        block1.timeStamp = 1524930909671
        val block2 = Block(1, "8920F06BF4D22760A587DC6AFF3977EF3F65FE0127022F346D796230122A3A91", "hackorz")
        block2.hash = "D6DFC950601B4FE4D1A4FD027C9C5872137F6F96CDB5B3CD6AEFA9C64C51B600"
        block2.timeStamp = 1524930909683
        block2.nonce = 571

        chain.addBlock(block1)
        chain.addBlock(block2)
        val test = chain.isChainValid()


        val expected = false
        Assert.assertEquals(expected, test)
    }
}