package bloklin.utils

import bloklin.Params
import bloklin.chain.BlockChain

object DificultyUtil {
    /**
     * get a string with an amount of zeros
     */
    fun getDificultyString(dificulty: Int): String {
        var result = "0"
        while (result.length < dificulty) result = result + "0"
        return result
    }

    /**
     * get dificulty from hash by counting leading zeros
     */
    fun getDificulty(hash: String): Int {
        return if (hash.startsWith("0")) getDificulty(hash.substring(1)) + 1
        else 0
    }


    /**
     * calculate the next dificulty by comparing the elapsed time between adjustment interval and actual interval
     */
    fun calculateNextDificulty(chain: BlockChain): Int {
        if (chain.size() < Params.DIFICULTY_ADJUSTMENT_INTERVAL) return Params.DEFAULT_DIFICULTY

        //check if the amount of blocks is passed and we need to recalculate the dificulty
        if (chain.size() % Params.DIFICULTY_ADJUSTMENT_INTERVAL == 0) {
            println("should adjust!")
            val lastBlock = chain.lastBlock()
            val heightBlock = chain.getBlock(chain.size() - (Params.DIFICULTY_ADJUSTMENT_INTERVAL - 1))
            if (lastBlock == null || heightBlock == null) return Params.DEFAULT_DIFICULTY

            val lastBlockTimeStamp = lastBlock.timeStamp
            val heightBlockTimeStamp = heightBlock.timeStamp

            val expectedInterval = Params.BLOCK_TIME * Params.DIFICULTY_ADJUSTMENT_INTERVAL
            val actualInterval = lastBlockTimeStamp - heightBlockTimeStamp

            //keep te adjustment within limits
            var adjustment = expectedInterval / actualInterval
            if (adjustment > 2) {
                adjustment = 2
            }
            if (adjustment < (1 / 2)) {
                adjustment = 1 / 2
            }


            val newDificulty = heightBlock.getDificulty() * adjustment

            //keep the dificulty within limits
            return cap(newDificulty.toInt(), Params.DEFAULT_DIFICULTY, Params.DIFICULTY_LIMIT)
        }

        //return the min dificulty over the last blocks
        val endIndex = chain.size()
        val startIndex = endIndex - (chain.size() % Params.DIFICULTY_ADJUSTMENT_INTERVAL)
        return getMinDificulty(chain, startIndex, endIndex)
    }

    fun getMinDificulty(chain: BlockChain, startIndex: Int, endIndex: Int) : Int{
        var minDificulty = Params.DIFICULTY_LIMIT
        for(i in startIndex..endIndex){
            val block = chain.getBlock(i)
            if(block != null ) minDificulty = Math.min(minDificulty, block.getDificulty())
        }

        return minDificulty
    }

    private fun cap(number: Int, min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Int {
        if (number > max) return max
        if (number < min) return min
        else return number
    }
}