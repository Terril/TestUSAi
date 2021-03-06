class SamePairProblem {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            pairSearch("11001")
            pairSearch("1111111111")
        }

        private fun pairSearch(sequence: String) {

            val array = sequence.toCharArray()
            val pairCountFrequency = HashMap<Char, Int>()
            var sequenceCount = 0

            // Loops through the char array from index as 1 till size-1
            for (index in 1 until array.size) {
                if (array[index - 1] == array[index]) {
                    sequenceCount++
                    addFrequency(pairCountFrequency, array[index - 1])
                }
            }
            if (sequenceCount > 0) {
                print("Same pair count :: $sequenceCount \n")
                pairCountFrequency.forEach {
                    print("Frequencies for :: ${it.key} is ${it.value} \n")
                }
            } else {
                print("Same pair not found")
            }
        }

        /*
* This function will maintain the frequency for same pair
* Uses a hashmap with the binary digit as key and the number of frequency as value
* @param frequencyMap to check for items to get the frequent of a bit
* @param binary is key for the map
* */
        private fun addFrequency(frequencyMap: HashMap<Char, Int>, binary: Char): HashMap<Char, Int> {
            if (frequencyMap.containsKey(binary)) {
                frequencyMap[binary] = frequencyMap.getValue(binary) + 1
            } else {
                frequencyMap[binary] = 1
            }
            return frequencyMap
        }
    }
}