package com.avinash.navigationpractice.utils

import java.util.Random

class CommonUtils {

    companion object {
        private lateinit var random: Random

        public fun generateRandomNumber(minimumBound: Int, maximumBound: Int): Int {
            random = Random()
            return random.nextInt(maximumBound - minimumBound) + minimumBound
        }
    }
}